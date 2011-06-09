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
import org.apache.uima.tools.cfe.config.GroupFeatureMatcherXML;
import org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Group Feature Matcher XML</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.GroupFeatureMatcherXMLImpl#getFeatureMatchers <em>Feature Matchers</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.GroupFeatureMatcherXMLImpl#isExclude <em>Exclude</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GroupFeatureMatcherXMLImpl extends EObjectImpl implements GroupFeatureMatcherXML
{
  /**
     * The cached value of the '{@link #getFeatureMatchers() <em>Feature Matchers</em>}' containment reference list.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getFeatureMatchers()
     * @generated
     * @ordered
     */
  protected EList<SingleFeatureMatcherXML> featureMatchers;

  /**
     * The default value of the '{@link #isExclude() <em>Exclude</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isExclude()
     * @generated
     * @ordered
     */
  protected static final boolean EXCLUDE_EDEFAULT = false;

  /**
     * The cached value of the '{@link #isExclude() <em>Exclude</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isExclude()
     * @generated
     * @ordered
     */
  protected boolean exclude = EXCLUDE_EDEFAULT;

  /**
     * This is true if the Exclude attribute has been set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  protected boolean excludeESet;

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected GroupFeatureMatcherXMLImpl()
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
        return ConfigPackage.Literals.GROUP_FEATURE_MATCHER_XML;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EList<SingleFeatureMatcherXML> getFeatureMatchers()
  {
        if (featureMatchers == null)
        {
            featureMatchers = new EObjectContainmentEList<SingleFeatureMatcherXML>(SingleFeatureMatcherXML.class, this, ConfigPackage.GROUP_FEATURE_MATCHER_XML__FEATURE_MATCHERS);
        }
        return featureMatchers;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public boolean isExclude()
  {
        return exclude;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setExclude(boolean newExclude)
  {
        boolean oldExclude = exclude;
        exclude = newExclude;
        boolean oldExcludeESet = excludeESet;
        excludeESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.GROUP_FEATURE_MATCHER_XML__EXCLUDE, oldExclude, exclude, !oldExcludeESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void unsetExclude()
  {
        boolean oldExclude = exclude;
        boolean oldExcludeESet = excludeESet;
        exclude = EXCLUDE_EDEFAULT;
        excludeESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ConfigPackage.GROUP_FEATURE_MATCHER_XML__EXCLUDE, oldExclude, EXCLUDE_EDEFAULT, oldExcludeESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public boolean isSetExclude()
  {
        return excludeESet;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  @Override
public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
        switch (featureID)
        {
            case ConfigPackage.GROUP_FEATURE_MATCHER_XML__FEATURE_MATCHERS:
                return ((InternalEList<?>)getFeatureMatchers()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
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
            case ConfigPackage.GROUP_FEATURE_MATCHER_XML__FEATURE_MATCHERS:
                return getFeatureMatchers();
            case ConfigPackage.GROUP_FEATURE_MATCHER_XML__EXCLUDE:
                return isExclude() ? Boolean.TRUE : Boolean.FALSE;
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
            case ConfigPackage.GROUP_FEATURE_MATCHER_XML__FEATURE_MATCHERS:
                getFeatureMatchers().clear();
                getFeatureMatchers().addAll((Collection<? extends SingleFeatureMatcherXML>)newValue);
                return;
            case ConfigPackage.GROUP_FEATURE_MATCHER_XML__EXCLUDE:
                setExclude(((Boolean)newValue).booleanValue());
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
            case ConfigPackage.GROUP_FEATURE_MATCHER_XML__FEATURE_MATCHERS:
                getFeatureMatchers().clear();
                return;
            case ConfigPackage.GROUP_FEATURE_MATCHER_XML__EXCLUDE:
                unsetExclude();
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
            case ConfigPackage.GROUP_FEATURE_MATCHER_XML__FEATURE_MATCHERS:
                return featureMatchers != null && !featureMatchers.isEmpty();
            case ConfigPackage.GROUP_FEATURE_MATCHER_XML__EXCLUDE:
                return isSetExclude();
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
        result.append(" (exclude: ");
        if (excludeESet) result.append(exclude); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} //GroupFeatureMatcherXMLImpl