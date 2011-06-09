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
 * A representation of the model object '<em><b>CFE Descriptor XML</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.uima.tools.cfe.config.CFEDescriptorXML#getTargetAnnotations <em>Target Annotations</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.CFEDescriptorXML#getNullValueImage <em>Null Value Image</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.uima.tools.cfe.config.ConfigPackage#getCFEDescriptorXML()
 * @model extendedMetaData="name='CFEDescriptorXML' kind='elementOnly'"
 * @generated
 */
public interface CFEDescriptorXML extends EObject
{
  /**
     * Returns the value of the '<em><b>Target Annotations</b></em>' containment reference list.
     * The list contents are of type {@link org.apache.uima.tools.cfe.config.TargetAnnotationXML}.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Annotations</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Target Annotations</em>' containment reference list.
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getCFEDescriptorXML_TargetAnnotations()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='targetAnnotations' namespace='##targetNamespace'"
     * @generated
     */
  EList<TargetAnnotationXML> getTargetAnnotations();

  /**
     * Returns the value of the '<em><b>Null Value Image</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Null Value Image</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Null Value Image</em>' attribute.
     * @see #setNullValueImage(String)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getCFEDescriptorXML_NullValueImage()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='nullValueImage'"
     * @generated
     */
  String getNullValueImage();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.CFEDescriptorXML#getNullValueImage <em>Null Value Image</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Null Value Image</em>' attribute.
     * @see #getNullValueImage()
     * @generated
     */
  void setNullValueImage(String value);

} // CFEDescriptorXML