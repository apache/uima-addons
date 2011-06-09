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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.apache.uima.tools.cfe.config.ConfigPackage
 * @generated
 */
public interface ConfigFactory extends EFactory
{
  /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  ConfigFactory eINSTANCE = org.apache.uima.tools.cfe.config.impl.ConfigFactoryImpl.init();

  /**
     * Returns a new object of class '<em>Bitset Feature Values XML</em>'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return a new object of class '<em>Bitset Feature Values XML</em>'.
     * @generated
     */
  BitsetFeatureValuesXML createBitsetFeatureValuesXML();

  /**
     * Returns a new object of class '<em>CFE Descriptor XML</em>'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return a new object of class '<em>CFE Descriptor XML</em>'.
     * @generated
     */
  CFEDescriptorXML createCFEDescriptorXML();

  /**
     * Returns a new object of class '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return a new object of class '<em>Document Root</em>'.
     * @generated
     */
  DocumentRoot createDocumentRoot();

  /**
     * Returns a new object of class '<em>Enum Feature Values XML</em>'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return a new object of class '<em>Enum Feature Values XML</em>'.
     * @generated
     */
  EnumFeatureValuesXML createEnumFeatureValuesXML();

  /**
     * Returns a new object of class '<em>Feature Object Matcher XML</em>'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return a new object of class '<em>Feature Object Matcher XML</em>'.
     * @generated
     */
  FeatureObjectMatcherXML createFeatureObjectMatcherXML();

  /**
     * Returns a new object of class '<em>Group Feature Matcher XML</em>'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return a new object of class '<em>Group Feature Matcher XML</em>'.
     * @generated
     */
  GroupFeatureMatcherXML createGroupFeatureMatcherXML();

  /**
     * Returns a new object of class '<em>Object Path Feature Values XML</em>'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return a new object of class '<em>Object Path Feature Values XML</em>'.
     * @generated
     */
  ObjectPathFeatureValuesXML createObjectPathFeatureValuesXML();

  /**
     * Returns a new object of class '<em>Partial Object Matcher XML</em>'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return a new object of class '<em>Partial Object Matcher XML</em>'.
     * @generated
     */
  PartialObjectMatcherXML createPartialObjectMatcherXML();

  /**
     * Returns a new object of class '<em>Pattern Feature Values XML</em>'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return a new object of class '<em>Pattern Feature Values XML</em>'.
     * @generated
     */
  PatternFeatureValuesXML createPatternFeatureValuesXML();

  /**
     * Returns a new object of class '<em>Range Feature Values XML</em>'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return a new object of class '<em>Range Feature Values XML</em>'.
     * @generated
     */
  RangeFeatureValuesXML createRangeFeatureValuesXML();

  /**
     * Returns a new object of class '<em>Single Feature Matcher XML</em>'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return a new object of class '<em>Single Feature Matcher XML</em>'.
     * @generated
     */
  SingleFeatureMatcherXML createSingleFeatureMatcherXML();

  /**
     * Returns a new object of class '<em>Target Annotation XML</em>'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return a new object of class '<em>Target Annotation XML</em>'.
     * @generated
     */
  TargetAnnotationXML createTargetAnnotationXML();

  /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
  ConfigPackage getConfigPackage();

} //ConfigFactory
