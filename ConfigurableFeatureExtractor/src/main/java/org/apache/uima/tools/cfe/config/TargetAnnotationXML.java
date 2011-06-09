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
 * A representation of the model object '<em><b>Target Annotation XML</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.uima.tools.cfe.config.TargetAnnotationXML#getTargetAnnotationMatcher <em>Target Annotation Matcher</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.TargetAnnotationXML#getFeatureAnnotationMatchers <em>Feature Annotation Matchers</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.TargetAnnotationXML#getClassName <em>Class Name</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.TargetAnnotationXML#getEnclosingAnnotation <em>Enclosing Annotation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.uima.tools.cfe.config.ConfigPackage#getTargetAnnotationXML()
 * @model extendedMetaData="name='TargetAnnotationXML' kind='elementOnly'"
 * @generated
 */
public interface TargetAnnotationXML extends EObject
{
  /**
     * Returns the value of the '<em><b>Target Annotation Matcher</b></em>' containment reference.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Annotation Matcher</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Target Annotation Matcher</em>' containment reference.
     * @see #setTargetAnnotationMatcher(PartialObjectMatcherXML)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getTargetAnnotationXML_TargetAnnotationMatcher()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='targetAnnotationMatcher' namespace='##targetNamespace'"
     * @generated
     */
  PartialObjectMatcherXML getTargetAnnotationMatcher();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.TargetAnnotationXML#getTargetAnnotationMatcher <em>Target Annotation Matcher</em>}' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Annotation Matcher</em>' containment reference.
     * @see #getTargetAnnotationMatcher()
     * @generated
     */
  void setTargetAnnotationMatcher(PartialObjectMatcherXML value);

  /**
     * Returns the value of the '<em><b>Feature Annotation Matchers</b></em>' containment reference list.
     * The list contents are of type {@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML}.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Feature Annotation Matchers</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Feature Annotation Matchers</em>' containment reference list.
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getTargetAnnotationXML_FeatureAnnotationMatchers()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='featureAnnotationMatchers' namespace='##targetNamespace'"
     * @generated
     */
  EList<FeatureObjectMatcherXML> getFeatureAnnotationMatchers();

  /**
     * Returns the value of the '<em><b>Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * class id for RRM training
     * <!-- end-model-doc -->
     * @return the value of the '<em>Class Name</em>' attribute.
     * @see #setClassName(String)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getTargetAnnotationXML_ClassName()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='className'"
     * @generated
     */
  String getClassName();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.TargetAnnotationXML#getClassName <em>Class Name</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Class Name</em>' attribute.
     * @see #getClassName()
     * @generated
     */
  void setClassName(String value);

  /**
     * Returns the value of the '<em><b>Enclosing Annotation</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Enclosing Annotation</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Enclosing Annotation</em>' attribute.
     * @see #setEnclosingAnnotation(String)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getTargetAnnotationXML_EnclosingAnnotation()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='enclosingAnnotation'"
     * @generated
     */
  String getEnclosingAnnotation();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.TargetAnnotationXML#getEnclosingAnnotation <em>Enclosing Annotation</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Enclosing Annotation</em>' attribute.
     * @see #getEnclosingAnnotation()
     * @generated
     */
  void setEnclosingAnnotation(String value);

} // TargetAnnotationXML