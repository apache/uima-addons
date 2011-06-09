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

/* First created by JCasGen Sat Sep 04 10:54:35 CEST 2010 */
package org.apache.uima.alchemy.ts.entity;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

/** 
 * Updated by JCasGen Fri Mar 11 17:40:07 CET 2011
 * @generated */
public class BaseEntity_Type extends TOP_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (BaseEntity_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = BaseEntity_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new BaseEntity(addr, BaseEntity_Type.this);
  			   BaseEntity_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new BaseEntity(addr, BaseEntity_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = BaseEntity.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.alchemy.ts.entity.BaseEntity");
 
  /** @generated */
  final Feature casFeat_text;
  /** @generated */
  final int     casFeatCode_text;
  /** @generated */ 
  public String getText(int addr) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_text);
  }
  /** @generated */    
  public void setText(int addr, String v) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_text, v);}
    
  
 
  /** @generated */
  final Feature casFeat_count;
  /** @generated */
  final int     casFeatCode_count;
  /** @generated */ 
  public String getCount(int addr) {
        if (featOkTst && casFeat_count == null)
      jcas.throwFeatMissing("count", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_count);
  }
  /** @generated */    
  public void setCount(int addr, String v) {
        if (featOkTst && casFeat_count == null)
      jcas.throwFeatMissing("count", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_count, v);}
    
  
 
  /** @generated */
  final Feature casFeat_relevance;
  /** @generated */
  final int     casFeatCode_relevance;
  /** @generated */ 
  public String getRelevance(int addr) {
        if (featOkTst && casFeat_relevance == null)
      jcas.throwFeatMissing("relevance", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_relevance);
  }
  /** @generated */    
  public void setRelevance(int addr, String v) {
        if (featOkTst && casFeat_relevance == null)
      jcas.throwFeatMissing("relevance", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_relevance, v);}
    
  
 
  /** @generated */
  final Feature casFeat_disambiguation;
  /** @generated */
  final int     casFeatCode_disambiguation;
  /** @generated */ 
  public String getDisambiguation(int addr) {
        if (featOkTst && casFeat_disambiguation == null)
      jcas.throwFeatMissing("disambiguation", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_disambiguation);
  }
  /** @generated */    
  public void setDisambiguation(int addr, String v) {
        if (featOkTst && casFeat_disambiguation == null)
      jcas.throwFeatMissing("disambiguation", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_disambiguation, v);}
    
  
 
  /** @generated */
  final Feature casFeat_subType;
  /** @generated */
  final int     casFeatCode_subType;
  /** @generated */ 
  public String getSubType(int addr) {
        if (featOkTst && casFeat_subType == null)
      jcas.throwFeatMissing("subType", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_subType);
  }
  /** @generated */    
  public void setSubType(int addr, String v) {
        if (featOkTst && casFeat_subType == null)
      jcas.throwFeatMissing("subType", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_subType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_website;
  /** @generated */
  final int     casFeatCode_website;
  /** @generated */ 
  public String getWebsite(int addr) {
        if (featOkTst && casFeat_website == null)
      jcas.throwFeatMissing("website", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_website);
  }
  /** @generated */    
  public void setWebsite(int addr, String v) {
        if (featOkTst && casFeat_website == null)
      jcas.throwFeatMissing("website", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_website, v);}
    
  
 
  /** @generated */
  final Feature casFeat_geo;
  /** @generated */
  final int     casFeatCode_geo;
  /** @generated */ 
  public String getGeo(int addr) {
        if (featOkTst && casFeat_geo == null)
      jcas.throwFeatMissing("geo", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_geo);
  }
  /** @generated */    
  public void setGeo(int addr, String v) {
        if (featOkTst && casFeat_geo == null)
      jcas.throwFeatMissing("geo", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_geo, v);}
    
  
 
  /** @generated */
  final Feature casFeat_dbpedia;
  /** @generated */
  final int     casFeatCode_dbpedia;
  /** @generated */ 
  public String getDbpedia(int addr) {
        if (featOkTst && casFeat_dbpedia == null)
      jcas.throwFeatMissing("dbpedia", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_dbpedia);
  }
  /** @generated */    
  public void setDbpedia(int addr, String v) {
        if (featOkTst && casFeat_dbpedia == null)
      jcas.throwFeatMissing("dbpedia", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_dbpedia, v);}
    
  
 
  /** @generated */
  final Feature casFeat_yago;
  /** @generated */
  final int     casFeatCode_yago;
  /** @generated */ 
  public String getYago(int addr) {
        if (featOkTst && casFeat_yago == null)
      jcas.throwFeatMissing("yago", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_yago);
  }
  /** @generated */    
  public void setYago(int addr, String v) {
        if (featOkTst && casFeat_yago == null)
      jcas.throwFeatMissing("yago", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_yago, v);}
    
  
 
  /** @generated */
  final Feature casFeat_opencyc;
  /** @generated */
  final int     casFeatCode_opencyc;
  /** @generated */ 
  public String getOpencyc(int addr) {
        if (featOkTst && casFeat_opencyc == null)
      jcas.throwFeatMissing("opencyc", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_opencyc);
  }
  /** @generated */    
  public void setOpencyc(int addr, String v) {
        if (featOkTst && casFeat_opencyc == null)
      jcas.throwFeatMissing("opencyc", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_opencyc, v);}
    
  
 
  /** @generated */
  final Feature casFeat_umbel;
  /** @generated */
  final int     casFeatCode_umbel;
  /** @generated */ 
  public String getUmbel(int addr) {
        if (featOkTst && casFeat_umbel == null)
      jcas.throwFeatMissing("umbel", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_umbel);
  }
  /** @generated */    
  public void setUmbel(int addr, String v) {
        if (featOkTst && casFeat_umbel == null)
      jcas.throwFeatMissing("umbel", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_umbel, v);}
    
  
 
  /** @generated */
  final Feature casFeat_freebase;
  /** @generated */
  final int     casFeatCode_freebase;
  /** @generated */ 
  public String getFreebase(int addr) {
        if (featOkTst && casFeat_freebase == null)
      jcas.throwFeatMissing("freebase", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_freebase);
  }
  /** @generated */    
  public void setFreebase(int addr, String v) {
        if (featOkTst && casFeat_freebase == null)
      jcas.throwFeatMissing("freebase", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_freebase, v);}
    
  
 
  /** @generated */
  final Feature casFeat_ciaFactbook;
  /** @generated */
  final int     casFeatCode_ciaFactbook;
  /** @generated */ 
  public String getCiaFactbook(int addr) {
        if (featOkTst && casFeat_ciaFactbook == null)
      jcas.throwFeatMissing("ciaFactbook", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_ciaFactbook);
  }
  /** @generated */    
  public void setCiaFactbook(int addr, String v) {
        if (featOkTst && casFeat_ciaFactbook == null)
      jcas.throwFeatMissing("ciaFactbook", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_ciaFactbook, v);}
    
  
 
  /** @generated */
  final Feature casFeat_census;
  /** @generated */
  final int     casFeatCode_census;
  /** @generated */ 
  public String getCensus(int addr) {
        if (featOkTst && casFeat_census == null)
      jcas.throwFeatMissing("census", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_census);
  }
  /** @generated */    
  public void setCensus(int addr, String v) {
        if (featOkTst && casFeat_census == null)
      jcas.throwFeatMissing("census", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_census, v);}
    
  
 
  /** @generated */
  final Feature casFeat_geonames;
  /** @generated */
  final int     casFeatCode_geonames;
  /** @generated */ 
  public String getGeonames(int addr) {
        if (featOkTst && casFeat_geonames == null)
      jcas.throwFeatMissing("geonames", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_geonames);
  }
  /** @generated */    
  public void setGeonames(int addr, String v) {
        if (featOkTst && casFeat_geonames == null)
      jcas.throwFeatMissing("geonames", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_geonames, v);}
    
  
 
  /** @generated */
  final Feature casFeat_musicBrainz;
  /** @generated */
  final int     casFeatCode_musicBrainz;
  /** @generated */ 
  public String getMusicBrainz(int addr) {
        if (featOkTst && casFeat_musicBrainz == null)
      jcas.throwFeatMissing("musicBrainz", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_musicBrainz);
  }
  /** @generated */    
  public void setMusicBrainz(int addr, String v) {
        if (featOkTst && casFeat_musicBrainz == null)
      jcas.throwFeatMissing("musicBrainz", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_musicBrainz, v);}
    
  
 
  /** @generated */
  final Feature casFeat_quotations;
  /** @generated */
  final int     casFeatCode_quotations;
  /** @generated */ 
  public int getQuotations(int addr) {
        if (featOkTst && casFeat_quotations == null)
      jcas.throwFeatMissing("quotations", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    return ll_cas.ll_getRefValue(addr, casFeatCode_quotations);
  }
  /** @generated */    
  public void setQuotations(int addr, int v) {
        if (featOkTst && casFeat_quotations == null)
      jcas.throwFeatMissing("quotations", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    ll_cas.ll_setRefValue(addr, casFeatCode_quotations, v);}
    
   /** @generated */
  public String getQuotations(int addr, int i) {
        if (featOkTst && casFeat_quotations == null)
      jcas.throwFeatMissing("quotations", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_quotations), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_quotations), i);
  return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_quotations), i);
  }
   
  /** @generated */ 
  public void setQuotations(int addr, int i, String v) {
        if (featOkTst && casFeat_quotations == null)
      jcas.throwFeatMissing("quotations", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_quotations), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_quotations), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_quotations), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_occurrences;
  /** @generated */
  final int     casFeatCode_occurrences;
  /** @generated */ 
  public int getOccurrences(int addr) {
        if (featOkTst && casFeat_occurrences == null)
      jcas.throwFeatMissing("occurrences", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    return ll_cas.ll_getRefValue(addr, casFeatCode_occurrences);
  }
  /** @generated */    
  public void setOccurrences(int addr, int v) {
        if (featOkTst && casFeat_occurrences == null)
      jcas.throwFeatMissing("occurrences", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    ll_cas.ll_setRefValue(addr, casFeatCode_occurrences, v);}
    
  
 
  /** @generated */
  final Feature casFeat_sentiment;
  /** @generated */
  final int     casFeatCode_sentiment;
  /** @generated */ 
  public int getSentiment(int addr) {
        if (featOkTst && casFeat_sentiment == null)
      jcas.throwFeatMissing("sentiment", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    return ll_cas.ll_getRefValue(addr, casFeatCode_sentiment);
  }
  /** @generated */    
  public void setSentiment(int addr, int v) {
        if (featOkTst && casFeat_sentiment == null)
      jcas.throwFeatMissing("sentiment", "org.apache.uima.alchemy.ts.entity.BaseEntity");
    ll_cas.ll_setRefValue(addr, casFeatCode_sentiment, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public BaseEntity_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_text = jcas.getRequiredFeatureDE(casType, "text", "uima.cas.String", featOkTst);
    casFeatCode_text  = (null == casFeat_text) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_text).getCode();

 
    casFeat_count = jcas.getRequiredFeatureDE(casType, "count", "uima.cas.String", featOkTst);
    casFeatCode_count  = (null == casFeat_count) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_count).getCode();

 
    casFeat_relevance = jcas.getRequiredFeatureDE(casType, "relevance", "uima.cas.String", featOkTst);
    casFeatCode_relevance  = (null == casFeat_relevance) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_relevance).getCode();

 
    casFeat_disambiguation = jcas.getRequiredFeatureDE(casType, "disambiguation", "uima.cas.String", featOkTst);
    casFeatCode_disambiguation  = (null == casFeat_disambiguation) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_disambiguation).getCode();

 
    casFeat_subType = jcas.getRequiredFeatureDE(casType, "subType", "uima.cas.String", featOkTst);
    casFeatCode_subType  = (null == casFeat_subType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_subType).getCode();

 
    casFeat_website = jcas.getRequiredFeatureDE(casType, "website", "uima.cas.String", featOkTst);
    casFeatCode_website  = (null == casFeat_website) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_website).getCode();

 
    casFeat_geo = jcas.getRequiredFeatureDE(casType, "geo", "uima.cas.String", featOkTst);
    casFeatCode_geo  = (null == casFeat_geo) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_geo).getCode();

 
    casFeat_dbpedia = jcas.getRequiredFeatureDE(casType, "dbpedia", "uima.cas.String", featOkTst);
    casFeatCode_dbpedia  = (null == casFeat_dbpedia) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_dbpedia).getCode();

 
    casFeat_yago = jcas.getRequiredFeatureDE(casType, "yago", "uima.cas.String", featOkTst);
    casFeatCode_yago  = (null == casFeat_yago) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_yago).getCode();

 
    casFeat_opencyc = jcas.getRequiredFeatureDE(casType, "opencyc", "uima.cas.String", featOkTst);
    casFeatCode_opencyc  = (null == casFeat_opencyc) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_opencyc).getCode();

 
    casFeat_umbel = jcas.getRequiredFeatureDE(casType, "umbel", "uima.cas.String", featOkTst);
    casFeatCode_umbel  = (null == casFeat_umbel) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_umbel).getCode();

 
    casFeat_freebase = jcas.getRequiredFeatureDE(casType, "freebase", "uima.cas.String", featOkTst);
    casFeatCode_freebase  = (null == casFeat_freebase) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_freebase).getCode();

 
    casFeat_ciaFactbook = jcas.getRequiredFeatureDE(casType, "ciaFactbook", "uima.cas.String", featOkTst);
    casFeatCode_ciaFactbook  = (null == casFeat_ciaFactbook) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ciaFactbook).getCode();

 
    casFeat_census = jcas.getRequiredFeatureDE(casType, "census", "uima.cas.String", featOkTst);
    casFeatCode_census  = (null == casFeat_census) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_census).getCode();

 
    casFeat_geonames = jcas.getRequiredFeatureDE(casType, "geonames", "uima.cas.String", featOkTst);
    casFeatCode_geonames  = (null == casFeat_geonames) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_geonames).getCode();

 
    casFeat_musicBrainz = jcas.getRequiredFeatureDE(casType, "musicBrainz", "uima.cas.String", featOkTst);
    casFeatCode_musicBrainz  = (null == casFeat_musicBrainz) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_musicBrainz).getCode();

 
    casFeat_quotations = jcas.getRequiredFeatureDE(casType, "quotations", "uima.cas.StringArray", featOkTst);
    casFeatCode_quotations  = (null == casFeat_quotations) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_quotations).getCode();

 
    casFeat_occurrences = jcas.getRequiredFeatureDE(casType, "occurrences", "uima.cas.FSList", featOkTst);
    casFeatCode_occurrences  = (null == casFeat_occurrences) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_occurrences).getCode();

 
    casFeat_sentiment = jcas.getRequiredFeatureDE(casType, "sentiment", "org.apache.uima.alchemy.ts.sentiment.SentimentFS", featOkTst);
    casFeatCode_sentiment  = (null == casFeat_sentiment) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentiment).getCode();

  }
}



    