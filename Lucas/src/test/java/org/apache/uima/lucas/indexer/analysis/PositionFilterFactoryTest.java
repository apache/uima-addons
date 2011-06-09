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
import org.apache.uima.lucas.indexer.test.util.DummyTokenStream;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class PositionFilterFactoryTest {

  private TokenFilterFactory positionFilterFactory;
  private TokenStream tokenStream;
  
  @Before
  public void setUp(){
    positionFilterFactory = new PositionFilterFactory();
    tokenStream = new DummyTokenStream("dummy", 1, 1, 0);
  }
  
  @Test
  public void testCreateTokenFilter() throws IOException{
    Properties properties = new Properties();
    properties.setProperty(PositionFilterFactory.POSITION_PARAMETER, PositionFilterFactory.FIRST_POSITION_PARAMETER_VALUE);
    
    PositionFilter positionTokenFilter = (PositionFilter) positionFilterFactory.createTokenFilter(tokenStream, properties);
    assertEquals(PositionFilter.FIRST_POSITION, positionTokenFilter.getPosition());
    
    properties = new Properties();
    properties.setProperty(PositionFilterFactory.POSITION_PARAMETER, PositionFilterFactory.LAST_POSITION_PARAMETER_VALUE);
    
    positionTokenFilter = (PositionFilter) positionFilterFactory.createTokenFilter(tokenStream, properties);
    assertEquals(PositionFilter.LAST_POSITION, positionTokenFilter.getPosition());
  }
}
