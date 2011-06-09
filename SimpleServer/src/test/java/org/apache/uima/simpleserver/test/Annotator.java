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

import org.apache.uima.analysis_engine.ResultSpecification;
import org.apache.uima.analysis_engine.annotator.JTextAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

/**
 * Test case annotator.
 */
public class Annotator extends JTextAnnotator_ImplBase {

  public Annotator() {
    super();
  }

  public void process(JCas jcas, ResultSpecification _rs) {
    final String text = jcas.getDocumentText();
    TypeWithListsAndArrays fs0 = new TypeWithListsAndArrays(jcas);
    fs0.setStringList("foo");
    TypeWithListsAndArrays fs1 = new TypeWithListsAndArrays(jcas);
    fs1.setStringList(null);
    for (int i = 0; i < text.length(); i++) {
      CharacterAnnotation ca = new CharacterAnnotation(jcas, i, i+1);
      if ((i % 2) == 0) {
        if ((i % 4) == 0) {
          ca.setFsFeature(fs0);
        } else {
          ca.setFsFeature(fs1);
        }
      }
      ca.setBooleanFeature((i % 10) == 0);
      ca.setByteFeature((byte) (i % Byte.MAX_VALUE));
      ca.setDoubleFeature(i);
      ca.setFloatFeature(i);
      ca.setIntegerFeature(i);
      ca.setLongFeature(i);
      ca.setShortFeature((short) (i % Short.MAX_VALUE));
      ca.setStringFeature(Integer.toString(i));
      ca.addToIndexes();
    }
  }

}
