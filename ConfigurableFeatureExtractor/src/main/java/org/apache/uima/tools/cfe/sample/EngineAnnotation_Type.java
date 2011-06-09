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


/* First created by JCasGen Fri Sep 05 14:43:49 EDT 2008 */
package org.apache.uima.tools.cfe.sample;

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

/** annotation representing an engine of a car
 * Updated by JCasGen Fri Sep 05 14:53:11 EDT 2008
 * @generated */
public class EngineAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (EngineAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = EngineAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new EngineAnnotation(addr, EngineAnnotation_Type.this);
  			   EngineAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new EngineAnnotation(addr, EngineAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = EngineAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.cfe.sample.EngineAnnotation");
 
  /** @generated */
  final Feature casFeat_Cylinders;
  /** @generated */
  final int     casFeatCode_Cylinders;
  /** @generated */ 
  public int getCylinders(int addr) {
        if (featOkTst && casFeat_Cylinders == null)
      jcas.throwFeatMissing("Cylinders", "org.apache.uima.cfe.sample.EngineAnnotation");
    return ll_cas.ll_getIntValue(addr, casFeatCode_Cylinders);
  }
  /** @generated */    
  public void setCylinders(int addr, int v) {
        if (featOkTst && casFeat_Cylinders == null)
      jcas.throwFeatMissing("Cylinders", "org.apache.uima.cfe.sample.EngineAnnotation");
    ll_cas.ll_setIntValue(addr, casFeatCode_Cylinders, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Size;
  /** @generated */
  final int     casFeatCode_Size;
  /** @generated */ 
  public float getSize(int addr) {
        if (featOkTst && casFeat_Size == null)
      jcas.throwFeatMissing("Size", "org.apache.uima.cfe.sample.EngineAnnotation");
    return ll_cas.ll_getFloatValue(addr, casFeatCode_Size);
  }
  /** @generated */    
  public void setSize(int addr, float v) {
        if (featOkTst && casFeat_Size == null)
      jcas.throwFeatMissing("Size", "org.apache.uima.cfe.sample.EngineAnnotation");
    ll_cas.ll_setFloatValue(addr, casFeatCode_Size, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public EngineAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Cylinders = jcas.getRequiredFeatureDE(casType, "Cylinders", "uima.cas.Integer", featOkTst);
    casFeatCode_Cylinders  = (null == casFeat_Cylinders) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Cylinders).getCode();

 
    casFeat_Size = jcas.getRequiredFeatureDE(casType, "Size", "uima.cas.Float", featOkTst);
    casFeatCode_Size  = (null == casFeat_Size) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Size).getCode();

  }
}



    