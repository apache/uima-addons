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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.uima.simpleserver.servlet.SimpleServerServlet;

public class HttpClientUtils {

  public static HttpResponse callGet(String host, int port, String file) throws IOException,
      URISyntaxException, HttpException {
    HttpClient httpClient = new DefaultHttpClient();
    HttpGet method = null;
    URL url = null;
    url = new URL("http", host, port, file);
    method = new HttpGet(url.toString());
    HttpResponse response = null;
    try {
    response = httpClient.execute(method);
    } catch (InterruptedException e) {
      // If we get here, something's seriously wrong
      e.printStackTrace();
      return null;
    }
    return response;
  }

  public static String getResponseContent(HttpResponse response) throws IOException {
    try {
      Reader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),
          SimpleServerServlet.DEFAULT_CODE_PAGE));
      char[] chars = new char[1024];
      int len = 0;
      StringBuffer buf = new StringBuffer();
      while ((len = reader.read(chars)) >= 0) {
        buf.append(chars, 0, len);
      }
      return buf.toString();
    } catch (UnsupportedEncodingException e) {
      // Can't get here
      e.printStackTrace();
      return null;
    } catch (IllegalStateException e) {
      // Shouldn't get here ;-)
      e.printStackTrace();
      return null;
    }
  }

}
