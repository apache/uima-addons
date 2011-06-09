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

/**
 * The DictionaryMatch interface defines the access to a dictionary match.
 */
public interface DictionaryMatch {

   /**
    * Returns the dictionary match entry meta data.
    * 
    * @return The dictionary match entry meta data is returned
    */
   public EntryMetaData getMatchMetaData();

   /**
    * Returns the length of the match.
    * 
    * @return returns 1 for a single word match. In case of a multi word match,
    *         the multi word token count is returned.
    */
   public int getMatchLength();
}
