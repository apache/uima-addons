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

package org.apache.uima.lucas.consumer;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.lucene.index.IndexWriter;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.lucas.LuceneDocumentAE;
import org.apache.uima.lucas.indexer.IndexWriterProvider;
import org.apache.uima.resource.ResourceAccessException;
import org.apache.uima.resource.ResourceInitializationException;

/**
 * Reads CAS object and writes the particular information in fields of a Lucene index.
 * requires a mapping file
 */
public class LuceneCASIndexer extends LuceneDocumentAE {

	private static final String RESOURCE_INDEX_WRITER_PROVIDER = "indexWriterProvider";
	
	private static final Logger logger = Logger
			.getLogger(LuceneCASIndexer.class);

	protected IndexWriter indexWriter;

	@Override
	public void initialize(UimaContext aContext)
			throws ResourceInitializationException {
		super.initialize(aContext);
		
		getIndexWriterInstance();
	}
	
	private void getIndexWriterInstance()
			throws ResourceInitializationException {
		UimaContext uimaContext = getContext();
		IndexWriterProvider indexWriterProvider;
		try {
			indexWriterProvider = (IndexWriterProvider) uimaContext
					.getResourceObject(RESOURCE_INDEX_WRITER_PROVIDER);
		} catch (ResourceAccessException e) {
			throw new ResourceInitializationException(e);
		}
		indexWriter = indexWriterProvider.getIndexWriter();
	}
	
	public void process(CAS cas) throws AnalysisEngineProcessException {
		try {
			indexWriter.addDocument(createDocument(cas));
		} catch (IOException e) {
			logger.error("processCas(CAS)", e);
			throw new AnalysisEngineProcessException(e);
		}
	}

	@Override
	public void destroy() {
		logger.info("destroy " + LuceneCASIndexer.class);
		optimizeIndex();
		super.destroy();
	}

	public void optimizeIndex() {
		try {
			logger.info("optimizing the index now!");
			indexWriter.optimize();
			indexWriter.close();
		} catch (IOException e) {
			logger.error("exception while closing index", e);
		}
	}

}