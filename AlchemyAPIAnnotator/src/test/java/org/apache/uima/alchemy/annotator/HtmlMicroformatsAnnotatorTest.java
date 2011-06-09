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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class HtmlMicroformatsAnnotatorTest {
  private static final String DOC = "<html xmlns=\"http://www.w3.org/1999/xhtml\"><head profile=\"http://www.w3.org/2006/03/hcard\"><body>"
          + "<div class=\"vcard\"><a class=\" fn\">Dan McCreary</a>    <!-- fn is a full name --> <div class=\"org\">Syntactica</div>"
          + "<div class=\"title\">    Semantic Solutions Architect  <div>  <div class=\"adr\"> <div class=\"street-address\">"
          + "7400 Metro Boulevard, Suite 350 </div> <span class=\"locality\"> Minneapolis </span>, <span class=\"region\">MN</span>"
          + "<span class=\"postal-code\">55439</span> </div> <div class=\"tel\">(952) 921-9368</div>/div> </body></html>";
  private static final String XML_PATH = "desc/HtmlMicroformatsAEDescriptor.xml";

  @SuppressWarnings("unchecked")
  @Test
  public void annotatorIntegrationTest() {
    try {
      Map<String,Object> parameterSettings = new HashMap<String, Object>();
      parameterSettings.put("apikey","04490000a72fe7ec5cb3497f14e77f338c86f2fe");
      JCas resultingCAS = TestUtils.executeAE(TestUtils.getAE(XML_PATH,parameterSettings), DOC);
      List<MicroformatFS> microformatsList = (List<MicroformatFS>) TestUtils.getAllFSofType(MicroformatFS.type, resultingCAS);
      assertTrue(microformatsList.size()>0);
    } catch (Exception e) {
      e.printStackTrace();
      fail(e.toString());
    }
  }

  @SuppressWarnings("unchecked")
  @Test
  public void mockedAnnotatorTest() {
    try {
      String mockedAnnotatorName = MockedMicroformatsAnnotator.class.getName();
      JCas resultingCAS = TestUtils.executeAE(TestUtils.getAEWithMockedImplementation(XML_PATH,mockedAnnotatorName), DOC);
      List<MicroformatFS> microformatsList = (List<MicroformatFS>) TestUtils.getAllFSofType(MicroformatFS.type, resultingCAS);
      assertTrue(microformatsList.size()>0);
    }
    catch (Exception e) {
      e.printStackTrace();
      fail(e.toString());
    }
  }

}
