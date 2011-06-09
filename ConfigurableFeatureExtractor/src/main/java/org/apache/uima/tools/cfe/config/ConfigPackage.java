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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.apache.uima.tools.cfe.config.ConfigFactory
 * @model kind="package"
 * @generated
 */
public interface ConfigPackage extends EPackage
{
  /**
     * The package name.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  String eNAME = "config";

  /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  String eNS_URI = "http://www.apache.org/uima/tools/cfe/config";

  /**
     * The package namespace name.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  String eNS_PREFIX = "config";

  /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  ConfigPackage eINSTANCE = org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl.init();

  /**
     * The meta object id for the '{@link org.apache.uima.tools.cfe.config.impl.BitsetFeatureValuesXMLImpl <em>Bitset Feature Values XML</em>}' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see org.apache.uima.tools.cfe.config.impl.BitsetFeatureValuesXMLImpl
     * @see org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl#getBitsetFeatureValuesXML()
     * @generated
     */
  int BITSET_FEATURE_VALUES_XML = 0;

  /**
     * The feature id for the '<em><b>Bitmask</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int BITSET_FEATURE_VALUES_XML__BITMASK = 0;

  /**
     * The feature id for the '<em><b>Exact Match</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int BITSET_FEATURE_VALUES_XML__EXACT_MATCH = 1;

  /**
     * The number of structural features of the '<em>Bitset Feature Values XML</em>' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int BITSET_FEATURE_VALUES_XML_FEATURE_COUNT = 2;

  /**
     * The meta object id for the '{@link org.apache.uima.tools.cfe.config.impl.CFEDescriptorXMLImpl <em>CFE Descriptor XML</em>}' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see org.apache.uima.tools.cfe.config.impl.CFEDescriptorXMLImpl
     * @see org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl#getCFEDescriptorXML()
     * @generated
     */
  int CFE_DESCRIPTOR_XML = 1;

  /**
     * The feature id for the '<em><b>Target Annotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int CFE_DESCRIPTOR_XML__TARGET_ANNOTATIONS = 0;

  /**
     * The feature id for the '<em><b>Null Value Image</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int CFE_DESCRIPTOR_XML__NULL_VALUE_IMAGE = 1;

  /**
     * The number of structural features of the '<em>CFE Descriptor XML</em>' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int CFE_DESCRIPTOR_XML_FEATURE_COUNT = 2;

  /**
     * The meta object id for the '{@link org.apache.uima.tools.cfe.config.impl.DocumentRootImpl <em>Document Root</em>}' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see org.apache.uima.tools.cfe.config.impl.DocumentRootImpl
     * @see org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl#getDocumentRoot()
     * @generated
     */
  int DOCUMENT_ROOT = 2;

  /**
     * The feature id for the '<em><b>Mixed</b></em>' attribute list.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int DOCUMENT_ROOT__MIXED = 0;

  /**
     * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

  /**
     * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

  /**
     * The feature id for the '<em><b>CFE Config</b></em>' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int DOCUMENT_ROOT__CFE_CONFIG = 3;

  /**
     * The number of structural features of the '<em>Document Root</em>' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int DOCUMENT_ROOT_FEATURE_COUNT = 4;

  /**
     * The meta object id for the '{@link org.apache.uima.tools.cfe.config.impl.EnumFeatureValuesXMLImpl <em>Enum Feature Values XML</em>}' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see org.apache.uima.tools.cfe.config.impl.EnumFeatureValuesXMLImpl
     * @see org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl#getEnumFeatureValuesXML()
     * @generated
     */
  int ENUM_FEATURE_VALUES_XML = 3;

  /**
     * The feature id for the '<em><b>Values</b></em>' attribute list.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int ENUM_FEATURE_VALUES_XML__VALUES = 0;

  /**
     * The feature id for the '<em><b>Case Sensitive</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int ENUM_FEATURE_VALUES_XML__CASE_SENSITIVE = 1;

  /**
     * The number of structural features of the '<em>Enum Feature Values XML</em>' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int ENUM_FEATURE_VALUES_XML_FEATURE_COUNT = 2;

  /**
     * The meta object id for the '{@link org.apache.uima.tools.cfe.config.impl.PartialObjectMatcherXMLImpl <em>Partial Object Matcher XML</em>}' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see org.apache.uima.tools.cfe.config.impl.PartialObjectMatcherXMLImpl
     * @see org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl#getPartialObjectMatcherXML()
     * @generated
     */
  int PARTIAL_OBJECT_MATCHER_XML = 7;

  /**
     * The feature id for the '<em><b>Group Feature Matchers</b></em>' containment reference list.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int PARTIAL_OBJECT_MATCHER_XML__GROUP_FEATURE_MATCHERS = 0;

  /**
     * The feature id for the '<em><b>Annotation Type Name</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int PARTIAL_OBJECT_MATCHER_XML__ANNOTATION_TYPE_NAME = 1;

  /**
     * The feature id for the '<em><b>Full Path</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int PARTIAL_OBJECT_MATCHER_XML__FULL_PATH = 2;

  /**
     * The number of structural features of the '<em>Partial Object Matcher XML</em>' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int PARTIAL_OBJECT_MATCHER_XML_FEATURE_COUNT = 3;

  /**
     * The meta object id for the '{@link org.apache.uima.tools.cfe.config.impl.FeatureObjectMatcherXMLImpl <em>Feature Object Matcher XML</em>}' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see org.apache.uima.tools.cfe.config.impl.FeatureObjectMatcherXMLImpl
     * @see org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl#getFeatureObjectMatcherXML()
     * @generated
     */
  int FEATURE_OBJECT_MATCHER_XML = 4;

  /**
     * The feature id for the '<em><b>Group Feature Matchers</b></em>' containment reference list.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int FEATURE_OBJECT_MATCHER_XML__GROUP_FEATURE_MATCHERS = PARTIAL_OBJECT_MATCHER_XML__GROUP_FEATURE_MATCHERS;

  /**
     * The feature id for the '<em><b>Annotation Type Name</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int FEATURE_OBJECT_MATCHER_XML__ANNOTATION_TYPE_NAME = PARTIAL_OBJECT_MATCHER_XML__ANNOTATION_TYPE_NAME;

  /**
     * The feature id for the '<em><b>Full Path</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int FEATURE_OBJECT_MATCHER_XML__FULL_PATH = PARTIAL_OBJECT_MATCHER_XML__FULL_PATH;

  /**
     * The feature id for the '<em><b>Distance</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int FEATURE_OBJECT_MATCHER_XML__DISTANCE = PARTIAL_OBJECT_MATCHER_XML_FEATURE_COUNT + 0;

  /**
     * The feature id for the '<em><b>Orientation</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int FEATURE_OBJECT_MATCHER_XML__ORIENTATION = PARTIAL_OBJECT_MATCHER_XML_FEATURE_COUNT + 1;

  /**
     * The feature id for the '<em><b>Window Flags</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int FEATURE_OBJECT_MATCHER_XML__WINDOW_FLAGS = PARTIAL_OBJECT_MATCHER_XML_FEATURE_COUNT + 2;

  /**
     * The feature id for the '<em><b>Windowsize Enclosed</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_ENCLOSED = PARTIAL_OBJECT_MATCHER_XML_FEATURE_COUNT + 3;

  /**
     * The feature id for the '<em><b>Windowsize Inside</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_INSIDE = PARTIAL_OBJECT_MATCHER_XML_FEATURE_COUNT + 4;

  /**
     * The feature id for the '<em><b>Windowsize Left</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_LEFT = PARTIAL_OBJECT_MATCHER_XML_FEATURE_COUNT + 5;

  /**
     * The feature id for the '<em><b>Windowsize Right</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_RIGHT = PARTIAL_OBJECT_MATCHER_XML_FEATURE_COUNT + 6;

  /**
     * The number of structural features of the '<em>Feature Object Matcher XML</em>' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int FEATURE_OBJECT_MATCHER_XML_FEATURE_COUNT = PARTIAL_OBJECT_MATCHER_XML_FEATURE_COUNT + 7;

  /**
     * The meta object id for the '{@link org.apache.uima.tools.cfe.config.impl.GroupFeatureMatcherXMLImpl <em>Group Feature Matcher XML</em>}' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see org.apache.uima.tools.cfe.config.impl.GroupFeatureMatcherXMLImpl
     * @see org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl#getGroupFeatureMatcherXML()
     * @generated
     */
  int GROUP_FEATURE_MATCHER_XML = 5;

  /**
     * The feature id for the '<em><b>Feature Matchers</b></em>' containment reference list.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int GROUP_FEATURE_MATCHER_XML__FEATURE_MATCHERS = 0;

  /**
     * The feature id for the '<em><b>Exclude</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int GROUP_FEATURE_MATCHER_XML__EXCLUDE = 1;

  /**
     * The number of structural features of the '<em>Group Feature Matcher XML</em>' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int GROUP_FEATURE_MATCHER_XML_FEATURE_COUNT = 2;

  /**
     * The meta object id for the '{@link org.apache.uima.tools.cfe.config.impl.ObjectPathFeatureValuesXMLImpl <em>Object Path Feature Values XML</em>}' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see org.apache.uima.tools.cfe.config.impl.ObjectPathFeatureValuesXMLImpl
     * @see org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl#getObjectPathFeatureValuesXML()
     * @generated
     */
  int OBJECT_PATH_FEATURE_VALUES_XML = 6;

  /**
     * The feature id for the '<em><b>Object Path</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int OBJECT_PATH_FEATURE_VALUES_XML__OBJECT_PATH = 0;

  /**
     * The number of structural features of the '<em>Object Path Feature Values XML</em>' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int OBJECT_PATH_FEATURE_VALUES_XML_FEATURE_COUNT = 1;

  /**
     * The meta object id for the '{@link org.apache.uima.tools.cfe.config.impl.PatternFeatureValuesXMLImpl <em>Pattern Feature Values XML</em>}' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see org.apache.uima.tools.cfe.config.impl.PatternFeatureValuesXMLImpl
     * @see org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl#getPatternFeatureValuesXML()
     * @generated
     */
  int PATTERN_FEATURE_VALUES_XML = 8;

  /**
     * The feature id for the '<em><b>Pattern</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int PATTERN_FEATURE_VALUES_XML__PATTERN = 0;

  /**
     * The number of structural features of the '<em>Pattern Feature Values XML</em>' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int PATTERN_FEATURE_VALUES_XML_FEATURE_COUNT = 1;

  /**
     * The meta object id for the '{@link org.apache.uima.tools.cfe.config.impl.RangeFeatureValuesXMLImpl <em>Range Feature Values XML</em>}' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see org.apache.uima.tools.cfe.config.impl.RangeFeatureValuesXMLImpl
     * @see org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl#getRangeFeatureValuesXML()
     * @generated
     */
  int RANGE_FEATURE_VALUES_XML = 9;

  /**
     * The feature id for the '<em><b>Lower Boundary</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int RANGE_FEATURE_VALUES_XML__LOWER_BOUNDARY = 0;

  /**
     * The feature id for the '<em><b>Lower Boundary Inclusive</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int RANGE_FEATURE_VALUES_XML__LOWER_BOUNDARY_INCLUSIVE = 1;

  /**
     * The feature id for the '<em><b>Upper Boundary</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int RANGE_FEATURE_VALUES_XML__UPPER_BOUNDARY = 2;

  /**
     * The feature id for the '<em><b>Upper Boundary Inclusive</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int RANGE_FEATURE_VALUES_XML__UPPER_BOUNDARY_INCLUSIVE = 3;

  /**
     * The number of structural features of the '<em>Range Feature Values XML</em>' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int RANGE_FEATURE_VALUES_XML_FEATURE_COUNT = 4;

  /**
     * The meta object id for the '{@link org.apache.uima.tools.cfe.config.impl.SingleFeatureMatcherXMLImpl <em>Single Feature Matcher XML</em>}' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see org.apache.uima.tools.cfe.config.impl.SingleFeatureMatcherXMLImpl
     * @see org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl#getSingleFeatureMatcherXML()
     * @generated
     */
  int SINGLE_FEATURE_MATCHER_XML = 10;

  /**
     * The feature id for the '<em><b>Range Feature Values</b></em>' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int SINGLE_FEATURE_MATCHER_XML__RANGE_FEATURE_VALUES = 0;

  /**
     * The feature id for the '<em><b>Enum Feature Values</b></em>' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int SINGLE_FEATURE_MATCHER_XML__ENUM_FEATURE_VALUES = 1;

  /**
     * The feature id for the '<em><b>Bitset Feature Values</b></em>' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int SINGLE_FEATURE_MATCHER_XML__BITSET_FEATURE_VALUES = 2;

  /**
     * The feature id for the '<em><b>Object Path Feature Values</b></em>' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int SINGLE_FEATURE_MATCHER_XML__OBJECT_PATH_FEATURE_VALUES = 3;

  /**
     * The feature id for the '<em><b>Pattern Feature Values</b></em>' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int SINGLE_FEATURE_MATCHER_XML__PATTERN_FEATURE_VALUES = 4;

  /**
     * The feature id for the '<em><b>Exclude</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int SINGLE_FEATURE_MATCHER_XML__EXCLUDE = 5;

  /**
     * The feature id for the '<em><b>Feature Path</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int SINGLE_FEATURE_MATCHER_XML__FEATURE_PATH = 6;

  /**
     * The feature id for the '<em><b>Feature Type Name</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int SINGLE_FEATURE_MATCHER_XML__FEATURE_TYPE_NAME = 7;

  /**
     * The feature id for the '<em><b>Quiet</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int SINGLE_FEATURE_MATCHER_XML__QUIET = 8;

  /**
     * The number of structural features of the '<em>Single Feature Matcher XML</em>' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int SINGLE_FEATURE_MATCHER_XML_FEATURE_COUNT = 9;

  /**
     * The meta object id for the '{@link org.apache.uima.tools.cfe.config.impl.TargetAnnotationXMLImpl <em>Target Annotation XML</em>}' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see org.apache.uima.tools.cfe.config.impl.TargetAnnotationXMLImpl
     * @see org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl#getTargetAnnotationXML()
     * @generated
     */
  int TARGET_ANNOTATION_XML = 11;

  /**
     * The feature id for the '<em><b>Target Annotation Matcher</b></em>' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int TARGET_ANNOTATION_XML__TARGET_ANNOTATION_MATCHER = 0;

  /**
     * The feature id for the '<em><b>Feature Annotation Matchers</b></em>' containment reference list.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int TARGET_ANNOTATION_XML__FEATURE_ANNOTATION_MATCHERS = 1;

  /**
     * The feature id for the '<em><b>Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int TARGET_ANNOTATION_XML__CLASS_NAME = 2;

  /**
     * The feature id for the '<em><b>Enclosing Annotation</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int TARGET_ANNOTATION_XML__ENCLOSING_ANNOTATION = 3;

  /**
     * The number of structural features of the '<em>Target Annotation XML</em>' class.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  int TARGET_ANNOTATION_XML_FEATURE_COUNT = 4;


  /**
     * Returns the meta object for class '{@link org.apache.uima.tools.cfe.config.BitsetFeatureValuesXML <em>Bitset Feature Values XML</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for class '<em>Bitset Feature Values XML</em>'.
     * @see org.apache.uima.tools.cfe.config.BitsetFeatureValuesXML
     * @generated
     */
  EClass getBitsetFeatureValuesXML();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.BitsetFeatureValuesXML#getBitmask <em>Bitmask</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Bitmask</em>'.
     * @see org.apache.uima.tools.cfe.config.BitsetFeatureValuesXML#getBitmask()
     * @see #getBitsetFeatureValuesXML()
     * @generated
     */
  EAttribute getBitsetFeatureValuesXML_Bitmask();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.BitsetFeatureValuesXML#isExactMatch <em>Exact Match</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Exact Match</em>'.
     * @see org.apache.uima.tools.cfe.config.BitsetFeatureValuesXML#isExactMatch()
     * @see #getBitsetFeatureValuesXML()
     * @generated
     */
  EAttribute getBitsetFeatureValuesXML_ExactMatch();

  /**
     * Returns the meta object for class '{@link org.apache.uima.tools.cfe.config.CFEDescriptorXML <em>CFE Descriptor XML</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for class '<em>CFE Descriptor XML</em>'.
     * @see org.apache.uima.tools.cfe.config.CFEDescriptorXML
     * @generated
     */
  EClass getCFEDescriptorXML();

  /**
     * Returns the meta object for the containment reference list '{@link org.apache.uima.tools.cfe.config.CFEDescriptorXML#getTargetAnnotations <em>Target Annotations</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Target Annotations</em>'.
     * @see org.apache.uima.tools.cfe.config.CFEDescriptorXML#getTargetAnnotations()
     * @see #getCFEDescriptorXML()
     * @generated
     */
  EReference getCFEDescriptorXML_TargetAnnotations();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.CFEDescriptorXML#getNullValueImage <em>Null Value Image</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Null Value Image</em>'.
     * @see org.apache.uima.tools.cfe.config.CFEDescriptorXML#getNullValueImage()
     * @see #getCFEDescriptorXML()
     * @generated
     */
  EAttribute getCFEDescriptorXML_NullValueImage();

  /**
     * Returns the meta object for class '{@link org.apache.uima.tools.cfe.config.DocumentRoot <em>Document Root</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for class '<em>Document Root</em>'.
     * @see org.apache.uima.tools.cfe.config.DocumentRoot
     * @generated
     */
  EClass getDocumentRoot();

  /**
     * Returns the meta object for the attribute list '{@link org.apache.uima.tools.cfe.config.DocumentRoot#getMixed <em>Mixed</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Mixed</em>'.
     * @see org.apache.uima.tools.cfe.config.DocumentRoot#getMixed()
     * @see #getDocumentRoot()
     * @generated
     */
  EAttribute getDocumentRoot_Mixed();

  /**
     * Returns the meta object for the map '{@link org.apache.uima.tools.cfe.config.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
     * @see org.apache.uima.tools.cfe.config.DocumentRoot#getXMLNSPrefixMap()
     * @see #getDocumentRoot()
     * @generated
     */
  EReference getDocumentRoot_XMLNSPrefixMap();

  /**
     * Returns the meta object for the map '{@link org.apache.uima.tools.cfe.config.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the map '<em>XSI Schema Location</em>'.
     * @see org.apache.uima.tools.cfe.config.DocumentRoot#getXSISchemaLocation()
     * @see #getDocumentRoot()
     * @generated
     */
  EReference getDocumentRoot_XSISchemaLocation();

  /**
     * Returns the meta object for the containment reference '{@link org.apache.uima.tools.cfe.config.DocumentRoot#getCFEConfig <em>CFE Config</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>CFE Config</em>'.
     * @see org.apache.uima.tools.cfe.config.DocumentRoot#getCFEConfig()
     * @see #getDocumentRoot()
     * @generated
     */
  EReference getDocumentRoot_CFEConfig();

  /**
     * Returns the meta object for class '{@link org.apache.uima.tools.cfe.config.EnumFeatureValuesXML <em>Enum Feature Values XML</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for class '<em>Enum Feature Values XML</em>'.
     * @see org.apache.uima.tools.cfe.config.EnumFeatureValuesXML
     * @generated
     */
  EClass getEnumFeatureValuesXML();

  /**
     * Returns the meta object for the attribute list '{@link org.apache.uima.tools.cfe.config.EnumFeatureValuesXML#getValues <em>Values</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Values</em>'.
     * @see org.apache.uima.tools.cfe.config.EnumFeatureValuesXML#getValues()
     * @see #getEnumFeatureValuesXML()
     * @generated
     */
  EAttribute getEnumFeatureValuesXML_Values();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.EnumFeatureValuesXML#isCaseSensitive <em>Case Sensitive</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Case Sensitive</em>'.
     * @see org.apache.uima.tools.cfe.config.EnumFeatureValuesXML#isCaseSensitive()
     * @see #getEnumFeatureValuesXML()
     * @generated
     */
  EAttribute getEnumFeatureValuesXML_CaseSensitive();

  /**
     * Returns the meta object for class '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML <em>Feature Object Matcher XML</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for class '<em>Feature Object Matcher XML</em>'.
     * @see org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML
     * @generated
     */
  EClass getFeatureObjectMatcherXML();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#isDistance <em>Distance</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Distance</em>'.
     * @see org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#isDistance()
     * @see #getFeatureObjectMatcherXML()
     * @generated
     */
  EAttribute getFeatureObjectMatcherXML_Distance();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#isOrientation <em>Orientation</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Orientation</em>'.
     * @see org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#isOrientation()
     * @see #getFeatureObjectMatcherXML()
     * @generated
     */
  EAttribute getFeatureObjectMatcherXML_Orientation();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowFlags <em>Window Flags</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Window Flags</em>'.
     * @see org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowFlags()
     * @see #getFeatureObjectMatcherXML()
     * @generated
     */
  EAttribute getFeatureObjectMatcherXML_WindowFlags();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowsizeEnclosed <em>Windowsize Enclosed</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Windowsize Enclosed</em>'.
     * @see org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowsizeEnclosed()
     * @see #getFeatureObjectMatcherXML()
     * @generated
     */
  EAttribute getFeatureObjectMatcherXML_WindowsizeEnclosed();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowsizeInside <em>Windowsize Inside</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Windowsize Inside</em>'.
     * @see org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowsizeInside()
     * @see #getFeatureObjectMatcherXML()
     * @generated
     */
  EAttribute getFeatureObjectMatcherXML_WindowsizeInside();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowsizeLeft <em>Windowsize Left</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Windowsize Left</em>'.
     * @see org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowsizeLeft()
     * @see #getFeatureObjectMatcherXML()
     * @generated
     */
  EAttribute getFeatureObjectMatcherXML_WindowsizeLeft();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowsizeRight <em>Windowsize Right</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Windowsize Right</em>'.
     * @see org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowsizeRight()
     * @see #getFeatureObjectMatcherXML()
     * @generated
     */
  EAttribute getFeatureObjectMatcherXML_WindowsizeRight();

  /**
     * Returns the meta object for class '{@link org.apache.uima.tools.cfe.config.GroupFeatureMatcherXML <em>Group Feature Matcher XML</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for class '<em>Group Feature Matcher XML</em>'.
     * @see org.apache.uima.tools.cfe.config.GroupFeatureMatcherXML
     * @generated
     */
  EClass getGroupFeatureMatcherXML();

  /**
     * Returns the meta object for the containment reference list '{@link org.apache.uima.tools.cfe.config.GroupFeatureMatcherXML#getFeatureMatchers <em>Feature Matchers</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Feature Matchers</em>'.
     * @see org.apache.uima.tools.cfe.config.GroupFeatureMatcherXML#getFeatureMatchers()
     * @see #getGroupFeatureMatcherXML()
     * @generated
     */
  EReference getGroupFeatureMatcherXML_FeatureMatchers();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.GroupFeatureMatcherXML#isExclude <em>Exclude</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Exclude</em>'.
     * @see org.apache.uima.tools.cfe.config.GroupFeatureMatcherXML#isExclude()
     * @see #getGroupFeatureMatcherXML()
     * @generated
     */
  EAttribute getGroupFeatureMatcherXML_Exclude();

  /**
     * Returns the meta object for class '{@link org.apache.uima.tools.cfe.config.ObjectPathFeatureValuesXML <em>Object Path Feature Values XML</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for class '<em>Object Path Feature Values XML</em>'.
     * @see org.apache.uima.tools.cfe.config.ObjectPathFeatureValuesXML
     * @generated
     */
  EClass getObjectPathFeatureValuesXML();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.ObjectPathFeatureValuesXML#getObjectPath <em>Object Path</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Object Path</em>'.
     * @see org.apache.uima.tools.cfe.config.ObjectPathFeatureValuesXML#getObjectPath()
     * @see #getObjectPathFeatureValuesXML()
     * @generated
     */
  EAttribute getObjectPathFeatureValuesXML_ObjectPath();

  /**
     * Returns the meta object for class '{@link org.apache.uima.tools.cfe.config.PartialObjectMatcherXML <em>Partial Object Matcher XML</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for class '<em>Partial Object Matcher XML</em>'.
     * @see org.apache.uima.tools.cfe.config.PartialObjectMatcherXML
     * @generated
     */
  EClass getPartialObjectMatcherXML();

  /**
     * Returns the meta object for the containment reference list '{@link org.apache.uima.tools.cfe.config.PartialObjectMatcherXML#getGroupFeatureMatchers <em>Group Feature Matchers</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Group Feature Matchers</em>'.
     * @see org.apache.uima.tools.cfe.config.PartialObjectMatcherXML#getGroupFeatureMatchers()
     * @see #getPartialObjectMatcherXML()
     * @generated
     */
  EReference getPartialObjectMatcherXML_GroupFeatureMatchers();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.PartialObjectMatcherXML#getAnnotationTypeName <em>Annotation Type Name</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Annotation Type Name</em>'.
     * @see org.apache.uima.tools.cfe.config.PartialObjectMatcherXML#getAnnotationTypeName()
     * @see #getPartialObjectMatcherXML()
     * @generated
     */
  EAttribute getPartialObjectMatcherXML_AnnotationTypeName();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.PartialObjectMatcherXML#getFullPath <em>Full Path</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Full Path</em>'.
     * @see org.apache.uima.tools.cfe.config.PartialObjectMatcherXML#getFullPath()
     * @see #getPartialObjectMatcherXML()
     * @generated
     */
  EAttribute getPartialObjectMatcherXML_FullPath();

  /**
     * Returns the meta object for class '{@link org.apache.uima.tools.cfe.config.PatternFeatureValuesXML <em>Pattern Feature Values XML</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for class '<em>Pattern Feature Values XML</em>'.
     * @see org.apache.uima.tools.cfe.config.PatternFeatureValuesXML
     * @generated
     */
  EClass getPatternFeatureValuesXML();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.PatternFeatureValuesXML#getPattern <em>Pattern</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Pattern</em>'.
     * @see org.apache.uima.tools.cfe.config.PatternFeatureValuesXML#getPattern()
     * @see #getPatternFeatureValuesXML()
     * @generated
     */
  EAttribute getPatternFeatureValuesXML_Pattern();

  /**
     * Returns the meta object for class '{@link org.apache.uima.tools.cfe.config.RangeFeatureValuesXML <em>Range Feature Values XML</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for class '<em>Range Feature Values XML</em>'.
     * @see org.apache.uima.tools.cfe.config.RangeFeatureValuesXML
     * @generated
     */
  EClass getRangeFeatureValuesXML();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.RangeFeatureValuesXML#getLowerBoundary <em>Lower Boundary</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Lower Boundary</em>'.
     * @see org.apache.uima.tools.cfe.config.RangeFeatureValuesXML#getLowerBoundary()
     * @see #getRangeFeatureValuesXML()
     * @generated
     */
  EAttribute getRangeFeatureValuesXML_LowerBoundary();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.RangeFeatureValuesXML#isLowerBoundaryInclusive <em>Lower Boundary Inclusive</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Lower Boundary Inclusive</em>'.
     * @see org.apache.uima.tools.cfe.config.RangeFeatureValuesXML#isLowerBoundaryInclusive()
     * @see #getRangeFeatureValuesXML()
     * @generated
     */
  EAttribute getRangeFeatureValuesXML_LowerBoundaryInclusive();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.RangeFeatureValuesXML#getUpperBoundary <em>Upper Boundary</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Upper Boundary</em>'.
     * @see org.apache.uima.tools.cfe.config.RangeFeatureValuesXML#getUpperBoundary()
     * @see #getRangeFeatureValuesXML()
     * @generated
     */
  EAttribute getRangeFeatureValuesXML_UpperBoundary();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.RangeFeatureValuesXML#isUpperBoundaryInclusive <em>Upper Boundary Inclusive</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Upper Boundary Inclusive</em>'.
     * @see org.apache.uima.tools.cfe.config.RangeFeatureValuesXML#isUpperBoundaryInclusive()
     * @see #getRangeFeatureValuesXML()
     * @generated
     */
  EAttribute getRangeFeatureValuesXML_UpperBoundaryInclusive();

  /**
     * Returns the meta object for class '{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML <em>Single Feature Matcher XML</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for class '<em>Single Feature Matcher XML</em>'.
     * @see org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML
     * @generated
     */
  EClass getSingleFeatureMatcherXML();

  /**
     * Returns the meta object for the containment reference '{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getRangeFeatureValues <em>Range Feature Values</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Range Feature Values</em>'.
     * @see org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getRangeFeatureValues()
     * @see #getSingleFeatureMatcherXML()
     * @generated
     */
  EReference getSingleFeatureMatcherXML_RangeFeatureValues();

  /**
     * Returns the meta object for the containment reference '{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getEnumFeatureValues <em>Enum Feature Values</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Enum Feature Values</em>'.
     * @see org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getEnumFeatureValues()
     * @see #getSingleFeatureMatcherXML()
     * @generated
     */
  EReference getSingleFeatureMatcherXML_EnumFeatureValues();

  /**
     * Returns the meta object for the containment reference '{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getBitsetFeatureValues <em>Bitset Feature Values</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Bitset Feature Values</em>'.
     * @see org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getBitsetFeatureValues()
     * @see #getSingleFeatureMatcherXML()
     * @generated
     */
  EReference getSingleFeatureMatcherXML_BitsetFeatureValues();

  /**
     * Returns the meta object for the containment reference '{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getObjectPathFeatureValues <em>Object Path Feature Values</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Object Path Feature Values</em>'.
     * @see org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getObjectPathFeatureValues()
     * @see #getSingleFeatureMatcherXML()
     * @generated
     */
  EReference getSingleFeatureMatcherXML_ObjectPathFeatureValues();

  /**
     * Returns the meta object for the containment reference '{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getPatternFeatureValues <em>Pattern Feature Values</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Pattern Feature Values</em>'.
     * @see org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getPatternFeatureValues()
     * @see #getSingleFeatureMatcherXML()
     * @generated
     */
  EReference getSingleFeatureMatcherXML_PatternFeatureValues();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#isExclude <em>Exclude</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Exclude</em>'.
     * @see org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#isExclude()
     * @see #getSingleFeatureMatcherXML()
     * @generated
     */
  EAttribute getSingleFeatureMatcherXML_Exclude();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getFeaturePath <em>Feature Path</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Feature Path</em>'.
     * @see org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getFeaturePath()
     * @see #getSingleFeatureMatcherXML()
     * @generated
     */
  EAttribute getSingleFeatureMatcherXML_FeaturePath();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getFeatureTypeName <em>Feature Type Name</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Feature Type Name</em>'.
     * @see org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#getFeatureTypeName()
     * @see #getSingleFeatureMatcherXML()
     * @generated
     */
  EAttribute getSingleFeatureMatcherXML_FeatureTypeName();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#isQuiet <em>Quiet</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Quiet</em>'.
     * @see org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML#isQuiet()
     * @see #getSingleFeatureMatcherXML()
     * @generated
     */
  EAttribute getSingleFeatureMatcherXML_Quiet();

  /**
     * Returns the meta object for class '{@link org.apache.uima.tools.cfe.config.TargetAnnotationXML <em>Target Annotation XML</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for class '<em>Target Annotation XML</em>'.
     * @see org.apache.uima.tools.cfe.config.TargetAnnotationXML
     * @generated
     */
  EClass getTargetAnnotationXML();

  /**
     * Returns the meta object for the containment reference '{@link org.apache.uima.tools.cfe.config.TargetAnnotationXML#getTargetAnnotationMatcher <em>Target Annotation Matcher</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Target Annotation Matcher</em>'.
     * @see org.apache.uima.tools.cfe.config.TargetAnnotationXML#getTargetAnnotationMatcher()
     * @see #getTargetAnnotationXML()
     * @generated
     */
  EReference getTargetAnnotationXML_TargetAnnotationMatcher();

  /**
     * Returns the meta object for the containment reference list '{@link org.apache.uima.tools.cfe.config.TargetAnnotationXML#getFeatureAnnotationMatchers <em>Feature Annotation Matchers</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Feature Annotation Matchers</em>'.
     * @see org.apache.uima.tools.cfe.config.TargetAnnotationXML#getFeatureAnnotationMatchers()
     * @see #getTargetAnnotationXML()
     * @generated
     */
  EReference getTargetAnnotationXML_FeatureAnnotationMatchers();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.TargetAnnotationXML#getClassName <em>Class Name</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Class Name</em>'.
     * @see org.apache.uima.tools.cfe.config.TargetAnnotationXML#getClassName()
     * @see #getTargetAnnotationXML()
     * @generated
     */
  EAttribute getTargetAnnotationXML_ClassName();

  /**
     * Returns the meta object for the attribute '{@link org.apache.uima.tools.cfe.config.TargetAnnotationXML#getEnclosingAnnotation <em>Enclosing Annotation</em>}'.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Enclosing Annotation</em>'.
     * @see org.apache.uima.tools.cfe.config.TargetAnnotationXML#getEnclosingAnnotation()
     * @see #getTargetAnnotationXML()
     * @generated
     */
  EAttribute getTargetAnnotationXML_EnclosingAnnotation();

  /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
  ConfigFactory getConfigFactory();

  /**
     * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
     * @generated
     */
  interface Literals
  {
    /**
         * The meta object literal for the '{@link org.apache.uima.tools.cfe.config.impl.BitsetFeatureValuesXMLImpl <em>Bitset Feature Values XML</em>}' class.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @see org.apache.uima.tools.cfe.config.impl.BitsetFeatureValuesXMLImpl
         * @see org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl#getBitsetFeatureValuesXML()
         * @generated
         */
    EClass BITSET_FEATURE_VALUES_XML = eINSTANCE.getBitsetFeatureValuesXML();

    /**
         * The meta object literal for the '<em><b>Bitmask</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute BITSET_FEATURE_VALUES_XML__BITMASK = eINSTANCE.getBitsetFeatureValuesXML_Bitmask();

    /**
         * The meta object literal for the '<em><b>Exact Match</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute BITSET_FEATURE_VALUES_XML__EXACT_MATCH = eINSTANCE.getBitsetFeatureValuesXML_ExactMatch();

    /**
         * The meta object literal for the '{@link org.apache.uima.tools.cfe.config.impl.CFEDescriptorXMLImpl <em>CFE Descriptor XML</em>}' class.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @see org.apache.uima.tools.cfe.config.impl.CFEDescriptorXMLImpl
         * @see org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl#getCFEDescriptorXML()
         * @generated
         */
    EClass CFE_DESCRIPTOR_XML = eINSTANCE.getCFEDescriptorXML();

    /**
         * The meta object literal for the '<em><b>Target Annotations</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EReference CFE_DESCRIPTOR_XML__TARGET_ANNOTATIONS = eINSTANCE.getCFEDescriptorXML_TargetAnnotations();

    /**
         * The meta object literal for the '<em><b>Null Value Image</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute CFE_DESCRIPTOR_XML__NULL_VALUE_IMAGE = eINSTANCE.getCFEDescriptorXML_NullValueImage();

    /**
         * The meta object literal for the '{@link org.apache.uima.tools.cfe.config.impl.DocumentRootImpl <em>Document Root</em>}' class.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @see org.apache.uima.tools.cfe.config.impl.DocumentRootImpl
         * @see org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl#getDocumentRoot()
         * @generated
         */
    EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

    /**
         * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

    /**
         * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

    /**
         * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

    /**
         * The meta object literal for the '<em><b>CFE Config</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EReference DOCUMENT_ROOT__CFE_CONFIG = eINSTANCE.getDocumentRoot_CFEConfig();

    /**
         * The meta object literal for the '{@link org.apache.uima.tools.cfe.config.impl.EnumFeatureValuesXMLImpl <em>Enum Feature Values XML</em>}' class.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @see org.apache.uima.tools.cfe.config.impl.EnumFeatureValuesXMLImpl
         * @see org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl#getEnumFeatureValuesXML()
         * @generated
         */
    EClass ENUM_FEATURE_VALUES_XML = eINSTANCE.getEnumFeatureValuesXML();

    /**
         * The meta object literal for the '<em><b>Values</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute ENUM_FEATURE_VALUES_XML__VALUES = eINSTANCE.getEnumFeatureValuesXML_Values();

    /**
         * The meta object literal for the '<em><b>Case Sensitive</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute ENUM_FEATURE_VALUES_XML__CASE_SENSITIVE = eINSTANCE.getEnumFeatureValuesXML_CaseSensitive();

    /**
         * The meta object literal for the '{@link org.apache.uima.tools.cfe.config.impl.FeatureObjectMatcherXMLImpl <em>Feature Object Matcher XML</em>}' class.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @see org.apache.uima.tools.cfe.config.impl.FeatureObjectMatcherXMLImpl
         * @see org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl#getFeatureObjectMatcherXML()
         * @generated
         */
    EClass FEATURE_OBJECT_MATCHER_XML = eINSTANCE.getFeatureObjectMatcherXML();

    /**
         * The meta object literal for the '<em><b>Distance</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute FEATURE_OBJECT_MATCHER_XML__DISTANCE = eINSTANCE.getFeatureObjectMatcherXML_Distance();

    /**
         * The meta object literal for the '<em><b>Orientation</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute FEATURE_OBJECT_MATCHER_XML__ORIENTATION = eINSTANCE.getFeatureObjectMatcherXML_Orientation();

    /**
         * The meta object literal for the '<em><b>Window Flags</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute FEATURE_OBJECT_MATCHER_XML__WINDOW_FLAGS = eINSTANCE.getFeatureObjectMatcherXML_WindowFlags();

    /**
         * The meta object literal for the '<em><b>Windowsize Enclosed</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_ENCLOSED = eINSTANCE.getFeatureObjectMatcherXML_WindowsizeEnclosed();

    /**
         * The meta object literal for the '<em><b>Windowsize Inside</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_INSIDE = eINSTANCE.getFeatureObjectMatcherXML_WindowsizeInside();

    /**
         * The meta object literal for the '<em><b>Windowsize Left</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_LEFT = eINSTANCE.getFeatureObjectMatcherXML_WindowsizeLeft();

    /**
         * The meta object literal for the '<em><b>Windowsize Right</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_RIGHT = eINSTANCE.getFeatureObjectMatcherXML_WindowsizeRight();

    /**
         * The meta object literal for the '{@link org.apache.uima.tools.cfe.config.impl.GroupFeatureMatcherXMLImpl <em>Group Feature Matcher XML</em>}' class.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @see org.apache.uima.tools.cfe.config.impl.GroupFeatureMatcherXMLImpl
         * @see org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl#getGroupFeatureMatcherXML()
         * @generated
         */
    EClass GROUP_FEATURE_MATCHER_XML = eINSTANCE.getGroupFeatureMatcherXML();

    /**
         * The meta object literal for the '<em><b>Feature Matchers</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EReference GROUP_FEATURE_MATCHER_XML__FEATURE_MATCHERS = eINSTANCE.getGroupFeatureMatcherXML_FeatureMatchers();

    /**
         * The meta object literal for the '<em><b>Exclude</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute GROUP_FEATURE_MATCHER_XML__EXCLUDE = eINSTANCE.getGroupFeatureMatcherXML_Exclude();

    /**
         * The meta object literal for the '{@link org.apache.uima.tools.cfe.config.impl.ObjectPathFeatureValuesXMLImpl <em>Object Path Feature Values XML</em>}' class.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @see org.apache.uima.tools.cfe.config.impl.ObjectPathFeatureValuesXMLImpl
         * @see org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl#getObjectPathFeatureValuesXML()
         * @generated
         */
    EClass OBJECT_PATH_FEATURE_VALUES_XML = eINSTANCE.getObjectPathFeatureValuesXML();

    /**
         * The meta object literal for the '<em><b>Object Path</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute OBJECT_PATH_FEATURE_VALUES_XML__OBJECT_PATH = eINSTANCE.getObjectPathFeatureValuesXML_ObjectPath();

    /**
         * The meta object literal for the '{@link org.apache.uima.tools.cfe.config.impl.PartialObjectMatcherXMLImpl <em>Partial Object Matcher XML</em>}' class.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @see org.apache.uima.tools.cfe.config.impl.PartialObjectMatcherXMLImpl
         * @see org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl#getPartialObjectMatcherXML()
         * @generated
         */
    EClass PARTIAL_OBJECT_MATCHER_XML = eINSTANCE.getPartialObjectMatcherXML();

    /**
         * The meta object literal for the '<em><b>Group Feature Matchers</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EReference PARTIAL_OBJECT_MATCHER_XML__GROUP_FEATURE_MATCHERS = eINSTANCE.getPartialObjectMatcherXML_GroupFeatureMatchers();

    /**
         * The meta object literal for the '<em><b>Annotation Type Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute PARTIAL_OBJECT_MATCHER_XML__ANNOTATION_TYPE_NAME = eINSTANCE.getPartialObjectMatcherXML_AnnotationTypeName();

    /**
         * The meta object literal for the '<em><b>Full Path</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute PARTIAL_OBJECT_MATCHER_XML__FULL_PATH = eINSTANCE.getPartialObjectMatcherXML_FullPath();

    /**
         * The meta object literal for the '{@link org.apache.uima.tools.cfe.config.impl.PatternFeatureValuesXMLImpl <em>Pattern Feature Values XML</em>}' class.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @see org.apache.uima.tools.cfe.config.impl.PatternFeatureValuesXMLImpl
         * @see org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl#getPatternFeatureValuesXML()
         * @generated
         */
    EClass PATTERN_FEATURE_VALUES_XML = eINSTANCE.getPatternFeatureValuesXML();

    /**
         * The meta object literal for the '<em><b>Pattern</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute PATTERN_FEATURE_VALUES_XML__PATTERN = eINSTANCE.getPatternFeatureValuesXML_Pattern();

    /**
         * The meta object literal for the '{@link org.apache.uima.tools.cfe.config.impl.RangeFeatureValuesXMLImpl <em>Range Feature Values XML</em>}' class.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @see org.apache.uima.tools.cfe.config.impl.RangeFeatureValuesXMLImpl
         * @see org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl#getRangeFeatureValuesXML()
         * @generated
         */
    EClass RANGE_FEATURE_VALUES_XML = eINSTANCE.getRangeFeatureValuesXML();

    /**
         * The meta object literal for the '<em><b>Lower Boundary</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute RANGE_FEATURE_VALUES_XML__LOWER_BOUNDARY = eINSTANCE.getRangeFeatureValuesXML_LowerBoundary();

    /**
         * The meta object literal for the '<em><b>Lower Boundary Inclusive</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute RANGE_FEATURE_VALUES_XML__LOWER_BOUNDARY_INCLUSIVE = eINSTANCE.getRangeFeatureValuesXML_LowerBoundaryInclusive();

    /**
         * The meta object literal for the '<em><b>Upper Boundary</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute RANGE_FEATURE_VALUES_XML__UPPER_BOUNDARY = eINSTANCE.getRangeFeatureValuesXML_UpperBoundary();

    /**
         * The meta object literal for the '<em><b>Upper Boundary Inclusive</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute RANGE_FEATURE_VALUES_XML__UPPER_BOUNDARY_INCLUSIVE = eINSTANCE.getRangeFeatureValuesXML_UpperBoundaryInclusive();

    /**
         * The meta object literal for the '{@link org.apache.uima.tools.cfe.config.impl.SingleFeatureMatcherXMLImpl <em>Single Feature Matcher XML</em>}' class.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @see org.apache.uima.tools.cfe.config.impl.SingleFeatureMatcherXMLImpl
         * @see org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl#getSingleFeatureMatcherXML()
         * @generated
         */
    EClass SINGLE_FEATURE_MATCHER_XML = eINSTANCE.getSingleFeatureMatcherXML();

    /**
         * The meta object literal for the '<em><b>Range Feature Values</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EReference SINGLE_FEATURE_MATCHER_XML__RANGE_FEATURE_VALUES = eINSTANCE.getSingleFeatureMatcherXML_RangeFeatureValues();

    /**
         * The meta object literal for the '<em><b>Enum Feature Values</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EReference SINGLE_FEATURE_MATCHER_XML__ENUM_FEATURE_VALUES = eINSTANCE.getSingleFeatureMatcherXML_EnumFeatureValues();

    /**
         * The meta object literal for the '<em><b>Bitset Feature Values</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EReference SINGLE_FEATURE_MATCHER_XML__BITSET_FEATURE_VALUES = eINSTANCE.getSingleFeatureMatcherXML_BitsetFeatureValues();

    /**
         * The meta object literal for the '<em><b>Object Path Feature Values</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EReference SINGLE_FEATURE_MATCHER_XML__OBJECT_PATH_FEATURE_VALUES = eINSTANCE.getSingleFeatureMatcherXML_ObjectPathFeatureValues();

    /**
         * The meta object literal for the '<em><b>Pattern Feature Values</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EReference SINGLE_FEATURE_MATCHER_XML__PATTERN_FEATURE_VALUES = eINSTANCE.getSingleFeatureMatcherXML_PatternFeatureValues();

    /**
         * The meta object literal for the '<em><b>Exclude</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute SINGLE_FEATURE_MATCHER_XML__EXCLUDE = eINSTANCE.getSingleFeatureMatcherXML_Exclude();

    /**
         * The meta object literal for the '<em><b>Feature Path</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute SINGLE_FEATURE_MATCHER_XML__FEATURE_PATH = eINSTANCE.getSingleFeatureMatcherXML_FeaturePath();

    /**
         * The meta object literal for the '<em><b>Feature Type Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute SINGLE_FEATURE_MATCHER_XML__FEATURE_TYPE_NAME = eINSTANCE.getSingleFeatureMatcherXML_FeatureTypeName();

    /**
         * The meta object literal for the '<em><b>Quiet</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute SINGLE_FEATURE_MATCHER_XML__QUIET = eINSTANCE.getSingleFeatureMatcherXML_Quiet();

    /**
         * The meta object literal for the '{@link org.apache.uima.tools.cfe.config.impl.TargetAnnotationXMLImpl <em>Target Annotation XML</em>}' class.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @see org.apache.uima.tools.cfe.config.impl.TargetAnnotationXMLImpl
         * @see org.apache.uima.tools.cfe.config.impl.ConfigPackageImpl#getTargetAnnotationXML()
         * @generated
         */
    EClass TARGET_ANNOTATION_XML = eINSTANCE.getTargetAnnotationXML();

    /**
         * The meta object literal for the '<em><b>Target Annotation Matcher</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EReference TARGET_ANNOTATION_XML__TARGET_ANNOTATION_MATCHER = eINSTANCE.getTargetAnnotationXML_TargetAnnotationMatcher();

    /**
         * The meta object literal for the '<em><b>Feature Annotation Matchers</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EReference TARGET_ANNOTATION_XML__FEATURE_ANNOTATION_MATCHERS = eINSTANCE.getTargetAnnotationXML_FeatureAnnotationMatchers();

    /**
         * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute TARGET_ANNOTATION_XML__CLASS_NAME = eINSTANCE.getTargetAnnotationXML_ClassName();

    /**
         * The meta object literal for the '<em><b>Enclosing Annotation</b></em>' attribute feature.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    EAttribute TARGET_ANNOTATION_XML__ENCLOSING_ANNOTATION = eINSTANCE.getTargetAnnotationXML_EnclosingAnnotation();

  }

} //ConfigPackage
