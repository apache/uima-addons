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
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.impl.TypeSystemUtils;
import org.apache.uima.cas.impl.TypeSystemUtils.PathValid;
import org.apache.uima.simpleserver.SimpleServerException;
import org.apache.uima.simpleserver.config.AndFilter;
import org.apache.uima.simpleserver.config.Filter;
import org.apache.uima.simpleserver.config.OrFilter;
import org.apache.uima.simpleserver.config.Output;
import org.apache.uima.simpleserver.config.ServerSpec;
import org.apache.uima.simpleserver.config.SimpleFilter;
import org.apache.uima.simpleserver.config.TypeMap;
import org.apache.uima.simpleserver.config.Filter.FilterType;

/**
 * Implementation of ServerSpec interface.
 */
public class ServerSpecImpl implements ServerSpec {

  private final String shortDescription;

  private final String longDescription;
  
  private final boolean doOutputAll;

  private final List<TypeMap> typeMaps = new ArrayList<TypeMap>();

  public ServerSpecImpl(String shortDesc, String longDesc, boolean doOutputAll) {
    super();
    this.shortDescription = shortDesc;
    this.longDescription = longDesc;
    this.doOutputAll = doOutputAll;
  }

  public ServerSpecImpl(String shortDesc, String longDesc) {
    // Default doOutputAll to false;
    this(shortDesc, longDesc, false);
  }

  public void addTypeMap(TypeMap typeMap) {
    this.typeMaps.add(typeMap);
  }

  public String getLongDescription() {
    return this.longDescription;
  }

  public String getShortDescription() {
    return this.shortDescription;
  }

  public List<TypeMap> getTypeSpecs() {
    return this.typeMaps;
  }

  public List<SimpleServerException> validate(TypeSystem typeSystem) {
    List<SimpleServerException> exc = new ArrayList<SimpleServerException>();
    List<TypeMap> typeSpecs = this.getTypeSpecs();
    for (TypeMap typeMap : typeSpecs) {
      // Check that the type exists.
      Type type = typeSystem.getType(typeMap.getTypeName());
      if (type == null) {
        exc.add(new SimpleServerException(SimpleServerException.type_does_not_exist,
            new Object[] { typeMap.getTypeName() }));
        continue;
      }
      // Now go through the filter and outputs and check each path for validity
      Filter filter = typeMap.getFilter();
      if (filter != null) {
        checkFilter(filter, type, exc);
      }
      checkOutputs(typeMap.getOutputs(), type, exc);
    }
    return exc;
  }

  private static final void checkOutputs(List<Output> outputs, Type type,
      List<SimpleServerException> exc) {
    for (Output output : outputs) {
      List<String> fPath = output.getFeaturePath();
      if (TypeSystemUtils.isPathValid(type, fPath) == PathValid.NEVER) {
        exc.add(new SimpleServerException(SimpleServerException.path_never_valid, new Object[] {
            fPathToString(fPath), type }));
      }
    }
  }

  private static final void checkFilter(Filter filter, Type type, List<SimpleServerException> exc) {
    FilterType filterType = filter.getFilterType();
    switch (filterType) {
    case AND: {
      checkFilters(((AndFilter) filter).getFilters(), type, exc);
      break;
    }
    case OR: {
      checkFilters(((OrFilter) filter).getFilters(), type, exc);
      break;
    }
    case SIMPLE: {
      List<String> fPath = ((SimpleFilter) filter).getFeaturePath();
      if (TypeSystemUtils.isPathValid(type, fPath) == PathValid.NEVER) {
        exc.add(new SimpleServerException(SimpleServerException.path_never_valid, new Object[] {
            fPathToString(fPath), type }));
      }
      break;
    }
    }
  }

  private static final String fPathToString(List<String> fPath) {
    StringBuffer buf = new StringBuffer();
    buf.append('/');
    for (String f : fPath) {
      buf.append(f);
      buf.append('/');
    }
    return buf.toString();
  }

  private static final void checkFilters(List<Filter> filters, Type type,
      List<SimpleServerException> exc) {
    for (Filter filter : filters) {
      checkFilter(filter, type, exc);
    }
  }

  public boolean getOutputAll() {
    return this.doOutputAll;
  }
}
