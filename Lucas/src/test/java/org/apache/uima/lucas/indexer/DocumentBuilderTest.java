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

import static org.easymock.classextension.EasyMock.createMock;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.uima.cas.CASException;
import org.apache.uima.lucas.indexer.DocumentBuilder;
import org.junit.Before;
import org.junit.Test;

public class DocumentBuilderTest {

	private Field field1;
	private Field field2;
	private DocumentBuilder documentBuilder;
	private Collection<Field> fields;

	@Before
	public void setUp(){
		documentBuilder = new DocumentBuilder();
		
		field1 = new Field("field1", createMock(TokenStream.class));
		field2 = new Field("field2", createMock(TokenStream.class));
		fields = new ArrayList<Field>();		 
		fields.add(field1);
		fields.add(field2);
	}
	
	@Test
	public void testCreateDocument() throws CASException, IOException{
		Document document = documentBuilder.createDocument(fields);
		
		assertEquals(field1, document.getField("field1"));
		assertEquals(field2, document.getField("field2"));
	}
	
}
