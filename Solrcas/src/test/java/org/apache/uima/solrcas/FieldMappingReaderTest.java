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

package org.apache.uima.solrcas;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * TestCase for FieldMappingReader object
 */
public class FieldMappingReaderTest {

  @Test
  public void testConfReader() {
    try {
      FieldMappingReader fieldMappingReader = new FieldMappingReader();
      SolrMappingConfiguration mapping = fieldMappingReader.getConf(this.getClass().
              getResourceAsStream("/solrmapping.xml"));
      assertNotNull(mapping);
      
      Map<String, Map<String, String>> featureStructuresMapping = mapping.getFeatureStructuresMapping();
      assertTrue(featureStructuresMapping != null);
      assertTrue(featureStructuresMapping.size()>0);
      for (String type : featureStructuresMapping.keySet()) {
        assertTrue(featureStructuresMapping.get(type)!=null && featureStructuresMapping.get(type).size()>0);
      }
      
      assertEquals("language", mapping.getDocumentLanguageMapping());
      assertEquals("text", mapping.getDocumentTextMapping());
    } catch (Exception e) {
      fail(e.getLocalizedMessage());
    }
  }
}
