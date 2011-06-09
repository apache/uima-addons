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

package org.apache.uima.simpleserver.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.simpleserver.ResultExtractor;
import org.apache.uima.simpleserver.SimpleServerException;
import org.apache.uima.simpleserver.config.ServerSpec;
import org.apache.uima.simpleserver.config.TypeMap;
import org.apache.uima.simpleserver.config.impl.XmlConfigReader;
import org.apache.uima.simpleserver.output.Result;
import org.apache.uima.simpleserver.output.ResultConverter;
import org.apache.uima.test.junit_extension.FileCompare;
import org.apache.uima.test.junit_extension.JUnitExtension;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.apache.uima.util.XMLParser;
import org.apache.xmlbeans.XmlException;
import org.junit.Test;

/**
 * TODO: Create type commment.
 */
public class ConfigTest {

  public static final String CONFIG_TEST_FILE = "stuff.xml";

  private static final String encoding = "UTF-8";

  @Test
  public void readSampleConfig() {
    try {
      InputStream is = this.getClass().getClassLoader().getResourceAsStream(CONFIG_TEST_FILE);
      ServerSpec spec = XmlConfigReader.readServerSpec(is);
      List<TypeMap> types = spec.getTypeSpecs();
      for (int i = 0; i < types.size(); i++) {
        assertNotNull(types.get(i));
      }
    } catch (XmlException e) {
      e.printStackTrace();
      assertTrue(false);
    } catch (IOException e) {
      e.printStackTrace();
      assertTrue(false);
    } catch (SimpleServerException e) {
      e.printStackTrace();
      assertTrue(false);
    }
  }

  @Test
  public void testResultGeneration1() {
    test("01", 290);
  }

  @Test
  public void testResultGeneration2() {
    test("02", 3);
  }

  @Test
  public void testResultGeneration3() {
    test("03", 1);
  }

  @Test
  public void testResultGeneration4() {
    test("04", 0);
  }

  @Test
  public void testResultGeneration5() {
    test("05", 10);
  }

  @Test
  public void testResultGeneration6() {
    test("06", 10);
  }

  @Test
  public void testResultGeneration7() {
    test("07", 73);
  }

  @Test
  public void testResultGeneration8() {
    test("08", 73);
  }

  @Test
  public void testResultGeneration9() {
    test("09", 72);
  }

  @Test
  public void testResultGeneration10() {
    test("10", 290);
  }

  private static final String getConfigFileName(final String number) {
    return "serverspec/spec" + number + ".xml";
  }

  private static final String getInlineXmlFileName(final String number) {
    return "src/test/resources/expected/inline" + number + ".xml";
  }

  private static final String getStandoffXmlFileName(final String number) {
    return "src/test/resources/expected/standoff" + number + ".xml";
  }

  private static final void test(String testNumber, int expectedResultNumber) {
    final String configFile = getConfigFileName(testNumber);
    JCas cas = createTestCas();
    ServerSpec serverSpec = null;
    try {
      serverSpec = XmlConfigReader.readServerSpec(JUnitExtension.getFile(configFile));
    } catch (SimpleServerException e) {
      e.printStackTrace();
      assertTrue(false);
    } catch (IOException e) {
      e.printStackTrace();
      assertTrue(false);
    } catch (XmlException e) {
      e.printStackTrace();
      assertTrue(false);
    }
    List<SimpleServerException> exc = serverSpec.validate(cas.getTypeSystem());
    if (exc.size() > 0) {
      exc.get(0).printStackTrace();
      assertTrue(false);
    }
    ResultExtractor ex = new ResultExtractor();
    Result result = ex.getResult(cas.getCas(), serverSpec);
    final int resultSize = result.getResultEntries().size();
    assertTrue("Expected number of results was " + expectedResultNumber + ", actual number is "
        + resultSize, (resultSize == expectedResultNumber));
    String inlineExpected = null;
    String standoffExpected = null;
    final String inlineActual = ResultConverter.getInlineXML(result);
    final String standoffActual = ResultConverter.getXMLString(result);
    try {
      inlineExpected = FileUtils.file2String(new File(getInlineXmlFileName(testNumber)), encoding);
      standoffExpected = FileUtils.file2String(new File(getStandoffXmlFileName(testNumber)),
          encoding);
    } catch (IOException e) {
      e.printStackTrace();
    }
    assertTrue("Expected inline output:\n" + inlineExpected + "\nbut found:\n" + inlineActual,
        FileCompare.compareStrings(inlineActual, inlineExpected));
    assertTrue(
        "Expected standoff output:\n" + standoffExpected + "\nbut found:\n" + standoffActual,
        FileCompare.compareStrings(standoffActual, standoffExpected));
    // final int len = configFile.length();
    // final String suffix = configFile.substring(len - 6, len);
    // final String outInlineFileName = "out/inline" + suffix;
    // final String inlineXmlContent = ResultConverter.getInlineXML(result);
    // final String outStandoffFileName = "out/standoff" + suffix;
    // final String standoffXmlContent = ResultConverter.getXMLString(result);
    // System.out.println((new File(outInlineFileName)).getAbsolutePath());
    // try {
    // FileUtils.saveString2File(inlineXmlContent, new File(outInlineFileName), encoding);
    // FileUtils.saveString2File(standoffXmlContent, new File(outStandoffFileName), encoding);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }

  }

  private static final JCas createTestCas() {
    XMLParser parser = UIMAFramework.getXMLParser();
    File descriptorFile = JUnitExtension.getFile("desc/simpleServerTestDescriptor.xml");
    File textFile = JUnitExtension.getFile("test.txt");
    String text = null;
    try {
      text = FileUtils.file2String(textFile, "utf-8");
    } catch (IOException e1) {
      e1.printStackTrace();
      assertTrue(false);
    }
    AnalysisEngineDescription aeDesc = null;
    try {
      aeDesc = (AnalysisEngineDescription) parser.parse(new XMLInputSource(descriptorFile));
    } catch (InvalidXMLException e) {
      e.printStackTrace();
      assertTrue(false);
    } catch (IOException e) {
      e.printStackTrace();
      assertTrue(false);
    }
    AnalysisEngine ae = null;
    try {
      ae = UIMAFramework.produceAnalysisEngine(aeDesc);
    } catch (ResourceInitializationException e) {
      e.printStackTrace();
      assertTrue(false);
    }
    JCas cas = null;
    try {
      cas = ae.newJCas();
    } catch (ResourceInitializationException e) {
      e.printStackTrace();
      assertTrue(false);
    }
    cas.setDocumentText(text);
    try {
      ae.process(cas);
    } catch (AnalysisEngineProcessException e) {
      e.printStackTrace();
      assertTrue(false);
    }
    return cas;
  }

}
