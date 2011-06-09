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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.resource.ResourceInitializationException;

/**
 * 
 * 
 */
public class RuleException_impl implements
      org.apache.uima.annotator.regex.RuleException {

   private final String matchTypeStr;

   private final String patternStr;

   private org.apache.uima.cas.Type matchType;

   private Pattern pattern;

   private AnnotationFS lastAnnot = null;

   private boolean lastMatchResult = false;

   /**
    * @param matchType
    * @param patternStr
    */
   public RuleException_impl(String matchType, String patternStr) {
      this.matchTypeStr = matchType;
      this.patternStr = patternStr;
      this.matchType = null;
      this.pattern = null;
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.apache.uima.annotator.regex.RuleException#matchPattern(org.apache.uima.cas.text.AnnotationFS)
    */
   public boolean matchPattern(AnnotationFS annot) {

      if (this.lastAnnot == annot) {
         return this.lastMatchResult;
      }

      // save current annotation
      this.lastAnnot = annot;

      // check if the pattern match the current annotation
      if (this.pattern != null) {
         Matcher matcher = this.pattern.matcher(annot.getCoveredText());
         // use match strategy matchFirst
         if (matcher.find()) {
            this.lastMatchResult = true;
            return true;
         }
      }
      this.lastMatchResult = false;
      return false;
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.apache.uima.annotator.regex.Exception#getType()
    */
   public Type getType() {
      return this.matchType;
   }

   public void typeInit(TypeSystem ts) throws ResourceInitializationException {
      // initialize annotation type
      if (this.matchTypeStr != null) {
         this.matchType = ts.getType(this.matchTypeStr);
         if (this.matchType == null) {
            throw new RegexAnnotatorConfigException(
                  "regex_annotator_error_resolving_types",
                  new Object[] { this.matchTypeStr });
         }
      }
   }

   /**
    * 
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
      buffer.append("Exception: ");
      buffer.append("\n  matchType: ");
      buffer.append(this.matchTypeStr);
      buffer.append("\n  Pattern: ");
      buffer.append(this.patternStr);
      buffer.append("\n");

      return buffer.toString();
   }

}
