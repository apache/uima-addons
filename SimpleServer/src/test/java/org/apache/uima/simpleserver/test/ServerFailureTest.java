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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.uima.simpleserver.servlet.SimpleServerServlet;
import org.apache.uima.simpleserver.util.HttpClientUtils;
import org.apache.uima.simpleserver.util.JettyUtils;
import org.junit.Test;
import org.mortbay.jetty.Server;

/**
 * Test server errors.
 */
public class ServerFailureTest {

  @Test
  public void uimaServiceNotInitialized() {
    Server server = JettyUtils.createServer();
    assertNotNull(server);
    String pathSpec = "/notinitialized";
    // Create a simple service but don't initialize it (should throw http error 500 when called)
    JettyUtils.addServletWithMapping(server, new SimpleServerServlet(true), pathSpec);
    try {
      server.start();
    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
    }
    try {
    HttpResponse response = HttpClientUtils.callGet(JettyUtils.getHost(server), JettyUtils.getPort(server), pathSpec);
    assertTrue("Expected http return code 500 (internal server error)", (response.getStatusLine()
        .getStatusCode() == HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
    System.out.println(HttpClientUtils.getResponseContent(response));
    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
    }
    try {
      server.stop();
      server.join();
    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
    }
  }

}
