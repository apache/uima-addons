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

public class PositionFilter extends TokenFilter {

	public final static Integer FIRST_POSITION = 0;

	public final static Integer LAST_POSITION = 1;

	private TokenStream input;

	private Token token;

	private Integer position;

	public PositionFilter(TokenStream input, Integer position) {
		super(input);
		this.input = input;
		this.position = position;
	}

	@Override
	public Token next(Token nextToken) throws IOException {
		Token newToken = input.next(nextToken);

		if (position.equals(FIRST_POSITION)) {
			if (token != null)
				return null;
			else {
				token = newToken;
				return newToken;
			}
		} else if (position.equals(LAST_POSITION)) {
			Token lastToken = null;
			while (newToken != null) {
				lastToken = newToken;
				newToken = input.next(nextToken);
			}
			return lastToken;
		} else
			return newToken;
	}

	public Integer getPosition() {
		return position;
	}

}
