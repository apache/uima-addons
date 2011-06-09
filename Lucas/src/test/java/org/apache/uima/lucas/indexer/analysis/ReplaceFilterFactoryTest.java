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

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.uima.lucas.indexer.test.util.DummyTokenStream;
import org.apache.uima.lucas.indexer.util.MapFileReader;
import org.apache.uima.lucas.indexer.util.MapFileReaderFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.assertEquals;

public class ReplaceFilterFactoryTest {

  private static final String TEST_FILE_1 = "src/test/resources/ReplaceFilterFactoryTest1.txt";
  private ReplaceFilterFactory replaceFilterFactory;
  private MapFileReaderFactory mapFileReaderFactory;
  private MapFileReader mapFileReader;
  private TokenStream tokenStream;
  private Map<String, String> mapping;
  
  @Before
  public void setUp(){
	mapFileReaderFactory = createMock(MapFileReaderFactory.class);
	mapFileReader= createMock(MapFileReader.class);
    replaceFilterFactory = new ReplaceFilterFactory(mapFileReaderFactory);
    tokenStream = new DummyTokenStream("dummy", 1, 1, 0);
    mapping = new HashMap<String, String>();
  }
  
  @Test
  public void testCreateTokenFilter() throws Exception{
    Properties properties = new Properties();
    properties.setProperty(ReplaceFilterFactory.FILE_PATH_PARAMETER, TEST_FILE_1);
    
    expect(mapFileReaderFactory.createMapFileReader(TEST_FILE_1)).andReturn(mapFileReader);
    expect(mapFileReader.readMap()).andReturn(mapping);
    replay(mapFileReaderFactory);
    replay(mapFileReader);
    
    TokenFilter tokenFilter = replaceFilterFactory.createTokenFilter(tokenStream, properties);
    assertEquals(ReplaceFilter.class, tokenFilter.getClass());
    ReplaceFilter replaceFilter = (ReplaceFilter) tokenFilter;
    assertEquals(mapping, replaceFilter.getMapping());
    verify(mapFileReaderFactory);
    verify(mapFileReader);
    
    reset(mapFileReaderFactory);
    reset(mapFileReader);
    replay(mapFileReaderFactory);
    replay(mapFileReader);
    
    // test caching (no calls of buffered reader)
    tokenFilter = replaceFilterFactory.createTokenFilter(tokenStream, properties);
    verify(mapFileReaderFactory);
    verify(mapFileReader);

    replaceFilter = (ReplaceFilter) tokenFilter;
    assertEquals(mapping, replaceFilter.getMapping());    
  }
  
  @Test
  public void testPreloadResources() throws Exception{
	    Properties properties = new Properties();
	    properties.setProperty(ReplaceFilterFactory.FILE_PATH_PARAMETER, TEST_FILE_1);
	    
	    expect(mapFileReaderFactory.createMapFileReader(TEST_FILE_1)).andReturn(mapFileReader);
	    expect(mapFileReader.readMap()).andReturn(mapping);
	    replay(mapFileReaderFactory);
	    replay(mapFileReader);
	    
	    replaceFilterFactory.preloadResources(properties);
	    Map<String, String> cachedMapping = replaceFilterFactory.getCachedMappings().get(TEST_FILE_1);
	    assertEquals(mapping, cachedMapping);
	    verify(mapFileReaderFactory);
	    verify(mapFileReader);
	    
	    reset(mapFileReaderFactory);
	    reset(mapFileReader);
	    replay(mapFileReaderFactory);
	    replay(mapFileReader);
	    
	    // test caching (no calls of buffered reader)
	    replaceFilterFactory.preloadResources(properties);
	    cachedMapping = replaceFilterFactory.getCachedMappings().get(TEST_FILE_1);
	    assertEquals(mapping, cachedMapping);
	    verify(mapFileReaderFactory);
	    verify(mapFileReader);
  }
  
}
