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
package org.apache.uima.conceptMapper.dictionaryCompiler;

import java.io.FileOutputStream;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.conceptMapper.support.dictionaryResource.DictionaryResource_impl;
import org.apache.uima.util.XMLInputSource;

public class CompileDictionary {
  private static final String DICTIONARY_RESOURCE_NAME = "/DictionaryFile";

  public static void main(String[] args) throws Exception {
    AnalysisEngineDescription conceptMapperDesc = UIMAFramework.getXMLParser()
            .parseAnalysisEngineDescription(new XMLInputSource(args[0]));
    AnalysisEngine ae = UIMAFramework.produceAnalysisEngine(conceptMapperDesc);
    DictionaryResource_impl dict = (DictionaryResource_impl) ae.getResourceManager().getResource(
            DICTIONARY_RESOURCE_NAME);

    FileOutputStream output = new FileOutputStream(args[1]);
    dict.serializeEntries(output);
    output.close();
    ae.destroy();
    // for some reason JVM won't exit normally,
    // probably because CPM threads are alive?
    System.exit(0);
  }
}
