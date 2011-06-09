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

import java.util.Collection;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;

/**
 * The {@link SearchQueryProvider} is responsible to provide access to
 * the {@link SearchQuery}s. 
 */
public interface SearchQueryProvider {

	/**
	 * Retrieves all {@link SearchQuery}s for the given <code>CAS</code>.
	 * The returned search queries may depend on the given <code>CAS</code>.
	 * <p>
	 * This method is called for every processed <code>CAS</code> and should be
	 * fast enough to not slow down the search. 
	 * <p>
	 * Note: The implementation must be thread safe.
	 * 
	 * @param cas
	 * @return all {@link SearchQuery}s for the given <code>CAS</code>.
	 * @throws AnalysisEngineProcessException
	 */
	Collection<SearchQuery> getSearchQueries(CAS cas) throws AnalysisEngineProcessException;
}
