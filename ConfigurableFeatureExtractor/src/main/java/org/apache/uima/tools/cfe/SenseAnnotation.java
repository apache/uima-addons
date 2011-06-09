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



/* First created by JCasGen Fri Mar 07 11:37:18 EST 2008 */
package org.apache.uima.tools.cfe;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;


/** contains manually preannotated sense, you can derive this with your own type, that might have additional information
 * Updated by JCasGen Tue Mar 18 14:49:11 EDT 2008
 * XML source: C:/eclipse/CFE/org/apache/uima/cfe/AppliedSenseAnnotation.xml
 * @generated */
public class SenseAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SenseAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SenseAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SenseAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SenseAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SenseAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: SENSE

  /** getter for SENSE - gets sense name
   * @generated */
  public String getSENSE() {
    if (SenseAnnotation_Type.featOkTst && ((SenseAnnotation_Type)jcasType).casFeat_SENSE == null)
      jcasType.jcas.throwFeatMissing("SENSE", "org.apache.uima.cfe.SenseAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SenseAnnotation_Type)jcasType).casFeatCode_SENSE);}
    
  /** setter for SENSE - sets sense name 
   * @generated */
  public void setSENSE(String v) {
    if (SenseAnnotation_Type.featOkTst && ((SenseAnnotation_Type)jcasType).casFeat_SENSE == null)
      jcasType.jcas.throwFeatMissing("SENSE", "org.apache.uima.cfe.SenseAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((SenseAnnotation_Type)jcasType).casFeatCode_SENSE, v);}    
  }

    