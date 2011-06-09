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
package org.apache.uima.tools.cfe.config.util;

import java.util.Map;

import org.apache.uima.tools.cfe.config.ConfigPackage;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ConfigXMLProcessor extends XMLProcessor
{

  /**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public ConfigXMLProcessor()
  {
        super((EPackage.Registry.INSTANCE));
        ConfigPackage.eINSTANCE.eClass();
    }
  
  /**
     * Register for "*" and "xml" file extensions the ConfigResourceFactoryImpl factory.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  @Override
protected Map<String, Resource.Factory> getRegistrations()
  {
        if (registrations == null)
        {
            super.getRegistrations();
            registrations.put(XML_EXTENSION, new ConfigResourceFactoryImpl());
            registrations.put(STAR_EXTENSION, new ConfigResourceFactoryImpl());
        }
        return registrations;
    }

} //ConfigXMLProcessor
