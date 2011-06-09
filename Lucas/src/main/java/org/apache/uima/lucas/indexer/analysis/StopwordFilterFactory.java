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
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.uima.lucas.indexer.util.PlainFileReaderFactory;

public class StopwordFilterFactory implements TokenFilterFactory {

  private static final String TRUE = "true";
  private static final String FALSE = "false";
  public static final String FILE_PATH_PARAMETER = "filePath";
  public static final String IGRNORE_CASE_PARAMETER = "ignoreCase";
  private PlainFileReaderFactory plainFileReaderFactory;
  private Map<String, String[]> cachedStopwords;
  private static Logger LOGGER = Logger.getLogger(StopwordFilterFactory.class);
  
  public StopwordFilterFactory(PlainFileReaderFactory plainFileReaderFactory) {
	  this.plainFileReaderFactory = plainFileReaderFactory;
	  cachedStopwords = new HashMap<String, String[]>();
	}

	public TokenFilter createTokenFilter(TokenStream tokenStream,
			Properties properties) throws IOException {
	  
	  String filePath = properties.getProperty(FILE_PATH_PARAMETER);
	  String ignoreCase = properties.getProperty(IGRNORE_CASE_PARAMETER);
	  ignoreCase = ignoreCase == null ? FALSE : ignoreCase;
	  
	  String[] stopwords = getStopwords(filePath );
    
		return new StopFilter(tokenStream, stopwords, ignoreCase.equals(TRUE));
	}

	private String[] getStopwords(String filePath) throws IOException {
	  String[] stopwords = cachedStopwords.get(filePath);
	  if( stopwords == null ){
	    stopwords = plainFileReaderFactory.createPlainFileReader(filePath).readLines();
	    LOGGER.info("file " + filePath + " loaded with " + stopwords.length + " stopwords");
	    cachedStopwords.put(filePath, stopwords);
	  }
	    
    return stopwords;
  }

	public void preloadResources(Properties properties) throws IOException {
    String filePath = properties.getProperty(FILE_PATH_PARAMETER);
    String[] stopwords = getStopwords(filePath );
	}

}
