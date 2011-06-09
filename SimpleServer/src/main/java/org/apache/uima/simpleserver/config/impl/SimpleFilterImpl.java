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

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.LowLevelCAS;
import org.apache.uima.cas.impl.TypeSystemUtils;
import org.apache.uima.simpleserver.config.Condition;
import org.apache.uima.simpleserver.config.Filter;
import org.apache.uima.simpleserver.config.FilterOp;
import org.apache.uima.simpleserver.config.SimpleFilter;

/**
 * Implementation of <code>Filter</code> interface.
 */
public class SimpleFilterImpl extends FilterImpl implements SimpleFilter {

  private final List<String> path;

  private final Condition condition;

  @SuppressWarnings("unused")
  private SimpleFilterImpl() {
    this(null, null);
  }

  public SimpleFilterImpl(List<String> path, Condition condition) {
    super(Filter.FilterType.SIMPLE);
    this.path = path;
    this.condition = condition;
  }

  public Condition getCondition() {
    return this.condition;
  }

  public List<String> getFeaturePath() {
    return Collections.unmodifiableList(this.path);
  }

  @Override
  public boolean match(FeatureStructure fs) {
    Stack<String> stack = list2Stack(this.path);
    return match(stack, fs);
  }

  private final boolean match(Stack<String> stack, FeatureStructure fs) {
    if (stack.isEmpty()) {
      // Can only be FS-type conditions
      switch (this.getCondition().getConditionType()) {
      case NULL: {
        return (fs == null);
      }
      case NOT_NULL: {
        return (fs != null);
      }
      default: {
        // All other cases must be false
        return false;
      }
      }
    }
    if (fs == null) {
      return false;
    }
    // If we get here, we know the stack is not empty, so get the next feature.
    String fName = stack.pop();
    Feature f = fs.getType().getFeatureByBaseName(fName);
    // If the feature is not defined for the type of the input FS, return false.
    if (f == null) {
      return false;
    }
    Type range = f.getRange();
    final int typeClass = TypeSystemUtils.classifyType(range);
    // If the next feature value is a FS, continue recursively.
    if (typeClass == LowLevelCAS.TYPE_CLASS_FS) {
      // If the type value is a regular FS, carry on recursively.
      fs = fs.getFeatureValue(f);
      return match(stack, fs);
    }
    // If we get here, the next feature value is not a FS, so the stack should be empty.
    if (!stack.isEmpty()) {
      return false;
    }
    // So now we know we're at the end of the path, the next feature value is what we evaluate
    // against.
    switch (typeClass) {
    case LowLevelCAS.TYPE_CLASS_BOOLEAN: {
      return checkBoolean(fs.getBooleanValue(f));
    }
    case LowLevelCAS.TYPE_CLASS_BYTE: {
      return checkByte(fs.getByteValue(f));
    }
    case LowLevelCAS.TYPE_CLASS_DOUBLE: {
      return checkDouble(fs.getDoubleValue(f));
    }
    case LowLevelCAS.TYPE_CLASS_FLOAT: {
      return checkFloat(fs.getFloatValue(f));
    }
    case LowLevelCAS.TYPE_CLASS_INT: {
      return checkInt(fs.getIntValue(f));
    }
    case LowLevelCAS.TYPE_CLASS_LONG: {
      return checkLong(fs.getLongValue(f));
    }
    case LowLevelCAS.TYPE_CLASS_SHORT: {
      return checkShort(fs.getShortValue(f));
    }
    case LowLevelCAS.TYPE_CLASS_STRING: {
      return checkString(fs.getStringValue(f));
    }
    default: {
      // Catch-all: any types we don't handle (arrays) will automatically return false.
      return false;
    }
    }
  }

  private final boolean checkByte(byte in) {
    String value = this.getCondition().getValue();
    byte v;
    try {
      v = Byte.parseByte(value);
    } catch (NumberFormatException e) {
      return false;
    }
    switch (this.getCondition().getConditionType()) {
    case EQUALS: {
      return (in == v);
    }
    case NOT_EQUALS: {
      return (in != v);
    }
    case GREATER: {
      return (in > v);
    }
    case GREATER_EQ: {
      return (in >= v);
    }
    case LESS: {
      return (in < v);
    }
    case LESS_EQ: {
      return (in <= v);
    }
    default: {
      return false;
    }
    }
  }

  private final boolean checkDouble(double in) {
    String value = this.getCondition().getValue();
    double v;
    try {
      v = Double.parseDouble(value);
    } catch (NumberFormatException e) {
      return false;
    }
    switch (this.getCondition().getConditionType()) {
    case EQUALS: {
      return (in == v);
    }
    case NOT_EQUALS: {
      return (in != v);
    }
    case GREATER: {
      return (in > v);
    }
    case GREATER_EQ: {
      return (in >= v);
    }
    case LESS: {
      return (in < v);
    }
    case LESS_EQ: {
      return (in <= v);
    }
    default: {
      return false;
    }
    }
  }

  private final boolean checkFloat(float in) {
    String value = this.getCondition().getValue();
    float v;
    try {
      v = Float.parseFloat(value);
    } catch (NumberFormatException e) {
      return false;
    }
    switch (this.getCondition().getConditionType()) {
    case EQUALS: {
      return (in == v);
    }
    case NOT_EQUALS: {
      return (in != v);
    }
    case GREATER: {
      return (in > v);
    }
    case GREATER_EQ: {
      return (in >= v);
    }
    case LESS: {
      return (in < v);
    }
    case LESS_EQ: {
      return (in <= v);
    }
    default: {
      return false;
    }
    }
  }

  private final boolean checkLong(long in) {
    String value = this.getCondition().getValue();
    long v;
    try {
      v = Long.parseLong(value);
    } catch (NumberFormatException e) {
      return false;
    }
    switch (this.getCondition().getConditionType()) {
    case EQUALS: {
      return (in == v);
    }
    case NOT_EQUALS: {
      return (in != v);
    }
    case GREATER: {
      return (in > v);
    }
    case GREATER_EQ: {
      return (in >= v);
    }
    case LESS: {
      return (in < v);
    }
    case LESS_EQ: {
      return (in <= v);
    }
    default: {
      return false;
    }
    }
  }

  private final boolean checkInt(int in) {
    String value = this.getCondition().getValue();
    int v;
    try {
      v = Integer.parseInt(value);
    } catch (NumberFormatException e) {
      return false;
    }
    switch (this.getCondition().getConditionType()) {
    case EQUALS: {
      return (in == v);
    }
    case NOT_EQUALS: {
      return (in != v);
    }
    case GREATER: {
      return (in > v);
    }
    case GREATER_EQ: {
      return (in >= v);
    }
    case LESS: {
      return (in < v);
    }
    case LESS_EQ: {
      return (in <= v);
    }
    default: {
      return false;
    }
    }
  }

  private final boolean checkShort(short in) {
    String value = this.getCondition().getValue();
    short v;
    try {
      v = Short.parseShort(value);
    } catch (NumberFormatException e) {
      return false;
    }
    switch (this.getCondition().getConditionType()) {
    case EQUALS: {
      return (in == v);
    }
    case NOT_EQUALS: {
      return (in != v);
    }
    case GREATER: {
      return (in > v);
    }
    case GREATER_EQ: {
      return (in >= v);
    }
    case LESS: {
      return (in < v);
    }
    case LESS_EQ: {
      return (in <= v);
    }
    default: {
      return false;
    }
    }
  }

  private final boolean checkString(String s) {
    String value = this.getCondition().getValue();
    FilterOp op = this.getCondition().getConditionType();
    // First check for conditions that makes sense if input value is null.
    switch (op) {
    case NULL: {
      return (s == null);
    }
    case NOT_NULL: {
      return (s != null);
    }
    }
    // If we get here and s is null, we fail.
    if (s == null) {
      return false;
    }
    // Now neither the value nor s are null, so we can compare them.
    final int comp = s.compareTo(value);
    switch (op) {
    case EQUALS: {
      return (comp == 0);
    }
    case NOT_EQUALS: {
      return (comp != 0);
    }
    case GREATER: {
      return (comp > 0);
    }
    case GREATER_EQ: {
      return (comp >= 0);
    }
    case LESS: {
      return (comp < 0);
    }
    case LESS_EQ: {
      return (comp <= 0);
    }
    default: {
      // Can't get here, but the compiler doesn't know that.
      return false;
    }
    }
  }

  private final boolean checkBoolean(boolean b) {
    String value = this.getCondition().getValue();
    boolean v = Boolean.parseBoolean(value);
    switch (this.getCondition().getConditionType()) {
    case EQUALS: {
      return (v == b);
    }
    case NOT_EQUALS: {
      return (v != b);
    }
    default: {
      return false;
    }
    }
  }

  private static final Stack<String> list2Stack(List<String> list) {
    Stack<String> stack = new Stack<String>();
    for (int i = list.size() - 1; i >= 0; i--) {
      stack.push(list.get(i));
    }
    return stack;
  }

}
