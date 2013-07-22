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
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.uima.lucas.indexer.test.util.DummyTokenStream;
import org.junit.Test;

import junit.framework.TestCase;

import static org.apache.uima.lucas.indexer.analysis.StopwordFilterFactory.FILE_PATH_PARAMETER;

/**
 * Testcase for {@link SelectFilterFactory}
 *
 */
public class SelectFilterFactoryTest extends TestCase {

	@Test
	public void test() throws IOException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		
		SelectFilterFactory selectFilterFactory = new SelectFilterFactory();
		Properties properties = new Properties();
		properties.put(FILE_PATH_PARAMETER, "src/test/resources/selectedwords.txt");
		TokenFilter selectFilter = selectFilterFactory.createTokenFilter(new DummyTokenStream("dummy", 0, 0, 0), properties);
		assertNotNull(selectFilter);
		
		Field includeWordsField = selectFilter.getClass().getDeclaredField("includeWords");
		includeWordsField.setAccessible(true);
		CharArraySet set = (CharArraySet) includeWordsField.get(selectFilter);
		assertTrue("'positive1' is not in set", set.contains("positive1"));
		assertTrue("'positive2' is not in set", set.contains("positive2"));
		assertTrue("'positive3' is not in set", set.contains("positive3"));
	}
	
}

