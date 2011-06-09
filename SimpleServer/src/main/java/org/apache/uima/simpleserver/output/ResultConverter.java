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

package org.apache.uima.simpleserver.output;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/*
 * This class contains static methods responsible for the creation
 * of particular view of the analysis result 
 */
public class ResultConverter {

  /*
   * Returns a string containing an XML document with analysis results, with tag
   * names and attribute names as specified by the current Result Specification
   * 
   * 
   * Straight-forward method, no other methods are used.
   */
  public static String getXMLString(Result result) {
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      DOMImplementation impl = builder.getDOMImplementation();

      Document document = impl.createDocument(null, null, null);
      Element root = document.createElement("result");
      document.appendChild(root);

      for (ResultEntry resultEntry : result.getResultEntries()) {
        Element element = document.createElement(resultEntry.getEntryName());
        root.appendChild(element);
        for (String attributeName : resultEntry.getAttributeNames()) {
          String attributeValue = resultEntry.getAttriuteValue(attributeName);
          element.setAttribute(attributeName, attributeValue);
        }
        // TODO covered text - DONE
        if (resultEntry.getCoveredText() != null) {
          Node textNode = document.createTextNode(resultEntry.getCoveredText());
          element.appendChild(textNode);
        }
      }

      DOMSource source = new DOMSource(document);
      TransformerFactory tFactory = TransformerFactory.newInstance();
      Transformer transformer = tFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.METHOD, "xml");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty(OutputKeys.STANDALONE, "no");

      StringWriter stringWriter = new StringWriter();
      StreamResult streamResult = new StreamResult(stringWriter);
      transformer.transform(source, streamResult);

      return stringWriter.toString().replace(" standalone=\"no\"", "");
    } catch (Throwable t) {
      throw new RuntimeException("XML output failed", t);
    }
  }

  /*
   * Delegates the call to the InlineXMLGenerator class, where the inline XML is
   * constructed from the given Result object
   */
  public static String getInlineXML(Result result) {
    try {
      return InlineXMLGenerator.getInlineXML(result);
    } catch (Throwable t) {
      throw new RuntimeException("Tagged text output failed", t);
    }
  }

}
