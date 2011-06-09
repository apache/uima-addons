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

import org.apache.uima.simpleserver.config.impl.AndFilterImpl;
import org.apache.uima.simpleserver.config.impl.ConditionImpl;
import org.apache.uima.simpleserver.config.impl.OrFilterImpl;
import org.apache.uima.simpleserver.config.impl.OutputImpl;
import org.apache.uima.simpleserver.config.impl.SimpleFilterImpl;
import org.apache.uima.simpleserver.config.impl.ServerSpecImpl;
import org.apache.uima.simpleserver.config.impl.TypeMapImpl;

/**
 * Creates config objects. This class is used internally when reading in config files. Similarly, it
 * may be used to create config objects programmatically.
 */
public final class ConfigFactory {

  /**
   * Create a new server specification.
   * 
   * @param shortDescription
   *                Short description of service.
   * @param longDescription
   *                Verbose description of service.
   * @param doOutputAll
   *                Output all types, regardless of individual type maps.
   * @return A new service config object.
   */
  public static ServerSpec newServerSpec(String shortDescription, String longDescription,
      boolean doOutputAll) {
    return new ServerSpecImpl(shortDescription, longDescription, doOutputAll);
  }

  /**
   * Create a new type map.
   * 
   * @param typeName
   *                The UIMA type name (input).
   * @param outputTag
   *                A XML tag name (output).
   * @param coveredText
   *                Output covered text, yes or no.
   * @return A new type map object. Can be added to a server spec.
   */
  public static TypeMap newTypeMap(String typeName, Filter filter, String outputTag,
      boolean coveredText) {
    return new TypeMapImpl(typeName, filter, outputTag, coveredText, false, null, null);
  }

  /**
   * Create a new type map.
   * 
   * @param typeName
   *                The UIMA type name (input).
   * @param outputTag
   *                A XML tag name (output).
   * @param coveredText
   *                Output covered text, yes or no.
   * @param shortDescription
   *                Short description of map.
   * @param longDescription
   *                Verbose description of map.
   * @return A new type map object. Can be added to a server spec.
   */
  public static TypeMap newTypeMap(String typeName, Filter filter, String outputTag,
      boolean coveredText, boolean outputAll, String shortDescription, String longDescription) {
    return new TypeMapImpl(typeName, filter, outputTag, coveredText, outputAll, shortDescription,
        longDescription);
  }

  /**
   * Create a new output mapping.
   * 
   * @param path
   *                Feature path whose value will be output.
   * @param attribute
   *                XML attribute that will contain the output.
   * @param shortDescription
   *                A short description of the output mapping.
   * @param longDescription
   *                A verbose description of the output mapping.
   * @return A new output mapping. Can be added to a type map.
   */
  public static Output newOutput(List<String> path, String attribute, String shortDescription,
      String longDescription) {
    return new OutputImpl(path, attribute, shortDescription, longDescription);
  }

  /**
   * Create a new Filter.
   * 
   * @param featurePath
   *                Feature path whose value the filter operates on. Must not be null.
   * @param condition
   *                The condition the path's value must satisfy.
   * @return A new Filter.
   */
  public static SimpleFilter newSimpleFilter(List<String> featurePath, Condition condition) {
    return new SimpleFilterImpl(featurePath, condition);
  }

  /**
   * Create a new filter conjunction.
   * 
   * @return A new filter conjunction.
   */
  public static AndFilter newAndFilter() {
    return new AndFilterImpl();
  }

  /**
   * Create a new filter disjunction.
   * 
   * @return A new filter disjunction.
   */
  public static OrFilter newOrFilter() {
    return new OrFilterImpl();
  }

  /**
   * Create new Condition.
   * 
   * @param type
   *                The condition's type (not null, equals etc.).
   * @param value
   *                Value for equality constraints.
   * @return A new Condition.
   */
  public static Condition newCondition(FilterOp type, String value) {
    return new ConditionImpl(type, value);
  }

  /**
   * Create new Condition with null value.
   * 
   * @param type
   *                The condition's type (not null, etc.).
   * @return A new Condition.
   */
  public static Condition newCondition(FilterOp type) {
    return newCondition(type, null);
  }

}
