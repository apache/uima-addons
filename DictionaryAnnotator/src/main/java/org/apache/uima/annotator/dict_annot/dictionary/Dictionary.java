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

import org.apache.uima.annotator.dict_annot.dictionary.impl.FeaturePathInfo;
import org.apache.uima.cas.text.AnnotationFS;


/**
 * Dictionary interface to work with a dictionary. 
 * The interface defines methods to check if a word or multi-word is available 
 * in the dictionary and to match tokens against the dictionary.
 */
public interface Dictionary {

   /**
    * Checks if the given word is available in the dictionary.
    * 
    * @param word
    *           word to look for
    * 
    * @return returns true if the given word is available in the dictionary,
    *         otherwise false is returned
    */
   public boolean contains(String word);

   /**
    * Checks if the given multi word is available in the dictionary.
    * 
    * @param multiWord
    *           multi word to look for
    * 
    * @return returns true if the given multi word is available in the
    *         dictionary, otherwise false is returned
    */
   public boolean contains(String[] multiWord);

   /**
    * Checks if at the current position in the token array a match in the
    * dictionary is found.
    * 
    * @param pos
    *          current array position 
    * 
    * @param annotFSs 
    *           input annotation FS array
    * 
    * @param featPathInfo 
    *           featurePath information for the matching
    *           
    * @return returns a DictionaryMatch object in case a match was found. If no
    *         match was found, null is returned
    */
   public DictionaryMatch matchEntry(int pos, AnnotationFS[] annotFSs, FeaturePathInfo featPathInfo);
   
   /**
    * Returns the number of entries that are stored in the dictionary.
    * 
    * @return number of entries
    */
   public int getEntryCount();
   
   /**
    * Returns the type name which should use to create annotations
    * 
    * @return type name
    */
   public String getTypeName();
   
   /**
    * Returns the language of this dictionary
    * 
    * @return dictionary language
    */
   public String getLanguage();
}
