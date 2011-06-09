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

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class DefaultFilterFactoryRegistryTest {

  private DefaultFilterFactoryRegistry defaultFilterFactoryRegistry;
  
  @Before
  public void setUp() throws Exception {
    this.defaultFilterFactoryRegistry = new DefaultFilterFactoryRegistry();
  }

  @Test
  public void testGetDefaultFilterFactories() {
    Map<String, TokenFilterFactory> registry = defaultFilterFactoryRegistry.getDefaultRegistry();
    assertEquals(11, registry.size());
    
    TokenFilterFactory filterFactory = registry.get(DefaultFilterFactoryRegistry.ADDITION_FILTER_FACTORY_NAME);
    assertEquals(AdditionFilterFactory.class, filterFactory.getClass());
    
    filterFactory = registry.get(DefaultFilterFactoryRegistry.HYPERNYM_FILTER_FACTORY_NAME);
    assertEquals(HypernymFilterFactory.class, filterFactory.getClass());
    
    filterFactory = registry.get(DefaultFilterFactoryRegistry.POSITION_FILTER_FACTORY_NAME);
    assertEquals(PositionFilterFactory.class, filterFactory.getClass());

    filterFactory = registry.get(DefaultFilterFactoryRegistry.REPLACE_FILTER_FACTORY_NAME);
    assertEquals(ReplaceFilterFactory.class, filterFactory.getClass());

    filterFactory = registry.get(DefaultFilterFactoryRegistry.SNOWBALL_FILTER_FACTORY_NAME);
    assertEquals(SnowballFilterFactory.class, filterFactory.getClass());

    filterFactory = registry.get(DefaultFilterFactoryRegistry.SPLITTER_FILTER_FACTORY_NAME);
    assertEquals(SplitterFilterFactory.class, filterFactory.getClass());

    filterFactory = registry.get(DefaultFilterFactoryRegistry.CONCAT_FILTER_FACTORY_NAME);
    assertEquals(ConcatFilterFactory.class, filterFactory.getClass());
    
    filterFactory = registry.get(DefaultFilterFactoryRegistry.STOPWORD_FILTER_FACTORY_NAME);
    assertEquals(StopwordFilterFactory.class, filterFactory.getClass());

    filterFactory = registry.get(DefaultFilterFactoryRegistry.UNIQUE_FILTER_FACTORY_NAME);
    assertEquals(UniqueFilterFactory.class, filterFactory.getClass());

    filterFactory = registry.get(DefaultFilterFactoryRegistry.UPPERCASE_FILTER_FACTORY_NAME);
    assertEquals(UpperCaseFilterFactory.class, filterFactory.getClass());

    filterFactory = registry.get(DefaultFilterFactoryRegistry.LOWERCASE_FILTER_FACTORY_NAME);
    assertEquals(LowerCaseFilterFactory.class, filterFactory.getClass());
  }

}
