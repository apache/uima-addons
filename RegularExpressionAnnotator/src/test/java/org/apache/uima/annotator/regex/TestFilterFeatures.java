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
package org.apache.uima.annotator.regex;

import java.io.File;

import junit.framework.TestCase;

import org.apache.uima.cas.CAS;
import org.apache.uima.test.junit_extension.AnnotatorTester;
import org.apache.uima.test.junit_extension.JUnitExtension;

/**
 * 
 * 
 */
public class TestFilterFeatures extends TestCase {

  /**
   * Test filter feature where the filter condition is correct and the 
   * annotation is created.
   * 
   * @throws Exception
   */
  public void testFilterFeaturesConditionCorrect() throws Exception {

    // create annotation tester with the regex annotator specifier
    AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("filters/RegExAnnotFilterFeature.xml"));
    CAS cas = annotTester.performTest("SimpleRegex", "en");

    // define result interested in
    String[] tofs = { "uima.tcas.DocumentAnnotation:language", "org.apache.uima.TestAnnot" };

    // compare results
    File outputFile = new File(JUnitExtension.getFile("filters"),
            "filterFeatureCorrect_testoutput.txt");
    AnnotatorTester.checkResult(cas, tofs, JUnitExtension
            .getFile("filters/filterFeatureCorrectRef.txt"), outputFile);
  }

  /**
   * Test filter feature where the filter condition is not correct and the 
   * annotation is not created.
   * 
   * @throws Exception
   */
  public void testFilterFeaturesConditionWrong() throws Exception {

    // create annotation tester with the regex annotator specifier
    AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("filters/RegExAnnotFilterFeature.xml"));
    CAS cas = annotTester.performTest("SimpleRegex", "en-US");

    // define result interested in
    String[] tofs = { "uima.tcas.DocumentAnnotation:language", "org.apache.uima.TestAnnot" };

    // compare results
    File outputFile = new File(JUnitExtension.getFile("filters"),
            "filterFeatureWrong_testoutput.txt");
    AnnotatorTester.checkResult(cas, tofs, JUnitExtension
            .getFile("filters/filterFeatureWrongRef.txt"), outputFile);
  }

  /**
   * Test filter feature where the filter has an advanced condition that is correct and the 
   * annotation is created.
   * 
   * @throws Exception
   */
  public void testFilterFeaturesAdvancedCondition1() throws Exception {

    // create annotation tester with the regex annotator specifier
    AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("filters/RegExAnnotFilterFeature.xml"));
    CAS cas = annotTester.performTest("AdvancedFilterRegex", "en-US");

    // define result interested in
    String[] tofs = { "uima.tcas.DocumentAnnotation:language", "org.apache.uima.TestAnnot" };

    // compare results
    File outputFile = new File(JUnitExtension.getFile("filters"),
            "filterFeatureAdvanced1_testoutput.txt");
    AnnotatorTester.checkResult(cas, tofs, JUnitExtension
            .getFile("filters/filterFeatureAdvanced1Ref.txt"), outputFile);
  }

  /**
   * Test filter feature where the filter has an advanced condition that is correct and the 
   * annotation is created.
   * 
   * @throws Exception
   */
  public void testFilterFeaturesAdvancedCondition2() throws Exception {

    // create annotation tester with the regex annotator specifier
    AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("filters/RegExAnnotFilterFeature.xml"));
    CAS cas = annotTester.performTest("AdvancedFilterRegex", "en-GB");

    // define result interested in
    String[] tofs = { "uima.tcas.DocumentAnnotation:language", "org.apache.uima.TestAnnot" };

    // compare results
    File outputFile = new File(JUnitExtension.getFile("filters"),
            "filterFeatureAdvanced2_testoutput.txt");
    AnnotatorTester.checkResult(cas, tofs, JUnitExtension
            .getFile("filters/filterFeatureAdvanced2Ref.txt"), outputFile);
  }

  /**
   * Test filter feature on an annotation that was just created before.
   * 
   * @throws Exception
   */
  public void testFilterFeaturesOnCreatedAnnot() throws Exception {

    // create annotation tester with the regex annotator specifier
    AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("filters/RegExAnnotFilterFeature.xml"));
    CAS cas = annotTester.performTest("JustCreateAnAnnotation", "en");

    // define result interested in
    String[] tofs = { "org.apache.uima.TestAnnot", "org.apache.uima.TestAnnot:testFeature" };

    // compare results
    File outputFile = new File(JUnitExtension.getFile("filters"),
            "filterFeatureOnCreatedAnnot_testoutput.txt");
    AnnotatorTester.checkResult(cas, tofs, JUnitExtension
            .getFile("filters/filterFeatureOnCreatedAnnotRef.txt"), outputFile);
  }

}
