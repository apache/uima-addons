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

package org.apache.uima.lucas.indexer.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.lucene.analysis.Token;
import org.apache.uima.lucas.indexer.test.util.CollectionTokenStream;
import org.apache.uima.lucas.indexer.util.TokenStreamStringConcatenator;
import org.junit.Test;

public class TokenStreamStringConcatenatorTest {

	@Test
	public void testTokenStreamToStringWithDelimiter() throws Exception{
		TokenStreamStringConcatenator tokenStreamStringConcatenator = new TokenStreamStringConcatenator();
		Collection<Token> tokens1 = new ArrayList<Token>();
		tokens1.add(new Token("token1".toCharArray(),0,6,0,6));
		tokens1.add(new Token("token2".toCharArray(),0,6,7,13));
		tokens1.add(new Token("token3".toCharArray(),0,6,14,20));

		CollectionTokenStream tokenStream1 = new CollectionTokenStream(tokens1);
		String concatenatedString = tokenStreamStringConcatenator.tokenStreamToStringWithDelimiter(tokenStream1, " ");
		assertEquals("token1 token2 token3", concatenatedString);
		
	}
	
}
