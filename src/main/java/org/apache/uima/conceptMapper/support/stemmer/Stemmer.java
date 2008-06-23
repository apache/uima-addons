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
package org.apache.uima.conceptMapper.support.stemmer;

import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 * Interface for stemmer. Two methods are defined:
 * 
 * {@link #stem stem()}, which takes a single token in as a <code>String</code> and returns the
 * stemmed token as a <code>String</code>. {@link #stem initialize ()}, which takes a dictionary
 * path as a <code>String</code> loads it.
 * 
 * 
 */
public interface Stemmer {

  /**
   * Take a single token in as a <code>String</code>, stem the token, and return the stemmed
   * token as a <code>String</code>.
   * 
   * @param token
   *          the input token to stem
   * @return the stemmed token
   */
  public String stem(String token);

  /**
   * Initialize the stemmer with a dictionary
   */
  public void initialize(String dictionary) throws FileNotFoundException, ParseException;

}
