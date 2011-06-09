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
public class TestRuleExceptions extends TestCase {

  /**
   * 
   * 
   * @throws Exception
   */
  public void testRuleExceptionDocAnnotNoMAtch() throws Exception {

    // create annotation tester with the regex annotator specifier
    AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("ruleExceptions/RegExAnnotExceptions.xml"));
    CAS cas = annotTester.performTest("this is my text with a simpleMatch and an exception 12-wbt-test", "en");

    // define result interested in
    String[] tofs = { "uima.tcas.DocumentAnnotation", "org.apache.uima.TestAnnot" };

    // compare results
    File outputFile = new File(JUnitExtension.getFile("ruleExceptions"),
            "docAnnotNoMatch_testoutput.txt");
    AnnotatorTester.checkResult(cas, tofs, JUnitExtension
            .getFile("ruleExceptions/docAnnotNoMatchRef.txt"), outputFile);
  }

  /**
   * 
   * 
   * @throws Exception
   */
  public void testRuleExceptionDocAnnotMatch() throws Exception {

    // create annotation tester with the regex annotator specifier
    AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("ruleExceptions/RegExAnnotExceptions.xml"));
    CAS cas = annotTester.performTest("this is my text with a simpleMatch and no exception", "en");

    // define result interested in
    String[] tofs = { "uima.tcas.DocumentAnnotation", "org.apache.uima.TestAnnot" };

    // compare results
    File outputFile = new File(JUnitExtension.getFile("ruleExceptions"),
            "docAnnotMatch_testoutput.txt");
    AnnotatorTester.checkResult(cas, tofs, JUnitExtension
            .getFile("ruleExceptions/docAnnotMatchRef.txt"), outputFile);
  }

  /**
   * 
   * 
   * @throws Exception
   */
  public void testRuleExceptionSentenceAnnotNoMatch() throws Exception {

    // create annotation tester with the regex annotator specifier
    AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("ruleExceptions/RegExAnnotExceptions.xml"));
    CAS cas = annotTester.performTest("My text starts here: car carbon test cat cart and ends here", "en");

    // define result interested in
    String[] tofs = { "uima.tcas.DocumentAnnotation", "org.apache.uima.TestAnnot", 
            "org.apache.uima.SentenceAnnot", "org.apache.uima.TokenAnnot"};

    // compare results
    File outputFile = new File(JUnitExtension.getFile("ruleExceptions"),
            "sentenceAnnotNoMatch_testoutput.txt");
    AnnotatorTester.checkResult(cas, tofs, JUnitExtension
            .getFile("ruleExceptions/sentenceAnnotNoMatchRef.txt"), outputFile);
  }

  /**
   * 
   * 
   * @throws Exception
   */
  public void testRuleExceptionSentenceAnnotMatch() throws Exception {

    // create annotation tester with the regex annotator specifier
    AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("ruleExceptions/RegExAnnotExceptions.xml"));
    CAS cas = annotTester.performTest("My text starts here: car carbon  cat cart and ends here", "en");

    // define result interested in
    String[] tofs = { "uima.tcas.DocumentAnnotation", "org.apache.uima.TestAnnot", 
            "org.apache.uima.SentenceAnnot", "org.apache.uima.TokenAnnot"};

    // compare results
    File outputFile = new File(JUnitExtension.getFile("ruleExceptions"),
            "sentenceAnnotMatch_testoutput.txt");
    AnnotatorTester.checkResult(cas, tofs, JUnitExtension
            .getFile("ruleExceptions/sentenceAnnotMatchRef.txt"), outputFile);
  }

}
