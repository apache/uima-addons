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
package org.apache.uima.alchemy.digester;

import java.io.ByteArrayInputStream;

import junit.framework.TestCase;

import org.apache.uima.alchemy.digester.domain.EntitiesResults;
import org.apache.uima.alchemy.digester.entity.ranked.XMLRankedEntityExtractionDigester;

public class RankedEntityXMLDigesterTest extends TestCase {

  public void testParseAlchemyXML() {
    try {
      String xmlString = "<results><status>REQUEST_STATUS</status><language>DOCUMENT_LANGUAGE</language>"
              + "<url>REQUESTED_URL</url><text>DOCUMENT_TEXT</text><entities><entity><type>DETECTED_TYPE</type>"
              + "<relevance>DETECTED_RELEVANCE</relevance><count>DETECTED_COUNT</count><text>DETECTED_ENTITY</text>"
              + "<disambiguated><name>DISAMBIGUATED_ENTITY</name><subType>ENTITY_SUBTYPE</subType><website>WEBSITE</website>"
              + "<geo>LATITUDE LONGITUDE</geo><dbpedia>LINKED_DATA_DBPEDIA</dbpedia><yago>LINKED_DATA_YAGO</yago>"
              + "<opencyc>LINKED_DATA_OPENCYC</opencyc><umbel>LINKED_DATA_UMBEL</umbel><freebase>LINKED_DATA_FREEBASE</freebase>"
              + "<ciaFactbook>LINKED_DATA_FACTBOOK</ciaFactbook><census>LINKED_DATA_CENSUS</census><geonames>LINKED_DATA_GEONAMES</geonames>"
              + "<musicBrainz>LINKED_DATA_MUSICBRAINZ</musicBrainz></disambiguated><quotations><quotation>ENTITY_QUOTATION</quotation>"
              + "</quotations></entity></entities></results>";
      XMLRankedEntityExtractionDigester digester = new XMLRankedEntityExtractionDigester();
      EntitiesResults results = (EntitiesResults) digester
              .parseAlchemyXML(new ByteArrayInputStream(xmlString.getBytes()));
      assertTrue(results != null);
      assertTrue(results.getLanguage() != null);
      assertTrue(results.getStatus() != null);
      assertTrue(results.getUrl() != null);
      assertTrue(results.getEntities() != null);
      assertTrue(results.getEntities().getEntities() != null);
      assertTrue(results.getEntities().getEntities().size() > 0);
      assertTrue(results.getEntities().getEntities().size() == 1);
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

}
