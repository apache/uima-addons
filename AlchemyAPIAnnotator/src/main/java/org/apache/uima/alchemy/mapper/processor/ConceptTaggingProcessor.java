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
package org.apache.uima.alchemy.mapper.processor;

import java.util.List;

import org.apache.uima.alchemy.digester.domain.Concept;
import org.apache.uima.alchemy.digester.domain.ConceptResults;
import org.apache.uima.alchemy.digester.domain.Results;
import org.apache.uima.alchemy.ts.concept.ConceptFS;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;

public class ConceptTaggingProcessor implements AlchemyOutputProcessor {

  public void process(JCas cas, Results results) throws Exception {
    ConceptResults conceptResults = (ConceptResults) results;
    if (conceptResults.getConcepts() != null) {
      List<Concept> concepts = conceptResults.getConcepts().getConcepts();
      for (Concept concept : concepts) {
        ConceptFS conceptFS = new ConceptFS(cas);
        Type type = conceptFS.getType();
        conceptFS.setFeatureValueFromString(type.getFeatureByBaseName("text"), concept.getText());
        conceptFS.setFeatureValueFromString(type.getFeatureByBaseName("relevance"), concept
                .getRelevance());
        conceptFS.setFeatureValueFromString(type.getFeatureByBaseName("website"), concept
                .getWebsite());
        conceptFS.setFeatureValueFromString(type.getFeatureByBaseName("geo"), concept.getGeo());
        conceptFS.setFeatureValueFromString(type.getFeatureByBaseName("dbpedia"), concept
                .getDbpedia());
        conceptFS.setFeatureValueFromString(type.getFeatureByBaseName("yago"), concept.getYago());
        conceptFS.setFeatureValueFromString(type.getFeatureByBaseName("opencyc"), concept
                .getOpencyc());
        conceptFS.setFeatureValueFromString(type.getFeatureByBaseName("ciaFactbook"), concept
                .getCiaFactbook());
        conceptFS.setFeatureValueFromString(type.getFeatureByBaseName("geonames"), concept
                .getGeonames());
        conceptFS.setFeatureValueFromString(type.getFeatureByBaseName("crunchbase"), concept
                .getCrunchbase());
        conceptFS.setFeatureValueFromString(type.getFeatureByBaseName("semanticCrunchbase"),
                concept.getSemanticCrunchbase());
        conceptFS.setFeatureValueFromString(type.getFeatureByBaseName("musicBrainz"), concept
                .getMusicBrainz());
        conceptFS.addToIndexes();
      }
    }
  }

}
