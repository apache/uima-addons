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

/* First created by JCasGen Mon Feb 28 10:31:26 CET 2011 */
package org.apache.uima.alchemy.ts.sentiment;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Fri Mar 11 17:40:07 CET 2011
 * @generated */
public class SentimentFS_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SentimentFS_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SentimentFS_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SentimentFS(addr, SentimentFS_Type.this);
  			   SentimentFS_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SentimentFS(addr, SentimentFS_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = SentimentFS.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.alchemy.ts.sentiment.SentimentFS");
 
  /** @generated */
  final Feature casFeat_sentimentType;
  /** @generated */
  final int     casFeatCode_sentimentType;
  /** @generated */ 
  public String getSentimentType(int addr) {
        if (featOkTst && casFeat_sentimentType == null)
      jcas.throwFeatMissing("sentimentType", "org.apache.uima.alchemy.ts.sentiment.SentimentFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_sentimentType);
  }
  /** @generated */    
  public void setSentimentType(int addr, String v) {
        if (featOkTst && casFeat_sentimentType == null)
      jcas.throwFeatMissing("sentimentType", "org.apache.uima.alchemy.ts.sentiment.SentimentFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_sentimentType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_score;
  /** @generated */
  final int     casFeatCode_score;
  /** @generated */ 
  public String getScore(int addr) {
        if (featOkTst && casFeat_score == null)
      jcas.throwFeatMissing("score", "org.apache.uima.alchemy.ts.sentiment.SentimentFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_score);
  }
  /** @generated */    
  public void setScore(int addr, String v) {
        if (featOkTst && casFeat_score == null)
      jcas.throwFeatMissing("score", "org.apache.uima.alchemy.ts.sentiment.SentimentFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_score, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public SentimentFS_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_sentimentType = jcas.getRequiredFeatureDE(casType, "sentimentType", "uima.cas.String", featOkTst);
    casFeatCode_sentimentType  = (null == casFeat_sentimentType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentimentType).getCode();

 
    casFeat_score = jcas.getRequiredFeatureDE(casType, "score", "uima.cas.String", featOkTst);
    casFeatCode_score  = (null == casFeat_score) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_score).getCode();

  }
}



    