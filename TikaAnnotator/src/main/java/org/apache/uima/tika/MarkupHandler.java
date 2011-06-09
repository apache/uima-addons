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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/*******************************************************************************
 * SAX Handler which gets events from the Tika parser events and create UIMA
 * annotations accordingly.
 * 
 ******************************************************************************/

public class MarkupHandler implements ContentHandler {

	private StringBuffer textBuffer;

	private List<ProtoAnnotation> protoAnnotations;

	private LinkedList<ProtoAnnotation> startedAnnotations;

	public MarkupHandler() {
		textBuffer = new StringBuffer();
		protoAnnotations = new LinkedList<ProtoAnnotation>();
		startedAnnotations = new LinkedList<ProtoAnnotation>();
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// MS doc spits out funny characters
		// we replace them with ' ' 
		for (int c = start;c<start+length;c++){
			if (!Character.isISOControl(ch[c])) continue;
			if (Character.isWhitespace(ch[c])) continue;	
			ch[c] = ' ';
		}
		
		// store the characters in the textBuffer
		textBuffer.append(ch, start, length);
	}

	public void startDocument() throws SAXException {
	}

	public void endDocument() throws SAXException {
		// there should be no annotation left at this stage
		if (startedAnnotations.size() != 0) {
			// TODO log + error message
		}
	}

	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		int startOffset = textBuffer.length();
		
		ProtoAnnotation proto = new ProtoAnnotation(uri,localName, qName,atts, startOffset);
		this.startedAnnotations.addLast(proto);
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		int endOffset = textBuffer.length();
		
		// try to get the corresponding annotation
		// we start from the last temporary 
		// and go up the stack
		Iterator<ProtoAnnotation> iter = startedAnnotations.iterator();
		ProtoAnnotation startedAnnot = null;
		while (iter.hasNext()){
			ProtoAnnotation temp = iter.next();
			if (temp.getLocalName().equals(localName)){
				startedAnnot = temp;
				break;
			}
		}
		// found something?
		if (startedAnnot==null){
			// TODO log etc...
			return;
		}
		
		startedAnnot.setEnd(endOffset);
		startedAnnotations.remove(startedAnnot);
		protoAnnotations.add(startedAnnot);
		
		// add a \n otherwise we get everything 
		// on a single line
		textBuffer.append("\n");
	}

	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
	}

	// the following methods are simply ignored

	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
	}

	public void endPrefixMapping(String prefix) throws SAXException {
	}

	public void setDocumentLocator(Locator locator) {
	}

	public void skippedEntity(String name) throws SAXException {
	}

	public void processingInstruction(String target, String data)
			throws SAXException {
	}

	public void populateCAS(CAS cas){
		// set the text 
		cas.setDocumentText(this.textBuffer.toString());
		
		Type markupType = cas.getTypeSystem().getType("org.apache.uima.tika.MarkupAnnotation");
		Type attributeType = cas.getTypeSystem().getType("org.apache.uima.tika.AttributeFS");
		if (attributeType == null) {
		    throw new RuntimeException("Can't find type org.apache.uima.tika.AttributeFS");
		}
		
		JCas jcas;
		try {
			jcas = cas.getJCas();
		} catch (CASException e) {
			throw new RuntimeException(e);
		}
		
		// now convert the proto annotations into real ones
		for (ProtoAnnotation proto : protoAnnotations) {
			MarkupAnnotation markup = new MarkupAnnotation(jcas);
			markup.setBegin(proto.getStart());
			markup.setEnd(proto.getEnd());
			// generate attributes
			Attributes protoAttributes = proto.getAtts();
			FSArray attribs = (FSArray) cas.createArrayFS(protoAttributes.getLength());
			for (int index=0; index< protoAttributes.getLength();index++){
				org.apache.uima.tika.AttributeFS afs = (AttributeFS) cas.createFS(attributeType);
				afs.setLocalName(protoAttributes.getLocalName(index));
				afs.setQualifiedName(protoAttributes.getQName(index));
				afs.setUri(protoAttributes.getURI(index));
				afs.setValue(protoAttributes.getValue(index));
				afs.addToIndexes();
				attribs.set(index, afs);
			}
			markup.setAttributes(attribs);
			markup.setUri(proto.getUri());
			markup.setName(proto.getLocalName());
			markup.setQualifiedName(proto.getQName());
			markup.addToIndexes();
		}
	}
	

}
