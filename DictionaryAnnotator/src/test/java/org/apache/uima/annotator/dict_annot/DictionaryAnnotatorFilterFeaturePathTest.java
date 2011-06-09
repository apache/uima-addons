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
package org.apache.uima.annotator.dict_annot;

import java.io.File;

import junit.framework.TestCase;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Feature;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.test.junit_extension.AnnotatorTester;
import org.apache.uima.test.junit_extension.JUnitExtension;
import org.apache.uima.util.XMLInputSource;

/**
 * Dictionary annotator test. This test class tests the dictionary annotator
 * code with input filter featurePath settings
 */
public class DictionaryAnnotatorFilterFeaturePathTest extends TestCase {

   /**
    * Test the dictionary annotator processing with an simple input filter
    * feature path setting.
    * 
    * @throws Exception
    */
   public void testDictionaryAnnotatorWithSimpleFilterFeaturePath()
         throws Exception {

      AnalysisEngine ae = null;
      // Create an XML input source from the specifier file.
      XMLInputSource in = new XMLInputSource(
            JUnitExtension
                  .getFile("DictionaryAnnotatorTestFilterFeaturePath/DictionaryAnnotatorFilterFeaturePath.xml"));
      // Parse the specifier.
      ResourceSpecifier specifier = UIMAFramework.getXMLParser()
            .parseResourceSpecifier(in);
      // Create the Text Analysis Engine.
      ae = UIMAFramework.produceAnalysisEngine(specifier);

      // Create a new CAS and set data
      CAS cas = ae.newCAS();
      cas.setDocumentText("nEw yOrk City");
      cas.setDocumentLanguage("en");

      Feature feat = cas.getDocumentAnnotation().getType().getFeatureByBaseName("featureToAnalyze");
      cas.getDocumentAnnotation().setStringValue(feat, "equalsTest");

      // Process cas
      ae.process(cas);

      // define result interested in
      String[] tofs = { "org.apache.uima.DictionaryEntry" };

      // compare results
      File outputFile = new File(JUnitExtension
            .getFile("DictionaryAnnotatorTestFilterFeaturePath"),
            "dictionaryAnnotFeaturePathTest_testoutput.txt");
      AnnotatorTester
            .checkResult(
                  cas,
                  tofs,
                  JUnitExtension
                        .getFile("DictionaryAnnotatorTestFilterFeaturePath/dictionaryAnnotFeaturePathTestRef.txt"),
                  outputFile);
   }

   /**
    * Test the dictionary annotator processing with an simple input filter
    * feature path setting of type int.
    * 
    * @throws Exception
    */
   public void testDictionaryAnnotatorWithSimpleFilterFeaturePathInt()
         throws Exception {

      AnalysisEngine ae = null;
      // Create an XML input source from the specifier file.
      XMLInputSource in = new XMLInputSource(
            JUnitExtension
                  .getFile("DictionaryAnnotatorTestFilterFeaturePath/DictionaryAnnotatorFilterFeaturePathInt.xml"));
      // Parse the specifier.
      ResourceSpecifier specifier = UIMAFramework.getXMLParser()
            .parseResourceSpecifier(in);
      // Create the Text Analysis Engine.
      ae = UIMAFramework.produceAnalysisEngine(specifier);

      // Create a new CAS and set data
      CAS cas = ae.newCAS();
      cas.setDocumentText("nEw yOrk City");
      cas.setDocumentLanguage("en");

      Feature feat = cas.getDocumentAnnotation().getType().getFeatureByBaseName("featureToAnalyze");
      cas.getDocumentAnnotation().setIntValue(feat, 22);

      // Process cas
      ae.process(cas);

      // define result interested in
      String[] tofs = { "org.apache.uima.DictionaryEntry" };

      // compare results
      File outputFile = new File(JUnitExtension
            .getFile("DictionaryAnnotatorTestFilterFeaturePath"),
            "dictionaryAnnotFeaturePathTest_testoutput.txt");
      AnnotatorTester
            .checkResult(
                  cas,
                  tofs,
                  JUnitExtension
                        .getFile("DictionaryAnnotatorTestFilterFeaturePath/dictionaryAnnotFeaturePathTestRef.txt"),
                  outputFile);
   }

   /**
    * Test the dictionary annotator processing with an simple input filter
    * feature path setting of type boolean.
    * 
    * @throws Exception
    */
   public void testDictionaryAnnotatorWithSimpleFilterFeaturePathBoolean()
         throws Exception {

      AnalysisEngine ae = null;
      // Create an XML input source from the specifier file.
      XMLInputSource in = new XMLInputSource(
            JUnitExtension
                  .getFile("DictionaryAnnotatorTestFilterFeaturePath/DictionaryAnnotatorFilterFeaturePathBoolean.xml"));
      // Parse the specifier.
      ResourceSpecifier specifier = UIMAFramework.getXMLParser()
            .parseResourceSpecifier(in);
      // Create the Text Analysis Engine.
      ae = UIMAFramework.produceAnalysisEngine(specifier);

      // Create a new CAS and set data
      CAS cas = ae.newCAS();
      cas.setDocumentText("nEw yOrk City");
      cas.setDocumentLanguage("en");

      Feature feat = cas.getDocumentAnnotation().getType().getFeatureByBaseName("featureToAnalyze");
      cas.getDocumentAnnotation().setBooleanValue(feat, true);

      // Process cas
      ae.process(cas);

      // define result interested in
      String[] tofs = { "org.apache.uima.DictionaryEntry" };

      // compare results
      File outputFile = new File(JUnitExtension
            .getFile("DictionaryAnnotatorTestFilterFeaturePath"),
            "dictionaryAnnotFeaturePathTest_testoutput.txt");
      AnnotatorTester
            .checkResult(
                  cas,
                  tofs,
                  JUnitExtension
                        .getFile("DictionaryAnnotatorTestFilterFeaturePath/dictionaryAnnotFeaturePathTestRef.txt"),
                  outputFile);
   }

}
