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

package org.apache.uima.lucas.indexer.test.util;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;

import java.io.IOException;

public class DummyTokenStream extends TokenStream{
	private int count = 0;
	private String tokenValue;
	private int distance;
	private int number;
	private int begin;
	private int end;
	private int offset;
	private boolean firstToken = true;
	private OffsetAttribute offsetAtt;
	private TermAttribute termAtt;
	
	
	public DummyTokenStream(String tokenValue, int distance, int number, int offset) {
		this.tokenValue = tokenValue;
		this.distance = distance;
		this.number = number;
		this.offset = offset;
		
		
		if( offset > 0 ){
			count = offset;
			begin = count * (tokenValue.length() + 1);
			end = count * (tokenValue.length() + 1) + tokenValue.length();
		}
		else{
			begin = 0; 
			end = tokenValue.length();
		}
		// set initial attributes
		offsetAtt = (OffsetAttribute)addAttribute(OffsetAttribute.class);
		offsetAtt.setOffset(begin, end);
		termAtt = (TermAttribute)addAttribute(TermAttribute.class);
		termAtt.setTermBuffer(tokenValue);
	}
	
	
	
	@Override
	public boolean incrementToken() throws IOException {
		if( number <= count / distance )
			return false;
		// for the first token just return the initial values
		if (firstToken) {
			firstToken = false;
			return true;
		}
		
		count += distance;
		
		begin+= distance* (tokenValue.length() + 1);
		end+= distance* (tokenValue.length() + 1);
		offsetAtt.setOffset(begin, end);

		return begin < (offset * (tokenValue.length() + 1) + (number - 1) * distance * (tokenValue.length() + 1) + tokenValue.length());
	}

	
	public String toString() {
		return "tokenValue: " + tokenValue + ", distance: " + distance + ", number: " + number + ", offset: " + offset;
	}
	
}