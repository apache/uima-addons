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

import java.util.ArrayList;

import org.apache.uima.annotator.regex.Annotation;
import org.apache.uima.annotator.regex.Feature;
import org.apache.uima.annotator.regex.Position;
import org.apache.uima.annotator.regex.extension.Validation;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.resource.ResourceInitializationException;

/**
 * Implementation of an RegEx Annotation definition
 * 
 */
public class Annotation_impl implements Annotation {

   private final String annotationId;

   private final String annotationTypeStr;

   private final Position begin;

   private final Position end;

   private final String validationClass;

   private Validation validator;
   
   private boolean hasValidator;

   private ArrayList<Feature> features;

   private Type annotationType;

   /**
    * @param annotId
    * @param annotType
    * @param begin
    * @param end
    */
   public Annotation_impl(String annotId, String annotType, Position begin,
         Position end, String validationClass) {
      this.annotationId = annotId;
      this.annotationTypeStr = annotType;
      this.begin = begin;
      this.end = end;
      this.features = new ArrayList<Feature>();
      this.validationClass = validationClass;
      this.validator = null;
      this.hasValidator = false;
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.apache.uima.annotator.regex.Annotation#getAnnotationType()
    */
   public Type getAnnotationType() {
      return this.annotationType;
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.apache.uima.annotator.regex.Annotation#getBegin()
    */
   public Position getBegin() {
      return this.begin;
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.apache.uima.annotator.regex.Annotation#getEnd()
    */
   public Position getEnd() {
      return this.end;
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.apache.uima.annotator.regex.Annotation#getId()
    */
   public String getId() {
      return this.annotationId;
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.apache.uima.annotator.regex.Annotation#addFeature(org.apache.uima.annotator.regex.Feature)
    */
   public void addFeature(Feature aFeature) {
      this.features.add(aFeature);
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.apache.uima.annotator.regex.Annotation#getFeatures()
    */
   public Feature[] getFeatures() {
      return this.features.toArray(new Feature[0]);
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.apache.uima.annotator.regex.Annotation#validate(java.lang.String,
    *      java.lang.String)
    */
   public boolean validate(String coveredText, String ruleID) throws Exception {
      if (this.validator != null) {
         return this.validator.validate(coveredText, ruleID);
      } else {
         return true;
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.apache.uima.annotator.regex.Annotation#hasValidator()
    */
   public boolean hasValidator() {
      return this.hasValidator;
   }

   /**
    * @param ts
    * @throws ResourceInitializationException
    */
   public void typeInit(TypeSystem ts) throws ResourceInitializationException {

      // initialize annotation type
      if (this.annotationTypeStr != null) {
         this.annotationType = ts.getType(this.annotationTypeStr);
         if (this.annotationType == null) {
            throw new RegexAnnotatorConfigException(
                  "regex_annotator_error_resolving_types",
                  new Object[] { this.annotationTypeStr });
         }
      }

      // initialize features with types
      Feature[] featureList = getFeatures();
      for (int i = 0; i < featureList.length; i++) {
         ((Feature_impl) featureList[i]).typeInit(this.annotationType);
      }
   }

   /**
    * initialize the Regex Annotation object with all the annotation features
    * 
    * @throws RegexAnnotatorConfigException
    */
   public void initialize() throws RegexAnnotatorConfigException {
      // initialize features
      Feature[] featureList = getFeatures();
      for (int i = 0; i < featureList.length; i++) {
         ((Feature_impl) featureList[i]).initialize();
      }

      if (this.validationClass != null) {

         try {
            // try to get the custom validation class
            Class<?> validatorClass = this.getClass().getClassLoader()
                  .loadClass(this.validationClass);
            Object obj = validatorClass.newInstance();
            if (obj instanceof Validation) {
               this.validator = (Validation) obj;
               this.hasValidator = true;
            } else {
               throw new RegexAnnotatorConfigException(
                     "regex_annotator_error_custom_validator_wrong_interface",
                     new Object[] { this.validationClass });
            }
         } catch (Exception ex) {
            throw new RegexAnnotatorConfigException(
                  "regex_annotator_error_creating_custom_validator",
                  new Object[] { this.validationClass }, ex);
         }
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see java.lang.Object#toString()
    */
   public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append("Annotation: ");
      buffer.append("\n  ID: ");
      buffer.append(this.annotationId);
      buffer.append("\n  Type: ");
      buffer.append(this.annotationTypeStr);
      buffer.append("\n  Begin: ");
      buffer.append(this.begin.toString());
      buffer.append("\n  End: ");
      buffer.append(this.end.toString());
      if (this.validationClass != null) {
         buffer.append("\n  Validation class: ");
         buffer.append(this.validationClass);
      }

      Feature[] featureList = getFeatures();
      if (featureList.length > 0) {
         buffer.append("\nFeatures: \n");
      }
      // print all features for this annotation
      for (int i = 0; i < featureList.length; i++) {
         buffer.append(featureList[i].toString());
      }
      buffer.append("\n");

      return buffer.toString();
   }
}
