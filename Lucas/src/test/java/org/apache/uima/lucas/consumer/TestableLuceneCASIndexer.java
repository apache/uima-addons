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
import java.util.Collection;
import java.util.Map;

import org.apache.lucene.index.IndexWriter;
import org.apache.uima.lucas.indexer.FilterBuilder;
import org.apache.uima.lucas.indexer.analysis.TokenFilterFactory;
import org.apache.uima.lucas.indexer.mapping.FieldDescription;

public class TestableLuceneCASIndexer extends LuceneCASIndexer {
  
  static TestableLuceneCASIndexer instance;
  
  public TestableLuceneCASIndexer() {
    instance = this;
  }
  
  FilterBuilder getFilterBuilder() {
    return filterBuilder;
  }

  Collection<FieldDescription> getFieldDescriptions() {
    return fieldDescriptions;
  }

  IndexWriter getIndexWriter() {
    return indexWriter;
  }
  
  public void preloadResources(Collection<FieldDescription> fieldDescriptions,
          Map<String, TokenFilterFactory> defaultFilterFactoryRegistry)
          throws IOException {
		super.preloadResources(fieldDescriptions, defaultFilterFactoryRegistry);
	}
}
