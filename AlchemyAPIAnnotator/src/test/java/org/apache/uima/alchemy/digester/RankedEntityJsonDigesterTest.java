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

import junit.framework.TestCase;

import org.apache.uima.alchemy.digester.domain.EntitiesResults;
import org.apache.uima.alchemy.digester.entity.ranked.JsonTextRankedEntityExtractionDigester;

public class RankedEntityJsonDigesterTest extends TestCase {

  public void testParseAlchemyJson() {
    try {
      JsonTextRankedEntityExtractionDigester digester = new JsonTextRankedEntityExtractionDigester();

      EntitiesResults results = (EntitiesResults) digester.parseAlchemyXML(this.getClass()
              .getResourceAsStream("/jsonTextRankedEntityResult.js"));
      assertTrue(results != null);
      assertTrue(results.getLanguage() != null);
      assertTrue(results.getStatus() != null);
      assertTrue(results.getUrl() != null);
      assertTrue(results.getEntities() != null);
      assertTrue(results.getEntities().getEntities() != null);
      assertTrue(results.getEntities().getEntities().size() > 0);
      assertTrue(results.getEntities().getEntities().size() == 2);
      assertTrue(results.getEntities().getEntities().get(1).getDisambiguated() != null);
      assertTrue(results.getEntities().getEntities().get(1).getDisambiguated().getDbpedia().equals(
              "http://dbpedia.org/resource/United_States"));
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

}
