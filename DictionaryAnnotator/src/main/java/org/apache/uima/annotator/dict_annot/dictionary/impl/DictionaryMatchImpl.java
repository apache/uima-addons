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

import org.apache.uima.annotator.dict_annot.dictionary.DictionaryMatch;
import org.apache.uima.annotator.dict_annot.dictionary.EntryMetaData;

/**
 * Implementation of the DictionaryMatch interface. Store the matches found in
 * the dictionary temporarily and check on the fly in they are valid or not. All
 * valid matches are stored separately. The last valid match is replaced if a new valid
 * match was found. At the end the match object contains the longest valid match
 * from the dictionary.
 */
public class DictionaryMatchImpl implements DictionaryMatch {

   // token count of the tempMatch that was found
   private int tempTokenCount;

   // metaData object of the temp match that was found
   private EntryMetaData tempMetaData;

   // token count of the last valid match
   private int correctTokenCount;

   // metaData of the last valid match
   private EntryMetaData correctMetaData;

   /**
    * Creates a new DictionaryMatch object and reset all members
    */
   public DictionaryMatchImpl() {
      reset();
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.apache.uima.annotator.dict_annot.DictionaryMatch#getMatchMetaData()
    */
   public EntryMetaData getMatchMetaData() {
      return this.correctMetaData;
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.apache.uima.annotator.dict_annot.DictionaryMatch#getMatchLength()
    */
   public int getMatchLength() {
      return this.correctTokenCount;
   }

   /**
    * stores a new match in the match object
    * 
    * @param metaData
    *           metaData for the entry that matched
    * 
    * @param isComplete
    *           specifies if the entry is a temporary match or a valid match
    */
   public void storeMatch(EntryMetaData metaData, boolean isComplete) {
      this.tempMetaData = metaData;
      this.tempTokenCount++;

      // if the match is valid -> store valid match
      if (isComplete) {
         this.correctTokenCount = this.tempTokenCount;
         this.correctMetaData = this.tempMetaData;
      }
   }

    /**
     * check if a valid match is available.
     * 
    * @return returns true if a valid match is available, otherwise false is returned
    */
   public boolean isValidMatch() {
      if (this.correctTokenCount > 0) {
         return true;
      }
      return false;
   }

   /**
    * reset the match data
    */
   public void reset() {
      this.correctMetaData = null;
      this.tempMetaData = null;
      this.correctTokenCount = 0;
      this.tempTokenCount = 0;
   }
}
