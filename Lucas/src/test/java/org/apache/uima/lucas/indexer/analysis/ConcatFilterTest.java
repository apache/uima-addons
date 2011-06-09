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

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConcatFilterTest {

	private ConcatFilter concatFilter;
	private TokenStream tokenStream;
	
	@Before
	public void setUp() throws IOException{
		tokenStream = new TestTokenStream();
		concatFilter = new ConcatFilter(tokenStream, "|");
	}
		
	
	@Test
	public void testNext() throws IOException{
		assertTrue(concatFilter.incrementToken());
		TermAttribute termAtt = (TermAttribute)concatFilter.getAttribute(TermAttribute.class);
		OffsetAttribute offsetAtt = (OffsetAttribute)concatFilter.getAttribute(OffsetAttribute.class);
		
		assertEquals(0, offsetAtt.startOffset());
		assertEquals(17, offsetAtt.endOffset());
		assertEquals("token1|token2|token3", termAtt.term());
	}

	private class TestTokenStream extends TokenStream {
		
		private TermAttribute termAtt;
		private OffsetAttribute offsetAtt;
		private int offset = 0;

		private String[] tokens = {"token1", "token2", "token3"};
		
		private int tokenIndex = 0;
		
		public TestTokenStream() {
			termAtt = (TermAttribute) addAttribute(TermAttribute.class);
			offsetAtt = (OffsetAttribute) addAttribute(OffsetAttribute.class);
		}
		
		@Override
		public boolean incrementToken() throws IOException {
			if (tokenIndex < tokens.length) {
				String currentToken = tokens[tokenIndex++];
				termAtt.setTermBuffer(currentToken);
				offsetAtt.setOffset(offset, offset + currentToken.length() - 1);
				offset += currentToken.length();
				return true;
			}
			return false;
		}
		
		
		
	}
}
