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

public class AdditionFilterFactory implements TokenFilterFactory {

	public static final String POSTFIX_POSITION = "postfix";
	public static final String PREFIX_POSITION = "prefix";

	public TokenFilter createTokenFilter(TokenStream tokenStream,
			Properties properties) throws IOException {

	  String postfix = properties.getProperty(POSTFIX_POSITION);	  
	  String addition = null;
    Integer position = null;
    
    if( postfix != null ){
      position = AdditionFilter.POSTFIX;
      addition = postfix;
    }
    else {
      String prefix = properties.getProperty(PREFIX_POSITION);
      if( prefix != null ){
        position = AdditionFilter.PREFIX;
        addition = prefix;        
      }
      else
        throw new IllegalArgumentException("no postfix or prefix attribute provided");
    }
    
    return new AdditionFilter(tokenStream, addition, position);
	}

	public void preloadResources(Properties properties) throws IOException {
		// TODO Auto-generated method stub

	}

}
