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

package org.apache.uima.lucas.indexer.mapping;

import org.xml.sax.Attributes;

public class AnnotationMapper implements ElementMapper<AnnotationDescription> {

	private final String ANNOTATION_TYPE = "type"; // defines the CAS annotation
	// type to be indexed

	private final String ANNOTATION_VALUE_DELIMITER_STRING = "featureValueDelimiterString"; // use
	// special
	// characters
	// to

	// concatenate the feature values

	// the feature values

	private final String ANNOTATION_FEATURE_PATH = "featurePath"; // path to the
	// featurestructure

	// which contains the features

	private final String ANNOTATION_SOFA = "sofa"; // source sofa of annotations

	private final String ANNOTATION_TOKENIZER = "tokenizer"; // defines the

	// tokenizer to
	// be used

	public AnnotationDescription mapElement(Attributes attributes) {
		AnnotationDescription annotationDescription = new AnnotationDescription();
		for (int i = 0; i < attributes.getLength(); i++) {
			String name = attributes.getQName(i);
			String value = attributes.getValue(i);

			if (name.equals(ANNOTATION_SOFA))
				annotationDescription.setSofa(value);
			else if (name.equals(ANNOTATION_TYPE))
				annotationDescription.setType(value);
			else if (name.equals(ANNOTATION_TOKENIZER))
				annotationDescription.setTokenizer(value);
			else if (name.equals(ANNOTATION_FEATURE_PATH))
				annotationDescription.setFeaturePath(value);
			else if (name.equals(ANNOTATION_VALUE_DELIMITER_STRING))
				annotationDescription.setFeatureValueDelimiterString(value);
		}
		return annotationDescription;
	}

}
