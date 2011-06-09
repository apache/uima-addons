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


package org.apache.uima.simpleserver.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ListServices extends HttpServlet {

  private static final long serialVersionUID = 804273796737974862L;

  public static final String CONFIG_FILE_NAME = "list.properties";

  private Properties properties = new Properties();

  private File myDir = null;

  private Logger logger = Logger.getLogger("org.apache.uima.uimaserver.servlet.ListServices");

  private BufferedReader readUrl(String urlStr) throws IOException {
    urlStr += "?mode=xmldesc";
    URL url = new URL(urlStr);
    URLConnection connection = url.openConnection();
    return new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    String server = request.getScheme() + "://" + request.getServerName() + ":"
        + request.getServerPort();

    PrintWriter writer = null;
    try {
      writer = response.getWriter();
    } catch (IOException e1) {
      this.logger.log(Level.SEVERE, "Could not obtain response writer, aborting.");
      e1.printStackTrace();
      return;
    }

    if (this.myDir == null) {
      this.myDir = new File(getServletContext().getRealPath(""));
      File iniFile = new File(this.myDir, CONFIG_FILE_NAME);
      try {
        this.properties.load(new FileInputStream(iniFile));
      } catch (IOException e) {
        this.logger.log(Level.SEVERE, "Could not load config file: " + iniFile.getAbsolutePath());
        e.printStackTrace();
      }
    }

    String mode = request.getParameter("mode");
    if ((mode != null) && mode.equals("xml")) {
      try {
        writer.print(xmlToString(produceXmlList(server)));
        writer.close();
      } finally {
        writer.close();
      }
    } else {

      Document document = produceXmlList(server);
      Element root = document.getDocumentElement();
      NodeList nlist = root.getElementsByTagName("service");

      String html = "<html><head><title>List of services</title></head><body>"
          + "<h2>List of UIMA services available on " + server + "</h2>" + "<ul>";

      int n = nlist.getLength();

      for (int i = 0; i < n; i++) {
        String url = "";
        try {
          Element serviceElement = (Element) nlist.item(i);
          url = serviceElement.getAttribute("url");
        } catch (Exception e) {
          e.printStackTrace();
        }

        String desc = "<i>No service description available</i>";

        try {
          Element serviceElement = (Element) nlist.item(i);
          Element uimaService = (Element) (serviceElement.getElementsByTagName("uimaService")
              .item(0));
          String tmpDesc = uimaService.getAttribute("shortDescription");
          if ((tmpDesc != null) && tmpDesc.length() > 0) {
            desc = tmpDesc;
          }
        } catch (Exception e) {
          e.printStackTrace();
        }

        html += "<li/> <b>" + desc + "</b>" + "<br/> URL: " + url
            + " </br> For further details, please see the <a href=\"" + url + "?mode=description"
            + "\">detailed service information</a> or " + "<a href=\"" + url + "?mode=form"
            + "\">try out</a> this service. <a href=\"" + url + "?mode=xmldesc"
            + "\">Description</a> " + "of this service in XML format is also available. <br/>";

      }

      html += "</ul></body></html>";
      writer.print(html);
      writer.close();
    }
  }

  private Document produceXmlList(String thisServer) {
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      DOMImplementation impl = builder.getDOMImplementation();

      Document document = impl.createDocument(null, null, null);
      Element root = document.createElement("serviceList");
      document.appendChild(root);

      for (int i = 1;; i++) {
        String url = this.properties.getProperty("url" + i);
        if (url == null) {
          break;
        }
        if (!url.startsWith("http:")) {
          url = thisServer + url;
        }

        Element service = document.createElement("service");
        root.appendChild(service);
        service.setAttribute("url", url);

        try {
          BufferedReader reader = readUrl(url);
          InputSource source = new InputSource(reader);
          Document readDocument = builder.parse(source);

          Node node = readDocument.getDocumentElement().cloneNode(true);
          service.appendChild(document.adoptNode(node));
        } catch (IOException e) {
          this.logger.log(Level.WARNING, "Could not retrieve information from service: " + url);
          e.printStackTrace();
        }

      }

      return document;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  public String xmlToString(Document document) {
    try {
      DOMSource source = new DOMSource(document);
      TransformerFactory tFactory = TransformerFactory.newInstance();
      Transformer transformer = tFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.METHOD, "xml");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");

      StringWriter stringWriter = new StringWriter();
      StreamResult streamResult = new StreamResult(stringWriter);
      transformer.transform(source, streamResult);

      return stringWriter.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "";
  }

}

