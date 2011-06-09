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

/**
 * Represents a type map in a service spec.
 */
public interface TypeMap {

  /**
   * Get the filter for this map.
   * 
   * @return This map's filter; may be <code>null</code>.
   */
  public Filter getFilter();

  /**
   * Get the "output all" switch. If this is set to true, output all simple-valued features of the
   * given type.  Defaults to <code>false</code>.
   * 
   * @return The "output all" switch.
   */
  public boolean getOutputAll();

  /**
   * Get list of output specs.
   * 
   * @return This map's output specs.
   */
  public List<Output> getOutputs();

  /**
   * Add an output spec.
   * 
   * @param output
   */
  public void addOutput(Output output);

  /**
   * Get the output tag that the type name is mapped to.
   * 
   * @return This type map's output tag.
   */
  public String getOutputTag();

  /**
   * Get the type name.
   * 
   * @return This map's type name; objects of this type will be considered for output.
   */
  public String getTypeName();

  /**
   * Get the switch for covered text.
   * 
   * @return Whether this map outputs the covered text for each annotation.
   */
  public boolean isOutputCoveredText();

  /**
   * @return The short description (one line) of this map; may be null.
   */
  public String getShortDescription();

  /**
   * @return The long description of this map; may be null.
   */
  public String getLongDescription();

}