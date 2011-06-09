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

package org.apache.uima.simpleserver.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.uima.simpleserver.servlet.SimpleServerServlet;
import org.apache.uima.simpleserver.util.HttpClientUtils;
import org.apache.uima.simpleserver.util.JettyUtils;
import org.apache.uima.test.junit_extension.JUnitExtension;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mortbay.jetty.Server;

public class ServerTest {

  public static class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
      response.setContentType("text/html");
      response.setCharacterEncoding(SimpleServerServlet.DEFAULT_CODE_PAGE);
      response.getWriter().println("<h1>Hello SimpleServlet</h1>");
      response.setStatus(HttpServletResponse.SC_OK);
    }
  }

  private static Server server = null;

  @BeforeClass
  public static void setUp() {
    // Set up the server
    server = JettyUtils.createServer();
    assertNotNull(server);

    // Add servlets
    JettyUtils.addServletWithMapping(server, HelloServlet.class, "/hello");

    // Set up UIMA servlet
    SimpleServerServlet uimaServlet = new SimpleServerServlet(true);
    File descriptorFile = JUnitExtension.getFile("desc/simpleServerTestDescriptor.xml");
    assertTrue(descriptorFile.exists());
    File specFile = JUnitExtension.getFile("serverspec/spec01.xml");
    assertTrue(specFile.exists());
    try {
      uimaServlet.init(descriptorFile, specFile);
    } catch (ServletException e1) {
      e1.printStackTrace();
      assertTrue(false);
    }
    JettyUtils.addServletWithMapping(server, uimaServlet, "/uima");
    
    // Set up config-less UIMA servlet
    SimpleServerServlet uimaNoConfigServlet = new SimpleServerServlet(true);
    try {
      uimaNoConfigServlet.init(descriptorFile, null);
    } catch (ServletException e1) {
      e1.printStackTrace();
      assertTrue(false);
    }
    JettyUtils.addServletWithMapping(server, uimaNoConfigServlet, "/uima-no-config");

    // Start the server
    try {
      server.start();
    } catch (Exception e) {
      e.printStackTrace();
      assertTrue("Exception running Jetty", false);
    }
  }

  /**
   * Test connection to Jetty. If this test fails, none of the others will go through. Start
   * investigating here.
   */
  @Test
  public void test() {
    try {
      HttpResponse response = HttpClientUtils.callGet(JettyUtils.getHost(server), JettyUtils
          .getPort(server), "/hello");
      assertTrue(response.getStatusLine().getStatusCode() == HttpServletResponse.SC_OK);
      System.out.println(HttpClientUtils.getResponseContent(response));
    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
    }
  }

  @Test
  public void test1() {
    try {
      HttpResponse response = HttpClientUtils.callGet(JettyUtils.getHost(server), JettyUtils
          .getPort(server), "/uima?text=foo%20bar");
      assertTrue(response.getStatusLine().getStatusCode() == HttpServletResponse.SC_OK);
      System.out.println(HttpClientUtils.getResponseContent(response));
    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
    }
  }

  @Test
  public void test2() {
    try {
      HttpResponse response = HttpClientUtils.callGet(JettyUtils.getHost(server), JettyUtils
          .getPort(server), "/uima-no-config?text=foo%20bar");
      assertTrue(response.getStatusLine().getStatusCode() == HttpServletResponse.SC_OK);
      System.out.println(HttpClientUtils.getResponseContent(response));
    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
    }
  }

  @AfterClass
  public static void tearDown() {
    try {
      server.stop();
    } catch (Exception e) {
      e.printStackTrace();
      assertTrue("Exception shutting down Jetty", false);
    }
  }

}
