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

package org.apache.uima.solrcas;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.core.CoreContainer;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.admin.CASFactory;
import org.apache.uima.cas.admin.CASMgr;
import org.apache.uima.cas.admin.FSIndexRepositoryMgr;
import org.apache.uima.cas.admin.TypeSystemMgr;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.test.junit_extension.AnnotatorTester;
import org.apache.uima.util.CasCreationUtils;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Collection;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Integration test with a running Solr
 */
public class SolrCasConsumerIntegrationTest {

  @Test
  public void testCASConsumer() {
    try {
      /* create Solrcas tester */
      AnnotatorTester annotatorTester = new AnnotatorTester("src/test/resources/TestSolrcasAE.xml");

      /* create a mock CAS */
      CAS cas = getCAS();

      cas.setDocumentText("Francesco Totti is the best football player");
      cas.setDocumentLanguage("en");

      AnnotationFS annotation = cas.createAnnotation(cas.getAnnotationType(), 0, 9);
      cas.addFsToIndexes(annotation);

      /* execute Solrcas on the created CAS*/
      annotatorTester.performTest(cas);

      /* create a Solr instance to check document has been indexed as expected */
      URL solrURL = this.getClass().getResource("/org/apache/uima/solrcas/");
      System.setProperty("solr.solr.home", new File(solrURL.toURI()).getAbsolutePath());
      CoreContainer.Initializer initializer = new CoreContainer.Initializer();
      CoreContainer coreContainer = initializer.initialize();
      SolrServer solrServer = new EmbeddedSolrServer(coreContainer, "");

      ModifiableSolrParams solrParams = new ModifiableSolrParams();
      solrParams.add("q", "annotation:Francesco");
      QueryResponse queryResponse = solrServer.query(solrParams);

      /* check the result contains only one doc with 2 annotations of the mock CAS */
      assertTrue(queryResponse != null);
      SolrDocumentList results = queryResponse.getResults();
      assertTrue(results.getNumFound() == 1);
      SolrDocument doc = results.get(0);
      Collection<Object> annotationValues = doc.getFieldValues("annotation");
      assertTrue(annotationValues.size() == 2);
      assertTrue(annotationValues.contains("Francesco"));
      assertTrue(annotationValues.contains("Francesco Totti is the best football player"));

    } catch (Exception e) {
      e.printStackTrace();
      fail(e.getLocalizedMessage());
    }
  }

  private CAS getCAS() throws ResourceInitializationException, CASException {
    // Create an initial CASMgr from the factory.
    CASMgr casMgr0 = CASFactory.createCAS();
    CASMgr casMgr = null;
    // this call does nothing: because 2nd arg is null
    CasCreationUtils.setupTypeSystem(casMgr0, null);
    // Create a writable type system.
    TypeSystemMgr tsa = casMgr0.getTypeSystemMgr();

    // Commit the type system.
    ((CASImpl) casMgr0).commitTypeSystem();

    casMgr = CASFactory.createCAS(tsa);

    // Create the Base indexes.
    casMgr.initCASIndexes();
    // Commit the index repository.
    FSIndexRepositoryMgr irm = casMgr.getIndexRepositoryMgr();

    irm.commit();

    // Create the default text Sofa and return CAS view
    return casMgr.getCAS().getCurrentView();
  }

}
