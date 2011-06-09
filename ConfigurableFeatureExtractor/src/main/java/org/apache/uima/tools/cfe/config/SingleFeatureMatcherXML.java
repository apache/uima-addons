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
 * A representation of the model object '<em><b>Single Feature Matcher XML</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getRangeFeatureValues <em>Range Feature Values</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getEnumFeatureValues <em>Enum Feature Values</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getBitsetFeatureValues <em>Bitset Feature Values</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getObjectPathFeatureValues <em>Object Path Feature Values</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getPatternFeatureValues <em>Pattern Feature Values</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#isExclude <em>Exclude</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getFeaturePath <em>Feature Path</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getFeatureTypeName <em>Feature Type Name</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#isQuiet <em>Quiet</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.uima.tools.cfe.config.ConfigPackage#getSingleFeatureMatcherXML()
 * @model extendedMetaData="name='SingleFeatureMatcherXML' kind='elementOnly'"
 * @generated
 */
public interface SingleFeatureMatcherXML extends EObject
{
  /**
     * Returns the value of the '<em><b>Range Feature Values</b></em>' containment reference.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Range Feature Values</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Range Feature Values</em>' containment reference.
     * @see #setRangeFeatureValues(RangeFeatureValuesXML)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getSingleFeatureMatcherXML_RangeFeatureValues()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='rangeFeatureValues' namespace='##targetNamespace'"
     * @generated
     */
  RangeFeatureValuesXML getRangeFeatureValues();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getRangeFeatureValues <em>Range Feature Values</em>}' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Range Feature Values</em>' containment reference.
     * @see #getRangeFeatureValues()
     * @generated
     */
  void setRangeFeatureValues(RangeFeatureValuesXML value);

  /**
     * Returns the value of the '<em><b>Enum Feature Values</b></em>' containment reference.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Enum Feature Values</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Enum Feature Values</em>' containment reference.
     * @see #setEnumFeatureValues(EnumFeatureValuesXML)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getSingleFeatureMatcherXML_EnumFeatureValues()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='enumFeatureValues' namespace='##targetNamespace'"
     * @generated
     */
  EnumFeatureValuesXML getEnumFeatureValues();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getEnumFeatureValues <em>Enum Feature Values</em>}' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Enum Feature Values</em>' containment reference.
     * @see #getEnumFeatureValues()
     * @generated
     */
  void setEnumFeatureValues(EnumFeatureValuesXML value);

  /**
     * Returns the value of the '<em><b>Bitset Feature Values</b></em>' containment reference.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bitset Feature Values</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Bitset Feature Values</em>' containment reference.
     * @see #setBitsetFeatureValues(BitsetFeatureValuesXML)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getSingleFeatureMatcherXML_BitsetFeatureValues()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='bitsetFeatureValues' namespace='##targetNamespace'"
     * @generated
     */
  BitsetFeatureValuesXML getBitsetFeatureValues();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getBitsetFeatureValues <em>Bitset Feature Values</em>}' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bitset Feature Values</em>' containment reference.
     * @see #getBitsetFeatureValues()
     * @generated
     */
  void setBitsetFeatureValues(BitsetFeatureValuesXML value);

  /**
     * Returns the value of the '<em><b>Object Path Feature Values</b></em>' containment reference.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Object Path Feature Values</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Object Path Feature Values</em>' containment reference.
     * @see #setObjectPathFeatureValues(ObjectPathFeatureValuesXML)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getSingleFeatureMatcherXML_ObjectPathFeatureValues()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='objectPathFeatureValues' namespace='##targetNamespace'"
     * @generated
     */
  ObjectPathFeatureValuesXML getObjectPathFeatureValues();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getObjectPathFeatureValues <em>Object Path Feature Values</em>}' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Object Path Feature Values</em>' containment reference.
     * @see #getObjectPathFeatureValues()
     * @generated
     */
  void setObjectPathFeatureValues(ObjectPathFeatureValuesXML value);

  /**
     * Returns the value of the '<em><b>Pattern Feature Values</b></em>' containment reference.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pattern Feature Values</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Pattern Feature Values</em>' containment reference.
     * @see #setPatternFeatureValues(PatternFeatureValuesXML)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getSingleFeatureMatcherXML_PatternFeatureValues()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='patternFeatureValues' namespace='##targetNamespace'"
     * @generated
     */
  PatternFeatureValuesXML getPatternFeatureValues();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getPatternFeatureValues <em>Pattern Feature Values</em>}' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Pattern Feature Values</em>' containment reference.
     * @see #getPatternFeatureValues()
     * @generated
     */
  void setPatternFeatureValues(PatternFeatureValuesXML value);

  /**
     * Returns the value of the '<em><b>Exclude</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Exclude</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Exclude</em>' attribute.
     * @see #isSetExclude()
     * @see #unsetExclude()
     * @see #setExclude(boolean)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getSingleFeatureMatcherXML_Exclude()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='exclude'"
     * @generated
     */
  boolean isExclude();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#isExclude <em>Exclude</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Exclude</em>' attribute.
     * @see #isSetExclude()
     * @see #unsetExclude()
     * @see #isExclude()
     * @generated
     */
  void setExclude(boolean value);

  /**
     * Unsets the value of the '{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#isExclude <em>Exclude</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isSetExclude()
     * @see #isExclude()
     * @see #setExclude(boolean)
     * @generated
     */
  void unsetExclude();

  /**
     * Returns whether the value of the '{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#isExclude <em>Exclude</em>}' attribute is set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return whether the value of the '<em>Exclude</em>' attribute is set.
     * @see #unsetExclude()
     * @see #isExclude()
     * @see #setExclude(boolean)
     * @generated
     */
  boolean isSetExclude();

  /**
     * Returns the value of the '<em><b>Feature Path</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Feature Path</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Feature Path</em>' attribute.
     * @see #setFeaturePath(String)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getSingleFeatureMatcherXML_FeaturePath()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='featurePath'"
     * @generated
     */
  String getFeaturePath();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getFeaturePath <em>Feature Path</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Feature Path</em>' attribute.
     * @see #getFeaturePath()
     * @generated
     */
  void setFeaturePath(String value);

  /**
     * Returns the value of the '<em><b>Feature Type Name</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Feature Type Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Feature Type Name</em>' attribute.
     * @see #setFeatureTypeName(String)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getSingleFeatureMatcherXML_FeatureTypeName()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='featureTypeName'"
     * @generated
     */
  String getFeatureTypeName();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getFeatureTypeName <em>Feature Type Name</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Feature Type Name</em>' attribute.
     * @see #getFeatureTypeName()
     * @generated
     */
  void setFeatureTypeName(String value);

  /**
     * Returns the value of the '<em><b>Quiet</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Quiet</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Quiet</em>' attribute.
     * @see #isSetQuiet()
     * @see #unsetQuiet()
     * @see #setQuiet(boolean)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getSingleFeatureMatcherXML_Quiet()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='quiet'"
     * @generated
     */
  boolean isQuiet();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#isQuiet <em>Quiet</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Quiet</em>' attribute.
     * @see #isSetQuiet()
     * @see #unsetQuiet()
     * @see #isQuiet()
     * @generated
     */
  void setQuiet(boolean value);

  /**
     * Unsets the value of the '{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#isQuiet <em>Quiet</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isSetQuiet()
     * @see #isQuiet()
     * @see #setQuiet(boolean)
     * @generated
     */
  void unsetQuiet();

  /**
     * Returns whether the value of the '{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#isQuiet <em>Quiet</em>}' attribute is set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return whether the value of the '<em>Quiet</em>' attribute is set.
     * @see #unsetQuiet()
     * @see #isQuiet()
     * @see #setQuiet(boolean)
     * @generated
     */
  boolean isSetQuiet();

} // SingleFeatureMatcherXML