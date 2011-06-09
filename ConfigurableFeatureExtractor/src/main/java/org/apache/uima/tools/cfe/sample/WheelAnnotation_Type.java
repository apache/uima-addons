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

/** annotation representing wheels of a car
 * Updated by JCasGen Fri Sep 05 14:53:11 EDT 2008
 * @generated */
public class WheelAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (WheelAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = WheelAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new WheelAnnotation(addr, WheelAnnotation_Type.this);
  			   WheelAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new WheelAnnotation(addr, WheelAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = WheelAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.cfe.sample.WheelAnnotation");
 
  /** @generated */
  final Feature casFeat_Diameter;
  /** @generated */
  final int     casFeatCode_Diameter;
  /** @generated */ 
  public float getDiameter(int addr) {
        if (featOkTst && casFeat_Diameter == null)
      jcas.throwFeatMissing("Diameter", "org.apache.uima.cfe.sample.WheelAnnotation");
    return ll_cas.ll_getFloatValue(addr, casFeatCode_Diameter);
  }
  /** @generated */    
  public void setDiameter(int addr, float v) {
        if (featOkTst && casFeat_Diameter == null)
      jcas.throwFeatMissing("Diameter", "org.apache.uima.cfe.sample.WheelAnnotation");
    ll_cas.ll_setFloatValue(addr, casFeatCode_Diameter, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Color;
  /** @generated */
  final int     casFeatCode_Color;
  /** @generated */ 
  public String getColor(int addr) {
        if (featOkTst && casFeat_Color == null)
      jcas.throwFeatMissing("Color", "org.apache.uima.cfe.sample.WheelAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Color);
  }
  /** @generated */    
  public void setColor(int addr, String v) {
        if (featOkTst && casFeat_Color == null)
      jcas.throwFeatMissing("Color", "org.apache.uima.cfe.sample.WheelAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_Color, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public WheelAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Diameter = jcas.getRequiredFeatureDE(casType, "Diameter", "uima.cas.Float", featOkTst);
    casFeatCode_Diameter  = (null == casFeat_Diameter) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Diameter).getCode();

 
    casFeat_Color = jcas.getRequiredFeatureDE(casType, "Color", "uima.cas.String", featOkTst);
    casFeatCode_Color  = (null == casFeat_Color) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Color).getCode();

  }
}



    