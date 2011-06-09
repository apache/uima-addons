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
package org.apache.uima.annotator.dict_annot.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.CasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.analysis_engine.annotator.AnnotatorContextException;
import org.apache.uima.annotator.dict_annot.dictionary.Dictionary;
import org.apache.uima.annotator.dict_annot.dictionary.DictionaryFileParser;
import org.apache.uima.annotator.dict_annot.dictionary.DictionaryMatch;
import org.apache.uima.annotator.dict_annot.dictionary.impl.DictionaryFileParserImpl;
import org.apache.uima.annotator.dict_annot.dictionary.impl.HashMapDictionaryBuilder;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Level;
import org.apache.uima.util.Logger;

/**
 * Dictionary annotator implementation that use HashMap dictionaries
 */
public class DictionaryAnnotator extends CasAnnotator_ImplBase {

  /**
   * Message catalog
   */
  public static final String MESSAGE_DIGEST = "org.apache.uima.annotator.dict_annot.dictionaryAnnotatorMessages";

  /**
   * System path separator
   */
  public static final String PATH_SEPARATOR = System.getProperty("path.separator");

  // DictionaryFiles configuration parameter name
  private static final String DICTIONARY_FILES = "DictionaryFiles";

  // InputMatchType configuration parameter name
  private static final String INPUT_MATCH_TYPE = "InputMatchType";

  // InputMatchFeaturePath configuration parameter name
  private static final String INPUT_MATCH_FEATURE_PATH = "InputMatchFeaturePath";

  // InputMatchFilterFeaturePath configuration parameter name
  private static final String INPUT_MATCH_FILTER_FEATURE_PATH = "InputMatchFilterFeaturePath";

  // FilterConditionOperator configuration parameter name
  private static final String FILTER_CONDITION_OPERATOR = "FilterConditionOperator";

  // FilterConditionValue configuration parameter name
  private static final String FILTER_CONDITION_VALUE = "FilterConditionValue";

  // annotator logger
  private Logger logger;

  // input match type name
  private String inputMatchTypeStr;

  // input match feature path
  private String inputMatchFeaturePathStr;

  // input match feature path
  private String inputMatchFilterFeaturePathStr;

  // input match feature path
  private String filterConditionOperator;

  // input match feature path
  private String filterConditionValue;

  // input match type
  private Type inputMatchType;

  // dictionaries used with this annotator
  private Dictionary[] dictionaries;

  // inputMatchFeaturePath object
  private FeaturePathInfo_impl inputMatchFeaturePath;

  // inputMatchFilterFeaturePath object
  private FeaturePathInfo_impl inputMatchFilterFeaturePath;

  // filterCondition object
  private Condition filterCondition;

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.analysis_component.CasAnnotator_ImplBase#process(org.apache.uima.cas.CAS)
   */
  public void process(CAS cas) throws AnalysisEngineProcessException {

    // copy input match type annotations to an array
    FSIterator it = cas.getAnnotationIndex(this.inputMatchType).iterator();
    ArrayList<AnnotationFS> inputTypeAnnots = new ArrayList<AnnotationFS>();
    while (it.hasNext()) {
      // get next annotation FS
      AnnotationFS annotFS = (AnnotationFS) it.next();
      // check if we have to filter the annotation
      if (this.inputMatchFilterFeaturePathStr != null) {
        // check annotation filter condition
        if (this.inputMatchFilterFeaturePath.match(annotFS, this.filterCondition)) {
          inputTypeAnnots.add(annotFS);
        }
      } else { // no annotation filter specified
        inputTypeAnnots.add(annotFS);
      }

    }
    AnnotationFS[] annotFSs = inputTypeAnnots.toArray(new AnnotationFS[] {});

    // -- use the array of annotations to detect matches --

    for (int i = 0; i < this.dictionaries.length; i++) {
      // get current dictionary output type
      Type currentDictOutputType = cas.getTypeSystem().getType(this.dictionaries[i].getTypeName());
      // check output type and throw an exception in case of errors
      if (currentDictOutputType == null) {
        throw new DictionaryAnnotatorProcessException("dictionary_annotator_error_resolving_types",
                new Object[] { this.dictionaries[i].getTypeName() });
      }

      // iterate over the annotation array and detect matches
      int currentPos = 0;
      while (currentPos < annotFSs.length) {

        // check for dictionary matches at the current token position
        DictionaryMatch dictMatch = this.dictionaries[i].matchEntry(currentPos, annotFSs,
                this.inputMatchFeaturePath);

        // check if we have a dictionary match
        if (dictMatch != null) {
          // -- we have found a match starting at the current position --

          // get match length of the match
          int matchLength = dictMatch.getMatchLength();

          // create annotation for the match we found
          int start = annotFSs[currentPos].getBegin();
          int end = annotFSs[currentPos + matchLength - 1].getEnd();
          FeatureStructure fs = cas.createAnnotation(currentDictOutputType, start, end);
          // add annotation to the CAS
          cas.getIndexRepository().addFS(fs);
          // adjust current array position, add match length
          currentPos = currentPos + matchLength;
        } else {
          // -- no match was found, go on with the next token --
          currentPos++;
        }
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.analysis_component.AnalysisComponent_ImplBase#initialize(org.apache.uima.UimaContext)
   */
  public void initialize(UimaContext context) throws ResourceInitializationException {
    super.initialize(context);

    // initialize annotator logger
    this.logger = this.getContext().getLogger();

    // get configuration parameter settings
    // get parameter ConceptFiles, default is an empty array
    String[] dictionaryFileNames = safeGetConfigParameterStringArrayValue(getContext(),
            DICTIONARY_FILES, new String[] {});

    // get input match type
    this.inputMatchTypeStr = (String) this.getContext().getConfigParameterValue(INPUT_MATCH_TYPE);

    // get input match feature path
    this.inputMatchFeaturePathStr = (String) this.getContext().getConfigParameterValue(
            INPUT_MATCH_FEATURE_PATH);

    // initialize inputMatchFeaturePath - this must only be done if a feature
    // path was specified
    this.inputMatchFeaturePath = new FeaturePathInfo_impl();
    if (this.inputMatchFeaturePathStr != null) {
      this.inputMatchFeaturePath.initialize(this.inputMatchFeaturePathStr);
    }

    // get input match filter feature path
    this.inputMatchFilterFeaturePathStr = (String) this.getContext().getConfigParameterValue(
            INPUT_MATCH_FILTER_FEATURE_PATH);

    // initialize inputMatchFilterFeaturePath - this must only be done if a
    // feature path was specified
    this.inputMatchFilterFeaturePath = new FeaturePathInfo_impl();
    if (this.inputMatchFilterFeaturePathStr != null) {
      this.inputMatchFilterFeaturePath.initialize(this.inputMatchFilterFeaturePathStr);
    }

    // get filter condition operator
    this.filterConditionOperator = (String) this.getContext().getConfigParameterValue(
            FILTER_CONDITION_OPERATOR);

    // get filter condition value
    this.filterConditionValue = (String) this.getContext().getConfigParameterValue(
            FILTER_CONDITION_VALUE);

    // check filter condition if we have a filter condition feature path
    if (this.inputMatchFilterFeaturePathStr != null) {
      if (this.filterConditionOperator == null) {
        throw new DictionaryAnnotatorConfigException(
                "dictionary_annotator_error_missing_config_parameter",
                new Object[] { FILTER_CONDITION_OPERATOR });
      }
      if (this.filterConditionValue == null) {
        throw new DictionaryAnnotatorConfigException(
                "dictionary_annotator_error_missing_config_parameter",
                new Object[] { FILTER_CONDITION_VALUE });
      }

      // get condition operator
      FilterOp operator = Condition.getOperator(this.filterConditionOperator);
      if (operator == null) {
        throw new DictionaryAnnotatorConfigException(
                "dictionary_annotator_error_condition_operator_not_valid",
                new Object[] { this.filterConditionOperator });
      }

      // create new Condition object
      this.filterCondition = new Condition(operator, this.filterConditionValue);

      // log filter condition
      StringBuffer buffer = new StringBuffer();
      buffer.append(this.inputMatchTypeStr);
      buffer.append(":");
      buffer.append(this.inputMatchFilterFeaturePathStr);
      buffer.append(" ");
      buffer.append(operator.toString());
      buffer.append(" ");
      buffer.append(this.filterConditionValue);
      this.logger.logrb(Level.CONFIG, "DictionaryAnnotator", "initialize", MESSAGE_DIGEST,
              "dictionary_annotator_filter_feature_condition", new Object[] { buffer.toString() });
    }

    // create dictionary file parser
    DictionaryFileParser fileParser = new DictionaryFileParserImpl();

    // get UIMA datapath and tokenize it into its elements
    StringTokenizer tokenizer = new StringTokenizer(getContext().getDataPath(), PATH_SEPARATOR);
    ArrayList<File> datapathElements = new ArrayList<File>();
    while (tokenizer.hasMoreTokens()) {
      // add datapath elements to the 'datapathElements' array list
      datapathElements.add(new File(tokenizer.nextToken()));
    }

    // parse dictionary files
    ArrayList<Dictionary> dicts = new ArrayList<Dictionary>();
    for (int i = 0; i < dictionaryFileNames.length; i++) {
      // try to resolve the relative file name with classpath or datapath
      DictionaryFile file = resolveRelativeFilePath(dictionaryFileNames[i], datapathElements);

      // if the current dictionary file wasn't found, throw an exception
      if (file == null) {
        throw new DictionaryAnnotatorConfigException("dictionary_annotator_resource_not_found",
                new Object[] { dictionaryFileNames[i] });
      } else {
        // log concept file path
        this.logger.logrb(Level.CONFIG, "DictionaryAnnotator", "initialize", MESSAGE_DIGEST,
                "dictionary_annotator_dictionary_file", new Object[] { file.getFilePath() });

        // parse dictionary file
        Dictionary dict = fileParser.parseDictionaryFile(file.getFilePath(), file.getStream(),
                new HashMapDictionaryBuilder());
        // add dictionary to the dictionary list
        dicts.add(dict);
      }
    }

    // store all dictionaries in the dictionary array
    this.dictionaries = dicts.toArray(new Dictionary[] {});
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.analysis_component.CasAnnotator_ImplBase#typeSystemInit(org.apache.uima.cas.TypeSystem)
   */
  public void typeSystemInit(TypeSystem typeSystem) throws AnalysisEngineProcessException {

    // initialize the input match type that is used to match the dictionary
    // entries
    this.inputMatchType = typeSystem.getType(this.inputMatchTypeStr);
    if (this.inputMatchType == null) {
      throw new DictionaryAnnotatorProcessException("dictionary_annotator_error_resolving_types",
              new Object[] { this.inputMatchTypeStr });
    }

    // validate inputMatchFeaturePath for given type - this must only be done
    // if a
    // feature path was specified
    if (this.inputMatchFeaturePathStr != null) {
      this.inputMatchFeaturePath.typeSystemInit(this.inputMatchType);
    }

    // validate inputMatchFilterfeaturePath for given type - this must only be
    // done if a
    // feature path was specified
    if (this.inputMatchFilterFeaturePathStr != null) {
      this.inputMatchFilterFeaturePath.typeSystemInit(this.inputMatchType);
    }
  }

  /**
   * Reads the given configuration parameters and returns the parameter value. If the parameter is
   * not available or the parameter type is not a String[], the given default value is returned.
   * 
   * @param context
   *          Annotator context
   * @param param
   *          configuration parameter to read
   * @param defaultValue
   *          default parameter value in case of errors
   * @return returns the boolean parameter value
   * @throws AnnotatorContextException
   *           if an unrecoverable error occurs
   */
  private static String[] safeGetConfigParameterStringArrayValue(UimaContext context, String param,
          String[] defaultValue) {
    String[] array = (String[]) context.getConfigParameterValue(param);
    if (array != null && array.length > 0) {
      return array;
    }
    return defaultValue;
  }

  /**
   * Resolves the absolute file name of the given relative file name using the given datapath path
   * elements. If the resolution was successful the File object is returned, if not null.
   * 
   * @param fileName
   *          relative file name to resolve
   * 
   * @param datapathElements
   *          datapath path elements
   * 
   * @return returns the File object of the resolved file, otherwise null.
   */
  /**
   * @param context
   * @param param
   * @param defaultValue
   * @return returns the boolean parameter value
   * @throws AnnotatorContextException
   */
  private DictionaryFile resolveRelativeFilePath(String fileName, ArrayList<File> datapathElements)
          throws DictionaryAnnotatorConfigException {

    DictionaryFile dictionaryFile;
    URL url = null;

    // check first if the current fileName is an URL
    if (fileName.startsWith("http")) {
      // try to open http connection to get the stream
      try {
        // create URL object
        url = new URL(fileName);
        // create URL connection
        URLConnection connection = url.openConnection();
        // get stream from URL connection
        dictionaryFile = new DictionaryFile(url.toString(), new BufferedInputStream(connection
                .getInputStream()));
        return dictionaryFile;
      } catch (MalformedURLException ex) {
        throw new DictionaryAnnotatorConfigException("dictionary_annotator_invalid_url_resource",
                new Object[] { fileName }, ex);
      } catch (IOException ex) {
        throw new DictionaryAnnotatorConfigException("dictionary_annotator_invalid_url_resource",
                new Object[] { fileName }, ex);
      }
    }
    // try to use the class loader to load the file resource
    else if ((url = this.getClass().getClassLoader().getResource(fileName)) != null) {
      // we have successfully resolved the concept file, now also get it as
      // stream
      InputStream stream = this.getClass().getClassLoader().getResourceAsStream(fileName);
      dictionaryFile = new DictionaryFile(url.getFile(), new BufferedInputStream(stream));
      return dictionaryFile;
    } else {
      if (datapathElements == null || datapathElements.size() == 0) {
        return null;
      }
      // try to use the datapath to load the file resource
      for (int i = 0; i < datapathElements.size(); i++) {
        File testFile = new File(datapathElements.get(i), fileName);
        if (testFile.exists()) {
          InputStream stream;
          try {
            stream = new BufferedInputStream(new FileInputStream(testFile));
          } catch (FileNotFoundException ex) {
            return null;
          }
          dictionaryFile = new DictionaryFile(testFile.getAbsolutePath(), stream);
          return dictionaryFile;
        }
      }
    }
    return null;

  }

  /**
   * Helper class to bundle the XML dictionary file name and the dictionary file input stream to one
   * object.
   */
  private static class DictionaryFile {
    // concept file path name
    private String filePath;

    // concept file stream
    private InputStream stream;

    /**
     * creates a new dictionaryFile object with the file path and the stream
     * 
     * @param filePath
     *          concept file path
     * 
     * @param stream
     *          concept file stream
     */
    public DictionaryFile(String filePath, InputStream stream) {
      this.filePath = filePath;
      this.stream = stream;
    }

    /**
     * Returns the dictionary file path name
     * 
     * @return concept file path name
     */
    public String getFilePath() {
      return this.filePath;
    }

    /**
     * Returns the dictionary file stream
     * 
     * @return concept file stream
     */
    public InputStream getStream() {
      return this.stream;
    }
  }
}
