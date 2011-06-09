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

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

/**
 * Updated by JCasGen Wed Nov 04 23:56:24 CET 2009
 * 
 * @generated
 */
public class Category_Type extends TOP_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {
    return fsGenerator;
  }

  /** @generated */
  private final FSGenerator fsGenerator = new FSGenerator() {
    public FeatureStructure createFS(int addr, CASImpl cas) {
      if (Category_Type.this.useExistingInstance) {
        // Return eq fs instance if already created
        FeatureStructure fs = Category_Type.this.jcas.getJfsFromCaddr(addr);
        if (null == fs) {
          fs = new Category(addr, Category_Type.this);
          Category_Type.this.jcas.putJfsFromCaddr(addr, fs);
          return fs;
        }
        return fs;
      } else
        return new Category(addr, Category_Type.this);
    }
  };

  /** @generated */
  public final static int typeIndexID = Category.typeIndexID;

  /**
   * @generated
   * @modifiable
   */
  public final static boolean featOkTst = JCasRegistry
          .getFeatOkTst("org.apache.uima.alchemy.ts.categorization.Category");

  /** @generated */
  final Feature casFeat_score;

  /** @generated */
  final int casFeatCode_score;

  /** @generated */
  public String getScore(int addr) {
    if (featOkTst && casFeat_score == null)
      jcas.throwFeatMissing("score", "org.apache.uima.alchemy.ts.categorization.Category");
    return ll_cas.ll_getStringValue(addr, casFeatCode_score);
  }

  /** @generated */
  public void setScore(int addr, String v) {
    if (featOkTst && casFeat_score == null)
      jcas.throwFeatMissing("score", "org.apache.uima.alchemy.ts.categorization.Category");
    ll_cas.ll_setStringValue(addr, casFeatCode_score, v);
  }

  /** @generated */
  final Feature casFeat_text;

  /** @generated */
  final int casFeatCode_text;

  /** @generated */
  public String getText(int addr) {
    if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "org.apache.uima.alchemy.ts.categorization.Category");
    return ll_cas.ll_getStringValue(addr, casFeatCode_text);
  }

  /** @generated */
  public void setText(int addr, String v) {
    if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "org.apache.uima.alchemy.ts.categorization.Category");
    ll_cas.ll_setStringValue(addr, casFeatCode_text, v);
  }

  /**
   * initialize variables to correspond with Cas Type and Features
   * 
   * @generated
   */
  public Category_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl) this.casType, getFSGenerator());

    casFeat_score = jcas.getRequiredFeatureDE(casType, "score", "uima.cas.String", featOkTst);
    casFeatCode_score = (null == casFeat_score) ? JCas.INVALID_FEATURE_CODE
            : ((FeatureImpl) casFeat_score).getCode();

    casFeat_text = jcas.getRequiredFeatureDE(casType, "text", "uima.cas.String", featOkTst);
    casFeatCode_text = (null == casFeat_text) ? JCas.INVALID_FEATURE_CODE
            : ((FeatureImpl) casFeat_text).getCode();

  }
}
