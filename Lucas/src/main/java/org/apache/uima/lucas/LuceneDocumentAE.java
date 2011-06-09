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

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.CasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.lucas.indexer.*;
import org.apache.uima.lucas.indexer.analysis.DefaultFilterFactoryRegistry;
import org.apache.uima.lucas.indexer.analysis.TokenFilterFactory;
import org.apache.uima.lucas.indexer.mapping.*;
import org.apache.uima.resource.ResourceInitializationException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract base class for AEs which need to process Lucene {@link Document}
 * objects. 
 */
public abstract class LuceneDocumentAE extends CasAnnotator_ImplBase {

	private static final Logger log = Logger
			.getLogger(LuceneDocumentAE.class);

	public final static String PARAM_MAPPINGFILE = "mappingFile";

	protected Collection<FieldDescription> fieldDescriptions;

	private DocumentBuilder documentBuilder;

	protected FieldBuilder fieldBuilder;

	protected FilterBuilder filterBuilder;

	protected AnnotationTokenStreamBuilder annotationTokenStreamBuilder;

	protected Tokenizer tokenizer;

	private Map<String, TokenFilterFactory> defaultFilterFactoryRegistry;

	/**
	 * initializes the analyzer
	 */
	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		super.initialize(aContext);
		
		createFieldDescriptions();
		createFilterBuilderWithPreloadedResources();

		annotationTokenStreamBuilder = new AnnotationTokenStreamBuilder();
		tokenizer = new Tokenizer();
		fieldBuilder = new FieldBuilder(filterBuilder);
		documentBuilder = new DocumentBuilder();
	}

	private void createFieldDescriptions()
			throws ResourceInitializationException {
		String mappingFilePath = (String) getContext().getConfigParameterValue(PARAM_MAPPINGFILE);

		try {
			MappingFileReader indexMappingFileReader = createMappingFileReader();
			File mappingFile = new File(mappingFilePath);
			fieldDescriptions = indexMappingFileReader
					.readFieldDescriptionsFromFile(mappingFile);
		} catch (IOException e) {
			throw new ResourceInitializationException(e);
		}
		catch (SAXException e) {
			throw new ResourceInitializationException(e);
		}
	}

	private MappingFileReader createMappingFileReader() throws IOException{
		try {
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			Map<String, ElementMapper<?>> elementMappers = new HashMap<String, ElementMapper<?>>();
			elementMappers.put(MappingFileReader.ANNOTATION, new AnnotationMapper());
			elementMappers.put(MappingFileReader.FILTER, new FilterMapper());
			elementMappers.put(MappingFileReader.FIELD, new FieldMapper());
			elementMappers.put(MappingFileReader.FEATURE, new FeatureMapper());			
			return new MappingFileReader(parser, elementMappers);
			
		} catch (ParserConfigurationException e) {
			throw new IOException("Can't build SAXParser: " + e.getMessage());
		} catch (SAXException e) {
			throw new IOException("Can't build SAXParser: " + e.getMessage());
		}
	}

	private void createFilterBuilderWithPreloadedResources()
			throws ResourceInitializationException {
		defaultFilterFactoryRegistry = new DefaultFilterFactoryRegistry()
				.getDefaultRegistry();
		try {
			preloadResources(fieldDescriptions, defaultFilterFactoryRegistry);
		} catch (IOException e) {
			throw new ResourceInitializationException(e);
		}
		filterBuilder = new FilterBuilder(defaultFilterFactoryRegistry);
	}

	protected void preloadResources(Collection<FieldDescription> fieldDescriptions,
			Map<String, TokenFilterFactory> defaultFilterFactoryRegistry)
			throws IOException {

		for (FieldDescription fieldDescription : fieldDescriptions) {
			for (FilterDescription filterDescription : fieldDescription
					.getFilterDescriptions()) {
				TokenFilterFactory tokenFilterFactory = defaultFilterFactoryRegistry
						.get(filterDescription.getName());
				if (tokenFilterFactory != null)
					tokenFilterFactory.preloadResources(filterDescription
							.getProperties());
			}

			for (AnnotationDescription annotationDescription : fieldDescription
					.getAnnotationDescriptions()) {
				for (FilterDescription filterDescription : annotationDescription
						.getFilterDescriptions()) {
					TokenFilterFactory tokenFilterFactory = defaultFilterFactoryRegistry
							.get(filterDescription.getName());
					if (tokenFilterFactory != null)
						tokenFilterFactory.preloadResources(filterDescription
								.getProperties());
				}
			}
		}
	}

	public Document createDocument(CAS cas) throws AnalysisEngineProcessException {

		try {
			JCas jCas = cas.getJCas();
			Collection<Field> fields = new ArrayList<Field>();
			// iterate over field descriptions from mapping file
			for (FieldDescription fieldDescription : fieldDescriptions) {
				Collection<TokenStream> tokenStreams = new ArrayList<TokenStream>();
				// iterate over annotation descriptions
				for (AnnotationDescription annotationDescription : fieldDescription
						.getAnnotationDescriptions()) {
					// create annotation token stream
					TokenStream tokenStream = annotationTokenStreamBuilder
							.createAnnotationTokenStream(jCas,
									annotationDescription);

					// needs (re)tokenization ?
					if (tokenizer.needsTokenization(annotationDescription))
						tokenStream = tokenizer.tokenize(tokenStream,
								annotationDescription);

					// wrap with filters
					tokenStream = filterBuilder.filter(tokenStream,
							annotationDescription.getFilterDescriptions());
					tokenStreams.add(tokenStream);
				}

				// create fields
				fields.addAll(fieldBuilder.createFields(tokenStreams,
						fieldDescription));
			}
			// create document and add to index
			Document document = documentBuilder.createDocument(fields);
			
			return document;
			
		} catch (AnnotationTokenStreamBuildingException e) {
			log.error("processCas(CAS)", e);
			throw new AnalysisEngineProcessException(e);
		} catch (IOException e) {
			log.error("processCas(CAS)", e);
			throw new AnalysisEngineProcessException(e);
		} catch (FieldBuildingException e) {
			log.error("processCas(CAS)", e);
			throw new AnalysisEngineProcessException(e);
		} catch (FilterBuildingException e) {
			log.error("processCas(CAS)", e);
			throw new AnalysisEngineProcessException(e);
		} catch (CASException e) {
			log.error("processCas(CAS)", e);
			throw new AnalysisEngineProcessException(e);
		}
	}

	FilterBuilder getFilterBuilder() {
		return filterBuilder;
	}

	Collection<FieldDescription> getFieldDescriptions() {
		return fieldDescriptions;
	}
}
