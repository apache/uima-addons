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

package org.apache.uima.lucas;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.memory.MemoryIndex;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.TextFragment;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.*;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.resource.ResourceAccessException;
import org.apache.uima.resource.ResourceInitializationException;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The <code>ProspectiveSearchAE<code> monitors if one of the defined
 * search queries occurs in the processed document, for each matching
 * search query a FS is inserted into the CAS.
 * <p>
 * Optionally the matched text can be marked by a set of annotations, the most
 * common use case for this is search term highlighting.
 * <p>
 * The defined search queries are provided by a user implemented 
 * {@link SearchQueryProvider}, which could for example retrieve
 * the search queries from a database or a web service.
 * <p>
 * The implementation first indexes the document and then searches all defined
 * queries against this one document index, for indexing the Lucene {@link MemoryIndex}
 * is used. Notes about the runtime performance can be found in the javadoc of the
 * <code>MemoryIndex</code> class.
 * 
 * @see SearchQueryProvider
 * @see SearchQuery
 * @see MemoryIndex
 */
public class ProspectiveSearchAE extends LuceneDocumentAE {

	private SearchQueryProvider searchQueryProvider;

	/**
	 * The search result type. For each matching query one search result feature
	 * structure will be inserted into the <code>CAS</code>.
	 * <p>
	 * The FS must have one long feature to identify the matching query.
	 * <p>
	 * Optionally the FS has an array feature which contains annotations which
	 * mark the matching text of the query in the document to enable hit
	 * highlighting.
	 */
	private Type searchResultType;

	/**
	 * The id feature of the search result type.
	 */
	private Feature searchResultIdFeature;

	/**
	 * The array feature which contains annotations which mark the matching
	 * text.
	 */
	private Feature searchResultMatchingTextFeature;

	/**
	 * The type used to mark the matching text.
	 */
	private Type matchingTextType;

	private float matchingThreshold = 0.0f;

	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);

		try {
			searchQueryProvider = (SearchQueryProvider) aContext
					.getResourceObject("searchQueryProvider");
		} catch (ResourceAccessException e) {
			throw new ResourceInitializationException(e);
		}
	}

	@Override
	public void typeSystemInit(TypeSystem aTypeSystem)
			throws AnalysisEngineProcessException {
		super.typeSystemInit(aTypeSystem);

		String searchResultTypeString = (String) getContext().getConfigParameterValue(
                "org.apache.uima.lucas.SearchResultType");
		searchResultType = aTypeSystem.getType(searchResultTypeString);
		
		String searchResultIdFeatureString = (String) getContext().getConfigParameterValue(
                "org.apache.uima.lucas.SearchResultIdFeature");
		searchResultIdFeature =  searchResultType.getFeatureByBaseName(searchResultIdFeatureString);
		
		String searchResultMatchingTextFeatureString = (String) getContext().getConfigParameterValue(
				"org.apache.uima.lucas.SearchResulMatchingTextFeature");
		if (searchResultMatchingTextFeatureString != null) {
			searchResultMatchingTextFeature = searchResultType.getFeatureByBaseName(searchResultMatchingTextFeatureString);
			
			String matchingTextTypeString = (String) getContext().getConfigParameterValue(
					"org.apache.uima.lucas.MatchingTextType");
			
			if (matchingTextTypeString != null) {
				matchingTextType = aTypeSystem.getType(matchingTextTypeString);
			}
			else {
				matchingTextType = aTypeSystem.getType(CAS.TYPE_NAME_ANNOTATION);
			}
		}
	}

	@Override
	public void process(CAS aCAS)
			throws AnalysisEngineProcessException {

		// First create the index of the document text
		MemoryIndex index = new MemoryIndex();

		List fields = createDocument(aCAS).getFields();
		
		for (Iterator it = fields.iterator(); it.hasNext(); ) {
			Field field = (Field) it.next();
			
			if (field.isIndexed() && field.tokenStreamValue() != null) {
			  index.addField(field.name(), field.tokenStreamValue());
			}
		}
		
		// Search all queries against the one document index
		for (SearchQuery query : searchQueryProvider.getSearchQueries(aCAS)) {

			float score = index.search(query.query());

			if (score > matchingThreshold) { 
				
				// Add a FS to the CAS with the search result
				FeatureStructure searchResult = aCAS.createFS(searchResultType);
				searchResult.setLongValue(searchResultIdFeature, query.id());
				aCAS.addFsToIndexes(searchResult);

				// Find matching tokens and link their annotations
				// in case the user wants search term highlighting
				if (searchResultMatchingTextFeature != null) {
					
					fields = createDocument(aCAS).getFields();
					
					for (Iterator it = fields.iterator(); it.hasNext(); ) {
						
						Field field = (Field) it.next();

						if (field.isIndexed() && field.tokenStreamValue() != null) {
							
							TokenStream tokenStream = field.tokenStreamValue();
							
							Collection<AnnotationFS> matchingTextAnnotations = new LinkedList<AnnotationFS>();

							QueryScorer scorer = new QueryScorer(query.query(), field.name());
							scorer.startFragment(new TextFragment(
									new StringBuffer(aCAS.getDocumentText()), 0, 0));
							
							try {
								scorer.init(tokenStream);
								
								OffsetAttribute offsetAttr = null;
								while (tokenStream.incrementToken()) {
									offsetAttr = (OffsetAttribute)tokenStream.getAttribute(OffsetAttribute.class);
									float tokenScore = scorer.getTokenScore();
									if (tokenScore > 0) {
										AnnotationFS annotation = aCAS.createAnnotation(matchingTextType,
												offsetAttr.startOffset(), offsetAttr.endOffset());
										
										matchingTextAnnotations.add(annotation);
									}
								}
							}
							catch (IOException e) {
								throw new AnalysisEngineProcessException(e);
							}
							
							ArrayFS matchtingTextArray = aCAS.createArrayFS(matchingTextAnnotations.size());
							
							int matchtingTextArrayIndex = 0; 
							for (AnnotationFS matchingTextAnnotation: matchingTextAnnotations) {
								matchtingTextArray.set(matchtingTextArrayIndex++,
										matchingTextAnnotation);
							}
							
							searchResult.setFeatureValue(searchResultMatchingTextFeature,
									matchtingTextArray);
						}
					}
				}
			}
		}
	}
}
