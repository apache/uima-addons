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
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.cas.TOP_Type;

/**
 * Updated by JCasGen Thu Nov 22 13:50:18 CET 2007 XML source:
 * C:/code/ApacheUIMA/SimpleServer/src/test/resources/desc/simpleServerTestDescriptor.xml
 * 
 * @generated
 */
public class TypeWithListsAndArrays extends TOP {
  /**
   * @generated
   * @ordered
   */
  @SuppressWarnings("hiding")
  public final static int typeIndexID = JCasRegistry.register(TypeWithListsAndArrays.class);

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
  protected TypeWithListsAndArrays() {
    // not used
  }
  

  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
  public TypeWithListsAndArrays(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }

  /** @generated */
  public TypeWithListsAndArrays(JCas jcas) {
    super(jcas);
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
  // * Feature: stringList

  /**
   * getter for stringList - gets
   * 
   * @generated
   */
  public String getStringList() {
    if (TypeWithListsAndArrays_Type.featOkTst
        && ((TypeWithListsAndArrays_Type) this.jcasType).casFeat_stringList == null) {
      this.jcasType.jcas.throwFeatMissing("stringList",
          "org.apache.uima.simpleserver.test.TypeWithListsAndArrays");
    }
    return this.jcasType.ll_cas.ll_getStringValue(this.addr,
        ((TypeWithListsAndArrays_Type) this.jcasType).casFeatCode_stringList);
  }

  /**
   * setter for stringList - sets
   * 
   * @generated
   */
  public void setStringList(String v) {
    if (TypeWithListsAndArrays_Type.featOkTst
        && ((TypeWithListsAndArrays_Type) this.jcasType).casFeat_stringList == null) {
      this.jcasType.jcas.throwFeatMissing("stringList",
          "org.apache.uima.simpleserver.test.TypeWithListsAndArrays");
    }
    this.jcasType.ll_cas.ll_setStringValue(this.addr,
        ((TypeWithListsAndArrays_Type) this.jcasType).casFeatCode_stringList, v);
  }

  // *--------------*
  // * Feature: annotationArray

  /**
   * getter for annotationArray - gets
   * 
   * @generated
   */
  public FSArray getAnnotationArray() {
    if (TypeWithListsAndArrays_Type.featOkTst
        && ((TypeWithListsAndArrays_Type) this.jcasType).casFeat_annotationArray == null) {
      this.jcasType.jcas.throwFeatMissing("annotationArray",
          "org.apache.uima.simpleserver.test.TypeWithListsAndArrays");
    }
    return (FSArray) this.jcasType.ll_cas.ll_getFSForRef(this.jcasType.ll_cas.ll_getRefValue(
        this.addr, ((TypeWithListsAndArrays_Type) this.jcasType).casFeatCode_annotationArray));
  }

  /**
   * setter for annotationArray - sets
   * 
   * @generated
   */
  public void setAnnotationArray(FSArray v) {
    if (TypeWithListsAndArrays_Type.featOkTst
        && ((TypeWithListsAndArrays_Type) this.jcasType).casFeat_annotationArray == null) {
      this.jcasType.jcas.throwFeatMissing("annotationArray",
          "org.apache.uima.simpleserver.test.TypeWithListsAndArrays");
    }
    this.jcasType.ll_cas.ll_setRefValue(this.addr,
        ((TypeWithListsAndArrays_Type) this.jcasType).casFeatCode_annotationArray,
        this.jcasType.ll_cas.ll_getFSRef(v));
  }

  /**
   * indexed getter for annotationArray - gets an indexed value -
   * 
   * @generated
   */
  public TOP getAnnotationArray(int i) {
    if (TypeWithListsAndArrays_Type.featOkTst
        && ((TypeWithListsAndArrays_Type) this.jcasType).casFeat_annotationArray == null) {
      this.jcasType.jcas.throwFeatMissing("annotationArray",
          "org.apache.uima.simpleserver.test.TypeWithListsAndArrays");
    }
    this.jcasType.jcas.checkArrayBounds(this.jcasType.ll_cas.ll_getRefValue(this.addr,
        ((TypeWithListsAndArrays_Type) this.jcasType).casFeatCode_annotationArray), i);
    return (TOP) this.jcasType.ll_cas.ll_getFSForRef(this.jcasType.ll_cas.ll_getRefArrayValue(
        this.jcasType.ll_cas.ll_getRefValue(this.addr,
            ((TypeWithListsAndArrays_Type) this.jcasType).casFeatCode_annotationArray), i));
  }

  /**
   * indexed setter for annotationArray - sets an indexed value -
   * 
   * @generated
   */
  public void setAnnotationArray(int i, TOP v) {
    if (TypeWithListsAndArrays_Type.featOkTst
        && ((TypeWithListsAndArrays_Type) this.jcasType).casFeat_annotationArray == null) {
      this.jcasType.jcas.throwFeatMissing("annotationArray",
          "org.apache.uima.simpleserver.test.TypeWithListsAndArrays");
    }
    this.jcasType.jcas.checkArrayBounds(this.jcasType.ll_cas.ll_getRefValue(this.addr,
        ((TypeWithListsAndArrays_Type) this.jcasType).casFeatCode_annotationArray), i);
    this.jcasType.ll_cas.ll_setRefArrayValue(this.jcasType.ll_cas.ll_getRefValue(this.addr,
        ((TypeWithListsAndArrays_Type) this.jcasType).casFeatCode_annotationArray), i,
        this.jcasType.ll_cas.ll_getFSRef(v));
  }
}
