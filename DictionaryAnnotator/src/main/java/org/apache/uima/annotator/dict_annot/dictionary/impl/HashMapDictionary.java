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
package org.apache.uima.annotator.dict_annot.dictionary.impl;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.uima.annotator.dict_annot.dictionary.Dictionary;
import org.apache.uima.annotator.dict_annot.dictionary.DictionaryMatch;
import org.apache.uima.annotator.dict_annot.dictionary.EntryMetaData;
import org.apache.uima.cas.text.AnnotationFS;

/**
 * HashMap dictionary implementation. Implements the Dictionary interface using
 * a simple HashMap for the lookup. Each word added to the dictionary is added
 * to the main dictionary HashMap with some additional meta data information. In
 * case of multi-words the multi word parts are added as tree to the dictionary.
 */
public class HashMapDictionary implements Dictionary {

   // main dictionary HashMap
   private HashMap<String, DictionaryEntry> dictionary = null;

   // counts the entries in the dictionary, each entry gets an own unique ID
   private int idCounter;

   // dictionary language
   private String language;

   // dictionary output type
   private String typeName;

   // dictionary case normalization setting
   private boolean caseNormalization = true;

   /**
    * Creates a new HashMapDictionary object with an initial capacity of 100
    * entries.
    */
   public HashMapDictionary(boolean caseNormalization) {
      this.dictionary = new HashMap<String, DictionaryEntry>(100);
      this.idCounter = 0;
      this.caseNormalization = caseNormalization;
      this.language = null;
      this.typeName = null;
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.apache.uima.annotator.dict_annot.Dictionary#contains(java.lang.String)
    */
   public boolean contains(String word) {
      // check if the given word is available in the dictionary
      DictionaryEntry entry = this.dictionary.get(normalizeString(word));
      if (entry != null) {
         return entry.isComplete();
      } else {
         return false;
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.apache.uima.annotator.dict_annot.Dictionary#contains(java.lang.String[])
    */
   public boolean contains(String[] multiWord) {
      // check if the dictionary contains the given multi-word
      DictionaryEntry entry = containsMultiWord(multiWord);
      if (entry != null) {
         return entry.isComplete();
      } else {
         return false;
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.apache.uima.annotator.dict_annot.dictionary.Dictionary#getTypeName()
    */
   public String getTypeName() {
      // returns the dictionary output type name
      return this.typeName;
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.apache.uima.annotator.dict_annot.dictionary.Dictionary#getLanguage()
    */
   public String getLanguage() {
      // returns the dictionary language
      return this.language;
   }

   /**
    * check if the given multi-word is available in the dictionary. If it is
    * available the DictionaryEntry is returned. If it is not available, null is
    * returned
    * 
    * @param multiWord
    *           multi-word used for the lookup
    * 
    * @return DictionaryEntry for the given multi-word, or null if the entry is
    *         not in the dictionary
    */
   private DictionaryEntry containsMultiWord(String[] multiWord) {
      HashMap<String, DictionaryEntry> currentMap = this.dictionary;
      DictionaryEntry entry = null;

      // iterate over all multi-word tokens and check if the multi-word is
      // available
      for (int i = 0; i < multiWord.length; i++) {
         // check token
         entry = currentMap.get(normalizeString(multiWord[i]));
         if (entry == null) {
            // multi-word is not available
            return null;
         } else {
            // use sub branch to lookup the next token
            currentMap = entry.getSubBranch();
         }
      }
      return entry;
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.apache.uima.annotator.dict_annot.Dictionary#matchEntry(int,
    *      java.lang.String[])
    */
   public DictionaryMatch matchEntry(int pos, AnnotationFS[] annotFSs,
         FeaturePathInfo featPathInfo) {

      // create a dictionary match object
      DictionaryMatchImpl match = new DictionaryMatchImpl();
      int offset = 0;

      // check dictionary matches
      checkMatches(pos, annotFSs, featPathInfo, this.dictionary, match, offset);

      // check if a match was found that is valid
      if (match.isValidMatch()) {
         // valid match found, return the match
         return match;
      } else {
         // no valid match found
         return null;
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.apache.uima.annotator.dict_annot.Dictionary#getEntryCount()
    */
   public int getEntryCount() {
      // returns the number of entries in the dictionary
      return this.idCounter;
   }

   /**
    * Adds a new word to the dictionary.
    * 
    * @param word
    *           word that should be added to the dictionary
    * 
    * @return ID that was generated for this entry
    */
   public int addWord(String word) {

      // check if the word is already available in the dictionary
      DictionaryEntry entry = this.dictionary.get(normalizeString(word));
      if (entry != null) {
         // check if entry is marked as complete
         if (entry.isComplete()) {
            return -1;
         } else {
            // set valid word
            entry.setComplete();

            // update entry meta data
            this.idCounter++;
            EntryMetaData metaData = new EntryMetaDataImpl(this.idCounter);
            entry.setEntryMetaData(metaData);
            return this.idCounter;
         }
      }

      // increase ID counter
      this.idCounter++;
      // create new entry meta data object and add ID
      EntryMetaData metaData = new EntryMetaDataImpl(this.idCounter);

      // add entry to the dictionary
      this.dictionary.put(normalizeString(word), new DictionaryEntry(true,
            metaData));
      return this.idCounter;
   }

   /**
    * Adds a new multi-word to the dictionary
    * 
    * @param multiWord
    *           multi-word that should be added to the dictionary
    * 
    * @return ID that was generated for this entry
    */
   public int addMultiWord(String[] multiWord) {

      DictionaryEntry entry = containsMultiWord(multiWord);
      if (entry != null) {
         // check if entry is marked as complete
         if (entry.isComplete()) {
            return -1;
         } else {
            // set valid word
            entry.setComplete();
            // update entry meta data
            this.idCounter++;
            EntryMetaData metaData = new EntryMetaDataImpl(this.idCounter);
            entry.setEntryMetaData(metaData);
            return this.idCounter;
         }
      }

      // increase ID counter
      this.idCounter++;
      // create new entry meta data object and add ID
      EntryMetaData metaData = new EntryMetaDataImpl(this.idCounter);

      HashMap<String, DictionaryEntry> currentMap = this.dictionary;

      // iterate over all multi-word tokens and add them to the dictionary
      for (int i = 0; i < multiWord.length; i++) {
         // check if the current token is already in the dictionary
         if (currentMap.containsKey(normalizeString(multiWord[i]))) {
            // current multi-word token is already in the dictionary get the map
            // of sub tokens for this entry
            currentMap = (currentMap.get(normalizeString(multiWord[i])))
                  .getSubBranch();
         } else { // current multi-word token is not in the dictionary
            // check how we have to set the word end property
            if (i == (multiWord.length - 1)) {
               // if it is the last token of the multi-word, the word end
               // property must be set to true
               entry = new DictionaryEntry(true, metaData);
            } else {
               entry = new DictionaryEntry(false, null);
            }
            // add token to the dictionary
            currentMap.put(normalizeString(multiWord[i]), entry);
            // set map for the next sub tokens
            currentMap = entry.getSubBranch();
         }
      }

      // return generated ID
      return this.idCounter;
   }

   /**
    * print the dictionary content either to an Writer object or if not output
    * is specified to the command line.
    * 
    * @param out
    *           Writer object to write the content to
    * 
    * @throws IOException
    */
   public void printDictionary(Writer out) throws IOException {
      // start printing with the main branch
      printBranch(this.dictionary, null, out);
   }

   /**
    * set the dictionary language
    * 
    * @param language
    *           dictionary language
    */
   public void setDictionaryLanguage(String language) {
      this.language = language;
   }

   /**
    * set the dictionary type name
    * 
    * @param typeName
    *           dictionary type name
    */
   public void setTypeName(String typeName) {
      this.typeName = typeName;
   }

   /**
    * search the first longest matches for the given input data. This method is
    * called recursive to detect multi word matches. All valid matches that are
    * found are stored so that they can be retrieved later.
    * 
    * @param pos
    *           start position in the token array
    * @param tokens
    *           token input array
    * @param dict
    *           dictionary to use
    * @param match
    *           match result object where all match data is stored
    * @param offset
    *           offset in relation to the start position of the current token
    *           that is discovered
    */
   private void checkMatches(int pos, AnnotationFS[] annotFSs,
         FeaturePathInfo featPathInfo, HashMap<String, DictionaryEntry> dict,
         DictionaryMatchImpl match, int offset) {

      // check if the current token that is investigated is available in the
      // current map
      String value = featPathInfo.getValue(annotFSs[pos + offset]);
      if (value != null) {
         String normalizedValue = normalizeString(value);
         if (dict.containsKey(normalizedValue)) {
            // token is available in the map, get the dictionary entry object
            DictionaryEntry currentEntry = dict.get(normalizedValue);
            // add match to the match object
            match.storeMatch(currentEntry.getEntryMetaData(), currentEntry
                  .isComplete());

            // increase offset to investigate the next token
            offset++;
            // if further tokens are available ....
            if (annotFSs.length > pos + offset) {
               // ... investigate them
               checkMatches(pos, annotFSs, featPathInfo, currentEntry
                     .getSubBranch(), match, offset);
            }
         }
      }
   }

   /**
    * print out the words of a dictionary branch
    * 
    * @param map
    *           current dictionary branch
    * 
    * @param previousTokens
    *           previous tokens for the current branch
    */
   private void printBranch(HashMap<String, DictionaryEntry> map,
         ArrayList<String> previousTokens, Writer out) throws IOException {
      // get an iterator over the main entries of this dictionary branch
      Iterator<String> mainEntries = map.keySet().iterator();

      // iterate over all entries of this branch
      while (mainEntries.hasNext()) {
         // get current token
         String currentToken = mainEntries.next();

         // get dictionary entry for the current token
         DictionaryEntry dictEntry = map.get(currentToken);

         // check if the token is a valid word entry or a part of a multi-word
         // entry
         if (dictEntry.isComplete()) {
            // word end detected, print word
            if (previousTokens != null) {
               // add previous tokens of this multi-word
               String previous = getString(previousTokens);
               if (out == null) {
                  System.out.println(previous + currentToken);
               } else {
                  out.write(previous + currentToken);
                  out.write(System.getProperty("line.separator"));
               }
            } else {
               // just print the single word
               if (out == null) {
                  System.out.println(currentToken);
               } else {
                  out.write(currentToken);
                  out.write(System.getProperty("line.separator"));
               }
            }
         }

         // check for the current token if it is part of a multi-word
         // get the sub branch of this entry
         HashMap<String, DictionaryEntry> subBranch = dictEntry.getSubBranch();
         // add current token to previousTokens list
         if (previousTokens == null) {
            previousTokens = new ArrayList<String>();
         }
         previousTokens.add(currentToken);
         // call printBranch for the current sub branch
         printBranch(subBranch, previousTokens, out);
         // remove current token form list, since the sub branch is processed
         // completely
         previousTokens.remove(previousTokens.size() - 1);
      }
   }

   /**
    * Converts the array list of string to a concatenated String separated by a
    * whitespace character.
    * 
    * @param stringList
    *           array list of string
    * 
    * @return concatenated String separated by whitespace characters
    */
   private String getString(ArrayList<String> stringList) {

      StringBuffer buf = new StringBuffer();

      for (int i = 0; i < stringList.size(); i++) {
         buf.append(stringList.get(i));
         buf.append(" ");
      }

      return buf.toString();
   }

   /**
    * Normalize the input string to lower case and remove all spaces around if
    * the dictionary is configured to do a case normalization
    * 
    * @param input
    *           input string to normalize
    * 
    * @return returns the normalized string
    */
   private String normalizeString(String input) {
      // check if case normalization is enabled
      if (this.caseNormalization) {
         return input.toLowerCase().trim();
      }

      return input.trim();
   }
}
