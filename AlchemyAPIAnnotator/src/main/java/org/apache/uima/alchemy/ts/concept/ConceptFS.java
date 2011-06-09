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

/* First created by JCasGen Sat Jul 10 16:08:15 CEST 2010 */
package org.apache.uima.alchemy.ts.concept;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;


/** a concept tag
 * Updated by JCasGen Sat Jul 10 16:08:15 CEST 2010
 * @generated */
public class ConceptFS extends TOP {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(ConceptFS.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected ConceptFS() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public ConceptFS(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public ConceptFS(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: text

  /** getter for text - gets 
   * @generated */
  public String getText() {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_text);}
    
  /** setter for text - sets  
   * @generated */
  public void setText(String v) {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_text, v);}    
   
    
  //*--------------*
  //* Feature: relevance

  /** getter for relevance - gets 
   * @generated */
  public String getRelevance() {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_relevance == null)
      jcasType.jcas.throwFeatMissing("relevance", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_relevance);}
    
  /** setter for relevance - sets  
   * @generated */
  public void setRelevance(String v) {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_relevance == null)
      jcasType.jcas.throwFeatMissing("relevance", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_relevance, v);}    
   
    
  //*--------------*
  //* Feature: website

  /** getter for website - gets 
   * @generated */
  public String getWebsite() {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_website == null)
      jcasType.jcas.throwFeatMissing("website", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_website);}
    
  /** setter for website - sets  
   * @generated */
  public void setWebsite(String v) {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_website == null)
      jcasType.jcas.throwFeatMissing("website", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_website, v);}    
   
    
  //*--------------*
  //* Feature: geo

  /** getter for geo - gets 
   * @generated */
  public String getGeo() {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_geo == null)
      jcasType.jcas.throwFeatMissing("geo", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_geo);}
    
  /** setter for geo - sets  
   * @generated */
  public void setGeo(String v) {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_geo == null)
      jcasType.jcas.throwFeatMissing("geo", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_geo, v);}    
   
    
  //*--------------*
  //* Feature: dbpedia

  /** getter for dbpedia - gets 
   * @generated */
  public String getDbpedia() {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_dbpedia == null)
      jcasType.jcas.throwFeatMissing("dbpedia", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_dbpedia);}
    
  /** setter for dbpedia - sets  
   * @generated */
  public void setDbpedia(String v) {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_dbpedia == null)
      jcasType.jcas.throwFeatMissing("dbpedia", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_dbpedia, v);}    
   
    
  //*--------------*
  //* Feature: yago

  /** getter for yago - gets 
   * @generated */
  public String getYago() {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_yago == null)
      jcasType.jcas.throwFeatMissing("yago", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_yago);}
    
  /** setter for yago - sets  
   * @generated */
  public void setYago(String v) {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_yago == null)
      jcasType.jcas.throwFeatMissing("yago", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_yago, v);}    
   
    
  //*--------------*
  //* Feature: opencyc

  /** getter for opencyc - gets 
   * @generated */
  public String getOpencyc() {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_opencyc == null)
      jcasType.jcas.throwFeatMissing("opencyc", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_opencyc);}
    
  /** setter for opencyc - sets  
   * @generated */
  public void setOpencyc(String v) {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_opencyc == null)
      jcasType.jcas.throwFeatMissing("opencyc", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_opencyc, v);}    
   
    
  //*--------------*
  //* Feature: freebase

  /** getter for freebase - gets 
   * @generated */
  public String getFreebase() {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_freebase == null)
      jcasType.jcas.throwFeatMissing("freebase", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_freebase);}
    
  /** setter for freebase - sets  
   * @generated */
  public void setFreebase(String v) {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_freebase == null)
      jcasType.jcas.throwFeatMissing("freebase", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_freebase, v);}    
   
    
  //*--------------*
  //* Feature: ciaFactbook

  /** getter for ciaFactbook - gets 
   * @generated */
  public String getCiaFactbook() {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_ciaFactbook == null)
      jcasType.jcas.throwFeatMissing("ciaFactbook", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_ciaFactbook);}
    
  /** setter for ciaFactbook - sets  
   * @generated */
  public void setCiaFactbook(String v) {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_ciaFactbook == null)
      jcasType.jcas.throwFeatMissing("ciaFactbook", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_ciaFactbook, v);}    
   
    
  //*--------------*
  //* Feature: census

  /** getter for census - gets 
   * @generated */
  public String getCensus() {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_census == null)
      jcasType.jcas.throwFeatMissing("census", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_census);}
    
  /** setter for census - sets  
   * @generated */
  public void setCensus(String v) {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_census == null)
      jcasType.jcas.throwFeatMissing("census", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_census, v);}    
   
    
  //*--------------*
  //* Feature: geonames

  /** getter for geonames - gets 
   * @generated */
  public String getGeonames() {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_geonames == null)
      jcasType.jcas.throwFeatMissing("geonames", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_geonames);}
    
  /** setter for geonames - sets  
   * @generated */
  public void setGeonames(String v) {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_geonames == null)
      jcasType.jcas.throwFeatMissing("geonames", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_geonames, v);}    
   
    
  //*--------------*
  //* Feature: musicBrainz

  /** getter for musicBrainz - gets 
   * @generated */
  public String getMusicBrainz() {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_musicBrainz == null)
      jcasType.jcas.throwFeatMissing("musicBrainz", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_musicBrainz);}
    
  /** setter for musicBrainz - sets  
   * @generated */
  public void setMusicBrainz(String v) {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_musicBrainz == null)
      jcasType.jcas.throwFeatMissing("musicBrainz", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_musicBrainz, v);}    
   
    
  //*--------------*
  //* Feature: crunchbase

  /** getter for crunchbase - gets 
   * @generated */
  public String getCrunchbase() {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_crunchbase == null)
      jcasType.jcas.throwFeatMissing("crunchbase", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_crunchbase);}
    
  /** setter for crunchbase - sets  
   * @generated */
  public void setCrunchbase(String v) {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_crunchbase == null)
      jcasType.jcas.throwFeatMissing("crunchbase", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_crunchbase, v);}    
   
    
  //*--------------*
  //* Feature: semanticCrunchbase

  /** getter for semanticCrunchbase - gets 
   * @generated */
  public String getSemanticCrunchbase() {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_semanticCrunchbase == null)
      jcasType.jcas.throwFeatMissing("semanticCrunchbase", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_semanticCrunchbase);}
    
  /** setter for semanticCrunchbase - sets  
   * @generated */
  public void setSemanticCrunchbase(String v) {
    if (ConceptFS_Type.featOkTst && ((ConceptFS_Type)jcasType).casFeat_semanticCrunchbase == null)
      jcasType.jcas.throwFeatMissing("semanticCrunchbase", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConceptFS_Type)jcasType).casFeatCode_semanticCrunchbase, v);}    
  }

    