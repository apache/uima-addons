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

package org.apache.uima.tika;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.test.junit_extension.AnnotatorTester;
import org.apache.uima.util.XMLInputSource;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * TestCase for {@link MarkupAnnotator}
 */
public class MarkupAnnotatorTest {
  private String markupAnnotatorDescPath = "src/test/resources/AggregateTestAE.xml";
  private final String originalView = "_InitialView";

  @Test
  public void integrationTest() {
    try {
      CAS cas = prepareCAS();
      AnnotatorTester annotatorTester = new AnnotatorTester(markupAnnotatorDescPath);
      CAS newCas = annotatorTester.performTest(cas);
      for (Annotation annotation : newCas.getCurrentView().getJCas().getAnnotationIndex(
              org.apache.uima.tika.MarkupAnnotation.type)) {
        Type type = annotation.getType();
        assertTrue(annotation.getType().getName() != null);
        assertTrue(annotation.getBegin() >= 0);
        assertTrue(annotation.getEnd() > 0);
        assertTrue(annotation.getCoveredText() != null && !"".equals(annotation.getCoveredText()));
        assertTrue(annotation.getFeatureValueAsString(type.getFeatureByBaseName("name")) != null);
        assertTrue(newCas.getJCas().getDocumentLanguage()!=null);
      }
    } catch (Exception e) {
      e.printStackTrace();
      fail(e.getLocalizedMessage());
    }
  }

  private CAS prepareCAS() {
    CAS cas = null;
    try {
      // get resource specifier from XML file
      XMLInputSource in = new XMLInputSource(markupAnnotatorDescPath);
      ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);

      // create analysis engine with resource manager
      AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(specifier);

      // create new cas
      cas = ae.newCAS();
//      cas.createView(originalView);
    }
    catch (Exception e) {
      // do nothing
    }
    return cas;

  }
}
