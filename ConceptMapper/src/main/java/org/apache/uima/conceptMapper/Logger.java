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
package org.apache.uima.conceptMapper;

import org.apache.uima.util.Level;

public class Logger {
  private org.apache.uima.util.Logger logger;

  private String prefix;

  public Logger(String prefix, org.apache.uima.util.Logger logger) {
    super();
    this.prefix = prefix;
    this.logger = logger;
  }

  public Logger(org.apache.uima.util.Logger logger) {
    super();
    this.prefix = "";
    this.logger = logger;
  }

  private void log(Level level, String message) {
    String logMessage = prefix + " " + level.toString() + ": " + message;

    if (logger == null) {
      System.err.println(logMessage);
    } else {
      logger.log(level, logMessage);
    }
  }

  public void logError(String message) {
    log(Level.SEVERE, message);
  }

  public void logWarning(String message) {
    log(Level.WARNING, message);
  }

  public void logInfo(String message) {
    log(Level.INFO, message);
  }

  public void logFine(String message) {
    log(Level.FINE, message);
  }

  public void logFinest(String message) {
    log(Level.FINEST, message);
  }
}
