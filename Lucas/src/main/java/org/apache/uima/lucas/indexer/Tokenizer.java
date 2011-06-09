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

package org.apache.uima.lucas.indexer;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.WhitespaceTokenizer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.uima.lucas.indexer.mapping.AnnotationDescription;
import org.apache.uima.lucas.indexer.util.TokenStreamStringConcatenator;

public class Tokenizer {
  public static final String TOKENIZER_WHITESPACE = "whitespace";

  public static final String TOKENIZER_CAS = "cas";

  public static final String TOKENIZER_STANDARD = "standard";

  private TokenStreamStringConcatenator tokenStreamStringConcatenator;

  public Tokenizer() {
    tokenStreamStringConcatenator = new TokenStreamStringConcatenator();
  }

  public boolean needsTokenization(AnnotationDescription annotationDescription) {
    String tokenizer = annotationDescription.getTokenizer();
    return tokenizer != null && !tokenizer.equals(TOKENIZER_CAS);
  }

  public TokenStream tokenize(TokenStream tokenStream, AnnotationDescription description)
          throws IOException {
    String tokenizer = description.getTokenizer();
    if (tokenizer != null && !tokenizer.equals(TOKENIZER_CAS)) {
      String tokenStreamAsString = tokenStreamStringConcatenator.tokenStreamToStringWithDelimiter(
              tokenStream, " ");
      StringReader stringReader = new StringReader(tokenStreamAsString);
      if (tokenizer.equals(TOKENIZER_WHITESPACE))
        tokenStream = new WhitespaceTokenizer(stringReader);
      else if (tokenizer.equals(TOKENIZER_STANDARD))
        tokenStream = new StandardTokenizer(stringReader);
    }

    return tokenStream;
  }
}
