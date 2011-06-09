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
package org.apache.uima.alchemy.digester.domain;

public class EntitiesResults extends Results {

  private Entities entities = new Entities();

  public Entities getEntities() {
    return entities;
  }

  public void setEntities(Entities entities) {
    this.entities = entities;
  }

  public void addEntity(Entity entity) {
    this.getEntities().addEntity(entity);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(super.toString());
    if (entities != null && entities.getEntities() != null && !entities.getEntities().isEmpty()) {
      for (Entity entity : entities.getEntities()) {
        builder.append("count : ").append(entity.getCount()).append("\n");
        builder.append("relevance : ").append(entity.getRelevance()).append("\n");
        builder.append("text : ").append(entity.getText()).append("\n");
        builder.append("type : ").append(entity.getType()).append("\n");
        if (entity.getDisambiguated() != null) {
          builder.append("disambiguated - census : ").append(entity.getDisambiguated().getCensus())
                  .append("\n");
          builder.append("disambiguated - ciaFactbook : ").append(
                  entity.getDisambiguated().getCiaFactbook()).append("\n");
          builder.append("disambiguated - dbpedia : ").append(
                  entity.getDisambiguated().getDbpedia()).append("\n");
          builder.append("disambiguated - freebase : ").append(
                  entity.getDisambiguated().getFreebase()).append("\n");
          builder.append("disambiguated - geo : ").append(entity.getDisambiguated().getGeo())
                  .append("\n");
          builder.append("disambiguated - geonames : ").append(
                  entity.getDisambiguated().getGeonames()).append("\n");
          builder.append("disambiguated - musicbrainz : ").append(
                  entity.getDisambiguated().getMusicBrainz()).append("\n");
          builder.append("disambiguated - name : ").append(entity.getDisambiguated().getName())
                  .append("\n");
          builder.append("disambiguated - opencyc : ").append(
                  entity.getDisambiguated().getOpencyc()).append("\n");
          builder.append("disambiguated - subtype : ").append(
                  entity.getDisambiguated().getSubType()).append("\n");
          builder.append("disambiguated - umbel : ").append(entity.getDisambiguated().getUmbel())
                  .append("\n");
          builder.append("disambiguated - website : ").append(
                  entity.getDisambiguated().getWebsite()).append("\n");
          builder.append("disambiguated - yago : ").append(entity.getDisambiguated().getYago())
                  .append("\n");
        }
      }
    }
    return builder.toString();
  }

}
