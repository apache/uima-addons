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

package org.apache.uima.simpleserver.config;

import java.util.List;

import org.apache.uima.cas.TypeSystem;
import org.apache.uima.simpleserver.SimpleServerException;

/**
 * Output specification for a simple service.
 */
public interface ServerSpec {

  /**
   * The path separator of feature paths: /.
   */
  public static final char PATH_SEPARATOR = '/';

  /**
   * Check setting to output all types.  If this returns true, individual type maps are ignored.
   * Defaults to <code>false</code>.
   * @return <code>true</code> iff output of all types is enabled.  
   */
  public boolean getOutputAll();
  
  /**
   * @return A list of type specifications. Each type specification represents output specifications
   *         for a single CAS type.
   */
  public List<TypeMap> getTypeSpecs();

  /**
   * Add a type map to an existing spec.
   * 
   * @param typeMap
   *                The input type map to be added.
   */
  public void addTypeMap(TypeMap typeMap);

  /**
   * Get the service's short description.
   * 
   * @return The short description.
   */
  public String getShortDescription();

  /**
   * Get the service's long description.
   * 
   * @return The long description.
   */
  public String getLongDescription();

  /**
   * Validate a service spec with respect to a type system. Check that types and feature paths
   * exist. None of the errors found here need to be considered fatal. A non-existing type simply
   * means a type spec that will never produce any output. Similarly, an invalid feature path means
   * a filter that will never match or an output spec that will never produce output. Server-side,
   * it's probably valid to simply log those errors and carry on.
   * 
   * @param typeSystem
   *                The type system to validate against.
   * @return A list of errors/warnings in the form of <code>SimpleServerException</code>s.
   */
  public List<SimpleServerException> validate(TypeSystem typeSystem);

}