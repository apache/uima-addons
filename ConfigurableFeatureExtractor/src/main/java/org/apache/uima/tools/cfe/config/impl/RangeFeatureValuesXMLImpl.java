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

import org.apache.uima.tools.cfe.config.ConfigPackage;
import org.apache.uima.tools.cfe.config.RangeFeatureValuesXML;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Range Feature Values XML</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.RangeFeatureValuesXMLImpl#getLowerBoundary <em>Lower Boundary</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.RangeFeatureValuesXMLImpl#isLowerBoundaryInclusive <em>Lower Boundary Inclusive</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.RangeFeatureValuesXMLImpl#getUpperBoundary <em>Upper Boundary</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.RangeFeatureValuesXMLImpl#isUpperBoundaryInclusive <em>Upper Boundary Inclusive</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RangeFeatureValuesXMLImpl extends EObjectImpl implements RangeFeatureValuesXML
{
  /**
     * The default value of the '{@link #getLowerBoundary() <em>Lower Boundary</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getLowerBoundary()
     * @generated
     * @ordered
     */
  protected static final Object LOWER_BOUNDARY_EDEFAULT = null;

  /**
     * The cached value of the '{@link #getLowerBoundary() <em>Lower Boundary</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getLowerBoundary()
     * @generated
     * @ordered
     */
  protected Object lowerBoundary = LOWER_BOUNDARY_EDEFAULT;

  /**
     * The default value of the '{@link #isLowerBoundaryInclusive() <em>Lower Boundary Inclusive</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isLowerBoundaryInclusive()
     * @generated
     * @ordered
     */
  protected static final boolean LOWER_BOUNDARY_INCLUSIVE_EDEFAULT = false;

  /**
     * The cached value of the '{@link #isLowerBoundaryInclusive() <em>Lower Boundary Inclusive</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isLowerBoundaryInclusive()
     * @generated
     * @ordered
     */
  protected boolean lowerBoundaryInclusive = LOWER_BOUNDARY_INCLUSIVE_EDEFAULT;

  /**
     * This is true if the Lower Boundary Inclusive attribute has been set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  protected boolean lowerBoundaryInclusiveESet;

  /**
     * The default value of the '{@link #getUpperBoundary() <em>Upper Boundary</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getUpperBoundary()
     * @generated
     * @ordered
     */
  protected static final Object UPPER_BOUNDARY_EDEFAULT = null;

  /**
     * The cached value of the '{@link #getUpperBoundary() <em>Upper Boundary</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getUpperBoundary()
     * @generated
     * @ordered
     */
  protected Object upperBoundary = UPPER_BOUNDARY_EDEFAULT;

  /**
     * The default value of the '{@link #isUpperBoundaryInclusive() <em>Upper Boundary Inclusive</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isUpperBoundaryInclusive()
     * @generated
     * @ordered
     */
  protected static final boolean UPPER_BOUNDARY_INCLUSIVE_EDEFAULT = false;

  /**
     * The cached value of the '{@link #isUpperBoundaryInclusive() <em>Upper Boundary Inclusive</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isUpperBoundaryInclusive()
     * @generated
     * @ordered
     */
  protected boolean upperBoundaryInclusive = UPPER_BOUNDARY_INCLUSIVE_EDEFAULT;

  /**
     * This is true if the Upper Boundary Inclusive attribute has been set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  protected boolean upperBoundaryInclusiveESet;

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected RangeFeatureValuesXMLImpl()
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
        return ConfigPackage.Literals.RANGE_FEATURE_VALUES_XML;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public Object getLowerBoundary()
  {
        return lowerBoundary;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setLowerBoundary(Object newLowerBoundary)
  {
        Object oldLowerBoundary = lowerBoundary;
        lowerBoundary = newLowerBoundary;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.RANGE_FEATURE_VALUES_XML__LOWER_BOUNDARY, oldLowerBoundary, lowerBoundary));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public boolean isLowerBoundaryInclusive()
  {
        return lowerBoundaryInclusive;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setLowerBoundaryInclusive(boolean newLowerBoundaryInclusive)
  {
        boolean oldLowerBoundaryInclusive = lowerBoundaryInclusive;
        lowerBoundaryInclusive = newLowerBoundaryInclusive;
        boolean oldLowerBoundaryInclusiveESet = lowerBoundaryInclusiveESet;
        lowerBoundaryInclusiveESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.RANGE_FEATURE_VALUES_XML__LOWER_BOUNDARY_INCLUSIVE, oldLowerBoundaryInclusive, lowerBoundaryInclusive, !oldLowerBoundaryInclusiveESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void unsetLowerBoundaryInclusive()
  {
        boolean oldLowerBoundaryInclusive = lowerBoundaryInclusive;
        boolean oldLowerBoundaryInclusiveESet = lowerBoundaryInclusiveESet;
        lowerBoundaryInclusive = LOWER_BOUNDARY_INCLUSIVE_EDEFAULT;
        lowerBoundaryInclusiveESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ConfigPackage.RANGE_FEATURE_VALUES_XML__LOWER_BOUNDARY_INCLUSIVE, oldLowerBoundaryInclusive, LOWER_BOUNDARY_INCLUSIVE_EDEFAULT, oldLowerBoundaryInclusiveESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public boolean isSetLowerBoundaryInclusive()
  {
        return lowerBoundaryInclusiveESet;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public Object getUpperBoundary()
  {
        return upperBoundary;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setUpperBoundary(Object newUpperBoundary)
  {
        Object oldUpperBoundary = upperBoundary;
        upperBoundary = newUpperBoundary;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.RANGE_FEATURE_VALUES_XML__UPPER_BOUNDARY, oldUpperBoundary, upperBoundary));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public boolean isUpperBoundaryInclusive()
  {
        return upperBoundaryInclusive;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setUpperBoundaryInclusive(boolean newUpperBoundaryInclusive)
  {
        boolean oldUpperBoundaryInclusive = upperBoundaryInclusive;
        upperBoundaryInclusive = newUpperBoundaryInclusive;
        boolean oldUpperBoundaryInclusiveESet = upperBoundaryInclusiveESet;
        upperBoundaryInclusiveESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.RANGE_FEATURE_VALUES_XML__UPPER_BOUNDARY_INCLUSIVE, oldUpperBoundaryInclusive, upperBoundaryInclusive, !oldUpperBoundaryInclusiveESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void unsetUpperBoundaryInclusive()
  {
        boolean oldUpperBoundaryInclusive = upperBoundaryInclusive;
        boolean oldUpperBoundaryInclusiveESet = upperBoundaryInclusiveESet;
        upperBoundaryInclusive = UPPER_BOUNDARY_INCLUSIVE_EDEFAULT;
        upperBoundaryInclusiveESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ConfigPackage.RANGE_FEATURE_VALUES_XML__UPPER_BOUNDARY_INCLUSIVE, oldUpperBoundaryInclusive, UPPER_BOUNDARY_INCLUSIVE_EDEFAULT, oldUpperBoundaryInclusiveESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public boolean isSetUpperBoundaryInclusive()
  {
        return upperBoundaryInclusiveESet;
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
            case ConfigPackage.RANGE_FEATURE_VALUES_XML__LOWER_BOUNDARY:
                return getLowerBoundary();
            case ConfigPackage.RANGE_FEATURE_VALUES_XML__LOWER_BOUNDARY_INCLUSIVE:
                return isLowerBoundaryInclusive() ? Boolean.TRUE : Boolean.FALSE;
            case ConfigPackage.RANGE_FEATURE_VALUES_XML__UPPER_BOUNDARY:
                return getUpperBoundary();
            case ConfigPackage.RANGE_FEATURE_VALUES_XML__UPPER_BOUNDARY_INCLUSIVE:
                return isUpperBoundaryInclusive() ? Boolean.TRUE : Boolean.FALSE;
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
            case ConfigPackage.RANGE_FEATURE_VALUES_XML__LOWER_BOUNDARY:
                setLowerBoundary(newValue);
                return;
            case ConfigPackage.RANGE_FEATURE_VALUES_XML__LOWER_BOUNDARY_INCLUSIVE:
                setLowerBoundaryInclusive(((Boolean)newValue).booleanValue());
                return;
            case ConfigPackage.RANGE_FEATURE_VALUES_XML__UPPER_BOUNDARY:
                setUpperBoundary(newValue);
                return;
            case ConfigPackage.RANGE_FEATURE_VALUES_XML__UPPER_BOUNDARY_INCLUSIVE:
                setUpperBoundaryInclusive(((Boolean)newValue).booleanValue());
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
            case ConfigPackage.RANGE_FEATURE_VALUES_XML__LOWER_BOUNDARY:
                setLowerBoundary(LOWER_BOUNDARY_EDEFAULT);
                return;
            case ConfigPackage.RANGE_FEATURE_VALUES_XML__LOWER_BOUNDARY_INCLUSIVE:
                unsetLowerBoundaryInclusive();
                return;
            case ConfigPackage.RANGE_FEATURE_VALUES_XML__UPPER_BOUNDARY:
                setUpperBoundary(UPPER_BOUNDARY_EDEFAULT);
                return;
            case ConfigPackage.RANGE_FEATURE_VALUES_XML__UPPER_BOUNDARY_INCLUSIVE:
                unsetUpperBoundaryInclusive();
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
            case ConfigPackage.RANGE_FEATURE_VALUES_XML__LOWER_BOUNDARY:
                return LOWER_BOUNDARY_EDEFAULT == null ? lowerBoundary != null : !LOWER_BOUNDARY_EDEFAULT.equals(lowerBoundary);
            case ConfigPackage.RANGE_FEATURE_VALUES_XML__LOWER_BOUNDARY_INCLUSIVE:
                return isSetLowerBoundaryInclusive();
            case ConfigPackage.RANGE_FEATURE_VALUES_XML__UPPER_BOUNDARY:
                return UPPER_BOUNDARY_EDEFAULT == null ? upperBoundary != null : !UPPER_BOUNDARY_EDEFAULT.equals(upperBoundary);
            case ConfigPackage.RANGE_FEATURE_VALUES_XML__UPPER_BOUNDARY_INCLUSIVE:
                return isSetUpperBoundaryInclusive();
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
        result.append(" (lowerBoundary: ");
        result.append(lowerBoundary);
        result.append(", lowerBoundaryInclusive: ");
        if (lowerBoundaryInclusiveESet) result.append(lowerBoundaryInclusive); else result.append("<unset>");
        result.append(", upperBoundary: ");
        result.append(upperBoundary);
        result.append(", upperBoundaryInclusive: ");
        if (upperBoundaryInclusiveESet) result.append(upperBoundaryInclusive); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} //RangeFeatureValuesXMLImpl