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


package org.apache.uima.simpleserver.config.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.simpleserver.config.Filter;
import org.apache.uima.simpleserver.config.OrFilter;

/**
 * TODO: Create type commment.
 */
public class OrFilterImpl extends FilterImpl implements OrFilter {
  
  private final List<Filter> filters = new ArrayList<Filter>();

  public OrFilterImpl() {
    super(Filter.FilterType.OR);
  }

  public void addFilter(Filter filter) {
    this.filters.add(filter);
  }

  public List<Filter> getFilters() {
    return Collections.unmodifiableList(this.filters);
  }

  @Override
  public boolean match(FeatureStructure fs) {
    for (Filter filter: this.filters) {
      if (filter.match(fs)) {
        return true;
      }
    }
    return false;
  }
  

}
