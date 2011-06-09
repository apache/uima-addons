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
public class TestAnnotationCreation extends TestCase {

  /**
   * test the annotation creation with different begin and end positions and with different match
   * groups
   * 
   * @throws Exception
   */
  public void testAnnotationCreation() throws Exception {

    // create annotation tester with the regex annotator specifier
    AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
            .getFile("annotationCreation/RegExAnnotCreateAnnot.xml"));
    CAS cas = annotTester
            .performTest("AnnotationCreationTestThatTestsTheVariousMatchGroupsNumbers)", "en");

    // define result interested in
    String[] tofs = { "org.apache.uima.TestAnnot", "org.apache.uima.TestAnnot:testFeature",
        "org.apache.uima.TestAnnot:testFeature1", "org.apache.uima.TestAnnot1",
        "org.apache.uima.TestAnnot1:testFeature", "org.apache.uima.TestAnnot1:testFeature1",
        "org.apache.uima.TestAnnot2", "org.apache.uima.TestAnnot2:testFeature",
        "org.apache.uima.TestAnnot2:testFeature1", "org.apache.uima.TestAnnot3",
        "org.apache.uima.TestAnnot3:testFeature", "org.apache.uima.TestAnnot3:testFeature1",
        "org.apache.uima.TestAnnot4"};

    // compare results
    File outputFile = new File(JUnitExtension.getFile("annotationCreation"),
            "createAnnot_testoutput.txt");
    AnnotatorTester.checkResult(cas, tofs, JUnitExtension
            .getFile("annotationCreation/createAnnotRef.txt"), outputFile);
  }

}
