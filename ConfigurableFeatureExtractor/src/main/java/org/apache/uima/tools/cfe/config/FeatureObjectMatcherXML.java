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
package org.apache.uima.tools.cfe.config;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Object Matcher XML</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#isDistance <em>Distance</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#isOrientation <em>Orientation</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowFlags <em>Window Flags</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowsizeEnclosed <em>Windowsize Enclosed</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowsizeInside <em>Windowsize Inside</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowsizeLeft <em>Windowsize Left</em>}</li>
 *   <li>{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowsizeRight <em>Windowsize Right</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.uima.tools.cfe.config.ConfigPackage#getFeatureObjectMatcherXML()
 * @model extendedMetaData="name='FeatureObjectMatcherXML' kind='elementOnly'"
 * @generated
 */
public interface FeatureObjectMatcherXML extends PartialObjectMatcherXML
{
  /**
     * Returns the value of the '<em><b>Distance</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Distance</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Distance</em>' attribute.
     * @see #isSetDistance()
     * @see #unsetDistance()
     * @see #setDistance(boolean)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getFeatureObjectMatcherXML_Distance()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='distance'"
     * @generated
     */
  boolean isDistance();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#isDistance <em>Distance</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Distance</em>' attribute.
     * @see #isSetDistance()
     * @see #unsetDistance()
     * @see #isDistance()
     * @generated
     */
  void setDistance(boolean value);

  /**
     * Unsets the value of the '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#isDistance <em>Distance</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isSetDistance()
     * @see #isDistance()
     * @see #setDistance(boolean)
     * @generated
     */
  void unsetDistance();

  /**
     * Returns whether the value of the '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#isDistance <em>Distance</em>}' attribute is set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return whether the value of the '<em>Distance</em>' attribute is set.
     * @see #unsetDistance()
     * @see #isDistance()
     * @see #setDistance(boolean)
     * @generated
     */
  boolean isSetDistance();

  /**
     * Returns the value of the '<em><b>Orientation</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Orientation</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Orientation</em>' attribute.
     * @see #isSetOrientation()
     * @see #unsetOrientation()
     * @see #setOrientation(boolean)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getFeatureObjectMatcherXML_Orientation()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='orientation'"
     * @generated
     */
  boolean isOrientation();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#isOrientation <em>Orientation</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Orientation</em>' attribute.
     * @see #isSetOrientation()
     * @see #unsetOrientation()
     * @see #isOrientation()
     * @generated
     */
  void setOrientation(boolean value);

  /**
     * Unsets the value of the '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#isOrientation <em>Orientation</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isSetOrientation()
     * @see #isOrientation()
     * @see #setOrientation(boolean)
     * @generated
     */
  void unsetOrientation();

  /**
     * Returns whether the value of the '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#isOrientation <em>Orientation</em>}' attribute is set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return whether the value of the '<em>Orientation</em>' attribute is set.
     * @see #unsetOrientation()
     * @see #isOrientation()
     * @see #setOrientation(boolean)
     * @generated
     */
  boolean isSetOrientation();

  /**
     * Returns the value of the '<em><b>Window Flags</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 
     *     						in class CommonFeatureMatcher
     *     						the following flg constants are defined
     * 
     *     						CONSTANT_LEFTLEFT // 1 feature
     *     						annotation starts left from the target ends
     *     						left from the target
     * 
     *     						CONSTANT_LEFTINSIDE // 2 feature
     *     						annotation starts left from the target ends
     *     						inside the target
     * 
     *     						CONSTANT_LEFTRIGHT  // 4 feature
     *     						annotation starts left from the target ends
     *     						right from the target
     * 
     *     						CONSTANT_INSIDEINSIDE // 8 feature
     *     						annotation starts inside the target ends
     *     						inside the target
     * 
     *     						CONSTANT_INSIDERIGHT // 16 feature
     *     						annotation starts inside the target ends
     *     						right from the target
     * 
     *     						CONSTANT_RIGHTRIGHT  // 32 feature
     *     						annotation starts right from the target ends
     *     						right from the target
     * 
     *     						// any annotation that starts left from the
     *     						target
     * 
     *     						CONSTANT_STARTSLEFT = CONSTANT_LEFTLEFT |
     *     						CONSTANT_LEFTINSIDE | CONSTANT_LEFTRIGHT;
     *     						// 7
     * 
     *     						// any annotation that starts inside the
     *     						target
     * 
     *     						CONSTANT_STARTSINSIDE =
     *     						CONSTANT_INSIDEINSIDE|CONSTANT_INSIDERIGHT;
     *     						// 24
     * 
     *     						// any annotation that starts right from the
     *     						target
     * 
     *     						CONSTANT_STARTSRIGHT = CONSTANT_RIGHTRIGHT;
     *     						// 32
     * 
     *     						// any annotation that is within the span
     *     						of enclosing annotation
     * 
     *     						CONSTANT_ANYENCLOSED = CONSTANT_STARTSLEFT |
     *     						CONSTANT_STARTSINSIDE |
     *     						CONSTANT_STARTSRIGHT;
     *     						// 63
     *     					
     * <!-- end-model-doc -->
     * @return the value of the '<em>Window Flags</em>' attribute.
     * @see #isSetWindowFlags()
     * @see #unsetWindowFlags()
     * @see #setWindowFlags(int)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getFeatureObjectMatcherXML_WindowFlags()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int"
     *        extendedMetaData="kind='attribute' name='windowFlags'"
     * @generated
     */
  int getWindowFlags();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowFlags <em>Window Flags</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Window Flags</em>' attribute.
     * @see #isSetWindowFlags()
     * @see #unsetWindowFlags()
     * @see #getWindowFlags()
     * @generated
     */
  void setWindowFlags(int value);

  /**
     * Unsets the value of the '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowFlags <em>Window Flags</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isSetWindowFlags()
     * @see #getWindowFlags()
     * @see #setWindowFlags(int)
     * @generated
     */
  void unsetWindowFlags();

  /**
     * Returns whether the value of the '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowFlags <em>Window Flags</em>}' attribute is set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return whether the value of the '<em>Window Flags</em>' attribute is set.
     * @see #unsetWindowFlags()
     * @see #getWindowFlags()
     * @see #setWindowFlags(int)
     * @generated
     */
  boolean isSetWindowFlags();

  /**
     * Returns the value of the '<em><b>Windowsize Enclosed</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Windowsize Enclosed</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Windowsize Enclosed</em>' attribute.
     * @see #isSetWindowsizeEnclosed()
     * @see #unsetWindowsizeEnclosed()
     * @see #setWindowsizeEnclosed(int)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getFeatureObjectMatcherXML_WindowsizeEnclosed()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int"
     *        extendedMetaData="kind='attribute' name='windowsizeEnclosed'"
     * @generated
     */
  int getWindowsizeEnclosed();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowsizeEnclosed <em>Windowsize Enclosed</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Windowsize Enclosed</em>' attribute.
     * @see #isSetWindowsizeEnclosed()
     * @see #unsetWindowsizeEnclosed()
     * @see #getWindowsizeEnclosed()
     * @generated
     */
  void setWindowsizeEnclosed(int value);

  /**
     * Unsets the value of the '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowsizeEnclosed <em>Windowsize Enclosed</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isSetWindowsizeEnclosed()
     * @see #getWindowsizeEnclosed()
     * @see #setWindowsizeEnclosed(int)
     * @generated
     */
  void unsetWindowsizeEnclosed();

  /**
     * Returns whether the value of the '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowsizeEnclosed <em>Windowsize Enclosed</em>}' attribute is set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return whether the value of the '<em>Windowsize Enclosed</em>' attribute is set.
     * @see #unsetWindowsizeEnclosed()
     * @see #getWindowsizeEnclosed()
     * @see #setWindowsizeEnclosed(int)
     * @generated
     */
  boolean isSetWindowsizeEnclosed();

  /**
     * Returns the value of the '<em><b>Windowsize Inside</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Windowsize Inside</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Windowsize Inside</em>' attribute.
     * @see #isSetWindowsizeInside()
     * @see #unsetWindowsizeInside()
     * @see #setWindowsizeInside(int)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getFeatureObjectMatcherXML_WindowsizeInside()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int"
     *        extendedMetaData="kind='attribute' name='windowsizeInside'"
     * @generated
     */
  int getWindowsizeInside();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowsizeInside <em>Windowsize Inside</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Windowsize Inside</em>' attribute.
     * @see #isSetWindowsizeInside()
     * @see #unsetWindowsizeInside()
     * @see #getWindowsizeInside()
     * @generated
     */
  void setWindowsizeInside(int value);

  /**
     * Unsets the value of the '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowsizeInside <em>Windowsize Inside</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isSetWindowsizeInside()
     * @see #getWindowsizeInside()
     * @see #setWindowsizeInside(int)
     * @generated
     */
  void unsetWindowsizeInside();

  /**
     * Returns whether the value of the '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowsizeInside <em>Windowsize Inside</em>}' attribute is set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return whether the value of the '<em>Windowsize Inside</em>' attribute is set.
     * @see #unsetWindowsizeInside()
     * @see #getWindowsizeInside()
     * @see #setWindowsizeInside(int)
     * @generated
     */
  boolean isSetWindowsizeInside();

  /**
     * Returns the value of the '<em><b>Windowsize Left</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Windowsize Left</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Windowsize Left</em>' attribute.
     * @see #isSetWindowsizeLeft()
     * @see #unsetWindowsizeLeft()
     * @see #setWindowsizeLeft(int)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getFeatureObjectMatcherXML_WindowsizeLeft()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int"
     *        extendedMetaData="kind='attribute' name='windowsizeLeft'"
     * @generated
     */
  int getWindowsizeLeft();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowsizeLeft <em>Windowsize Left</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Windowsize Left</em>' attribute.
     * @see #isSetWindowsizeLeft()
     * @see #unsetWindowsizeLeft()
     * @see #getWindowsizeLeft()
     * @generated
     */
  void setWindowsizeLeft(int value);

  /**
     * Unsets the value of the '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowsizeLeft <em>Windowsize Left</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isSetWindowsizeLeft()
     * @see #getWindowsizeLeft()
     * @see #setWindowsizeLeft(int)
     * @generated
     */
  void unsetWindowsizeLeft();

  /**
     * Returns whether the value of the '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowsizeLeft <em>Windowsize Left</em>}' attribute is set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return whether the value of the '<em>Windowsize Left</em>' attribute is set.
     * @see #unsetWindowsizeLeft()
     * @see #getWindowsizeLeft()
     * @see #setWindowsizeLeft(int)
     * @generated
     */
  boolean isSetWindowsizeLeft();

  /**
     * Returns the value of the '<em><b>Windowsize Right</b></em>' attribute.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Windowsize Right</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Windowsize Right</em>' attribute.
     * @see #isSetWindowsizeRight()
     * @see #unsetWindowsizeRight()
     * @see #setWindowsizeRight(int)
     * @see org.apache.uima.tools.cfe.config.ConfigPackage#getFeatureObjectMatcherXML_WindowsizeRight()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int"
     *        extendedMetaData="kind='attribute' name='windowsizeRight'"
     * @generated
     */
  int getWindowsizeRight();

  /**
     * Sets the value of the '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowsizeRight <em>Windowsize Right</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Windowsize Right</em>' attribute.
     * @see #isSetWindowsizeRight()
     * @see #unsetWindowsizeRight()
     * @see #getWindowsizeRight()
     * @generated
     */
  void setWindowsizeRight(int value);

  /**
     * Unsets the value of the '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowsizeRight <em>Windowsize Right</em>}' attribute.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @see #isSetWindowsizeRight()
     * @see #getWindowsizeRight()
     * @see #setWindowsizeRight(int)
     * @generated
     */
  void unsetWindowsizeRight();

  /**
     * Returns whether the value of the '{@link org.apache.uima.tools.cfe.config.FeatureObjectMatcherXML#getWindowsizeRight <em>Windowsize Right</em>}' attribute is set.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return whether the value of the '<em>Windowsize Right</em>' attribute is set.
     * @see #unsetWindowsizeRight()
     * @see #getWindowsizeRight()
     * @see #setWindowsizeRight(int)
     * @generated
     */
  boolean isSetWindowsizeRight();

} // FeatureObjectMatcherXML