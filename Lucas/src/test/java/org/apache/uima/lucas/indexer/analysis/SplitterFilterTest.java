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
import java.util.Collection;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.uima.lucas.indexer.analysis.SplitterFilter;
import org.apache.uima.lucas.indexer.test.util.CollectionTokenStream;
import org.junit.Test;
import static org.apache.uima.lucas.indexer.util.TokenFactory.*;

public class SplitterFilterTest {

	@Test
	public void testNext() throws Exception{
		Collection<Token> tokens = new ArrayList<Token>();
		tokens.add(newToken("token1 token2 token3", 0, 6));
		tokens.add(newToken("token4 token5 token6", 7, 13));
		
		TokenStream tokenStream = new CollectionTokenStream(tokens);
		TokenFilter filter = new SplitterFilter(tokenStream, " ");
		
		Token nextToken = new Token(); 
		filter.next(nextToken);
		assertNotNull(nextToken);
		assertEquals("token1", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());
		
		nextToken = filter.next(nextToken);
		assertNotNull(nextToken);
		assertEquals("token2", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());
		
		nextToken = filter.next(nextToken);
		assertNotNull(nextToken);
		assertEquals("token3", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(0, nextToken.startOffset());
		assertEquals(6, nextToken.endOffset());

		nextToken = filter.next(nextToken);
		assertNotNull(nextToken);
		assertEquals("token4", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());

		nextToken = filter.next(nextToken);
		assertNotNull(nextToken);
		assertEquals("token5", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());

		nextToken = filter.next(nextToken);
		assertNotNull(nextToken);
		assertEquals("token6", new String(nextToken.termBuffer(), 0, nextToken.termLength()));
		assertEquals(7, nextToken.startOffset());
		assertEquals(13, nextToken.endOffset());
	}
}
