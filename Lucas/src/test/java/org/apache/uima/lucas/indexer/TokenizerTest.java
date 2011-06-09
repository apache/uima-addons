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

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.WhitespaceTokenizer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.uima.lucas.indexer.Tokenizer;
import org.apache.uima.lucas.indexer.mapping.AnnotationDescription;
import org.apache.uima.lucas.indexer.test.util.CollectionTokenStream;
import org.junit.Before;
import org.junit.Test;


public class TokenizerTest {
	private Tokenizer tokenizer;
	private AnnotationDescription annotationDescription;
	private TokenStream tokenStream; 
	
	@Before
	public void setUp(){
		tokenizer = new Tokenizer();
		annotationDescription = new AnnotationDescription(null);
		Collection<Token> tokens = new ArrayList<Token>();
		tokens.add(new Token("token1".toCharArray(),0,6,0,6));
		tokens.add(new Token("token2".toCharArray(),0,6,7,13));
		tokens.add(new Token("token3".toCharArray(),0,6,14,20));

		tokenStream = new CollectionTokenStream(tokens);
	}
	
	@Test
	public void testTokenizeWhiteSpace() throws Exception{
		annotationDescription.setTokenizer(Tokenizer.TOKENIZER_WHITESPACE);
		assertTrue(tokenizer.needsTokenization(annotationDescription));
		
		TokenStream reTokenizedTokenStream = tokenizer.tokenize(tokenStream, annotationDescription);
		assertTrue(reTokenizedTokenStream instanceof WhitespaceTokenizer );
	}
	
	@Test
	public void testTokenizeStandard() throws Exception{
		annotationDescription.setTokenizer(Tokenizer.TOKENIZER_STANDARD);
		assertTrue(tokenizer.needsTokenization(annotationDescription));
		
		TokenStream reTokenizedTokenStream = tokenizer.tokenize(tokenStream, annotationDescription);
		assertTrue(reTokenizedTokenStream instanceof StandardTokenizer );
	}

}
