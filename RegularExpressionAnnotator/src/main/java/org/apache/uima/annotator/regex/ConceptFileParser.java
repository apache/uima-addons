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
package org.apache.uima.annotator.regex;

import java.io.InputStream;

import org.apache.uima.resource.ResourceInitializationException;

/**
 *
 */
public interface ConceptFileParser {

  /**
   * parse the XML concepts file and create the object representation for the concepts and rules. To parse the XML
   * concept file, XMLBeans are used.
   * 
   * @param conceptFilePathName 
   *          XML concepts file path name
   * 
   * @param conceptFileStream
   *          XML concept file stream
   *          
   * @return returns a Concept[] that contains the object representation of all concepts
   * 
   * @throws ResourceInitializationException
   *           in case of parsing errors.
   */
  public Concept[] parseConceptFile(String conceptFilePathName, InputStream conceptFileStream) throws ResourceInitializationException;
}
