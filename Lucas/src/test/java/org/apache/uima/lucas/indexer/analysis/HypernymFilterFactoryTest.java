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

import com.google.common.collect.Lists;
import org.apache.lucene.analysis.TokenStream;
import org.apache.uima.lucas.indexer.test.util.DummyTokenStream;
import org.apache.uima.lucas.indexer.util.MultimapFileReader;
import org.apache.uima.lucas.indexer.util.MultimapFileReaderFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

public class HypernymFilterFactoryTest {

	private static final String FILE_ENTRY = "token1=id1,id2,id3";
	private static final String HYPERNYM_FILE = "src/test/resources/hypernyms.txt";
	private HypernymFilterFactory hypernymFilterFactory;
	private TokenStream tokenStream;
	private MultimapFileReaderFactory multimapFileReaderFactory;
	private MultimapFileReader multimapFileReader;
	private Map<String, List<String>> hypernyms;
	
	@Before
	public void setUp(){
		tokenStream = new DummyTokenStream("dummy", 1, 1, 0);
		hypernyms = new HashMap<String, List<String>>();
		hypernyms.put("token1", Lists.newArrayList("id1", "id2", "id3"));
		
		multimapFileReader = createMock(MultimapFileReader.class);
		multimapFileReaderFactory = createMock(MultimapFileReaderFactory.class);
		hypernymFilterFactory = new HypernymFilterFactory(multimapFileReaderFactory);
	}
	
	@Test
	public void testCreateTokenFilter() throws Exception{
		Properties properties = new Properties();
		properties.setProperty(HypernymFilterFactory.FILE_PATH_PARAMETER, HYPERNYM_FILE);
		
		expect(multimapFileReaderFactory.createMultimapFileReader(HYPERNYM_FILE)).andReturn(multimapFileReader);
		expect(multimapFileReader.readMultimap()).andReturn(hypernyms);
		replay(multimapFileReaderFactory);
		replay(multimapFileReader);
		
		HypernymFilter hypernymFilter = (HypernymFilter) hypernymFilterFactory.createTokenFilter(tokenStream, properties);
		Map<String, Map<String, List<String>>> mappings = hypernymFilterFactory.getCachedMappings();
		verify(multimapFileReaderFactory);
		verify(multimapFileReader);
		
		Map<String, List<String>> mapping = mappings.get(HYPERNYM_FILE);
		assertEquals(hypernyms, mapping);
		assertEquals(hypernyms, hypernymFilter.getHypernyms());
		
		// test caching
		reset(multimapFileReaderFactory);
		reset(multimapFileReader);
		replay(multimapFileReaderFactory);
		replay(multimapFileReader);
		
		hypernymFilter = (HypernymFilter) hypernymFilterFactory.createTokenFilter(tokenStream, properties);
		verify(multimapFileReaderFactory);
		verify(multimapFileReader);
		
		assertEquals(hypernyms, hypernymFilter.getHypernyms());
	}
	
	public void testPreloadResources() throws Exception{
		Properties properties = new Properties();
		properties.setProperty(HypernymFilterFactory.FILE_PATH_PARAMETER, HYPERNYM_FILE);
		
		expect(multimapFileReaderFactory.createMultimapFileReader(FILE_ENTRY)).andReturn(multimapFileReader);
		expect(multimapFileReader.readMultimap()).andReturn(hypernyms);
		hypernymFilterFactory.preloadResources(properties);
		
		Map<String, Map<String, List<String>>> mappings = hypernymFilterFactory.getCachedMappings();
		verify(multimapFileReaderFactory);
		verify(multimapFileReader);
		
		Map<String, List<String>> mapping = mappings.get(HYPERNYM_FILE);
		assertEquals(hypernyms, mapping);
		
		// test caching
		reset(multimapFileReaderFactory);
		reset(multimapFileReader);
		replay(multimapFileReaderFactory);
		replay(multimapFileReader);
		
		hypernymFilterFactory.preloadResources(properties);
		mappings = hypernymFilterFactory.getCachedMappings();
		
		verify(multimapFileReaderFactory);
		verify(multimapFileReader);
		
		mapping = mappings.get(HYPERNYM_FILE);
		assertEquals(hypernyms, mapping);
	}
}
