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

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.snowball.SnowballFilter;
import org.apache.uima.lucas.indexer.test.util.DummyTokenStream;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.assertNotNull;

public class SnowballFilterFactoryTest {

  private static final String PORTER = "Porter";
  private TokenStream tokenStream;
  private SnowballFilterFactory snowballFilterFactory;
  
  @Before
  public void setUp(){
    tokenStream = new DummyTokenStream("dummy", 1, 1, 0);
    snowballFilterFactory = new SnowballFilterFactory();
  }
  
  @Test
  public void testCreateTokenFilter() throws Exception{
    Properties properties = new Properties();
    properties.setProperty(SnowballFilterFactory.STEMMER_NAME_PARAMETER, PORTER);
    
    SnowballFilter snowballFilter = (SnowballFilter) snowballFilterFactory.createTokenFilter(tokenStream, properties );
    assertNotNull(snowballFilter);
  }
  
}
