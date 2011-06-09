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

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.easymock.classextension.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class PlainFileReaderTest {


	private BufferedReader reader;
	
	@Before
	public void setUp(){
		reader = createMock(BufferedReader.class);
	}
	
	@Test
	public void testReadFile() throws Exception{
		PlainFileReader plainFileReader = new PlainFileReader(reader);
		expect(reader.readLine()).andReturn("na");
		expect(reader.readLine()).andReturn("und");
		expect(reader.readLine()).andReturn("nu");
		expect(reader.readLine()).andReturn(null);

		replay(reader);
		
		String[] lines = plainFileReader.readLines();
		verify(reader);
		
		assertEquals(3, lines.length);
		assertEquals("na", lines[0]);
		assertEquals("und", lines[1]);
		assertEquals("nu", lines[2]);
		
	}
	
	@Test
	public void testClose() throws IOException{
		PlainFileReader plainFileReader = new PlainFileReader(reader);

		reader.close();
		replay(reader);
		plainFileReader.close();
		verify(reader);
	}

}
