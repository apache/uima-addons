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

package org.apache.uima.simpleserver.config.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.uima.simpleserver.config.Filter;
import org.apache.uima.simpleserver.config.Output;
import org.apache.uima.simpleserver.config.TypeMap;

/*
 * Implementation of the TypeSpecification interface. Just a simple bean-like class without any
 * logic, exept for constructor method.
 */
public class TypeMapImpl implements TypeMap {

  private final String typeName;

  private final String outputTag;

  private boolean outputCoveredText;

  private boolean outputAll;

  private final Filter filter;

  private final List<Output> outputs = new ArrayList<Output>();

  private final String shortDescription;

  private final String longDescription;

  public TypeMapImpl(String typeName, Filter filter, String outputTag, boolean outputCoveredText,
      boolean outputAll, String shortDescription, String longDescription) {
    super();
    this.typeName = typeName;
    this.filter = filter;
    this.outputTag = outputTag;
    this.outputCoveredText = outputCoveredText;
    this.outputAll = outputAll;
    this.shortDescription = shortDescription;
    this.longDescription = longDescription;
  }

  public Filter getFilter() {
    return this.filter;
  }

  public List<Output> getOutputs() {
    return Collections.unmodifiableList(this.outputs);
  }

  public String getOutputTag() {
    return this.outputTag;
  }

  public String getTypeName() {
    return this.typeName;
  }

  public boolean isOutputCoveredText() {
    return this.outputCoveredText;
  }

  public String getLongDescription() {
    return this.longDescription;
  }

  public String getShortDescription() {
    return this.shortDescription;
  }

  public void addOutput(Output output) {
    this.outputs.add(output);
  }

  public boolean getOutputAll() {
    return this.outputAll;
  }

}
