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

package org.apache.uima.simpleserver.output;

import java.util.List;

/*
 * represents one annotation taken for output, i.e. for example 
 * one tag of the XML output
 */
public interface ResultEntry {

  /*
   * represents the name of the output tag for this annotation
   */
  public String getEntryName();

  /*
   * gives an attribute value for the given attribute name
   */
  public String getAttriuteValue(String string);

  /*
   * a list of the attributes taken for output for this annotation, i.e.
   * attributes of the XML tag (in case we produce XML output)
   */
  public List<String> getAttributeNames();

  /*
   * text covered by the annotation
   */
  public String getCoveredText();
  
  public int getBegin();
  
  public int getEnd();
  
}