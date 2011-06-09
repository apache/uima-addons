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

package org.apache.uima.lucas.indexer.util;

import java.io.IOException;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;

public class TokenStreamStringConcatenator {

  /**
   * builds a string from the tokens that can be found in tokenStream and delimites the tokens with
   * a given delimiter
   * 
   * @param ts
   * @return a sting made of tokens
   * @throws IOException
   */

  public String tokenStreamToStringWithDelimiter(TokenStream ts, String delimiter)
          throws IOException {
    String tokenString = "";
    Token newToken = new Token();
    while (true) {
      Token token = ts.next(newToken);
      if (token != null) {
        tokenString = tokenString.concat(new String(token.termBuffer(), 0, token.termLength()))
                .concat(delimiter);
      } else {
        int lastIndex = tokenString.lastIndexOf(delimiter);
        if (lastIndex >= 0) {
          tokenString = tokenString.substring(0, lastIndex);
        }
        break;
      }
    }
    return tokenString;
  }
}
