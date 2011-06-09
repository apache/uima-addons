/**
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.apache.uima.alchemy.annotator;

import org.apache.uima.alchemy.utils.TestUtils;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test case for simple actions
 */
public class SimpleTest {

  /**
   * test reconfiguration of an existing AE
   */
  @Test
  public void reconfigureTest() {
    try {
      AnalysisEngine ae = TestUtils.getAE("desc/TextCategorizationAEDescriptor.xml");
      ae.reconfigure();
    } catch (Exception e) {
      e.printStackTrace();
      fail(e.getLocalizedMessage());
    }
  }

  @Test
  public void testAnalysisEngineError() {
    try {
      Map<String, Object> parameterSettings = new HashMap<String, Object>();
      parameterSettings.put("apikey", "asdasdas12131");
      JCas resultingCAS = TestUtils.executeAE(TestUtils.getAE("desc/TextCategorizationAEDescriptor.xml", parameterSettings), "the big brown fox jumped on the table");
      fail("it should've failed with AnalysisEngineProcessException but it worked flawlessly");
    } catch (ResourceInitializationException e) {
      fail("it should've failed with AnalysisEngineProcessException - "+e.getLocalizedMessage());
    } catch (AnalysisEngineProcessException e) {
      assertEquals("org.apache.uima.alchemy.annotator.exception.AlchemyCallFailedException: ERROR - invalid-api-key",
              e.getCause().getLocalizedMessage());
    }
  }

}
