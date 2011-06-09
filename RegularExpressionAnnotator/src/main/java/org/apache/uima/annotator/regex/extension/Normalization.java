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
package org.apache.uima.annotator.regex.extension;


/**
 * The Normalization interface was add to implement a custom normalization
 * for feature values before they are assigned to an annotation. 
 */
public interface Normalization {
	
/**
 * Custom feature value normalization. This interface must be implemented to 
 * perform a custom normalization on the given input string.
 * 
 * @param input input string which should be normalized
 * 
 * @param ruleID rule ID of the matching rule
 * 
 * @return String - normalized input string 
 */
public String normalize(String input, String ruleID) throws Exception;
}