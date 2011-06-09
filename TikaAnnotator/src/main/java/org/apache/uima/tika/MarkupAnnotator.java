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

package org.apache.uima.tika;

import org.apache.tika.config.TikaConfig;
import org.apache.tika.language.LanguageIdentifier;
import org.apache.tika.language.LanguageProfile;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.Parser;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.CasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Level;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;


/**
 * Uses TIKA to convert original markup into UIMA annotations*
 */
public class MarkupAnnotator extends CasAnnotator_ImplBase {


  private final static String ORIGINAL_VIEW_PARAM_NAME = "ORIGINAL_VIEW_PARAM_NAME";
  private final static String TEXT_VIEW_PARAM_NAME = "TEXT_VIEW_PARAM_NAME";
  private final static String SET_TEXT_VIEW_DEFAULT_PARAM_NAME = "SET_TEXT_VIEW_DEFAULT_PARAM_NAME";

  private final static String tika_file_param = "tikaConfigFile";

  // takes an option indicating the name of the view containing the binary document
  private String originalViewName = "_InitialView";

  // takes an option indicating the name of the view containing the text version of the document
  private String textViewName = "textView";

  // whether to make the text view default or not
  private Boolean makeTextDefaultView = true;

  // configuration for TIKA - can be created by specifying a custom resource
  private TikaConfig config = null;

  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    // Get config param setting
    originalViewName = (String) aContext.getConfigParameterValue(ORIGINAL_VIEW_PARAM_NAME);

    textViewName = (String) aContext.getConfigParameterValue(TEXT_VIEW_PARAM_NAME);
    if (textViewName == null) {
      this.getContext().getLogger().log(Level.WARNING,
              new StringBuffer("Parameter TEXT_VIEW_PARAM_NAME is null; setting to \"textView\"").toString());
      textViewName = "textView";
    } else
      this.getContext().getLogger().log(Level.WARNING, new StringBuffer("Parameter TEXT_VIEW_PARAM_NAME is ")
              .append(textViewName).toString());

    makeTextDefaultView = (Boolean) aContext.getConfigParameterValue(SET_TEXT_VIEW_DEFAULT_PARAM_NAME);
    if (makeTextDefaultView == null) {
      this.getContext().getLogger().log(Level.WARNING,
              new StringBuffer("Parameter SET_TEXT_VIEW_DEFAULT_PARAM_NAME is null; setting to \"true\"").toString());
      makeTextDefaultView = new Boolean(true);
    } else
      this.getContext().getLogger().log(Level.WARNING, new StringBuffer("Parameter SET_TEXT_VIEW_DEFAULT_PARAM_NAME is ")
              .append(makeTextDefaultView).toString());
    // initialise TIKA parser
    // try to get a custom config
    URL tikaConfigURL = null;
    try {
      tikaConfigURL = getContext().getResourceURL(tika_file_param);
      config = new TikaConfig(tikaConfigURL);
    } catch (Exception e1) {
      // to log
      this.getContext().getLogger().log(Level.WARNING, new StringBuffer("Failed to load TIKA config file from ")
              .append(tikaConfigURL).append(" due to ").append(e1.getLocalizedMessage()).toString());
      config = null;
    }

    // if not rely on default one
    if (config == null) {
      try {
        config = TikaConfig.getDefaultConfig();
      } catch (Exception e) {
        throw new ResourceInitializationException(e);
      }
    }

  }

  public void process(CAS cas) throws AnalysisEngineProcessException {
    CAS originalCas = null;
    try {
      originalCas = cas.getView(originalViewName);
    }
    catch (Exception e) {
      String viewName = cas.getViewName();
      // can't find originalViewName
      this.getContext().getLogger().log(Level.WARNING, new StringBuffer("can't find view ").append(originalViewName)
              .append(" using ").append(viewName).append(" instead").toString());
      originalCas = cas.getCurrentView();
    }

    InputStream originalStream = originalCas.getSofa().getSofaDataStream();

    // parsing with TIKA

    // TODO if content type is known then we use it
    // otherwise we guess

    Parser parser = new AutoDetectParser(config);

    Metadata md = new Metadata();
    MarkupHandler handler = new MarkupHandler();

    try {
      parser.parse(originalStream, handler, md);
    }
    catch (Exception e) {
      // if we have a problem just dump the message and continue
      this.getContext().getLogger().log(Level.WARNING, new StringBuffer("Problem converting file : ")
              .append(e.getMessage()).toString());
      // PROBLEM => trying to serialize binary content in XML crash!
      return;
    }
    finally {
      try {
        originalStream.close();
      } catch (IOException e) {
      }
    }

    CAS plainTextView = cas.createView(textViewName);


    handler.populateCAS(plainTextView);

    // get additional metadata about the document
    // e.g content type etc...
    // TODO add possibility to define type as parameter and discover
    // feature names on the fly
    JCas ptv = null;
    try {
      ptv = plainTextView.getJCas();
    } catch (CASException e) {
      e.printStackTrace();
      return;
    }
    /* identify language */
    extractLanguage(ptv);

    Type docAnnotationType = ptv.getTypeSystem().getType("org.apache.uima.tika.SourceDocumentAnnotation");
    Iterator iter = ptv.getAnnotationIndex(docAnnotationType).iterator();
    SourceDocumentAnnotation docAnnotation = null;
    // do we already have one?
    if (iter.hasNext()) docAnnotation = (SourceDocumentAnnotation) iter.next();
      // otherwise let's create a new annotation
    else docAnnotation = new SourceDocumentAnnotation(ptv);

    // now iterate on the metadata found by Tika and add them to the info
    if (docAnnotation.getFeatures() == null)
      docAnnotation.setFeatures((FSArray) cas.createArrayFS(md.size()));

    for (int i = 0; i < md.size(); i++) {
      String name = md.names()[i];
      String value = md.get(name);
      FeatureValue fv = new FeatureValue(ptv);
      fv.setName(name);
      fv.setValue(value);
      docAnnotation.setFeatures(i, fv);
    }
    docAnnotation.addToIndexes();

  }

  private void extractLanguage(JCas plainTextView) {
    try {
      LanguageIdentifier li = new LanguageIdentifier(new LanguageProfile(plainTextView.getDocumentText()));
      if (li.getLanguage() != null && !"".equals(li.getLanguage()))
        plainTextView.setDocumentLanguage(li.getLanguage());
    }
    catch (Exception e) {
      this.getContext().getLogger().log(Level.WARNING, new StringBuffer("Could not extract language due to ")
              .append(e.getLocalizedMessage()).toString());
    }
    this.getContext().getLogger().log(Level.INFO,new StringBuffer("Extracted language: ").append(plainTextView
            .getDocumentLanguage()).toString());
  }

}
