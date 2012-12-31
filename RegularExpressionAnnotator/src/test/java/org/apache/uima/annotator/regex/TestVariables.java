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

import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.test.junit_extension.AnnotatorTester;
import org.apache.uima.test.junit_extension.JUnitExtension;

/**
 * 
 * 
 */
public class TestVariables extends TestCase {

   /**
    * Test regex variable replacement for variable concept 1
    * 
    * @throws Exception
    */
   public void testVariablesConcept1() throws Exception {

      // create annotation tester with the regex annotator specifier
      AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("variables/RegExAnnotVariables.xml"));

      CAS cas = annotTester.performTest(
            "Monday simple regex with variables Tuesday", "en");

      // define result interested in
      String[] tofs = { "org.apache.uima.TestAnnot" };

      // compare results
      File outputFile = new File(JUnitExtension.getFile("variables"),
            "variablesConcept1_testoutput.txt");
      AnnotatorTester.checkResult(cas, tofs, JUnitExtension
            .getFile("variables/variablesConcept1Ref.txt"), outputFile);
   }

   /**
    * Test regex variable replacement for variable concept 2
    * 
    * @throws Exception
    */
   public void testVariablesConcept2() throws Exception {

      // create annotation tester with the regex annotator specifier
      AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("variables/RegExAnnotVariables.xml"));

      CAS cas = annotTester.performTest("Tuesday January Sunday test {days}",
            "en");

      // define result interested in
      String[] tofs = { "org.apache.uima.TestAnnot" };

      // compare results
      File outputFile = new File(JUnitExtension.getFile("variables"),
            "variablesConcept2_testoutput.txt");
      AnnotatorTester.checkResult(cas, tofs, JUnitExtension
            .getFile("variables/variablesConcept2Ref.txt"), outputFile);
   }

   /**
    * Test regex variable replacement for variable concept 3. Tests escaping
    * 
    * @throws Exception
    */
   public void testVariablesConcept3() throws Exception {

      // create annotation tester with the regex annotator specifier
      AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("variables/RegExAnnotVariables.xml"));

      CAS cas = annotTester.performTest("Match: Jan. Don't macht Jana", "en");

      // define result interested in
      String[] tofs = { "org.apache.uima.TestAnnot" };

      // compare results
      File outputFile = new File(JUnitExtension.getFile("variables"),
            "variablesConcept3_testoutput.txt");
      AnnotatorTester.checkResult(cas, tofs, JUnitExtension
            .getFile("variables/variablesConcept3Ref.txt"), outputFile);
   }

   /**
    * Test regex variable replacement for variable concept 3. Tests dollar signs
    * 
    * @throws Exception
    */
   public void testVariablesConcept4() throws Exception {
      
      // create annotation tester with the regex annotator specifier
      AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("variables/RegExAnnotVariables.xml"));
      
      CAS cas = annotTester.performTest("this is the end. my only friend, the end", "en");
      
      // define result interested in
      String[] tofs = { "org.apache.uima.TestAnnot" };
      
      // compare results
      File outputFile = new File(JUnitExtension.getFile("variables"),
            "variablesConcept4_testoutput.txt");
      AnnotatorTester.checkResult(cas, tofs, JUnitExtension
            .getFile("variables/variablesConcept4Ref.txt"), outputFile);
   }

   /**
    * Test regex variable in variable replacement
    * 
    * @throws Exception
    */
   public void testVariablesInVariables() throws Exception {

      // create annotation tester with the regex annotator specifier
      AnnotatorTester annotTester = new AnnotatorTester(
            JUnitExtension.getFile("variables/RegExAnnotVariablesInVariables.xml"));

      CAS cas = annotTester.performTest("Monday regex with variables in variables Thursday",
            "en");

      // define result interested in
      String[] tofs = { "org.apache.uima.TestAnnot" };

      // compare results
      File outputFile = new File(JUnitExtension.getFile("variables"),
            "variablesInVariables_testoutput.txt");
      AnnotatorTester.checkResult(cas, tofs,
            JUnitExtension.getFile("variables/variablesInVariablesRef.txt"), outputFile);
   }  
   
   /**
    * Test regex variable replacement for variable concept 2
    * 
    * @throws Exception
    */
   public void testVariablesNotAvailable() throws Exception {

      boolean foundMessage = false;
      try {
         // create annotation tester with the regex annotator specifier
         new AnnotatorTester(JUnitExtension
               .getFile("variables/RegExAnnotVariablesError.xml"));
      } catch (ResourceInitializationException ex) {
         String message = ex.getCause().getMessage();
         if ((message.indexOf("days") > -1)
               && (message.indexOf("VariableError") > -1)) {
            foundMessage = true;
         }
      }
      Assert.assertTrue(foundMessage);
   }

}
