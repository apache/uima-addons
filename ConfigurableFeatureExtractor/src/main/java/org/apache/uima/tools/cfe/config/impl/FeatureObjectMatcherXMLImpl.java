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
import org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Object Matcher XML</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.FeatureObjectMatcherXMLImpl#isDistance <em>Distance</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.FeatureObjectMatcherXMLImpl#isOrientation <em>Orientation</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.FeatureObjectMatcherXMLImpl#getWindowFlags <em>Window Flags</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.FeatureObjectMatcherXMLImpl#getWindowsizeEnclosed <em>Windowsize Enclosed</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.FeatureObjectMatcherXMLImpl#getWindowsizeInside <em>Windowsize Inside</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.FeatureObjectMatcherXMLImpl#getWindowsizeLeft <em>Windowsize Left</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.impl.FeatureObjectMatcherXMLImpl#getWindowsizeRight <em>Windowsize Right</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureObjectMatcherXMLImpl extends PartialObjectMatcherXMLImpl implements FeatureObjectMatcherXML
{
  /**
     * The default value of the '{@link #isDistance() <em>Distance</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isDistance()
     * @generated
     * @ordered
     */
  protected static final boolean DISTANCE_EDEFAULT = false;

  /**
     * The cached value of the '{@link #isDistance() <em>Distance</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isDistance()
     * @generated
     * @ordered
     */
  protected boolean distance = DISTANCE_EDEFAULT;

  /**
     * This is true if the Distance attribute has been set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  protected boolean distanceESet;

  /**
     * The default value of the '{@link #isOrientation() <em>Orientation</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isOrientation()
     * @generated
     * @ordered
     */
  protected static final boolean ORIENTATION_EDEFAULT = false;

  /**
     * The cached value of the '{@link #isOrientation() <em>Orientation</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isOrientation()
     * @generated
     * @ordered
     */
  protected boolean orientation = ORIENTATION_EDEFAULT;

  /**
     * This is true if the Orientation attribute has been set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  protected boolean orientationESet;

  /**
     * The default value of the '{@link #getWindowFlags() <em>Window Flags</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getWindowFlags()
     * @generated
     * @ordered
     */
  protected static final int WINDOW_FLAGS_EDEFAULT = 0;

  /**
     * The cached value of the '{@link #getWindowFlags() <em>Window Flags</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getWindowFlags()
     * @generated
     * @ordered
     */
  protected int windowFlags = WINDOW_FLAGS_EDEFAULT;

  /**
     * This is true if the Window Flags attribute has been set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  protected boolean windowFlagsESet;

  /**
     * The default value of the '{@link #getWindowsizeEnclosed() <em>Windowsize Enclosed</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getWindowsizeEnclosed()
     * @generated
     * @ordered
     */
  protected static final int WINDOWSIZE_ENCLOSED_EDEFAULT = 0;

  /**
     * The cached value of the '{@link #getWindowsizeEnclosed() <em>Windowsize Enclosed</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getWindowsizeEnclosed()
     * @generated
     * @ordered
     */
  protected int windowsizeEnclosed = WINDOWSIZE_ENCLOSED_EDEFAULT;

  /**
     * This is true if the Windowsize Enclosed attribute has been set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  protected boolean windowsizeEnclosedESet;

  /**
     * The default value of the '{@link #getWindowsizeInside() <em>Windowsize Inside</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getWindowsizeInside()
     * @generated
     * @ordered
     */
  protected static final int WINDOWSIZE_INSIDE_EDEFAULT = 0;

  /**
     * The cached value of the '{@link #getWindowsizeInside() <em>Windowsize Inside</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getWindowsizeInside()
     * @generated
     * @ordered
     */
  protected int windowsizeInside = WINDOWSIZE_INSIDE_EDEFAULT;

  /**
     * This is true if the Windowsize Inside attribute has been set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  protected boolean windowsizeInsideESet;

  /**
     * The default value of the '{@link #getWindowsizeLeft() <em>Windowsize Left</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getWindowsizeLeft()
     * @generated
     * @ordered
     */
  protected static final int WINDOWSIZE_LEFT_EDEFAULT = 0;

  /**
     * The cached value of the '{@link #getWindowsizeLeft() <em>Windowsize Left</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getWindowsizeLeft()
     * @generated
     * @ordered
     */
  protected int windowsizeLeft = WINDOWSIZE_LEFT_EDEFAULT;

  /**
     * This is true if the Windowsize Left attribute has been set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  protected boolean windowsizeLeftESet;

  /**
     * The default value of the '{@link #getWindowsizeRight() <em>Windowsize Right</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getWindowsizeRight()
     * @generated
     * @ordered
     */
  protected static final int WINDOWSIZE_RIGHT_EDEFAULT = 0;

  /**
     * The cached value of the '{@link #getWindowsizeRight() <em>Windowsize Right</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #getWindowsizeRight()
     * @generated
     * @ordered
     */
  protected int windowsizeRight = WINDOWSIZE_RIGHT_EDEFAULT;

  /**
     * This is true if the Windowsize Right attribute has been set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
  protected boolean windowsizeRightESet;

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  protected FeatureObjectMatcherXMLImpl()
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
        return ConfigPackage.Literals.FEATURE_OBJECT_MATCHER_XML;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public boolean isDistance()
  {
        return distance;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setDistance(boolean newDistance)
  {
        boolean oldDistance = distance;
        distance = newDistance;
        boolean oldDistanceESet = distanceESet;
        distanceESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.FEATURE_OBJECT_MATCHER_XML__DISTANCE, oldDistance, distance, !oldDistanceESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void unsetDistance()
  {
        boolean oldDistance = distance;
        boolean oldDistanceESet = distanceESet;
        distance = DISTANCE_EDEFAULT;
        distanceESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ConfigPackage.FEATURE_OBJECT_MATCHER_XML__DISTANCE, oldDistance, DISTANCE_EDEFAULT, oldDistanceESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public boolean isSetDistance()
  {
        return distanceESet;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public boolean isOrientation()
  {
        return orientation;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setOrientation(boolean newOrientation)
  {
        boolean oldOrientation = orientation;
        orientation = newOrientation;
        boolean oldOrientationESet = orientationESet;
        orientationESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.FEATURE_OBJECT_MATCHER_XML__ORIENTATION, oldOrientation, orientation, !oldOrientationESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void unsetOrientation()
  {
        boolean oldOrientation = orientation;
        boolean oldOrientationESet = orientationESet;
        orientation = ORIENTATION_EDEFAULT;
        orientationESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ConfigPackage.FEATURE_OBJECT_MATCHER_XML__ORIENTATION, oldOrientation, ORIENTATION_EDEFAULT, oldOrientationESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public boolean isSetOrientation()
  {
        return orientationESet;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public int getWindowFlags()
  {
        return windowFlags;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setWindowFlags(int newWindowFlags)
  {
        int oldWindowFlags = windowFlags;
        windowFlags = newWindowFlags;
        boolean oldWindowFlagsESet = windowFlagsESet;
        windowFlagsESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOW_FLAGS, oldWindowFlags, windowFlags, !oldWindowFlagsESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void unsetWindowFlags()
  {
        int oldWindowFlags = windowFlags;
        boolean oldWindowFlagsESet = windowFlagsESet;
        windowFlags = WINDOW_FLAGS_EDEFAULT;
        windowFlagsESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOW_FLAGS, oldWindowFlags, WINDOW_FLAGS_EDEFAULT, oldWindowFlagsESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public boolean isSetWindowFlags()
  {
        return windowFlagsESet;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public int getWindowsizeEnclosed()
  {
        return windowsizeEnclosed;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setWindowsizeEnclosed(int newWindowsizeEnclosed)
  {
        int oldWindowsizeEnclosed = windowsizeEnclosed;
        windowsizeEnclosed = newWindowsizeEnclosed;
        boolean oldWindowsizeEnclosedESet = windowsizeEnclosedESet;
        windowsizeEnclosedESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_ENCLOSED, oldWindowsizeEnclosed, windowsizeEnclosed, !oldWindowsizeEnclosedESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void unsetWindowsizeEnclosed()
  {
        int oldWindowsizeEnclosed = windowsizeEnclosed;
        boolean oldWindowsizeEnclosedESet = windowsizeEnclosedESet;
        windowsizeEnclosed = WINDOWSIZE_ENCLOSED_EDEFAULT;
        windowsizeEnclosedESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_ENCLOSED, oldWindowsizeEnclosed, WINDOWSIZE_ENCLOSED_EDEFAULT, oldWindowsizeEnclosedESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public boolean isSetWindowsizeEnclosed()
  {
        return windowsizeEnclosedESet;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public int getWindowsizeInside()
  {
        return windowsizeInside;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setWindowsizeInside(int newWindowsizeInside)
  {
        int oldWindowsizeInside = windowsizeInside;
        windowsizeInside = newWindowsizeInside;
        boolean oldWindowsizeInsideESet = windowsizeInsideESet;
        windowsizeInsideESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_INSIDE, oldWindowsizeInside, windowsizeInside, !oldWindowsizeInsideESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void unsetWindowsizeInside()
  {
        int oldWindowsizeInside = windowsizeInside;
        boolean oldWindowsizeInsideESet = windowsizeInsideESet;
        windowsizeInside = WINDOWSIZE_INSIDE_EDEFAULT;
        windowsizeInsideESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_INSIDE, oldWindowsizeInside, WINDOWSIZE_INSIDE_EDEFAULT, oldWindowsizeInsideESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public boolean isSetWindowsizeInside()
  {
        return windowsizeInsideESet;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public int getWindowsizeLeft()
  {
        return windowsizeLeft;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setWindowsizeLeft(int newWindowsizeLeft)
  {
        int oldWindowsizeLeft = windowsizeLeft;
        windowsizeLeft = newWindowsizeLeft;
        boolean oldWindowsizeLeftESet = windowsizeLeftESet;
        windowsizeLeftESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_LEFT, oldWindowsizeLeft, windowsizeLeft, !oldWindowsizeLeftESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void unsetWindowsizeLeft()
  {
        int oldWindowsizeLeft = windowsizeLeft;
        boolean oldWindowsizeLeftESet = windowsizeLeftESet;
        windowsizeLeft = WINDOWSIZE_LEFT_EDEFAULT;
        windowsizeLeftESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_LEFT, oldWindowsizeLeft, WINDOWSIZE_LEFT_EDEFAULT, oldWindowsizeLeftESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public boolean isSetWindowsizeLeft()
  {
        return windowsizeLeftESet;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public int getWindowsizeRight()
  {
        return windowsizeRight;
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void setWindowsizeRight(int newWindowsizeRight)
  {
        int oldWindowsizeRight = windowsizeRight;
        windowsizeRight = newWindowsizeRight;
        boolean oldWindowsizeRightESet = windowsizeRightESet;
        windowsizeRightESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_RIGHT, oldWindowsizeRight, windowsizeRight, !oldWindowsizeRightESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public void unsetWindowsizeRight()
  {
        int oldWindowsizeRight = windowsizeRight;
        boolean oldWindowsizeRightESet = windowsizeRightESet;
        windowsizeRight = WINDOWSIZE_RIGHT_EDEFAULT;
        windowsizeRightESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_RIGHT, oldWindowsizeRight, WINDOWSIZE_RIGHT_EDEFAULT, oldWindowsizeRightESet));
    }

  /**
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public boolean isSetWindowsizeRight()
  {
        return windowsizeRightESet;
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
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__DISTANCE:
                return isDistance() ? Boolean.TRUE : Boolean.FALSE;
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__ORIENTATION:
                return isOrientation() ? Boolean.TRUE : Boolean.FALSE;
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOW_FLAGS:
                return Integer.valueOf(getWindowFlags());
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_ENCLOSED:
                return Integer.valueOf(getWindowsizeEnclosed());
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_INSIDE:
                return Integer.valueOf(getWindowsizeInside());
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_LEFT:
                return Integer.valueOf(getWindowsizeLeft());
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_RIGHT:
                return Integer.valueOf(getWindowsizeRight());
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
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__DISTANCE:
                setDistance(((Boolean)newValue).booleanValue());
                return;
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__ORIENTATION:
                setOrientation(((Boolean)newValue).booleanValue());
                return;
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOW_FLAGS:
                setWindowFlags(((Integer)newValue).intValue());
                return;
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_ENCLOSED:
                setWindowsizeEnclosed(((Integer)newValue).intValue());
                return;
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_INSIDE:
                setWindowsizeInside(((Integer)newValue).intValue());
                return;
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_LEFT:
                setWindowsizeLeft(((Integer)newValue).intValue());
                return;
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_RIGHT:
                setWindowsizeRight(((Integer)newValue).intValue());
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
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__DISTANCE:
                unsetDistance();
                return;
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__ORIENTATION:
                unsetOrientation();
                return;
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOW_FLAGS:
                unsetWindowFlags();
                return;
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_ENCLOSED:
                unsetWindowsizeEnclosed();
                return;
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_INSIDE:
                unsetWindowsizeInside();
                return;
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_LEFT:
                unsetWindowsizeLeft();
                return;
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_RIGHT:
                unsetWindowsizeRight();
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
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__DISTANCE:
                return isSetDistance();
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__ORIENTATION:
                return isSetOrientation();
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOW_FLAGS:
                return isSetWindowFlags();
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_ENCLOSED:
                return isSetWindowsizeEnclosed();
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_INSIDE:
                return isSetWindowsizeInside();
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_LEFT:
                return isSetWindowsizeLeft();
            case ConfigPackage.FEATURE_OBJECT_MATCHER_XML__WINDOWSIZE_RIGHT:
                return isSetWindowsizeRight();
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

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (distance: ");
        if (distanceESet) result.append(distance); else result.append("<unset>");
        result.append(", orientation: ");
        if (orientationESet) result.append(orientation); else result.append("<unset>");
        result.append(", windowFlags: ");
        if (windowFlagsESet) result.append(windowFlags); else result.append("<unset>");
        result.append(", windowsizeEnclosed: ");
        if (windowsizeEnclosedESet) result.append(windowsizeEnclosed); else result.append("<unset>");
        result.append(", windowsizeInside: ");
        if (windowsizeInsideESet) result.append(windowsizeInside); else result.append("<unset>");
        result.append(", windowsizeLeft: ");
        if (windowsizeLeftESet) result.append(windowsizeLeft); else result.append("<unset>");
        result.append(", windowsizeRight: ");
        if (windowsizeRightESet) result.append(windowsizeRight); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} //FeatureObjectMatcherXMLImpl