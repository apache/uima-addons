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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.alchemy.annotator.mocked.MockedTextConceptTaggingAnnotator;
import org.apache.uima.alchemy.ts.concept.ConceptFS;
import org.apache.uima.alchemy.utils.TestUtils;
import org.apache.uima.jcas.JCas;
import org.apache.uima.util.FileUtils;
import org.junit.Test;

public class TextConceptTaggingAnnotatorTest {
  private static final String DOC = "Eight US soldiers die in attacks in south Afghanistan, making October the deadliest month for the US in the war there";

  private static final String DOCPATH = "src/test/resources/rankedTestText.txt";

  private static final String XML_PATH = "desc/TextConceptTaggingAEDescriptor.xml";

  @SuppressWarnings("unchecked")
  @Test
  public void annotatorIntegrationTest() {
    try {
      Map<String, Object> parameterSettings = new HashMap<String, Object>();
      parameterSettings.put("apikey", "04490000a72fe7ec5cb3497f14e77f338c86f2fe");
      String documentText = FileUtils.file2String(new File(DOCPATH));
      JCas resultingCAS = TestUtils.executeAE(TestUtils.getAE(XML_PATH, parameterSettings),
              documentText);
      List<ConceptFS> concepts = (List<ConceptFS>) TestUtils.getAllFSofType(ConceptFS.type,
              resultingCAS);
      assertTrue(concepts != null);
      assertTrue(concepts.size() == 8);
    } catch (Exception e) {
      e.printStackTrace();
      fail(e.toString());
    }
  }

  @SuppressWarnings("unchecked")
  @Test
  public void mockedAnnotatorTest() {
    try {
      String mockedAnnotatorName = MockedTextConceptTaggingAnnotator.class.getName();
      JCas resultingCAS = TestUtils.executeAE(TestUtils.getAEWithMockedImplementation(XML_PATH,
              mockedAnnotatorName), DOC);
      List<ConceptFS> concepts = (List<ConceptFS>) TestUtils.getAllFSofType(ConceptFS.type,
              resultingCAS);
      assertTrue(concepts != null);
      assertTrue(concepts.size() == 1);
    } catch (Exception e) {
      e.printStackTrace();
      fail(e.toString());
    }
  }
}
