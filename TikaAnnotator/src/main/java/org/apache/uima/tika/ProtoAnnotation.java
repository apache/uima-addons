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

package org.apache.uima.tika;

import org.xml.sax.Attributes;

/** 
 * Neutral representation of an annotation which can be converted into a proper GATE or UIMA annotation later
 ***/

public class ProtoAnnotation {

	private String uri;
	private String localName;
	private String qName;
	private Attributes atts;
	private int start;
	private int end;
	
	public ProtoAnnotation(String uri, String localName, String qName, Attributes atts, int start) {
		super();
		this.uri = uri;
		this.localName = localName;
		this.qName = qName;
		this.atts = atts;
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public Attributes getAtts() {
		return atts;
	}

	public String getLocalName() {
		return localName;
	}

	public String getQName() {
		return qName;
	}

	public int getStart() {
		return start;
	}

	public String getUri() {
		return uri;
	}
	
	
	
}
