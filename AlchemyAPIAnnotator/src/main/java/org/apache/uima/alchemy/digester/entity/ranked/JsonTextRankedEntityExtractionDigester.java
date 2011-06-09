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
import org.apache.uima.alchemy.digester.json.JsonDigester;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class JsonTextRankedEntityExtractionDigester implements OutputDigester {

  public Results parseAlchemyXML(InputStream stream) throws IOException, SAXException,
          URISyntaxException {

    Digester digester = new JsonDigester();
    digester.setValidating(false);
    digester.addObjectCreate("$", EntitiesResults.class);
    digester.addBeanPropertySetter("$/status");
    digester.addBeanPropertySetter("$/statusInfo", "statusInfo");
    digester.addBeanPropertySetter("$/url");
    digester.addBeanPropertySetter("$/language");
    digester.addObjectCreate("$/entities", Entity.class);
    digester.addBeanPropertySetter("$/entities/type");
    digester.addBeanPropertySetter("$/entities/relevance");
    digester.addBeanPropertySetter("$/entities/count");
    digester.addBeanPropertySetter("$/entities/text");
    digester.addObjectCreate("$/entities/disambiguated", Disambiguated.class);
    digester.addBeanPropertySetter("$/entities/disambiguated/name", "name");
    digester.addBeanPropertySetter("$/entities/disambiguated/subType", "subType");
    digester.addBeanPropertySetter("$/entities/disambiguated/website", "website");
    digester.addBeanPropertySetter("$/entities/disambiguated/geo", "geo");
    digester.addBeanPropertySetter("$/entities/disambiguated/dbpedia", "dbpedia");
    digester.addBeanPropertySetter("$/entities/disambiguated/yago", "yago");
    digester.addBeanPropertySetter("$/entities/disambiguated/opencyc", "opencyc");
    digester.addBeanPropertySetter("$/entities/disambiguated/umbel", "umbel");
    digester.addBeanPropertySetter("$/entities/disambiguated/freebase", "freebase");
    digester.addBeanPropertySetter("$/entities/disambiguated/ciaFactbook", "ciaFactbook");
    digester.addBeanPropertySetter("$/entities/disambiguated/census", "census");
    digester.addBeanPropertySetter("$/entities/disambiguated/geonames", "geonames");
    digester.addBeanPropertySetter("$/entities/disambiguated/musicBrainz", "musicBrainz");
    digester.addSetNext("$/entities/disambiguated", "setDisambiguated");
    digester.addObjectCreate("$/entities/quotations", Quotations.class);
    digester.addBeanPropertySetter("$/entities/quotations/quotation", "quotation");
    digester.addSetNext("$/entities/quotations", "setQuotations");
    digester.addSetNext("$/entities", "addEntity");
    return (Results) digester.parse(stream);
  }

}
