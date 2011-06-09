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

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.test.junit_extension.AnnotatorTester;
import org.apache.uima.test.junit_extension.JUnitExtension;
import org.apache.uima.util.XMLInputSource;
import org.apache.uima.util.XMLParser;


/**
 * 
 * 
 */
public class TestWildcardFileLoading extends TestCase {

  /**
   * Test loading rule files with a wildcard file name.
   * 
   * @throws Exception
   */
  public void testEmptyRegex() throws Exception {

    // create annotation tester with the regex annotator specifier
    File descFile = JUnitExtension.getFile("wildcardFilenameSyntax/loadFilesWithWildcard.xml");
    File dpDir = descFile.getParentFile().getParentFile();
    String datapath = dpDir.getAbsolutePath();
    XMLParser parser = UIMAFramework.getXMLParser();
    AnalysisEngineDescription desc = parser.parseAnalysisEngineDescription(new XMLInputSource(
        descFile));
    ResourceManager rm = UIMAFramework.newDefaultResourceManager();
    rm.setDataPath(datapath);
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(desc, rm, null);
    CAS cas = ae.newCAS();
    cas.setDocumentLanguage("en");
    cas.setDocumentText("This is a test.");
    ae.process(cas);
  }

}
