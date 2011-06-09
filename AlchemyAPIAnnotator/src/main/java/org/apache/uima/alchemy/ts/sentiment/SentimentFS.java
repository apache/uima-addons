/**
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

/* First created by JCasGen Mon Feb 28 10:31:26 CET 2011 */
package org.apache.uima.alchemy.ts.sentiment;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Fri Mar 11 17:40:07 CET 2011
 * XML source: /Users/tommasoteofili/Documents/workspaces/uima_workspace/uima/sandbox/AlchemyAPIAnnotator/desc/TextRankedEntityExtractionAEDescriptor.xml
 * @generated */
public class SentimentFS extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SentimentFS.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SentimentFS() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SentimentFS(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SentimentFS(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SentimentFS(JCas jcas, int begin, int end) {
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
  //* Feature: sentimentType

  /** getter for sentimentType - gets 
   * @generated */
  public String getSentimentType() {
    if (SentimentFS_Type.featOkTst && ((SentimentFS_Type)jcasType).casFeat_sentimentType == null)
      jcasType.jcas.throwFeatMissing("sentimentType", "org.apache.uima.alchemy.ts.sentiment.SentimentFS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SentimentFS_Type)jcasType).casFeatCode_sentimentType);}
    
  /** setter for sentimentType - sets  
   * @generated */
  public void setSentimentType(String v) {
    if (SentimentFS_Type.featOkTst && ((SentimentFS_Type)jcasType).casFeat_sentimentType == null)
      jcasType.jcas.throwFeatMissing("sentimentType", "org.apache.uima.alchemy.ts.sentiment.SentimentFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((SentimentFS_Type)jcasType).casFeatCode_sentimentType, v);}    
   
    
  //*--------------*
  //* Feature: score

  /** getter for score - gets 
   * @generated */
  public String getScore() {
    if (SentimentFS_Type.featOkTst && ((SentimentFS_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "org.apache.uima.alchemy.ts.sentiment.SentimentFS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SentimentFS_Type)jcasType).casFeatCode_score);}
    
  /** setter for score - sets  
   * @generated */
  public void setScore(String v) {
    if (SentimentFS_Type.featOkTst && ((SentimentFS_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "org.apache.uima.alchemy.ts.sentiment.SentimentFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((SentimentFS_Type)jcasType).casFeatCode_score, v);}    
  }

    