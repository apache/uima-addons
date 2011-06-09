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
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Fri Sep 05 14:53:11 EDT 2008
 * XML source: C:/eclipse/CFE/resources/samples/SampleTypeSystem.xml
 * @generated */
public class CarAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(CarAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected CarAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public CarAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public CarAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public CarAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: Wheels

  /** getter for Wheels - gets wheels of a car
   * @generated */
  public FSArray getWheels() {
    if (CarAnnotation_Type.featOkTst && ((CarAnnotation_Type)jcasType).casFeat_Wheels == null)
      jcasType.jcas.throwFeatMissing("Wheels", "org.apache.uima.cfe.sample.CarAnnotation");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((CarAnnotation_Type)jcasType).casFeatCode_Wheels)));}
    
  /** setter for Wheels - sets wheels of a car 
   * @generated */
  public void setWheels(FSArray v) {
    if (CarAnnotation_Type.featOkTst && ((CarAnnotation_Type)jcasType).casFeat_Wheels == null)
      jcasType.jcas.throwFeatMissing("Wheels", "org.apache.uima.cfe.sample.CarAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((CarAnnotation_Type)jcasType).casFeatCode_Wheels, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Wheels - gets an indexed value - wheels of a car
   * @generated */
  public WheelAnnotation getWheels(int i) {
    if (CarAnnotation_Type.featOkTst && ((CarAnnotation_Type)jcasType).casFeat_Wheels == null)
      jcasType.jcas.throwFeatMissing("Wheels", "org.apache.uima.cfe.sample.CarAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CarAnnotation_Type)jcasType).casFeatCode_Wheels), i);
    return (WheelAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CarAnnotation_Type)jcasType).casFeatCode_Wheels), i)));}

  /** indexed setter for Wheels - sets an indexed value - wheels of a car
   * @generated */
  public void setWheels(int i, WheelAnnotation v) { 
    if (CarAnnotation_Type.featOkTst && ((CarAnnotation_Type)jcasType).casFeat_Wheels == null)
      jcasType.jcas.throwFeatMissing("Wheels", "org.apache.uima.cfe.sample.CarAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CarAnnotation_Type)jcasType).casFeatCode_Wheels), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CarAnnotation_Type)jcasType).casFeatCode_Wheels), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: Engine

  /** getter for Engine - gets engine of a car
   * @generated */
  public EngineAnnotation getEngine() {
    if (CarAnnotation_Type.featOkTst && ((CarAnnotation_Type)jcasType).casFeat_Engine == null)
      jcasType.jcas.throwFeatMissing("Engine", "org.apache.uima.cfe.sample.CarAnnotation");
    return (EngineAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((CarAnnotation_Type)jcasType).casFeatCode_Engine)));}
    
  /** setter for Engine - sets engine of a car 
   * @generated */
  public void setEngine(EngineAnnotation v) {
    if (CarAnnotation_Type.featOkTst && ((CarAnnotation_Type)jcasType).casFeat_Engine == null)
      jcasType.jcas.throwFeatMissing("Engine", "org.apache.uima.cfe.sample.CarAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((CarAnnotation_Type)jcasType).casFeatCode_Engine, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    