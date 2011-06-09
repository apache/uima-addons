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

package org.apache.uima.tools.cfe.test;

import java.io.File;

import junit.framework.TestCase;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.examples.SourceDocumentInformation;
import org.apache.uima.pear.util.FileUtil;
import org.apache.uima.test.junit_extension.FileCompare;
import org.apache.uima.test.junit_extension.JUnitExtension;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.XMLInputSource;

/**
 * The CFE tests installs a pear file
 */

public class CFETest extends TestCase {

  // Temporary working directory, used to install the pear package
  private File tempInstallDir = null;

  /**
   * @see junit.framework.TestCase#setUp()
   */
  protected void setUp() throws Exception {

    // create temporary working directory
    File tempFile = File.createTempFile("pear_cfe_test_", "tmp");
    if (tempFile.delete()) {
      File tempDir = tempFile;
      if (tempDir.mkdirs())
        this.tempInstallDir = tempDir;
    }
  }

  /**
   * @see junit.framework.TestCase#tearDown()
   */
  protected void tearDown() throws Exception {
    if (this.tempInstallDir != null) {
      FileUtil.deleteDirectory(this.tempInstallDir);
    }
  }

  public void testCFE() throws Exception {
    
    // Run an aggregate with a person-title annotator and 
    // a CFE Cas Consumer
    
    XMLInputSource in = new XMLInputSource(JUnitExtension.getFile("PersonTitlePlusFeatureExtraction.xml"));
    AnalysisEngineDescription specifier = (AnalysisEngineDescription) UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    // CFE currently requires that the config file be specified as an absolute path.
    File configFile = JUnitExtension.getFile("CFEConfig.xml");
    specifier.getAnalysisEngineMetaData().getConfigurationParameterSettings().setParameterValue("ConfigurationFile", configFile.getAbsolutePath());
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);
    ae.setConfigParameterValue("ConfigurationFile", configFile.getAbsolutePath());
    ae.reconfigure();
    CAS cas = ae.newCAS();
    File docFile = JUnitExtension.getFile("testData.txt");
    String document = FileUtils.file2String(docFile);
    cas.setDocumentText(document);
    cas.setDocumentLanguage("en");
    
    SourceDocumentInformation sdi_ann = new SourceDocumentInformation(cas.getJCas(), 0, document.length());
    sdi_ann.setUri(docFile.toURI().toString());
    sdi_ann.addToIndexes();
    ae.process(cas); 
    
    File outFile = new File("tempTestOut/testData.txt.fve");
    File outFileRef = JUnitExtension.getFile("testDataRef.txt.fve");
    
    assertTrue(FileCompare.compare(outFile, outFileRef));
    outFile.delete();
    new File("tempTestOut").delete();
  }
}
