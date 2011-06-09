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
 * The Dictionary builder interface define the methods to create a new
 * dictionary.
 */
public interface DictionaryBuilder {

   /**
    * @return returns the Dictionary object if the dictionary could be created.
    */
   public Dictionary getDictionary();

   /**
    * Adds a new word to the dictionary.
    * 
    * @param word
    *           word that should be added.
    */
   public void addWord(String word);

   /**
    * Set the dictionary properties, this method have to be called before words
    * can be added to the dictionary.
    * 
    * @param language
    *           dictionary language
    * 
    * @param typeName
    *           type name for the dictionary content
    * @param caseNormalization
    *           case normalization settings
    * @param multiWordEntries
    *           multi-word entries setting
    * @param multiWordSeparator
    *           multi-word entry separator
    */
   public void setDictionaryProperties(String language, String typeName,
         boolean caseNormalization, boolean multiWordEntries, String multiWordSeparator);

}
