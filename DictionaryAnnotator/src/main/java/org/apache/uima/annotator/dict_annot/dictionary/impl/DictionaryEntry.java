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

import java.util.HashMap;

import org.apache.uima.annotator.dict_annot.dictionary.EntryMetaData;

/**
 * The DictionaryEntry contains the entry meta data object and the information
 * if an entry is complete or just a part of a multi-word entry.
 * 
 * If the entry is part of a multi-word entry it also contains the branch of
 * multi-word tokens that are followed by the current entry.
 */
public class DictionaryEntry {

   // sub branch of multi word token entries of the current entry
   private HashMap<String, DictionaryEntry> subBranch;

   // specifies if the current entry is complete or just a part of a multi word
   private boolean isComplete = false;

   // contains the current entry meta data
   private EntryMetaData entryMetaData;

   /**
    * Creates a new DictionaryEntry object
    * 
    * @param isComplete
    *           should be true if the current entry is complete. An entry is
    *           complete if it is a single word or the last token of a multi
    *           word entry
    * 
    * @param metaData
    *           meta data for the current entry
    */
   public DictionaryEntry(boolean isComplete, EntryMetaData metaData) {
      this.isComplete = isComplete;
      this.entryMetaData = metaData;
   }

   /**
    * Returns the sub branch for a dictionary entry. If no sub branch exist, a
    * new branch is created.
    * 
    * @return returns the sub branch HashMap for this entry
    */
   public HashMap<String, DictionaryEntry> getSubBranch() {
      if (this.subBranch == null) {
         this.subBranch = new HashMap<String, DictionaryEntry>();
      }
      return this.subBranch;
   }

   /**
    * Returns for the current entry if it is complete or not. An entry is
    * complete if it is a single word or the last token of a multi word entry.
    * 
    * @return returns true if the entry is complete, otherwise false.
    */
   public boolean isComplete() {
      return this.isComplete;
   }

   /**
    * marks the current entry as a valid entry
    */
   public void setComplete() {
      this.isComplete = true;
   }

   /**
    * Returns the current entry meta data object.
    * 
    * @return EntryMetaData object for the current entry.
    */
   public EntryMetaData getEntryMetaData() {
      return this.entryMetaData;
   }

   /**
    * Sets the metaData for this entry.
    * 
    * @param metaData
    *           entry meta data
    */
   public void setEntryMetaData(EntryMetaData metaData) {
      this.entryMetaData = metaData;
   }
}
