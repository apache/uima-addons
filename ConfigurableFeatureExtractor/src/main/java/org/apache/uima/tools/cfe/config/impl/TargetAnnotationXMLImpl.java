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
import org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML;
import org.apache.uima.tools.cfe.config.PartialObjectMatcherXML;
import org.apache.uima.tools.cfe.config.TargetAnnotationXML;
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
 * An implementation of the model object '<em><b>Target Annotation XML</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.TargetAnnotationXMLImpl#getTargetAnnotationMatcher <em>Target Annotation Matcher</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.TargetAnnotationXMLImpl#getFeatureAnnotationMatchers <em>Feature Annotation Matchers</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.TargetAnnotationXMLImpl#getClassName <em>Class Name</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.TargetAnnotationXMLImpl#getEnclosingAnnotation <em>Enclosing Annotation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TargetAnnotationXMLImpl extends EObjectImpl implements TargetAnnotationXML
{
  /**
     * The cached value of the '{@link #getTargetAnnotationMatcher() <em>Target Annotation Matcher</em>}' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getTargetAnnotationMatcher()
     * @generated
     * @ordered
     */
  protected PartialObjectMatcherXML targetAnnotationMatcher;

  /**
     * The cached value of the '{@link #getFeatureAnnotationMatchers() <em>Feature Annotation Matchers</em>}' containment reference list.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getFeatureAnnotationMatchers()
     * @generated
     * @ordered
     */
  protected EList<FeatureObjectMatcherXML> featureAnnotationMatchers;

  /**
     * The default value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getClassName()
     * @generated
     * @ordered
     */
  protected static final String CLASS_NAME_EDEFAULT = null;

  /**
     * The cached value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getClassName()
     * @generated
     * @ordered
     */
  protected String className = CLASS_NAME_EDEFAULT;

  /**
     * The default value of the '{@link #getEnclosingAnnotation() <em>Enclosing Annotation</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getEnclosingAnnotation()
     * @generated
     * @ordered
     */
  protected static final String ENCLOSING_ANNOTATION_EDEFAULT = null;

  /**
     * The cached value of the '{@link #getEnclosingAnnotation() <em>Enclosing Annotation</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getEnclosingAnnotation()
     * @generated
     * @ordered
     */
  protected String enclosingAnnotation = ENCLOSING_ANNOTATION_EDEFAULT;

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected TargetAnnotationXMLImpl()
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
        return ConfigPackage.Literals.TARGET_ANNOTATION_XML;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public PartialObjectMatcherXML getTargetAnnotationMatcher()
  {
        return targetAnnotationMatcher;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public NotificationChain basicSetTargetAnnotationMatcher(PartialObjectMatcherXML newTargetAnnotationMatcher, NotificationChain msgs)
  {
        PartialObjectMatcherXML oldTargetAnnotationMatcher = targetAnnotationMatcher;
        targetAnnotationMatcher = newTargetAnnotationMatcher;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConfigPackage.TARGET_ANNOTATION_XML__TARGET_ANNOTATION_MATCHER, oldTargetAnnotationMatcher, newTargetAnnotationMatcher);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setTargetAnnotationMatcher(PartialObjectMatcherXML newTargetAnnotationMatcher)
  {
        if (newTargetAnnotationMatcher != targetAnnotationMatcher)
        {
            NotificationChain msgs = null;
            if (targetAnnotationMatcher != null)
                msgs = ((InternalEObject)targetAnnotationMatcher).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConfigPackage.TARGET_ANNOTATION_XML__TARGET_ANNOTATION_MATCHER, null, msgs);
            if (newTargetAnnotationMatcher != null)
                msgs = ((InternalEObject)newTargetAnnotationMatcher).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ConfigPackage.TARGET_ANNOTATION_XML__TARGET_ANNOTATION_MATCHER, null, msgs);
            msgs = basicSetTargetAnnotationMatcher(newTargetAnnotationMatcher, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.TARGET_ANNOTATION_XML__TARGET_ANNOTATION_MATCHER, newTargetAnnotationMatcher, newTargetAnnotationMatcher));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EList<FeatureObjectMatcherXML> getFeatureAnnotationMatchers()
  {
        if (featureAnnotationMatchers == null)
        {
            featureAnnotationMatchers = new EObjectContainmentEList<FeatureObjectMatcherXML>(FeatureObjectMatcherXML.class, this, ConfigPackage.TARGET_ANNOTATION_XML__FEATURE_ANNOTATION_MATCHERS);
        }
        return featureAnnotationMatchers;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public String getClassName()
  {
        return className;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setClassName(String newClassName)
  {
        String oldClassName = className;
        className = newClassName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.TARGET_ANNOTATION_XML__CLASS_NAME, oldClassName, className));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public String getEnclosingAnnotation()
  {
        return enclosingAnnotation;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setEnclosingAnnotation(String newEnclosingAnnotation)
  {
        String oldEnclosingAnnotation = enclosingAnnotation;
        enclosingAnnotation = newEnclosingAnnotation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.TARGET_ANNOTATION_XML__ENCLOSING_ANNOTATION, oldEnclosingAnnotation, enclosingAnnotation));
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
            case ConfigPackage.TARGET_ANNOTATION_XML__TARGET_ANNOTATION_MATCHER:
                return basicSetTargetAnnotationMatcher(null, msgs);
            case ConfigPackage.TARGET_ANNOTATION_XML__FEATURE_ANNOTATION_MATCHERS:
                return ((InternalEList<?>)getFeatureAnnotationMatchers()).basicRemove(otherEnd, msgs);
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
            case ConfigPackage.TARGET_ANNOTATION_XML__TARGET_ANNOTATION_MATCHER:
                return getTargetAnnotationMatcher();
            case ConfigPackage.TARGET_ANNOTATION_XML__FEATURE_ANNOTATION_MATCHERS:
                return getFeatureAnnotationMatchers();
            case ConfigPackage.TARGET_ANNOTATION_XML__CLASS_NAME:
                return getClassName();
            case ConfigPackage.TARGET_ANNOTATION_XML__ENCLOSING_ANNOTATION:
                return getEnclosingAnnotation();
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
            case ConfigPackage.TARGET_ANNOTATION_XML__TARGET_ANNOTATION_MATCHER:
                setTargetAnnotationMatcher((PartialObjectMatcherXML)newValue);
                return;
            case ConfigPackage.TARGET_ANNOTATION_XML__FEATURE_ANNOTATION_MATCHERS:
                getFeatureAnnotationMatchers().clear();
                getFeatureAnnotationMatchers().addAll((Collection<? extends FeatureObjectMatcherXML>)newValue);
                return;
            case ConfigPackage.TARGET_ANNOTATION_XML__CLASS_NAME:
                setClassName((String)newValue);
                return;
            case ConfigPackage.TARGET_ANNOTATION_XML__ENCLOSING_ANNOTATION:
                setEnclosingAnnotation((String)newValue);
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
            case ConfigPackage.TARGET_ANNOTATION_XML__TARGET_ANNOTATION_MATCHER:
                setTargetAnnotationMatcher((PartialObjectMatcherXML)null);
                return;
            case ConfigPackage.TARGET_ANNOTATION_XML__FEATURE_ANNOTATION_MATCHERS:
                getFeatureAnnotationMatchers().clear();
                return;
            case ConfigPackage.TARGET_ANNOTATION_XML__CLASS_NAME:
                setClassName(CLASS_NAME_EDEFAULT);
                return;
            case ConfigPackage.TARGET_ANNOTATION_XML__ENCLOSING_ANNOTATION:
                setEnclosingAnnotation(ENCLOSING_ANNOTATION_EDEFAULT);
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
            case ConfigPackage.TARGET_ANNOTATION_XML__TARGET_ANNOTATION_MATCHER:
                return targetAnnotationMatcher != null;
            case ConfigPackage.TARGET_ANNOTATION_XML__FEATURE_ANNOTATION_MATCHERS:
                return featureAnnotationMatchers != null && !featureAnnotationMatchers.isEmpty();
            case ConfigPackage.TARGET_ANNOTATION_XML__CLASS_NAME:
                return CLASS_NAME_EDEFAULT == null ? className != null : !CLASS_NAME_EDEFAULT.equals(className);
            case ConfigPackage.TARGET_ANNOTATION_XML__ENCLOSING_ANNOTATION:
                return ENCLOSING_ANNOTATION_EDEFAULT == null ? enclosingAnnotation != null : !ENCLOSING_ANNOTATION_EDEFAULT.equals(enclosingAnnotation);
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
        result.append(" (className: ");
        result.append(className);
        result.append(", enclosingAnnotation: ");
        result.append(enclosingAnnotation);
        result.append(')');
        return result.toString();
    }

} //TargetAnnotationXMLImpl