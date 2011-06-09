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
import java.util.Collection;
import java.util.HashSet;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;

/**
 * A UniqueFilter filters multiple occurrences of {@link org.apache.lucene.analysis.Token tokens}
 * tokens with the same token text and removes them.
 */
public class UniqueFilter extends TokenFilter {

  private TokenStream input;

  private Collection<String> termTexts;

  private Token currentToken;

  public UniqueFilter(TokenStream input) {
    super(input);
    this.input = input;
    termTexts = new HashSet<String>();
  }

  @Override
  public Token next(Token inputToken) throws IOException {
    currentToken = input.next(inputToken);
    if (currentToken == null)
      return null;

    String termText = new String(currentToken.termBuffer(), 0, currentToken.termLength());
    while (termTexts.contains(termText)) {
      currentToken = input.next(inputToken);
      if (currentToken != null)
        termText = new String(currentToken.termBuffer(), 0, currentToken.termLength());
      else
        termText = null;
    }

    if (currentToken == null)
      return null;

    termTexts.add(termText);
    return currentToken;
  }

  @Override
  public void reset() throws IOException {
    input.reset();
    termTexts.clear();
  }

}
