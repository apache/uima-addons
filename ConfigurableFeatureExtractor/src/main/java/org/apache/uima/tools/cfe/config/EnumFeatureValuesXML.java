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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enum Feature Values XML</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.uima.tools.cfe.config.EnumFeatureValuesXML#getValues <em>Values</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.EnumFeatureValuesXML#isCaseSensitive <em>Case Sensitive</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.uima.tools.cfe.config.ConfigPackage#getEnumFeatureValuesXML()
 * @model extendedMetaData="name='EnumFeatureValuesXML' kind='elementOnly'"
 * @generated
 */
public interface EnumFeatureValuesXML extends EObject
{
  /**
     * Returns the value of the '<em><b>Values</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Values</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Values</em>' attribute list.
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getEnumFeatureValuesXML_Values()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='values' namespace='##targetNamespace'"
     * @generated
     */
  EList<String> getValues();

  /**
     * Returns the value of the '<em><b>Case Sensitive</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Case Sensitive</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Case Sensitive</em>' attribute.
     * @see #isSetCaseSensitive()
     * @see #unsetCaseSensitive()
     * @see #setCaseSensitive(boolean)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getEnumFeatureValuesXML_CaseSensitive()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='caseSensitive'"
     * @generated
     */
  boolean isCaseSensitive();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.EnumFeatureValuesXML#isCaseSensitive <em>Case Sensitive</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Case Sensitive</em>' attribute.
     * @see #isSetCaseSensitive()
     * @see #unsetCaseSensitive()
     * @see #isCaseSensitive()
     * @generated
     */
  void setCaseSensitive(boolean value);

  /**
     * Unsets the value of the '{@link org.apache.uima.tools.cfe.config.EnumFeatureValuesXML#isCaseSensitive <em>Case Sensitive</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isSetCaseSensitive()
     * @see #isCaseSensitive()
     * @see #setCaseSensitive(boolean)
     * @generated
     */
  void unsetCaseSensitive();

  /**
     * Returns whether the value of the '{@link org.apache.uima.tools.cfe.config.EnumFeatureValuesXML#isCaseSensitive <em>Case Sensitive</em>}' attribute is set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return whether the value of the '<em>Case Sensitive</em>' attribute is set.
     * @see #unsetCaseSensitive()
     * @see #isCaseSensitive()
     * @see #setCaseSensitive(boolean)
     * @generated
     */
  boolean isSetCaseSensitive();

} // EnumFeatureValuesXML