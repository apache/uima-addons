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

import java.io.IOException;
import java.util.Properties;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;

public class PositionFilterFactory implements TokenFilterFactory {
  public static final String POSITION_PARAMETER = "position"; 
  public static final String FIRST_POSITION_PARAMETER_VALUE = "first";
  public static final String LAST_POSITION_PARAMETER_VALUE = "last";
  
  public TokenFilter createTokenFilter(TokenStream tokenStream, Properties properties)
          throws IOException {

    String positionAttribute = properties.getProperty(POSITION_PARAMETER);
    Integer position = -1;
    
    if( positionAttribute == null )
      throw new IllegalArgumentException("no position information provided");
    
    if( positionAttribute.equals(FIRST_POSITION_PARAMETER_VALUE) )
      position = PositionFilter.FIRST_POSITION;
    else if( positionAttribute.equals(LAST_POSITION_PARAMETER_VALUE) )
      position = PositionFilter.LAST_POSITION;
    else
      throw new IllegalArgumentException("unknown position value: " + positionAttribute);
    
    return new PositionFilter(tokenStream, position);
  }

  public void preloadResources(Properties properties) throws IOException {
    // we have no resources
  }

}
