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
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.uima.lucas.indexer.test.util.CollectionTokenStream;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import junit.framework.TestCase;

import static org.apache.uima.lucas.indexer.util.TokenFactory.newToken;

/**
 * Testcase for {@link SelectFilter}
 */
public class SelectFilterTest extends TestCase {

	private Set<String> selectedWords;
	private CollectionTokenStream inputTokenStream;

	@Before
	public void setUp() {
		selectedWords = new HashSet<String>();
		selectedWords.add("positive1");
		selectedWords.add("positive2");
		selectedWords.add("positive3");

		Token t1 = newToken("negative1", 0, 9);
		Token t2 = newToken("positive3", 9, 18);
		Token t3 = newToken("positive1", 18, 27);
		Token t4 = newToken("negative2", 27, 36);
		Token t5 = newToken("positive2", 36, 45);
		Token t6 = newToken("negative3", 45, 54);
		Token t7 = newToken("negative4", 54, 63);
		inputTokenStream = new CollectionTokenStream(Lists.newArrayList(t1, t2,
				t3, t4, t5, t6, t7));
	}

	@Test
	public void testWithEnablePositionIncrement() throws IOException {
		inputTokenStream.reset();

		SelectFilter selectFilter = new SelectFilter(true, inputTokenStream,
				selectedWords, true);

		TermAttribute tAtt = (TermAttribute) selectFilter
				.addAttribute(TermAttribute.class);
		PositionIncrementAttribute posAtt = (PositionIncrementAttribute) selectFilter
				.addAttribute(PositionIncrementAttribute.class);
		selectFilter.incrementToken();
		assertEquals("Selected Token 1", "positive3", tAtt.term());
		assertEquals("Position increment one token skipped", 2,
				posAtt.getPositionIncrement());

		selectFilter.incrementToken();
		assertEquals("Selected Token 2", "positive1", tAtt.term());
		assertEquals("Position increment next contigous token", 1,
				posAtt.getPositionIncrement());

		selectFilter.incrementToken();
		assertEquals("Selected Token 3", "positive2", tAtt.term());
		assertEquals("Position increment one token skipped", 2,
				posAtt.getPositionIncrement());
	}

	@Test
	public void testWithoutEnablePositionIncrement() throws IOException {
		inputTokenStream.reset();

		SelectFilter selectFilter = new SelectFilter(false, inputTokenStream,
				selectedWords, true);

		TermAttribute tAtt = (TermAttribute) selectFilter
				.addAttribute(TermAttribute.class);
		PositionIncrementAttribute posAtt = (PositionIncrementAttribute) selectFilter
				.addAttribute(PositionIncrementAttribute.class);
		selectFilter.incrementToken();
		assertEquals("Selected Token 1", "positive3", tAtt.term());
		assertEquals("Position increment without skipping", 1,
				posAtt.getPositionIncrement());

		selectFilter.incrementToken();
		assertEquals("Selected Token 2", "positive1", tAtt.term());
		assertEquals("Position increment without skipping", 1,
				posAtt.getPositionIncrement());

		selectFilter.incrementToken();
		assertEquals("Selected Token 3", "positive2", tAtt.term());
		assertEquals("Position increment without skipping", 1,
				posAtt.getPositionIncrement());
	}
}
