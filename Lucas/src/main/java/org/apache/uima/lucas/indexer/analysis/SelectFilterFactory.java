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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.uima.lucas.indexer.util.PlainFileReaderFactory;

import static org.apache.uima.lucas.indexer.analysis.StopwordFilterFactory.FILE_PATH_PARAMETER;
import static org.apache.uima.lucas.indexer.analysis.StopwordFilterFactory.IGRNORE_CASE_PARAMETER;

/**
 * {@link TokenFilterFactory} for {@link SelectFilter}
 */
public class SelectFilterFactory implements TokenFilterFactory {

	private static Logger LOGGER = Logger.getLogger(SelectFilterFactory.class);

	public static final String COVER_SUBSET_NAME = "coverSubsetName";
	
	private static final String TRUE = "true";
	private static final String ENABLE_POSITION_INCREMENTS = "enablePositionIncrements";

	private PlainFileReaderFactory plainFileReaderFactory;
	private Map<String, Set<?>> cachedIncludeWords;

	public SelectFilterFactory() {
		this(new PlainFileReaderFactory());
	}

	public SelectFilterFactory(PlainFileReaderFactory plainFileReaderFactory) {
		cachedIncludeWords = new HashMap<String, Set<?>>();
		this.plainFileReaderFactory = plainFileReaderFactory;
	}

	public void preloadResources(Properties properties) throws IOException {
		String filePath = properties.getProperty(FILE_PATH_PARAMETER);
		String ignoreCaseStr = properties.getProperty(IGRNORE_CASE_PARAMETER);
		boolean ignoreCase = ignoreCaseStr != null && ignoreCaseStr
                .equals(TRUE);
		Set stopwords = getIncludeWords(filePath, ignoreCase);

	}

	public TokenFilter createTokenFilter(TokenStream tokenStream,
			Properties properties, List<String> includeWordsList) {
		String ignoreCaseStr = properties.getProperty(IGRNORE_CASE_PARAMETER);
		String enablePosIncStr = properties
				.getProperty(ENABLE_POSITION_INCREMENTS);
		boolean ignoreCase = ignoreCaseStr != null && ignoreCaseStr
                .equals(TRUE);
		boolean enablePosInc = enablePosIncStr != null && enablePosIncStr.equals(TRUE);

		Set includeWords = getIncludeWordsForCoverSubset(properties.getProperty(COVER_SUBSET_NAME), includeWordsList, ignoreCase);

		return new SelectFilter(enablePosInc, tokenStream, includeWords,
				ignoreCase);
	}


	public TokenFilter createTokenFilter(TokenStream tokenStream,
			Properties properties) throws IOException {
		String filePath = properties.getProperty(FILE_PATH_PARAMETER);
		String ignoreCaseStr = properties.getProperty(IGRNORE_CASE_PARAMETER);
		String enablePosIncStr = properties
				.getProperty(ENABLE_POSITION_INCREMENTS);
		boolean ignoreCase = ignoreCaseStr != null && ignoreCaseStr
                .equals(TRUE);
		boolean enablePosInc = enablePosIncStr != null && enablePosIncStr.equals(TRUE);

		Set includeWords = getIncludeWords(filePath, ignoreCase);

		return new SelectFilter(enablePosInc, tokenStream, includeWords,
				ignoreCase);
	}

	private Set getIncludeWords(String filePath, boolean ignoreCase)
			throws IOException {
		Set includeWords = cachedIncludeWords.get(filePath);
		if (includeWords == null) {
			String[] includeWordLines = plainFileReaderFactory
					.createPlainFileReader(filePath).readLines();
			includeWords = StopFilter.makeStopSet(includeWordLines, ignoreCase);
			LOGGER.info("file " + filePath + " loaded with "
					+ includeWords.size() + " stopwords");
			cachedIncludeWords.put(filePath, includeWords);
		}

		return includeWords;
	}
	
	/**
	 * @param coverSubsetName
	 * @param ignoreCase
	 * @param includeWordsList
	 * @return a <code>Set</code>
	 */
	private Set<?> getIncludeWordsForCoverSubset(String coverSubsetName, List<String> includeWordsList, boolean ignoreCase) {
		Set<?> includeWords = cachedIncludeWords.get(coverSubsetName);
		if (includeWords == null) {
			includeWords = StopFilter.makeStopSet(includeWordsList, ignoreCase);
			cachedIncludeWords.put(coverSubsetName, includeWords);
		}

		return includeWords;
	}
}
