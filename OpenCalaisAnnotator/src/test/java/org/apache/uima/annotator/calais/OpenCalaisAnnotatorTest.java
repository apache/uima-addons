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
package org.apache.uima.annotator.calais;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.test.junit_extension.AnnotatorTester;
import org.junit.Test;

/**
 * Test for {@link OpenCalaisAnnotator}
 */
public class OpenCalaisAnnotatorTest {
  private String integrationDescPath = "src/test/resources/IntegrationTestOpenCalaisAnnotator.xml";
  private String mockedDescPath = "src/test/resources/MockedTestOpenCalaisAnnotator.xml";

  private static final String text = "President Obama vows to \"make BP pay\" for the Gulf oil spill, and says the US must end its fossil fuel \"addiction\"";

  @Test
  public void integrationTest() {
    try {
      AnnotatorTester annotatorTester = new AnnotatorTester(integrationDescPath);
      testAnnotator(annotatorTester);
    } catch (Exception e) {
      e.printStackTrace();
      fail(e.getLocalizedMessage());
    }
  }
  
  @Test
  public void unitMockedTest() {
    try {
      AnnotatorTester annotatorTester = new AnnotatorTester(mockedDescPath);
      testAnnotator(annotatorTester);
    } catch (Exception e) {
      e.printStackTrace();
      fail(e.getLocalizedMessage());
    }
  }

  private void testAnnotator(AnnotatorTester annotatorTester) throws Exception, CASException {
    CAS cas = annotatorTester.performTest(text, "en");
    for (Annotation annotation : cas.getJCas().getAnnotationIndex(
            org.apache.uima.calais.BaseType.type)) {
      Type type = annotation.getType();
      assertTrue(annotation.getType().getName() != null);
      assertTrue(annotation.getBegin() >= 0);
      assertTrue(annotation.getEnd() > 0);
      assertTrue(annotation.getCoveredText() != null && !"".equals(annotation.getCoveredText()));
      assertTrue(annotation.getFeatureValueAsString(type.getFeatureByBaseName("calaisType")) != null);
    }
  }
}
