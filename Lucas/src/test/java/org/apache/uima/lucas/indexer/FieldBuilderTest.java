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

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.easymock.classextension.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Field;
import org.apache.uima.jcas.JCas;
import org.apache.uima.lucas.indexer.analysis.TokenStreamConcatenator;
import org.apache.uima.lucas.indexer.analysis.TokenStreamMerger;
import org.apache.uima.lucas.indexer.mapping.AnnotationDescription;
import org.apache.uima.lucas.indexer.mapping.FieldDescription;
import org.apache.uima.lucas.indexer.test.util.CollectionTokenStream;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

public class FieldBuilderTest {

	private FieldBuilder fieldBuilder;
	private FieldDescription fieldDescription;
	private AnnotationDescription annotationDescription1;
	private AnnotationDescription annotationDescription2;
	private JCas cas;
	
	private TokenStream tokenStream1;
	private TokenStream tokenStream2;
	private List<TokenStream> tokenStreams;
	private FilterBuilder filterBuilder;
	
	@Before
	public void setUp(){
		annotationDescription1= new AnnotationDescription("uima.cas.Annotation");		
		annotationDescription2= new AnnotationDescription("uima.cas.Annotation");
		filterBuilder = createMock(FilterBuilder.class);
		fieldBuilder = new FieldBuilder(filterBuilder);
		cas = createMock(JCas.class);

		Collection<Token> tokens1 = new ArrayList<Token>();
		tokens1.add(new Token("token1".toCharArray(),0,6,0,6));
		tokens1.add(new Token("token2".toCharArray(),0,6,7,13));
		tokens1.add(new Token("token3".toCharArray(),0,6,14,20));

		Collection<Token> tokens2 = new ArrayList<Token>();
		tokens2.add(new Token("token4".toCharArray(),0,6,0,6));
		tokens2.add(new Token("token5".toCharArray(),0,6,7,13));
		tokens2.add(new Token("token6".toCharArray(),0,6,14,20));

		tokenStream1 = new CollectionTokenStream(tokens1);
		tokenStream2 = new CollectionTokenStream(tokens2);

		tokenStreams = Lists.newArrayList(tokenStream1, tokenStream2);
		
		fieldDescription = new FieldDescription("field1");
		fieldDescription.getAnnotationDescriptions().add(annotationDescription1);
		fieldDescription.getAnnotationDescriptions().add(annotationDescription2);
		fieldDescription.setFilterDescriptions(Collections.EMPTY_LIST);
	}
	
	@Test
	public void testCreateFieldConcatenated() throws Exception{
		
		fieldDescription.setIndex(FieldBuilder.FIELD_INDEX_YES);

		TokenStream tokenStream = createMock(TokenStream.class);
		expect(filterBuilder.filter(isA(TokenStreamConcatenator.class), isA(Collection.class))).andReturn(tokenStream);
		replay(filterBuilder);
		
		Collection<Field> fields = fieldBuilder.createFields(tokenStreams, fieldDescription);
		verify(filterBuilder);
		Iterator<Field> fieldIterator = fields.iterator();
		Field field1 = fieldIterator.next();
		assertEquals("field1", field1.name());
		assertEquals(tokenStream, field1.tokenStreamValue());

	}
	
	@Test
	public void testCreateFieldMerged() throws Exception{
		
		fieldDescription.setMerge(true);
		fieldDescription.setIndex(FieldBuilder.FIELD_INDEX_YES);
    TokenStream tokenStream = createMock(TokenStream.class);
    expect(filterBuilder.filter(isA(TokenStreamMerger.class), isA(Collection.class))).andReturn(tokenStream);
    replay(filterBuilder);
    
    Collection<Field> fields = fieldBuilder.createFields(tokenStreams, fieldDescription);
    verify(filterBuilder);

		Iterator<Field> fieldIterator = fields.iterator();
		Field field1 = fieldIterator.next();
		assertEquals("field1", field1.name());
    assertEquals(tokenStream, field1.tokenStreamValue());
	}

	@Test
	public void testCreateFieldNoIndex() throws Exception{
		
		fieldDescription.setMerge(true);
		fieldDescription.setIndex(FieldBuilder.FIELD_INDEX_NO);
		
		Collection<Field> fields = fieldBuilder.createFields(tokenStreams, fieldDescription);
		
		assertEquals(0, fields.size());
	}

	@Test
	public void testCreateFieldNoNorms() throws Exception{
		fieldDescription.setMerge(true);
		fieldDescription.setIndex(FieldBuilder.FIELD_INDEX_NO_NORMS);

		TokenStream tokenStream = createMock(TokenStream.class);
    expect(filterBuilder.filter(isA(TokenStreamMerger.class), isA(Collection.class))).andReturn(tokenStream);
    replay(filterBuilder);
		
    Collection<Field> fields = fieldBuilder.createFields(tokenStreams, fieldDescription);
		
    verify(filterBuilder);
		Iterator<Field> fieldIterator = fields.iterator();
		Field field1 = fieldIterator.next();
		assertEquals("field1", field1.name());
		assertTrue(field1.getOmitNorms());
		assertTrue(field1.isIndexed());
		assertFalse(field1.isStored());
	}
	
	@Test
	public void testCreateFieldNoTF() throws Exception{
		fieldDescription.setMerge(true);
		fieldDescription.setIndex(FieldBuilder.FIELD_INDEX_NO_TF);
		
		TokenStream tokenStream = createMock(TokenStream.class);
		expect(filterBuilder.filter(isA(TokenStreamMerger.class), isA(Collection.class))).andReturn(tokenStream);
		replay(filterBuilder);
		
		Collection<Field> fields = fieldBuilder.createFields(tokenStreams, fieldDescription);
		verify(filterBuilder);
		
		Iterator<Field> fieldIterator = fields.iterator();
		Field field1 = fieldIterator.next();
		assertEquals("field1", field1.name());
		assertTrue(field1.getOmitTf());
		assertFalse(field1.getOmitNorms());
		assertTrue(field1.isIndexed());
		assertFalse(field1.isStored());
	}
	
	@Test
	public void testCreateFieldNoNormsTF() throws Exception{
		
		fieldDescription.setMerge(true);
		fieldDescription.setIndex(FieldBuilder.FIELD_INDEX_NO_NORMS_TF);

		TokenStream tokenStream = createMock(TokenStream.class);
    expect(filterBuilder.filter(isA(TokenStreamMerger.class), isA(Collection.class))).andReturn(tokenStream);
    replay(filterBuilder);
		
		Collection<Field> fields = fieldBuilder.createFields(tokenStreams, fieldDescription);
		verify(filterBuilder);		
		Iterator<Field> fieldIterator = fields.iterator();
		Field field1 = fieldIterator.next();
		assertEquals("field1", field1.name());
		assertTrue(field1.getOmitTf());
		assertTrue(field1.getOmitNorms());
		assertTrue(field1.isIndexed());
		assertFalse(field1.isStored());
	}
	
	@Test
	public void testCreateFieldTermVector() throws Exception{
		
		fieldDescription.setMerge(true);
		fieldDescription.setIndex(FieldBuilder.FIELD_INDEX_YES);
		fieldDescription.setTermVector(FieldBuilder.FIELD_TERM_VECTOR_YES);
		tokenStreams.remove(1);

    TokenStream tokenStream = createMock(TokenStream.class);
    expect(filterBuilder.filter(isA(TokenStreamMerger.class), isA(Collection.class))).andReturn(tokenStream);
    replay(filterBuilder);

		Collection<Field> fields = fieldBuilder.createFields(tokenStreams, fieldDescription);
    verify(filterBuilder);
    
		Iterator<Field> fieldIterator = fields.iterator();
		Field field1 = fieldIterator.next();
		assertEquals("field1", field1.name());
		assertFalse(field1.isStoreOffsetWithTermVector());
		assertTrue(field1.isTermVectorStored());
		assertFalse(field1.isStorePositionWithTermVector());
	}
	
	@Test
	public void testCreateFieldTermVectorOffset() throws Exception{
		fieldDescription.setMerge(true);
		fieldDescription.setIndex(FieldBuilder.FIELD_INDEX_YES);
		fieldDescription.setTermVector(FieldBuilder.FIELD_TERM_VECTOR_WITH_OFFSETS);
		tokenStreams.remove(1);

    TokenStream tokenStream = createMock(TokenStream.class);
    expect(filterBuilder.filter(isA(TokenStreamMerger.class), isA(Collection.class))).andReturn(tokenStream);
    replay(filterBuilder);

		Collection<Field> fields = fieldBuilder.createFields(tokenStreams, fieldDescription);
    verify(filterBuilder);
    
		Iterator<Field> fieldIterator = fields.iterator();
		Field field1 = fieldIterator.next();
		assertEquals("field1", field1.name());
		assertTrue(field1.isStoreOffsetWithTermVector());
		assertTrue(field1.isTermVectorStored());
		assertFalse(field1.isStorePositionWithTermVector());
	}
	
	@Test
	public void testCreateFieldTermVectorPositions() throws Exception{
		fieldDescription.setMerge(true);
		fieldDescription.setIndex(FieldBuilder.FIELD_INDEX_YES);
		fieldDescription.setTermVector(FieldBuilder.FIELD_TERM_VECTOR_WITH_POSITIONS);
		tokenStreams.remove(1);

		TokenStream tokenStream = createMock(TokenStream.class);
    expect(filterBuilder.filter(isA(TokenStreamMerger.class), isA(Collection.class))).andReturn(tokenStream);
    replay(filterBuilder);

		Collection<Field> fields = fieldBuilder.createFields(tokenStreams, fieldDescription);
		verify(filterBuilder);
		
		Iterator<Field> fieldIterator = fields.iterator();
		Field field1 = fieldIterator.next();
		assertEquals("field1", field1.name());
		assertFalse(field1.isStoreOffsetWithTermVector());
		assertTrue(field1.isTermVectorStored());
		assertTrue(field1.isStorePositionWithTermVector());
	}

	@Test
	public void testCreateFieldTermVectorOffsetPositions() throws Exception{

		fieldDescription.setMerge(true);
		fieldDescription.setIndex(FieldBuilder.FIELD_INDEX_YES);
		fieldDescription.setTermVector(FieldBuilder.FIELD_TERM_VECTOR_WITH_POSITIONS_OFFSETS);
		tokenStreams.remove(1);

    TokenStream tokenStream = createMock(TokenStream.class);
    expect(filterBuilder.filter(isA(TokenStreamMerger.class), isA(Collection.class))).andReturn(tokenStream);
    replay(filterBuilder);

		Collection<Field> fields = fieldBuilder.createFields(tokenStreams, fieldDescription);
    verify(filterBuilder);
    
		Iterator<Field> fieldIterator = fields.iterator();
		Field field1 = fieldIterator.next();
		assertEquals("field1", field1.name());
		assertTrue(field1.isStoreOffsetWithTermVector());
		assertTrue(field1.isTermVectorStored());
		assertTrue(field1.isStorePositionWithTermVector());
	}

	
	@Test
	public void testCreateFieldIndexStored() throws Exception{
		
		fieldDescription.setIndex(FieldBuilder.FIELD_INDEX_YES);
		fieldDescription.setStored(FieldBuilder.FIELD_STORE_YES);
		tokenStreams.remove(1);

    expect(filterBuilder.filter(isA(TokenStream.class), isA(Collection.class))).andReturn(tokenStream1);
    replay(filterBuilder);
		
		Collection<Field> fields = fieldBuilder.createFields(tokenStreams, fieldDescription);
    verify(filterBuilder);
    
		assertEquals(4, fields.size());
		Iterator<Field> fieldIterator = fields.iterator();
		Field field1 = fieldIterator.next();
		assertEquals("field1", field1.name());
		assertFalse(field1.isIndexed());
		assertTrue(field1.isStored());
		assertEquals("token1", field1.stringValue());

		Field field2 = fieldIterator.next();
		assertEquals("field1", field2.name());
		assertFalse(field2.isIndexed());
		assertTrue(field2.isStored());
		assertEquals("token2", field2.stringValue());

		Field field3 = fieldIterator.next();
		assertEquals("field1", field3.name());
		assertFalse(field3.isIndexed());
		assertTrue(field3.isStored());
		assertEquals("token3", field3.stringValue());

		Field field4 = fieldIterator.next();
		assertEquals("field1", field4.name());
		assertTrue(field4.isIndexed());
		assertFalse(field4.isStored());
	}
	
	@Test
	public void testCreateFieldIndexStoredDelimiter() throws Exception{
		
		fieldDescription.setIndex(FieldBuilder.FIELD_INDEX_YES);
		fieldDescription.setStored(FieldBuilder.FIELD_STORE_YES);
		fieldDescription.setDelimiter(" ");
		tokenStreams.remove(1);
		
    expect(filterBuilder.filter(isA(TokenStream.class), isA(Collection.class))).andReturn(tokenStream1);
    replay(filterBuilder);
    
		Collection<Field> fields = fieldBuilder.createFields(tokenStreams, fieldDescription);
		verify(filterBuilder);
		
		assertEquals(2, fields.size());
		Iterator<Field> fieldIterator = fields.iterator();
		Field field1 = fieldIterator.next();
		assertEquals("field1", field1.name());
		assertFalse(field1.isIndexed());
		assertTrue(field1.isStored());
		assertEquals("token1 token2 token3", field1.stringValue());

		Field field2 = fieldIterator.next();
		assertEquals("field1", field2.name());
		assertTrue(field2.isIndexed());
		assertFalse(field2.isStored());
	}
	
	@Test
	public void testCreateFieldIndexStoredCompress() throws Exception{
		fieldDescription.setIndex(FieldBuilder.FIELD_INDEX_YES);
		fieldDescription.setStored(FieldBuilder.FIELD_STORE_COMPRESS);
		tokenStreams.remove(1);
		
    expect(filterBuilder.filter(isA(TokenStream.class), isA(Collection.class))).andReturn(tokenStream1);
    replay(filterBuilder);

		Collection<Field> fields = fieldBuilder.createFields(tokenStreams, fieldDescription);
    verify(filterBuilder);
    
		assertEquals(4, fields.size());
		Iterator<Field> fieldIterator = fields.iterator();
		Field field1 = fieldIterator.next();
		assertEquals("field1", field1.name());
		assertFalse(field1.isIndexed());
		assertTrue(field1.isStored());
		assertTrue(field1.isCompressed());
		assertEquals("token1", field1.stringValue());

		Field field2 = fieldIterator.next();
		assertEquals("field1", field2.name());
		assertFalse(field2.isIndexed());
		assertTrue(field2.isStored());
		assertTrue(field2.isCompressed());
		assertEquals("token2", field2.stringValue());

		Field field3 = fieldIterator.next();
		assertEquals("field1", field3.name());
		assertFalse(field3.isIndexed());
		assertTrue(field3.isStored());
		assertTrue(field3.isCompressed());
		assertEquals("token3", field3.stringValue());

		Field field4 = fieldIterator.next();
		assertEquals("field1", field4.name());
		assertTrue(field4.isIndexed());
		assertFalse(field4.isStored());
	}
}
