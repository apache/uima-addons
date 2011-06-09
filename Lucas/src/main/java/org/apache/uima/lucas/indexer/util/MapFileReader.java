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

package org.apache.uima.lucas.indexer.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class MapFileReader extends Reader {

  private BufferedReader reader;

  public MapFileReader(BufferedReader reader) {
    super();
    this.reader = reader;
  }

  public Map<String, String> readMap() throws IOException {
    Map<String, String> mapping = new HashMap<String, String>();

    String line = reader.readLine();
    while (line != null) {
      String[] keyValue = line.split("=");
      String key = keyValue[0];
      String value = keyValue[1];

      mapping.put(key, value);
      line = reader.readLine();
    }

    return mapping;
  }

  @Override
  public void close() throws IOException {
    reader.close();
  }

  @Override
  public int read(char[] cbuf, int off, int len) throws IOException {
    return reader.read(cbuf, off, len);
  }

}
