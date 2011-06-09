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

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.uima.lucas.indexer.analysis.UpperCaseFilter;
import org.apache.uima.lucas.indexer.test.util.CollectionTokenStream;
import static org.apache.uima.lucas.indexer.util.TokenFactory.*;

public class UppercaseFilterTest extends TestCase {

	public void testNext() throws Exception{
		List<Token> tokens = new ArrayList<Token>();
		tokens.add(newToken("token1", 0, 6));
		tokens.add(newToken("token2", 7, 13));
		tokens.add(newToken("token3", 14, 20));
		
		TokenStream tokenStream = new CollectionTokenStream(tokens );
		
		UpperCaseFilter upperCaseTokenFilter = new UpperCaseFilter(tokenStream);
		
		Token next = new Token(); 
		upperCaseTokenFilter.next(next);
		
		String nextString = new String(next.termBuffer(), 0, next.termLength());
		assertEquals("TOKEN1", nextString);

		next = upperCaseTokenFilter.next(next);		
		nextString = new String(next.termBuffer(), 0, next.termLength());
		assertEquals("TOKEN2", nextString);

		next = upperCaseTokenFilter.next(next);		
		nextString = new String(next.termBuffer(), 0, next.termLength());
		assertEquals("TOKEN3", nextString);

	}
}
