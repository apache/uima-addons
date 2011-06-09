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
import org.apache.uima.tools.cfe.config.ConfigPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bitset Feature Values XML</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.BitsetFeatureValuesXMLImpl#getBitmask <em>Bitmask</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.BitsetFeatureValuesXMLImpl#isExactMatch <em>Exact Match</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BitsetFeatureValuesXMLImpl extends EObjectImpl implements BitsetFeatureValuesXML
{
  /**
     * The default value of the '{@link #getBitmask() <em>Bitmask</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getBitmask()
     * @generated
     * @ordered
     */
  protected static final String BITMASK_EDEFAULT = null;

  /**
     * The cached value of the '{@link #getBitmask() <em>Bitmask</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getBitmask()
     * @generated
     * @ordered
     */
  protected String bitmask = BITMASK_EDEFAULT;

  /**
     * The default value of the '{@link #isExactMatch() <em>Exact Match</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isExactMatch()
     * @generated
     * @ordered
     */
  protected static final boolean EXACT_MATCH_EDEFAULT = false;

  /**
     * The cached value of the '{@link #isExactMatch() <em>Exact Match</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isExactMatch()
     * @generated
     * @ordered
     */
  protected boolean exactMatch = EXACT_MATCH_EDEFAULT;

  /**
     * This is true if the Exact Match attribute has been set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  protected boolean exactMatchESet;

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected BitsetFeatureValuesXMLImpl()
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
        return ConfigPackage.Literals.BITSET_FEATURE_VALUES_XML;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public String getBitmask()
  {
        return bitmask;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setBitmask(String newBitmask)
  {
        String oldBitmask = bitmask;
        bitmask = newBitmask;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.BITSET_FEATURE_VALUES_XML__BITMASK, oldBitmask, bitmask));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public boolean isExactMatch()
  {
        return exactMatch;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setExactMatch(boolean newExactMatch)
  {
        boolean oldExactMatch = exactMatch;
        exactMatch = newExactMatch;
        boolean oldExactMatchESet = exactMatchESet;
        exactMatchESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.BITSET_FEATURE_VALUES_XML__EXACT_MATCH, oldExactMatch, exactMatch, !oldExactMatchESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void unsetExactMatch()
  {
        boolean oldExactMatch = exactMatch;
        boolean oldExactMatchESet = exactMatchESet;
        exactMatch = EXACT_MATCH_EDEFAULT;
        exactMatchESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ConfigPackage.BITSET_FEATURE_VALUES_XML__EXACT_MATCH, oldExactMatch, EXACT_MATCH_EDEFAULT, oldExactMatchESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public boolean isSetExactMatch()
  {
        return exactMatchESet;
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
            case ConfigPackage.BITSET_FEATURE_VALUES_XML__BITMASK:
                return getBitmask();
            case ConfigPackage.BITSET_FEATURE_VALUES_XML__EXACT_MATCH:
                return isExactMatch() ? Boolean.TRUE : Boolean.FALSE;
        }
        return super.eGet(featureID, resolve, coreType);
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  @Override
public void eSet(int featureID, Object newValue)
  {
        switch (featureID)
        {
            case ConfigPackage.BITSET_FEATURE_VALUES_XML__BITMASK:
                setBitmask((String)newValue);
                return;
            case ConfigPackage.BITSET_FEATURE_VALUES_XML__EXACT_MATCH:
                setExactMatch(((Boolean)newValue).booleanValue());
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
            case ConfigPackage.BITSET_FEATURE_VALUES_XML__BITMASK:
                setBitmask(BITMASK_EDEFAULT);
                return;
            case ConfigPackage.BITSET_FEATURE_VALUES_XML__EXACT_MATCH:
                unsetExactMatch();
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
            case ConfigPackage.BITSET_FEATURE_VALUES_XML__BITMASK:
                return BITMASK_EDEFAULT == null ? bitmask != null : !BITMASK_EDEFAULT.equals(bitmask);
            case ConfigPackage.BITSET_FEATURE_VALUES_XML__EXACT_MATCH:
                return isSetExactMatch();
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
        result.append(" (bitmask: ");
        result.append(bitmask);
        result.append(", exactMatch: ");
        if (exactMatchESet) result.append(exactMatch); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} //BitsetFeatureValuesXMLImpl