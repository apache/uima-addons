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

import java.util.regex.Pattern;

import org.apache.uima.annotator.regex.FeaturePath;
import org.apache.uima.annotator.regex.FilterFeature;
import org.apache.uima.cas.Type;
import org.apache.uima.resource.ResourceInitializationException;

/**
 * 
 * 
 */
public class FilterFeature_impl implements FilterFeature {

   private final FeaturePath_impl featurePath;

   private final String featurePathString;
   
   private final String patternStr;

   private Pattern pattern;

   /**
    * @param featurePathString
    * @param patternStr
    */
   public FilterFeature_impl(String featurePathString, String patternStr) {
      this.featurePath = new FeaturePath_impl(featurePathString);
      this.featurePathString = featurePathString;
      this.patternStr = patternStr;
      this.pattern = null;
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.apache.uima.annotator.regex.FilterFeature#getPattern()
    */
   public Pattern getPattern() {
      return this.pattern;
   }

   /* (non-Javadoc)
    * @see org.apache.uima.annotator.regex.FilterFeature#getFeaturePath()
    */
   public FeaturePath getFeaturePath() {
      return this.featurePath;
   }

   public void typeInit(Type annotationType)
         throws ResourceInitializationException {
      
      this.featurePath.initialize(annotationType);
   }

   /**
    * initialize the regex pattern
    */
   public void initialize() {
      // compile pattern
      this.pattern = Pattern.compile(this.patternStr);
   }

   /*
    * (non-Javadoc)
    * 
    * @see java.lang.Object#toString()
    */
   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("Filter Feature: ");
      buffer.append("\n  FeaturePath: ");
      buffer.append(this.featurePathString);
      buffer.append("\n  Pattern: ");
      buffer.append(this.patternStr);
      buffer.append("\n");

      return buffer.toString();
   }

}
