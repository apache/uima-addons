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

import static org.easymock.EasyMock.capture;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.easymock.classextension.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

import org.apache.lucene.store.FSDirectory;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.lucas.indexer.analysis.TokenFilterFactory;
import org.apache.uima.lucas.indexer.mapping.FieldDescription;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.easymock.Capture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableBiMap;

public class LuceneCASIndexerTest {

  private static final String pathSep = System.getProperty("file.separator");

  private static final String TEST_FILTER_ANNOTATION = "stopwords";

  private static final String TEST_FILTER_FIELD = "hypernyms";

  private static final String FIELD_NAME = "annotation1";

  private static final String DESCRIPTOR_FILE = "src/test/resources/LuceneCASIndexer.xml";

  private static final String INDEX_DIRECTORY = "src" + pathSep
      + "test" + pathSep + "resources" + pathSep + "test-index";

  private AnalysisEngine analysisEngine;
    
  private TestableLuceneCASIndexer luceneCASIndexer;

  @Before
  public void setUp() throws InvalidXMLException, IOException, ResourceInitializationException {
    
    AnalysisEngineDescription analysisEngineDescription = (AnalysisEngineDescription) UIMAFramework
    .getXMLParser().parseAnalysisEngineDescription(new XMLInputSource(DESCRIPTOR_FILE));
    analysisEngine = UIMAFramework.produceAnalysisEngine(analysisEngineDescription);
    luceneCASIndexer = TestableLuceneCASIndexer.instance;
    assertNotNull(luceneCASIndexer);
  }

  @After
  public void tearDown() throws Exception {
    if (luceneCASIndexer == null)
        return;
    
    FSDirectory directory = (FSDirectory) luceneCASIndexer.getIndexWriter().getDirectory();
    File directoryFile = directory.getFile();
    luceneCASIndexer.destroy();

    directory = FSDirectory.getDirectory(directoryFile);

    for (String file : directory.list())
      directory.deleteFile(file);

    directory.getFile().delete();
  }

  @Test
  public void testIndexOutDir() {
    FSDirectory directory = (FSDirectory) luceneCASIndexer.getIndexWriter().getDirectory();

    String path = directory.getFile().getPath();
    assertTrue(path.contains(INDEX_DIRECTORY));
  }

  @Test
  public void testMappingFile() {
    Collection<FieldDescription> fieldDescriptions = luceneCASIndexer.getFieldDescriptions();
    assertEquals(1, fieldDescriptions.size());
    FieldDescription fieldDescription = fieldDescriptions.iterator().next();
    assertEquals(FIELD_NAME, fieldDescription.getName());
    assertEquals(2, fieldDescription.getAnnotationDescriptions().size());
  }

  @Test
  public void testPreloadResources() throws IOException {
    Collection<FieldDescription> fieldDescriptions = luceneCASIndexer.getFieldDescriptions();
    TokenFilterFactory testFactoryField = createMock(TokenFilterFactory.class);
    TokenFilterFactory testFactoryAnnotation = createMock(TokenFilterFactory.class);

    Capture<Properties> propertiesCaptureField = new Capture<Properties>();
    Capture<Properties> propertiesCaptureAnnotation = new Capture<Properties>();

    testFactoryField.preloadResources(capture(propertiesCaptureField));
    testFactoryAnnotation.preloadResources(capture(propertiesCaptureAnnotation));

    replay(testFactoryField);
    replay(testFactoryAnnotation);

    luceneCASIndexer.preloadResources(fieldDescriptions, ImmutableBiMap.of(TEST_FILTER_ANNOTATION,
        testFactoryAnnotation, TEST_FILTER_FIELD, testFactoryField));
    verify(testFactoryField);
    verify(testFactoryAnnotation);

    Properties fieldFilterProperties = propertiesCaptureField.getValue();
    assertEquals("src/test/resources/hypernyms.txt", fieldFilterProperties.getProperty("filePath"));

    Properties annotationFilterProperties = propertiesCaptureAnnotation.getValue();
    assertEquals("src/test/resources/stopwords.txt", annotationFilterProperties.getProperty("filePath"));
  }

  @Test
  public void testIndexOneDocument() throws ResourceInitializationException,
          AnalysisEngineProcessException {
    CAS cas = analysisEngine.newCAS();
    cas.setDocumentText("test document text");
    analysisEngine.process(cas);
    
    
  }
}
