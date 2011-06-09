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

import org.apache.uima.tools.cfe.config.CFEDescriptorXML;
import org.apache.uima.tools.cfe.config.ConfigPackage;
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
 * An implementation of the model object '<em><b>CFE Descriptor XML</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.CFEDescriptorXMLImpl#getTargetAnnotations <em>Target Annotations</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.CFEDescriptorXMLImpl#getNullValueImage <em>Null Value Image</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CFEDescriptorXMLImpl extends EObjectImpl implements CFEDescriptorXML
{
  /**
     * The cached value of the '{@link #getTargetAnnotations() <em>Target Annotations</em>}' containment reference list.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getTargetAnnotations()
     * @generated
     * @ordered
     */
  protected EList<TargetAnnotationXML> targetAnnotations;

  /**
     * The default value of the '{@link #getNullValueImage() <em>Null Value Image</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getNullValueImage()
     * @generated
     * @ordered
     */
  protected static final String NULL_VALUE_IMAGE_EDEFAULT = null;

  /**
     * The cached value of the '{@link #getNullValueImage() <em>Null Value Image</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getNullValueImage()
     * @generated
     * @ordered
     */
  protected String nullValueImage = NULL_VALUE_IMAGE_EDEFAULT;

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected CFEDescriptorXMLImpl()
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
        return ConfigPackage.Literals.CFE_DESCRIPTOR_XML;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EList<TargetAnnotationXML> getTargetAnnotations()
  {
        if (targetAnnotations == null)
        {
            targetAnnotations = new EObjectContainmentEList<TargetAnnotationXML>(TargetAnnotationXML.class, this, ConfigPackage.CFE_DESCRIPTOR_XML__TARGET_ANNOTATIONS);
        }
        return targetAnnotations;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public String getNullValueImage()
  {
        return nullValueImage;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setNullValueImage(String newNullValueImage)
  {
        String oldNullValueImage = nullValueImage;
        nullValueImage = newNullValueImage;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.CFE_DESCRIPTOR_XML__NULL_VALUE_IMAGE, oldNullValueImage, nullValueImage));
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
            case ConfigPackage.CFE_DESCRIPTOR_XML__TARGET_ANNOTATIONS:
                return ((InternalEList<?>)getTargetAnnotations()).basicRemove(otherEnd, msgs);
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
            case ConfigPackage.CFE_DESCRIPTOR_XML__TARGET_ANNOTATIONS:
                return getTargetAnnotations();
            case ConfigPackage.CFE_DESCRIPTOR_XML__NULL_VALUE_IMAGE:
                return getNullValueImage();
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
            case ConfigPackage.CFE_DESCRIPTOR_XML__TARGET_ANNOTATIONS:
                getTargetAnnotations().clear();
                getTargetAnnotations().addAll((Collection<? extends TargetAnnotationXML>)newValue);
                return;
            case ConfigPackage.CFE_DESCRIPTOR_XML__NULL_VALUE_IMAGE:
                setNullValueImage((String)newValue);
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
            case ConfigPackage.CFE_DESCRIPTOR_XML__TARGET_ANNOTATIONS:
                getTargetAnnotations().clear();
                return;
            case ConfigPackage.CFE_DESCRIPTOR_XML__NULL_VALUE_IMAGE:
                setNullValueImage(NULL_VALUE_IMAGE_EDEFAULT);
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
            case ConfigPackage.CFE_DESCRIPTOR_XML__TARGET_ANNOTATIONS:
                return targetAnnotations != null && !targetAnnotations.isEmpty();
            case ConfigPackage.CFE_DESCRIPTOR_XML__NULL_VALUE_IMAGE:
                return NULL_VALUE_IMAGE_EDEFAULT == null ? nullValueImage != null : !NULL_VALUE_IMAGE_EDEFAULT.equals(nullValueImage);
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
        result.append(" (nullValueImage: ");
        result.append(nullValueImage);
        result.append(')');
        return result.toString();
    }

} //CFEDescriptorXMLImpl