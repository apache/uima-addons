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

import java.util.regex.Matcher;

/**
 * 
 */
public interface Position {
	
  public static final int START_LOCATION = 1;
  public static final int END_LOCATION = 2;
  
	/**
	 * Get the match group number of this position.
	 * 
	 * @return returns the match group number.
	 */
	int getMatchGroup();

 	/**
	 * Returns the index of the first or last character of the subsequence this position's
	 * match group covers, dependent on whether this position denotes the begin or end of
	 * the match group.
	 * 
	 * @param aMatcher The matcher that matched the subsequence.
	 * @return The index of the first or last character of this position's match group.
	 * 
	 * @see java.util.regex.Matcher#start(int)
	 * @see java.util.regex.Matcher#end(int)
	 */
	int getMatchPosition(Matcher aMatcher);
	
}