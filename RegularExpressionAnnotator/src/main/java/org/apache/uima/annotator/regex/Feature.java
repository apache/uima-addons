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
package org.apache.uima.annotator.regex;

/**
 * The Feature interface provides access to a Regex Feature definition
 */
public interface Feature {
	
  public static final int STRING_FEATURE = 1;
  public static final int INTEGER_FEATURE = 2;
  public static final int FLOAT_FEATURE = 3;
  public static final int REFERENCE_FEATURE = 4;
  public static final int CONFIDENCE_FEATURE = 5;
  public static final int RULEID_FEATURE = 6;
  
  public static final int CUSTOM_NORMALIZATION = 1;
  public static final int TO_LOWER_NORMALIZATION = 2;
  public static final int TO_UPPER_NORMALIZATION = 3;
  public static final int TRIM_NORMALIZATION = 4;

  /**
	 * Get the feature name of this feature
	 * 
	 * @return returns the feature name
	 */
	public String getName();

	/**
	 * Get the feature type.
	 * 
	 * @return returns the feature type.
	 */
	public int getType();

	/**
	 * Get the feature value of this feature.
	 * 
	 * @return returns the feature value of this feature.
	 */
	public String getValue();

  /**
   * Get the UIMA feature value of this feature object
   * 
   * @return returns the UIMA feature object.
   */
  public org.apache.uima.cas.Feature getFeature();
  
  /**
   * Get the normalization of the input string based on the specified
   * normalization for this feature. If no normalization was specified,
   * the input string is returned.
   * 
   * @return returns the normalization type for this feature.
   */
  public String normalize(String input, String ruleId) throws Exception;

}