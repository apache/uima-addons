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
 * A representation of the model object '<em><b>Group Feature Matcher XML</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.uima.tools.cfe.config.GroupFeatureMatcherXML#getFeatureMatchers <em>Feature Matchers</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.GroupFeatureMatcherXML#isExclude <em>Exclude</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.uima.tools.cfe.config.ConfigPackage#getGroupFeatureMatcherXML()
 * @model extendedMetaData="name='GroupFeatureMatcherXML' kind='elementOnly'"
 * @generated
 */
public interface GroupFeatureMatcherXML extends EObject
{
  /**
     * Returns the value of the '<em><b>Feature Matchers</b></em>' containment reference list.
     * The list contents are of type {@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML}.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Feature Matchers</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Feature Matchers</em>' containment reference list.
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getGroupFeatureMatcherXML_FeatureMatchers()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='featureMatchers' namespace='##targetNamespace'"
     * @generated
     */
  EList<SingleFeatureMatcherXML> getFeatureMatchers();

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
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getGroupFeatureMatcherXML_Exclude()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='element' name='exclude' namespace='##targetNamespace'"
     * @generated
     */
  boolean isExclude();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.GroupFeatureMatcherXML#isExclude <em>Exclude</em>}' attribute.
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
     * Unsets the value of the '{@link org.apache.uima.tools.cfe.config.GroupFeatureMatcherXML#isExclude <em>Exclude</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isSetExclude()
     * @see #isExclude()
     * @see #setExclude(boolean)
     * @generated
     */
  void unsetExclude();

  /**
     * Returns whether the value of the '{@link org.apache.uima.tools.cfe.config.GroupFeatureMatcherXML#isExclude <em>Exclude</em>}' attribute is set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return whether the value of the '<em>Exclude</em>' attribute is set.
     * @see #unsetExclude()
     * @see #isExclude()
     * @see #setExclude(boolean)
     * @generated
     */
  boolean isSetExclude();

} // GroupFeatureMatcherXML