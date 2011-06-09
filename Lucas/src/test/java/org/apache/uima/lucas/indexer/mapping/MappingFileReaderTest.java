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

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.SAXParserFactory;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

public class MappingFileReaderTest {

  private static final String FACTORY_NAME = "myFactory";
  private static final String TESTFACTORY = "testfactory";
  private static final String NUMBER_FORMAT = "##";
  private static final String FEATURE_NAME = "featureInteger";
  private static final String TOKENIZER = "cas";
  private static final String VALUE_DELIMITER_STRING = "aValueDelimiterString";
  private static final String FEATURE_PATH = "aFeaturePath";
  private static final String SOFA = "aSofa";
  private static final String ANNOTATION1_TYPE = "de.julielab.jules.types.test.Annotation1";
  private static final String VALUE = "value";
  private static final String KEY = "key";
  private static final String TESTCLASS = "testfilter";
  private static final String NO = "no";
  private static final String YES = "yes";
  private static final String FIELD_NAME = "annotation1";
  private static final String MAPPING_FILE = "src/test/resources/MappingFileReaderTest.xml";
  private MappingFileReader mappingFileReader;
  
  @Before
  public void setUp() throws Exception{
	Map<String, ElementMapper<?>> elementMappers = new HashMap<String, ElementMapper<?>>();
	elementMappers.put(MappingFileReader.ANNOTATION, new AnnotationMapper());
	elementMappers.put(MappingFileReader.FILTER, new FilterMapper());
	elementMappers.put(MappingFileReader.FIELD, new FieldMapper());
	elementMappers.put(MappingFileReader.FEATURE, new FeatureMapper());
	
    mappingFileReader = new MappingFileReader(SAXParserFactory.newInstance().newSAXParser(), elementMappers);
  }
  
	@Test
	public void testCreateFieldDescriptions() throws IOException, SAXException{
	  Collection<FieldDescription> fieldDescriptions = mappingFileReader.readFieldDescriptionsFromFile(new File(MAPPING_FILE));
	  assertEquals(1, fieldDescriptions.size());

	  FieldDescription fieldDescription = fieldDescriptions.iterator().next();
	  assertEquals(FIELD_NAME, fieldDescription.getName());
	  assertEquals(YES, fieldDescription.getIndex());
	  assertEquals(NO, fieldDescription.getTermVector());
	  assertEquals(YES, fieldDescription.getStored());
	  assertEquals(true, fieldDescription.getMerge());
	  assertEquals(26, fieldDescription.getLineNumber());
	  assertEquals(16, fieldDescription.getColumnNumber());
	  
	  Collection<FilterDescription> filterDescriptions = fieldDescription.getFilterDescriptions();
	  assertEquals(1, filterDescriptions.size());
	  FilterDescription filterDescription = filterDescriptions.iterator().next();
	  assertEquals(TESTCLASS, filterDescription.getClassName());
	  assertEquals(TESTFACTORY, filterDescription.getFactoryClassName());
	  assertEquals(FACTORY_NAME, filterDescription.getName());
	  assertTrue(filterDescription.isReuseFactory());
	  assertEquals(29, filterDescription.getLineNumber());
	  assertEquals(56, filterDescription.getColumnNumber());
	  
	  Properties properties = filterDescription.getProperties(); 
	  assertEquals(VALUE, properties.getProperty(KEY));
	  
	  Collection<AnnotationDescription> annotationDescriptions = fieldDescription.getAnnotationDescriptions();
	  assertEquals(1, annotationDescriptions.size());
	  Iterator<AnnotationDescription> annotationDescriptionIterator = annotationDescriptions.iterator();
	  
	  AnnotationDescription annotationDescription = annotationDescriptionIterator.next();
	  assertEquals(ANNOTATION1_TYPE, annotationDescription.getType());
	  assertEquals(SOFA, annotationDescription.getSofa());
	  assertEquals(FEATURE_PATH, annotationDescription.getFeaturePath());
	  assertEquals(VALUE_DELIMITER_STRING, annotationDescription.getFeatureValueDelimiterString());
	  assertEquals(TOKENIZER, annotationDescription.getTokenizer());
	  assertEquals(33, annotationDescription.getLineNumber());
	  assertEquals(113, annotationDescription.getColumnNumber());

	  filterDescriptions = annotationDescription.getFilterDescriptions();
	  assertEquals(1, filterDescriptions.size());
	  filterDescription = filterDescriptions.iterator().next();
	  assertEquals(TESTCLASS, filterDescription.getClassName());    
	  assertEquals(TESTFACTORY, filterDescription.getFactoryClassName());
	  assertEquals(FACTORY_NAME, filterDescription.getName());
	  assertTrue(filterDescription.isReuseFactory());
	  assertEquals(36, filterDescription.getLineNumber());
	  assertEquals(58, filterDescription.getColumnNumber());

	  properties = filterDescription.getProperties(); 
	  assertEquals(VALUE, properties.getProperty(KEY));

	  Collection<FeatureDescription> featureDescriptions = annotationDescription.getFeatureDescriptions();
	  assertEquals(1, featureDescriptions.size());
	  FeatureDescription featureDescription = featureDescriptions.iterator().next();
	  assertEquals(FEATURE_NAME, featureDescription.getFeatureName());
	  assertEquals(NUMBER_FORMAT, featureDescription.getNumberFormat());
	  assertEquals(39, featureDescription.getLineNumber());
	  assertEquals(57, featureDescription.getColumnNumber());
	}
}
