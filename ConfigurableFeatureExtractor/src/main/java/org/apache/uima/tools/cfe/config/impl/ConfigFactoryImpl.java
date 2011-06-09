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
package org.apache.uima.tools.cfe.config.impl;

import org.apache.uima.tools.cfe.config.BitsetFeatureValuesXML;
import org.apache.uima.tools.cfe.config.CFEDescriptorXML;
import org.apache.uima.tools.cfe.config.ConfigFactory;
import org.apache.uima.tools.cfe.config.ConfigPackage;
import org.apache.uima.tools.cfe.config.DocumentRoot;
import org.apache.uima.tools.cfe.config.EnumFeatureValuesXML;
import org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML;
import org.apache.uima.tools.cfe.config.GroupFeatureMatcherXML;
import org.apache.uima.tools.cfe.config.ObjectPathFeatureValuesXML;
import org.apache.uima.tools.cfe.config.PartialObjectMatcherXML;
import org.apache.uima.tools.cfe.config.PatternFeatureValuesXML;
import org.apache.uima.tools.cfe.config.RangeFeatureValuesXML;
import org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML;
import org.apache.uima.tools.cfe.config.TargetAnnotationXML;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ConfigFactoryImpl extends EFactoryImpl implements ConfigFactory
{
  /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public static ConfigFactory init()
  {
        try
        {
            ConfigFactory theConfigFactory = (ConfigFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.apache.org/uima/tools/cfe/config"); 
            if (theConfigFactory != null)
            {
                return theConfigFactory;
            }
        }
        catch (Exception exception)
        {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ConfigFactoryImpl();
    }

  /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public ConfigFactoryImpl()
  {
        super();
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  @Override
public EObject create(EClass eClass)
  {
        switch (eClass.getClassifierID())
        {
            case ConfigPackage.BITSET_FEATURE_VALUES_XML: return createBitsetFeatureValuesXML();
            case ConfigPackage.CFE_DESCRIPTOR_XML: return createCFEDescriptorXML();
            case ConfigPackage.DOCUMENT_ROOT: return createDocumentRoot();
            case ConfigPackage.ENUM_FEATURE_VALUES_XML: return createEnumFeatureValuesXML();
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML: return createFeatureObjectMatcherXML();
            case ConfigPackage.GROUP_FEATURE_MATCHER_XML: return createGroupFeatureMatcherXML();
            case ConfigPackage.OBJECT_PATH_FEATURE_VALUES_XML: return createObjectPathFeatureValuesXML();
            case ConfigPackage.PARTIAL_OBJECT_MATCHER_XML: return createPartialObjectMatcherXML();
            case ConfigPackage.PATTERN_FEATURE_VALUES_XML: return createPatternFeatureValuesXML();
            case ConfigPackage.RANGE_FEATURE_VALUES_XML: return createRangeFeatureValuesXML();
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML: return createSingleFeatureMatcherXML();
            case ConfigPackage.TARGET_ANNOTATION_XML: return createTargetAnnotationXML();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public BitsetFeatureValuesXML createBitsetFeatureValuesXML()
  {
        BitsetFeatureValuesXMLImpl bitsetFeatureValuesXML = new BitsetFeatureValuesXMLImpl();
        return bitsetFeatureValuesXML;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public CFEDescriptorXML createCFEDescriptorXML()
  {
        CFEDescriptorXMLImpl cfeDescriptorXML = new CFEDescriptorXMLImpl();
        return cfeDescriptorXML;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public DocumentRoot createDocumentRoot()
  {
        DocumentRootImpl documentRoot = new DocumentRootImpl();
        return documentRoot;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EnumFeatureValuesXML createEnumFeatureValuesXML()
  {
        EnumFeatureValuesXMLImpl enumFeatureValuesXML = new EnumFeatureValuesXMLImpl();
        return enumFeatureValuesXML;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public FeatureObjectMatcherXML createFeatureObjectMatcherXML()
  {
        FeatureObjectMatcherXMLImpl featureObjectMatcherXML = new FeatureObjectMatcherXMLImpl();
        return featureObjectMatcherXML;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public GroupFeatureMatcherXML createGroupFeatureMatcherXML()
  {
        GroupFeatureMatcherXMLImpl groupFeatureMatcherXML = new GroupFeatureMatcherXMLImpl();
        return groupFeatureMatcherXML;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public ObjectPathFeatureValuesXML createObjectPathFeatureValuesXML()
  {
        ObjectPathFeatureValuesXMLImpl objectPathFeatureValuesXML = new ObjectPathFeatureValuesXMLImpl();
        return objectPathFeatureValuesXML;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public PartialObjectMatcherXML createPartialObjectMatcherXML()
  {
        PartialObjectMatcherXMLImpl partialObjectMatcherXML = new PartialObjectMatcherXMLImpl();
        return partialObjectMatcherXML;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public PatternFeatureValuesXML createPatternFeatureValuesXML()
  {
        PatternFeatureValuesXMLImpl patternFeatureValuesXML = new PatternFeatureValuesXMLImpl();
        return patternFeatureValuesXML;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public RangeFeatureValuesXML createRangeFeatureValuesXML()
  {
        RangeFeatureValuesXMLImpl rangeFeatureValuesXML = new RangeFeatureValuesXMLImpl();
        return rangeFeatureValuesXML;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public SingleFeatureMatcherXML createSingleFeatureMatcherXML()
  {
        SingleFeatureMatcherXMLImpl singleFeatureMatcherXML = new SingleFeatureMatcherXMLImpl();
        return singleFeatureMatcherXML;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public TargetAnnotationXML createTargetAnnotationXML()
  {
        TargetAnnotationXMLImpl targetAnnotationXML = new TargetAnnotationXMLImpl();
        return targetAnnotationXML;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public ConfigPackage getConfigPackage()
  {
        return (ConfigPackage)getEPackage();
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
  @Deprecated
public static ConfigPackage getPackage()
  {
        return ConfigPackage.eINSTANCE;
    }

} //ConfigFactoryImpl
