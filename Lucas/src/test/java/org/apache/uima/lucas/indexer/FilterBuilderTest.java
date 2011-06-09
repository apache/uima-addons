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

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.uima.lucas.indexer.analysis.TokenFilterFactory;
import org.apache.uima.lucas.indexer.mapping.FilterDescription;
import org.apache.uima.lucas.indexer.test.util.CollectionTokenStream;
import org.junit.Before;
import org.junit.Test;


public class FilterBuilderTest {

	private static final String LOWER_CASE_FACTORY_ID = "lowerCaseFactory";
  private FilterBuilder filterBuilder;
	private TokenStream tokenStream;
  
	
	@Before
	public void setUp(){
		Collection<Token> tokens = new ArrayList<Token>();
		tokens.add(new Token("token1".toCharArray(),0,6,0,6));
		tokens.add(new Token("token2".toCharArray(),0,6,7,13));
		tokens.add(new Token("token3".toCharArray(),0,6,14,20));

		tokenStream = new CollectionTokenStream(tokens);
		
		filterBuilder = new FilterBuilder(new HashMap<String, TokenFilterFactory>());
	}
	
	@Test
	public void testFilterWithoutFactory() throws Exception{
	  Collection<FilterDescription> filterDescriptions = new ArrayList<FilterDescription>();
	  filterDescriptions.add(new FilterDescription(LowerCaseFilter.class.getCanonicalName(), null, null, false, null));
	  
	  TokenStream filteredTokenStream = filterBuilder.filter(tokenStream, filterDescriptions);
	  assertEquals(LowerCaseFilter.class, filteredTokenStream.getClass());
	}

  @Test
  public void testFilterWithFactory() throws Exception{
    Collection<FilterDescription> filterDescriptions = new ArrayList<FilterDescription>();
    filterDescriptions.add(new FilterDescription(null, DummyTokenFilterFactory.class.getCanonicalName(), null, false, null));
    
    TokenStream filteredTokenStream = filterBuilder.filter(tokenStream, filterDescriptions);
    assertEquals(LowerCaseFilter.class, filteredTokenStream.getClass());
  }

  @Test
  public void testFilterWithNamedAndCachedFactory() throws Exception{
    Collection<FilterDescription> filterDescriptions = new ArrayList<FilterDescription>();
    filterDescriptions.add(new FilterDescription(null, DummyTokenFilterFactory.class.getCanonicalName(), LOWER_CASE_FACTORY_ID, true, null));
    
    TokenStream filteredTokenStream = filterBuilder.filter(tokenStream, filterDescriptions);
    assertEquals(LowerCaseFilter.class, filteredTokenStream.getClass());
    Map<String, TokenFilterFactory> cachedFactories = filterBuilder.getCachedFactories();
    assertEquals(LOWER_CASE_FACTORY_ID, cachedFactories.keySet().iterator().next());
    assertTrue(cachedFactories.get(LOWER_CASE_FACTORY_ID) instanceof DummyTokenFilterFactory);

    // test caching
    filterDescriptions = new ArrayList<FilterDescription>();
    filterDescriptions.add(new FilterDescription(null, null, LOWER_CASE_FACTORY_ID, true, null));
    TokenFilterFactory factory = createMock(TokenFilterFactory.class);
    expect(factory.createTokenFilter(tokenStream, null)).andReturn(null);
    replay(factory);
    
    cachedFactories.put(LOWER_CASE_FACTORY_ID, factory);
    filterBuilder.filter(tokenStream, filterDescriptions);
    verify(factory);
  }
	
  @Test
  public void testFilterWithPredefinedFactory() throws Exception{
    Map<String, TokenFilterFactory> predifinedFactories = new HashMap<String, TokenFilterFactory>();
    
    // test caching
    Collection<FilterDescription> filterDescriptions = new ArrayList<FilterDescription>();
    filterDescriptions.add(new FilterDescription(null, null, LOWER_CASE_FACTORY_ID, true, null));
    TokenFilterFactory factory = createMock(TokenFilterFactory.class);
    expect(factory.createTokenFilter(tokenStream, null)).andReturn(null);
    replay(factory);
    
    predifinedFactories.put(LOWER_CASE_FACTORY_ID, factory);
    filterBuilder = new FilterBuilder(predifinedFactories);
    filterBuilder.filter(tokenStream, filterDescriptions);
    verify(factory);
  }
}
