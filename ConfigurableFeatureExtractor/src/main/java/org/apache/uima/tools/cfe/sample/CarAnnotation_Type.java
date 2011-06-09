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

/** 
 * Updated by JCasGen Fri Sep 05 14:53:11 EDT 2008
 * @generated */
public class CarAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (CarAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = CarAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new CarAnnotation(addr, CarAnnotation_Type.this);
  			   CarAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new CarAnnotation(addr, CarAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = CarAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.cfe.sample.CarAnnotation");
 
  /** @generated */
  final Feature casFeat_Wheels;
  /** @generated */
  final int     casFeatCode_Wheels;
  /** @generated */ 
  public int getWheels(int addr) {
        if (featOkTst && casFeat_Wheels == null)
      jcas.throwFeatMissing("Wheels", "org.apache.uima.cfe.sample.CarAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Wheels);
  }
  /** @generated */    
  public void setWheels(int addr, int v) {
        if (featOkTst && casFeat_Wheels == null)
      jcas.throwFeatMissing("Wheels", "org.apache.uima.cfe.sample.CarAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_Wheels, v);}
    
   /** @generated */
  public int getWheels(int addr, int i) {
        if (featOkTst && casFeat_Wheels == null)
      jcas.throwFeatMissing("Wheels", "org.apache.uima.cfe.sample.CarAnnotation");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Wheels), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Wheels), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Wheels), i);
  }
   
  /** @generated */ 
  public void setWheels(int addr, int i, int v) {
        if (featOkTst && casFeat_Wheels == null)
      jcas.throwFeatMissing("Wheels", "org.apache.uima.cfe.sample.CarAnnotation");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Wheels), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Wheels), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Wheels), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_Engine;
  /** @generated */
  final int     casFeatCode_Engine;
  /** @generated */ 
  public int getEngine(int addr) {
        if (featOkTst && casFeat_Engine == null)
      jcas.throwFeatMissing("Engine", "org.apache.uima.cfe.sample.CarAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Engine);
  }
  /** @generated */    
  public void setEngine(int addr, int v) {
        if (featOkTst && casFeat_Engine == null)
      jcas.throwFeatMissing("Engine", "org.apache.uima.cfe.sample.CarAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_Engine, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public CarAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Wheels = jcas.getRequiredFeatureDE(casType, "Wheels", "uima.cas.FSArray", featOkTst);
    casFeatCode_Wheels  = (null == casFeat_Wheels) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Wheels).getCode();

 
    casFeat_Engine = jcas.getRequiredFeatureDE(casType, "Engine", "org.apache.uima.cfe.sample.EngineAnnotation", featOkTst);
    casFeatCode_Engine  = (null == casFeat_Engine) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Engine).getCode();

  }
}



    