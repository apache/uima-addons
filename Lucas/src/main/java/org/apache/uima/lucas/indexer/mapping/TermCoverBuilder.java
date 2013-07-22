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
package org.apache.uima.lucas.indexer.mapping;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.lucene.analysis.TokenStream;
import org.apache.uima.lucas.indexer.analysis.SelectFilter;
import org.apache.uima.lucas.indexer.analysis.SelectFilterFactory;

/**
 * <p>
 * Constructs <tt>TokenStream</tt>s according to a cover definition.
 * </p>
 * <p>
 * This class uses {@link SelectFilter}s to build <tt>TokenStream</tt>s which
 * only output terms that pass the <tt>SelectFilter</tt>. Each
 * <tt>TokenStream</tt> corresponds to one cover set and will only output terms
 * included in this particular set. This mechanism can be used to dynamically
 * create multiple fields according to a definition about which term may be included
 * in which field.<br/>
 * To create an instance of this class, the {@link TermCoverBuilderFactory}
 * should be used as it cashes the cover definition. For further explanation of
 * the nature of these covers and their required file format, please refer to
 * the factory class.
 * </p>
 * 
 * @see TermCoverBuilderFactory
 *
 */
public class TermCoverBuilder {

	private final Map<String, List<String>> vocabularyCover;
	private Iterator<String> subsetIterator;
	private String currentCoverSubsetName;
	private TokenStream currentCoverSubsetTokenStream;
	private SelectFilterFactory selectFilterFactory;
	private TokenStream tokenStream;
	private Properties properties;
	private final TermCoverDescription fieldDescription;

	/**
	 * @param vocabularyCover
	 * @param selectFilterFactory
	 */
	public TermCoverBuilder(Map<String, List<String>> vocabularyCover,
			TokenStream tokenStream, TermCoverDescription fieldDescription,
			SelectFilterFactory selectFilterFactory) {
		this.vocabularyCover = vocabularyCover;
		this.tokenStream = tokenStream;
		this.fieldDescription = fieldDescription;
		this.selectFilterFactory = selectFilterFactory;
		properties = new Properties();
		properties.put("enablePositionIncrements", "false");
		properties.put("ignoreCase",
				fieldDescription.getIgnoreCaseOfSelectedTerms() ? "true" : "false");
		subsetIterator = vocabularyCover.keySet().iterator();
	}

	public boolean increaseCoverSubset() {
		if (subsetIterator.hasNext()) {
			currentCoverSubsetName = subsetIterator.next();
			properties.put(SelectFilterFactory.COVER_SUBSET_NAME, getCoverSubsetName());
			currentCoverSubsetTokenStream = selectFilterFactory
					.createTokenFilter(tokenStream, properties,
							vocabularyCover.get(currentCoverSubsetName));
			return true;
		}
		return false;
	}

	public String getCoverSubsetName() {
		String fieldName = fieldDescription.getFieldName();
		String combineFieldName = fieldDescription.getGenerateFieldNameMethod();
		if (combineFieldName.equals(TermCoverMapper.USE_SUBSET_NAME_APPEND))
			fieldName = fieldName + currentCoverSubsetName;
		else if (combineFieldName.equals(TermCoverMapper.USE_SUBSET_NAME_PREPEND))
			fieldName = currentCoverSubsetName + fieldName;
		else if (combineFieldName.equals(TermCoverMapper.USE_SUBSET_NAME_REPLACE))
			fieldName = currentCoverSubsetName;
		return fieldName;
	}

	public TokenStream getPartitionTokenStream() {
		return currentCoverSubsetTokenStream;
	}

}
