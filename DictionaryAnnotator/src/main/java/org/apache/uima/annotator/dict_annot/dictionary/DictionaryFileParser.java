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
package org.apache.uima.annotator.dict_annot.dictionary;

import java.io.InputStream;

import org.apache.uima.resource.ResourceInitializationException;

/**
 * The DictionaryFileParser interface defines the method to parse a dictionary
 * file to create a dictionary.
 */
public interface DictionaryFileParser {

   /**
    * parse the given dictionary file and creates a new dictionary using the
    * given dictionary builder.
    * 
    * @param dictionaryFilePath
    *           dictionary XML file path
    * 
    * @param dictionaryFileStream
    *           dictionary XML file stream
    *           
    * @param dictBuilder
    *           dictionary build that should be used to create the dictionary
    * @return returns the created Dictionary
    * @throws ResourceInitializationException
    */
   public Dictionary parseDictionaryFile(String dictionaryFilePath,
         InputStream dictionaryFileStream, DictionaryBuilder dictBuilder)
         throws ResourceInitializationException;
}
