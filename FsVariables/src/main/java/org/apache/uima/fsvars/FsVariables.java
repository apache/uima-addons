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


package org.apache.uima.fsvars;

import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;

/**
 * Implements cross-annotator named feature structure variables.
 * 
 * <p>To use, a certain type and index must be declared in the processing pipeline.  Use the type
 * system and index declaration that come with this package.  There's also an annotator descriptor
 * that shows you how to import the type system and index specification into your own descriptor.
 * 
 * <p>Use {@link FsVariableFactory#newInstance() FsVariableFactory.newInstance()} to create a
 * <code>FsVariables</code> object.  On every process call to your annotator (or whenever there's
 * a new CAS; you can use this in other places than just annotators), you need to call
 * {@link #init(CAS) init(CAS)} to re-initialize the object to the next CAS. 
 */
public interface FsVariables {
  
  static final String INDEX_NAME = "org.apache.uima.fsvars";
  
  static final String TYPE_NAME = "org.apache.uima.FsVariable";
  
  static final String NAME_FEATURE_NAME = "name";
  
  static final String TYPE_FEATURE_NAME = "type"; 
  
  static final String VALUE_FEATURE_NAME = "value";
  
  /**
   * Check a CAS for FsVariable type system and index definition.  If this return <code>true</code>,
   * {@link #init(CAS) init(CAS)} will not throw an exception.
   * @param cas The CAS to check.
   * @return <code>true</code> iff CAS contains required type and index.
   */
  boolean checkCas(CAS cas);
  
  /**
   * Initialize <code>this</code> with current CAS.  This needs to be called on every process, before
   * any other APIs are called!
   * @param cas The current CAS.
   * @throws FsVariablesException When the CAS does not contain FsVariable type or index.
   */
  void init(CAS cas) throws FsVariablesException;
  
  /**
   * Declare a new FsVariable.  The variable must not yet exist, or an exception is thrown.  Use
   * {@link #isFsVariable(String) isFsVariable(String)} to check if a variable with the same name
   * already exists. 
   * @param name The name of the variable.
   * @param type The type of the variable.
   * @throws FsVariablesException When FsVariable <code>name</code> already exists.
   */
  void declareFsVariable(String name, Type type) throws FsVariablesException;
  
  /**
   * Check if a variable of that name exists.
   * @param name The name of the variable.
   * @return <code>true</code> iff a FsVariable of that name exists.
   */
  boolean isFsVariable(String name);
  
  /**
   * Get the type of an FsVariable.
   * @param name The name of the variable.
   * @return The type of the variable, if it exists.
   * @throws FsVariablesException If no variable of that name exists, or somehow the name of the
   * variable's type does not resolve to a type in the type system.
   */
  Type getFsVariableType(String name) throws FsVariablesException;
  
  /**
   * Return a list of all variable names that are currently declared.
   * @return A (sorted) list of strings of all variable names.
   */
  List listFsVariables();
  
  /**
   * Return a list of all variable names of those variables whose type is a subtype of the type
   * parameter.
   * @param type The type of variables we want to see.
   * @return A (sorted) list of strings of variable names.
   */
  List listFsVariables(Type type);
  
  /**
   * Get the FeatureStructure value of a variable.
   * @param name The name of the variable.
   * @return The FS value of the variable.
   * @throws FsVariablesException When no variable of <code>name</code> exists.
   */
  FeatureStructure getVariableValue(String name) throws FsVariablesException;
  
  /**
   * Set the value of an existing variable.
   * @param name The name of the variable.
   * @param fs The value to set.
   * @throws FsVariablesException When no variable of <code>name</code> exists.
   */
  void setVariable(String name, FeatureStructure fs) throws FsVariablesException;

}

