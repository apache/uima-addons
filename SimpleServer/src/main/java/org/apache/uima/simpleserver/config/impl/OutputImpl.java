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

import java.util.Collections;
import java.util.List;

import org.apache.uima.simpleserver.config.Output;

/*
 * implementation of the OutputSpecification interface.
 * A bean-like class, no special logic here, except for constructor method.
 */

public class OutputImpl implements Output {

  // feature path
  private final List<String> features;

  // attribute name
  private final String attribute;

  private final String shortDescription;

  private final String longDescription;

  @SuppressWarnings("unused")
  private OutputImpl() {
    this(null, null, null, null);
  }

  public OutputImpl(List<String> features, String attribute) {
    this(features, attribute, null, null);
  }

  public OutputImpl(List<String> features, String attribute, String shortDescription,
      String longDescription) {
    this.features = features;
    this.attribute = attribute;
    this.shortDescription = shortDescription;
    this.longDescription = longDescription;
  }

  public String getLongDescription() {
    return this.longDescription;
  }

  public String getShortDescription() {
    return this.shortDescription;
  }

  public String getAttribute() {
    return this.attribute;
  }

  public List<String> getFeaturePath() {
    return Collections.unmodifiableList(this.features);
  }

}
