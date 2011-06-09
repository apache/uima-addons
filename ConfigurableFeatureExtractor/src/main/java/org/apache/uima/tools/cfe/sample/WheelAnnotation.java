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

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;


/** annotation representing wheels of a car
 * Updated by JCasGen Fri Sep 05 14:53:11 EDT 2008
 * XML source: C:/eclipse/CFE/resources/samples/SampleTypeSystem.xml
 * @generated */
public class WheelAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(WheelAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected WheelAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public WheelAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public WheelAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public WheelAnnotation(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: Diameter

  /** getter for Diameter - gets diameter of a wheel
   * @generated */
  public float getDiameter() {
    if (WheelAnnotation_Type.featOkTst && ((WheelAnnotation_Type)jcasType).casFeat_Diameter == null)
      jcasType.jcas.throwFeatMissing("Diameter", "org.apache.uima.cfe.sample.WheelAnnotation");
    return jcasType.ll_cas.ll_getFloatValue(addr, ((WheelAnnotation_Type)jcasType).casFeatCode_Diameter);}
    
  /** setter for Diameter - sets diameter of a wheel 
   * @generated */
  public void setDiameter(float v) {
    if (WheelAnnotation_Type.featOkTst && ((WheelAnnotation_Type)jcasType).casFeat_Diameter == null)
      jcasType.jcas.throwFeatMissing("Diameter", "org.apache.uima.cfe.sample.WheelAnnotation");
    jcasType.ll_cas.ll_setFloatValue(addr, ((WheelAnnotation_Type)jcasType).casFeatCode_Diameter, v);}    
   
    
  //*--------------*
  //* Feature: Color

  /** getter for Color - gets color of a car
   * @generated */
  public String getColor() {
    if (WheelAnnotation_Type.featOkTst && ((WheelAnnotation_Type)jcasType).casFeat_Color == null)
      jcasType.jcas.throwFeatMissing("Color", "org.apache.uima.cfe.sample.WheelAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((WheelAnnotation_Type)jcasType).casFeatCode_Color);}
    
  /** setter for Color - sets color of a car 
   * @generated */
  public void setColor(String v) {
    if (WheelAnnotation_Type.featOkTst && ((WheelAnnotation_Type)jcasType).casFeat_Color == null)
      jcasType.jcas.throwFeatMissing("Color", "org.apache.uima.cfe.sample.WheelAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((WheelAnnotation_Type)jcasType).casFeatCode_Color, v);}    
  }

    