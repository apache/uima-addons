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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.cas.impl.TypeSystemUtils;
import org.apache.uima.simpleserver.SimpleServerException;
import org.apache.uima.simpleserver.config.AndFilter;
import org.apache.uima.simpleserver.config.Condition;
import org.apache.uima.simpleserver.config.ConfigFactory;
import org.apache.uima.simpleserver.config.Filter;
import org.apache.uima.simpleserver.config.FilterOp;
import org.apache.uima.simpleserver.config.OrFilter;
import org.apache.uima.simpleserver.config.Output;
import org.apache.uima.simpleserver.config.ServerSpec;
import org.apache.uima.simpleserver.config.SimpleFilter;
import org.apache.uima.simpleserver.config.TypeMap;
import org.apache.uima.simpleserver.config.xml.And;
import org.apache.uima.simpleserver.config.xml.FilterOperator;
import org.apache.uima.simpleserver.config.xml.FilterType;
import org.apache.uima.simpleserver.config.xml.Or;
import org.apache.uima.simpleserver.config.xml.OutputType;
import org.apache.uima.simpleserver.config.xml.SimpleFilterType;
import org.apache.uima.simpleserver.config.xml.TypeElementType;
import org.apache.uima.simpleserver.config.xml.UimaSimpleServerSpecDocument;
import org.apache.uima.simpleserver.config.xml.TypeElementType.Filters;
import org.apache.uima.simpleserver.config.xml.TypeElementType.Outputs;
import org.apache.uima.simpleserver.config.xml.UimaSimpleServerSpecDocument.UimaSimpleServerSpec;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;

/**
 * Read server configuration from an XML file or stream.
 */
public final class XmlConfigReader {

  // Constants for filter operators
  private static final int NULL = FilterOperator.Enum.forString("null").intValue();

  private static final int NOT_NULL = FilterOperator.Enum.forString("!null").intValue();

  private static final int EQUALS = FilterOperator.Enum.forString("=").intValue();

  private static final int NOT_EQUALS = FilterOperator.Enum.forString("!=").intValue();

  private static final int LESS = FilterOperator.Enum.forString("<").intValue();

  private static final int LESS_EQ = FilterOperator.Enum.forString("<=").intValue();

  private static final int GREATER = FilterOperator.Enum.forString(">").intValue();

  private static final int GREATER_EQ = FilterOperator.Enum.forString(">=").intValue();

  /**
   * Read a config file.
   * 
   * @param file
   *                The config file.
   * @return The corresponding server spec.
   * @throws IOException
   * @throws XmlException
   *                 XML parsing error.
   * @throws SimpleServerException
   *                 Content parsing error.
   */
  public static ServerSpec readServerSpec(File file) throws IOException, XmlException,
      SimpleServerException {
    return readServerSpec(new BufferedInputStream(new FileInputStream(file)));
  }

  /**
   * Read a config XML stream.
   * 
   * @param is
   *                The XML input stream.
   * @return The corresponding server spec.
   * @throws IOException
   * @throws XmlException
   *                 XML parsing error.
   * @throws SimpleServerException
   *                 Content parsing error.
   */
  public static ServerSpec readServerSpec(InputStream is) throws IOException, XmlException,
      SimpleServerException {
    UimaSimpleServerSpec specBean = UimaSimpleServerSpecDocument.Factory.parse(is)
        .getUimaSimpleServerSpec();

    // Do validation. If XML is not valid, throw first error.
    ArrayList<XmlError> validationErrors = new ArrayList<XmlError>();
    XmlOptions validationOptions = new XmlOptions();
    validationOptions.setErrorListener(validationErrors);
    boolean isValid = specBean.validate(validationOptions);
    if (!isValid) {
      Iterator<XmlError> iter = validationErrors.iterator();
      if (iter.hasNext()) {
        throw new XmlException(iter.next());
      }
    }

    // TODO: recompile XML beans code from XSD. While doing this: check if the number of jars for
    // xml parsing (3) can be reduced. This is all Apache code, so legally this should be ok.
    // Also create ant build script for this.
    // TEMPORARY:
    final boolean doOutputAll = false;

    // Create new server spec from XML beans.
    ServerSpec spec = ConfigFactory.newServerSpec(specBean.getShortDescription(), specBean
        .getLongDescription(), doOutputAll);
    TypeElementType[] typeMaps = specBean.getTypeArray();
    for (int i = 0; i < typeMaps.length; i++) {
      spec.addTypeMap(readTypeMap(typeMaps[i]));
    }
    return spec;
  }

  // Read a type element.
  private static TypeMap readTypeMap(TypeElementType typeBean) throws SimpleServerException {
    boolean coveredText = typeBean.getOutputCoveredText();
    Filter filter = null;
    // Check if type element has a filter, and what kind of filter it is (atomic filter, and, or).
    if (typeBean.getFilters() != null) {
      Filters filterBean = typeBean.getFilters();
      if (filterBean.getAnd() != null) {
        filter = readFilter(filterBean.getAnd());
      } else if (filterBean.getOr() != null) {
        filter = readFilter(filterBean.getOr());
      } else if (filterBean.getFilter() != null) {
        filter = readFilter(filterBean.getFilter());
      }
    }

    // create a type map with the given information
    TypeMap typeMap = ConfigFactory.newTypeMap(typeBean.getName(), filter, typeBean.getOutputTag(),
        coveredText, typeBean.getOutputAll(), typeBean.getShortDescription(), typeBean
            .getLongDescription());

    // check if for the current type output features are mapped
    if (typeBean.getOutputs() != null) {
      Outputs outputBean = typeBean.getOutputs();
      // get mapped output features
      OutputType[] outputTypes = outputBean.getOutputArray();
      for (int i = 0; i < outputTypes.length; i++) {
        // parse output feature featurePath
        List<String> featurePath = parseFeaturePath(outputTypes[i].getFeaturePath());
        Output output = ConfigFactory.newOutput(featurePath, outputTypes[i].getOutputAttribute(),
            outputTypes[i].getShortDescription(), outputTypes[i].getLongDescription());
        // add output feature to the typeMap
        typeMap.addOutput(output);
      }
    }
    return typeMap;
  }

  // Process a filter bean.
  private static final Filter readFilter(FilterType filterBean) throws SimpleServerException {
    Filter filter = null;
    // Need to distinguish the various kinds of filters.
    if (filterBean instanceof And) {
      filter = readAndFilter((And) filterBean);
    } else if (filterBean instanceof Or) {
      filter = readOrFilter((Or) filterBean);
    } else {
      filter = readSimpleFilter((SimpleFilterType) filterBean);
    }
    return filter;
  }

  // Parse an AND filter.
  private static final AndFilter readAndFilter(And filterBean) throws SimpleServerException {
    AndFilter filter = ConfigFactory.newAndFilter();
    // May embed any number of simple filters, conjunctions and disjunctions.
    SimpleFilterType[] simpleFilters = filterBean.getFilterArray();
    And[] andBeans = filterBean.getAndArray();
    Or[] orBeans = filterBean.getOrArray();
    List<FilterType> filterBeans = getFilters(simpleFilters, andBeans, orBeans);
    for (int i = 0; i < filterBeans.size(); i++) {
      filter.addFilter(readFilter(filterBeans.get(i)));
    }
    return filter;
  }

  // Aggregate the various forms of filter arrays into one filter list.
  private static final List<FilterType> getFilters(SimpleFilterType[] simpleFilters,
      And[] andBeans, Or[] orBeans) {
    List<FilterType> list = new ArrayList<FilterType>();
    for (int i = 0; i < simpleFilters.length; i++) {
      list.add(simpleFilters[i]);
    }
    for (int i = 0; i < andBeans.length; i++) {
      list.add(andBeans[i]);
    }
    for (int i = 0; i < orBeans.length; i++) {
      list.add(orBeans[i]);
    }
    return list;
  }

  // Parse an OR filter
  private static final OrFilter readOrFilter(Or filterBean) throws SimpleServerException {
    OrFilter filter = ConfigFactory.newOrFilter();
    SimpleFilterType[] simpleFilters = filterBean.getFilterArray();
    And[] andBeans = filterBean.getAndArray();
    Or[] orBeans = filterBean.getOrArray();
    List<FilterType> filterBeans = getFilters(simpleFilters, andBeans, orBeans);
    for (int i = 0; i < filterBeans.size(); i++) {
      filter.addFilter(readFilter(filterBeans.get(i)));
    }
    return filter;
  }

  // Process a simple, atomic filter.
  private static final SimpleFilter readSimpleFilter(SimpleFilterType filterBean)
      throws SimpleServerException {
    List<String> path = parseFeaturePath(filterBean.getFeaturePath());
    Condition condition = readCondition(filterBean.getOperator(), filterBean.getValue());
    return ConfigFactory.newSimpleFilter(path, condition);
  }

  // Process a condition. Check that value is set according to the operator.
  private static final Condition readCondition(FilterOperator.Enum operator, String value)
      throws SimpleServerException {
    FilterOp op = readOperator(operator);
    checkCondition(op, value);
    return ConfigFactory.newCondition(op, value);
  }

  // Check filter syntax: null and !null must not have the value attribute defined, while all others
  // must.
  private static final void checkCondition(FilterOp op, String value) throws SimpleServerException {
    switch (op) {
    case NOT_NULL:
    case NULL: {
      valueMustBeNull(op, value);
      break;
    }
    default: {
      valueMustNotBeNull(op, value);
      break;
    }
    }
  }

  private static final void valueMustBeNull(FilterOp op, String value) throws SimpleServerException {
    if (value != null) {
      throw new SimpleServerException(SimpleServerException.value_must_not_be_set, new Object[] {
          op, value });
    }
  }

  private static final void valueMustNotBeNull(FilterOp op, String value)
      throws SimpleServerException {
    if (value == null) {
      throw new SimpleServerException(SimpleServerException.value_must_be_set, new Object[] { op });
    }
  }

  private static final FilterOp readOperator(FilterOperator.Enum operator) {
    final int op = operator.intValue();
    // Can't use switch because enum values aren't constants.
    if (op == NULL) {
      return FilterOp.NULL;
    } else if (op == NOT_NULL) {
      return FilterOp.NOT_NULL;
    } else if (op == EQUALS) {
      return FilterOp.EQUALS;
    } else if (op == NOT_EQUALS) {
      return FilterOp.NOT_EQUALS;
    } else if (op == LESS) {
      return FilterOp.LESS;
    } else if (op == LESS_EQ) {
      return FilterOp.LESS_EQ;
    } else if (op == GREATER) {
      return FilterOp.GREATER;
    } else if (op == GREATER_EQ) {
      return FilterOp.GREATER_EQ;
    }
    assert (false);
    return null;

  }

  private static List<String> parseFeaturePath(String path) throws SimpleServerException {
    List<String> featureList = new ArrayList<String>();
    final int max = path.length();
    int pos = 0;
    // Check if path starts with a slash; if so, eliminate.
    if ((max > 0) && (path.charAt(0) == ServerSpec.PATH_SEPARATOR)) {
      pos = 1;
    }
    while (pos < max) {
      // Find the next path separator
      int next = pos;
      while ((next < max) && (path.charAt(next) != ServerSpec.PATH_SEPARATOR)) {
        ++next;
      }
      // Found a slash at next position, invalid path syntax
      if ((next < max) && (next == pos)) {
        throw new SimpleServerException(SimpleServerException.incorrect_path_syntax,
            new Object[] { path });
      }
      String feature = path.substring(pos, next);
      if (!TypeSystemUtils.isIdentifier(feature)) {
        throw new SimpleServerException(SimpleServerException.incorrect_feature_syntax,
            new Object[] { feature, path });
      }
      featureList.add(feature);
      pos = next + 1;
    }
    return featureList;
  }
}
