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
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.cas.TOP_Type;

/**
 * Updated by JCasGen Sun Jan 24 22:12:30 CET 2010
 * 
 * @generated
 */
public class MicroformatFS_Type extends TOP_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {
    return fsGenerator;
  }

  /** @generated */
  private final FSGenerator fsGenerator = new FSGenerator() {
    public FeatureStructure createFS(int addr, CASImpl cas) {
      if (MicroformatFS_Type.this.useExistingInstance) {
        // Return eq fs instance if already created
        FeatureStructure fs = MicroformatFS_Type.this.jcas.getJfsFromCaddr(addr);
        if (null == fs) {
          fs = new MicroformatFS(addr, MicroformatFS_Type.this);
          MicroformatFS_Type.this.jcas.putJfsFromCaddr(addr, fs);
          return fs;
        }
        return fs;
      } else
        return new MicroformatFS(addr, MicroformatFS_Type.this);
    }
  };

  /** @generated */
  public final static int typeIndexID = MicroformatFS.typeIndexID;

  /**
   * @generated
   * @modifiable
   */
  public final static boolean featOkTst = JCasRegistry
          .getFeatOkTst("org.apache.uima.alchemy.ts.microformats.MicroformatFS");

  /** @generated */
  final Feature casFeat_fieldName;

  /** @generated */
  final int casFeatCode_fieldName;

  /** @generated */
  public String getFieldName(int addr) {
    if (featOkTst && casFeat_fieldName == null)
      jcas.throwFeatMissing("fieldName", "org.apache.uima.alchemy.ts.microformats.MicroformatFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_fieldName);
  }

  /** @generated */
  public void setFieldName(int addr, String v) {
    if (featOkTst && casFeat_fieldName == null)
      jcas.throwFeatMissing("fieldName", "org.apache.uima.alchemy.ts.microformats.MicroformatFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_fieldName, v);
  }

  /** @generated */
  final Feature casFeat_fieldData;

  /** @generated */
  final int casFeatCode_fieldData;

  /** @generated */
  public String getFieldData(int addr) {
    if (featOkTst && casFeat_fieldData == null)
      jcas.throwFeatMissing("fieldData", "org.apache.uima.alchemy.ts.microformats.MicroformatFS");
    return ll_cas.ll_getStringValue(addr, casFeatCode_fieldData);
  }

  /** @generated */
  public void setFieldData(int addr, String v) {
    if (featOkTst && casFeat_fieldData == null)
      jcas.throwFeatMissing("fieldData", "org.apache.uima.alchemy.ts.microformats.MicroformatFS");
    ll_cas.ll_setStringValue(addr, casFeatCode_fieldData, v);
  }

  /**
   * initialize variables to correspond with Cas Type and Features
   * 
   * @generated
   */
  public MicroformatFS_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl) this.casType, getFSGenerator());

    casFeat_fieldName = jcas.getRequiredFeatureDE(casType, "fieldName", "uima.cas.String",
            featOkTst);
    casFeatCode_fieldName = (null == casFeat_fieldName) ? JCas.INVALID_FEATURE_CODE
            : ((FeatureImpl) casFeat_fieldName).getCode();

    casFeat_fieldData = jcas.getRequiredFeatureDE(casType, "fieldData", "uima.cas.String",
            featOkTst);
    casFeatCode_fieldData = (null == casFeat_fieldData) ? JCas.INVALID_FEATURE_CODE
            : ((FeatureImpl) casFeat_fieldData).getCode();

  }
}
