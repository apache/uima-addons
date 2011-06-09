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

import java.util.Map;

/**
 * A mapping configuration to stream a CAS documentText, documentLanguage and FeatureStructures to Solr fields
 */
public class SolrMappingConfiguration {

  private Map<String, Map<String, String>> featureStructuresMapping;

  private String documentTextMapping;

  private String documentLanguageMapping;


  public SolrMappingConfiguration(String documentTextMapping, String documentLanguageMapping, Map<String, Map<String, String>> featureStructureMapping) {
    this.documentLanguageMapping = documentLanguageMapping;
    this.documentTextMapping = documentTextMapping;
    this.featureStructuresMapping = featureStructureMapping;
  }

  public String getDocumentLanguageMapping() {
    return documentLanguageMapping;
  }

  public String getDocumentTextMapping() {
    return documentTextMapping;
  }

  public Map<String, Map<String, String>> getFeatureStructuresMapping() {
    return featureStructuresMapping;
  }

}
