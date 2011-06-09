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


/** annotation representing an engine of a car
 * Updated by JCasGen Fri Sep 05 14:53:11 EDT 2008
 * XML source: C:/eclipse/CFE/resources/samples/SampleTypeSystem.xml
 * @generated */
public class EngineAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(EngineAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected EngineAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public EngineAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public EngineAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public EngineAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: Cylinders

  /** getter for Cylinders - gets Number of cylinders
   * @generated */
  public int getCylinders() {
    if (EngineAnnotation_Type.featOkTst && ((EngineAnnotation_Type)jcasType).casFeat_Cylinders == null)
      jcasType.jcas.throwFeatMissing("Cylinders", "org.apache.uima.cfe.sample.EngineAnnotation");
    return jcasType.ll_cas.ll_getIntValue(addr, ((EngineAnnotation_Type)jcasType).casFeatCode_Cylinders);}
    
  /** setter for Cylinders - sets Number of cylinders 
   * @generated */
  public void setCylinders(int v) {
    if (EngineAnnotation_Type.featOkTst && ((EngineAnnotation_Type)jcasType).casFeat_Cylinders == null)
      jcasType.jcas.throwFeatMissing("Cylinders", "org.apache.uima.cfe.sample.EngineAnnotation");
    jcasType.ll_cas.ll_setIntValue(addr, ((EngineAnnotation_Type)jcasType).casFeatCode_Cylinders, v);}    
   
    
  //*--------------*
  //* Feature: Size

  /** getter for Size - gets size of an engine
   * @generated */
  public float getSize() {
    if (EngineAnnotation_Type.featOkTst && ((EngineAnnotation_Type)jcasType).casFeat_Size == null)
      jcasType.jcas.throwFeatMissing("Size", "org.apache.uima.cfe.sample.EngineAnnotation");
    return jcasType.ll_cas.ll_getFloatValue(addr, ((EngineAnnotation_Type)jcasType).casFeatCode_Size);}
    
  /** setter for Size - sets size of an engine 
   * @generated */
  public void setSize(float v) {
    if (EngineAnnotation_Type.featOkTst && ((EngineAnnotation_Type)jcasType).casFeat_Size == null)
      jcasType.jcas.throwFeatMissing("Size", "org.apache.uima.cfe.sample.EngineAnnotation");
    jcasType.ll_cas.ll_setFloatValue(addr, ((EngineAnnotation_Type)jcasType).casFeatCode_Size, v);}    
  }

    