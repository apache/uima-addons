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
/* First created by JCasGen Mon Nov 09 23:02:37 CET 2009 */
package org.apache.uima.alchemy.ts.entity;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;

/** 
 * Updated by JCasGen Fri Mar 11 17:40:07 CET 2011
 * @generated */
public class RadioProgram_Type extends BaseEntity_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (RadioProgram_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = RadioProgram_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new RadioProgram(addr, RadioProgram_Type.this);
  			   RadioProgram_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new RadioProgram(addr, RadioProgram_Type.this);
  	  }
    };

  /** @generated */
  public final static int typeIndexID = RadioProgram.typeIndexID;

  /**
   * @generated
   * @modifiable
   */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.alchemy.ts.entity.RadioProgram");

  /**
   * initialize variables to correspond with Cas Type and Features
   * 
   * @generated
   */
  public RadioProgram_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}
