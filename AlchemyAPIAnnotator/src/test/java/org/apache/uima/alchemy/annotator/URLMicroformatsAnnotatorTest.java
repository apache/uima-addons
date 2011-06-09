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

import org.apache.uima.alchemy.annotator.mocked.MockedMicroformatsAnnotator;
import org.apache.uima.alchemy.ts.microformats.MicroformatFS;
import org.apache.uima.alchemy.utils.TestUtils;
import org.apache.uima.jcas.JCas;
import org.junit.Test;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class URLMicroformatsAnnotatorTest {
  private static final String URL = "http://microformats.org/wiki/hcard";
  private static final String XML_PATH = "desc/URLMicroformatsAEDescriptor.xml";

  @SuppressWarnings("unchecked")
  @Test
  public void annotatorIntegrationTest() {
    try {
      if (isURLreachable()) {
        Map<String,Object> parameterSettings = new HashMap<String, Object>();
        parameterSettings.put("apikey","04490000a72fe7ec5cb3497f14e77f338c86f2fe");
        JCas resultingCAS = TestUtils.executeAE(TestUtils.getAE(XML_PATH,parameterSettings), URL);
        List<MicroformatFS> microformats = (List<MicroformatFS>) TestUtils.getAllFSofType(MicroformatFS.type, resultingCAS);
        assertTrue(microformats!=null);
        assertTrue(microformats.size()==7);
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail(e.toString());
    }
  }

  private boolean isURLreachable() {
    try {
      return new URI(URL).toURL().getContent() != null;
    } catch (Exception e) {
      return false;
    }
  }

  @SuppressWarnings("unchecked")
  @Test
  public void mockedAnnotatorTest() {
    try {
      String mockedAnnotatorName = MockedMicroformatsAnnotator.class.getName();
      JCas resultingCAS = TestUtils.executeAE(TestUtils.getAEWithMockedImplementation(XML_PATH,mockedAnnotatorName), URL);
      List<MicroformatFS> microformats = (List<MicroformatFS>) TestUtils.getAllFSofType(MicroformatFS.type, resultingCAS);
      assertTrue(microformats!=null);
      assertTrue(microformats.size()==2);
    } catch (Exception e) {
      e.printStackTrace();
      fail(e.toString());
    }
  }

}
