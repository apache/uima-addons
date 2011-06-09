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

import java.util.Collection;

import org.apache.uima.tools.cfe.config.ConfigPackage;
import org.apache.uima.tools.cfe.config.EnumFeatureValuesXML;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enum Feature Values XML</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.EnumFeatureValuesXMLImpl#getValues <em>Values</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.EnumFeatureValuesXMLImpl#isCaseSensitive <em>Case Sensitive</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnumFeatureValuesXMLImpl extends EObjectImpl implements EnumFeatureValuesXML
{
  /**
     * The cached value of the '{@link #getValues() <em>Values</em>}' attribute list.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getValues()
     * @generated
     * @ordered
     */
  protected EList<String> values;

  /**
     * The default value of the '{@link #isCaseSensitive() <em>Case Sensitive</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isCaseSensitive()
     * @generated
     * @ordered
     */
  protected static final boolean CASE_SENSITIVE_EDEFAULT = false;

  /**
     * The cached value of the '{@link #isCaseSensitive() <em>Case Sensitive</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isCaseSensitive()
     * @generated
     * @ordered
     */
  protected boolean caseSensitive = CASE_SENSITIVE_EDEFAULT;

  /**
     * This is true if the Case Sensitive attribute has been set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  protected boolean caseSensitiveESet;

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected EnumFeatureValuesXMLImpl()
  {
        super();
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  @Override
protected EClass eStaticClass()
  {
        return ConfigPackage.Literals.ENUM_FEATURE_VALUES_XML;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EList<String> getValues()
  {
        if (values == null)
        {
            values = new EDataTypeEList<String>(String.class, this, ConfigPackage.ENUM_FEATURE_VALUES_XML__VALUES);
        }
        return values;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public boolean isCaseSensitive()
  {
        return caseSensitive;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setCaseSensitive(boolean newCaseSensitive)
  {
        boolean oldCaseSensitive = caseSensitive;
        caseSensitive = newCaseSensitive;
        boolean oldCaseSensitiveESet = caseSensitiveESet;
        caseSensitiveESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.ENUM_FEATURE_VALUES_XML__CASE_SENSITIVE, oldCaseSensitive, caseSensitive, !oldCaseSensitiveESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void unsetCaseSensitive()
  {
        boolean oldCaseSensitive = caseSensitive;
        boolean oldCaseSensitiveESet = caseSensitiveESet;
        caseSensitive = CASE_SENSITIVE_EDEFAULT;
        caseSensitiveESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ConfigPackage.ENUM_FEATURE_VALUES_XML__CASE_SENSITIVE, oldCaseSensitive, CASE_SENSITIVE_EDEFAULT, oldCaseSensitiveESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public boolean isSetCaseSensitive()
  {
        return caseSensitiveESet;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  @Override
public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
        switch (featureID)
        {
            case ConfigPackage.ENUM_FEATURE_VALUES_XML__VALUES:
                return getValues();
            case ConfigPackage.ENUM_FEATURE_VALUES_XML__CASE_SENSITIVE:
                return isCaseSensitive() ? Boolean.TRUE : Boolean.FALSE;
        }
        return super.eGet(featureID, resolve, coreType);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  @SuppressWarnings("unchecked")
@Override
public void eSet(int featureID, Object newValue)
  {
        switch (featureID)
        {
            case ConfigPackage.ENUM_FEATURE_VALUES_XML__VALUES:
                getValues().clear();
                getValues().addAll((Collection<? extends String>)newValue);
                return;
            case ConfigPackage.ENUM_FEATURE_VALUES_XML__CASE_SENSITIVE:
                setCaseSensitive(((Boolean)newValue).booleanValue());
                return;
        }
        super.eSet(featureID, newValue);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  @Override
public void eUnset(int featureID)
  {
        switch (featureID)
        {
            case ConfigPackage.ENUM_FEATURE_VALUES_XML__VALUES:
                getValues().clear();
                return;
            case ConfigPackage.ENUM_FEATURE_VALUES_XML__CASE_SENSITIVE:
                unsetCaseSensitive();
                return;
        }
        super.eUnset(featureID);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  @Override
public boolean eIsSet(int featureID)
  {
        switch (featureID)
        {
            case ConfigPackage.ENUM_FEATURE_VALUES_XML__VALUES:
                return values != null && !values.isEmpty();
            case ConfigPackage.ENUM_FEATURE_VALUES_XML__CASE_SENSITIVE:
                return isSetCaseSensitive();
        }
        return super.eIsSet(featureID);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  @Override
public String toString()
  {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (values: ");
        result.append(values);
        result.append(", caseSensitive: ");
        if (caseSensitiveESet) result.append(caseSensitive); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} //EnumFeatureValuesXMLImpl