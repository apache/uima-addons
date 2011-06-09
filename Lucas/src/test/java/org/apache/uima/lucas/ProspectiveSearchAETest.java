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

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.*;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ProspectiveSearchAETest {
	
	private static final String DESCRIPTOR_FILE = "src/test/resources/ProspectiveSearchAEDescriptor.xml";
	
	private AnalysisEngine searchAE;
	
	private Type searchResultType;
	private Feature searchResultIdFeature;
	private Feature searchResultMatchingTextFeature;
	
	@Before
	public void setUp() throws InvalidXMLException, IOException, ResourceInitializationException {
		AnalysisEngineDescription analysisEngineDescription = (AnalysisEngineDescription) UIMAFramework
                .getXMLParser().parseAnalysisEngineDescription(
                new XMLInputSource(DESCRIPTOR_FILE));
		
		searchAE = UIMAFramework.produceAnalysisEngine(analysisEngineDescription);
		TypeSystem ts = searchAE.newCAS().getTypeSystem();
		
		searchResultType = ts.getType("org.apache.uima.lucas.SearchResult");
		searchResultIdFeature = searchResultType.getFeatureByBaseName("id");
		searchResultMatchingTextFeature = searchResultType.getFeatureByBaseName("matchingText");
	}
	
	@Test
	public void testSimpleSearch() throws ResourceInitializationException,
	        AnalysisEngineProcessException {
		CAS cas = searchAE.newCAS();
		
		cas.setDocumentText("The quick brown fox jumps over the lazy dog");
		searchAE.process(cas);
		
		FSIterator<FeatureStructure> searchResults = cas.getIndexRepository().getAllIndexedFS(searchResultType);
		
		// test for the hit for search query "quick fox"
		assertTrue(searchResults.hasNext());
		
		FeatureStructure result = searchResults.next();
		
		assertEquals(0, result.getLongValue(searchResultIdFeature));
		
		// Test if highlighting is correct
		ArrayFS matchingTextArray = (ArrayFS) result.getFeatureValue(searchResultMatchingTextFeature);
		
		assertEquals(2, matchingTextArray.size());
		
		for (int i = 0; i < matchingTextArray.size(); i++) {
			AnnotationFS annotation = (AnnotationFS) matchingTextArray.get(i);
			if (i == 0) {
				assertEquals(4, annotation.getBegin());
				assertEquals(9, annotation.getEnd());
			}
			else if (i == 1) {
				assertEquals(16, annotation.getBegin());
				assertEquals(19, annotation.getEnd());				
			}
			else {
				fail();
			}
		}
	}
}