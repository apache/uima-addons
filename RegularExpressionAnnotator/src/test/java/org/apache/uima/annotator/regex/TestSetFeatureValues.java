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
public class TestSetFeatureValues extends TestCase {

   /**
    * test all possible feature values and types use Rule with id=ID1
    * 
    * @throws Exception
    */
   public void testSetFeatureValues1() throws Exception {

      // create annotation tester with the regex annotator specifier
      AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("setFeatureValues/RegExAnnotFeatureValues.xml"));
      CAS cas = annotTester.performTest("RuleRegex12.4", "en");

      // define result interested in
      String[] tofs = { "uima.tcas.DocumentAnnotation:language",
            "org.apache.uima.TestAnnot",
            "org.apache.uima.TestAnnot:testFeature0",
            "org.apache.uima.TestAnnot:testFeature1",
            "org.apache.uima.TestAnnot:testFeature2",
            "org.apache.uima.TestAnnot:testFeature3",
            "org.apache.uima.TestAnnot:testFeature4",
            "org.apache.uima.TestAnnot:testFeature5",
            "org.apache.uima.TestAnnot:testFeature6",
            "org.apache.uima.TestAnnot:testFeature7",
            "org.apache.uima.TestAnnot:testFeature8",
            "org.apache.uima.TestAnnot:testFeature9",
            "org.apache.uima.TestAnnot:testFeature10",
            "org.apache.uima.TestAnnot:testFeature11",
            "org.apache.uima.TestAnnot:testFeature12",
            "org.apache.uima.TestAnnot:testFeature13",
            "org.apache.uima.TestAnnot:testFeature14",
            "uima.tcas.DocumentAnnotation:language1",
            "uima.tcas.DocumentAnnotation:language2",
            "uima.tcas.DocumentAnnotation:refFeature",
            "uima.tcas.DocumentAnnotation:ruleID",
            "org.apache.uima.TestAnnot:confidenceValue",
            "org.apache.uima.TestAnnot:ruleId", "org.apache.uima.TestAnnot1" };

      // compare results
      File outputFile = new File(JUnitExtension.getFile("setFeatureValues"),
            "featureValues_testoutput.txt");
      AnnotatorTester.checkResult(cas, tofs, JUnitExtension
            .getFile("setFeatureValues/featureValuesRef.txt"), outputFile);

   }

   /**
    * test all possbible feature values and types with the second rule of a
    * concept. Especially the confidence and rule ID. Use Rule with id=ID2
    * 
    * @throws Exception
    */
   public void testSetFeatureValues2() throws Exception {

      // create annotation tester with the regex annotator specifier
      AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("setFeatureValues/RegExAnnotFeatureValues.xml"));
      CAS cas = annotTester.performTest("RuleRegex22.4", "en");

      // define result interested in
      String[] tofs = { "uima.tcas.DocumentAnnotation:language",
            "org.apache.uima.TestAnnot",
            "org.apache.uima.TestAnnot:testFeature0",
            "org.apache.uima.TestAnnot:testFeature1",
            "org.apache.uima.TestAnnot:testFeature2",
            "org.apache.uima.TestAnnot:testFeature3",
            "org.apache.uima.TestAnnot:testFeature4",
            "org.apache.uima.TestAnnot:testFeature5",
            "org.apache.uima.TestAnnot:testFeature6",
            "org.apache.uima.TestAnnot:testFeature7",
            "org.apache.uima.TestAnnot:testFeature8",
            "org.apache.uima.TestAnnot:testFeature9",
            "org.apache.uima.TestAnnot:testFeature10",
            "org.apache.uima.TestAnnot:testFeature11",
            "org.apache.uima.TestAnnot:testFeature12",
            "org.apache.uima.TestAnnot:testFeature13",
            "org.apache.uima.TestAnnot:testFeature14",
            "uima.tcas.DocumentAnnotation:language1",
            "uima.tcas.DocumentAnnotation:language2",
            "uima.tcas.DocumentAnnotation:refFeature",
            "uima.tcas.DocumentAnnotation:ruleID",
            "org.apache.uima.TestAnnot:confidenceValue",
            "org.apache.uima.TestAnnot:ruleId", "org.apache.uima.TestAnnot1" };

      // compare results
      File outputFile = new File(JUnitExtension.getFile("setFeatureValues"),
            "featureValues2_testoutput.txt");
      AnnotatorTester.checkResult(cas, tofs, JUnitExtension
            .getFile("setFeatureValues/featureValuesRef2.txt"), outputFile);

   }

   /**
    * test match group names. Use Rule with id=ID3
    * 
    * @throws Exception
    */
   public void testSetFeatureValues3() throws Exception {

      // create annotation tester with the regex annotator specifier
      AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("setFeatureValues/RegExAnnotFeatureValues.xml"));
      CAS cas = annotTester.performTest("RuleRegex32.4", "en");

      // define result interested in
      String[] tofs = { "org.apache.uima.TestAnnot",
            "org.apache.uima.TestAnnot:testFeature0",
            "org.apache.uima.TestAnnot:testFeature1",
            "org.apache.uima.TestAnnot:testFeature2",
            "org.apache.uima.TestAnnot:testFeature3",
            "org.apache.uima.TestAnnot:testFeature4",
            "org.apache.uima.TestAnnot:testFeature7" };

      // compare results
      File outputFile = new File(JUnitExtension.getFile("setFeatureValues"),
            "featureValues3_testoutput.txt");
      AnnotatorTester.checkResult(cas, tofs, JUnitExtension
            .getFile("setFeatureValues/featureValuesRef3.txt"), outputFile);

   }

   /**
    * test all setFeature values for confidence and ruleID without have
    * specified these values in the concept file.
    * 
    * @throws Exception
    */
   public void testSetFeatureValuesForConfidenceAndRuleID() throws Exception {

      // create annotation tester with the regex annotator specifier
      AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("setFeatureValues/RegExAnnotFeatureValues.xml"));
      CAS cas = annotTester.performTest("Minimal Configuration", "en");

      // define result interested in
      String[] tofs = { "org.apache.uima.TestAnnot",
            "org.apache.uima.TestAnnot:confidenceValue",
            "org.apache.uima.TestAnnot:ruleId" };

      // compare results
      File outputFile = new File(JUnitExtension.getFile("setFeatureValues"),
            "miniConfig_testoutput.txt");
      AnnotatorTester.checkResult(cas, tofs, JUnitExtension
            .getFile("setFeatureValues/miniConfigRef.txt"), outputFile);

   }

}
