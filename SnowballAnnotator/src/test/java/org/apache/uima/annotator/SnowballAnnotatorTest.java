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

import java.io.File;

import junit.framework.TestCase;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.test.junit_extension.AnnotatorTester;
import org.apache.uima.test.junit_extension.JUnitExtension;
import org.apache.uima.util.XMLInputSource;

/**
 * Testclass for the SnowballAnnotator.
 */
public class SnowballAnnotatorTest extends TestCase {
  private AnnotatorTester annotTester;

  /**
   * @see junit.framework.TestCase#setUp()
   */
  protected void setUp() throws Exception {
    this.annotTester = new AnnotatorTester(JUnitExtension.getFile("SnowballAnnotator.xml"));
  }

  /*
   * (non-Javadoc)
   * 
   * @see junit.framework.TestCase#tearDown()
   */
  protected void tearDown() throws Exception {
    super.tearDown();
    this.annotTester = null;
  }

  public void testAnnotatorEnglish() throws Exception {
    // get cas from xcas file
    CAS cas = AnnotatorTester.getCASfromXCAS(JUnitExtension.getFile("typesystem.xml"),
            JUnitExtension.getFile("englishXCAS.xml"));

    // execute sample text
    this.annotTester.performTest(cas);

    // define result interested in
    String[] tofs = { "org.apache.uima.TokenAnnotation", "org.apache.uima.TokenAnnotation:stem" };

    // compare results
    File outputFile = new File(JUnitExtension.getFile("englishRef.txt").getParent(),
            "englishRef_testoutput.txt");
    AnnotatorTester.checkResult(cas, tofs, JUnitExtension.getFile("englishRef.txt"), outputFile);
  }

  public void testReconfigure() {
    try {
      XMLInputSource in = new XMLInputSource("desc/SnowballAnnotator.xml");
      ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);

      AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);

      CAS cas = AnnotatorTester.getCASfromXCAS(JUnitExtension.getFile("typesystem.xml"),
              JUnitExtension.getFile("englishXCAS.xml"));

      ae.process(cas);

      ae.reconfigure();
    } catch (Exception e) {
      fail();
    }
  }
}
