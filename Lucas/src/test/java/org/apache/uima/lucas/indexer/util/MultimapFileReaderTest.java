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
import java.util.List;
import java.util.Map;

import org.apache.uima.lucas.indexer.util.MultimapFileReader;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.classextension.EasyMock.*;
import static org.junit.Assert.*;

public class MultimapFileReaderTest {

	private BufferedReader reader;
	private MultimapFileReader multimapFileReader;
	private static final String LINE_1 = "term1=term111|term11|term1";
	private static final String LINE_2 = "term2=term222|term22|term2";
	
	@Before
	public void setUp(){
		reader = createMock(BufferedReader.class);
		multimapFileReader = new MultimapFileReader(reader);
	}
	
	@Test
	public void testReadMultimap() throws Exception{
		expect(reader.readLine()).andReturn(LINE_1);
		expect(reader.readLine()).andReturn(LINE_2);
		expect(reader.readLine()).andReturn(null);
		replay(reader);
		
		Map<String, List<String>> multimap = multimapFileReader.readMultimap();
		verify(reader);
		
		assertEquals(2, multimap.size());
		List<String> line1Values = multimap.get("term1");
		assertEquals("term111", line1Values.get(0));
		assertEquals("term11", line1Values.get(1));
		assertEquals("term1", line1Values.get(2));

		List<String> line2Values = multimap.get("term2");
		assertEquals("term222", line2Values.get(0));
		assertEquals("term22", line2Values.get(1));
		assertEquals("term2", line2Values.get(2));
	}
	
	@Test
	public void testClose() throws Exception{
		reader.close();
		replay(reader);
		
		multimapFileReader.close();
		verify(reader);
	}
}
