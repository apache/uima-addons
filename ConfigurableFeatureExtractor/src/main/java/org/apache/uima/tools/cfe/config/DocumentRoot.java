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

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.uima.tools.cfe.config;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.uima.tools.cfe.config.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.DocumentRoot#getCFEConfig <em>CFE Config</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.uima.tools.cfe.config.ConfigPackage#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot extends EObject
{
  /**
     * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
     * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Mixed</em>' attribute list.
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getDocumentRoot_Mixed()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     *        extendedMetaData="kind='elementWildcard' name=':mixed'"
     * @generated
     */
  FeatureMap getMixed();

  /**
     * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.String},
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>XMLNS Prefix Map</em>' map.
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getDocumentRoot_XMLNSPrefixMap()
     * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
     *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
     * @generated
     */
  EMap<String, String> getXMLNSPrefixMap();

  /**
     * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.String},
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>XSI Schema Location</em>' map.
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getDocumentRoot_XSISchemaLocation()
     * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
     *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
     * @generated
     */
  EMap<String, String> getXSISchemaLocation();

  /**
     * Returns the value of the '<em><b>CFE Config</b></em>' containment reference.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>CFE Config</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>CFE Config</em>' containment reference.
     * @see #setCFEConfig(CFEDescriptorXML)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getDocumentRoot_CFEConfig()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='CFEConfig' namespace='##targetNamespace'"
     * @generated
     */
  CFEDescriptorXML getCFEConfig();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.DocumentRoot#getCFEConfig <em>CFE Config</em>}' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>CFE Config</em>' containment reference.
     * @see #getCFEConfig()
     * @generated
     */
  void setCFEConfig(CFEDescriptorXML value);

} // DocumentRoot