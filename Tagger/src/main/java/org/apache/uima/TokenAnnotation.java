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

/* First created by JCasGen Thu Oct 25 11:28:37 CEST 2007 */
package org.apache.uima;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;

/**
 * Single token annotation Updated by JCasGen Thu Oct 25 11:28:37 CEST 2007 XML source:
 * C:/code/ApacheUIMA/Tagger/desc/HmmTaggerTAE.xml
 * 
 * @generated
 */
public class TokenAnnotation extends Annotation {
  /**
   * @generated
   * @ordered
   */
  public final static int typeIndexID = JCasRegistry.register(TokenAnnotation.class);

  /**
   * @generated
   * @ordered
   */
  public final static int type = typeIndexID;

  /** @generated */
  public int getTypeIndexID() {
    return typeIndexID;
  }

  /**
   * Never called. Disable default constructor
   * 
   * @generated
   */
  protected TokenAnnotation() {
  }

  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
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

  /**
   * <!-- begin-user-doc --> Write your own initialization here <!-- end-user-doc -->
   * 
   * @generated modifiable
   */
  private void readObject() {
  }

  // *--------------*
  // * Feature: tokenType

  /**
   * getter for tokenType - gets token type
   * 
   * @generated
   */
  public String getTokenType() {
    if (TokenAnnotation_Type.featOkTst
            && ((TokenAnnotation_Type) jcasType).casFeat_tokenType == null)
      jcasType.jcas.throwFeatMissing("tokenType", "org.apache.uima.TokenAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr,
            ((TokenAnnotation_Type) jcasType).casFeatCode_tokenType);
  }

  /**
   * setter for tokenType - sets token type
   * 
   * @generated
   */
  public void setTokenType(String v) {
    if (TokenAnnotation_Type.featOkTst
            && ((TokenAnnotation_Type) jcasType).casFeat_tokenType == null)
      jcasType.jcas.throwFeatMissing("tokenType", "org.apache.uima.TokenAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr,
            ((TokenAnnotation_Type) jcasType).casFeatCode_tokenType, v);
  }

  // *--------------*
  // * Feature: posTag

  /**
   * getter for posTag - gets contains part-of-speech of a corresponding token
   * 
   * @generated
   */
  public String getPosTag() {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type) jcasType).casFeat_posTag == null)
      jcasType.jcas.throwFeatMissing("posTag", "org.apache.uima.TokenAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr,
            ((TokenAnnotation_Type) jcasType).casFeatCode_posTag);
  }

  /**
   * setter for posTag - sets contains part-of-speech of a corresponding token
   * 
   * @generated
   */
  public void setPosTag(String v) {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type) jcasType).casFeat_posTag == null)
      jcasType.jcas.throwFeatMissing("posTag", "org.apache.uima.TokenAnnotation");
    jcasType.ll_cas
            .ll_setStringValue(addr, ((TokenAnnotation_Type) jcasType).casFeatCode_posTag, v);
  }
}
