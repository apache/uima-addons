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
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ConfigPackageImpl extends EPackageImpl implements ConfigPackage
{
  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  private EClass bitsetFeatureValuesXMLEClass = null;

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  private EClass cfeDescriptorXMLEClass = null;

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  private EClass documentRootEClass = null;

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  private EClass enumFeatureValuesXMLEClass = null;

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  private EClass featureObjectMatcherXMLEClass = null;

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  private EClass groupFeatureMatcherXMLEClass = null;

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  private EClass objectPathFeatureValuesXMLEClass = null;

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  private EClass partialObjectMatcherXMLEClass = null;

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  private EClass patternFeatureValuesXMLEClass = null;

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  private EClass rangeFeatureValuesXMLEClass = null;

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  private EClass singleFeatureMatcherXMLEClass = null;

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  private EClass targetAnnotationXMLEClass = null;

  /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#eNS_URI
     * @see #init()
     * @generated
     */
  private ConfigPackageImpl()
  {
        super(eNS_URI, ConfigFactory.eINSTANCE);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  private static boolean isInited = false;

  /**
     * Creates, registers, and initializes the <b>Package</b> for this
     * model, and for any others upon which it depends.  Simple
     * dependencies are satisfied by calling this method on all
     * dependent packages before doing anything else.  This method drives
     * initialization for interdependent packages directly, in parallel
     * with this package, itself.
     * <p>Of this package and its interdependencies, all packages which
     * have not yet been registered by their URI values are first created
     * and registered.  The packages are then initialized in two steps:
     * meta-model objects for all of the packages are created before any
     * are initialized, since one package's meta-model objects may refer to
     * those of another.
     * <p>Invocation of this method will not affect any packages that have
     * already been initialized.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
  public static ConfigPackage init()
  {
        if (isInited) return (ConfigPackage)EPackage.Registry.INSTANCE.getEPackage(ConfigPackage.eNS_URI);

        // Obtain or create and register package
        ConfigPackageImpl theConfigPackage = (ConfigPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof ConfigPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new ConfigPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        XMLTypePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theConfigPackage.createPackageContents();

        // Initialize created meta-data
        theConfigPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theConfigPackage.freeze();

        return theConfigPackage;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EClass getBitsetFeatureValuesXML()
  {
        return bitsetFeatureValuesXMLEClass;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getBitsetFeatureValuesXML_Bitmask()
  {
        return (EAttribute)bitsetFeatureValuesXMLEClass.getEStructuralFeatures().get(0);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getBitsetFeatureValuesXML_ExactMatch()
  {
        return (EAttribute)bitsetFeatureValuesXMLEClass.getEStructuralFeatures().get(1);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EClass getCFEDescriptorXML()
  {
        return cfeDescriptorXMLEClass;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EReference getCFEDescriptorXML_TargetAnnotations()
  {
        return (EReference)cfeDescriptorXMLEClass.getEStructuralFeatures().get(0);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getCFEDescriptorXML_NullValueImage()
  {
        return (EAttribute)cfeDescriptorXMLEClass.getEStructuralFeatures().get(1);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EClass getDocumentRoot()
  {
        return documentRootEClass;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getDocumentRoot_Mixed()
  {
        return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EReference getDocumentRoot_XMLNSPrefixMap()
  {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EReference getDocumentRoot_XSISchemaLocation()
  {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EReference getDocumentRoot_CFEConfig()
  {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EClass getEnumFeatureValuesXML()
  {
        return enumFeatureValuesXMLEClass;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getEnumFeatureValuesXML_Values()
  {
        return (EAttribute)enumFeatureValuesXMLEClass.getEStructuralFeatures().get(0);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getEnumFeatureValuesXML_CaseSensitive()
  {
        return (EAttribute)enumFeatureValuesXMLEClass.getEStructuralFeatures().get(1);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EClass getFeatureObjectMatcherXML()
  {
        return featureObjectMatcherXMLEClass;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getFeatureObjectMatcherXML_Distance()
  {
        return (EAttribute)featureObjectMatcherXMLEClass.getEStructuralFeatures().get(0);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getFeatureObjectMatcherXML_Orientation()
  {
        return (EAttribute)featureObjectMatcherXMLEClass.getEStructuralFeatures().get(1);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getFeatureObjectMatcherXML_WindowFlags()
  {
        return (EAttribute)featureObjectMatcherXMLEClass.getEStructuralFeatures().get(2);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getFeatureObjectMatcherXML_WindowsizeEnclosed()
  {
        return (EAttribute)featureObjectMatcherXMLEClass.getEStructuralFeatures().get(3);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getFeatureObjectMatcherXML_WindowsizeInside()
  {
        return (EAttribute)featureObjectMatcherXMLEClass.getEStructuralFeatures().get(4);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getFeatureObjectMatcherXML_WindowsizeLeft()
  {
        return (EAttribute)featureObjectMatcherXMLEClass.getEStructuralFeatures().get(5);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getFeatureObjectMatcherXML_WindowsizeRight()
  {
        return (EAttribute)featureObjectMatcherXMLEClass.getEStructuralFeatures().get(6);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EClass getGroupFeatureMatcherXML()
  {
        return groupFeatureMatcherXMLEClass;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EReference getGroupFeatureMatcherXML_FeatureMatchers()
  {
        return (EReference)groupFeatureMatcherXMLEClass.getEStructuralFeatures().get(0);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getGroupFeatureMatcherXML_Exclude()
  {
        return (EAttribute)groupFeatureMatcherXMLEClass.getEStructuralFeatures().get(1);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EClass getObjectPathFeatureValuesXML()
  {
        return objectPathFeatureValuesXMLEClass;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getObjectPathFeatureValuesXML_ObjectPath()
  {
        return (EAttribute)objectPathFeatureValuesXMLEClass.getEStructuralFeatures().get(0);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EClass getPartialObjectMatcherXML()
  {
        return partialObjectMatcherXMLEClass;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EReference getPartialObjectMatcherXML_GroupFeatureMatchers()
  {
        return (EReference)partialObjectMatcherXMLEClass.getEStructuralFeatures().get(0);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getPartialObjectMatcherXML_AnnotationTypeName()
  {
        return (EAttribute)partialObjectMatcherXMLEClass.getEStructuralFeatures().get(1);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getPartialObjectMatcherXML_FullPath()
  {
        return (EAttribute)partialObjectMatcherXMLEClass.getEStructuralFeatures().get(2);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EClass getPatternFeatureValuesXML()
  {
        return patternFeatureValuesXMLEClass;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getPatternFeatureValuesXML_Pattern()
  {
        return (EAttribute)patternFeatureValuesXMLEClass.getEStructuralFeatures().get(0);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EClass getRangeFeatureValuesXML()
  {
        return rangeFeatureValuesXMLEClass;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getRangeFeatureValuesXML_LowerBoundary()
  {
        return (EAttribute)rangeFeatureValuesXMLEClass.getEStructuralFeatures().get(0);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getRangeFeatureValuesXML_LowerBoundaryInclusive()
  {
        return (EAttribute)rangeFeatureValuesXMLEClass.getEStructuralFeatures().get(1);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getRangeFeatureValuesXML_UpperBoundary()
  {
        return (EAttribute)rangeFeatureValuesXMLEClass.getEStructuralFeatures().get(2);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getRangeFeatureValuesXML_UpperBoundaryInclusive()
  {
        return (EAttribute)rangeFeatureValuesXMLEClass.getEStructuralFeatures().get(3);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EClass getSingleFeatureMatcherXML()
  {
        return singleFeatureMatcherXMLEClass;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EReference getSingleFeatureMatcherXML_RangeFeatureValues()
  {
        return (EReference)singleFeatureMatcherXMLEClass.getEStructuralFeatures().get(0);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EReference getSingleFeatureMatcherXML_EnumFeatureValues()
  {
        return (EReference)singleFeatureMatcherXMLEClass.getEStructuralFeatures().get(1);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EReference getSingleFeatureMatcherXML_BitsetFeatureValues()
  {
        return (EReference)singleFeatureMatcherXMLEClass.getEStructuralFeatures().get(2);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EReference getSingleFeatureMatcherXML_ObjectPathFeatureValues()
  {
        return (EReference)singleFeatureMatcherXMLEClass.getEStructuralFeatures().get(3);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EReference getSingleFeatureMatcherXML_PatternFeatureValues()
  {
        return (EReference)singleFeatureMatcherXMLEClass.getEStructuralFeatures().get(4);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getSingleFeatureMatcherXML_Exclude()
  {
        return (EAttribute)singleFeatureMatcherXMLEClass.getEStructuralFeatures().get(5);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getSingleFeatureMatcherXML_FeaturePath()
  {
        return (EAttribute)singleFeatureMatcherXMLEClass.getEStructuralFeatures().get(6);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getSingleFeatureMatcherXML_FeatureTypeName()
  {
        return (EAttribute)singleFeatureMatcherXMLEClass.getEStructuralFeatures().get(7);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getSingleFeatureMatcherXML_Quiet()
  {
        return (EAttribute)singleFeatureMatcherXMLEClass.getEStructuralFeatures().get(8);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EClass getTargetAnnotationXML()
  {
        return targetAnnotationXMLEClass;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EReference getTargetAnnotationXML_TargetAnnotationMatcher()
  {
        return (EReference)targetAnnotationXMLEClass.getEStructuralFeatures().get(0);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EReference getTargetAnnotationXML_FeatureAnnotationMatchers()
  {
        return (EReference)targetAnnotationXMLEClass.getEStructuralFeatures().get(1);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getTargetAnnotationXML_ClassName()
  {
        return (EAttribute)targetAnnotationXMLEClass.getEStructuralFeatures().get(2);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EAttribute getTargetAnnotationXML_EnclosingAnnotation()
  {
        return (EAttribute)targetAnnotationXMLEClass.getEStructuralFeatures().get(3);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public ConfigFactory getConfigFactory()
  {
        return (ConfigFactory)getEFactoryInstance();
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  private boolean isCreated = false;

  /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void createPackageContents()
  {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        bitsetFeatureValuesXMLEClass = createEClass(BITSET_FEATURE_VALUES_XML);
        createEAttribute(bitsetFeatureValuesXMLEClass, BITSET_FEATURE_VALUES_XML__BITMASK);
        createEAttribute(bitsetFeatureValuesXMLEClass, BITSET_FEATURE_VALUES_XML__EXACT_MATCH);

        cfeDescriptorXMLEClass = createEClass(CFE_DESCRIPTOR_XML);
        createEReference(cfeDescriptorXMLEClass, CFE_DESCRIPTOR_XML__TARGET_ANNOTATIONS);
        createEAttribute(cfeDescriptorXMLEClass, CFE_DESCRIPTOR_XML__NULL_VALUE_IMAGE);

        documentRootEClass = createEClass(DOCUMENT_ROOT);
        createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
        createEReference(documentRootEClass, DOCUMENT_ROOT__CFE_CONFIG);

        enumFeatureValuesXMLEClass = createEClass(ENUM_FEATURE_VALUES_XML);
        createEAttribute(enumFeatureValuesXMLEClass, ENUM_FEATURE_VALUES_XML__VALUES);
        createEAttribute(enumFeatureValuesXMLEClass, ENUM_FEATURE_VALUES_XML__CASE_SENSITIVE);

        featureObjectMatcherXMLEClass = createEClass(FEATURE_OBJECT_MATCHER_XML);
        createEAttribute(featureObjectMatcherXMLEClass, FEATURE_OBJECT_MATCHER_XML__DISTANCE);
        createEAttribute(featureObjectMatcherXMLEClass, FEATURE_OBJECT_MATCHER_XML__ORIENTATION);
        createEAttribute(featureObjectMatcherXMLEClass, FEATURE_OBJECT_MATCHER_XML__WINDOW_FLAGS);
        createEAttribute(featureObjectMatcherXMLEClass, FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_ENCLOSED);
        createEAttribute(featureObjectMatcherXMLEClass, FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_INSIDE);
        createEAttribute(featureObjectMatcherXMLEClass, FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_LEFT);
        createEAttribute(featureObjectMatcherXMLEClass, FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_RIGHT);

        groupFeatureMatcherXMLEClass = createEClass(GROUP_FEATURE_MATCHER_XML);
        createEReference(groupFeatureMatcherXMLEClass, GROUP_FEATURE_MATCHER_XML__FEATURE_MATCHERS);
        createEAttribute(groupFeatureMatcherXMLEClass, GROUP_FEATURE_MATCHER_XML__EXCLUDE);

        objectPathFeatureValuesXMLEClass = createEClass(OBJECT_PATH_FEATURE_VALUES_XML);
        createEAttribute(objectPathFeatureValuesXMLEClass, OBJECT_PATH_FEATURE_VALUES_XML__OBJECT_PATH);

        partialObjectMatcherXMLEClass = createEClass(PARTIAL_OBJECT_MATCHER_XML);
        createEReference(partialObjectMatcherXMLEClass, PARTIAL_OBJECT_MATCHER_XML__GROUP_FEATURE_MATCHERS);
        createEAttribute(partialObjectMatcherXMLEClass, PARTIAL_OBJECT_MATCHER_XML__ANNOTATION_TYPE_NAME);
        createEAttribute(partialObjectMatcherXMLEClass, PARTIAL_OBJECT_MATCHER_XML__FULL_PATH);

        patternFeatureValuesXMLEClass = createEClass(PATTERN_FEATURE_VALUES_XML);
        createEAttribute(patternFeatureValuesXMLEClass, PATTERN_FEATURE_VALUES_XML__PATTERN);

        rangeFeatureValuesXMLEClass = createEClass(RANGE_FEATURE_VALUES_XML);
        createEAttribute(rangeFeatureValuesXMLEClass, RANGE_FEATURE_VALUES_XML__LOWER_BOUNDARY);
        createEAttribute(rangeFeatureValuesXMLEClass, RANGE_FEATURE_VALUES_XML__LOWER_BOUNDARY_INCLUSIVE);
        createEAttribute(rangeFeatureValuesXMLEClass, RANGE_FEATURE_VALUES_XML__UPPER_BOUNDARY);
        createEAttribute(rangeFeatureValuesXMLEClass, RANGE_FEATURE_VALUES_XML__UPPER_BOUNDARY_INCLUSIVE);

        singleFeatureMatcherXMLEClass = createEClass(SINGLE_FEATURE_MATCHER_XML);
        createEReference(singleFeatureMatcherXMLEClass, SINGLE_FEATURE_MATCHER_XML__RANGE_FEATURE_VALUES);
        createEReference(singleFeatureMatcherXMLEClass, SINGLE_FEATURE_MATCHER_XML__ENUM_FEATURE_VALUES);
        createEReference(singleFeatureMatcherXMLEClass, SINGLE_FEATURE_MATCHER_XML__BITSET_FEATURE_VALUES);
        createEReference(singleFeatureMatcherXMLEClass, SINGLE_FEATURE_MATCHER_XML__OBJECT_PATH_FEATURE_VALUES);
        createEReference(singleFeatureMatcherXMLEClass, SINGLE_FEATURE_MATCHER_XML__PATTERN_FEATURE_VALUES);
        createEAttribute(singleFeatureMatcherXMLEClass, SINGLE_FEATURE_MATCHER_XML__EXCLUDE);
        createEAttribute(singleFeatureMatcherXMLEClass, SINGLE_FEATURE_MATCHER_XML__FEATURE_PATH);
        createEAttribute(singleFeatureMatcherXMLEClass, SINGLE_FEATURE_MATCHER_XML__FEATURE_TYPE_NAME);
        createEAttribute(singleFeatureMatcherXMLEClass, SINGLE_FEATURE_MATCHER_XML__QUIET);

        targetAnnotationXMLEClass = createEClass(TARGET_ANNOTATION_XML);
        createEReference(targetAnnotationXMLEClass, TARGET_ANNOTATION_XML__TARGET_ANNOTATION_MATCHER);
        createEReference(targetAnnotationXMLEClass, TARGET_ANNOTATION_XML__FEATURE_ANNOTATION_MATCHERS);
        createEAttribute(targetAnnotationXMLEClass, TARGET_ANNOTATION_XML__CLASS_NAME);
        createEAttribute(targetAnnotationXMLEClass, TARGET_ANNOTATION_XML__ENCLOSING_ANNOTATION);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  private boolean isInitialized = false;

  /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void initializePackageContents()
  {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        featureObjectMatcherXMLEClass.getESuperTypes().add(this.getPartialObjectMatcherXML());

        // Initialize classes and features; add operations and parameters
        initEClass(bitsetFeatureValuesXMLEClass, BitsetFeatureValuesXML.class, "BitsetFeatureValuesXML", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBitsetFeatureValuesXML_Bitmask(), theXMLTypePackage.getString(), "bitmask", null, 1, 1, BitsetFeatureValuesXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBitsetFeatureValuesXML_ExactMatch(), theXMLTypePackage.getBoolean(), "exactMatch", null, 0, 1, BitsetFeatureValuesXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(cfeDescriptorXMLEClass, CFEDescriptorXML.class, "CFEDescriptorXML", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCFEDescriptorXML_TargetAnnotations(), this.getTargetAnnotationXML(), null, "targetAnnotations", null, 1, -1, CFEDescriptorXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getCFEDescriptorXML_NullValueImage(), theXMLTypePackage.getString(), "nullValueImage", null, 0, 1, CFEDescriptorXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_CFEConfig(), this.getCFEDescriptorXML(), null, "cFEConfig", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

        initEClass(enumFeatureValuesXMLEClass, EnumFeatureValuesXML.class, "EnumFeatureValuesXML", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getEnumFeatureValuesXML_Values(), theXMLTypePackage.getString(), "values", null, 0, -1, EnumFeatureValuesXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEnumFeatureValuesXML_CaseSensitive(), theXMLTypePackage.getBoolean(), "caseSensitive", null, 0, 1, EnumFeatureValuesXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(featureObjectMatcherXMLEClass, FeatureObjectMatcherXML.class, "FeatureObjectMatcherXML", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFeatureObjectMatcherXML_Distance(), theXMLTypePackage.getBoolean(), "distance", null, 0, 1, FeatureObjectMatcherXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFeatureObjectMatcherXML_Orientation(), theXMLTypePackage.getBoolean(), "orientation", null, 0, 1, FeatureObjectMatcherXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFeatureObjectMatcherXML_WindowFlags(), theXMLTypePackage.getInt(), "windowFlags", null, 0, 1, FeatureObjectMatcherXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFeatureObjectMatcherXML_WindowsizeEnclosed(), theXMLTypePackage.getInt(), "windowsizeEnclosed", null, 0, 1, FeatureObjectMatcherXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFeatureObjectMatcherXML_WindowsizeInside(), theXMLTypePackage.getInt(), "windowsizeInside", null, 0, 1, FeatureObjectMatcherXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFeatureObjectMatcherXML_WindowsizeLeft(), theXMLTypePackage.getInt(), "windowsizeLeft", null, 0, 1, FeatureObjectMatcherXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFeatureObjectMatcherXML_WindowsizeRight(), theXMLTypePackage.getInt(), "windowsizeRight", null, 0, 1, FeatureObjectMatcherXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(groupFeatureMatcherXMLEClass, GroupFeatureMatcherXML.class, "GroupFeatureMatcherXML", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getGroupFeatureMatcherXML_FeatureMatchers(), this.getSingleFeatureMatcherXML(), null, "featureMatchers", null, 1, -1, GroupFeatureMatcherXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGroupFeatureMatcherXML_Exclude(), theXMLTypePackage.getBoolean(), "exclude", null, 0, 1, GroupFeatureMatcherXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(objectPathFeatureValuesXMLEClass, ObjectPathFeatureValuesXML.class, "ObjectPathFeatureValuesXML", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getObjectPathFeatureValuesXML_ObjectPath(), theXMLTypePackage.getString(), "objectPath", null, 1, 1, ObjectPathFeatureValuesXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(partialObjectMatcherXMLEClass, PartialObjectMatcherXML.class, "PartialObjectMatcherXML", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPartialObjectMatcherXML_GroupFeatureMatchers(), this.getGroupFeatureMatcherXML(), null, "groupFeatureMatchers", null, 0, -1, PartialObjectMatcherXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPartialObjectMatcherXML_AnnotationTypeName(), theXMLTypePackage.getString(), "annotationTypeName", null, 1, 1, PartialObjectMatcherXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPartialObjectMatcherXML_FullPath(), theXMLTypePackage.getString(), "fullPath", null, 0, 1, PartialObjectMatcherXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(patternFeatureValuesXMLEClass, PatternFeatureValuesXML.class, "PatternFeatureValuesXML", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getPatternFeatureValuesXML_Pattern(), theXMLTypePackage.getString(), "pattern", null, 0, 1, PatternFeatureValuesXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(rangeFeatureValuesXMLEClass, RangeFeatureValuesXML.class, "RangeFeatureValuesXML", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRangeFeatureValuesXML_LowerBoundary(), theXMLTypePackage.getAnySimpleType(), "lowerBoundary", null, 0, 1, RangeFeatureValuesXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRangeFeatureValuesXML_LowerBoundaryInclusive(), theXMLTypePackage.getBoolean(), "lowerBoundaryInclusive", null, 0, 1, RangeFeatureValuesXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRangeFeatureValuesXML_UpperBoundary(), theXMLTypePackage.getAnySimpleType(), "upperBoundary", null, 0, 1, RangeFeatureValuesXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRangeFeatureValuesXML_UpperBoundaryInclusive(), theXMLTypePackage.getBoolean(), "upperBoundaryInclusive", null, 0, 1, RangeFeatureValuesXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(singleFeatureMatcherXMLEClass, SingleFeatureMatcherXML.class, "SingleFeatureMatcherXML", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSingleFeatureMatcherXML_RangeFeatureValues(), this.getRangeFeatureValuesXML(), null, "rangeFeatureValues", null, 0, 1, SingleFeatureMatcherXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSingleFeatureMatcherXML_EnumFeatureValues(), this.getEnumFeatureValuesXML(), null, "enumFeatureValues", null, 0, 1, SingleFeatureMatcherXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSingleFeatureMatcherXML_BitsetFeatureValues(), this.getBitsetFeatureValuesXML(), null, "bitsetFeatureValues", null, 0, 1, SingleFeatureMatcherXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSingleFeatureMatcherXML_ObjectPathFeatureValues(), this.getObjectPathFeatureValuesXML(), null, "objectPathFeatureValues", null, 0, 1, SingleFeatureMatcherXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSingleFeatureMatcherXML_PatternFeatureValues(), this.getPatternFeatureValuesXML(), null, "patternFeatureValues", null, 0, 1, SingleFeatureMatcherXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSingleFeatureMatcherXML_Exclude(), theXMLTypePackage.getBoolean(), "exclude", null, 0, 1, SingleFeatureMatcherXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSingleFeatureMatcherXML_FeaturePath(), theXMLTypePackage.getString(), "featurePath", null, 1, 1, SingleFeatureMatcherXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSingleFeatureMatcherXML_FeatureTypeName(), theXMLTypePackage.getString(), "featureTypeName", null, 0, 1, SingleFeatureMatcherXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSingleFeatureMatcherXML_Quiet(), theXMLTypePackage.getBoolean(), "quiet", null, 0, 1, SingleFeatureMatcherXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(targetAnnotationXMLEClass, TargetAnnotationXML.class, "TargetAnnotationXML", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTargetAnnotationXML_TargetAnnotationMatcher(), this.getPartialObjectMatcherXML(), null, "targetAnnotationMatcher", null, 1, 1, TargetAnnotationXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTargetAnnotationXML_FeatureAnnotationMatchers(), this.getFeatureObjectMatcherXML(), null, "featureAnnotationMatchers", null, 0, -1, TargetAnnotationXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTargetAnnotationXML_ClassName(), theXMLTypePackage.getString(), "className", null, 1, 1, TargetAnnotationXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTargetAnnotationXML_EnclosingAnnotation(), theXMLTypePackage.getString(), "enclosingAnnotation", null, 1, 1, TargetAnnotationXML.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
        createExtendedMetaDataAnnotations();
    }

  /**
     * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected void createExtendedMetaDataAnnotations()
  {
        String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
        addAnnotation
          (bitsetFeatureValuesXMLEClass, 
           source, 
           new String[] 
           {
             "name", "BitsetFeatureValuesXML",
             "kind", "empty"
           });		
        addAnnotation
          (getBitsetFeatureValuesXML_Bitmask(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "bitmask"
           });		
        addAnnotation
          (getBitsetFeatureValuesXML_ExactMatch(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "exact_match"
           });		
        addAnnotation
          (cfeDescriptorXMLEClass, 
           source, 
           new String[] 
           {
             "name", "CFEDescriptorXML",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getCFEDescriptorXML_TargetAnnotations(), 
           source, 
           new String[] 
           {
             "kind", "element",
             "name", "targetAnnotations",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getCFEDescriptorXML_NullValueImage(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "nullValueImage"
           });		
        addAnnotation
          (documentRootEClass, 
           source, 
           new String[] 
           {
             "name", "",
             "kind", "mixed"
           });		
        addAnnotation
          (getDocumentRoot_Mixed(), 
           source, 
           new String[] 
           {
             "kind", "elementWildcard",
             "name", ":mixed"
           });		
        addAnnotation
          (getDocumentRoot_XMLNSPrefixMap(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "xmlns:prefix"
           });		
        addAnnotation
          (getDocumentRoot_XSISchemaLocation(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "xsi:schemaLocation"
           });		
        addAnnotation
          (getDocumentRoot_CFEConfig(), 
           source, 
           new String[] 
           {
             "kind", "element",
             "name", "CFEConfig",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (enumFeatureValuesXMLEClass, 
           source, 
           new String[] 
           {
             "name", "EnumFeatureValuesXML",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getEnumFeatureValuesXML_Values(), 
           source, 
           new String[] 
           {
             "kind", "element",
             "name", "values",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getEnumFeatureValuesXML_CaseSensitive(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "caseSensitive"
           });		
        addAnnotation
          (featureObjectMatcherXMLEClass, 
           source, 
           new String[] 
           {
             "name", "FeatureObjectMatcherXML",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getFeatureObjectMatcherXML_Distance(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "distance"
           });		
        addAnnotation
          (getFeatureObjectMatcherXML_Orientation(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "orientation"
           });			
        addAnnotation
          (getFeatureObjectMatcherXML_WindowFlags(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "windowFlags"
           });		
        addAnnotation
          (getFeatureObjectMatcherXML_WindowsizeEnclosed(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "windowsizeEnclosed"
           });		
        addAnnotation
          (getFeatureObjectMatcherXML_WindowsizeInside(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "windowsizeInside"
           });		
        addAnnotation
          (getFeatureObjectMatcherXML_WindowsizeLeft(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "windowsizeLeft"
           });		
        addAnnotation
          (getFeatureObjectMatcherXML_WindowsizeRight(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "windowsizeRight"
           });		
        addAnnotation
          (groupFeatureMatcherXMLEClass, 
           source, 
           new String[] 
           {
             "name", "GroupFeatureMatcherXML",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getGroupFeatureMatcherXML_FeatureMatchers(), 
           source, 
           new String[] 
           {
             "kind", "element",
             "name", "featureMatchers",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getGroupFeatureMatcherXML_Exclude(), 
           source, 
           new String[] 
           {
             "kind", "element",
             "name", "exclude",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (objectPathFeatureValuesXMLEClass, 
           source, 
           new String[] 
           {
             "name", "ObjectPathFeatureValuesXML",
             "kind", "empty"
           });		
        addAnnotation
          (getObjectPathFeatureValuesXML_ObjectPath(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "objectPath"
           });		
        addAnnotation
          (partialObjectMatcherXMLEClass, 
           source, 
           new String[] 
           {
             "name", "PartialObjectMatcherXML",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getPartialObjectMatcherXML_GroupFeatureMatchers(), 
           source, 
           new String[] 
           {
             "kind", "element",
             "name", "groupFeatureMatchers",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getPartialObjectMatcherXML_AnnotationTypeName(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "annotationTypeName"
           });		
        addAnnotation
          (getPartialObjectMatcherXML_FullPath(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "fullPath"
           });		
        addAnnotation
          (patternFeatureValuesXMLEClass, 
           source, 
           new String[] 
           {
             "name", "PatternFeatureValuesXML",
             "kind", "empty"
           });		
        addAnnotation
          (getPatternFeatureValuesXML_Pattern(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "pattern"
           });		
        addAnnotation
          (rangeFeatureValuesXMLEClass, 
           source, 
           new String[] 
           {
             "name", "RangeFeatureValuesXML",
             "kind", "empty"
           });		
        addAnnotation
          (getRangeFeatureValuesXML_LowerBoundary(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "lowerBoundary"
           });		
        addAnnotation
          (getRangeFeatureValuesXML_LowerBoundaryInclusive(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "lowerBoundaryInclusive"
           });		
        addAnnotation
          (getRangeFeatureValuesXML_UpperBoundary(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "upperBoundary"
           });		
        addAnnotation
          (getRangeFeatureValuesXML_UpperBoundaryInclusive(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "upperBoundaryInclusive"
           });		
        addAnnotation
          (singleFeatureMatcherXMLEClass, 
           source, 
           new String[] 
           {
             "name", "SingleFeatureMatcherXML",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getSingleFeatureMatcherXML_RangeFeatureValues(), 
           source, 
           new String[] 
           {
             "kind", "element",
             "name", "rangeFeatureValues",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getSingleFeatureMatcherXML_EnumFeatureValues(), 
           source, 
           new String[] 
           {
             "kind", "element",
             "name", "enumFeatureValues",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getSingleFeatureMatcherXML_BitsetFeatureValues(), 
           source, 
           new String[] 
           {
             "kind", "element",
             "name", "bitsetFeatureValues",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getSingleFeatureMatcherXML_ObjectPathFeatureValues(), 
           source, 
           new String[] 
           {
             "kind", "element",
             "name", "objectPathFeatureValues",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getSingleFeatureMatcherXML_PatternFeatureValues(), 
           source, 
           new String[] 
           {
             "kind", "element",
             "name", "patternFeatureValues",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getSingleFeatureMatcherXML_Exclude(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "exclude"
           });		
        addAnnotation
          (getSingleFeatureMatcherXML_FeaturePath(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "featurePath"
           });		
        addAnnotation
          (getSingleFeatureMatcherXML_FeatureTypeName(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "featureTypeName"
           });		
        addAnnotation
          (getSingleFeatureMatcherXML_Quiet(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "quiet"
           });		
        addAnnotation
          (targetAnnotationXMLEClass, 
           source, 
           new String[] 
           {
             "name", "TargetAnnotationXML",
             "kind", "elementOnly"
           });		
        addAnnotation
          (getTargetAnnotationXML_TargetAnnotationMatcher(), 
           source, 
           new String[] 
           {
             "kind", "element",
             "name", "targetAnnotationMatcher",
             "namespace", "##targetNamespace"
           });		
        addAnnotation
          (getTargetAnnotationXML_FeatureAnnotationMatchers(), 
           source, 
           new String[] 
           {
             "kind", "element",
             "name", "featureAnnotationMatchers",
             "namespace", "##targetNamespace"
           });			
        addAnnotation
          (getTargetAnnotationXML_ClassName(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "className"
           });		
        addAnnotation
          (getTargetAnnotationXML_EnclosingAnnotation(), 
           source, 
           new String[] 
           {
             "kind", "attribute",
             "name", "enclosingAnnotation"
           });
    }

} //ConfigPackageImpl
