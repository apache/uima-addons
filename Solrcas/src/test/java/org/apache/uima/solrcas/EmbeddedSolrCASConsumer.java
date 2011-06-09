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

import java.io.File;
import java.net.URI;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.core.CoreContainer;

public class EmbeddedSolrCASConsumer extends SolrCASConsumer {

  @Override
  protected SolrServer createServer() throws SolrServerException {
    SolrServer solrServer = null;
    try {
      String solrInstanceTypeParam = String.valueOf(getContext().
              getConfigParameterValue("solrInstanceType"));
  
      String solrPathParam = String.valueOf(getContext().
              getConfigParameterValue("solrPath"));
  
      if (solrInstanceTypeParam.equals("embedded")) {
        URI solrURI = getURI(solrPathParam);
        System.setProperty("solr.solr.home", new File(solrURI).getAbsolutePath());
        CoreContainer.Initializer initializer = new CoreContainer.Initializer();
        CoreContainer coreContainer = initializer.initialize();
        solrServer = new EmbeddedSolrServer(coreContainer, "");
      }
    } catch (Exception e){
      throw new SolrServerException(e);
    }

    return solrServer;
  }
}
