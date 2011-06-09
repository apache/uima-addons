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

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;

public class AdditionFilter extends TokenFilter {

  public static final Integer PREFIX = 0;

  public static final Integer POSTFIX = 1;

  private Integer position;

  private String addition;

  public AdditionFilter(TokenStream input, String addition, Integer position) {
    super(input);
    this.position = position;
    this.addition = addition;
  }

  @Override
  public Token next(Token aToken) throws IOException {
    Token token = input.next(aToken);
    if (token == null)
      return null;

    String tokenText = new String(token.termBuffer(), 0, token.termLength());
    if (position.equals(PREFIX))
      tokenText = addition.concat(tokenText);
    else
      tokenText = tokenText.concat(addition);

    token.setTermBuffer(tokenText.toCharArray(), 0, tokenText.length());

    return token;
  }

  public Integer getPosition() {
    return position;
  }

  public String getAddition() {
    return addition;
  }
}
