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
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.apache.uima.tools.cfe.config.ConfigPackage
 * @generated
 */
public class ConfigAdapterFactory extends AdapterFactoryImpl
{
  /**
     * The cached model package.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected static ConfigPackage modelPackage;

  /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public ConfigAdapterFactory()
  {
        if (modelPackage == null)
        {
            modelPackage = ConfigPackage.eINSTANCE;
        }
    }

  /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
  @Override
public boolean isFactoryForType(Object object)
  {
        if (object == modelPackage)
        {
            return true;
        }
        if (object instanceof EObject)
        {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

  /**
     * The switch the delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected ConfigSwitch<Adapter> modelSwitch =
    new ConfigSwitch<Adapter>()
        {
            @Override
            public Adapter caseBitsetFeatureValuesXML(BitsetFeatureValuesXML object)
            {
                return createBitsetFeatureValuesXMLAdapter();
            }
            @Override
            public Adapter caseCFEDescriptorXML(CFEDescriptorXML object)
            {
                return createCFEDescriptorXMLAdapter();
            }
            @Override
            public Adapter caseDocumentRoot(DocumentRoot object)
            {
                return createDocumentRootAdapter();
            }
            @Override
            public Adapter caseEnumFeatureValuesXML(EnumFeatureValuesXML object)
            {
                return createEnumFeatureValuesXMLAdapter();
            }
            @Override
            public Adapter caseFeatureObjectMatcherXML(FeatureObjectMatcherXML object)
            {
                return createFeatureObjectMatcherXMLAdapter();
            }
            @Override
            public Adapter caseGroupFeatureMatcherXML(GroupFeatureMatcherXML object)
            {
                return createGroupFeatureMatcherXMLAdapter();
            }
            @Override
            public Adapter caseObjectPathFeatureValuesXML(ObjectPathFeatureValuesXML object)
            {
                return createObjectPathFeatureValuesXMLAdapter();
            }
            @Override
            public Adapter casePartialObjectMatcherXML(PartialObjectMatcherXML object)
            {
                return createPartialObjectMatcherXMLAdapter();
            }
            @Override
            public Adapter casePatternFeatureValuesXML(PatternFeatureValuesXML object)
            {
                return createPatternFeatureValuesXMLAdapter();
            }
            @Override
            public Adapter caseRangeFeatureValuesXML(RangeFeatureValuesXML object)
            {
                return createRangeFeatureValuesXMLAdapter();
            }
            @Override
            public Adapter caseSingleFeatureMatcherXML(SingleFeatureMatcherXML object)
            {
                return createSingleFeatureMatcherXMLAdapter();
            }
            @Override
            public Adapter caseTargetAnnotationXML(TargetAnnotationXML object)
            {
                return createTargetAnnotationXMLAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object)
            {
                return createEObjectAdapter();
            }
        };

  /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
  @Override
public Adapter createAdapter(Notifier target)
  {
        return modelSwitch.doSwitch((EObject)target);
    }


  /**
     * Creates a new adapter for an object of class '{@link org.apache.uima.tools.cfe.config.BitsetFeatureValuesXML <em>Bitset Feature Values XML</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.apache.uima.tools.cfe.config.BitsetFeatureValuesXML
     * @generated
     */
  public Adapter createBitsetFeatureValuesXMLAdapter()
  {
        return null;
    }

  /**
     * Creates a new adapter for an object of class '{@link org.apache.uima.tools.cfe.config.CFEDescriptorXML <em>CFE Descriptor XML</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.apache.uima.tools.cfe.config.CFEDescriptorXML
     * @generated
     */
  public Adapter createCFEDescriptorXMLAdapter()
  {
        return null;
    }

  /**
     * Creates a new adapter for an object of class '{@link org.apache.uima.tools.cfe.config.DocumentRoot <em>Document Root</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.apache.uima.tools.cfe.config.DocumentRoot
     * @generated
     */
  public Adapter createDocumentRootAdapter()
  {
        return null;
    }

  /**
     * Creates a new adapter for an object of class '{@link org.apache.uima.tools.cfe.config.EnumFeatureValuesXML <em>Enum Feature Values XML</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.apache.uima.tools.cfe.config.EnumFeatureValuesXML
     * @generated
     */
  public Adapter createEnumFeatureValuesXMLAdapter()
  {
        return null;
    }

  /**
     * Creates a new adapter for an object of class '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML <em>Feature Object Matcher XML</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML
     * @generated
     */
  public Adapter createFeatureObjectMatcherXMLAdapter()
  {
        return null;
    }

  /**
     * Creates a new adapter for an object of class '{@link org.apache.uima.tools.cfe.config.GroupFeatureMatcherXML <em>Group Feature Matcher XML</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.apache.uima.tools.cfe.config.GroupFeatureMatcherXML
     * @generated
     */
  public Adapter createGroupFeatureMatcherXMLAdapter()
  {
        return null;
    }

  /**
     * Creates a new adapter for an object of class '{@link org.apache.uima.tools.cfe.config.ObjectPathFeatureValuesXML <em>Object Path Feature Values XML</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.apache.uima.tools.cfe.config.ObjectPathFeatureValuesXML
     * @generated
     */
  public Adapter createObjectPathFeatureValuesXMLAdapter()
  {
        return null;
    }

  /**
     * Creates a new adapter for an object of class '{@link org.apache.uima.tools.cfe.config.PartialObjectMatcherXML <em>Partial Object Matcher XML</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.apache.uima.tools.cfe.config.PartialObjectMatcherXML
     * @generated
     */
  public Adapter createPartialObjectMatcherXMLAdapter()
  {
        return null;
    }

  /**
     * Creates a new adapter for an object of class '{@link org.apache.uima.tools.cfe.config.PatternFeatureValuesXML <em>Pattern Feature Values XML</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.apache.uima.tools.cfe.config.PatternFeatureValuesXML
     * @generated
     */
  public Adapter createPatternFeatureValuesXMLAdapter()
  {
        return null;
    }

  /**
     * Creates a new adapter for an object of class '{@link org.apache.uima.tools.cfe.config.RangeFeatureValuesXML <em>Range Feature Values XML</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.apache.uima.tools.cfe.config.RangeFeatureValuesXML
     * @generated
     */
  public Adapter createRangeFeatureValuesXMLAdapter()
  {
        return null;
    }

  /**
     * Creates a new adapter for an object of class '{@link org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML <em>Single Feature Matcher XML</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML
     * @generated
     */
  public Adapter createSingleFeatureMatcherXMLAdapter()
  {
        return null;
    }

  /**
     * Creates a new adapter for an object of class '{@link org.apache.uima.tools.cfe.config.TargetAnnotationXML <em>Target Annotation XML</em>}'.
     * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.apache.uima.tools.cfe.config.TargetAnnotationXML
     * @generated
     */
  public Adapter createTargetAnnotationXMLAdapter()
  {
        return null;
    }

  /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
  public Adapter createEObjectAdapter()
  {
        return null;
    }

} //ConfigAdapterFactory
