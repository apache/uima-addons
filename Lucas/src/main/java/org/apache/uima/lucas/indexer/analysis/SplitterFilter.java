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
import java.util.Iterator;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;

import com.google.common.collect.Iterators;

public class SplitterFilter extends TokenFilter {

  private String splitString;

  private Iterator<String> splitIterator;

  private Token currentToken;

  public SplitterFilter(TokenStream input, String splitString) {
    super(input);
    this.splitString = splitString;
    this.splitIterator = Iterators.emptyIterator();
  }

  @Override
  public Token next(Token token) throws IOException {

    if (!splitIterator.hasNext()) {
      currentToken = input.next(token);
      if (currentToken == null)
        return null;

      String tokenText = new String(currentToken.termBuffer(), 0, currentToken.termLength());
      String[] splitts = tokenText.split(splitString);
      splitIterator = Iterators.forArray(splitts, 0, splitts.length);
    }

    if (!splitIterator.hasNext())
      return null;

    token.setStartOffset(currentToken.startOffset());
    token.setEndOffset(currentToken.endOffset());
    char[] termBuffer = splitIterator.next().toCharArray();
    token.setTermBuffer(termBuffer, 0, termBuffer.length);

    return token;
  }

  @Override
  public Token next() throws IOException {
    return next(new Token());
  }

  public String getSplitString() {
    return splitString;
  }

}
