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

package org.apache.uima.lucas.indexer.analysis;

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.lucas.indexer.util.MapFileReaderFactory;
import org.apache.uima.lucas.indexer.util.MultimapFileReaderFactory;
import org.apache.uima.lucas.indexer.util.PlainFileReaderFactory;

public class DefaultFilterFactoryRegistry {

  public static final String ADDITION_FILTER_FACTORY_NAME = "addition";
  public static final String HYPERNYM_FILTER_FACTORY_NAME = "hypernyms";
  public static final String POSITION_FILTER_FACTORY_NAME = "position";
  public static final String REPLACE_FILTER_FACTORY_NAME = "replace";
  public static final String SNOWBALL_FILTER_FACTORY_NAME = "snowball";
  public static final String SPLITTER_FILTER_FACTORY_NAME = "splitter";
  public static final String STOPWORD_FILTER_FACTORY_NAME = "stopwords";
  public static final String UNIQUE_FILTER_FACTORY_NAME = "unique";
  public static final String UPPERCASE_FILTER_FACTORY_NAME = "uppercase";
  public static final String LOWERCASE_FILTER_FACTORY_NAME = "lowercase";
  public static final String UEA_FILTER_FACTORY_NAME = "uea-stemmer";
  public static final String CONCAT_FILTER_FACTORY_NAME = "concatenate";

  public Map<String, TokenFilterFactory> getDefaultRegistry(){
    Map<String, TokenFilterFactory> registry = new HashMap<String, TokenFilterFactory>();
    registry.put(ADDITION_FILTER_FACTORY_NAME, new AdditionFilterFactory());
    registry.put(HYPERNYM_FILTER_FACTORY_NAME, new HypernymFilterFactory(new MultimapFileReaderFactory()));
    registry.put(POSITION_FILTER_FACTORY_NAME, new PositionFilterFactory());
    registry.put(REPLACE_FILTER_FACTORY_NAME, new ReplaceFilterFactory(new MapFileReaderFactory()));
    registry.put(SNOWBALL_FILTER_FACTORY_NAME, new SnowballFilterFactory());
    registry.put(SPLITTER_FILTER_FACTORY_NAME, new SplitterFilterFactory());
    registry.put(CONCAT_FILTER_FACTORY_NAME, new ConcatFilterFactory());
    registry.put(STOPWORD_FILTER_FACTORY_NAME, new StopwordFilterFactory(new PlainFileReaderFactory()));
    registry.put(UNIQUE_FILTER_FACTORY_NAME, new UniqueFilterFactory());
    registry.put(UPPERCASE_FILTER_FACTORY_NAME, new UpperCaseFilterFactory());
    registry.put(LOWERCASE_FILTER_FACTORY_NAME, new LowerCaseFilterFactory());
    return registry;
  }
  
}
