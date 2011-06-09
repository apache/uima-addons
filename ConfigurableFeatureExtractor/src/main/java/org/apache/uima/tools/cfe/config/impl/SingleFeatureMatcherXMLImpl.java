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
import org.apache.uima.tools.cfe.config.EnumFeatureValuesXML;
import org.apache.uima.tools.cfe.config.ObjectPathFeatureValuesXML;
import org.apache.uima.tools.cfe.config.PatternFeatureValuesXML;
import org.apache.uima.tools.cfe.config.RangeFeatureValuesXML;
import org.apache.uima.tools.cfe.config.SingleFeatureMatcherXML;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Single Feature Matcher XML</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.SingleFeatureMatcherXMLImpl#getRangeFeatureValues <em>Range Feature Values</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.SingleFeatureMatcherXMLImpl#getEnumFeatureValues <em>Enum Feature Values</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.SingleFeatureMatcherXMLImpl#getBitsetFeatureValues <em>Bitset Feature Values</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.SingleFeatureMatcherXMLImpl#getObjectPathFeatureValues <em>Object Path Feature Values</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.SingleFeatureMatcherXMLImpl#getPatternFeatureValues <em>Pattern Feature Values</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.SingleFeatureMatcherXMLImpl#isExclude <em>Exclude</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.SingleFeatureMatcherXMLImpl#getFeaturePath <em>Feature Path</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.SingleFeatureMatcherXMLImpl#getFeatureTypeName <em>Feature Type Name</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.SingleFeatureMatcherXMLImpl#isQuiet <em>Quiet</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SingleFeatureMatcherXMLImpl extends EObjectImpl implements SingleFeatureMatcherXML
{
  /**
     * The cached value of the '{@link #getRangeFeatureValues() <em>Range Feature Values</em>}' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getRangeFeatureValues()
     * @generated
     * @ordered
     */
  protected RangeFeatureValuesXML rangeFeatureValues;

  /**
     * The cached value of the '{@link #getEnumFeatureValues() <em>Enum Feature Values</em>}' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getEnumFeatureValues()
     * @generated
     * @ordered
     */
  protected EnumFeatureValuesXML enumFeatureValues;

  /**
     * The cached value of the '{@link #getBitsetFeatureValues() <em>Bitset Feature Values</em>}' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getBitsetFeatureValues()
     * @generated
     * @ordered
     */
  protected BitsetFeatureValuesXML bitsetFeatureValues;

  /**
     * The cached value of the '{@link #getObjectPathFeatureValues() <em>Object Path Feature Values</em>}' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getObjectPathFeatureValues()
     * @generated
     * @ordered
     */
  protected ObjectPathFeatureValuesXML objectPathFeatureValues;

  /**
     * The cached value of the '{@link #getPatternFeatureValues() <em>Pattern Feature Values</em>}' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getPatternFeatureValues()
     * @generated
     * @ordered
     */
  protected PatternFeatureValuesXML patternFeatureValues;

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
     * The default value of the '{@link #getFeaturePath() <em>Feature Path</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getFeaturePath()
     * @generated
     * @ordered
     */
  protected static final String FEATURE_PATH_EDEFAULT = null;

  /**
     * The cached value of the '{@link #getFeaturePath() <em>Feature Path</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getFeaturePath()
     * @generated
     * @ordered
     */
  protected String featurePath = FEATURE_PATH_EDEFAULT;

  /**
     * The default value of the '{@link #getFeatureTypeName() <em>Feature Type Name</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getFeatureTypeName()
     * @generated
     * @ordered
     */
  protected static final String FEATURE_TYPE_NAME_EDEFAULT = null;

  /**
     * The cached value of the '{@link #getFeatureTypeName() <em>Feature Type Name</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getFeatureTypeName()
     * @generated
     * @ordered
     */
  protected String featureTypeName = FEATURE_TYPE_NAME_EDEFAULT;

  /**
     * The default value of the '{@link #isQuiet() <em>Quiet</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isQuiet()
     * @generated
     * @ordered
     */
  protected static final boolean QUIET_EDEFAULT = false;

  /**
     * The cached value of the '{@link #isQuiet() <em>Quiet</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isQuiet()
     * @generated
     * @ordered
     */
  protected boolean quiet = QUIET_EDEFAULT;

  /**
     * This is true if the Quiet attribute has been set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  protected boolean quietESet;

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected SingleFeatureMatcherXMLImpl()
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
        return ConfigPackage.Literals.SINGLE_FEATURE_MATCHER_XML;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public RangeFeatureValuesXML getRangeFeatureValues()
  {
        return rangeFeatureValues;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public NotificationChain basicSetRangeFeatureValues(RangeFeatureValuesXML newRangeFeatureValues, NotificationChain msgs)
  {
        RangeFeatureValuesXML oldRangeFeatureValues = rangeFeatureValues;
        rangeFeatureValues = newRangeFeatureValues;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConfigPackage.SINGLE_FEATURE_MATCHER_XML__RANGE_FEATURE_VALUES, oldRangeFeatureValues, newRangeFeatureValues);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setRangeFeatureValues(RangeFeatureValuesXML newRangeFeatureValues)
  {
        if (newRangeFeatureValues != rangeFeatureValues)
        {
            NotificationChain msgs = null;
            if (rangeFeatureValues != null)
                msgs = ((InternalEObject)rangeFeatureValues).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConfigPackage.SINGLE_FEATURE_MATCHER_XML__RANGE_FEATURE_VALUES, null, msgs);
            if (newRangeFeatureValues != null)
                msgs = ((InternalEObject)newRangeFeatureValues).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ConfigPackage.SINGLE_FEATURE_MATCHER_XML__RANGE_FEATURE_VALUES, null, msgs);
            msgs = basicSetRangeFeatureValues(newRangeFeatureValues, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.SINGLE_FEATURE_MATCHER_XML__RANGE_FEATURE_VALUES, newRangeFeatureValues, newRangeFeatureValues));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public EnumFeatureValuesXML getEnumFeatureValues()
  {
        return enumFeatureValues;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public NotificationChain basicSetEnumFeatureValues(EnumFeatureValuesXML newEnumFeatureValues, NotificationChain msgs)
  {
        EnumFeatureValuesXML oldEnumFeatureValues = enumFeatureValues;
        enumFeatureValues = newEnumFeatureValues;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConfigPackage.SINGLE_FEATURE_MATCHER_XML__ENUM_FEATURE_VALUES, oldEnumFeatureValues, newEnumFeatureValues);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setEnumFeatureValues(EnumFeatureValuesXML newEnumFeatureValues)
  {
        if (newEnumFeatureValues != enumFeatureValues)
        {
            NotificationChain msgs = null;
            if (enumFeatureValues != null)
                msgs = ((InternalEObject)enumFeatureValues).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConfigPackage.SINGLE_FEATURE_MATCHER_XML__ENUM_FEATURE_VALUES, null, msgs);
            if (newEnumFeatureValues != null)
                msgs = ((InternalEObject)newEnumFeatureValues).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ConfigPackage.SINGLE_FEATURE_MATCHER_XML__ENUM_FEATURE_VALUES, null, msgs);
            msgs = basicSetEnumFeatureValues(newEnumFeatureValues, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.SINGLE_FEATURE_MATCHER_XML__ENUM_FEATURE_VALUES, newEnumFeatureValues, newEnumFeatureValues));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public BitsetFeatureValuesXML getBitsetFeatureValues()
  {
        return bitsetFeatureValues;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public NotificationChain basicSetBitsetFeatureValues(BitsetFeatureValuesXML newBitsetFeatureValues, NotificationChain msgs)
  {
        BitsetFeatureValuesXML oldBitsetFeatureValues = bitsetFeatureValues;
        bitsetFeatureValues = newBitsetFeatureValues;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConfigPackage.SINGLE_FEATURE_MATCHER_XML__BITSET_FEATURE_VALUES, oldBitsetFeatureValues, newBitsetFeatureValues);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setBitsetFeatureValues(BitsetFeatureValuesXML newBitsetFeatureValues)
  {
        if (newBitsetFeatureValues != bitsetFeatureValues)
        {
            NotificationChain msgs = null;
            if (bitsetFeatureValues != null)
                msgs = ((InternalEObject)bitsetFeatureValues).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConfigPackage.SINGLE_FEATURE_MATCHER_XML__BITSET_FEATURE_VALUES, null, msgs);
            if (newBitsetFeatureValues != null)
                msgs = ((InternalEObject)newBitsetFeatureValues).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ConfigPackage.SINGLE_FEATURE_MATCHER_XML__BITSET_FEATURE_VALUES, null, msgs);
            msgs = basicSetBitsetFeatureValues(newBitsetFeatureValues, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.SINGLE_FEATURE_MATCHER_XML__BITSET_FEATURE_VALUES, newBitsetFeatureValues, newBitsetFeatureValues));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public ObjectPathFeatureValuesXML getObjectPathFeatureValues()
  {
        return objectPathFeatureValues;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public NotificationChain basicSetObjectPathFeatureValues(ObjectPathFeatureValuesXML newObjectPathFeatureValues, NotificationChain msgs)
  {
        ObjectPathFeatureValuesXML oldObjectPathFeatureValues = objectPathFeatureValues;
        objectPathFeatureValues = newObjectPathFeatureValues;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConfigPackage.SINGLE_FEATURE_MATCHER_XML__OBJECT_PATH_FEATURE_VALUES, oldObjectPathFeatureValues, newObjectPathFeatureValues);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setObjectPathFeatureValues(ObjectPathFeatureValuesXML newObjectPathFeatureValues)
  {
        if (newObjectPathFeatureValues != objectPathFeatureValues)
        {
            NotificationChain msgs = null;
            if (objectPathFeatureValues != null)
                msgs = ((InternalEObject)objectPathFeatureValues).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConfigPackage.SINGLE_FEATURE_MATCHER_XML__OBJECT_PATH_FEATURE_VALUES, null, msgs);
            if (newObjectPathFeatureValues != null)
                msgs = ((InternalEObject)newObjectPathFeatureValues).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ConfigPackage.SINGLE_FEATURE_MATCHER_XML__OBJECT_PATH_FEATURE_VALUES, null, msgs);
            msgs = basicSetObjectPathFeatureValues(newObjectPathFeatureValues, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.SINGLE_FEATURE_MATCHER_XML__OBJECT_PATH_FEATURE_VALUES, newObjectPathFeatureValues, newObjectPathFeatureValues));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public PatternFeatureValuesXML getPatternFeatureValues()
  {
        return patternFeatureValues;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public NotificationChain basicSetPatternFeatureValues(PatternFeatureValuesXML newPatternFeatureValues, NotificationChain msgs)
  {
        PatternFeatureValuesXML oldPatternFeatureValues = patternFeatureValues;
        patternFeatureValues = newPatternFeatureValues;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConfigPackage.SINGLE_FEATURE_MATCHER_XML__PATTERN_FEATURE_VALUES, oldPatternFeatureValues, newPatternFeatureValues);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setPatternFeatureValues(PatternFeatureValuesXML newPatternFeatureValues)
  {
        if (newPatternFeatureValues != patternFeatureValues)
        {
            NotificationChain msgs = null;
            if (patternFeatureValues != null)
                msgs = ((InternalEObject)patternFeatureValues).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConfigPackage.SINGLE_FEATURE_MATCHER_XML__PATTERN_FEATURE_VALUES, null, msgs);
            if (newPatternFeatureValues != null)
                msgs = ((InternalEObject)newPatternFeatureValues).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ConfigPackage.SINGLE_FEATURE_MATCHER_XML__PATTERN_FEATURE_VALUES, null, msgs);
            msgs = basicSetPatternFeatureValues(newPatternFeatureValues, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.SINGLE_FEATURE_MATCHER_XML__PATTERN_FEATURE_VALUES, newPatternFeatureValues, newPatternFeatureValues));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.SINGLE_FEATURE_MATCHER_XML__EXCLUDE, oldExclude, exclude, !oldExcludeESet));
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
            eNotify(new ENotificationImpl(this, Notification.UNSET, ConfigPackage.SINGLE_FEATURE_MATCHER_XML__EXCLUDE, oldExclude, EXCLUDE_EDEFAULT, oldExcludeESet));
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
  public String getFeaturePath()
  {
        return featurePath;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setFeaturePath(String newFeaturePath)
  {
        String oldFeaturePath = featurePath;
        featurePath = newFeaturePath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.SINGLE_FEATURE_MATCHER_XML__FEATURE_PATH, oldFeaturePath, featurePath));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public String getFeatureTypeName()
  {
        return featureTypeName;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setFeatureTypeName(String newFeatureTypeName)
  {
        String oldFeatureTypeName = featureTypeName;
        featureTypeName = newFeatureTypeName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.SINGLE_FEATURE_MATCHER_XML__FEATURE_TYPE_NAME, oldFeatureTypeName, featureTypeName));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public boolean isQuiet()
  {
        return quiet;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setQuiet(boolean newQuiet)
  {
        boolean oldQuiet = quiet;
        quiet = newQuiet;
        boolean oldQuietESet = quietESet;
        quietESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.SINGLE_FEATURE_MATCHER_XML__QUIET, oldQuiet, quiet, !oldQuietESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void unsetQuiet()
  {
        boolean oldQuiet = quiet;
        boolean oldQuietESet = quietESet;
        quiet = QUIET_EDEFAULT;
        quietESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ConfigPackage.SINGLE_FEATURE_MATCHER_XML__QUIET, oldQuiet, QUIET_EDEFAULT, oldQuietESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public boolean isSetQuiet()
  {
        return quietESet;
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
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__RANGE_FEATURE_VALUES:
                return basicSetRangeFeatureValues(null, msgs);
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__ENUM_FEATURE_VALUES:
                return basicSetEnumFeatureValues(null, msgs);
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__BITSET_FEATURE_VALUES:
                return basicSetBitsetFeatureValues(null, msgs);
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__OBJECT_PATH_FEATURE_VALUES:
                return basicSetObjectPathFeatureValues(null, msgs);
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__PATTERN_FEATURE_VALUES:
                return basicSetPatternFeatureValues(null, msgs);
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
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__RANGE_FEATURE_VALUES:
                return getRangeFeatureValues();
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__ENUM_FEATURE_VALUES:
                return getEnumFeatureValues();
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__BITSET_FEATURE_VALUES:
                return getBitsetFeatureValues();
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__OBJECT_PATH_FEATURE_VALUES:
                return getObjectPathFeatureValues();
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__PATTERN_FEATURE_VALUES:
                return getPatternFeatureValues();
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__EXCLUDE:
                return isExclude() ? Boolean.TRUE : Boolean.FALSE;
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__FEATURE_PATH:
                return getFeaturePath();
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__FEATURE_TYPE_NAME:
                return getFeatureTypeName();
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__QUIET:
                return isQuiet() ? Boolean.TRUE : Boolean.FALSE;
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
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__RANGE_FEATURE_VALUES:
                setRangeFeatureValues((RangeFeatureValuesXML)newValue);
                return;
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__ENUM_FEATURE_VALUES:
                setEnumFeatureValues((EnumFeatureValuesXML)newValue);
                return;
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__BITSET_FEATURE_VALUES:
                setBitsetFeatureValues((BitsetFeatureValuesXML)newValue);
                return;
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__OBJECT_PATH_FEATURE_VALUES:
                setObjectPathFeatureValues((ObjectPathFeatureValuesXML)newValue);
                return;
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__PATTERN_FEATURE_VALUES:
                setPatternFeatureValues((PatternFeatureValuesXML)newValue);
                return;
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__EXCLUDE:
                setExclude(((Boolean)newValue).booleanValue());
                return;
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__FEATURE_PATH:
                setFeaturePath((String)newValue);
                return;
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__FEATURE_TYPE_NAME:
                setFeatureTypeName((String)newValue);
                return;
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__QUIET:
                setQuiet(((Boolean)newValue).booleanValue());
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
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__RANGE_FEATURE_VALUES:
                setRangeFeatureValues((RangeFeatureValuesXML)null);
                return;
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__ENUM_FEATURE_VALUES:
                setEnumFeatureValues((EnumFeatureValuesXML)null);
                return;
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__BITSET_FEATURE_VALUES:
                setBitsetFeatureValues((BitsetFeatureValuesXML)null);
                return;
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__OBJECT_PATH_FEATURE_VALUES:
                setObjectPathFeatureValues((ObjectPathFeatureValuesXML)null);
                return;
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__PATTERN_FEATURE_VALUES:
                setPatternFeatureValues((PatternFeatureValuesXML)null);
                return;
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__EXCLUDE:
                unsetExclude();
                return;
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__FEATURE_PATH:
                setFeaturePath(FEATURE_PATH_EDEFAULT);
                return;
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__FEATURE_TYPE_NAME:
                setFeatureTypeName(FEATURE_TYPE_NAME_EDEFAULT);
                return;
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__QUIET:
                unsetQuiet();
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
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__RANGE_FEATURE_VALUES:
                return rangeFeatureValues != null;
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__ENUM_FEATURE_VALUES:
                return enumFeatureValues != null;
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__BITSET_FEATURE_VALUES:
                return bitsetFeatureValues != null;
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__OBJECT_PATH_FEATURE_VALUES:
                return objectPathFeatureValues != null;
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__PATTERN_FEATURE_VALUES:
                return patternFeatureValues != null;
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__EXCLUDE:
                return isSetExclude();
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__FEATURE_PATH:
                return FEATURE_PATH_EDEFAULT == null ? featurePath != null : !FEATURE_PATH_EDEFAULT.equals(featurePath);
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__FEATURE_TYPE_NAME:
                return FEATURE_TYPE_NAME_EDEFAULT == null ? featureTypeName != null : !FEATURE_TYPE_NAME_EDEFAULT.equals(featureTypeName);
            case ConfigPackage.SINGLE_FEATURE_MATCHER_XML__QUIET:
                return isSetQuiet();
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
        result.append(", featurePath: ");
        result.append(featurePath);
        result.append(", featureTypeName: ");
        result.append(featureTypeName);
        result.append(", quiet: ");
        if (quietESet) result.append(quiet); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} //SingleFeatureMatcherXMLImpl