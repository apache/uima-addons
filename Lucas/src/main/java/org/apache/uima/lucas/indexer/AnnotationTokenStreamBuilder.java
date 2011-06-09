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

package org.apache.uima.lucas.indexer;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.uima.cas.CAS;
import org.apache.uima.jcas.JCas;
import org.apache.uima.lucas.indexer.analysis.AnnotationTokenStream;
import org.apache.uima.lucas.indexer.analysis.InvalidTokenSourceException;
import org.apache.uima.lucas.indexer.mapping.AnnotationDescription;
import org.apache.uima.lucas.indexer.mapping.FeatureDescription;

/**
 * Builds annotation token streams wrapped with filters according to annotation descriptions.
 */
public class AnnotationTokenStreamBuilder {

  private static final Logger LOGGER = Logger.getLogger(AnnotationTokenStreamBuilder.class);


  public AnnotationTokenStream createAnnotationTokenStream(JCas jCas,
          AnnotationDescription annotationDescription) throws AnnotationTokenStreamBuildingException {

    String sofaName = annotationDescription.getSofa();
    sofaName = sofaName == null ? CAS.NAME_DEFAULT_SOFA : sofaName;

    // create tokenstream from annotations

    AnnotationTokenStream annotationTokenStream = null;

    String typeName = annotationDescription.getType();
    if (annotationDescription.getFeatureDescriptions().size() == 0)
		try {
			annotationTokenStream = new AnnotationTokenStream(jCas, sofaName, typeName);
		} catch (InvalidTokenSourceException e) {
			throw createException(annotationDescription, e); 
		}
	else {
      String featurePath = annotationDescription.getFeaturePath();
      String delimiter = annotationDescription.getFeatureValueDelimiterString();

      Collection<FeatureDescription> featureDescriptions = annotationDescription.getFeatureDescriptions();
      List<String> featureNames = extractFeatureNames(featureDescriptions);
      Map<String, Format> featureFormats = extractFeatureFormats(featureDescriptions);

      if (featurePath != null)
		try {
			annotationTokenStream = new AnnotationTokenStream(jCas, sofaName, typeName, featurePath,
			        featureNames, delimiter, featureFormats);
		} catch (InvalidTokenSourceException e) {
			throw createException(annotationDescription, e);
		}
	else
		try {
			annotationTokenStream = new AnnotationTokenStream(jCas, sofaName, typeName, featureNames,
			        delimiter, featureFormats);
		} catch (InvalidTokenSourceException e) {
			throw createException(annotationDescription, e);
		}

    }

    return annotationTokenStream;
  }

  private List<String> extractFeatureNames(Collection<FeatureDescription> featureDescriptions){
      List<String> featureNames = new ArrayList<String>();
      for (FeatureDescription featureDescription : featureDescriptions) {
        featureNames.add(featureDescription.getFeatureName());
      }
      return featureNames;
  }
  
  private Map<String, Format> extractFeatureFormats(Collection<FeatureDescription> featureDescriptions){
      Map<String, Format> featureFormats = new HashMap<String, Format>();
      for (FeatureDescription featureDescription : featureDescriptions) {
        if (featureDescription.getNumberFormat() != null) {
          Format format = new DecimalFormat(featureDescription.getNumberFormat());
          featureFormats.put(featureDescription.getFeatureName(), format);
        }
      }
	  return featureFormats;
  }
  
  private AnnotationTokenStreamBuildingException createException(AnnotationDescription annotationDescription,
		  InvalidTokenSourceException e) {
	  String message = "Can't build TokenStream from annotation at line " +
	  annotationDescription.getLineNumber();
	  return new AnnotationTokenStreamBuildingException(message, e);
  }

}
