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

package org.apache.uima.lucas.indexer.test.util;

import java.io.IOException;

import org.apache.uima.cas.CAS;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.util.Progress;

public class MockCollectionReader extends CollectionReader_ImplBase {

	public void getNext(CAS arg0) throws IOException, CollectionException {
		// TODO Auto-generated method stub

	}

	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	public Progress[] getProgress() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasNext() throws IOException, CollectionException {
		// TODO Auto-generated method stub
		return false;
	}

}
