/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.examples.tagger;

import org.apache.uima.TokenAnnotation;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.analysis_engine.annotator.AnnotatorConfigurationException;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.examples.tagger.trainAndTest.MappingInterface;
import org.apache.uima.examples.tagger.trainAndTest.ModelGeneration;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * UIMA Analysis Engine that invokes HMM POS tagger. HMM POS tagger generates part-of-speech tags
 * for every token. This annotator assumes that sentences and tokens have already been annotated in the CAS
 * with Sentence and Token annotations, respectively. We iterate over sentences, then iterate over
 * tokens in the current sentence to accumulate a list of words, then invoke the HMM POS tagger on
 * the list of words. For each Token we then update the posTag field with the POS tag. The model
 * file for the HMM POS tagger is specified as a parameter (MODEL_FILE_PARAM).
 * <p/>
 * The configuration of this analysis engine is done through several parameters:
 * <ul>
 * <li>InputView: - the view to process </li>
 * <li>SentenceType: the annotation type which covers token annotations</li>
 * <li>TokenFeaturePath: - feature path to the token feature to be tagged.
 * The annotation should exactly cover a "word".</li>
 * <li>ModelFile: - the path from where the model will be read</li>
 * </ul>
 */

public class HMMTagger extends JCasAnnotator_ImplBase implements Tagger {

  private static final String model_file_param = "Model";

  private static final String n_param = "NGRAM_SIZE";

  /**
   * Name of the parameter for the input view
   */
  public static String PARAM_INPUT_VIEW = "InputView";
  /**
   * Name of the parameter for the model import path
   */
  public static String PARAM_IMPORT_MODEL_FILE = "ModelFile";
  /**
   * Name of the parameter for the annotation type which covers token annotations
   */
  public static String PARAM_SENTENCE = "SentenceType";
  /**
   * Name of the parameter for the feature path to the token feature to be tagged
   */
  public static String PARAM_TOKEN_FP = "TokenFeaturePath";

  /**
   * Default Input View value
   */
  private static final String DEFAULT_INPUT_VIEW = "_InitialView";
  /**
   * Default sentence annotation type which covers token annotations
   */
  private static final String DEFAULT_SENTENCE_TYPE = "org.apache.uima.SentenceAnnotation";
  /**
   * Default token feature path value
   */
  private static final String DEFAULT_TOKEN_FEATURE_PATH = "org.apache.uima.TokenAnnotation:posTag";

  /**
   * The view from which the tokens will be extracted
   */
  private String inputView;
  /**
   * The path to the file where the model will be read
   */
  private String modelFile;
  /**
   * The Annotation type which covers the token annotations
   */
  private String theSentenceTypeName;
  /**
   * The type to which we will set the POS tag feature
   */
  private String theTokenTypeName;
  /**
   * The name of the attribute where the POS to tag is stored
   */
  private String thePOSAttribute;

  /**
   * for a bigram model: N = 2, for a trigram model N=3 N is defined in parameter file
   */
  public int N;

  // public String END_OF_SENT_TAG;

  public ModelGeneration my_model;

  MappingInterface MAPPING;

  /**
   * Initialize the Annotator.
   *
   * @see JCasAnnotator_ImplBase#initialize(UimaContext)
   */
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    // Configure the component
    this.inputView =
            (String) aContext.getConfigParameterValue(PARAM_INPUT_VIEW);
    // Set a default inputView value
    if (this.inputView == null) {
      this.inputView = DEFAULT_INPUT_VIEW;
    }


    // Configure the sentence
    this.theSentenceTypeName =
            (String) aContext.getConfigParameterValue(PARAM_SENTENCE);
    // Set a default sentence type name
    if (this.theSentenceTypeName == null) {
      this.theSentenceTypeName = DEFAULT_SENTENCE_TYPE;
    }

    // Compute the type and the attribute name
    String fpPOS =
            (String) aContext.getConfigParameterValue(PARAM_TOKEN_FP);
    // Set a default token and feature path value
    if (fpPOS == null) {
      fpPOS = DEFAULT_TOKEN_FEATURE_PATH;
    }
    Integer idx = fpPOS.lastIndexOf(":");
    if (idx >= 0) {
      this.theTokenTypeName = fpPOS.substring(0, idx);
      this.thePOSAttribute = fpPOS.substring(idx + 1);
    } else {
      throw new ResourceInitializationException("The feature path passed " +
              "in parameter ('" + fpPOS + "') is not valid. " +
              "It should be like : 'type.name:attribute'", null);
    }

    try {

      this.N = ((Integer) aContext.getConfigParameterValue(n_param)).intValue();

      this.modelFile =
              (String) aContext.getConfigParameterValue(PARAM_IMPORT_MODEL_FILE);

      if (this.modelFile != null) {
        // in that case, the multiple deployment cannot be allowed
        this.my_model = get_model(this.modelFile);
      } else {
        this.my_model = get_model();
      }

      if (this.my_model == null) {
        throw new ResourceInitializationException(new Exception("Could not load model file."));
      }

    } catch (Exception e) {
      throw new ResourceInitializationException(e);
    }
  }

  /**
   * Reads a saved {@code MODEL} object from a file
   *
   * @param filename model file
   * @return {@link ModelGeneration}
   */
  public static ModelGeneration get_model(String filename) {

    System.out.println("The used model is:" + filename);

    InputStream model = null;
    ModelGeneration oRead = null;

    try {
      model = new FileInputStream(filename);
      ObjectInputStream p = new ObjectInputStream(model);
      oRead = (ModelGeneration) p.readObject();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } finally {
      try {
        model.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return oRead;
  }

  private ModelGeneration get_model() throws AnnotatorConfigurationException {

    IModelResource modelResource = null;
    try {
      modelResource = (IModelResource) getContext().getResourceObject(model_file_param);
    } catch (Exception e) {
      throw new AnnotatorConfigurationException(e);
    }

    return modelResource.getModel();
  }


  /**
   * Process a CAS.
   *
   * @see JCasAnnotator_ImplBase#process(JCas)
   */
  @SuppressWarnings("unchecked")
  public void process(JCas aJCas) throws AnalysisEngineProcessException {

    // Select the view we will work on
    JCas workingView = null;
    try {
      workingView = aJCas.getView(this.inputView);
    } catch (CASException e1) {
      throw new AnalysisEngineProcessException(e1);
    }

    ArrayList<Annotation> tokenList = new ArrayList<Annotation>();

    ArrayList<String> wordList = new ArrayList<String>();

    AnnotationIndex sentenceIndex = workingView.getAnnotationIndex(getType(workingView, this.theSentenceTypeName));
    AnnotationIndex tokenIndex = workingView.getAnnotationIndex(getType(workingView, this.theTokenTypeName));
    // iterate over Sentences
    FSIterator sentenceIterator = sentenceIndex.iterator();

    while (sentenceIterator.hasNext()) {
      Annotation sentence = (Annotation) sentenceIterator.next();

      tokenList.clear();
      wordList.clear();

      FSIterator tokenIterator = tokenIndex.subiterator(sentence);
      while (tokenIterator.hasNext()) {
        TokenAnnotation token = (TokenAnnotation) tokenIterator.next();

        tokenList.add(token);
        wordList.add(token.getCoveredText());
      }

      List<String> wordTagList = Viterbi.process(this.N, wordList, this.my_model.suffix_tree, this.my_model.suffix_tree_capitalized, this.my_model.transition_probs,
              this.my_model.word_probs, this.my_model.lambdas2, this.my_model.lambdas3, this.my_model.theta);


      try {
        for (int i = 0; i < tokenList.size(); i++) {
          Annotation token = tokenList.get(i);

          String posTag = wordTagList.get(i);
          Feature featPOS = getType(workingView, this.theTokenTypeName).getFeatureByBaseName(thePOSAttribute);
          token.setFeatureValueFromString(featPOS, posTag);

        }
      } catch (IndexOutOfBoundsException e) {
        System.err.println("POS tagger error - list of tags shorter than list of words");
      }
    }
  }

  /**
   * Get the type of a given annotation name and check if it exists
   *
   * @param aJCas
   * @param annotationString
   * @return annotationType
   * @throws AnalysisEngineProcessException
   */
  public static Type getType(JCas aJCas, String annotationString) throws AnalysisEngineProcessException {

    // get Type from string and verify existence on TypeSystem
    Type annotationType = null;
    annotationType = aJCas.getTypeSystem().getType(
            annotationString);
    if ((annotationType == null)) {
      throw new AnalysisEngineProcessException(new StringBuilder("Error: Type ").append(annotationString)
              .append(" is not defined in the Type System !").toString(), new Object[]{annotationType});
    }
    return annotationType;
  }
}
