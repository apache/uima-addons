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
 * A representation of the model object '<em><b>Object Path Feature Values XML</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.uima.tools.cfe.config.ObjectPathFeatureValuesXML#getObjectPath <em>Object Path</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.uima.tools.cfe.config.ConfigPackage#getObjectPathFeatureValuesXML()
 * @model extendedMetaData="name='ObjectPathFeatureValuesXML' kind='empty'"
 * @generated
 */
public interface ObjectPathFeatureValuesXML extends EObject
{
  /**
     * Returns the value of the '<em><b>Object Path</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Object Path</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Object Path</em>' attribute.
     * @see #setObjectPath(String)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getObjectPathFeatureValuesXML_ObjectPath()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='objectPath'"
     * @generated
     */
  String getObjectPath();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.ObjectPathFeatureValuesXML#getObjectPath <em>Object Path</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Object Path</em>' attribute.
     * @see #getObjectPath()
     * @generated
     */
  void setObjectPath(String value);

} // ObjectPathFeatureValuesXML