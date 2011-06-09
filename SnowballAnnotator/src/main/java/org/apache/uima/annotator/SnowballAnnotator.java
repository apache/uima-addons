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

package org.apache.uima.annotator;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.apache.uima.TokenAnnotation;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.Language;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Level;
import org.apache.uima.util.Logger;
import org.tartarus.snowball.SnowballProgram;
import org.tartarus.snowball.ext.danishStemmer;
import org.tartarus.snowball.ext.dutchStemmer;
import org.tartarus.snowball.ext.englishStemmer;
import org.tartarus.snowball.ext.finnishStemmer;
import org.tartarus.snowball.ext.frenchStemmer;
import org.tartarus.snowball.ext.germanStemmer;
import org.tartarus.snowball.ext.hungarianStemmer;
import org.tartarus.snowball.ext.italianStemmer;
import org.tartarus.snowball.ext.norwegianStemmer;
import org.tartarus.snowball.ext.portugueseStemmer;
import org.tartarus.snowball.ext.russianStemmer;
import org.tartarus.snowball.ext.spanishStemmer;
import org.tartarus.snowball.ext.swedishStemmer;

public class SnowballAnnotator extends JCasAnnotator_ImplBase {

  private Logger logger;

  private HashMap<String,SnowballProgram> stemmers;

  private static final Object[] emptyArgs = new Object[0];

  public void process(JCas aJCas) throws AnalysisEngineProcessException {

    this.logger.log(Level.INFO, "Snowball annotator starts processing");

    // get get stemmer for the document language
    String language = new Language(aJCas.getDocumentLanguage()).getLanguagePart();

    SnowballProgram stemmer = this.stemmers.get(language);

    // create stemms if stemmer for the current document language is available
    if (stemmer != null) {

      // get stem() method from stemmer
      Method stemmerStemMethod;
      try {
        stemmerStemMethod = stemmer.getClass().getMethod("stem", new Class[0]);
      } catch (Exception ex) {
        throw new AnalysisEngineProcessException(ex);
      }

      // iterate over all token annotations and add stem if available
      FSIterator<Annotation> tokenIterator = aJCas.getAnnotationIndex(TokenAnnotation.type).iterator();
      while (tokenIterator.hasNext()) {
        // get token content
        TokenAnnotation annot = (TokenAnnotation) tokenIterator.next();
        String span = annot.getCoveredText();

        // set annotation content and call stemmer
        try {
          stemmer.setCurrent(span);
          stemmerStemMethod.invoke(stemmer, emptyArgs);
        } catch (Exception ex) {
          throw new AnalysisEngineProcessException(ex);
        }

        // get stemmer result and set annotation feature
        annot.setStem(stemmer.getCurrent());
      }
    } else {
      if (language.equals("x")) {
        this.logger.log(Level.WARNING, "Language of the CAS is set to 'x', SnowballAnnotator skipped processing.");
      }
    }
    this.logger.log(Level.INFO, "Snowball annotator processing finished");
  }

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    // initialize logger
    try {
      this.logger = aContext.getLogger();

      // initialize stemmers
      this.stemmers = new HashMap<String, SnowballProgram>();
      this.stemmers.put("da", new danishStemmer());
      this.stemmers.put("nl", new dutchStemmer());
      this.stemmers.put("en", new englishStemmer());
      this.stemmers.put("fi", new finnishStemmer());
      this.stemmers.put("fr", new frenchStemmer());
      this.stemmers.put("de", new germanStemmer());
      this.stemmers.put("hu", new hungarianStemmer());
      this.stemmers.put("it", new italianStemmer());
      this.stemmers.put("no", new norwegianStemmer());
      this.stemmers.put("pt", new portugueseStemmer());
      this.stemmers.put("ru", new russianStemmer());
      this.stemmers.put("es", new spanishStemmer());
      this.stemmers.put("sw", new swedishStemmer());
    } catch (Exception ex) {
      throw new ResourceInitializationException(ex);
    }

    this.logger.log(Level.INFO, "Snowball annotator successfully initialized");
  }
}
