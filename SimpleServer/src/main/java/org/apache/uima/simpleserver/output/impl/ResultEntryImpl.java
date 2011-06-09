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

package org.apache.uima.simpleserver.output.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.uima.simpleserver.output.ResultEntry;

/*
 * Default implementation of the ResultEntry interface.
 * No sophisticated logic, just a bean-like class.
 */
public class ResultEntryImpl implements ResultEntry {

  private final String entryName;

  private final int begin;
  
  private final int end;

  private List<String> attributeNames;

  private Map<String, String> attributes;

  private String coveredText = null;
  
  public ResultEntryImpl(String entryName, int begin, int end) {
    super();
    this.entryName = entryName;
    this.begin = begin;
    this.end = end;
    this.attributes = new HashMap<String, String>();
    this.attributeNames = new LinkedList<String>();
  }

  public String getCoveredText() {
    return this.coveredText;
  }

  public void setCoveredText(String coveredText) {
    this.coveredText = coveredText;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.uimaserver.output.ResultEntry#getEntryName()
   */
  public String getEntryName() {
    return this.entryName;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.uimaserver.output.ResultEntry#getAttriuteValue(java.lang.String)
   */
  public String getAttriuteValue(String string) {
    return this.attributes.get(string);
  }

  public void setAttributeValue(String attributeName, String attributeValue) {
    if (!this.attributes.containsKey(attributeName)) {
      this.attributeNames.add(attributeName);
    }
    this.attributes.put(attributeName, attributeValue);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.uimaserver.output.ResultEntry#getAttributeNames()
   */
  public List<String> getAttributeNames() {
    return this.attributeNames;
  }

  /* (non-Javadoc)
   * @see org.apache.uima.simpleserver.output.ResultEntry#getBegin()
   */
  public int getBegin() {
    return this.begin;
  }

  /* (non-Javadoc)
   * @see org.apache.uima.simpleserver.output.ResultEntry#getEnd()
   */
  public int getEnd() {
    return this.end;
  }
}
