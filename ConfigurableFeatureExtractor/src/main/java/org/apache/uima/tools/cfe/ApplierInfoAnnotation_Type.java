/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


/* First created by JCasGen Tue Mar 18 14:49:11 EDT 2008 */
package org.apache.uima.tools.cfe;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Tue Mar 18 14:49:11 EDT 2008
 * @generated */
public class ApplierInfoAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (ApplierInfoAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = ApplierInfoAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new ApplierInfoAnnotation(addr, ApplierInfoAnnotation_Type.this);
  			   ApplierInfoAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new ApplierInfoAnnotation(addr, ApplierInfoAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = ApplierInfoAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.cfe.ApplierInfoAnnotation");
 
  /** @generated */
  final Feature casFeat_Features;
  /** @generated */
  final int     casFeatCode_Features;
  /** @generated */ 
  public int getFeatures(int addr) {
        if (featOkTst && casFeat_Features == null)
      jcas.throwFeatMissing("Features", "org.apache.uima.cfe.ApplierInfoAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Features);
  }
  /** @generated */    
  public void setFeatures(int addr, int v) {
        if (featOkTst && casFeat_Features == null)
      jcas.throwFeatMissing("Features", "org.apache.uima.cfe.ApplierInfoAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_Features, v);}
    
   /** @generated */
  public String getFeatures(int addr, int i) {
        if (featOkTst && casFeat_Features == null)
      jcas.throwFeatMissing("Features", "org.apache.uima.cfe.ApplierInfoAnnotation");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Features), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Features), i);
	return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Features), i);
  }
   
  /** @generated */ 
  public void setFeatures(int addr, int i, String v) {
        if (featOkTst && casFeat_Features == null)
      jcas.throwFeatMissing("Features", "org.apache.uima.cfe.ApplierInfoAnnotation");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Features), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Features), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Features), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_AppliedAnnotation;
  /** @generated */
  final int     casFeatCode_AppliedAnnotation;
  /** @generated */ 
  public int getAppliedAnnotation(int addr) {
        if (featOkTst && casFeat_AppliedAnnotation == null)
      jcas.throwFeatMissing("AppliedAnnotation", "org.apache.uima.cfe.ApplierInfoAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_AppliedAnnotation);
  }
  /** @generated */    
  public void setAppliedAnnotation(int addr, int v) {
        if (featOkTst && casFeat_AppliedAnnotation == null)
      jcas.throwFeatMissing("AppliedAnnotation", "org.apache.uima.cfe.ApplierInfoAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_AppliedAnnotation, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Context;
  /** @generated */
  final int     casFeatCode_Context;
  /** @generated */ 
  public int getContext(int addr) {
        if (featOkTst && casFeat_Context == null)
      jcas.throwFeatMissing("Context", "org.apache.uima.cfe.ApplierInfoAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Context);
  }
  /** @generated */    
  public void setContext(int addr, int v) {
        if (featOkTst && casFeat_Context == null)
      jcas.throwFeatMissing("Context", "org.apache.uima.cfe.ApplierInfoAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_Context, v);}
    
   /** @generated */
  public String getContext(int addr, int i) {
        if (featOkTst && casFeat_Context == null)
      jcas.throwFeatMissing("Context", "org.apache.uima.cfe.ApplierInfoAnnotation");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Context), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Context), i);
	return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Context), i);
  }
   
  /** @generated */ 
  public void setContext(int addr, int i, String v) {
        if (featOkTst && casFeat_Context == null)
      jcas.throwFeatMissing("Context", "org.apache.uima.cfe.ApplierInfoAnnotation");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Context), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Context), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Context), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_DocumentId;
  /** @generated */
  final int     casFeatCode_DocumentId;
  /** @generated */ 
  public String getDocumentId(int addr) {
        if (featOkTst && casFeat_DocumentId == null)
      jcas.throwFeatMissing("DocumentId", "org.apache.uima.cfe.ApplierInfoAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_DocumentId);
  }
  /** @generated */    
  public void setDocumentId(int addr, String v) {
        if (featOkTst && casFeat_DocumentId == null)
      jcas.throwFeatMissing("DocumentId", "org.apache.uima.cfe.ApplierInfoAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_DocumentId, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public ApplierInfoAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Features = jcas.getRequiredFeatureDE(casType, "Features", "uima.cas.StringArray", featOkTst);
    casFeatCode_Features  = (null == casFeat_Features) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Features).getCode();

 
    casFeat_AppliedAnnotation = jcas.getRequiredFeatureDE(casType, "AppliedAnnotation", "org.apache.uima.cfe.AppliedAnnotation", featOkTst);
    casFeatCode_AppliedAnnotation  = (null == casFeat_AppliedAnnotation) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_AppliedAnnotation).getCode();

 
    casFeat_Context = jcas.getRequiredFeatureDE(casType, "Context", "uima.cas.StringArray", featOkTst);
    casFeatCode_Context  = (null == casFeat_Context) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Context).getCode();

 
    casFeat_DocumentId = jcas.getRequiredFeatureDE(casType, "DocumentId", "uima.cas.String", featOkTst);
    casFeatCode_DocumentId  = (null == casFeat_DocumentId) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_DocumentId).getCode();

  }
}



    