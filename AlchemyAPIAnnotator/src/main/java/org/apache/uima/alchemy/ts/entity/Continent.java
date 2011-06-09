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

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

/** 
 * Updated by JCasGen Fri Mar 11 17:40:07 CET 2011
 * XML source: /Users/tommasoteofili/Documents/workspaces/uima_workspace/uima/sandbox/AlchemyAPIAnnotator/desc/TextRankedEntityExtractionAEDescriptor.xml
 * @generated */
public class Continent extends BaseEntity {
  /**
   * @generated
   * @ordered
   */
  public final static int typeIndexID = JCasRegistry.register(Continent.class);

  /**
   * @generated
   * @ordered
   */
  public final static int type = typeIndexID;

  /** @generated */
  public int getTypeIndexID() {return typeIndexID;}
 
  /**
   * Never called. Disable default constructor
   * 
   * @generated
   */
  protected Continent() {}
    
  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
  public Continent(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Continent(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc --> Write your own initialization here <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {
  }

}
