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

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.simpleserver.output.Result;
import org.apache.uima.simpleserver.output.ResultEntry;

/*
 * Implementation of the Result interface.
 * No sophisticated logic, just a bean-like class.
 */
public class ResultImpl implements Result {

  private List<ResultEntry> resultEntries = null;

  private String text = null;

  public ResultImpl(List<ResultEntry> resultEntries, String text) {
    super();
    this.resultEntries = resultEntries;
    this.text = text;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.uimaserver.output.Result#getResultEntries()
   */
  public List<ResultEntry> getResultEntries() {
    return this.resultEntries;
  }

  public ResultImpl() {
    super();
    this.resultEntries = new ArrayList<ResultEntry>();
  }

  public String getText() {
    return this.text;
  }

}
