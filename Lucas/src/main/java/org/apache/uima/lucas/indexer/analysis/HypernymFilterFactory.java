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

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.uima.lucas.indexer.util.MultimapFileReaderFactory;

public class HypernymFilterFactory implements TokenFilterFactory {

	public static final String FILE_PATH_PARAMETER = "filePath";
	private Map<String, Map<String, List<String>>> cachedMappings;
	private MultimapFileReaderFactory multimapFileReaderFactory;
	private static Logger LOGGER = Logger.getLogger(HypernymFilterFactory.class);
	
	public HypernymFilterFactory(MultimapFileReaderFactory multimapFileReaderFactory) {
		this.multimapFileReaderFactory = multimapFileReaderFactory;
		cachedMappings = new HashMap<String, Map<String, List<String>>>();
	}

	public TokenFilter createTokenFilter(TokenStream tokenStream,
			Properties properties) throws IOException {
		
		String filePath = properties.getProperty(FILE_PATH_PARAMETER);
		Map<String, List<String>> hypernyms = getHypernyms(filePath);
		return new HypernymFilter(tokenStream, hypernyms);
	}

	private Map<String, List<String>> getHypernyms(String filePath) throws IOException {
		Map<String, List<String>> hypernyms = cachedMappings.get(filePath);
		if( hypernyms == null )
		{
			hypernyms = multimapFileReaderFactory.createMultimapFileReader(filePath).readMultimap();
			LOGGER.info("file " + filePath + " loaded with " + hypernyms.size());
			cachedMappings.put(filePath, hypernyms);
		}
			
		return hypernyms;
	}

	public void preloadResources(Properties properties) throws IOException {
		String filePath = properties.getProperty(FILE_PATH_PARAMETER);		
		Map<String, List<String>> hypernyms = getHypernyms(filePath);
		
	}

	public Map<String, Map<String, List<String>>> getCachedMappings() {
		return cachedMappings;
	}

}
