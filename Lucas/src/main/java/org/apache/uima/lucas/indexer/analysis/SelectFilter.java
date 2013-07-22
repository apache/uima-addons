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
import java.util.Set;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;

/**
 * The opposite to a stop word filter: This filter takes a set of strings. Only
 * incoming tokens which are contained in this set are returned by this filter.
 * 
 * @author faessler
 */
public class SelectFilter extends TokenFilter {

	private TermAttribute termAtt;
	private PositionIncrementAttribute posIncrAtt;
	private CharArraySet includeWords;
	boolean enablePositionIncrements;
	/**
	 * The select filter can sometimes - for example in conjunction with the
	 * hypernyms or synonyms filter - cause the first token of the stream to
	 * have a position increment of 0. This must not happen, which is why in
	 * such a case we just set the position increment to 1. This is an indicator
	 * variable to show whether we are at the first token or later.
	 */
	private boolean firstToken;

	/**
	 * Construct a token stream filtering the given input. If
	 * <code>includeWords</code> is an instance of {@link CharArraySet} (true if
	 * <code>makeStopSet()</code> was used to construct the set) it will be
	 * directly used and <code>ignoreCase</code> will be ignored since
	 * <code>CharArraySet</code> directly controls case sensitivity.
	 * <p/>
	 * If <code>includeWords</code> is not an instance of {@link CharArraySet},
	 * a new CharArraySet will be constructed and <code>ignoreCase</code> will
	 * be used to specify the case sensitivity of that set.
	 * 
	 * @param enablePositionIncrements
	 *            true if token positions should record the removed non-include
	 *            words
	 * @param input
	 *            Input TokenStream
	 * @param includeWords
	 *            The set of words to include.
	 * @param ignoreCase
	 *            -Ignore case when filtering.
	 */
	public SelectFilter(boolean enablePositionIncrements, TokenStream input,
			Set<?> includeWords, boolean ignoreCase) {
		super(input);
		if (includeWords instanceof CharArraySet) {
			this.includeWords = (CharArraySet) includeWords;
		} else {
			this.includeWords = new CharArraySet(includeWords.size(),
					ignoreCase);
			this.includeWords.addAll(includeWords);
		}
		this.enablePositionIncrements = enablePositionIncrements;
		termAtt = (TermAttribute) addAttribute(TermAttribute.class);
		posIncrAtt = (PositionIncrementAttribute) addAttribute(PositionIncrementAttribute.class);
		firstToken = true;
	}

	/**
	 * Returns the next input Token whose term() is an included word.
	 */
	public final boolean incrementToken() throws IOException {
		// return the first word found to be included
		int skippedPositions = 0;
		while (input.incrementToken()) {
			if (includeWords.contains(termAtt.termBuffer(), 0,
					termAtt.termLength())) {
				// The first token must not have a position increment of 0.
				if (posIncrAtt.getPositionIncrement() == 0 && firstToken)
					posIncrAtt.setPositionIncrement(1);
				if (enablePositionIncrements) {
					posIncrAtt.setPositionIncrement(posIncrAtt
							.getPositionIncrement() + skippedPositions);
				}
				firstToken = false;
				return true;
			}
			skippedPositions += posIncrAtt.getPositionIncrement();
		}
		// reached EOS -- return null
		return false;
	}

}
