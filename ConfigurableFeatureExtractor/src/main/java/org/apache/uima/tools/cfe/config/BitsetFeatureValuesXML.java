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

import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bitset Feature Values XML</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.uima.tools.cfe.config.BitsetFeatureValuesXML#getBitmask <em>Bitmask</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.BitsetFeatureValuesXML#isExactMatch <em>Exact Match</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.uima.tools.cfe.config.ConfigPackage#getBitsetFeatureValuesXML()
 * @model extendedMetaData="name='BitsetFeatureValuesXML' kind='empty'"
 * @generated
 */
public interface BitsetFeatureValuesXML extends EObject
{
  /**
     * Returns the value of the '<em><b>Bitmask</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bitmask</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Bitmask</em>' attribute.
     * @see #setBitmask(String)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getBitsetFeatureValuesXML_Bitmask()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='bitmask'"
     * @generated
     */
  String getBitmask();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.BitsetFeatureValuesXML#getBitmask <em>Bitmask</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bitmask</em>' attribute.
     * @see #getBitmask()
     * @generated
     */
  void setBitmask(String value);

  /**
     * Returns the value of the '<em><b>Exact Match</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Exact Match</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Exact Match</em>' attribute.
     * @see #isSetExactMatch()
     * @see #unsetExactMatch()
     * @see #setExactMatch(boolean)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getBitsetFeatureValuesXML_ExactMatch()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='exact_match'"
     * @generated
     */
  boolean isExactMatch();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.BitsetFeatureValuesXML#isExactMatch <em>Exact Match</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Exact Match</em>' attribute.
     * @see #isSetExactMatch()
     * @see #unsetExactMatch()
     * @see #isExactMatch()
     * @generated
     */
  void setExactMatch(boolean value);

  /**
     * Unsets the value of the '{@link org.apache.uima.tools.cfe.config.BitsetFeatureValuesXML#isExactMatch <em>Exact Match</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isSetExactMatch()
     * @see #isExactMatch()
     * @see #setExactMatch(boolean)
     * @generated
     */
  void unsetExactMatch();

  /**
     * Returns whether the value of the '{@link org.apache.uima.tools.cfe.config.BitsetFeatureValuesXML#isExactMatch <em>Exact Match</em>}' attribute is set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return whether the value of the '<em>Exact Match</em>' attribute is set.
     * @see #unsetExactMatch()
     * @see #isExactMatch()
     * @see #setExactMatch(boolean)
     * @generated
     */
  boolean isSetExactMatch();

} // BitsetFeatureValuesXML