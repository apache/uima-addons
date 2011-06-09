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

import java.util.List;

import org.apache.uima.tools.cfe.config.BitsetFeatureValuesXML;
import org.apache.uima.tools.cfe.config.CFEDescriptorXML;
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

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.apache.uima.tools.cfe.config.ConfigPackage
 * @generated
 */
public class ConfigSwitch<T>
{
  /**
     * The cached model package
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected static ConfigPackage modelPackage;

  /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public ConfigSwitch()
  {
        if (modelPackage == null)
        {
            modelPackage = ConfigPackage.eINSTANCE;
        }
    }

  /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
  public T doSwitch(EObject theEObject)
  {
        return doSwitch(theEObject.eClass(), theEObject);
    }

  /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
  protected T doSwitch(EClass theEClass, EObject theEObject)
  {
        if (theEClass.eContainer() == modelPackage)
        {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        }
        else
        {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return
                eSuperTypes.isEmpty() ?
                    defaultCase(theEObject) :
                    doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

  /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
  protected T doSwitch(int classifierID, EObject theEObject)
  {
        switch (classifierID)
        {
            case ConfigPackage.BITSET_FEATURE_VALUES_XML:
            {
                BitsetFeatureValuesXML bitsetFeatureValuesXML = (BitsetFeatureValuesXML)theEObject;
                T result = caseBitsetFeatureValuesXML(bitsetFeatureValuesXML);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConfigPackage.CFE_DESCRIPTOR_XML:
            {
                CFEDescriptorXML cfeDescriptorXML = (CFEDescriptorXML)theEObject;
                T result = caseCFEDescriptorXML(cfeDescriptorXML);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConfigPackage.DOCUMENT_ROOT:
            {
                DocumentRoot documentRoot = (DocumentRoot)theEObject;
                T result = caseDocumentRoot(documentRoot);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConfigPackage.ENUM_FEATURE_VALUES_XML:
            {
                EnumFeatureValuesXML enumFeatureValuesXML = (EnumFeatureValuesXML)theEObject;
                T result = caseEnumFeatureValuesXML(enumFeatureValuesXML);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML:
            {
                FeatureObjectMatcherXML featureObjectMatcherXML = (FeatureObjectMatcherXML)theEObject;
                T result = caseFeatureObjectMatcherXML(featureObjectMatcherXML);
                if (result == null) result = casePartialObjectMatcherXML(featureObjectMatcherXML);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConfigPackage.GROUP_FEATURE_MATCHER_XML:
            {
                GroupFeatureMatcherXML groupFeatureMatcherXML = (GroupFeatureMatcherXML)theEObject;
                T result = caseGroupFeatureMatcherXML(groupFeatureMatcherXML);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConfigPackage.OBJECT_PATH_FEATURE_VALUES_XML:
            {
                ObjectPathFeatureValuesXML objectPathFeatureValuesXML = (ObjectPathFeatureValuesXML)theEObject;
                T result = caseObjectPathFeatureValuesXML(objectPathFeatureValuesXML);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConfigPackage.PARTIAL_OBJECT_MATCHER_XML:
            {
                PartialObjectMatcherXML partialObjectMatcherXML = (PartialObjectMatcherXML)theEObject;
                T result = casePartialObjectMatcherXML(partialObjectMatcherXML);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConfigPackage.PATTERN_FEATURE_VALUES_XML:
            {
                PatternFeatureValuesXML patternFeatureValuesXML = (PatternFeatureValuesXML)theEObject;
                T result = casePatternFeatureValuesXML(patternFeatureValuesXML);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConfigPackage.RANGE_FEATURE_VALUES_XML:
            {
                RangeFeatureValuesXML rangeFeatureValuesXML = (RangeFeatureValuesXML)theEObject;
                T result = caseRangeFeatureValuesXML(rangeFeatureValuesXML);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML:
            {
                SingleFeatureMatcherXML singleFeatureMatcherXML = (SingleFeatureMatcherXML)theEObject;
                T result = caseSingleFeatureMatcherXML(singleFeatureMatcherXML);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ConfigPackage.TARGET_ANNOTATION_XML:
            {
                TargetAnnotationXML targetAnnotationXML = (TargetAnnotationXML)theEObject;
                T result = caseTargetAnnotationXML(targetAnnotationXML);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

  /**
     * Returns the result of interpreting the object as an instance of '<em>Bitset Feature Values XML</em>'.
     * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Bitset Feature Values XML</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
  public T caseBitsetFeatureValuesXML(BitsetFeatureValuesXML object)
  {
        return null;
    }

  /**
     * Returns the result of interpreting the object as an instance of '<em>CFE Descriptor XML</em>'.
     * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>CFE Descriptor XML</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
  public T caseCFEDescriptorXML(CFEDescriptorXML object)
  {
        return null;
    }

  /**
     * Returns the result of interpreting the object as an instance of '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Document Root</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
  public T caseDocumentRoot(DocumentRoot object)
  {
        return null;
    }

  /**
     * Returns the result of interpreting the object as an instance of '<em>Enum Feature Values XML</em>'.
     * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Enum Feature Values XML</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
  public T caseEnumFeatureValuesXML(EnumFeatureValuesXML object)
  {
        return null;
    }

  /**
     * Returns the result of interpreting the object as an instance of '<em>Feature Object Matcher XML</em>'.
     * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Feature Object Matcher XML</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
  public T caseFeatureObjectMatcherXML(FeatureObjectMatcherXML object)
  {
        return null;
    }

  /**
     * Returns the result of interpreting the object as an instance of '<em>Group Feature Matcher XML</em>'.
     * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Group Feature Matcher XML</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
  public T caseGroupFeatureMatcherXML(GroupFeatureMatcherXML object)
  {
        return null;
    }

  /**
     * Returns the result of interpreting the object as an instance of '<em>Object Path Feature Values XML</em>'.
     * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Object Path Feature Values XML</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
  public T caseObjectPathFeatureValuesXML(ObjectPathFeatureValuesXML object)
  {
        return null;
    }

  /**
     * Returns the result of interpreting the object as an instance of '<em>Partial Object Matcher XML</em>'.
     * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Partial Object Matcher XML</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
  public T casePartialObjectMatcherXML(PartialObjectMatcherXML object)
  {
        return null;
    }

  /**
     * Returns the result of interpreting the object as an instance of '<em>Pattern Feature Values XML</em>'.
     * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Pattern Feature Values XML</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
  public T casePatternFeatureValuesXML(PatternFeatureValuesXML object)
  {
        return null;
    }

  /**
     * Returns the result of interpreting the object as an instance of '<em>Range Feature Values XML</em>'.
     * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Range Feature Values XML</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
  public T caseRangeFeatureValuesXML(RangeFeatureValuesXML object)
  {
        return null;
    }

  /**
     * Returns the result of interpreting the object as an instance of '<em>Single Feature Matcher XML</em>'.
     * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Single Feature Matcher XML</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
  public T caseSingleFeatureMatcherXML(SingleFeatureMatcherXML object)
  {
        return null;
    }

  /**
     * Returns the result of interpreting the object as an instance of '<em>Target Annotation XML</em>'.
     * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Target Annotation XML</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
  public T caseTargetAnnotationXML(TargetAnnotationXML object)
  {
        return null;
    }

  /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
  public T defaultCase(EObject object)
  {
        return null;
    }

} //ConfigSwitch
