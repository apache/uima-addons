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
import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Mar 18 14:49:11 EDT 2008
 * XML source: C:/eclipse/CFE/org/apache/uima/cfe/AppliedSenseAnnotation.xml
 * @generated */
public class ApplierInfoAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(ApplierInfoAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected ApplierInfoAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public ApplierInfoAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public ApplierInfoAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public ApplierInfoAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: Features

  /** getter for Features - gets List of features for a term
   * @generated */
  public StringArray getFeatures() {
    if (ApplierInfoAnnotation_Type.featOkTst && ((ApplierInfoAnnotation_Type)jcasType).casFeat_Features == null)
      jcasType.jcas.throwFeatMissing("Features", "org.apache.uima.cfe.ApplierInfoAnnotation");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ApplierInfoAnnotation_Type)jcasType).casFeatCode_Features)));}
    
  /** setter for Features - sets List of features for a term 
   * @generated */
  public void setFeatures(StringArray v) {
    if (ApplierInfoAnnotation_Type.featOkTst && ((ApplierInfoAnnotation_Type)jcasType).casFeat_Features == null)
      jcasType.jcas.throwFeatMissing("Features", "org.apache.uima.cfe.ApplierInfoAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((ApplierInfoAnnotation_Type)jcasType).casFeatCode_Features, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Features - gets an indexed value - List of features for a term
   * @generated */
  public String getFeatures(int i) {
    if (ApplierInfoAnnotation_Type.featOkTst && ((ApplierInfoAnnotation_Type)jcasType).casFeat_Features == null)
      jcasType.jcas.throwFeatMissing("Features", "org.apache.uima.cfe.ApplierInfoAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((ApplierInfoAnnotation_Type)jcasType).casFeatCode_Features), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((ApplierInfoAnnotation_Type)jcasType).casFeatCode_Features), i);}

  /** indexed setter for Features - sets an indexed value - List of features for a term
   * @generated */
  public void setFeatures(int i, String v) { 
    if (ApplierInfoAnnotation_Type.featOkTst && ((ApplierInfoAnnotation_Type)jcasType).casFeat_Features == null)
      jcasType.jcas.throwFeatMissing("Features", "org.apache.uima.cfe.ApplierInfoAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((ApplierInfoAnnotation_Type)jcasType).casFeatCode_Features), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((ApplierInfoAnnotation_Type)jcasType).casFeatCode_Features), i, v);}
   
    
  //*--------------*
  //* Feature: AppliedAnnotation

  /** getter for AppliedAnnotation - gets Annotation that contains assigned senses as well as it might contain onfo about manualy preannotated sense
   * @generated */
  public AppliedAnnotation getAppliedAnnotation() {
    if (ApplierInfoAnnotation_Type.featOkTst && ((ApplierInfoAnnotation_Type)jcasType).casFeat_AppliedAnnotation == null)
      jcasType.jcas.throwFeatMissing("AppliedAnnotation", "org.apache.uima.cfe.ApplierInfoAnnotation");
    return (AppliedAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ApplierInfoAnnotation_Type)jcasType).casFeatCode_AppliedAnnotation)));}
    
  /** setter for AppliedAnnotation - sets Annotation that contains assigned senses as well as it might contain onfo about manualy preannotated sense 
   * @generated */
  public void setAppliedAnnotation(AppliedAnnotation v) {
    if (ApplierInfoAnnotation_Type.featOkTst && ((ApplierInfoAnnotation_Type)jcasType).casFeat_AppliedAnnotation == null)
      jcasType.jcas.throwFeatMissing("AppliedAnnotation", "org.apache.uima.cfe.ApplierInfoAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((ApplierInfoAnnotation_Type)jcasType).casFeatCode_AppliedAnnotation, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: Context

  /** getter for Context - gets 
   * @generated */
  public StringArray getContext() {
    if (ApplierInfoAnnotation_Type.featOkTst && ((ApplierInfoAnnotation_Type)jcasType).casFeat_Context == null)
      jcasType.jcas.throwFeatMissing("Context", "org.apache.uima.cfe.ApplierInfoAnnotation");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ApplierInfoAnnotation_Type)jcasType).casFeatCode_Context)));}
    
  /** setter for Context - sets  
   * @generated */
  public void setContext(StringArray v) {
    if (ApplierInfoAnnotation_Type.featOkTst && ((ApplierInfoAnnotation_Type)jcasType).casFeat_Context == null)
      jcasType.jcas.throwFeatMissing("Context", "org.apache.uima.cfe.ApplierInfoAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((ApplierInfoAnnotation_Type)jcasType).casFeatCode_Context, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Context - gets an indexed value - 
   * @generated */
  public String getContext(int i) {
    if (ApplierInfoAnnotation_Type.featOkTst && ((ApplierInfoAnnotation_Type)jcasType).casFeat_Context == null)
      jcasType.jcas.throwFeatMissing("Context", "org.apache.uima.cfe.ApplierInfoAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((ApplierInfoAnnotation_Type)jcasType).casFeatCode_Context), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((ApplierInfoAnnotation_Type)jcasType).casFeatCode_Context), i);}

  /** indexed setter for Context - sets an indexed value - 
   * @generated */
  public void setContext(int i, String v) { 
    if (ApplierInfoAnnotation_Type.featOkTst && ((ApplierInfoAnnotation_Type)jcasType).casFeat_Context == null)
      jcasType.jcas.throwFeatMissing("Context", "org.apache.uima.cfe.ApplierInfoAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((ApplierInfoAnnotation_Type)jcasType).casFeatCode_Context), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((ApplierInfoAnnotation_Type)jcasType).casFeatCode_Context), i, v);}
   
    
  //*--------------*
  //* Feature: DocumentId

  /** getter for DocumentId - gets unique id of a document (used in tf*idf calculations)
   * @generated */
  public String getDocumentId() {
    if (ApplierInfoAnnotation_Type.featOkTst && ((ApplierInfoAnnotation_Type)jcasType).casFeat_DocumentId == null)
      jcasType.jcas.throwFeatMissing("DocumentId", "org.apache.uima.cfe.ApplierInfoAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ApplierInfoAnnotation_Type)jcasType).casFeatCode_DocumentId);}
    
  /** setter for DocumentId - sets unique id of a document (used in tf*idf calculations) 
   * @generated */
  public void setDocumentId(String v) {
    if (ApplierInfoAnnotation_Type.featOkTst && ((ApplierInfoAnnotation_Type)jcasType).casFeat_DocumentId == null)
      jcasType.jcas.throwFeatMissing("DocumentId", "org.apache.uima.cfe.ApplierInfoAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((ApplierInfoAnnotation_Type)jcasType).casFeatCode_DocumentId, v);}    
  }

    