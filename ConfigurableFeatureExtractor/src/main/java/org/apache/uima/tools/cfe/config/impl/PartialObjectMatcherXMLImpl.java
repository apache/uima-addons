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
import org.apache.uima.tools.cfe.config.PartialObjectMatcherXML;
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
 * An implementation of the model object '<em><b>Partial Object Matcher XML</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.PartialObjectMatcherXMLImpl#getGroupFeatureMatchers <em>Group Feature Matchers</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.PartialObjectMatcherXMLImpl#getAnnotationTypeName <em>Annotation Type Name</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.PartialObjectMatcherXMLImpl#getFullPath <em>Full Path</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PartialObjectMatcherXMLImpl extends EObjectImpl implements PartialObjectMatcherXML
{
  /**
     * The cached value of the '{@link #getGroupFeatureMatchers() <em>Group Feature Matchers</em>}' containment reference list.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getGroupFeatureMatchers()
     * @generated
     * @ordered
     */
  protected EList<GroupFeatureMatcherXML> groupFeatureMatchers;

  /**
     * The default value of the '{@link #getAnnotationTypeName() <em>Annotation Type Name</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getAnnotationTypeName()
     * @generated
     * @ordered
     */
  protected static final String ANNOTATION_TYPE_NAME_EDEFAULT = null;

  /**
     * The cached value of the '{@link #getAnnotationTypeName() <em>Annotation Type Name</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getAnnotationTypeName()
     * @generated
     * @ordered
     */
  protected String annotationTypeName = ANNOTATION_TYPE_NAME_EDEFAULT;

  /**
     * The default value of the '{@link #getFullPath() <em>Full Path</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getFullPath()
     * @generated
     * @ordered
     */
  protected static final String FULL_PATH_EDEFAULT = null;

  /**
     * The cached value of the '{@link #getFullPath() <em>Full Path</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getFullPath()
     * @generated
     * @ordered
     */
  protected String fullPath = FULL_PATH_EDEFAULT;

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected PartialObjectMatcherXMLImpl()
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
        return ConfigPackage.Literals.PARTIAL_OBJECT_MATCHER_XML;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EList<GroupFeatureMatcherXML> getGroupFeatureMatchers()
  {
        if (groupFeatureMatchers == null)
        {
            groupFeatureMatchers = new EObjectContainmentEList<GroupFeatureMatcherXML>(GroupFeatureMatcherXML.class, this, ConfigPackage.PARTIAL_OBJECT_MATCHER_XML__GROUP_FEATURE_MATCHERS);
        }
        return groupFeatureMatchers;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public String getAnnotationTypeName()
  {
        return annotationTypeName;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setAnnotationTypeName(String newAnnotationTypeName)
  {
        String oldAnnotationTypeName = annotationTypeName;
        annotationTypeName = newAnnotationTypeName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.PARTIAL_OBJECT_MATCHER_XML__ANNOTATION_TYPE_NAME, oldAnnotationTypeName, annotationTypeName));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public String getFullPath()
  {
        return fullPath;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setFullPath(String newFullPath)
  {
        String oldFullPath = fullPath;
        fullPath = newFullPath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.PARTIAL_OBJECT_MATCHER_XML__FULL_PATH, oldFullPath, fullPath));
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
            case ConfigPackage.PARTIAL_OBJECT_MATCHER_XML__GROUP_FEATURE_MATCHERS:
                return ((InternalEList<?>)getGroupFeatureMatchers()).basicRemove(otherEnd, msgs);
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
            case ConfigPackage.PARTIAL_OBJECT_MATCHER_XML__GROUP_FEATURE_MATCHERS:
                return getGroupFeatureMatchers();
            case ConfigPackage.PARTIAL_OBJECT_MATCHER_XML__ANNOTATION_TYPE_NAME:
                return getAnnotationTypeName();
            case ConfigPackage.PARTIAL_OBJECT_MATCHER_XML__FULL_PATH:
                return getFullPath();
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
            case ConfigPackage.PARTIAL_OBJECT_MATCHER_XML__GROUP_FEATURE_MATCHERS:
                getGroupFeatureMatchers().clear();
                getGroupFeatureMatchers().addAll((Collection<? extends GroupFeatureMatcherXML>)newValue);
                return;
            case ConfigPackage.PARTIAL_OBJECT_MATCHER_XML__ANNOTATION_TYPE_NAME:
                setAnnotationTypeName((String)newValue);
                return;
            case ConfigPackage.PARTIAL_OBJECT_MATCHER_XML__FULL_PATH:
                setFullPath((String)newValue);
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
            case ConfigPackage.PARTIAL_OBJECT_MATCHER_XML__GROUP_FEATURE_MATCHERS:
                getGroupFeatureMatchers().clear();
                return;
            case ConfigPackage.PARTIAL_OBJECT_MATCHER_XML__ANNOTATION_TYPE_NAME:
                setAnnotationTypeName(ANNOTATION_TYPE_NAME_EDEFAULT);
                return;
            case ConfigPackage.PARTIAL_OBJECT_MATCHER_XML__FULL_PATH:
                setFullPath(FULL_PATH_EDEFAULT);
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
            case ConfigPackage.PARTIAL_OBJECT_MATCHER_XML__GROUP_FEATURE_MATCHERS:
                return groupFeatureMatchers != null && !groupFeatureMatchers.isEmpty();
            case ConfigPackage.PARTIAL_OBJECT_MATCHER_XML__ANNOTATION_TYPE_NAME:
                return ANNOTATION_TYPE_NAME_EDEFAULT == null ? annotationTypeName != null : !ANNOTATION_TYPE_NAME_EDEFAULT.equals(annotationTypeName);
            case ConfigPackage.PARTIAL_OBJECT_MATCHER_XML__FULL_PATH:
                return FULL_PATH_EDEFAULT == null ? fullPath != null : !FULL_PATH_EDEFAULT.equals(fullPath);
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
        result.append(" (annotationTypeName: ");
        result.append(annotationTypeName);
        result.append(", fullPath: ");
        result.append(fullPath);
        result.append(')');
        return result.toString();
    }

} //PartialObjectMatcherXMLImpl