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

package org.apache.uima.lucas.indexer;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.Field.TermVector;
import org.apache.uima.lucas.indexer.analysis.TokenStreamConcatenator;
import org.apache.uima.lucas.indexer.analysis.TokenStreamMerger;
import org.apache.uima.lucas.indexer.mapping.FieldDescription;
import org.apache.uima.lucas.indexer.mapping.FilterDescription;
import org.apache.uima.lucas.indexer.util.TokenStreamStringConcatenator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class FieldBuilder {

	public static final String FIELD_INDEX_NO = "no";

	public static final String FIELD_INDEX_YES = "yes";

	public static final String FIELD_INDEX_NO_NORMS = "no_norms";

	public static final String FIELD_INDEX_NO_TF = "no_tf";

	public static final String FIELD_INDEX_NO_NORMS_TF = "no_norms_tf";

	public static final String FIELD_TERM_VECTOR_NO = "no";

	public static final String FIELD_TERM_VECTOR_YES = "yes";

	public static final String FIELD_TERM_VECTOR_WITH_OFFSETS = "offsets";

	public static final String FIELD_TERM_VECTOR_WITH_POSITIONS = "positions";

	public static final String FIELD_TERM_VECTOR_WITH_POSITIONS_OFFSETS = "positions_offsets";

	public static final String FIELD_STORE_NO = "no";

	public static final String FIELD_STORE_YES = "yes";

	public static final String FIELD_STORE_COMPRESS = "compress";

	protected TokenStreamStringConcatenator tokenStreamStringConcatenator;
	private FilterBuilder filterBuilder;
	protected FieldDescription fieldDescription;

	public FieldBuilder(FilterBuilder filterBuilder) {
		tokenStreamStringConcatenator = new TokenStreamStringConcatenator();
		this.filterBuilder = filterBuilder;
	}

	public Collection<Field> createFields(Collection<TokenStream> tokenStreams,
			FieldDescription fieldDescription) throws FieldBuildingException {

		TokenStream tokenStream = createFieldTokenStream(tokenStreams,
				fieldDescription);

		this.fieldDescription = fieldDescription;
		String fieldName = fieldDescription.getName();
		String delimiter = fieldDescription.getDelimiter();
		Collection<Field> fields = new ArrayList<Field>();

		Collection<FilterDescription> filterDescriptions = fieldDescription
				.getFilterDescriptions();
		tokenStream = getFilteredTokenStream(fieldName, tokenStream,
				filterDescriptions);

		// The unique flag means we only want ONE field instance with the
		// name fieldName.
		Boolean unique = fieldDescription.getUnique();
		Field.Store fieldStore = getFieldStore(fieldDescription.getStored());
		Field.Index fieldIndex = getFieldIndex(fieldDescription.getIndex());
		Field.TermVector fieldTermVector = getFieldTermVector(fieldDescription
				.getTermVector());
		boolean omitTF = fieldDescription.getIndex().equals(FIELD_INDEX_NO_TF)
				|| fieldDescription.getIndex().equals(FIELD_INDEX_NO_NORMS_TF);
		boolean store = fieldStore == Field.Store.YES || fieldStore == Field.Store.COMPRESS;

		// Created stored fields. The parameters unique, fieldIndex and omitTF
		// are only necessary in case of a stored and indexed unique field. Then,
		// the field is instanced stored and indexed, thus only one instance
		// of the field is necessary. This only works with TokenStreams which
		// contain exactly one token (if a TokenStream emits more tokens,
		// several fields will be instanced).
		if (store)
			fields.addAll(createStoredFields(fieldName, tokenStream,
					fieldStore, delimiter, unique, fieldIndex, omitTF));

		// Create indexed fields. If the field is unique and has been stored,
		// there already is an instance of the field and we don't create another.
		if (fieldIndex != Field.Index.NO && (!unique || !store))
			fields.add(createIndexedField(fieldName, tokenStream, fieldIndex,
					fieldTermVector, omitTF));

		return fields;
	}

	protected TokenStream createFieldTokenStream(
			Collection<TokenStream> tokenStreams,
			FieldDescription fieldDescription) throws FieldBuildingException {
		TokenStream tokenStream = null;
		if (fieldDescription.getMerge())
			tokenStream = getTokenStreamMerger(tokenStreams);
		else if (tokenStreams.size() > 1)
			tokenStream = new TokenStreamConcatenator(tokenStreams);
		else if (tokenStreams.size() == 1)
			tokenStream = tokenStreams.iterator().next();
		return tokenStream;
	}

	protected TokenStream getFilteredTokenStream(String fieldName,
			TokenStream tokenStream,
			Collection<FilterDescription> filterDescriptions)
			throws FieldBuildingException {
		try {
			return filterBuilder.filter(tokenStream, filterDescriptions);
		} catch (FilterBuildingException e) {
			throw createException(e);
		}
	}

	protected FieldBuildingException createException(Exception e) {
		String message = "Can't build field " + fieldDescription.getName()
				+ " at line " + fieldDescription.getLineNumber();
		return new FieldBuildingException(message, e);
	}

	private TokenStream getTokenStreamMerger(
			Collection<TokenStream> tokenStreams) throws FieldBuildingException {
		try {
			return new TokenStreamMerger(tokenStreams);
		} catch (IOException e) {
			throw createException(e);
		}

	}

	protected Field createIndexedField(String fieldName, TokenStream tokenStream,
			Index fieldIndex, TermVector fieldTermVector, boolean omitTF) {

		Field field = new Field(fieldName, tokenStream, fieldTermVector);
		if (fieldIndex == Field.Index.NOT_ANALYZED_NO_NORMS)
			field.setOmitNorms(true);
		field.setOmitTermFreqAndPositions(omitTF);

		return field;
	}

	protected Collection<Field> createStoredFields(String fieldName,
			TokenStream tokenStream, Store fieldStore, String delimiter,
			Boolean unique, Index fieldIndex, Boolean omitTF)
			throws FieldBuildingException {
		Collection<Field> fields = new ArrayList<Field>();
		// Only do indexing if we need a unique, indexed AND stored field.
		Index index = unique ? fieldIndex : Field.Index.NO;
		try {
			Field field;
			if (delimiter != null) {
				String value = tokenStreamStringConcatenator
						.tokenStreamToStringWithDelimiter(tokenStream,
								delimiter);
				field = new Field(fieldName, value, fieldStore, index);
				if (unique)
					field.setOmitTermFreqAndPositions(omitTF);
				fields.add(field);
			} else {
				Token nextToken = tokenStream.next(new Token());
				while (nextToken != null) {
					String value = new String(nextToken.termBuffer(), 0,
							nextToken.termLength());
					field = new Field(fieldName, value, fieldStore, index);
					if (unique)
						field.setOmitTermFreqAndPositions(omitTF);
					fields.add(field);
					nextToken = tokenStream.next(nextToken);
				}
			}
			tokenStream.reset();
		} catch (IOException e) {
			throw createException(e);
		}
		return fields;
	}

	protected Field.Index getFieldIndex(String index)
			throws FieldBuildingException {
		if (index.equals(FIELD_INDEX_NO))
			return Field.Index.NO;
		else if (index.equals(FIELD_INDEX_YES))
			return Field.Index.NOT_ANALYZED;
		else if (index.equals(FIELD_INDEX_NO_NORMS))
			return Field.Index.NOT_ANALYZED_NO_NORMS;
		else if (index.equals(FIELD_INDEX_NO_NORMS_TF))
			return Field.Index.NOT_ANALYZED_NO_NORMS;
		else if (index.equals(FIELD_INDEX_NO_TF))
			return Field.Index.NOT_ANALYZED;

		throw createException(new IllegalArgumentException(
				"unknown index parameter: " + index));

	}

	protected Field.TermVector getFieldTermVector(String termVector)
			throws FieldBuildingException {
		if (termVector.equals(FIELD_TERM_VECTOR_NO))
			return Field.TermVector.NO;
		else if (termVector.equals(FIELD_TERM_VECTOR_YES))
			return Field.TermVector.YES;
		else if (termVector.equals(FIELD_TERM_VECTOR_WITH_OFFSETS))
			return Field.TermVector.WITH_OFFSETS;
		else if (termVector.equals(FIELD_TERM_VECTOR_WITH_POSITIONS))
			return Field.TermVector.WITH_POSITIONS;
		else if (termVector.equals(FIELD_TERM_VECTOR_WITH_POSITIONS_OFFSETS))
			return Field.TermVector.WITH_POSITIONS_OFFSETS;

		throw createException(new IllegalArgumentException(
				"unknown termVector parameter: " + termVector));
	}

	protected Field.Store getFieldStore(String store)
			throws FieldBuildingException {
		if (store.equals(FIELD_STORE_NO))
			return Field.Store.NO;
		else if (store.equals(FIELD_STORE_YES))
			return Field.Store.YES;
		else if (store.equals(FIELD_STORE_COMPRESS))
			return Field.Store.COMPRESS;

		throw createException(new IllegalArgumentException(
				"unknown stored parameter: " + store));
	}

}
