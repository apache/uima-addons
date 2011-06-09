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
/* First created by JCasGen Sat Jul 10 16:08:16 CEST 2010 */
package org.apache.uima.alchemy.ts.concept;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.cas.TOP_Type;

/** a concept tag
 * Updated by JCasGen Sat Jul 10 16:08:16 CEST 2010
 * @generated */
public class ConceptFS_Type extends TOP_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (ConceptFS_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = ConceptFS_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new ConceptFS(addr, ConceptFS_Type.this);
  			   ConceptFS_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new ConceptFS(addr, ConceptFS_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = ConceptFS.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.alchemy.ts.concept.ConceptFS");
 
  /** @generated */
  final Feature casFeat_text;
  /** @generated */
  final int     casFeatCode_text;
  /** @generated */ 
  public String getText(int addr) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_text);
  }
  /** @generated */    
  public void setText(int addr, String v) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_text, v);}
    
  
 
  /** @generated */
  final Feature casFeat_relevance;
  /** @generated */
  final int     casFeatCode_relevance;
  /** @generated */ 
  public String getRelevance(int addr) {
        if (featOkTst && casFeat_relevance == null)
      jcas.throwFeatMissing("relevance", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_relevance);
  }
  /** @generated */    
  public void setRelevance(int addr, String v) {
        if (featOkTst && casFeat_relevance == null)
      jcas.throwFeatMissing("relevance", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_relevance, v);}
    
  
 
  /** @generated */
  final Feature casFeat_website;
  /** @generated */
  final int     casFeatCode_website;
  /** @generated */ 
  public String getWebsite(int addr) {
        if (featOkTst && casFeat_website == null)
      jcas.throwFeatMissing("website", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_website);
  }
  /** @generated */    
  public void setWebsite(int addr, String v) {
        if (featOkTst && casFeat_website == null)
      jcas.throwFeatMissing("website", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_website, v);}
    
  
 
  /** @generated */
  final Feature casFeat_geo;
  /** @generated */
  final int     casFeatCode_geo;
  /** @generated */ 
  public String getGeo(int addr) {
        if (featOkTst && casFeat_geo == null)
      jcas.throwFeatMissing("geo", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_geo);
  }
  /** @generated */    
  public void setGeo(int addr, String v) {
        if (featOkTst && casFeat_geo == null)
      jcas.throwFeatMissing("geo", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_geo, v);}
    
  
 
  /** @generated */
  final Feature casFeat_dbpedia;
  /** @generated */
  final int     casFeatCode_dbpedia;
  /** @generated */ 
  public String getDbpedia(int addr) {
        if (featOkTst && casFeat_dbpedia == null)
      jcas.throwFeatMissing("dbpedia", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_dbpedia);
  }
  /** @generated */    
  public void setDbpedia(int addr, String v) {
        if (featOkTst && casFeat_dbpedia == null)
      jcas.throwFeatMissing("dbpedia", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_dbpedia, v);}
    
  
 
  /** @generated */
  final Feature casFeat_yago;
  /** @generated */
  final int     casFeatCode_yago;
  /** @generated */ 
  public String getYago(int addr) {
        if (featOkTst && casFeat_yago == null)
      jcas.throwFeatMissing("yago", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_yago);
  }
  /** @generated */    
  public void setYago(int addr, String v) {
        if (featOkTst && casFeat_yago == null)
      jcas.throwFeatMissing("yago", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_yago, v);}
    
  
 
  /** @generated */
  final Feature casFeat_opencyc;
  /** @generated */
  final int     casFeatCode_opencyc;
  /** @generated */ 
  public String getOpencyc(int addr) {
        if (featOkTst && casFeat_opencyc == null)
      jcas.throwFeatMissing("opencyc", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_opencyc);
  }
  /** @generated */    
  public void setOpencyc(int addr, String v) {
        if (featOkTst && casFeat_opencyc == null)
      jcas.throwFeatMissing("opencyc", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_opencyc, v);}
    
  
 
  /** @generated */
  final Feature casFeat_freebase;
  /** @generated */
  final int     casFeatCode_freebase;
  /** @generated */ 
  public String getFreebase(int addr) {
        if (featOkTst && casFeat_freebase == null)
      jcas.throwFeatMissing("freebase", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_freebase);
  }
  /** @generated */    
  public void setFreebase(int addr, String v) {
        if (featOkTst && casFeat_freebase == null)
      jcas.throwFeatMissing("freebase", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_freebase, v);}
    
  
 
  /** @generated */
  final Feature casFeat_ciaFactbook;
  /** @generated */
  final int     casFeatCode_ciaFactbook;
  /** @generated */ 
  public String getCiaFactbook(int addr) {
        if (featOkTst && casFeat_ciaFactbook == null)
      jcas.throwFeatMissing("ciaFactbook", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_ciaFactbook);
  }
  /** @generated */    
  public void setCiaFactbook(int addr, String v) {
        if (featOkTst && casFeat_ciaFactbook == null)
      jcas.throwFeatMissing("ciaFactbook", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_ciaFactbook, v);}
    
  
 
  /** @generated */
  final Feature casFeat_census;
  /** @generated */
  final int     casFeatCode_census;
  /** @generated */ 
  public String getCensus(int addr) {
        if (featOkTst && casFeat_census == null)
      jcas.throwFeatMissing("census", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_census);
  }
  /** @generated */    
  public void setCensus(int addr, String v) {
        if (featOkTst && casFeat_census == null)
      jcas.throwFeatMissing("census", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_census, v);}
    
  
 
  /** @generated */
  final Feature casFeat_geonames;
  /** @generated */
  final int     casFeatCode_geonames;
  /** @generated */ 
  public String getGeonames(int addr) {
        if (featOkTst && casFeat_geonames == null)
      jcas.throwFeatMissing("geonames", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_geonames);
  }
  /** @generated */    
  public void setGeonames(int addr, String v) {
        if (featOkTst && casFeat_geonames == null)
      jcas.throwFeatMissing("geonames", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_geonames, v);}
    
  
 
  /** @generated */
  final Feature casFeat_musicBrainz;
  /** @generated */
  final int     casFeatCode_musicBrainz;
  /** @generated */ 
  public String getMusicBrainz(int addr) {
        if (featOkTst && casFeat_musicBrainz == null)
      jcas.throwFeatMissing("musicBrainz", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_musicBrainz);
  }
  /** @generated */    
  public void setMusicBrainz(int addr, String v) {
        if (featOkTst && casFeat_musicBrainz == null)
      jcas.throwFeatMissing("musicBrainz", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_musicBrainz, v);}
    
  
 
  /** @generated */
  final Feature casFeat_crunchbase;
  /** @generated */
  final int     casFeatCode_crunchbase;
  /** @generated */ 
  public String getCrunchbase(int addr) {
        if (featOkTst && casFeat_crunchbase == null)
      jcas.throwFeatMissing("crunchbase", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_crunchbase);
  }
  /** @generated */    
  public void setCrunchbase(int addr, String v) {
        if (featOkTst && casFeat_crunchbase == null)
      jcas.throwFeatMissing("crunchbase", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_crunchbase, v);}
    
  
 
  /** @generated */
  final Feature casFeat_semanticCrunchbase;
  /** @generated */
  final int     casFeatCode_semanticCrunchbase;
  /** @generated */ 
  public String getSemanticCrunchbase(int addr) {
        if (featOkTst && casFeat_semanticCrunchbase == null)
      jcas.throwFeatMissing("semanticCrunchbase", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_semanticCrunchbase);
  }
  /** @generated */    
  public void setSemanticCrunchbase(int addr, String v) {
        if (featOkTst && casFeat_semanticCrunchbase == null)
      jcas.throwFeatMissing("semanticCrunchbase", "org.apache.uima.alchemy.ts.concept.ConceptFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_semanticCrunchbase, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public ConceptFS_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_text = jcas.getRequiredFeatureDE(casType, "text", "uima.cas.String", featOkTst);
    casFeatCode_text  = (null == casFeat_text) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_text).getCode();

 
    casFeat_relevance = jcas.getRequiredFeatureDE(casType, "relevance", "uima.cas.String", featOkTst);
    casFeatCode_relevance  = (null == casFeat_relevance) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_relevance).getCode();

 
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

 
    casFeat_crunchbase = jcas.getRequiredFeatureDE(casType, "crunchbase", "uima.cas.String", featOkTst);
    casFeatCode_crunchbase  = (null == casFeat_crunchbase) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_crunchbase).getCode();

 
    casFeat_semanticCrunchbase = jcas.getRequiredFeatureDE(casType, "semanticCrunchbase", "uima.cas.String", featOkTst);
    casFeatCode_semanticCrunchbase  = (null == casFeat_semanticCrunchbase) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_semanticCrunchbase).getCode();

  }
}



    