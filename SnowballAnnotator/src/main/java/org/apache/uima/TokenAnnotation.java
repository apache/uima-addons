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

/* First created by JCasGen Thu Sep 02 14:47:15 EDT 2010 */
package org.apache.uima;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** Single token annotation
 * Updated by JCasGen Thu Sep 02 14:47:15 EDT 2010
 * XML source: D:/mavenAlign/sandbox-trunk-data/SnowballAnnotator/desc/SnowballAnnotator.xml
 * @generated */
public class TokenAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(TokenAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected TokenAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public TokenAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public TokenAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public TokenAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: stem

  /** getter for stem - gets stem
   * @generated */
  public String getStem() {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_stem == null)
      jcasType.jcas.throwFeatMissing("stem", "org.apache.uima.TokenAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_stem);}
    
  /** setter for stem - sets stem 
   * @generated */
  public void setStem(String v) {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_stem == null)
      jcasType.jcas.throwFeatMissing("stem", "org.apache.uima.TokenAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_stem, v);}    
  }

    