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

/* First created by JCasGen Sun Jan 24 22:12:30 CET 2010 */
package org.apache.uima.alchemy.ts.microformats;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;

/**
 * Updated by JCasGen Sun Jan 24 22:12:30 CET 2010 XML source:
 * 
 * @generated
 */
public class MicroformatFS extends TOP {
  /**
   * @generated
   * @ordered
   */
  public final static int typeIndexID = JCasRegistry.register(MicroformatFS.class);

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
  protected MicroformatFS() {
  }

  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
  public MicroformatFS(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }

  /** @generated */
  public MicroformatFS(JCas jcas) {
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
  // * Feature: fieldName

  /**
   * getter for fieldName - gets
   * 
   * @generated
   */
  public String getFieldName() {
    if (MicroformatFS_Type.featOkTst && ((MicroformatFS_Type) jcasType).casFeat_fieldName == null)
      jcasType.jcas.throwFeatMissing("fieldName",
              "org.apache.uima.alchemy.ts.microformats.MicroformatFS");
    return jcasType.ll_cas.ll_getStringValue(addr,
            ((MicroformatFS_Type) jcasType).casFeatCode_fieldName);
  }

  /**
   * setter for fieldName - sets
   * 
   * @generated
   */
  public void setFieldName(String v) {
    if (MicroformatFS_Type.featOkTst && ((MicroformatFS_Type) jcasType).casFeat_fieldName == null)
      jcasType.jcas.throwFeatMissing("fieldName",
              "org.apache.uima.alchemy.ts.microformats.MicroformatFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((MicroformatFS_Type) jcasType).casFeatCode_fieldName,
            v);
  }

  // *--------------*
  // * Feature: fieldData

  /**
   * getter for fieldData - gets
   * 
   * @generated
   */
  public String getFieldData() {
    if (MicroformatFS_Type.featOkTst && ((MicroformatFS_Type) jcasType).casFeat_fieldData == null)
      jcasType.jcas.throwFeatMissing("fieldData",
              "org.apache.uima.alchemy.ts.microformats.MicroformatFS");
    return jcasType.ll_cas.ll_getStringValue(addr,
            ((MicroformatFS_Type) jcasType).casFeatCode_fieldData);
  }

  /**
   * setter for fieldData - sets
   * 
   * @generated
   */
  public void setFieldData(String v) {
    if (MicroformatFS_Type.featOkTst && ((MicroformatFS_Type) jcasType).casFeat_fieldData == null)
      jcasType.jcas.throwFeatMissing("fieldData",
              "org.apache.uima.alchemy.ts.microformats.MicroformatFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((MicroformatFS_Type) jcasType).casFeatCode_fieldData,
            v);
  }
}
