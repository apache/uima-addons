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
package org.apache.uima.annotator.calais;

import java.util.ArrayList;
import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RDFSaxHandler extends DefaultHandler {

    DescriptionElement currentDesc = null;
    private boolean enableOffset = false;
    private boolean enableLength = false;
    private boolean enableDocument = false;
    private Offset offset;
    
    private HashMap<String, DescriptionElement> elements;
    private ArrayList<DescriptionElement> subjectMap;
  
   public RDFSaxHandler(HashMap<String, DescriptionElement> elements, ArrayList<DescriptionElement> subjectMap, Offset offset) {
     this.elements = elements;
     this.subjectMap = subjectMap;
     this.offset = offset;
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
    *      java.lang.String, java.lang.String)
    */
   public void endElement(String uri, String localName, String qName)
         throws SAXException {
     
     if(qName.equals("rdf:Description")) {
       this.elements.put(this.currentDesc.getAboutURL(), this.currentDesc);
       if(this.currentDesc.getSubjectURL() != null) {
         this.subjectMap.add(this.currentDesc);
       }
     }

   }

   /*
    * (non-Javadoc)
    * 
    * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
    *      java.lang.String, java.lang.String, org.xml.sax.Attributes)
    */
   public void startElement(String uri, String localName, String qName,
         Attributes attributes) throws SAXException {

     if(qName.equals("rdf:Description")) {
       this.currentDesc = new DescriptionElement(); 
       // get "about" attribute value
       this.currentDesc.setAboutURL(attributes.getValue("rdf:about"));
     } else if(qName.equals("rdf:type")) {
       // get "resource" attribute value
       this.currentDesc.setTypeURL(attributes.getValue("rdf:resource"));
     } else if(qName.equals("c:subject")) {
       // get "resource" attribute value
       this.currentDesc.setSubjectURL(attributes.getValue("rdf:resource"));
     } else if(qName.equals("c:offset")) {
         this.enableOffset = true;
     } else if(qName.equals("c:length")) {
         this.enableLength = true;
     } else if(qName.equals("rdf:RDF")) {
         this.enableDocument = true;
     }
   }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    if(this.enableOffset) {
        this.currentDesc.setOffset(Integer.parseInt(new String(ch, start, length)));
        this.enableOffset = false;
    } else if(this.enableLength) {
      this.currentDesc.setLength(Integer.parseInt(new String(ch, start, length)));
      this.enableLength = false;
    } else if(this.enableDocument) {
      String documentText = new String(ch, start, length);
      this.offset.setOffset(documentText.toLowerCase().indexOf("<body>") + 6);
      this.enableDocument = false;
    }

    
  }
   
   
}
