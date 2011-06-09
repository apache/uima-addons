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
public class TestMatchStrategy extends TestCase {

  /**
   * test matchAll match strategy
   * 
   * @throws Exception
   */
  public void testMatchAll() throws Exception {

    // create annotation tester with the regex annotator specifier
    AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("matchStrategy/RegExAnnotMatchStrategy.xml"));
    CAS cas = annotTester.performTest("no mactch matchALL matchALL matchALL no match", "en");

    // define result interested in
    String[] tofs = { "org.apache.uima.TestAnnot" };

    // compare results
    File outputFile = new File(JUnitExtension.getFile("matchStrategy"),
            "matchAll_testoutput.txt");
    AnnotatorTester.checkResult(cas, tofs, JUnitExtension
            .getFile("matchStrategy/matchAllRef.txt"), outputFile);
  }
  
  /**
   * test matchFirst match strategy
   * 
   * @throws Exception
   */
  public void testMatchFirst() throws Exception {

    // create annotation tester with the regex annotator specifier
    AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("matchStrategy/RegExAnnotMatchStrategy.xml"));
    CAS cas = annotTester.performTest("no mactch matchFirst matchFirst no match", "en");

    // define result interested in
    String[] tofs = { "org.apache.uima.TestAnnot" };

    // compare results
    File outputFile = new File(JUnitExtension.getFile("matchStrategy"),
            "matchFirst_testoutput.txt");
    AnnotatorTester.checkResult(cas, tofs, JUnitExtension
            .getFile("matchStrategy/matchFirstRef.txt"), outputFile);
  }

  /**
   * test matchComplete match strategy - Test match
   * 
   * @throws Exception
   */
  public void testMatchCompleteOK() throws Exception {

    // create annotation tester with the regex annotator specifier
    AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("matchStrategy/RegExAnnotMatchStrategy.xml"));
    CAS cas = annotTester.performTest("matchComplete", "en");

    // define result interested in
    String[] tofs = { "org.apache.uima.TestAnnot" };

    // compare results
    File outputFile = new File(JUnitExtension.getFile("matchStrategy"),
            "matchCompleteOK_testoutput.txt");
    AnnotatorTester.checkResult(cas, tofs, JUnitExtension
            .getFile("matchStrategy/matchCompleteOKRef.txt"), outputFile);
  }
  
  /**
   * test matchComplete match strategy - no match
   * 
   * @throws Exception
   */
  public void testMatchCompleteNotOK() throws Exception {

    // create annotation tester with the regex annotator specifier
    AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("matchStrategy/RegExAnnotMatchStrategy.xml"));
    CAS cas = annotTester.performTest("matchComplete is not OK", "en");

    // define result interested in
    String[] tofs = { "org.apache.uima.TestAnnot", "uima.tcas.DocumentAnnotation" };

    // compare results
    File outputFile = new File(JUnitExtension.getFile("matchStrategy"),
            "matchCompleteNotOK_testoutput.txt");
    AnnotatorTester.checkResult(cas, tofs, JUnitExtension
            .getFile("matchStrategy/matchCompleteNotOKRef.txt"), outputFile);
  }
  

}
