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



/* First created by JCasGen Thu Nov 22 13:50:18 CET 2007 */
package org.apache.uima.simpleserver.test;

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
 * Updated by JCasGen Thu Nov 22 13:50:18 CET 2007
 * 
 * @generated
 */
public class TypeWithListsAndArrays_Type extends TOP_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {
    return this.fsGenerator;
  }

  /** @generated */
  private final FSGenerator fsGenerator = new FSGenerator() {
    public FeatureStructure createFS(int addr, CASImpl cas) {
      if (TypeWithListsAndArrays_Type.this.useExistingInstance) {
        // Return eq fs instance if already created
        FeatureStructure fs = TypeWithListsAndArrays_Type.this.jcas.getJfsFromCaddr(addr);
        if (null == fs) {
          fs = new TypeWithListsAndArrays(addr, TypeWithListsAndArrays_Type.this);
          TypeWithListsAndArrays_Type.this.jcas.putJfsFromCaddr(addr, fs);
          return fs;
        }
        return fs;
      }
      return new TypeWithListsAndArrays(addr, TypeWithListsAndArrays_Type.this);
    }
  };

  /** @generated */
  @SuppressWarnings("hiding")
  public final static int typeIndexID = TypeWithListsAndArrays.typeIndexID;

  /**
   * @generated
   * @modifiable
   */
  public final static boolean featOkTst = JCasRegistry
      .getFeatOkTst("org.apache.uima.simpleserver.test.TypeWithListsAndArrays");

  /** @generated */
  final Feature casFeat_stringList;

  /** @generated */
  final int casFeatCode_stringList;

  /** @generated */
  public String getStringList(int addr) {
    if (featOkTst && this.casFeat_stringList == null) {
      this.jcas.throwFeatMissing("stringList",
          "org.apache.uima.simpleserver.test.TypeWithListsAndArrays");
    }
    return this.ll_cas.ll_getStringValue(addr, this.casFeatCode_stringList);
  }

  /** @generated */
  public void setStringList(int addr, String v) {
    if (featOkTst && this.casFeat_stringList == null) {
      this.jcas.throwFeatMissing("stringList",
          "org.apache.uima.simpleserver.test.TypeWithListsAndArrays");
    }
    this.ll_cas.ll_setStringValue(addr, this.casFeatCode_stringList, v);
  }

  /** @generated */
  final Feature casFeat_annotationArray;

  /** @generated */
  final int casFeatCode_annotationArray;

  /** @generated */
  public int getAnnotationArray(int addr) {
    if (featOkTst && this.casFeat_annotationArray == null) {
      this.jcas.throwFeatMissing("annotationArray",
          "org.apache.uima.simpleserver.test.TypeWithListsAndArrays");
    }
    return this.ll_cas.ll_getRefValue(addr, this.casFeatCode_annotationArray);
  }

  /** @generated */
  public void setAnnotationArray(int addr, int v) {
    if (featOkTst && this.casFeat_annotationArray == null) {
      this.jcas.throwFeatMissing("annotationArray",
          "org.apache.uima.simpleserver.test.TypeWithListsAndArrays");
    }
    this.ll_cas.ll_setRefValue(addr, this.casFeatCode_annotationArray, v);
  }

  /** @generated */
  public int getAnnotationArray(int addr, int i) {
    if (featOkTst && this.casFeat_annotationArray == null) {
      this.jcas.throwFeatMissing("annotationArray",
          "org.apache.uima.simpleserver.test.TypeWithListsAndArrays");
    }
    if (this.lowLevelTypeChecks) {
      return this.ll_cas.ll_getRefArrayValue(this.ll_cas.ll_getRefValue(addr,
          this.casFeatCode_annotationArray), i, true);
    }
    this.jcas.checkArrayBounds(this.ll_cas.ll_getRefValue(addr, this.casFeatCode_annotationArray),
        i);
    return this.ll_cas.ll_getRefArrayValue(this.ll_cas.ll_getRefValue(addr,
        this.casFeatCode_annotationArray), i);
  }

  /** @generated */
  public void setAnnotationArray(int addr, int i, int v) {
    if (featOkTst && this.casFeat_annotationArray == null) {
      this.jcas.throwFeatMissing("annotationArray",
          "org.apache.uima.simpleserver.test.TypeWithListsAndArrays");
    }
    if (this.lowLevelTypeChecks) {
      this.ll_cas.ll_setRefArrayValue(this.ll_cas.ll_getRefValue(addr,
          this.casFeatCode_annotationArray), i, v, true);
    }
    this.jcas.checkArrayBounds(this.ll_cas.ll_getRefValue(addr, this.casFeatCode_annotationArray),
        i);
    this.ll_cas.ll_setRefArrayValue(this.ll_cas.ll_getRefValue(addr,
        this.casFeatCode_annotationArray), i, v);
  }

  /**
   * initialize variables to correspond with Cas Type and Features
   * 
   * @generated
   */
  public TypeWithListsAndArrays_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    this.casImpl.getFSClassRegistry()
        .addGeneratorForType((TypeImpl) this.casType, getFSGenerator());

    this.casFeat_stringList = jcas.getRequiredFeatureDE(casType, "stringList", "uima.cas.String",
        featOkTst);
    this.casFeatCode_stringList = null == this.casFeat_stringList ? JCas.INVALID_FEATURE_CODE
        : ((FeatureImpl) this.casFeat_stringList).getCode();

    this.casFeat_annotationArray = jcas.getRequiredFeatureDE(casType, "annotationArray",
        "uima.cas.FSArray", featOkTst);
    this.casFeatCode_annotationArray = null == this.casFeat_annotationArray ? JCas.INVALID_FEATURE_CODE
        : ((FeatureImpl) this.casFeat_annotationArray).getCode();

  }
}
