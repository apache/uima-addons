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

/**
 * For each token, matches a given regular expression <code>regExp</code> and, if found, replaces the matching span with <code>replacement</code>
 */
package org.apache.uima.lucas.indexer.analysis;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;

/**
 * A {@link TokenFilter} filtering by regular expression
 * 
 */
public class RegExpFilter extends TokenFilter {

	private final String replacement;
	private final TermAttribute termAtt;
	private final TermAttribute inputTermAtt;
	private final Matcher m;

	/**
	 * 
	 * @param input input <code>TokenStream</code>
	 * @param regExp the regular expression which is matched
	 * @param replacement the replacement string that is inserted for the span matched by <code>regExp</code>
	 */
	protected RegExpFilter(TokenStream input, String regExp, String replacement) {
		super(input);
		this.replacement = replacement;
		m = Pattern.compile(regExp).matcher("dummy");
		termAtt = (TermAttribute) addAttribute(TermAttribute.class);
		inputTermAtt = (TermAttribute) input.addAttribute(TermAttribute.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.lucene.analysis.TokenStream#incrementToken()
	 */
	@Override
	public boolean incrementToken() throws IOException {
		if (input.incrementToken()) {
			String term = inputTermAtt.term();
			m.reset(term);
			termAtt.setTermBuffer(m.replaceAll(replacement));
			return true;
		}
		return false;
	}

	/**
	 * @return the replacement
	 */
	public String getReplacement() {
		return replacement;
	}

	/**
	 * @return the regexp
	 */
	public String getRegExp() {
		return m.pattern().pattern();
	}

}
