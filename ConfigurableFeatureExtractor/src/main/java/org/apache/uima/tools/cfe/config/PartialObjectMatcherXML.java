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
 * A representation of the model object '<em><b>Partial Object Matcher XML</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.uima.tools.cfe.config.PartialObjectMatcherXML#getGroupFeatureMatchers <em>Group Feature Matchers</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.PartialObjectMatcherXML#getAnnotationTypeName <em>Annotation Type Name</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.PartialObjectMatcherXML#getFullPath <em>Full Path</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.uima.tools.cfe.config.ConfigPackage#getPartialObjectMatcherXML()
 * @model extendedMetaData="name='PartialObjectMatcherXML' kind='elementOnly'"
 * @generated
 */
public interface PartialObjectMatcherXML extends EObject
{
  /**
     * Returns the value of the '<em><b>Group Feature Matchers</b></em>' containment reference list.
     * The list contents are of type {@link org.apache.uima.tools.cfe.config.GroupFeatureMatcherXML}.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Group Feature Matchers</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Group Feature Matchers</em>' containment reference list.
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getPartialObjectMatcherXML_GroupFeatureMatchers()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='groupFeatureMatchers' namespace='##targetNamespace'"
     * @generated
     */
  EList<GroupFeatureMatcherXML> getGroupFeatureMatchers();

  /**
     * Returns the value of the '<em><b>Annotation Type Name</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Annotation Type Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Annotation Type Name</em>' attribute.
     * @see #setAnnotationTypeName(String)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getPartialObjectMatcherXML_AnnotationTypeName()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='annotationTypeName'"
     * @generated
     */
  String getAnnotationTypeName();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.PartialObjectMatcherXML#getAnnotationTypeName <em>Annotation Type Name</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Annotation Type Name</em>' attribute.
     * @see #getAnnotationTypeName()
     * @generated
     */
  void setAnnotationTypeName(String value);

  /**
     * Returns the value of the '<em><b>Full Path</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Full Path</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Full Path</em>' attribute.
     * @see #setFullPath(String)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getPartialObjectMatcherXML_FullPath()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='fullPath'"
     * @generated
     */
  String getFullPath();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.PartialObjectMatcherXML#getFullPath <em>Full Path</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Full Path</em>' attribute.
     * @see #getFullPath()
     * @generated
     */
  void setFullPath(String value);

} // PartialObjectMatcherXML