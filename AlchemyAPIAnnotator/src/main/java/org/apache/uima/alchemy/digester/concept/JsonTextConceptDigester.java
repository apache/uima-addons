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
package org.apache.uima.alchemy.digester.concept;

import org.apache.commons.digester.Digester;
import org.apache.uima.alchemy.digester.OutputDigester;
import org.apache.uima.alchemy.digester.domain.Concept;
import org.apache.uima.alchemy.digester.domain.ConceptResults;
import org.apache.uima.alchemy.digester.domain.Results;
import org.apache.uima.alchemy.digester.json.JsonDigester;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class JsonTextConceptDigester implements OutputDigester {
  public Results parseAlchemyXML(InputStream xmlReader) throws IOException, SAXException,
          URISyntaxException {
    Digester digester = new JsonDigester();
    digester.setValidating(false);

    digester.addObjectCreate("$", ConceptResults.class);
    digester.addBeanPropertySetter("$/status", "status");
    digester.addBeanPropertySetter("$/statusInfo", "statusInfo");
    digester.addBeanPropertySetter("$/url", "url");
    digester.addBeanPropertySetter("$/language", "language");
    digester.addObjectCreate("$/concepts/concept", Concept.class);
    digester.addBeanPropertySetter("$/concepts/concept/text", "text");
    digester.addBeanPropertySetter("$/concepts/concept/relevance", "relevance");
    digester.addBeanPropertySetter("$/concepts/concept/website", "website");
    digester.addBeanPropertySetter("$/concepts/concept/geo", "geo");
    digester.addBeanPropertySetter("$/concepts/concept/dbpedia", "dbpedia");
    digester.addBeanPropertySetter("$/concepts/concept/yago", "yago");
    digester.addBeanPropertySetter("$/concepts/concept/musicBrainz", "musicBrainz");
    digester.addBeanPropertySetter("$/concepts/concept/opencyc", "opencyc");
    digester.addBeanPropertySetter("$/concepts/concept/freebase", "freebase");
    digester.addBeanPropertySetter("$/concepts/concept/ciaFactbook", "ciaFactbook");
    digester.addBeanPropertySetter("$/concepts/concept/census", "census");
    digester.addBeanPropertySetter("$/concepts/concept/geonames", "geonames");
    digester.addBeanPropertySetter("$/concepts/concept/crunchbase", "crunchbase");
    digester.addBeanPropertySetter("$/concepts/concept/semanticCrunchbase", "semanticCrunchbase");
    digester.addSetNext("$/concepts", "addConcept");

    return (Results) digester.parse(xmlReader);
  }
}
