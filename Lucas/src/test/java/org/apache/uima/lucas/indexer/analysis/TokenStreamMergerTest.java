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

import junit.framework.TestCase;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.uima.lucas.indexer.test.util.DummyTokenStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TokenStreamMergerTest extends TestCase {
	private TokenStreamMerger merger;
	@Override
	protected void setUp() throws Exception {
		List<TokenStream> streams = new ArrayList<TokenStream>();
		streams.add(new DummyTokenStream("1111", 1, 4, 0));
		streams.add(new DummyTokenStream("2222", 2, 2, 1));
		streams.add(new DummyTokenStream("3333", 3, 1, 2));
		merger = new TokenStreamMerger(streams);
	}
	
	public void testNext() throws IOException {
		
		
		TermAttribute termAtt = (TermAttribute)merger.getAttribute(TermAttribute.class);
		OffsetAttribute offsetAtt = (OffsetAttribute)merger.getAttribute(OffsetAttribute.class);
		PositionIncrementAttribute posIncAtt = (PositionIncrementAttribute)merger.getAttribute(PositionIncrementAttribute.class);
		
		merger.incrementToken();
		
		assertEquals("1111", termAtt.term());
		assertEquals(0, offsetAtt.startOffset());
		assertEquals(4, offsetAtt.endOffset());
		assertEquals(1, posIncAtt.getPositionIncrement());
		
		merger.incrementToken();		
		assertEquals("1111",  termAtt.term());
		assertEquals(5,  offsetAtt.startOffset());
		assertEquals(9,  offsetAtt.endOffset());
		assertEquals(1,  posIncAtt.getPositionIncrement());

		merger.incrementToken();
		assertEquals("2222",  termAtt.term());
		assertEquals(5,  offsetAtt.startOffset());
		assertEquals(9,  offsetAtt.endOffset());
		assertEquals(0,  posIncAtt.getPositionIncrement());

		merger.incrementToken();
		assertEquals("1111",  termAtt.term());
		assertEquals(10,  offsetAtt.startOffset());
		assertEquals(14,  offsetAtt.endOffset());
		assertEquals(1,  posIncAtt.getPositionIncrement());

		merger.incrementToken();
		assertEquals("3333",  termAtt.term());
		assertEquals(10,  offsetAtt.startOffset());
		assertEquals(14,  offsetAtt.endOffset());
		assertEquals(0,  posIncAtt.getPositionIncrement());

		merger.incrementToken();
		assertEquals("1111",  termAtt.term());
		assertEquals(15, offsetAtt.startOffset());
		assertEquals(19, offsetAtt.endOffset());
		assertEquals(1,  posIncAtt.getPositionIncrement());

		merger.incrementToken();
		assertEquals("2222",  termAtt.term());
		assertEquals(15,  offsetAtt.startOffset());
		assertEquals(19, offsetAtt.endOffset());
		assertEquals(0, posIncAtt.getPositionIncrement());
	}

}
