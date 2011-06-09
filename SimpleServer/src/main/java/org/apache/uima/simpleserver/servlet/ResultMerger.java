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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.uima.simpleserver.config.ServerSpec;
import org.apache.uima.simpleserver.config.impl.ServerSpecImpl;
import org.apache.uima.simpleserver.output.ResultEntry;
import org.apache.uima.simpleserver.output.impl.ResultEntryImpl;
import org.apache.uima.simpleserver.output.impl.ResultImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/*
 * Implementation of a result-merging service.
 * The task of this service is to send multiple queries with the same
 * text to different UIMA Servlets and to aggregate the results of these
 * services. 
 * 
 * This service reacts to POST queries in the same way as other 
 * UIMA services - it receives the parameters "text", "lang" and "mode" 
 * and sends the requests simultaneously to the URLs received as 
 * parameters "url1", "url2", etc. 
 * 
 * TODO: does not currently work, needs to be fixed.
 * 
 */
public class ResultMerger extends SimpleServerServlet {

  private static final long serialVersionUID = -2326819932388842032L;

  /*
   * the description for this service is not produced automatically, like for other UIMA services,
   * so the method which constructs the HTML page is overridden
   */
  @Override
  public String constructHtmlDescription(String servletURL) {
    return getHtmlDescription(servletURL);
  }

  private String getHtmlDescription(String urlstring) {
    String html = "<html>"
        + "<head>"
        + "<title>Description of the XML Result Merger service</title>"
        + "</head>"
        + "<body>"
        + "<h2>Service Result Merger</h2>"
        + "<p>This service merges the results of other simple UIMA services"
        + "and returns the combined result in the specified format. </p>"
        + "<h3>Usage</h3>"
        + "In odrer to use this service, a POST-request should be sent to the server with the following URL:"
        + "<pre>"
        + urlstring
        + "</pre>"
        + "The following request parameters are expected:"
        + "<ul>"
        + "<li/><b>text</b> - the value of this parameter is the text to analyze. Expected "
        + "encoding is UTF-8. This parameter must always be set."
        + "<li/><b>mode</b> - the value of this parameter is either "
        + "&quot;<b>xml</b>&quot; or &quot;<b>inline</b>&quot;."
//        + " or &quot;<b>csv</b>&quot;. "  // Jira 1795
        + "This parameter allows you to define, which view of the result should be returned. "
        + "&quot;<b>xml</b>&quot; means to output the result as a XML-document containing a list of found entities, and"
        + "&quot;<b>inline</b>&quot; returns inline-xml containing the analyzed text in which all found entities are"
        + " represented by tags." 
//        + ", and &quot;<b>csv</b>&quot; returns the found entities"  //Jira 1795
//        + " in a comma-separated list. " 
        + "If this parameter is not set, xml output will be produced."
        + ""
        + "<li/><b>url1</b>, <b>url2</b>, <b>url3</b>, etc. - the values of these parameters are the "
        + "URLs of other simple UIMA services which are to be invoked."
        + "</ul>"
        + ""
        + "<h3>Result</h3>"
        + "The returned result will be merged from results of the specified services. "
        + "For output details see the description of the services which you use."
        + ""
        + ""
        + ""
        + "" + "";
    return html;
  }

  /*
   * we don't need an instance ofthe Server class to process the queries, but still we need to set a
   * valid instance of ResultSpecification in order to be able to deliver a valid XML service
   * description
   */
  @Override
  public boolean initServer() {
    ServerSpec res = new ServerSpecImpl("Result Merger",
        "Result Merger - merge results of other UIMA services");
    // res.setTypeSpecs(new ArrayList<TypeMap>());
    // TODO: fix this
    // this.server.setResultSpec(res);
    return true;
  }

  /*
   * We cannot just use the implementation of this method from the superclass, since we need to do
   * something special: we need to send a number of requests to other services and to aggregate the
   * result in one Result object
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    List<ResultEntry> resultEntries = new ArrayList<ResultEntry>();
    // get text
    request.setCharacterEncoding("UTF-8");
    String text = request.getParameter("text");
    String mode = request.getParameter("mode");
    String lang = request.getParameter("lang");
    if ((lang == null) || (lang.equals(""))) {
      lang = "en";
    }

    // collection of threads that will be launched simultaneously,
    // one thread for every URL
    Collection<ServiceReaderThread> threads = new LinkedList<ServiceReaderThread>();

    // iterate over given urls
    for (int i = 1;; i++) {
      String urlStr = request.getParameter("url" + i);
      if (urlStr == null) {
        // if the next url parameter is not defined, we stop
        break;
      }
      // a thread gets all information it needs to send the request
      // to its service: url, text and lang
      ServiceReaderThread thread = new ServiceReaderThread(urlStr, text, lang);
      threads.add(thread);
    }
    for (Thread thread : threads) {
      thread.start();
    }
    // now we will have to wait until every thread finishes its work
    for (Thread thread : threads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    // add the result collections provided by every thread
    // to one big common collection
    for (ServiceReaderThread thread : threads) {
      resultEntries.addAll(thread.getResultEntries());
    }

    response.setCharacterEncoding("UTF-8");
    response.getWriter().print(this.transformResult(
    // create a Result object which contains all of the
        // result entries created by all threads
        new ResultImpl(resultEntries, text), mode));
    response.getWriter().close();
  }

  @Override
  public String getHtmlForm(String serverUrl) {
    String str = "<html>" + "<head>" + "<title> Sorry, tryout not supported </title>"
        + "<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\" >" + "</head>"
        + "<body>" + "<h2>Sorry, the tryout form of " + serverUrl
        + " is not available. Tryout is not supported for this service. </h2>" + "</body>"
        + "</html>";
    return str;
  }
}

/*
 * one thread which is initialised with url, lang and text
 */
class ServiceReaderThread extends Thread {
  // collection that is used to store the results of the query
  private Collection<ResultEntry> resultEntries = null;

  private String url = null;

  private String text = null;

  private String lang = null;

  public ServiceReaderThread(String url, String text, String lang) {
    this.url = url;
    this.text = text;
    this.lang = lang;
    this.resultEntries = new LinkedList<ResultEntry>();
  }

  // send a request to the given URL, get the result, parse the result
  // and add the result entries to the collection
  @Override
  public void run() {
    try {
      DocumentBuilderFactory factory = null;
      DocumentBuilder builder = null;
      try {
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
      } catch (Throwable t) {
        t.printStackTrace();
      }
      // read the result document from the url
      BufferedReader reader = readUrl(this.url, this.text, this.lang);
      InputSource source = new InputSource(reader);
      // parse the result
      Document document = builder.parse(source);
      // insert the results in the collection
      insertResults(document, this.resultEntries);
    } catch (Throwable t) {
      t.printStackTrace();
    }
  }

  // sends a POST query to the given url and reads the result
  private BufferedReader readUrl(String urlStr, String text1, String lang1) throws IOException {
    String parameter = URLEncoder.encode("text", "UTF-8") + "=" + URLEncoder.encode(text1, "UTF-8")
        + "&mode=xml" + "&lang=" + lang1;

    URL url1 = new URL(urlStr);
    URLConnection connection = url1.openConnection();
    connection.setDoOutput(true);
    OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
    writer.write(parameter);
    writer.close();
    return new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
  }

  public Collection<ResultEntry> getResultEntries() {
    return this.resultEntries;
  }

  // goes through the result document and constructs a
  // ResultEntry object for every result tag
  private void insertResults(Document document, Collection<ResultEntry> resultEntries1) {
    Element root = document.getDocumentElement();
    NodeList resultElements = root.getChildNodes();
    int l = resultElements.getLength();
    for (int i = 0; i < l; i++) {
      // take every element in the document
      if (resultElements.item(i) instanceof Element) {
        Element element = (Element) resultElements.item(i);
        // create a ResultEntry object
        ResultEntryImpl entry = new ResultEntryImpl(element.getNodeName(), 0, 0);
        // fill the ResultEntry with data read from the element
        NamedNodeMap attributes = element.getAttributes();
        int k = attributes.getLength();
        for (int x = 0; x < k; x++) {
          Node attribute = attributes.item(x);
          if ((attribute.getNodeName() != null) && (attribute.getNodeValue() != null)) {
            entry.setAttributeValue(attribute.getNodeName(), attribute.getNodeValue());
          }
        }
        // TODO covered text - DONE

        entry.setCoveredText(element.getTextContent());

        resultEntries1.add(entry);
      }
    }
  }
}
