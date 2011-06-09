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
import java.util.Iterator;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;

/**
 * A TokenStreamStringConcatenator takes a {@link java.util.Collection Collection} of
 * {@link org.apache.lucene.analysis.TokenStream tokenstreams} and concats them.
 */
public class TokenStreamConcatenator extends TokenStream {

  private Collection<TokenStream> tokenStreams;

  private Iterator<TokenStream> tokenStreamIterator;

  private TokenStream currentTokenStream;

  public TokenStreamConcatenator(Collection<TokenStream> tokenStreams) {
    super();
    this.tokenStreams = tokenStreams;
    this.tokenStreamIterator = tokenStreams.iterator();

    if (tokenStreamIterator.hasNext())
      currentTokenStream = tokenStreamIterator.next();
  }

  @Override
  public Token next(Token inputToken) throws IOException {
    if (currentTokenStream == null)
      if (tokenStreamIterator.hasNext())
        currentTokenStream = tokenStreamIterator.next();
      else
        return null;

    Token nextToken = currentTokenStream.next(inputToken);
    while (nextToken == null) {
      if (tokenStreamIterator.hasNext()) {
        currentTokenStream = tokenStreamIterator.next();
        nextToken = currentTokenStream.next(inputToken);
      } else
        return null;
    }

    return nextToken;
  }

  public void reset() throws IOException {
    for (TokenStream tokenStream : tokenStreams)
      tokenStream.reset();

    tokenStreamIterator = tokenStreams.iterator();

    if (tokenStreamIterator.hasNext())
      currentTokenStream = tokenStreamIterator.next();
    else
      currentTokenStream = null;
  }

}
