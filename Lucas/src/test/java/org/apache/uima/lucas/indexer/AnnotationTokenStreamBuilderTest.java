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

import static org.junit.Assert.*;

import java.text.DecimalFormat;

import org.apache.lucene.analysis.Token;
import org.apache.uima.UIMAFramework;
import org.apache.uima.cas.Type;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.lucas.indexer.AnnotationTokenStreamBuilder;
import org.apache.uima.lucas.indexer.analysis.AnnotationTokenStream;
import org.apache.uima.lucas.indexer.mapping.AnnotationDescription;
import org.apache.uima.lucas.indexer.mapping.FeatureDescription;
import org.apache.uima.lucas.indexer.types.test.Annotation1;
import org.apache.uima.lucas.indexer.types.test.FeatureStructure1;
import org.apache.uima.lucas.indexer.types.test.FeatureStructure2;
import org.apache.uima.jcas.JCas;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.XMLInputSource;
import org.junit.Before;
import org.junit.Test;


public class AnnotationTokenStreamBuilderTest {
	private final static String READER_DESCRIPTOR = "src/test/resources/AnnotationTokenStreamTestDummyCollectionReader.xml";
	private static final String DOCUMENT_TEXT = "token1 token2 token3";
	private CollectionReader reader;
	private JCas cas;
	private AnnotationDescription annotationDescription;
	private AnnotationTokenStreamBuilder annotationTokenStreamBuilder;
	private Type annotation1Type;
	private Annotation1 annotation1;
	
	@Before
	public  void setUp() throws Exception {
		CollectionReaderDescription readerDescription = (CollectionReaderDescription) UIMAFramework.getXMLParser().parseCollectionReaderDescription(new XMLInputSource(READER_DESCRIPTOR));
		reader = UIMAFramework.produceCollectionReader(readerDescription);
		cas = CasCreationUtils.createCas(reader.getProcessingResourceMetaData()).getJCas();
		cas.setDocumentText(DOCUMENT_TEXT);
		annotation1 = new Annotation1(cas);
		annotation1.setBegin(0);
		annotation1.setEnd(6);
		annotation1.addToIndexes();
	
		annotation1Type = annotation1.getType();
		annotationDescription = new AnnotationDescription();
		annotationDescription.setType(annotation1Type.getName());
		annotationTokenStreamBuilder = new AnnotationTokenStreamBuilder();
	}
	
	@Test
	public void testBuildCoveredTextAnnotationTokenStream() throws Exception{
		AnnotationTokenStream annotationTokenStream = annotationTokenStreamBuilder.createAnnotationTokenStream(cas, annotationDescription);
		assertEquals(annotation1Type, annotationTokenStream.getAnnotationType());
		assertEquals(0, annotationTokenStream.getFeatureNames().size());
		assertEquals(0, annotationTokenStream.getFeatureFormats().size());
		assertEquals("token1", annotationTokenStream.next(new Token()).term());
	}
	
	@Test
	public void testBuildFeatureAnnotationTokenStream() throws Exception{
		annotation1.setFeatureString("token1Feature1");
		annotationDescription.getFeatureDescriptions().add(new FeatureDescription("featureString"));
		AnnotationTokenStream annotationTokenStream = annotationTokenStreamBuilder.createAnnotationTokenStream(cas, annotationDescription);
		assertEquals(annotation1Type, annotationTokenStream.getAnnotationType());
		assertEquals(1, annotationTokenStream.getFeatureNames().size());
		assertEquals(0, annotationTokenStream.getFeatureFormats().size());
		assertEquals("token1Feature1", annotationTokenStream.next(new Token()).term());
	}

	@Test
	public void testBuildFeatureAnnotationTokenStreamWithFormat() throws Exception{
		annotation1.setFeatureInteger(3);
		
		FeatureDescription featureDescription = new FeatureDescription("featureInteger");
		featureDescription.setNumberFormat("##");
		
		annotationDescription.getFeatureDescriptions().add(featureDescription);
		AnnotationTokenStream annotationTokenStream = annotationTokenStreamBuilder.createAnnotationTokenStream(cas, annotationDescription);
		assertEquals(annotation1Type, annotationTokenStream.getAnnotationType());
		assertEquals(1, annotationTokenStream.getFeatureNames().size());
		assertEquals(1, annotationTokenStream.getFeatureFormats().size());
		assertTrue(annotationTokenStream.getFeatureFormats().get("featureInteger") instanceof DecimalFormat);
		assertEquals("3", annotationTokenStream.next(new Token()).term());
	}
	
	@Test
	public void testBuildFeaturePathAnnotationTokenStream() throws Exception{
		FeatureStructure1 featureStructure1 = new FeatureStructure1(cas);
		featureStructure1.setFeature1("token1Feature1");
		FeatureStructure2 featureStructure2 = new FeatureStructure2(cas);
		featureStructure2.setFeature1("token1Feature3Feature1");
		featureStructure1.setFeature3(featureStructure2);
		annotation1.setFeatureStructure1(featureStructure1);
		
		annotationDescription.getFeatureDescriptions().add(new FeatureDescription("feature1"));
		annotationDescription.setFeaturePath("featureStructure1");
		AnnotationTokenStream annotationTokenStream = annotationTokenStreamBuilder.createAnnotationTokenStream(cas, annotationDescription);
		assertEquals(annotation1Type, annotationTokenStream.getAnnotationType());
		assertEquals(1, annotationTokenStream.getFeatureNames().size());
		assertEquals(0, annotationTokenStream.getFeatureFormats().size());
		assertEquals("token1Feature1", annotationTokenStream.next(new Token()).term());
	}

	
	
}
