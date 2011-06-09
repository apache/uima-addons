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
package org.apache.uima.alchemy.digester.domain;

public class Concept {
  private String text;
  private String relevance;
  private String website;
  private String geo;
  private String dbpedia;
  private String yago;
  private String opencyc;
  private String freebase;
  private String ciaFactbook;
  private String census;
  private String geonames;
  private String crunchbase;
  private String semanticCrunchbase;

  public String getMusicBrainz() {
    return musicBrainz;
  }

  public void setMusicBrainz(String musicBrainz) {
    this.musicBrainz = musicBrainz;
  }

  private String musicBrainz;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getRelevance() {
    return relevance;
  }

  public void setRelevance(String relevance) {
    this.relevance = relevance;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public String getGeo() {
    return geo;
  }

  public void setGeo(String geo) {
    this.geo = geo;
  }

  public String getDbpedia() {
    return dbpedia;
  }

  public void setDbpedia(String dbpedia) {
    this.dbpedia = dbpedia;
  }

  public String getYago() {
    return yago;
  }

  public void setYago(String yago) {
    this.yago = yago;
  }

  public String getOpencyc() {
    return opencyc;
  }

  public void setOpencyc(String opencyc) {
    this.opencyc = opencyc;
  }

  public String getFreebase() {
    return freebase;
  }

  public void setFreebase(String freebase) {
    this.freebase = freebase;
  }

  public String getCiaFactbook() {
    return ciaFactbook;
  }

  public void setCiaFactbook(String ciaFactbook) {
    this.ciaFactbook = ciaFactbook;
  }

  public String getCensus() {
    return census;
  }

  public void setCensus(String census) {
    this.census = census;
  }

  public String getGeonames() {
    return geonames;
  }

  public void setGeonames(String geonames) {
    this.geonames = geonames;
  }

  public String getCrunchbase() {
    return crunchbase;
  }

  public void setCrunchbase(String crunchbase) {
    this.crunchbase = crunchbase;
  }

  public String getSemanticCrunchbase() {
    return semanticCrunchbase;
  }

  public void setSemanticCrunchbase(String semanticCrunchbase) {
    this.semanticCrunchbase = semanticCrunchbase;
  }
}
