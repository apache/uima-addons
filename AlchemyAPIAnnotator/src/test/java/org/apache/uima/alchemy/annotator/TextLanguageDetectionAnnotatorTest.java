/**
 * 	Licensed to the Apache Software Foundation (ASF) under one
 * 	or more contributor license agreements.  See the NOTICE file
 * 	distributed with this work for additional information
 * 	regarding copyright ownership.  The ASF licenses this file
 * 	to you under the Apache License, Version 2.0 (the
 * 	"License"); you may not use this file except in compliance
 * 	with the License.  You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * 	Unless required by applicable law or agreed to in writing,
 * 	software distributed under the License is distributed on an
 * 	"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * 	KIND, either express or implied.  See the License for the
 * 	specific language governing permissions and limitations
 * 	under the License.
 */
package org.apache.uima.alchemy.annotator;

import org.apache.uima.alchemy.annotator.mocked.MockedTextLanguageDetectionAnnotator;
import org.apache.uima.alchemy.ts.language.LanguageFS;
import org.apache.uima.alchemy.utils.TestUtils;
import org.apache.uima.jcas.JCas;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TextLanguageDetectionAnnotatorTest {
  private static final String DOC = "Eight US soldiers die in attacks in south Afghanistan, making October the deadliest month for the US in the war there";
  private static final String XML_PATH = "desc/TextLanguageDetectionAEDescriptor.xml";
  private static final String YOUR_KEY_HERE = "api-key";

  @SuppressWarnings("unchecked")
  @Ignore
  public void annotatorIntegrationTest() throws Exception {
    Map<String,Object> parameterSettings = new HashMap<String, Object>();
    parameterSettings.put("apikey", YOUR_KEY_HERE);
    JCas resultingCAS = TestUtils.executeAE(TestUtils.getAE(XML_PATH,parameterSettings), DOC);
    List<LanguageFS> language = (List<LanguageFS>) TestUtils.getAllFSofType(LanguageFS.type, resultingCAS);
    assertTrue(language != null);
    assertTrue(language.size() == 1);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void mockedAnnotatorTest() throws Exception {
    String mockedAnnotatorName = MockedTextLanguageDetectionAnnotator.class.getName();
    JCas resultingCAS = TestUtils.executeAE(TestUtils.getAEWithMockedImplementation(XML_PATH,mockedAnnotatorName), DOC);
    List<LanguageFS> language = (List<LanguageFS>) TestUtils.getAllFSofType(LanguageFS.type, resultingCAS);
    assertTrue(language != null);
    assertTrue(language.size() == 1);
  }

}
