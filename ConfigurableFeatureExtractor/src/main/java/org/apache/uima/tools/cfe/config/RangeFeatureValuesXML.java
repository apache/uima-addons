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
 * A representation of the model object '<em><b>Range Feature Values XML</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.uima.tools.cfe.config.RangeFeatureValuesXML#getLowerBoundary <em>Lower Boundary</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.RangeFeatureValuesXML#isLowerBoundaryInclusive <em>Lower Boundary Inclusive</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.RangeFeatureValuesXML#getUpperBoundary <em>Upper Boundary</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.RangeFeatureValuesXML#isUpperBoundaryInclusive <em>Upper Boundary Inclusive</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.uima.tools.cfe.config.ConfigPackage#getRangeFeatureValuesXML()
 * @model extendedMetaData="name='RangeFeatureValuesXML' kind='empty'"
 * @generated
 */
public interface RangeFeatureValuesXML extends EObject
{
  /**
     * Returns the value of the '<em><b>Lower Boundary</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Lower Boundary</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Lower Boundary</em>' attribute.
     * @see #setLowerBoundary(Object)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getRangeFeatureValuesXML_LowerBoundary()
     * @model dataType="org.eclipse.emf.ecore.xml.type.AnySimpleType"
     *        extendedMetaData="kind='attribute' name='lowerBoundary'"
     * @generated
     */
  Object getLowerBoundary();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.RangeFeatureValuesXML#getLowerBoundary <em>Lower Boundary</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Lower Boundary</em>' attribute.
     * @see #getLowerBoundary()
     * @generated
     */
  void setLowerBoundary(Object value);

  /**
     * Returns the value of the '<em><b>Lower Boundary Inclusive</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Lower Boundary Inclusive</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Lower Boundary Inclusive</em>' attribute.
     * @see #isSetLowerBoundaryInclusive()
     * @see #unsetLowerBoundaryInclusive()
     * @see #setLowerBoundaryInclusive(boolean)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getRangeFeatureValuesXML_LowerBoundaryInclusive()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='lowerBoundaryInclusive'"
     * @generated
     */
  boolean isLowerBoundaryInclusive();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.RangeFeatureValuesXML#isLowerBoundaryInclusive <em>Lower Boundary Inclusive</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Lower Boundary Inclusive</em>' attribute.
     * @see #isSetLowerBoundaryInclusive()
     * @see #unsetLowerBoundaryInclusive()
     * @see #isLowerBoundaryInclusive()
     * @generated
     */
  void setLowerBoundaryInclusive(boolean value);

  /**
     * Unsets the value of the '{@link org.apache.uima.tools.cfe.config.RangeFeatureValuesXML#isLowerBoundaryInclusive <em>Lower Boundary Inclusive</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isSetLowerBoundaryInclusive()
     * @see #isLowerBoundaryInclusive()
     * @see #setLowerBoundaryInclusive(boolean)
     * @generated
     */
  void unsetLowerBoundaryInclusive();

  /**
     * Returns whether the value of the '{@link org.apache.uima.tools.cfe.config.RangeFeatureValuesXML#isLowerBoundaryInclusive <em>Lower Boundary Inclusive</em>}' attribute is set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return whether the value of the '<em>Lower Boundary Inclusive</em>' attribute is set.
     * @see #unsetLowerBoundaryInclusive()
     * @see #isLowerBoundaryInclusive()
     * @see #setLowerBoundaryInclusive(boolean)
     * @generated
     */
  boolean isSetLowerBoundaryInclusive();

  /**
     * Returns the value of the '<em><b>Upper Boundary</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Upper Boundary</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Upper Boundary</em>' attribute.
     * @see #setUpperBoundary(Object)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getRangeFeatureValuesXML_UpperBoundary()
     * @model dataType="org.eclipse.emf.ecore.xml.type.AnySimpleType"
     *        extendedMetaData="kind='attribute' name='upperBoundary'"
     * @generated
     */
  Object getUpperBoundary();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.RangeFeatureValuesXML#getUpperBoundary <em>Upper Boundary</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Upper Boundary</em>' attribute.
     * @see #getUpperBoundary()
     * @generated
     */
  void setUpperBoundary(Object value);

  /**
     * Returns the value of the '<em><b>Upper Boundary Inclusive</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Upper Boundary Inclusive</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Upper Boundary Inclusive</em>' attribute.
     * @see #isSetUpperBoundaryInclusive()
     * @see #unsetUpperBoundaryInclusive()
     * @see #setUpperBoundaryInclusive(boolean)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getRangeFeatureValuesXML_UpperBoundaryInclusive()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='upperBoundaryInclusive'"
     * @generated
     */
  boolean isUpperBoundaryInclusive();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.RangeFeatureValuesXML#isUpperBoundaryInclusive <em>Upper Boundary Inclusive</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Upper Boundary Inclusive</em>' attribute.
     * @see #isSetUpperBoundaryInclusive()
     * @see #unsetUpperBoundaryInclusive()
     * @see #isUpperBoundaryInclusive()
     * @generated
     */
  void setUpperBoundaryInclusive(boolean value);

  /**
     * Unsets the value of the '{@link org.apache.uima.tools.cfe.config.RangeFeatureValuesXML#isUpperBoundaryInclusive <em>Upper Boundary Inclusive</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isSetUpperBoundaryInclusive()
     * @see #isUpperBoundaryInclusive()
     * @see #setUpperBoundaryInclusive(boolean)
     * @generated
     */
  void unsetUpperBoundaryInclusive();

  /**
     * Returns whether the value of the '{@link org.apache.uima.tools.cfe.config.RangeFeatureValuesXML#isUpperBoundaryInclusive <em>Upper Boundary Inclusive</em>}' attribute is set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return whether the value of the '<em>Upper Boundary Inclusive</em>' attribute is set.
     * @see #unsetUpperBoundaryInclusive()
     * @see #isUpperBoundaryInclusive()
     * @see #setUpperBoundaryInclusive(boolean)
     * @generated
     */
  boolean isSetUpperBoundaryInclusive();

} // RangeFeatureValuesXML