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

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;


/** Annotation that contains truth(if available) and assigned senses
 * Updated by JCasGen Tue Mar 18 14:49:11 EDT 2008
 * XML source: C:/eclipse/CFE/org/apache/uima/cfe/AppliedSenseAnnotation.xml
 * @generated */
public class AppliedAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(AppliedAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected AppliedAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public AppliedAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public AppliedAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public AppliedAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: Assigned

  /** getter for Assigned - gets Annotation that contains information about sense assigned by applier
   * @generated */
  public SenseAnnotation getAssigned() {
    if (AppliedAnnotation_Type.featOkTst && ((AppliedAnnotation_Type)jcasType).casFeat_Assigned == null)
      jcasType.jcas.throwFeatMissing("Assigned", "org.apache.uima.cfe.AppliedAnnotation");
    return (SenseAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((AppliedAnnotation_Type)jcasType).casFeatCode_Assigned)));}
    
  /** setter for Assigned - sets Annotation that contains information about sense assigned by applier 
   * @generated */
  public void setAssigned(SenseAnnotation v) {
    if (AppliedAnnotation_Type.featOkTst && ((AppliedAnnotation_Type)jcasType).casFeat_Assigned == null)
      jcasType.jcas.throwFeatMissing("Assigned", "org.apache.uima.cfe.AppliedAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((AppliedAnnotation_Type)jcasType).casFeatCode_Assigned, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: Truth

  /** getter for Truth - gets Annotation that contains information about manualy preannotated sense
   * @generated */
  public SenseAnnotation getTruth() {
    if (AppliedAnnotation_Type.featOkTst && ((AppliedAnnotation_Type)jcasType).casFeat_Truth == null)
      jcasType.jcas.throwFeatMissing("Truth", "org.apache.uima.cfe.AppliedAnnotation");
    return (SenseAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((AppliedAnnotation_Type)jcasType).casFeatCode_Truth)));}
    
  /** setter for Truth - sets Annotation that contains information about manualy preannotated sense 
   * @generated */
  public void setTruth(SenseAnnotation v) {
    if (AppliedAnnotation_Type.featOkTst && ((AppliedAnnotation_Type)jcasType).casFeat_Truth == null)
      jcasType.jcas.throwFeatMissing("Truth", "org.apache.uima.cfe.AppliedAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((AppliedAnnotation_Type)jcasType).casFeatCode_Truth, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    