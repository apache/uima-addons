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

/* First created by JCasGen Sun Jan 24 22:11:51 CET 2010 */
package org.apache.uima.alchemy.ts.language;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;

/**
 * Updated by JCasGen Sun Jan 24 22:11:51 CET 2010 XML source:
 * 
 * @generated
 */
public class LanguageFS extends TOP {
  /**
   * @generated
   * @ordered
   */
  public final static int typeIndexID = JCasRegistry.register(LanguageFS.class);

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
  protected LanguageFS() {
  }

  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
  public LanguageFS(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }

  /** @generated */
  public LanguageFS(JCas jcas) {
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
  // * Feature: language

  /**
   * getter for language - gets
   * 
   * @generated
   */
  public String getLanguage() {
    if (LanguageFS_Type.featOkTst && ((LanguageFS_Type) jcasType).casFeat_language == null)
      jcasType.jcas.throwFeatMissing("language", "org.apache.uima.alchemy.ts.language.LanguageFS");
    return jcasType.ll_cas.ll_getStringValue(addr,
            ((LanguageFS_Type) jcasType).casFeatCode_language);
  }

  /**
   * setter for language - sets
   * 
   * @generated
   */
  public void setLanguage(String v) {
    if (LanguageFS_Type.featOkTst && ((LanguageFS_Type) jcasType).casFeat_language == null)
      jcasType.jcas.throwFeatMissing("language", "org.apache.uima.alchemy.ts.language.LanguageFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((LanguageFS_Type) jcasType).casFeatCode_language, v);
  }

  // *--------------*
  // * Feature: iso6391

  /**
   * getter for iso6391 - gets
   * 
   * @generated
   */
  public String getIso6391() {
    if (LanguageFS_Type.featOkTst && ((LanguageFS_Type) jcasType).casFeat_iso6391 == null)
      jcasType.jcas.throwFeatMissing("iso6391", "org.apache.uima.alchemy.ts.language.LanguageFS");
    return jcasType.ll_cas
            .ll_getStringValue(addr, ((LanguageFS_Type) jcasType).casFeatCode_iso6391);
  }

  /**
   * setter for iso6391 - sets
   * 
   * @generated
   */
  public void setIso6391(String v) {
    if (LanguageFS_Type.featOkTst && ((LanguageFS_Type) jcasType).casFeat_iso6391 == null)
      jcasType.jcas.throwFeatMissing("iso6391", "org.apache.uima.alchemy.ts.language.LanguageFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((LanguageFS_Type) jcasType).casFeatCode_iso6391, v);
  }

  // *--------------*
  // * Feature: iso6392

  /**
   * getter for iso6392 - gets
   * 
   * @generated
   */
  public String getIso6392() {
    if (LanguageFS_Type.featOkTst && ((LanguageFS_Type) jcasType).casFeat_iso6392 == null)
      jcasType.jcas.throwFeatMissing("iso6392", "org.apache.uima.alchemy.ts.language.LanguageFS");
    return jcasType.ll_cas
            .ll_getStringValue(addr, ((LanguageFS_Type) jcasType).casFeatCode_iso6392);
  }

  /**
   * setter for iso6392 - sets
   * 
   * @generated
   */
  public void setIso6392(String v) {
    if (LanguageFS_Type.featOkTst && ((LanguageFS_Type) jcasType).casFeat_iso6392 == null)
      jcasType.jcas.throwFeatMissing("iso6392", "org.apache.uima.alchemy.ts.language.LanguageFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((LanguageFS_Type) jcasType).casFeatCode_iso6392, v);
  }

  // *--------------*
  // * Feature: iso6393

  /**
   * getter for iso6393 - gets
   * 
   * @generated
   */
  public String getIso6393() {
    if (LanguageFS_Type.featOkTst && ((LanguageFS_Type) jcasType).casFeat_iso6393 == null)
      jcasType.jcas.throwFeatMissing("iso6393", "org.apache.uima.alchemy.ts.language.LanguageFS");
    return jcasType.ll_cas
            .ll_getStringValue(addr, ((LanguageFS_Type) jcasType).casFeatCode_iso6393);
  }

  /**
   * setter for iso6393 - sets
   * 
   * @generated
   */
  public void setIso6393(String v) {
    if (LanguageFS_Type.featOkTst && ((LanguageFS_Type) jcasType).casFeat_iso6393 == null)
      jcasType.jcas.throwFeatMissing("iso6393", "org.apache.uima.alchemy.ts.language.LanguageFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((LanguageFS_Type) jcasType).casFeatCode_iso6393, v);
  }

  // *--------------*
  // * Feature: ethnologue

  /**
   * getter for ethnologue - gets
   * 
   * @generated
   */
  public String getEthnologue() {
    if (LanguageFS_Type.featOkTst && ((LanguageFS_Type) jcasType).casFeat_ethnologue == null)
      jcasType.jcas
              .throwFeatMissing("ethnologue", "org.apache.uima.alchemy.ts.language.LanguageFS");
    return jcasType.ll_cas.ll_getStringValue(addr,
            ((LanguageFS_Type) jcasType).casFeatCode_ethnologue);
  }

  /**
   * setter for ethnologue - sets
   * 
   * @generated
   */
  public void setEthnologue(String v) {
    if (LanguageFS_Type.featOkTst && ((LanguageFS_Type) jcasType).casFeat_ethnologue == null)
      jcasType.jcas
              .throwFeatMissing("ethnologue", "org.apache.uima.alchemy.ts.language.LanguageFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((LanguageFS_Type) jcasType).casFeatCode_ethnologue, v);
  }

  // *--------------*
  // * Feature: nativeSpeakers

  /**
   * getter for nativeSpeakers - gets
   * 
   * @generated
   */
  public String getNativeSpeakers() {
    if (LanguageFS_Type.featOkTst && ((LanguageFS_Type) jcasType).casFeat_nativeSpeakers == null)
      jcasType.jcas.throwFeatMissing("nativeSpeakers",
              "org.apache.uima.alchemy.ts.language.LanguageFS");
    return jcasType.ll_cas.ll_getStringValue(addr,
            ((LanguageFS_Type) jcasType).casFeatCode_nativeSpeakers);
  }

  /**
   * setter for nativeSpeakers - sets
   * 
   * @generated
   */
  public void setNativeSpeakers(String v) {
    if (LanguageFS_Type.featOkTst && ((LanguageFS_Type) jcasType).casFeat_nativeSpeakers == null)
      jcasType.jcas.throwFeatMissing("nativeSpeakers",
              "org.apache.uima.alchemy.ts.language.LanguageFS");
    jcasType.ll_cas.ll_setStringValue(addr,
            ((LanguageFS_Type) jcasType).casFeatCode_nativeSpeakers, v);
  }

  // *--------------*
  // * Feature: wikipedia

  /**
   * getter for wikipedia - gets
   * 
   * @generated
   */
  public String getWikipedia() {
    if (LanguageFS_Type.featOkTst && ((LanguageFS_Type) jcasType).casFeat_wikipedia == null)
      jcasType.jcas.throwFeatMissing("wikipedia", "org.apache.uima.alchemy.ts.language.LanguageFS");
    return jcasType.ll_cas.ll_getStringValue(addr,
            ((LanguageFS_Type) jcasType).casFeatCode_wikipedia);
  }

  /**
   * setter for wikipedia - sets
   * 
   * @generated
   */
  public void setWikipedia(String v) {
    if (LanguageFS_Type.featOkTst && ((LanguageFS_Type) jcasType).casFeat_wikipedia == null)
      jcasType.jcas.throwFeatMissing("wikipedia", "org.apache.uima.alchemy.ts.language.LanguageFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((LanguageFS_Type) jcasType).casFeatCode_wikipedia, v);
  }
}
