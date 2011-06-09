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
package org.apache.uima.alchemy.digester.entity.ranked;

import org.apache.commons.digester.Digester;
import org.apache.uima.alchemy.digester.OutputDigester;
import org.apache.uima.alchemy.digester.domain.*;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class XMLRankedEntityExtractionDigester implements OutputDigester {

  public Results parseAlchemyXML(InputStream stream) throws IOException, SAXException,
          URISyntaxException {
    Digester digester = new Digester();
    digester.setValidating(false);

    digester.addObjectCreate("results", EntitiesResults.class);
    digester.addBeanPropertySetter("results/status", "status");
    digester.addBeanPropertySetter("results/statusInfo", "statusInfo");
    digester.addBeanPropertySetter("results/language", "language");
    digester.addBeanPropertySetter("results/url", "url");
    digester.addObjectCreate("results/entities", Entities.class);
    digester.addObjectCreate("results/entities/entity", Entity.class);
    digester.addBeanPropertySetter("results/entities/entity/type", "type");
    digester.addBeanPropertySetter("results/entities/entity/relevance", "relevance");
    digester.addBeanPropertySetter("results/entities/entity/count", "count");
    digester.addBeanPropertySetter("results/entities/entity/text", "text");
    digester.addObjectCreate("results/entities/entity/disambiguated", Disambiguated.class);
    digester.addBeanPropertySetter("results/entities/entity/disambiguated/name", "name");
    digester.addBeanPropertySetter("results/entities/entity/disambiguated/subType", "subType");
    digester.addBeanPropertySetter("results/entities/entity/disambiguated/website", "website");
    digester.addBeanPropertySetter("results/entities/entity/disambiguated/geo", "geo");
    digester.addBeanPropertySetter("results/entities/entity/disambiguated/dbpedia", "dbpedia");
    digester.addBeanPropertySetter("results/entities/entity/disambiguated/yago", "yago");
    digester.addBeanPropertySetter("results/entities/entity/disambiguated/opencyc", "opencyc");
    digester.addBeanPropertySetter("results/entities/entity/disambiguated/umbel", "umbel");
    digester.addBeanPropertySetter("results/entities/entity/disambiguated/freebase", "freebase");
    digester.addBeanPropertySetter("results/entities/entity/disambiguated/ciaFactbook",
            "ciaFactbook");
    digester.addBeanPropertySetter("results/entities/entity/disambiguated/census", "census");
    digester.addBeanPropertySetter("results/entities/entity/disambiguated/geonames", "geonames");
    digester.addBeanPropertySetter("results/entities/entity/disambiguated/musicBrainz",
            "musicBrainz");
    digester.addSetNext("results/entities/entity/disambiguated", "setDisambiguated");
    digester.addObjectCreate("results/entities/entity/quotations", Quotations.class);
    digester.addBeanPropertySetter("results/entities/entity/quotations/quotation", "quotation");
    digester.addSetNext("results/entities/entity/quotations", "setQuotations");
    digester.addObjectCreate("results/entities/entity/sentiment", DocumentSentiment.class);
    digester.addBeanPropertySetter("results/entities/entity/sentiment/type", "type");
    digester.addBeanPropertySetter("results/entities/entity/sentiment/score", "score");
    digester.addSetNext("results/entities/entity/sentiment", "setSentiment");
    digester.addSetNext("results/entities/entity", "addEntity");
    digester.addSetNext("results/entities", "setEntities");

    return (Results) digester.parse(stream);
  }

}
