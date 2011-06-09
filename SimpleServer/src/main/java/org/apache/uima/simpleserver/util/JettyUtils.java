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

package org.apache.uima.simpleserver.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;

import javax.servlet.Servlet;

import org.apache.uima.simpleserver.servlet.SimpleServerServlet;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.servlet.ServletHandler;
import org.mortbay.jetty.servlet.ServletHolder;

public class JettyUtils {

  private static final String uimaServletUrlFile = "/uima";

  public static final Server startJettyServer(String analysisEngineDescriptor, String mappingFile)
      throws Exception {
    // Create Jetty server
    Server server = createServer();
    // Set up UIMA servlet
    SimpleServerServlet uimaServlet = new SimpleServerServlet(true);
    File descriptorFile = new File(analysisEngineDescriptor);
    File specFile = new File(mappingFile);
    uimaServlet.init(descriptorFile, specFile);
    // Add UIMA servlet to jetty
    JettyUtils.addServletWithMapping(server, uimaServlet, uimaServletUrlFile);
    // Start the server
    server.start();
    return server;
  }

  public static final void stopJettyServer(Server server) throws Exception {
    server.stop();
    server.join();
  }

  public static final String getServletUrl(Server server) {
    StringBuffer buf = new StringBuffer();
    buf.append("http://");
    buf.append(JettyUtils.getHost(server));
    buf.append(":");
    buf.append(Integer.toString(JettyUtils.getPort(server)));
    buf.append(uimaServletUrlFile);
    buf.append("?mode=form");
    return buf.toString();
  }

  public static void main(String[] args) {
    Server server = null;
    try {
      server = startJettyServer(args[0], args[1]);
      String servletUrl = getServletUrl(server);
      System.out.println("Use the following URL to access the servlet: " + servletUrl);
      String prompt = "Type 'stop' to exit> ";
      String stop = "stop";
      System.out.println(prompt);
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.equals(stop)) {
          break;
        }
        System.out.println(prompt);
      }
      stopJettyServer(server);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (server != null) {
        try {
          stopJettyServer(server);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static Server createServer() {
    Server server = new Server();
    Connector connector = new SelectChannelConnector();
    final int port = findFreePort();
    if (port <= 0) {
      return null;
    }
    System.out.println("Using port: " + port);
    connector.setPort(port);
    connector.setHost("127.0.0.1");
    server.addConnector(connector);
    server.setStopAtShutdown(true);

    // Set up the servlet handler
    server.setHandler(new ServletHandler());

    return server;
  }

  public static void addServletWithMapping(Server server, Class<?> servlet, String pathSpec) {
    ((ServletHandler) server.getHandler()).addServletWithMapping(servlet, pathSpec);
  }

  public static void addServletWithMapping(Server server, Servlet servlet, String pathSpec) {
    ((ServletHandler) server.getHandler()).addServletWithMapping(new ServletHolder(servlet),
        pathSpec);
  }

  public static String getHost(Server server) {
    return server.getConnectors()[0].getHost();
  }

  public static int getPort(Server server) {
    return server.getConnectors()[0].getPort();
  }

  private static final int findFreePort() {
    int p = -1;
    try {
      // Create a new server socket on an unused port.
      ServerSocket serverSocket = new ServerSocket(0);
      p = serverSocket.getLocalPort();
      serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return p;
  }

}
