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

/** Annotation that contains truth(if available) and assigned senses
 * Updated by JCasGen Tue Mar 18 14:49:11 EDT 2008
 * @generated */
public class AppliedAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (AppliedAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = AppliedAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new AppliedAnnotation(addr, AppliedAnnotation_Type.this);
  			   AppliedAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new AppliedAnnotation(addr, AppliedAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = AppliedAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.cfe.AppliedAnnotation");
 
  /** @generated */
  final Feature casFeat_Assigned;
  /** @generated */
  final int     casFeatCode_Assigned;
  /** @generated */ 
  public int getAssigned(int addr) {
        if (featOkTst && casFeat_Assigned == null)
      jcas.throwFeatMissing("Assigned", "org.apache.uima.cfe.AppliedAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Assigned);
  }
  /** @generated */    
  public void setAssigned(int addr, int v) {
        if (featOkTst && casFeat_Assigned == null)
      jcas.throwFeatMissing("Assigned", "org.apache.uima.cfe.AppliedAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_Assigned, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Truth;
  /** @generated */
  final int     casFeatCode_Truth;
  /** @generated */ 
  public int getTruth(int addr) {
        if (featOkTst && casFeat_Truth == null)
      jcas.throwFeatMissing("Truth", "org.apache.uima.cfe.AppliedAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Truth);
  }
  /** @generated */    
  public void setTruth(int addr, int v) {
        if (featOkTst && casFeat_Truth == null)
      jcas.throwFeatMissing("Truth", "org.apache.uima.cfe.AppliedAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_Truth, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public AppliedAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Assigned = jcas.getRequiredFeatureDE(casType, "Assigned", "org.apache.uima.cfe.SenseAnnotation", featOkTst);
    casFeatCode_Assigned  = (null == casFeat_Assigned) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Assigned).getCode();

 
    casFeat_Truth = jcas.getRequiredFeatureDE(casType, "Truth", "org.apache.uima.cfe.SenseAnnotation", featOkTst);
    casFeatCode_Truth  = (null == casFeat_Truth) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Truth).getCode();

  }
}



    