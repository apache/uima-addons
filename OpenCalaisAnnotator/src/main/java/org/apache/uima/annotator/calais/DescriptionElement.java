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
package org.apache.uima.annotator.calais;

/**
 * 
 *
 */
public class DescriptionElement {

  private String aboutURL = null;
  private String typeURL = null;
  private String subjectURL = null;
  private int offset = 0;
  private int length = 0;
  
  
  public DescriptionElement() {
    this.aboutURL = null;
    this.typeURL = null;
    this.subjectURL = null;
    this.offset = 0;
    this.length = 0;
  }
  
  public String getAboutURL() {
    return this.aboutURL;
  }
  public void setAboutURL(String aboutURL) {
    this.aboutURL = aboutURL;
  }
  public String getTypeURL() {
    return this.typeURL;
  }
  public void setTypeURL(String typeURL) {
    this.typeURL = typeURL;
  }
  public String getSubjectURL() {
    return this.subjectURL;
  }
  public void setSubjectURL(String subjectURL) {
    this.subjectURL = subjectURL;
  }
  public int getOffset() {
    return this.offset;
  }
  public void setOffset(int offset) {
    this.offset = offset;
  }
  public int getLength() {
    return this.length;
  }
  public void setLength(int length) {
    this.length = length;
  }
  
}
