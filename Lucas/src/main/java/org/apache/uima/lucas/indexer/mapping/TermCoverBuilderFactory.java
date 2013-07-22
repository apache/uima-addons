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
 * 
 */
package org.apache.uima.lucas.indexer.mapping;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.TokenStream;
import org.apache.uima.lucas.indexer.analysis.SelectFilterFactory;

/**
 * <p>
 * This factory essentially serves as a caching solution for the files which
 * define term set covers. Each cover definition file is read once and its
 * contents are stored in a map. The name of each set of the cover is mapped to
 * the list of terms included in this set.<br/>
 * The file format is<br/>
 * &lt;term&gt;=&lt;set1&gt;|&lt;set2&gt;|...|&lt;setN&gt;<br/>
 * For example, the file with the following contents:
 * <p>
 * <samp>
 * light bulb=electronics<br/>
 * electric shaver=electronics|sanitaryArticles<br/>
 * smartphone=electronics|computers<br/>
 * </samp>
 * </p>
 * will result in three cover set names <tt>electronics</tt>,
 * <tt>sanitaryArticles</tt> and <tt>computers</tt>. These will be mapped to term
 * lists as such that <tt>electronics</tt> contains <tt>light bulb</tt>,
 * <tt>electronic shaver</tt> and <tt>smartphone</tt>. <tt>sanitaryArticles</tt>
 * will only contain <tt>electric shaver</tt> and <tt>computers</tt> will
 * include <tt>smartphone</tt>. </p>
 * <p>
 * Each of such files is modeled as one instance of {@link TermCoverBuilder}
 * which then distributes terms (incoming to the field defined with a cover set)
 * onto the cover fields according to the cover definition.
 * 
 * 
 *
 */
public class TermCoverBuilderFactory {

	private final Map<String, Map<String, List<String>>> cachedVocabularyPartitions;
	private final SelectFilterFactory selectFilterFactory;

	public TermCoverBuilderFactory() {
		cachedVocabularyPartitions = new HashMap<String, Map<String, List<String>>>();
		selectFilterFactory = new SelectFilterFactory();
	}

	public TermCoverBuilder createTermCoverBuilder(TokenStream tokenStream,
			TermCoverDescription termCoverDescription) {
		String fileName = termCoverDescription.getCoverDefinitionFile();
		Map<String, List<String>> vocabularyCover = cachedVocabularyPartitions
				.get(fileName);
		if (vocabularyCover == null) {
			vocabularyCover = new HashMap<String, List<String>>();

			BufferedReader br = null;
			try {
				FileReader fr = new FileReader(fileName);
				br = new BufferedReader(fr);
				String line;
				while ((line = br.readLine()) != null) {
					String[] keyValue = line.split("=");
					String term = keyValue[0];
					String[] subsetNames = keyValue[1].split("\\|");
					for (String subsetName : subsetNames) {
						List<String> vocabularyList = vocabularyCover
								.get(subsetName);
						if (vocabularyList == null) {
							vocabularyList = new ArrayList<String>();
							vocabularyCover.put(subsetName, vocabularyList);
						}
						vocabularyList.add(term);
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)
						br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			cachedVocabularyPartitions.put(fileName, vocabularyCover);
		}

		return new TermCoverBuilder(vocabularyCover, tokenStream,
				termCoverDescription, selectFilterFactory);
	}

}
