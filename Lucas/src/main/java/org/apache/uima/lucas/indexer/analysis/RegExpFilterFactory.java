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
import java.util.Properties;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;

/**
 * {@link TokenFilterFactory} for {@link RegExpFilter}
 *
 */
public class RegExpFilterFactory implements TokenFilterFactory {

	public static final String REGEXP = "regexp";
	public static final String REPLACEMENT = "replacement";
	
	/* (non-Javadoc)
	 * @see org.apache.uima.lucas.indexer.analysis.TokenFilterFactory#preloadResources(java.util.Properties)
	 */
	public void preloadResources(Properties properties) throws IOException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.apache.uima.lucas.indexer.analysis.TokenFilterFactory#createTokenFilter(org.apache.lucene.analysis.TokenStream, java.util.Properties)
	 */
	public TokenFilter createTokenFilter(TokenStream tokenStream,
			Properties properties) throws IOException {
		String regexp = (String) properties.get(REGEXP);
		String replacement = (String) properties.get(REPLACEMENT);
		if (regexp == null || replacement == null)
			throw new IllegalArgumentException("Attributes " + REGEXP + " and " + REPLACEMENT + " must be provided for RegExpFilter.");
		return new RegExpFilter(tokenStream, regexp, replacement);
	}

}

