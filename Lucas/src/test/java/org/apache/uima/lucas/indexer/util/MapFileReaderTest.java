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

import java.io.BufferedReader;
import java.util.Map;

import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.*;

import org.apache.uima.lucas.indexer.util.MapFileReader;
import org.junit.Before;
import org.junit.Test;

public class MapFileReaderTest {

	private BufferedReader reader;
	private MapFileReader mapFileReader;
	private static final String LINE_1 = "term1=id1";
	private static final String LINE_2 = "term2=id2";
	private static final String LINE_3 = "term3=id3";
	
	@Before
	public void setUp(){
		reader = createMock(BufferedReader.class);
		mapFileReader = new MapFileReader(reader);
	}
	
	@Test
	public void testReadMap() throws Exception{
		expect(reader.readLine()).andReturn(LINE_1);
		expect(reader.readLine()).andReturn(LINE_2);
		expect(reader.readLine()).andReturn(LINE_3);
		expect(reader.readLine()).andReturn(null);
		replay(reader);
		
		Map<String, String> map = mapFileReader.readMap();
		verify(reader);
		
		assertEquals("id1", map.get("term1"));
		assertEquals("id2", map.get("term2"));
		assertEquals("id3", map.get("term3"));		
	}
	
	@Test
	public void testClose() throws Exception{
		reader.close();
		replay(reader);
		
		mapFileReader.close();
		verify(reader);
	}
	
	
	
}
