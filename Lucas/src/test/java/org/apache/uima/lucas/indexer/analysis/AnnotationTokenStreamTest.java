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

package org.apache.uima.lucas.indexer.analysis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Token;
import org.apache.uima.UIMAFramework;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.Type;
import org.apache.uima.collection.CollectionReader;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.lucas.indexer.types.test.Annotation1;
import org.apache.uima.lucas.indexer.types.test.FeatureStructure1;
import org.apache.uima.lucas.indexer.types.test.FeatureStructure2;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.XMLInputSource;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

public class AnnotationTokenStreamTest {
	
	private final static String READER_DESCRIPTOR = "src/test/resources/AnnotationTokenStreamTestDummyCollectionReader.xml";
	private CollectionReader reader;
	
	@Before
	public void setUp() throws Exception {
		CollectionReaderDescription readerDescription = (CollectionReaderDescription) UIMAFramework.getXMLParser().parseCollectionReaderDescription(new XMLInputSource(READER_DESCRIPTOR));
		reader = UIMAFramework.produceCollectionReader(readerDescription);		
	}
	
	public JCas createCasWithText(String documentText) throws ResourceInitializationException, CASException{		
		JCas cas = CasCreationUtils.createCas(reader.getProcessingResourceMetaData()).getJCas();
		cas.setDocumentText(documentText);
		return cas;
	}

	@Test
	public void testCreateDocumentTextTokens() throws Exception{
		JCas cas = createCasWithText("token1 token2 token3");
		Annotation1 annotation1 = new Annotation1(cas);
		annotation1.setBegin(0);
		annotation1.setEnd(6);
		annotation1.addToIndexes();
		Annotation1 annotation2 = new Annotation1(cas);
		annotation2.setBegin(7);
		annotation2.setEnd(13);
		annotation2.addToIndexes();
		
		Annotation1 annotation3 = new Annotation1(cas);
		annotation3.setBegin(14);
		annotation3.setEnd(20);
		annotation3.addToIndexes();
		
		AnnotationTokenStream annotationTokenStream = new AnnotationTokenStream(cas, CAS.NAME_DEFAULT_SOFA, "org.apache.uima.lucas.indexer.types.test.Annotation1");
		Token nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());
		
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());
		
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());
	}
	
	@Test
	public void testCreateFeatureTokens() throws Exception{
		JCas cas = createCasWithText("token1 token2 token3");
		Annotation1 annotation1 = new Annotation1(cas);
		annotation1.setBegin(0);
		annotation1.setEnd(6);
		annotation1.setFeatureString("token1Feature1");
		annotation1.addToIndexes();
		
		Annotation1 annotation2 = new Annotation1(cas);
		annotation2.setFeatureString("token2Feature1");
		annotation2.setBegin(7);
		annotation2.setEnd(13);
		annotation2.addToIndexes();
		
		Annotation1 annotation3 = new Annotation1(cas);
		annotation3.setFeatureString("token3Feature1");
		annotation3.setBegin(14);
		annotation3.setEnd(20);
		annotation3.addToIndexes();		

		AnnotationTokenStream annotationTokenStream = new AnnotationTokenStream(cas, CAS.NAME_DEFAULT_SOFA, "org.apache.uima.lucas.indexer.types.test.Annotation1", "featureString", null);
		Token nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1Feature1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());
		
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2Feature1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());
		
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3Feature1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());
	}
	 
  @Test
	public void testCreateMultipleFeatureTokens() throws Exception{
		JCas cas = createCasWithText("token1 token2 token3");
		Annotation1 annotation1 = new Annotation1(cas);
		annotation1.setBegin(0);
		annotation1.setEnd(6);
		annotation1.setFeatureString("token1Feature1");
		annotation1.setFeatureInteger(1);
		annotation1.addToIndexes();
		
		Annotation1 annotation2 = new Annotation1(cas);
		annotation2.setFeatureString("token2Feature1");
		annotation2.setFeatureInteger(2);
		annotation2.setBegin(7);
		annotation2.setEnd(13);
		annotation2.addToIndexes();
		
		Annotation1 annotation3 = new Annotation1(cas);
		annotation3.setFeatureString("token3Feature1");
		annotation3.setFeatureInteger(3);
		annotation3.setBegin(14);
		annotation3.setEnd(20);
		annotation3.addToIndexes();		

		List<String> featureNames = new ArrayList<String>();
		featureNames.add("featureString");
		featureNames.add("featureInteger");
		
		AnnotationTokenStream annotationTokenStream = new AnnotationTokenStream(cas, CAS.NAME_DEFAULT_SOFA, "org.apache.uima.lucas.indexer.types.test.Annotation1", featureNames, "_", null);
		Token nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1Feature1_1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());
		
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2Feature1_2", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());
		
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3Feature1_3", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());
	}	
  
  @Test
	public void testCreateArrayFeatureTokensWithoutDelimiter() throws Exception{
		JCas cas = createCasWithText("token1 token2 token3");
		Annotation1 annotation1 = new Annotation1(cas);
		annotation1.setBegin(0);
		annotation1.setEnd(6);
		StringArray stringArray1 = new StringArray(cas, 3);
		stringArray1.set(0, "token1FeatureStringArray1");
		stringArray1.set(1, "token1FeatureStringArray2");
		stringArray1.set(2, "token1FeatureStringArray3");
		annotation1.setFeatureStringArray(stringArray1);
		annotation1.addToIndexes();
		
		Annotation1 annotation2 = new Annotation1(cas);
		annotation2.setBegin(7);
		annotation2.setEnd(13);
		StringArray stringArray2 = new StringArray(cas, 3);
		stringArray2.set(0, "token2FeatureStringArray1");
		stringArray2.set(1, "token2FeatureStringArray2");
		stringArray2.set(2, "token2FeatureStringArray3");
		annotation2.setFeatureStringArray(stringArray2);
		
		annotation2.addToIndexes();
		
		Annotation1 annotation3 = new Annotation1(cas);
		annotation3.setBegin(14);
		annotation3.setEnd(20);
		StringArray stringArray3 = new StringArray(cas, 3);
		stringArray3.set(0, "token3FeatureStringArray1");
		stringArray3.set(1, "token3FeatureStringArray2");
		stringArray3.set(2, "token3FeatureStringArray3");
		annotation3.setFeatureStringArray(stringArray3);
		annotation3.addToIndexes();		
		
		List<String> featureNames = new ArrayList<String>();
		featureNames.add("featureStringArray");
		
		AnnotationTokenStream annotationTokenStream = new AnnotationTokenStream(cas, CAS.NAME_DEFAULT_SOFA, "org.apache.uima.lucas.indexer.types.test.Annotation1", featureNames, null);
		Token nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1FeatureStringArray1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());
		
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1FeatureStringArray2", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());
	
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1FeatureStringArray3", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());
	
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2FeatureStringArray1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());
		
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2FeatureStringArray2", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());
	
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2FeatureStringArray3", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());
	
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3FeatureStringArray1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());
	
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3FeatureStringArray2", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());
	
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3FeatureStringArray3", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());		
	}
  
  @Test
	public void testCreateArrayFeatureTokensWithDelimiter() throws Exception{
		JCas cas = createCasWithText("token1 token2 token3");
		Annotation1 annotation1 = new Annotation1(cas);
		annotation1.setBegin(0);
		annotation1.setEnd(6);
		StringArray stringArray1 = new StringArray(cas, 3);
		stringArray1.set(0, "token1FeatureStringArray1");
		stringArray1.set(1, "token1FeatureStringArray2");
		stringArray1.set(2, "token1FeatureStringArray3");
		annotation1.setFeatureStringArray(stringArray1);
		annotation1.addToIndexes();
		
		Annotation1 annotation2 = new Annotation1(cas);
		annotation2.setBegin(7);
		annotation2.setEnd(13);
		StringArray stringArray2 = new StringArray(cas, 3);
		stringArray2.set(0, "token2FeatureStringArray1");
		stringArray2.set(1, "token2FeatureStringArray2");
		stringArray2.set(2, "token2FeatureStringArray3");
		annotation2.setFeatureStringArray(stringArray2);
		
		annotation2.addToIndexes();
		
		Annotation1 annotation3 = new Annotation1(cas);
		annotation3.setBegin(14);
		annotation3.setEnd(20);
		StringArray stringArray3 = new StringArray(cas, 3);
		stringArray3.set(0, "token3FeatureStringArray1");
		stringArray3.set(1, "token3FeatureStringArray2");
		stringArray3.set(2, "token3FeatureStringArray3");
		annotation3.setFeatureStringArray(stringArray3);
		annotation3.addToIndexes();		
		
		List<String> featureNames = new ArrayList<String>();
		featureNames.add("featureStringArray");
		
		
		AnnotationTokenStream annotationTokenStream = new AnnotationTokenStream(cas, CAS.NAME_DEFAULT_SOFA, "org.apache.uima.lucas.indexer.types.test.Annotation1", featureNames, " ", null);
		Token nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1FeatureStringArray1 token1FeatureStringArray2 token1FeatureStringArray3", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2FeatureStringArray1 token2FeatureStringArray2 token2FeatureStringArray3", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3FeatureStringArray1 token3FeatureStringArray2 token3FeatureStringArray3", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());
	}
  
  @Test
	public void testCreateFeatureStructureTokens() throws Exception{
		JCas cas = createCasWithText("token1 token2 token3");
		Annotation1 annotation1 = new Annotation1(cas);
		annotation1.setBegin(0);
		annotation1.setEnd(6);
		FeatureStructure1 featureStructure1 = new FeatureStructure1(cas);
		featureStructure1.setFeature1("token1Feature1");
		FeatureStructure2 featureStructure2 = new FeatureStructure2(cas);
		featureStructure2.setFeature1("token1Feature3Feature1");
		featureStructure1.setFeature3(featureStructure2);
		annotation1.setFeatureStructure1(featureStructure1);
		annotation1.addToIndexes();
		
		Annotation1 annotation2 = new Annotation1(cas);
		annotation2.setBegin(7);
		annotation2.setEnd(13);
		featureStructure1 = new FeatureStructure1(cas);
		featureStructure1.setFeature1("token2Feature1");
		featureStructure2 = new FeatureStructure2(cas);
		featureStructure2.setFeature1("token2Feature3Feature1");
		featureStructure1.setFeature3(featureStructure2);
		annotation2.setFeatureStructure1(featureStructure1);		
		annotation2.addToIndexes();
		
		Annotation1 annotation3 = new Annotation1(cas);
		annotation3.setBegin(14);
		annotation3.setEnd(20);
		featureStructure1 = new FeatureStructure1(cas);
		featureStructure1.setFeature1("token3Feature1");
		featureStructure2 = new FeatureStructure2(cas);
		featureStructure2.setFeature1("token3Feature3Feature1");
		featureStructure1.setFeature3(featureStructure2);		
		annotation3.setFeatureStructure1(featureStructure1);		
		annotation3.addToIndexes();
		List<String> featureNames = new ArrayList<String>();
		featureNames.add("feature1");
		
		AnnotationTokenStream annotationTokenStream = new AnnotationTokenStream(cas, CAS.NAME_DEFAULT_SOFA, "org.apache.uima.lucas.indexer.types.test.Annotation1", "featureStructure1", featureNames, null);
		Token nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1Feature1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());
		
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2Feature1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());
		
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3Feature1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());
		
		annotationTokenStream = new AnnotationTokenStream(cas, CAS.NAME_DEFAULT_SOFA, "org.apache.uima.lucas.indexer.types.test.Annotation1", "featureStructure1.feature3", featureNames, null);
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1Feature3Feature1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());
		
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2Feature3Feature1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());
		
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3Feature3Feature1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());		
	}
  
  @Test
	public void testCreateArrayFeatureStructuresWithDelimterTokens() throws Exception{
		JCas cas = createCasWithText("token1 token2 token3");
		Annotation1 annotation1 = new Annotation1(cas);
		annotation1.setBegin(0);
		annotation1.setEnd(6);
		
		FSArray featureStructure1Array = new FSArray(cas, 3);
		FeatureStructure1 featureStructure1 = new FeatureStructure1(cas);
		featureStructure1.setFeature1("token1FeatureStructures1Feature10");
		featureStructure1.setFeature2("token1FeatureStructures1Feature20");
	
		featureStructure1Array.set(0, featureStructure1);
	
		featureStructure1 = new FeatureStructure1(cas);
		featureStructure1.setFeature1("token1FeatureStructures1Feature11");
		featureStructure1.setFeature2("token1FeatureStructures1Feature21");
		
		featureStructure1Array.set(1, featureStructure1);
	
		featureStructure1 = new FeatureStructure1(cas);
		featureStructure1.setFeature1("token1FeatureStructures1Feature12");
		featureStructure1.setFeature2("token1FeatureStructures1Feature22");
		
		featureStructure1Array.set(2, featureStructure1);
		
		annotation1.setFeatureStructures1(featureStructure1Array);
		annotation1.addToIndexes();
		
		Annotation1 annotation2 = new Annotation1(cas);
		annotation2.setBegin(7);
		annotation2.setEnd(13);
	
		featureStructure1Array = new FSArray(cas, 3);
		featureStructure1 = new FeatureStructure1(cas);
		featureStructure1.setFeature1("token2FeatureStructures1Feature10");
		featureStructure1.setFeature2("token2FeatureStructures1Feature20");
				
		featureStructure1Array.set(0, featureStructure1);
	
		featureStructure1 = new FeatureStructure1(cas);
		featureStructure1.setFeature1("token2FeatureStructures1Feature11");
		featureStructure1.setFeature2("token2FeatureStructures1Feature21");
		
		featureStructure1Array.set(1, featureStructure1);
	
		featureStructure1 = new FeatureStructure1(cas);
		featureStructure1.setFeature1("token2FeatureStructures1Feature12");
		featureStructure1.setFeature2("token2FeatureStructures1Feature22");		
		
		featureStructure1Array.set(2, featureStructure1);
		
		annotation2.setFeatureStructures1(featureStructure1Array);
		annotation2.addToIndexes();
		
		Annotation1 annotation3 = new Annotation1(cas);
		annotation3.setBegin(14);
		annotation3.setEnd(20);
		
		featureStructure1Array = new FSArray(cas, 3);
		featureStructure1 = new FeatureStructure1(cas);
		featureStructure1.setFeature1("token3FeatureStructures1Feature10");
		featureStructure1.setFeature2("token3FeatureStructures1Feature20");
		
		featureStructure1Array.set(0, featureStructure1);
	
		featureStructure1 = new FeatureStructure1(cas);
		featureStructure1.setFeature1("token3FeatureStructures1Feature11");
		featureStructure1.setFeature2("token3FeatureStructures1Feature21");
		
		featureStructure1Array.set(1, featureStructure1);
	
		featureStructure1 = new FeatureStructure1(cas);
		featureStructure1.setFeature1("token3FeatureStructures1Feature12");
		featureStructure1.setFeature2("token3FeatureStructures1Feature22");
		
		featureStructure1Array.set(2, featureStructure1);
		
		annotation3.setFeatureStructures1(featureStructure1Array);
		annotation3.addToIndexes();
	
		List<String> featureNames = new ArrayList<String>();
		featureNames.add("feature1");
		featureNames.add("feature2");
	
		AnnotationTokenStream annotationTokenStream = new AnnotationTokenStream(cas, CAS.NAME_DEFAULT_SOFA, "org.apache.uima.lucas.indexer.types.test.Annotation1", "featureStructures1", featureNames, ", ", null);
		Token nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1FeatureStructures1Feature10, token1FeatureStructures1Feature20", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());
	
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1FeatureStructures1Feature11, token1FeatureStructures1Feature21", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());
		
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1FeatureStructures1Feature12, token1FeatureStructures1Feature22", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());
		
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2FeatureStructures1Feature10, token2FeatureStructures1Feature20", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());
	
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2FeatureStructures1Feature11, token2FeatureStructures1Feature21", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());
	
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2FeatureStructures1Feature12, token2FeatureStructures1Feature22", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());
		
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3FeatureStructures1Feature10, token3FeatureStructures1Feature20", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());
	
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3FeatureStructures1Feature11, token3FeatureStructures1Feature21", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());
	
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3FeatureStructures1Feature12, token3FeatureStructures1Feature22", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());		
	}

//	public void testCreateArrayFeatureStructuresWithoutDelimterTokens() throws Exception{
//		JCas cas = createCasWithText("token1 token2 token3");
//		Annotation1 annotation1 = new Annotation1(cas);
//		annotation1.setBegin(0);
//		annotation1.setEnd(6);
//		
//		FSArray featureStructure1Array = new FSArray(cas, 3);
//		FeatureStructure1 featureStructure1 = new FeatureStructure1(cas);
//		featureStructure1.setFeature1("token1FeatureStructures1Feature10");
//		featureStructure1.setFeature2("token1FeatureStructures1Feature20");
//	
//		featureStructure1Array.set(0, featureStructure1);
//	
//		featureStructure1 = new FeatureStructure1(cas);
//		featureStructure1.setFeature1("token1FeatureStructures1Feature11");
//		featureStructure1.setFeature2("token1FeatureStructures1Feature21");
//		
//		featureStructure1Array.set(1, featureStructure1);
//	
//		featureStructure1 = new FeatureStructure1(cas);
//		featureStructure1.setFeature1("token1FeatureStructures1Feature12");
//		featureStructure1.setFeature2("token1FeatureStructures1Feature22");
//		
//		featureStructure1Array.set(2, featureStructure1);
//		
//		annotation1.setFeatureStructures1(featureStructure1Array);
//		annotation1.addToIndexes();
//		
//		Annotation1 annotation2 = new Annotation1(cas);
//		annotation2.setBegin(7);
//		annotation2.setEnd(13);
//	
//		featureStructure1Array = new FSArray(cas, 3);
//		featureStructure1 = new FeatureStructure1(cas);
//		featureStructure1.setFeature1("token2FeatureStructures1Feature10");
//		featureStructure1.setFeature2("token2FeatureStructures1Feature20");
//				
//		featureStructure1Array.set(0, featureStructure1);
//	
//		featureStructure1 = new FeatureStructure1(cas);
//		featureStructure1.setFeature1("token2FeatureStructures1Feature11");
//		featureStructure1.setFeature2("token2FeatureStructures1Feature21");
//		
//		featureStructure1Array.set(1, featureStructure1);
//	
//		featureStructure1 = new FeatureStructure1(cas);
//		featureStructure1.setFeature1("token2FeatureStructures1Feature12");
//		featureStructure1.setFeature2("token2FeatureStructures1Feature22");		
//		
//		featureStructure1Array.set(2, featureStructure1);
//		
//		annotation2.setFeatureStructures1(featureStructure1Array);
//		annotation2.addToIndexes();
//		
//		Annotation1 annotation3 = new Annotation1(cas);
//		annotation3.setBegin(14);
//		annotation3.setEnd(20);
//		
//		featureStructure1Array = new FSArray(cas, 3);
//		featureStructure1 = new FeatureStructure1(cas);
//		featureStructure1.setFeature1("token3FeatureStructures1Feature10");
//		featureStructure1.setFeature2("token3FeatureStructures1Feature20");
//		
//		featureStructure1Array.set(0, featureStructure1);
//	
//		featureStructure1 = new FeatureStructure1(cas);
//		featureStructure1.setFeature1("token3FeatureStructures1Feature11");
//		featureStructure1.setFeature2("token3FeatureStructures1Feature21");
//		
//		featureStructure1Array.set(1, featureStructure1);
//	
//		featureStructure1 = new FeatureStructure1(cas);
//		featureStructure1.setFeature1("token3FeatureStructures1Feature12");
//		featureStructure1.setFeature2("token3FeatureStructures1Feature22");
//		
//		featureStructure1Array.set(2, featureStructure1);
//		
//		annotation3.setFeatureStructures1(featureStructure1Array);
//		annotation3.addToIndexes();
//	
//		List<String> featureNames = new ArrayList<String>();
//		featureNames.add("feature1");
//		featureNames.add("feature2");
//	
//
//		AnnotationTokenStream annotationTokenStream = new AnnotationTokenStream(cas, "org.apache.uima.lucas.indexer.types.test.Annotation1", "featureStructures1", featureNames, null);
//		Token nextToken = annotationTokenStream.next();
//		assertNotNull(nextToken);
//		assertEquals("token1FeatureStructures1Feature10", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
//		assertEquals(0, nextToken.startOffset());
//		assertEquals(6, nextToken.endOffset());
//	
//		nextToken = annotationTokenStream.next();
//		assertNotNull(nextToken);
//		assertEquals("token1FeatureStructures1Feature11", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
//		assertEquals(0, nextToken.startOffset());
//		assertEquals(6, nextToken.endOffset());
//		
//		nextToken = annotationTokenStream.next();
//		assertNotNull(nextToken);
//		assertEquals("token1FeatureStructures1Feature12", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
//		assertEquals(0, nextToken.startOffset());
//		assertEquals(6, nextToken.endOffset());
//		
//		nextToken = annotationTokenStream.next();
//		assertNotNull(nextToken);
//		assertEquals("token2FeatureStructures1Feature10", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
//		assertEquals(7, nextToken.startOffset());
//		assertEquals(13, nextToken.endOffset());
//	
//		nextToken = annotationTokenStream.next();
//		assertNotNull(nextToken);
//		assertEquals("token2FeatureStructures1Feature11", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
//		assertEquals(7, nextToken.startOffset());
//		assertEquals(13, nextToken.endOffset());
//	
//		nextToken = annotationTokenStream.next();
//		assertNotNull(nextToken);
//		assertEquals("token2FeatureStructures1Feature12", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
//		assertEquals(7, nextToken.startOffset());
//		assertEquals(13, nextToken.endOffset());
//		
//		nextToken = annotationTokenStream.next();
//		assertNotNull(nextToken);
//		assertEquals("token3FeatureStructures1Feature10", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
//		assertEquals(14, nextToken.startOffset());
//		assertEquals(20, nextToken.endOffset());
//	
//		nextToken = annotationTokenStream.next();
//		assertNotNull(nextToken);
//		assertEquals("token3FeatureStructures1Feature11", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
//		assertEquals(14, nextToken.startOffset());
//		assertEquals(20, nextToken.endOffset());
//	
//		nextToken = annotationTokenStream.next();
//		assertNotNull(nextToken);
//		assertEquals("token3FeatureStructures1Feature12", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
//		assertEquals(14, nextToken.startOffset());
//		assertEquals(20, nextToken.endOffset());
//	}

  
  @Test
	public void testCreateArrayFeatureStructuresWithSinleFeature() throws Exception{
			JCas cas = createCasWithText("token1 token2 token3");
			Annotation1 annotation1 = new Annotation1(cas);
			annotation1.setBegin(0);
			annotation1.setEnd(6);
			
			FSArray featureStructure1Array = new FSArray(cas, 3);
			
			FeatureStructure1 featureStructure1 = new FeatureStructure1(cas);
			featureStructure1.setFeature1("token1FeatureStructures1Feature10");
			featureStructure1Array.set(0, featureStructure1);
			
			featureStructure1 = new FeatureStructure1(cas);
			featureStructure1.setFeature1("token1FeatureStructures1Feature11");			
			featureStructure1Array.set(1, featureStructure1);

			featureStructure1 = new FeatureStructure1(cas);
			featureStructure1.setFeature1("token1FeatureStructures1Feature12");					
			featureStructure1Array.set(2, featureStructure1);
			
			annotation1.setFeatureStructures1(featureStructure1Array);
			annotation1.addToIndexes();
			
			Annotation1 annotation2 = new Annotation1(cas);
			annotation2.setBegin(7);
			annotation2.setEnd(13);

			featureStructure1Array = new FSArray(cas, 3);

			featureStructure1 = new FeatureStructure1(cas);
			featureStructure1.setFeature1("token2FeatureStructures1Feature10");			
			featureStructure1Array.set(0, featureStructure1);

			featureStructure1 = new FeatureStructure1(cas);
			featureStructure1.setFeature1("token2FeatureStructures1Feature11");			
			featureStructure1Array.set(1, featureStructure1);

			featureStructure1 = new FeatureStructure1(cas);
			featureStructure1.setFeature1("token2FeatureStructures1Feature12");
			featureStructure1Array.set(2, featureStructure1);
			
			annotation2.setFeatureStructures1(featureStructure1Array);
			annotation2.addToIndexes();
			
			Annotation1 annotation3 = new Annotation1(cas);
			annotation3.setBegin(14);
			annotation3.setEnd(20);
			
			featureStructure1Array = new FSArray(cas, 3);
			featureStructure1 = new FeatureStructure1(cas);
			featureStructure1.setFeature1("token3FeatureStructures1Feature10");
			featureStructure1Array.set(0, featureStructure1);

			featureStructure1 = new FeatureStructure1(cas);
			featureStructure1.setFeature1("token3FeatureStructures1Feature11");
			featureStructure1Array.set(1, featureStructure1);

			featureStructure1 = new FeatureStructure1(cas);
			featureStructure1.setFeature1("token3FeatureStructures1Feature12");
			featureStructure1Array.set(2, featureStructure1);
			
			annotation3.setFeatureStructures1(featureStructure1Array);
			annotation3.addToIndexes();

			List<String> featureNames = new ArrayList<String>();
			featureNames.add("feature1");
					
			AnnotationTokenStream annotationTokenStream = new AnnotationTokenStream(cas, CAS.NAME_DEFAULT_SOFA, "org.apache.uima.lucas.indexer.types.test.Annotation1", "featureStructures1", featureNames, null);
			Token nextToken = annotationTokenStream.next();
			assertNotNull(nextToken);
			assertEquals("token1FeatureStructures1Feature10", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
			assertEquals(0, nextToken.startOffset());
			assertEquals(6, nextToken.endOffset());

			nextToken = annotationTokenStream.next();
			assertNotNull(nextToken);
			assertEquals("token1FeatureStructures1Feature11", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
			assertEquals(0, nextToken.startOffset());
			assertEquals(6, nextToken.endOffset());
			
			nextToken = annotationTokenStream.next();
			assertNotNull(nextToken);
			assertEquals("token1FeatureStructures1Feature12", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
			assertEquals(0, nextToken.startOffset());
			assertEquals(6, nextToken.endOffset());
			
			nextToken = annotationTokenStream.next();
			assertNotNull(nextToken);
			assertEquals("token2FeatureStructures1Feature10", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
			assertEquals(7, nextToken.startOffset());
			assertEquals(13, nextToken.endOffset());

			nextToken = annotationTokenStream.next();
			assertNotNull(nextToken);
			assertEquals("token2FeatureStructures1Feature11", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
			assertEquals(7, nextToken.startOffset());
			assertEquals(13, nextToken.endOffset());

			nextToken = annotationTokenStream.next();
			assertNotNull(nextToken);
			assertEquals("token2FeatureStructures1Feature12", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
			assertEquals(7, nextToken.startOffset());
			assertEquals(13, nextToken.endOffset());
			
			nextToken = annotationTokenStream.next();
			assertNotNull(nextToken);
			assertEquals("token3FeatureStructures1Feature10", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
			assertEquals(14, nextToken.startOffset());
			assertEquals(20, nextToken.endOffset());

			nextToken = annotationTokenStream.next();
			assertNotNull(nextToken);
			assertEquals("token3FeatureStructures1Feature11", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
			assertEquals(14, nextToken.startOffset());
			assertEquals(20, nextToken.endOffset());

			nextToken = annotationTokenStream.next();
			assertNotNull(nextToken);
			assertEquals("token3FeatureStructures1Feature12", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
			assertEquals(14, nextToken.startOffset());
			assertEquals(20, nextToken.endOffset());		
	}
  
  @Test
	public void testCreateArrayFeatureWithFeaturePath() throws Exception{		
		JCas cas = createCasWithText("token1 token2 token3");
		Annotation1 annotation1 = new Annotation1(cas);
		annotation1.setBegin(0);
		annotation1.setEnd(6);
		
		FSArray featureStructure1Array = new FSArray(cas, 3);
		FeatureStructure1 featureStructure1 = new FeatureStructure1(cas);

		FeatureStructure2 featureStructure2 = new FeatureStructure2(cas);
		featureStructure2.setFeature1("token1FeatureStructures1Feature3Feature10");
	
		featureStructure1.setFeature3(featureStructure2);
		featureStructure1Array.set(0, featureStructure1);

		featureStructure1 = new FeatureStructure1(cas);
		
		featureStructure2 = new FeatureStructure2(cas);
		featureStructure2.setFeature1("token1FeatureStructures1Feature3Feature11");

		featureStructure1.setFeature3(featureStructure2);
		featureStructure1Array.set(1, featureStructure1);

		featureStructure1 = new FeatureStructure1(cas);
		
		featureStructure2 = new FeatureStructure2(cas);
		featureStructure2.setFeature1("token1FeatureStructures1Feature3Feature12");
		featureStructure1.setFeature3(featureStructure2);
		featureStructure1Array.set(2, featureStructure1);
		
		annotation1.setFeatureStructures1(featureStructure1Array);
		annotation1.addToIndexes();
		
		Annotation1 annotation2 = new Annotation1(cas);
		annotation2.setBegin(7);
		annotation2.setEnd(13);

		featureStructure1Array = new FSArray(cas, 3);
		featureStructure1 = new FeatureStructure1(cas);
	
		featureStructure2 = new FeatureStructure2(cas);
		featureStructure2.setFeature1("token2FeatureStructures1Feature3Feature10");
		
		featureStructure1.setFeature3(featureStructure2);
		featureStructure1Array.set(0, featureStructure1);

		featureStructure1 = new FeatureStructure1(cas);
		
		featureStructure2 = new FeatureStructure2(cas);
		featureStructure2.setFeature1("token2FeatureStructures1Feature3Feature11");
		
		featureStructure1.setFeature3(featureStructure2);
		featureStructure1Array.set(1, featureStructure1);

		featureStructure1 = new FeatureStructure1(cas);
		
		featureStructure2 = new FeatureStructure2(cas);
		featureStructure2.setFeature1("token2FeatureStructures1Feature3Feature12");
		
		featureStructure1.setFeature3(featureStructure2);
		featureStructure1Array.set(2, featureStructure1);
		
		annotation2.setFeatureStructures1(featureStructure1Array);
		annotation2.addToIndexes();
		
		Annotation1 annotation3 = new Annotation1(cas);
		annotation3.setBegin(14);
		annotation3.setEnd(20);
		
		featureStructure1Array = new FSArray(cas, 3);
		featureStructure1 = new FeatureStructure1(cas);
		
		featureStructure2 = new FeatureStructure2(cas);
		featureStructure2.setFeature1("token3FeatureStructures1Feature3Feature10");
		
		featureStructure1.setFeature3(featureStructure2);
		featureStructure1Array.set(0, featureStructure1);

		featureStructure1 = new FeatureStructure1(cas);
		
		featureStructure2 = new FeatureStructure2(cas);
		featureStructure2.setFeature1("token3FeatureStructures1Feature3Feature11");
				
		featureStructure1.setFeature3(featureStructure2);
		featureStructure1Array.set(1, featureStructure1);

		featureStructure1 = new FeatureStructure1(cas);
		featureStructure1.setFeature1("token3FeatureStructures1Feature12");
		featureStructure1.setFeature2("token3FeatureStructures1Feature22");
			
		featureStructure2 = new FeatureStructure2(cas);
		featureStructure2.setFeature1("token3FeatureStructures1Feature3Feature12");
		
		featureStructure1.setFeature3(featureStructure2);
		featureStructure1Array.set(2, featureStructure1);
		
		annotation3.setFeatureStructures1(featureStructure1Array);
		annotation3.addToIndexes();

		List<String> featureNames = new ArrayList<String>();
		featureNames.add("feature1");
				
		AnnotationTokenStream annotationTokenStream = new AnnotationTokenStream(cas, CAS.NAME_DEFAULT_SOFA, "org.apache.uima.lucas.indexer.types.test.Annotation1", "featureStructures1.feature3", featureNames, null);
		Token nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1FeatureStructures1Feature3Feature10", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1FeatureStructures1Feature3Feature11", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());
		
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1FeatureStructures1Feature3Feature12", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());
		
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2FeatureStructures1Feature3Feature10", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2FeatureStructures1Feature3Feature11", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2FeatureStructures1Feature3Feature12", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());
		
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3FeatureStructures1Feature3Feature10", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3FeatureStructures1Feature3Feature11", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3FeatureStructures1Feature3Feature12", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());
	}
  
  @Test
	public void testCreateArrayFeaturesWithFeaturePathAndStringArray() throws Exception{

		JCas cas = createCasWithText("token1 token2 token3");
		Annotation1 annotation1 = new Annotation1(cas);
		annotation1.setBegin(0);
		annotation1.setEnd(6);

		FSArray featureStructure1Array = new FSArray(cas, 3);
		FeatureStructure1 featureStructure1 = new FeatureStructure1(cas);
		featureStructure1.setFeature1("token1FeatureStructures1Feature10");
		featureStructure1.setFeature2("token1FeatureStructures1Feature20");
		StringArray stringArray1 = new StringArray(cas, 3);
		stringArray1.set(0, "token1FeatureStructures1Feature3Feature3_0_0");
		stringArray1.set(1, "token1FeatureStructures1Feature3Feature3_0_1");
		stringArray1.set(2, "token1FeatureStructures1Feature3Feature3_0_2");

		FeatureStructure2 featureStructure2 = new FeatureStructure2(cas);
		featureStructure2.setFeature1("token1FeatureStructures1Feature3Feature10");
		featureStructure2.setFeature3(stringArray1);		
		featureStructure1.setFeature3(featureStructure2);
		featureStructure1Array.set(0, featureStructure1);

		featureStructure1 = new FeatureStructure1(cas);

		stringArray1 = new StringArray(cas, 3);
		stringArray1.set(0, "token1FeatureStructures1Feature3Feature3_1_0");
		stringArray1.set(1, "token1FeatureStructures1Feature3Feature3_1_1");
		stringArray1.set(2, "token1FeatureStructures1Feature3Feature3_1_2");

		featureStructure2 = new FeatureStructure2(cas);

		featureStructure2.setFeature3(stringArray1);
		featureStructure1.setFeature3(featureStructure2);
		featureStructure1Array.set(1, featureStructure1);

		featureStructure1 = new FeatureStructure1(cas);

		stringArray1 = new StringArray(cas, 3);
		stringArray1.set(0, "token1FeatureStructures1Feature3Feature3_2_0");
		stringArray1.set(1, "token1FeatureStructures1Feature3Feature3_2_1");
		stringArray1.set(2, "token1FeatureStructures1Feature3Feature3_2_2");
		featureStructure2 = new FeatureStructure2(cas);

		featureStructure2.setFeature3(stringArray1);
		featureStructure1.setFeature3(featureStructure2);
		featureStructure1Array.set(2, featureStructure1);

		annotation1.setFeatureStructures1(featureStructure1Array);
		annotation1.addToIndexes();

		Annotation1 annotation2 = new Annotation1(cas);
		annotation2.setBegin(7);
		annotation2.setEnd(13);

		featureStructure1Array = new FSArray(cas, 3);
		featureStructure1 = new FeatureStructure1(cas);

		stringArray1 = new StringArray(cas, 3);
		stringArray1.set(0, "token2FeatureStructures1Feature3Feature3_0_0");
		stringArray1.set(1, "token2FeatureStructures1Feature3Feature3_0_1");
		stringArray1.set(2, "token2FeatureStructures1Feature3Feature3_0_2");

		featureStructure2 = new FeatureStructure2(cas);
		featureStructure2.setFeature3(stringArray1);

		featureStructure1.setFeature3(featureStructure2);
		featureStructure1Array.set(0, featureStructure1);

		featureStructure1 = new FeatureStructure1(cas);

		stringArray1 = new StringArray(cas, 3);
		stringArray1.set(0, "token2FeatureStructures1Feature3Feature3_1_0");
		stringArray1.set(1, "token2FeatureStructures1Feature3Feature3_1_1");
		stringArray1.set(2, "token2FeatureStructures1Feature3Feature3_1_2");

		featureStructure2 = new FeatureStructure2(cas);
		featureStructure2.setFeature3(stringArray1);

		featureStructure1.setFeature3(featureStructure2);
		featureStructure1Array.set(1, featureStructure1);

		stringArray1 = new StringArray(cas, 3);
		stringArray1.set(0, "token2FeatureStructures1Feature3Feature3_2_0");
		stringArray1.set(1, "token2FeatureStructures1Feature3Feature3_2_1");
		stringArray1.set(2, "token2FeatureStructures1Feature3Feature3_2_2");

		featureStructure1 = new FeatureStructure1(cas);

		featureStructure2 = new FeatureStructure2(cas);
		featureStructure2.setFeature3(stringArray1);

		featureStructure1.setFeature3(featureStructure2);
		featureStructure1Array.set(2, featureStructure1);

		annotation2.setFeatureStructures1(featureStructure1Array);
		annotation2.addToIndexes();

		Annotation1 annotation3 = new Annotation1(cas);
		annotation3.setBegin(14);
		annotation3.setEnd(20);

		featureStructure1Array = new FSArray(cas, 3);
		featureStructure1 = new FeatureStructure1(cas);

		stringArray1 = new StringArray(cas, 3);
		stringArray1.set(0, "token3FeatureStructures1Feature3Feature3_0_0");
		stringArray1.set(1, "token3FeatureStructures1Feature3Feature3_0_1");
		stringArray1.set(2, "token3FeatureStructures1Feature3Feature3_0_2");

		featureStructure2 = new FeatureStructure2(cas);
		featureStructure2.setFeature3(stringArray1);

		featureStructure1.setFeature3(featureStructure2);
		featureStructure1Array.set(0, featureStructure1);

		featureStructure1 = new FeatureStructure1(cas);

		stringArray1 = new StringArray(cas, 3);
		stringArray1.set(0, "token3FeatureStructures1Feature3Feature3_1_0");
		stringArray1.set(1, "token3FeatureStructures1Feature3Feature3_1_1");
		stringArray1.set(2, "token3FeatureStructures1Feature3Feature3_1_2");		

		featureStructure2 = new FeatureStructure2(cas);
		featureStructure2.setFeature3(stringArray1);

		featureStructure1.setFeature3(featureStructure2);
		featureStructure1Array.set(1, featureStructure1);

		featureStructure1 = new FeatureStructure1(cas);

		stringArray1 = new StringArray(cas, 3);
		stringArray1.set(0, "token3FeatureStructures1Feature3Feature3_2_0");
		stringArray1.set(1, "token3FeatureStructures1Feature3Feature3_2_1");
		stringArray1.set(2, "token3FeatureStructures1Feature3Feature3_2_2");		

		featureStructure2 = new FeatureStructure2(cas);
		featureStructure2.setFeature3(stringArray1);

		featureStructure1.setFeature3(featureStructure2);
		featureStructure1Array.set(2, featureStructure1);

		annotation3.setFeatureStructures1(featureStructure1Array);
		annotation3.addToIndexes();

		List<String> featureNames = new ArrayList<String>();
		featureNames.add("feature3");

		AnnotationTokenStream annotationTokenStream = new AnnotationTokenStream(cas, CAS.NAME_DEFAULT_SOFA, "org.apache.uima.lucas.indexer.types.test.Annotation1", "featureStructures1.feature3", featureNames, null);

		Token nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1FeatureStructures1Feature3Feature3_0_0", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1FeatureStructures1Feature3Feature3_0_1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1FeatureStructures1Feature3Feature3_0_2", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1FeatureStructures1Feature3Feature3_1_0", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1FeatureStructures1Feature3Feature3_1_1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1FeatureStructures1Feature3Feature3_1_2", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1FeatureStructures1Feature3Feature3_2_0", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1FeatureStructures1Feature3Feature3_2_1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1FeatureStructures1Feature3Feature3_2_2", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2FeatureStructures1Feature3Feature3_0_0", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2FeatureStructures1Feature3Feature3_0_1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2FeatureStructures1Feature3Feature3_0_2", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2FeatureStructures1Feature3Feature3_1_0", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2FeatureStructures1Feature3Feature3_1_1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2FeatureStructures1Feature3Feature3_1_2", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2FeatureStructures1Feature3Feature3_2_0", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2FeatureStructures1Feature3Feature3_2_1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2FeatureStructures1Feature3Feature3_2_2", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3FeatureStructures1Feature3Feature3_0_0", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3FeatureStructures1Feature3Feature3_0_1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3FeatureStructures1Feature3Feature3_0_2", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3FeatureStructures1Feature3Feature3_1_0", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3FeatureStructures1Feature3Feature3_1_1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3FeatureStructures1Feature3Feature3_1_2", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3FeatureStructures1Feature3Feature3_2_0", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3FeatureStructures1Feature3Feature3_2_1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3FeatureStructures1Feature3Feature3_2_2", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());		

	}
  
  @Test
	public void testReset() throws Exception{
		JCas cas = createCasWithText("token1 token2 token3");
		Annotation1 annotation1 = new Annotation1(cas);
		annotation1.setBegin(0);
		annotation1.setEnd(6);
		annotation1.addToIndexes();
		Annotation1 annotation2 = new Annotation1(cas);
		annotation2.setBegin(7);
		annotation2.setEnd(13);
		annotation2.addToIndexes();
		
		Annotation1 annotation3 = new Annotation1(cas);
		annotation3.setBegin(14);
		annotation3.setEnd(20);
		annotation3.addToIndexes();
		
		AnnotationTokenStream annotationTokenStream = new AnnotationTokenStream(cas, CAS.NAME_DEFAULT_SOFA, "org.apache.uima.lucas.indexer.types.test.Annotation1");
		Token nextToken = annotationTokenStream.next();
		nextToken = annotationTokenStream.next();
		nextToken = annotationTokenStream.next();
		annotationTokenStream.reset();

		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());
		
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token2", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());
		
		nextToken = annotationTokenStream.next();
		assertNotNull(nextToken);
		assertEquals("token3", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(14, nextToken.startOffset());
		assertEquals(20, nextToken.endOffset());
	}
  
  @Test 
	public void testValidate() throws Exception{
    JCas cas = createCasWithText("token1 token2 token3");
    AnnotationTokenStream annotationTokenStream = new AnnotationTokenStream(cas, CAS.NAME_DEFAULT_SOFA, "org.apache.uima.lucas.indexer.types.test.Annotation1");
    Type annotation1Type = (Type) cas.getTypeSystem().getType(Annotation1.class.getCanonicalName());
    annotationTokenStream.validate(annotation1Type, Lists.newArrayList("featureString"), null);    
  }
  
  @Test(expected= InvalidTokenSourceException.class)
  public void testValidateFail() throws Exception{
    JCas cas = createCasWithText("token1 token2 token3");
    AnnotationTokenStream annotationTokenStream = new AnnotationTokenStream(cas, CAS.NAME_DEFAULT_SOFA, "org.apache.uima.lucas.indexer.types.test.Annotation1");
    Type annotation1Type = (Type) cas.getTypeSystem().getType(Annotation1.class.getCanonicalName());
    annotationTokenStream.validate(annotation1Type, Lists.newArrayList("featureStringss"), null);    
  }

  @Test(expected= InvalidTokenSourceException.class)
  public void testValidateFailWithWrongType() throws Exception{
    JCas cas = createCasWithText("token1 token2 token3");
    new AnnotationTokenStream(cas, CAS.NAME_DEFAULT_SOFA, "org.apache.uima.lucas.indexer.types.test.Annotation3");    
  }
}
