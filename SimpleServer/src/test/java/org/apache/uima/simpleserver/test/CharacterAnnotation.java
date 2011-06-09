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

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;

/**
 * Updated by JCasGen Thu Nov 22 13:50:18 CET 2007 XML source:
 * C:/code/ApacheUIMA/SimpleServer/src/test/resources/desc/simpleServerTestDescriptor.xml
 * 
 * @generated
 */
public class CharacterAnnotation extends Annotation {
  /**
   * @generated
   * @ordered
   */
  @SuppressWarnings("hiding")
  public final static int typeIndexID = JCasRegistry.register(CharacterAnnotation.class);

  /**
   * @generated
   * @ordered
   */
  @SuppressWarnings("hiding")
  public final static int type = typeIndexID;

  /** @generated */
  @Override
  public int getTypeIndexID() {
    return typeIndexID;
  }

  /**
   * Never called. Disable default constructor
   * 
   * @generated
   */
  protected CharacterAnnotation() {
    // unused
  }

  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
  public CharacterAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }

  /** @generated */
  public CharacterAnnotation(JCas jcas) {
    super(jcas);
    readObject();
  }

  /** @generated */
  public CharacterAnnotation(JCas jcas, int begin, int end) {
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
    // doesn't need to do anything
  }

  // *--------------*
  // * Feature: booleanFeature

  /**
   * getter for booleanFeature - gets
   * 
   * @generated
   */
  public boolean getBooleanFeature() {
    if (CharacterAnnotation_Type.featOkTst
        && ((CharacterAnnotation_Type) this.jcasType).casFeat_booleanFeature == null) {
      this.jcasType.jcas.throwFeatMissing("booleanFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    return this.jcasType.ll_cas.ll_getBooleanValue(this.addr,
        ((CharacterAnnotation_Type) this.jcasType).casFeatCode_booleanFeature);
  }

  /**
   * setter for booleanFeature - sets
   * 
   * @generated
   */
  public void setBooleanFeature(boolean v) {
    if (CharacterAnnotation_Type.featOkTst
        && ((CharacterAnnotation_Type) this.jcasType).casFeat_booleanFeature == null) {
      this.jcasType.jcas.throwFeatMissing("booleanFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    this.jcasType.ll_cas.ll_setBooleanValue(this.addr,
        ((CharacterAnnotation_Type) this.jcasType).casFeatCode_booleanFeature, v);
  }

  // *--------------*
  // * Feature: byteFeature

  /**
   * getter for byteFeature - gets
   * 
   * @generated
   */
  public byte getByteFeature() {
    if (CharacterAnnotation_Type.featOkTst
        && ((CharacterAnnotation_Type) this.jcasType).casFeat_byteFeature == null) {
      this.jcasType.jcas.throwFeatMissing("byteFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    return this.jcasType.ll_cas.ll_getByteValue(this.addr,
        ((CharacterAnnotation_Type) this.jcasType).casFeatCode_byteFeature);
  }

  /**
   * setter for byteFeature - sets
   * 
   * @generated
   */
  public void setByteFeature(byte v) {
    if (CharacterAnnotation_Type.featOkTst
        && ((CharacterAnnotation_Type) this.jcasType).casFeat_byteFeature == null) {
      this.jcasType.jcas.throwFeatMissing("byteFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    this.jcasType.ll_cas.ll_setByteValue(this.addr,
        ((CharacterAnnotation_Type) this.jcasType).casFeatCode_byteFeature, v);
  }

  // *--------------*
  // * Feature: shortFeature

  /**
   * getter for shortFeature - gets
   * 
   * @generated
   */
  public short getShortFeature() {
    if (CharacterAnnotation_Type.featOkTst
        && ((CharacterAnnotation_Type) this.jcasType).casFeat_shortFeature == null) {
      this.jcasType.jcas.throwFeatMissing("shortFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    return this.jcasType.ll_cas.ll_getShortValue(this.addr,
        ((CharacterAnnotation_Type) this.jcasType).casFeatCode_shortFeature);
  }

  /**
   * setter for shortFeature - sets
   * 
   * @generated
   */
  public void setShortFeature(short v) {
    if (CharacterAnnotation_Type.featOkTst
        && ((CharacterAnnotation_Type) this.jcasType).casFeat_shortFeature == null) {
      this.jcasType.jcas.throwFeatMissing("shortFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    this.jcasType.ll_cas.ll_setShortValue(this.addr,
        ((CharacterAnnotation_Type) this.jcasType).casFeatCode_shortFeature, v);
  }

  // *--------------*
  // * Feature: integerFeature

  /**
   * getter for integerFeature - gets
   * 
   * @generated
   */
  public int getIntegerFeature() {
    if (CharacterAnnotation_Type.featOkTst
        && ((CharacterAnnotation_Type) this.jcasType).casFeat_integerFeature == null) {
      this.jcasType.jcas.throwFeatMissing("integerFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    return this.jcasType.ll_cas.ll_getIntValue(this.addr,
        ((CharacterAnnotation_Type) this.jcasType).casFeatCode_integerFeature);
  }

  /**
   * setter for integerFeature - sets
   * 
   * @generated
   */
  public void setIntegerFeature(int v) {
    if (CharacterAnnotation_Type.featOkTst
        && ((CharacterAnnotation_Type) this.jcasType).casFeat_integerFeature == null) {
      this.jcasType.jcas.throwFeatMissing("integerFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    this.jcasType.ll_cas.ll_setIntValue(this.addr,
        ((CharacterAnnotation_Type) this.jcasType).casFeatCode_integerFeature, v);
  }

  // *--------------*
  // * Feature: longFeature

  /**
   * getter for longFeature - gets
   * 
   * @generated
   */
  public long getLongFeature() {
    if (CharacterAnnotation_Type.featOkTst
        && ((CharacterAnnotation_Type) this.jcasType).casFeat_longFeature == null) {
      this.jcasType.jcas.throwFeatMissing("longFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    return this.jcasType.ll_cas.ll_getLongValue(this.addr,
        ((CharacterAnnotation_Type) this.jcasType).casFeatCode_longFeature);
  }

  /**
   * setter for longFeature - sets
   * 
   * @generated
   */
  public void setLongFeature(long v) {
    if (CharacterAnnotation_Type.featOkTst
        && ((CharacterAnnotation_Type) this.jcasType).casFeat_longFeature == null) {
      this.jcasType.jcas.throwFeatMissing("longFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    this.jcasType.ll_cas.ll_setLongValue(this.addr,
        ((CharacterAnnotation_Type) this.jcasType).casFeatCode_longFeature, v);
  }

  // *--------------*
  // * Feature: floatFeature

  /**
   * getter for floatFeature - gets
   * 
   * @generated
   */
  public float getFloatFeature() {
    if (CharacterAnnotation_Type.featOkTst
        && ((CharacterAnnotation_Type) this.jcasType).casFeat_floatFeature == null) {
      this.jcasType.jcas.throwFeatMissing("floatFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    return this.jcasType.ll_cas.ll_getFloatValue(this.addr,
        ((CharacterAnnotation_Type) this.jcasType).casFeatCode_floatFeature);
  }

  /**
   * setter for floatFeature - sets
   * 
   * @generated
   */
  public void setFloatFeature(float v) {
    if (CharacterAnnotation_Type.featOkTst
        && ((CharacterAnnotation_Type) this.jcasType).casFeat_floatFeature == null) {
      this.jcasType.jcas.throwFeatMissing("floatFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    this.jcasType.ll_cas.ll_setFloatValue(this.addr,
        ((CharacterAnnotation_Type) this.jcasType).casFeatCode_floatFeature, v);
  }

  // *--------------*
  // * Feature: doubleFeature

  /**
   * getter for doubleFeature - gets
   * 
   * @generated
   */
  public double getDoubleFeature() {
    if (CharacterAnnotation_Type.featOkTst
        && ((CharacterAnnotation_Type) this.jcasType).casFeat_doubleFeature == null) {
      this.jcasType.jcas.throwFeatMissing("doubleFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    return this.jcasType.ll_cas.ll_getDoubleValue(this.addr,
        ((CharacterAnnotation_Type) this.jcasType).casFeatCode_doubleFeature);
  }

  /**
   * setter for doubleFeature - sets
   * 
   * @generated
   */
  public void setDoubleFeature(double v) {
    if (CharacterAnnotation_Type.featOkTst
        && ((CharacterAnnotation_Type) this.jcasType).casFeat_doubleFeature == null) {
      this.jcasType.jcas.throwFeatMissing("doubleFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    this.jcasType.ll_cas.ll_setDoubleValue(this.addr,
        ((CharacterAnnotation_Type) this.jcasType).casFeatCode_doubleFeature, v);
  }

  // *--------------*
  // * Feature: stringFeature

  /**
   * getter for stringFeature - gets
   * 
   * @generated
   */
  public String getStringFeature() {
    if (CharacterAnnotation_Type.featOkTst
        && ((CharacterAnnotation_Type) this.jcasType).casFeat_stringFeature == null) {
      this.jcasType.jcas.throwFeatMissing("stringFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    return this.jcasType.ll_cas.ll_getStringValue(this.addr,
        ((CharacterAnnotation_Type) this.jcasType).casFeatCode_stringFeature);
  }

  /**
   * setter for stringFeature - sets
   * 
   * @generated
   */
  public void setStringFeature(String v) {
    if (CharacterAnnotation_Type.featOkTst
        && ((CharacterAnnotation_Type) this.jcasType).casFeat_stringFeature == null) {
      this.jcasType.jcas.throwFeatMissing("stringFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    this.jcasType.ll_cas.ll_setStringValue(this.addr,
        ((CharacterAnnotation_Type) this.jcasType).casFeatCode_stringFeature, v);
  }

  // *--------------*
  // * Feature: fsFeature

  /**
   * getter for fsFeature - gets
   * 
   * @generated
   */
  public TypeWithListsAndArrays getFsFeature() {
    if (CharacterAnnotation_Type.featOkTst
        && ((CharacterAnnotation_Type) this.jcasType).casFeat_fsFeature == null) {
      this.jcasType.jcas.throwFeatMissing("fsFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    return (TypeWithListsAndArrays) this.jcasType.ll_cas
        .ll_getFSForRef(this.jcasType.ll_cas.ll_getRefValue(this.addr,
            ((CharacterAnnotation_Type) this.jcasType).casFeatCode_fsFeature));
  }

  /**
   * setter for fsFeature - sets
   * 
   * @generated
   */
  public void setFsFeature(TypeWithListsAndArrays v) {
    if (CharacterAnnotation_Type.featOkTst
        && ((CharacterAnnotation_Type) this.jcasType).casFeat_fsFeature == null) {
      this.jcasType.jcas.throwFeatMissing("fsFeature",
          "org.apache.uima.simpleserver.test.CharacterAnnotation");
    }
    this.jcasType.ll_cas.ll_setRefValue(this.addr,
        ((CharacterAnnotation_Type) this.jcasType).casFeatCode_fsFeature, this.jcasType.ll_cas
            .ll_getFSRef(v));
  }
}
