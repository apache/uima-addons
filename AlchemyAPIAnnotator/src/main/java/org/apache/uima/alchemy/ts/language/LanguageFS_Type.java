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
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.cas.TOP_Type;

/**
 * Updated by JCasGen Sun Jan 24 22:11:51 CET 2010
 * 
 * @generated
 */
public class LanguageFS_Type extends TOP_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {
    return fsGenerator;
  }

  /** @generated */
  private final FSGenerator fsGenerator = new FSGenerator() {
    public FeatureStructure createFS(int addr, CASImpl cas) {
      if (LanguageFS_Type.this.useExistingInstance) {
        // Return eq fs instance if already created
        FeatureStructure fs = LanguageFS_Type.this.jcas.getJfsFromCaddr(addr);
        if (null == fs) {
          fs = new LanguageFS(addr, LanguageFS_Type.this);
          LanguageFS_Type.this.jcas.putJfsFromCaddr(addr, fs);
          return fs;
        }
        return fs;
      } else
        return new LanguageFS(addr, LanguageFS_Type.this);
    }
  };

  /** @generated */
  public final static int typeIndexID = LanguageFS.typeIndexID;

  /**
   * @generated
   * @modifiable
   */
  public final static boolean featOkTst = JCasRegistry
          .getFeatOkTst("org.apache.uima.alchemy.ts.language.LanguageFS");

  /** @generated */
  final Feature casFeat_language;

  /** @generated */
  final int casFeatCode_language;

  /** @generated */
  public String getLanguage(int addr) {
    if (featOkTst && casFeat_language == null)
      jcas.throwFeatMissing("language", "org.apache.uima.alchemy.ts.language.LanguageFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_language);
  }

  /** @generated */
  public void setLanguage(int addr, String v) {
    if (featOkTst && casFeat_language == null)
      jcas.throwFeatMissing("language", "org.apache.uima.alchemy.ts.language.LanguageFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_language, v);
  }

  /** @generated */
  final Feature casFeat_iso6391;

  /** @generated */
  final int casFeatCode_iso6391;

  /** @generated */
  public String getIso6391(int addr) {
    if (featOkTst && casFeat_iso6391 == null)
      jcas.throwFeatMissing("iso6391", "org.apache.uima.alchemy.ts.language.LanguageFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_iso6391);
  }

  /** @generated */
  public void setIso6391(int addr, String v) {
    if (featOkTst && casFeat_iso6391 == null)
      jcas.throwFeatMissing("iso6391", "org.apache.uima.alchemy.ts.language.LanguageFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_iso6391, v);
  }

  /** @generated */
  final Feature casFeat_iso6392;

  /** @generated */
  final int casFeatCode_iso6392;

  /** @generated */
  public String getIso6392(int addr) {
    if (featOkTst && casFeat_iso6392 == null)
      jcas.throwFeatMissing("iso6392", "org.apache.uima.alchemy.ts.language.LanguageFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_iso6392);
  }

  /** @generated */
  public void setIso6392(int addr, String v) {
    if (featOkTst && casFeat_iso6392 == null)
      jcas.throwFeatMissing("iso6392", "org.apache.uima.alchemy.ts.language.LanguageFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_iso6392, v);
  }

  /** @generated */
  final Feature casFeat_iso6393;

  /** @generated */
  final int casFeatCode_iso6393;

  /** @generated */
  public String getIso6393(int addr) {
    if (featOkTst && casFeat_iso6393 == null)
      jcas.throwFeatMissing("iso6393", "org.apache.uima.alchemy.ts.language.LanguageFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_iso6393);
  }

  /** @generated */
  public void setIso6393(int addr, String v) {
    if (featOkTst && casFeat_iso6393 == null)
      jcas.throwFeatMissing("iso6393", "org.apache.uima.alchemy.ts.language.LanguageFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_iso6393, v);
  }

  /** @generated */
  final Feature casFeat_ethnologue;

  /** @generated */
  final int casFeatCode_ethnologue;

  /** @generated */
  public String getEthnologue(int addr) {
    if (featOkTst && casFeat_ethnologue == null)
      jcas.throwFeatMissing("ethnologue", "org.apache.uima.alchemy.ts.language.LanguageFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_ethnologue);
  }

  /** @generated */
  public void setEthnologue(int addr, String v) {
    if (featOkTst && casFeat_ethnologue == null)
      jcas.throwFeatMissing("ethnologue", "org.apache.uima.alchemy.ts.language.LanguageFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_ethnologue, v);
  }

  /** @generated */
  final Feature casFeat_nativeSpeakers;

  /** @generated */
  final int casFeatCode_nativeSpeakers;

  /** @generated */
  public String getNativeSpeakers(int addr) {
    if (featOkTst && casFeat_nativeSpeakers == null)
      jcas.throwFeatMissing("nativeSpeakers", "org.apache.uima.alchemy.ts.language.LanguageFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_nativeSpeakers);
  }

  /** @generated */
  public void setNativeSpeakers(int addr, String v) {
    if (featOkTst && casFeat_nativeSpeakers == null)
      jcas.throwFeatMissing("nativeSpeakers", "org.apache.uima.alchemy.ts.language.LanguageFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_nativeSpeakers, v);
  }

  /** @generated */
  final Feature casFeat_wikipedia;

  /** @generated */
  final int casFeatCode_wikipedia;

  /** @generated */
  public String getWikipedia(int addr) {
    if (featOkTst && casFeat_wikipedia == null)
      jcas.throwFeatMissing("wikipedia", "org.apache.uima.alchemy.ts.language.LanguageFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_wikipedia);
  }

  /** @generated */
  public void setWikipedia(int addr, String v) {
    if (featOkTst && casFeat_wikipedia == null)
      jcas.throwFeatMissing("wikipedia", "org.apache.uima.alchemy.ts.language.LanguageFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_wikipedia, v);
  }

  /**
   * initialize variables to correspond with Cas Type and Features
   * 
   * @generated
   */
  public LanguageFS_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl) this.casType, getFSGenerator());

    casFeat_language = jcas.getRequiredFeatureDE(casType, "language", "uima.cas.String", featOkTst);
    casFeatCode_language = (null == casFeat_language) ? JCas.INVALID_FEATURE_CODE
            : ((FeatureImpl) casFeat_language).getCode();

    casFeat_iso6391 = jcas.getRequiredFeatureDE(casType, "iso6391", "uima.cas.String", featOkTst);
    casFeatCode_iso6391 = (null == casFeat_iso6391) ? JCas.INVALID_FEATURE_CODE
            : ((FeatureImpl) casFeat_iso6391).getCode();

    casFeat_iso6392 = jcas.getRequiredFeatureDE(casType, "iso6392", "uima.cas.String", featOkTst);
    casFeatCode_iso6392 = (null == casFeat_iso6392) ? JCas.INVALID_FEATURE_CODE
            : ((FeatureImpl) casFeat_iso6392).getCode();

    casFeat_iso6393 = jcas.getRequiredFeatureDE(casType, "iso6393", "uima.cas.String", featOkTst);
    casFeatCode_iso6393 = (null == casFeat_iso6393) ? JCas.INVALID_FEATURE_CODE
            : ((FeatureImpl) casFeat_iso6393).getCode();

    casFeat_ethnologue = jcas.getRequiredFeatureDE(casType, "ethnologue", "uima.cas.String",
            featOkTst);
    casFeatCode_ethnologue = (null == casFeat_ethnologue) ? JCas.INVALID_FEATURE_CODE
            : ((FeatureImpl) casFeat_ethnologue).getCode();

    casFeat_nativeSpeakers = jcas.getRequiredFeatureDE(casType, "nativeSpeakers",
            "uima.cas.String", featOkTst);
    casFeatCode_nativeSpeakers = (null == casFeat_nativeSpeakers) ? JCas.INVALID_FEATURE_CODE
            : ((FeatureImpl) casFeat_nativeSpeakers).getCode();

    casFeat_wikipedia = jcas.getRequiredFeatureDE(casType, "wikipedia", "uima.cas.String",
            featOkTst);
    casFeatCode_wikipedia = (null == casFeat_wikipedia) ? JCas.INVALID_FEATURE_CODE
            : ((FeatureImpl) casFeat_wikipedia).getCode();

  }
}
