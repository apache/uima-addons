/**
 * 	Licensed to the Apache Software Foundation (ASF) under one
 * 	or more contributor license agreements.  See the NOTICE file
 * 	distributed with this work for additional information
 * 	regarding copyright ownership.  The ASF licenses this file
 * 	to you under the Apache License, Version 2.0 (the
 * 	"License"); you may not use this file except in compliance
 * 	with the License.  You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * 	Unless required by applicable law or agreed to in writing,
 * 	software distributed under the License is distributed on an
 * 	"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * 	KIND, either express or implied.  See the License for the
 * 	specific language governing permissions and limitations
 * 	under the License.
 */
/* First created by JCasGen Wed Nov 04 23:56:01 CET 2009 */
package org.apache.uima.alchemy.ts.categorization;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.cas.TOP_Type;

/**
 * Updated by JCasGen Wed Nov 04 23:56:24 CET 2009 XML source:
 * 
 * @generated
 */
public class Category extends TOP {
  /**
   * @generated
   * @ordered
   */
  public final static int typeIndexID = JCasRegistry.register(Category.class);

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
  protected Category() {
  }

  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
  public Category(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }

  /** @generated */
  public Category(JCas jcas) {
    super(jcas);
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
  // * Feature: score

  /**
   * getter for score - gets
   * 
   * @generated
   */
  public String getScore() {
    if (Category_Type.featOkTst && ((Category_Type) jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "org.apache.uima.alchemy.ts.categorization.Category");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Category_Type) jcasType).casFeatCode_score);
  }

  /**
   * setter for score - sets
   * 
   * @generated
   */
  public void setScore(String v) {
    if (Category_Type.featOkTst && ((Category_Type) jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "org.apache.uima.alchemy.ts.categorization.Category");
    jcasType.ll_cas.ll_setStringValue(addr, ((Category_Type) jcasType).casFeatCode_score, v);
  }

  // *--------------*
  // * Feature: text

  /**
   * getter for text - gets
   * 
   * @generated
   */
  public String getText() {
    if (Category_Type.featOkTst && ((Category_Type) jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "org.apache.uima.alchemy.ts.categorization.Category");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Category_Type) jcasType).casFeatCode_text);
  }

  /**
   * setter for text - sets
   * 
   * @generated
   */
  public void setText(String v) {
    if (Category_Type.featOkTst && ((Category_Type) jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "org.apache.uima.alchemy.ts.categorization.Category");
    jcasType.ll_cas.ll_setStringValue(addr, ((Category_Type) jcasType).casFeatCode_text, v);
  }
}
