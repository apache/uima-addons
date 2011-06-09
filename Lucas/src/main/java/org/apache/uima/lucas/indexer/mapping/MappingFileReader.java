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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.xml.parsers.SAXParser;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Build Object out of the Index Mapping File (XML)
 */
public class MappingFileReader extends DefaultHandler {


	public final static String FIELD = "field"; // the tag name for field definitions

	public final static String FILTER = "filter"; // filter element name

	public final static String ANNOTATION = "annotation"; // the tag name for
													// annotation definitions

	public final static String FEATURE = "feature"; // the tag name for feature
												// definitions

	private Collection<FieldDescription> fieldDescriptions;
	private SAXParser parser;

	private FieldDescription currentFieldDescription;
	private AnnotationDescription currentAnnotationDescription;
	private Locator currentLocator;
	private Map<String, ElementMapper<?>> elementMappers;
	
	public MappingFileReader(SAXParser parser, Map<String, ElementMapper<?>> elementMappers) throws IOException {
		super();
		fieldDescriptions = new ArrayList<FieldDescription>();
		this.parser = parser;
		this.elementMappers = elementMappers;
	}

	public Collection<FieldDescription> readFieldDescriptionsFromFile(
			File mappingFile) throws IOException, SAXException {

		parseFile(mappingFile);
		return fieldDescriptions;
	}

	private void parseFile(File mappingFile) throws IOException, SAXException {
//		try {
			parser.parse(mappingFile, this);
//		} catch (SAXException e) {
//			throw new IOException(e);
//		}
	}

	@Override
	public void setDocumentLocator(Locator locator) {
		currentLocator = locator;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals(FIELD))
			addFieldDescription(attributes);
		else if (qName.equals(FILTER))
			addFilterDescription(attributes);
		else if (qName.equals(ANNOTATION))
			addAnnotationDescription(attributes);
		else if (qName.equals(FEATURE))
			addFeatureDescription(attributes);
	}

	private void addFieldDescription(Attributes attributes) {
		ElementMapper<FieldDescription> elementMapper = (ElementMapper<FieldDescription>) elementMappers.get(FIELD);
		currentFieldDescription = elementMapper.mapElement(attributes);
		currentAnnotationDescription = null;
		mapLocator(currentFieldDescription);
		fieldDescriptions.add(currentFieldDescription);
	}

	private void addFilterDescription(Attributes attributes) {
		ElementMapper<FilterDescription> filterMapper = (ElementMapper<FilterDescription>) elementMappers.get(FILTER);
		FilterDescription filterDescription = filterMapper.mapElement(attributes);
		mapLocator(filterDescription);
		if (currentAnnotationDescription != null ){
			Collection<FilterDescription> filterDescriptions = currentAnnotationDescription.getFilterDescriptions();
			filterDescriptions.add(filterDescription);
		}
		else{
			Collection<FilterDescription> filterDescriptions = currentFieldDescription.getFilterDescriptions();
			filterDescriptions.add(filterDescription);
		}
	}

	private void addAnnotationDescription(Attributes attributes) {
		ElementMapper<AnnotationDescription> annotationMapper = (ElementMapper<AnnotationDescription>) elementMappers.get(ANNOTATION);
		currentAnnotationDescription = annotationMapper.mapElement(attributes);
		mapLocator(currentAnnotationDescription);
		Collection<AnnotationDescription> annotationDescriptions = currentFieldDescription.getAnnotationDescriptions();
		annotationDescriptions.add(currentAnnotationDescription);
	}

	private void addFeatureDescription(Attributes attributes) {
		ElementMapper<FeatureDescription> featureMapper = (ElementMapper<FeatureDescription>) elementMappers.get(FEATURE);
		FeatureDescription featureDescription = featureMapper.mapElement(attributes);
		mapLocator(featureDescription);
		Collection<FeatureDescription> featureDescriptions = currentAnnotationDescription.getFeatureDescriptions();
		featureDescriptions.add(featureDescription);
	}

	private void mapLocator(Locateable locateable) {
		if (currentLocator!=null){
			int lineNumber = currentLocator.getLineNumber();
			locateable.setLineNumber(lineNumber);
			int columnNumber = currentLocator.getColumnNumber();
			locateable.setColumnNumber(columnNumber);
		}
	}

}