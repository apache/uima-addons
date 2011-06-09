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
import org.apache.uima.jcas.tcas.Annotation_Type;

/**
 * Updated by JCasGen Thu Nov 22 13:50:18 CET 2007
 * 
 * @generated
 */
public class CharacterAnnotation_Type extends Annotation_Type {

  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {
    return this.fsGenerator;
  }

  /** @generated */
  private final FSGenerator fsGenerator = new FSGenerator() {
    public FeatureStructure createFS(int addr, CASImpl cas) {
      if (CharacterAnnotation_Type.this.useExistingInstance) {
        // Return eq fs instance if already created
        FeatureStructure fs = CharacterAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
        if (null == fs) {
          fs = new CharacterAnnotation(addr, CharacterAnnotation_Type.this);
          CharacterAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
          return fs;
        }
        return fs;
      }
      return new CharacterAnnotation(addr, CharacterAnnotation_Type.this);
    }
  };

  /** @generated */
  @SuppressWarnings("hiding")
  public final static int typeIndexID = CharacterAnnotation.typeIndexID;

  /**
   * @generated
   * @modifiable
   */
  @SuppressWarnings("hiding")
  public final static boolean featOkTst = JCasRegistry
      .getFeatOkTst("org.apache.uima.simpleserver.test.CharacterAnnotation");

  /** @generated */
  final Feature casFeat_booleanFeature;

  /** @generated */
  final int casFeatCode_booleanFeature;

  /** @generated */
  public boolean getBooleanFeature(int addr) {
    if (featOkTst && this.casFeat_booleanFeature == null) {
      this.jcas.throwFeatMissing("booleanFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    return this.ll_cas.ll_getBooleanValue(addr, this.casFeatCode_booleanFeature);
  }

  /** @generated */
  public void setBooleanFeature(int addr, boolean v) {
    if (featOkTst && this.casFeat_booleanFeature == null) {
      this.jcas.throwFeatMissing("booleanFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    this.ll_cas.ll_setBooleanValue(addr, this.casFeatCode_booleanFeature, v);
  }

  /** @generated */
  final Feature casFeat_byteFeature;

  /** @generated */
  final int casFeatCode_byteFeature;

  /** @generated */
  public byte getByteFeature(int addr) {
    if (featOkTst && this.casFeat_byteFeature == null) {
      this.jcas.throwFeatMissing("byteFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    return this.ll_cas.ll_getByteValue(addr, this.casFeatCode_byteFeature);
  }

  /** @generated */
  public void setByteFeature(int addr, byte v) {
    if (featOkTst && this.casFeat_byteFeature == null) {
      this.jcas.throwFeatMissing("byteFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    this.ll_cas.ll_setByteValue(addr, this.casFeatCode_byteFeature, v);
  }

  /** @generated */
  final Feature casFeat_shortFeature;

  /** @generated */
  final int casFeatCode_shortFeature;

  /** @generated */
  public short getShortFeature(int addr) {
    if (featOkTst && this.casFeat_shortFeature == null) {
      this.jcas.throwFeatMissing("shortFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    return this.ll_cas.ll_getShortValue(addr, this.casFeatCode_shortFeature);
  }

  /** @generated */
  public void setShortFeature(int addr, short v) {
    if (featOkTst && this.casFeat_shortFeature == null) {
      this.jcas.throwFeatMissing("shortFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    this.ll_cas.ll_setShortValue(addr, this.casFeatCode_shortFeature, v);
  }

  /** @generated */
  final Feature casFeat_integerFeature;

  /** @generated */
  final int casFeatCode_integerFeature;

  /** @generated */
  public int getIntegerFeature(int addr) {
    if (featOkTst && this.casFeat_integerFeature == null) {
      this.jcas.throwFeatMissing("integerFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    return this.ll_cas.ll_getIntValue(addr, this.casFeatCode_integerFeature);
  }

  /** @generated */
  public void setIntegerFeature(int addr, int v) {
    if (featOkTst && this.casFeat_integerFeature == null) {
      this.jcas.throwFeatMissing("integerFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    this.ll_cas.ll_setIntValue(addr, this.casFeatCode_integerFeature, v);
  }

  /** @generated */
  final Feature casFeat_longFeature;

  /** @generated */
  final int casFeatCode_longFeature;

  /** @generated */
  public long getLongFeature(int addr) {
    if (featOkTst && this.casFeat_longFeature == null) {
      this.jcas.throwFeatMissing("longFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    return this.ll_cas.ll_getLongValue(addr, this.casFeatCode_longFeature);
  }

  /** @generated */
  public void setLongFeature(int addr, long v) {
    if (featOkTst && this.casFeat_longFeature == null) {
      this.jcas.throwFeatMissing("longFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    this.ll_cas.ll_setLongValue(addr, this.casFeatCode_longFeature, v);
  }

  /** @generated */
  final Feature casFeat_floatFeature;

  /** @generated */
  final int casFeatCode_floatFeature;

  /** @generated */
  public float getFloatFeature(int addr) {
    if (featOkTst && this.casFeat_floatFeature == null) {
      this.jcas.throwFeatMissing("floatFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    return this.ll_cas.ll_getFloatValue(addr, this.casFeatCode_floatFeature);
  }

  /** @generated */
  public void setFloatFeature(int addr, float v) {
    if (featOkTst && this.casFeat_floatFeature == null) {
      this.jcas.throwFeatMissing("floatFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    this.ll_cas.ll_setFloatValue(addr, this.casFeatCode_floatFeature, v);
  }

  /** @generated */
  final Feature casFeat_doubleFeature;

  /** @generated */
  final int casFeatCode_doubleFeature;

  /** @generated */
  public double getDoubleFeature(int addr) {
    if (featOkTst && this.casFeat_doubleFeature == null) {
      this.jcas.throwFeatMissing("doubleFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    return this.ll_cas.ll_getDoubleValue(addr, this.casFeatCode_doubleFeature);
  }

  /** @generated */
  public void setDoubleFeature(int addr, double v) {
    if (featOkTst && this.casFeat_doubleFeature == null) {
      this.jcas.throwFeatMissing("doubleFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    this.ll_cas.ll_setDoubleValue(addr, this.casFeatCode_doubleFeature, v);
  }

  /** @generated */
  final Feature casFeat_stringFeature;

  /** @generated */
  final int casFeatCode_stringFeature;

  /** @generated */
  public String getStringFeature(int addr) {
    if (featOkTst && this.casFeat_stringFeature == null) {
      this.jcas.throwFeatMissing("stringFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    return this.ll_cas.ll_getStringValue(addr, this.casFeatCode_stringFeature);
  }

  /** @generated */
  public void setStringFeature(int addr, String v) {
    if (featOkTst && this.casFeat_stringFeature == null) {
      this.jcas.throwFeatMissing("stringFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    this.ll_cas.ll_setStringValue(addr, this.casFeatCode_stringFeature, v);
  }

  /** @generated */
  final Feature casFeat_fsFeature;

  /** @generated */
  final int casFeatCode_fsFeature;

  /** @generated */
  public int getFsFeature(int addr) {
    if (featOkTst && this.casFeat_fsFeature == null) {
      this.jcas.throwFeatMissing("fsFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    return this.ll_cas.ll_getRefValue(addr, this.casFeatCode_fsFeature);
  }

  /** @generated */
  public void setFsFeature(int addr, int v) {
    if (featOkTst && this.casFeat_fsFeature == null) {
      this.jcas.throwFeatMissing("fsFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    this.ll_cas.ll_setRefValue(addr, this.casFeatCode_fsFeature, v);
  }

  /**
   * initialize variables to correspond with Cas Type and Features
   * 
   * @generated
   */
  public CharacterAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    this.casImpl.getFSClassRegistry()
        .addGeneratorForType((TypeImpl) this.casType, getFSGenerator());

    this.casFeat_booleanFeature = jcas.getRequiredFeatureDE(casType, "booleanFeature",
        "uima.cas.Boolean", featOkTst);
    this.casFeatCode_booleanFeature = null == this.casFeat_booleanFeature ? JCas.INVALID_FEATURE_CODE
        : ((FeatureImpl) this.casFeat_booleanFeature).getCode();

    this.casFeat_byteFeature = jcas.getRequiredFeatureDE(casType, "byteFeature", "uima.cas.Byte",
        featOkTst);
    this.casFeatCode_byteFeature = null == this.casFeat_byteFeature ? JCas.INVALID_FEATURE_CODE
        : ((FeatureImpl) this.casFeat_byteFeature).getCode();

    this.casFeat_shortFeature = jcas.getRequiredFeatureDE(casType, "shortFeature",
        "uima.cas.Short", featOkTst);
    this.casFeatCode_shortFeature = null == this.casFeat_shortFeature ? JCas.INVALID_FEATURE_CODE
        : ((FeatureImpl) this.casFeat_shortFeature).getCode();

    this.casFeat_integerFeature = jcas.getRequiredFeatureDE(casType, "integerFeature",
        "uima.cas.Integer", featOkTst);
    this.casFeatCode_integerFeature = null == this.casFeat_integerFeature ? JCas.INVALID_FEATURE_CODE
        : ((FeatureImpl) this.casFeat_integerFeature).getCode();

    this.casFeat_longFeature = jcas.getRequiredFeatureDE(casType, "longFeature", "uima.cas.Long",
        featOkTst);
    this.casFeatCode_longFeature = null == this.casFeat_longFeature ? JCas.INVALID_FEATURE_CODE
        : ((FeatureImpl) this.casFeat_longFeature).getCode();

    this.casFeat_floatFeature = jcas.getRequiredFeatureDE(casType, "floatFeature",
        "uima.cas.Float", featOkTst);
    this.casFeatCode_floatFeature = null == this.casFeat_floatFeature ? JCas.INVALID_FEATURE_CODE
        : ((FeatureImpl) this.casFeat_floatFeature).getCode();

    this.casFeat_doubleFeature = jcas.getRequiredFeatureDE(casType, "doubleFeature",
        "uima.cas.Double", featOkTst);
    this.casFeatCode_doubleFeature = null == this.casFeat_doubleFeature ? JCas.INVALID_FEATURE_CODE
        : ((FeatureImpl) this.casFeat_doubleFeature).getCode();

    this.casFeat_stringFeature = jcas.getRequiredFeatureDE(casType, "stringFeature",
        "uima.cas.String", featOkTst);
    this.casFeatCode_stringFeature = null == this.casFeat_stringFeature ? JCas.INVALID_FEATURE_CODE
        : ((FeatureImpl) this.casFeat_stringFeature).getCode();

    this.casFeat_fsFeature = jcas.getRequiredFeatureDE(casType, "fsFeature",
        "org.apache.uima.simpleserver.test.TypeWithListsAndArrays", featOkTst);
    this.casFeatCode_fsFeature = null == this.casFeat_fsFeature ? JCas.INVALID_FEATURE_CODE
        : ((FeatureImpl) this.casFeat_fsFeature).getCode();

  }
}
