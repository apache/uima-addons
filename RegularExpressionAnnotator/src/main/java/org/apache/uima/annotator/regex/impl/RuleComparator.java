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
package org.apache.uima.annotator.regex.impl;

import java.io.Serializable;
import java.util.Comparator;

import org.apache.uima.annotator.regex.Rule;

/**
 *
 */
public class RuleComparator implements Comparator<Rule>, Serializable {

  private static final long serialVersionUID = -1327195437752705633L;

/* (non-Javadoc)
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  public int compare(Rule r1, Rule r2) {
    
    Rule rule1 = r1;
    Rule rule2 = r2;
    
    if(rule1.getConfidence() < rule2.getConfidence()) {
       return 1;
    }
    else if (rule1.getConfidence() > rule2.getConfidence()) {
       return -1;
    }
    else {
       return 0;
    }
 }
  
}
