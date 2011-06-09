/**
 * 	Licensed to the Apache Software Foundation (ASF) under one
 * 	or more contributor license agreements.  See the NOTICE file
 * 	distributed with this work for additional information
 * 	regarding copyright ownership.  The ASF licenses this file
 * 	to you under the Apache License, Version 2.0 (the
 * 	"License"); you may not use this file except in compliance
 * 	with the License.  You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * 	Unless required by applicable law or agreed to in writing,
 * 	software distributed under the License is distributed on an
 * 	"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * 	KIND, either express or implied.  See the License for the
 * 	specific language governing permissions and limitations
 * 	under the License.
 */
package org.apache.uima.alchemy.digester.json;

import java.io.IOException;

import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

/**
 * JSON parser that throws XML events.
 * 
 */
final class JsonXMLReader implements XMLReader {

  private final String customRootElementName;

  private ContentHandler contentHandler;

  public ContentHandler getContentHandler() {
    return contentHandler;
  }

  public void setContentHandler(ContentHandler contentHandler) {
    this.contentHandler = contentHandler;
  }

  public EntityResolver getEntityResolver() {
    return entityResolver;
  }

  public void setEntityResolver(EntityResolver entityResolver) {
    this.entityResolver = entityResolver;
  }

  public ErrorHandler getErrorHandler() {
    return errorHandler;
  }

  public void setErrorHandler(ErrorHandler errorHandler) {
    this.errorHandler = errorHandler;
  }

  private EntityResolver entityResolver;

  private ErrorHandler errorHandler;

  private DTDHandler dtdHandler;

  public JsonXMLReader(final String customRootElementName) {
    this.customRootElementName = customRootElementName;
  }

  /**
   * {@inheritDoc}
   */
  public boolean getFeature(final String name) throws SAXNotRecognizedException,
          SAXNotSupportedException {
    throw new SAXNotSupportedException();
  }

  /**
   * {@inheritDoc}
   */
  public Object getProperty(final String name) throws SAXNotRecognizedException,
          SAXNotSupportedException {
    throw new SAXNotSupportedException();
  }

  /**
   * {@inheritDoc}
   */
  public DTDHandler getDTDHandler() {
    return this.dtdHandler;
  }

  /**
   * {@inheritDoc}
   */
  public void setDTDHandler(DTDHandler handler) {
    this.dtdHandler = handler;
  }

  /**
   * {@inheritDoc}
   */
  public void parse(final InputSource input) throws IOException, SAXException {
    JsonParser jsonParser = new JsonParser(input.getByteStream());
    if (this.customRootElementName != null && this.customRootElementName.length() > 0) {
      jsonParser.setRootElementName(this.customRootElementName);
    }
    jsonParser.setContentHandler(this.contentHandler);
    try {
      jsonParser.json();
    } catch (ParseException e) {
      throw new SAXException("An error occurred while parsing JSON", e);
    }
  }

  /**
   * {@inheritDoc}
   */
  public void parse(final String systemId) throws IOException, SAXException {
    // not used
  }

  /**
   * {@inheritDoc}
   */
  public void setFeature(final String name, final boolean value) throws SAXNotRecognizedException,
          SAXNotSupportedException {
    throw new SAXNotSupportedException();
  }

  /**
   * {@inheritDoc}
   */
  public void setProperty(final String name, final Object value) throws SAXNotRecognizedException,
          SAXNotSupportedException {
    throw new SAXNotSupportedException();
  }

}
