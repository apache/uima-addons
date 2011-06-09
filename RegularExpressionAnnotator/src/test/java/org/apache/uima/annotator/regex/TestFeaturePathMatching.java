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

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.uima.annotator.regex.impl.RegexAnnotatorProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.test.junit_extension.AnnotatorTester;
import org.apache.uima.test.junit_extension.JUnitExtension;

/**
 * 
 * 
 */
public class TestFeaturePathMatching extends TestCase {

   /**
    * Test simple feature path matching. Test matching on a feature of the match
    * type
    * 
    * @throws Exception
    */
   public void testSimpleFeaturePathMatching() throws Exception {

      // create annotation tester with the regex annotator specifier
      AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("featurePathMatching/RegExAnnotFeaturePath.xml"));
      CAS cas = annotTester.performTest("My SimpleRegex String", "en");

      // define result interested in
      String[] tofs = { "org.apache.uima.TestAnnot",
            "org.apache.uima.TestAnnot:testFeature",
            "org.apache.uima.TestAnnot1",
            "org.apache.uima.TestAnnot1:testFeature" };

      // compare results
      File outputFile = new File(JUnitExtension.getFile("featurePathMatching"),
            "featurePathMatching_testouput.txt");
      AnnotatorTester.checkResult(cas, tofs, JUnitExtension
            .getFile("featurePathMatching/featurePathMatchingRef.txt"),
            outputFile);
   }
   /**
    * Test advanced feature path matching. Test matching on a feature path of
    * the match type
    * 
    * @throws Exception
    */
   public void testAdvancedFeaturePathMatching() throws Exception {

      // create annotation tester with the regex annotator specifier
      AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("featurePathMatching/RegExAnnotFeaturePath.xml"));
      CAS cas = annotTester.performTest("My AdvancedRegex String", "en");

      // define result interested in
      String[] tofs = { "org.apache.uima.TestAnnot",
            "org.apache.uima.TestAnnot:testFeature",
            "org.apache.uima.TestAnnot1",
            "org.apache.uima.TestAnnot1:testFeature",
            "org.apache.uima.TestAnnot1:refFeature",
            "org.apache.uima.TestAnnot2",
            "org.apache.uima.TestAnnot2:testFeature"};

      // compare results
      File outputFile = new File(JUnitExtension.getFile("featurePathMatching"),
            "advancedFeaturePathMatching_testouput.txt");
      AnnotatorTester.checkResult(cas, tofs, JUnitExtension
            .getFile("featurePathMatching/advancedFeaturePathMatchingRef.txt"),
            outputFile);
   }

   /**
    * Test invalid feature path matching. Test a feature path that does not exist.
    * 
    * @throws Exception
    */
   public void testInvalidFeaturePath() throws Exception {

      // create annotation tester with the regex annotator specifier
      AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("featurePathMatching/RegExAnnotInvalidFeaturePath.xml"));
      
      boolean foundMessage = false;
      try {
         annotTester.performTest("Dummy text", "en");
      }catch(RegexAnnotatorProcessException ex) {
         String message = ex.getCause().getMessage();
         if(message.indexOf("testFeature/invalid/path") > -1){
            foundMessage = true;
         }
      }
      Assert.assertTrue(foundMessage);
   }

   /**
    * Test unsupported feature path matching. Test an unsupported feature path
    * 
    * @throws Exception
    */
   public void testInvalidFeaturePath2() throws Exception {

      // create annotation tester with the regex annotator specifier
      AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("featurePathMatching/RegExAnnotInvalidFeaturePath2.xml"));

      boolean foundMessage = false;
      try {
         annotTester.performTest("Dummy text", "en");
      }catch(RegexAnnotatorProcessException ex) {
         String message = ex.getMessage();
         if(message.indexOf("arrayFeature") > -1){
            foundMessage = true;
         }
      }
      Assert.assertTrue(foundMessage);
   }

}
