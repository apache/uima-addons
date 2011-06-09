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

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.util.Attribute;

import java.io.IOException;
import java.util.*;

/**
 * A TokenStreamMerger merges a {@link java.util.List list} of
 * {@link org.apache.lucene.analysis.TokenStream token streams} by the means of
 * their token offsets. Adapts positionIncrement of tokens if their startOffset
 * is exactly the same.
 */
public class TokenStreamMerger extends TokenStream {

	private class TokenStreamComparator implements Comparator<TokenStream> {

		public int compare(TokenStream stream1, TokenStream stream2) {
			OffsetAttribute attr1 = (OffsetAttribute) stream1
					.getAttribute(OffsetAttribute.class);
			OffsetAttribute attr2 = (OffsetAttribute) stream2
					.getAttribute(OffsetAttribute.class);
			return attr2.startOffset() - attr1.startOffset();

		}
	}

	private Collection<TokenStream> streams;
	
	private int currentOffset;

	private TokenStreamComparator comparator;

	private Stack<TokenStream> sortedStreams;

	private boolean initialized;
	
    private PositionIncrementAttribute posIncAtt;
    
	public TokenStreamMerger(Collection<TokenStream> streams)
			throws IOException {
		super();
		this.streams = streams;
		this.comparator = new TokenStreamComparator();
		currentOffset = -1;
		sortedStreams = new Stack<TokenStream>();
		
		// add all attributes which are contained in any input token stream
		for (TokenStream stream : streams) {
			Iterator<Class<? extends Attribute>> attIt = stream.getAttributeClassesIterator();
			while (attIt.hasNext()) {
				Class<? extends Attribute> attClass = attIt.next();
				addAttribute(attClass);
			}
		}
		// we need a PositionIncrementAttribute in any case
		posIncAtt = (PositionIncrementAttribute)addAttribute(PositionIncrementAttribute.class);
	}

	private void init() throws IOException {
		for (TokenStream stream : streams) {
			stream.reset();
			stream.incrementToken();
			sortedStreams.add(stream);
		}
		rebuildSortedTokens();
		initialized = true;
	}

	public void reset() throws IOException {
		for (TokenStream stream : streams)
			stream.reset();

		currentOffset = -1;
		sortedStreams.clear();
		initialized = false;
	}

	@Override
	public boolean incrementToken() throws IOException {
		if (!initialized)
			init();

		if (sortedStreams.size() == 0)
			return false;

		TokenStream currentTokenStream = sortedStreams.pop();

		restoreState(currentTokenStream.captureState());
		
		OffsetAttribute offsetAttr = (OffsetAttribute)currentTokenStream.getAttribute(OffsetAttribute.class);
		if (offsetAttr.startOffset() == currentOffset)
			posIncAtt.setPositionIncrement(0);
		else
			posIncAtt.setPositionIncrement(1);

		currentOffset = offsetAttr.startOffset();
		
		// proceed the token stream to its next token and resort the stack
		if(currentTokenStream.incrementToken())
			sortedStreams.add(currentTokenStream);
		rebuildSortedTokens();

		return true;
	}

	private void rebuildSortedTokens() throws IOException {
		Collections.sort(sortedStreams, comparator);
	}

}
