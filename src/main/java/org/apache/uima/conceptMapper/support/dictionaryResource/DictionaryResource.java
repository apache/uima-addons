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
package org.apache.uima.conceptMapper.support.dictionaryResource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;

import org.apache.uima.UimaContext;
import org.apache.uima.conceptMapper.Logger;
import org.apache.uima.resource.DataResource;
import org.apache.uima.resource.ResourceInitializationException;

/*
 * Interface for external UIMA dictionary resource
 */

public interface DictionaryResource {
  // shared resource loader
  public void load(DataResource data) throws ResourceInitializationException;

  public DictionaryResource newDictionaryResource(int initialSize);

  public EntryPropertiesRoot getEntryPropertiesRoot ();
  
  public interface DictEntriesByLength extends Serializable {
    public DictEntries getEntries(int length);

    void putEntry(int length, String[] elements, String unsorted,
            EntryProperties props);

    public Integer getLongest();

    public Integer getShortest();
  }

  public interface DictEntries extends Serializable {

    /**
     * @param elements
     * @param unsorted
     * @param props
     */
    void putEntry(String[] elements, String unsorted, EntryProperties props);

    /**
     * @return list of dictionary entries
     */
    ArrayList<DictEntry> getEntries();

    public String toString();
  }

  public interface DictEntry extends Serializable {
    public void setElements(String[] elements);

    public String[] getElements();

    public String getUnsorted();

    public void setUnsorted(String text);

    public EntryProperties getProperties();

    public void setProperties(EntryProperties props);

    public String toString();

  }

  /**
   * return data structure containing a list of dictionary entries, sorted by number of tokens
   * 
   * @param key
   * @return data structure containing a list of dictionary entries, sorted by number of tokens
   */
  public DictEntriesByLength getEntries(String key);

  /**
   * @param key
   *          the key to index on
   * @param tokens
   *          array of tokens to be entered in the dictionary
   * @param unsortedEntry
   *          String representation of tokens to be entered in the dictionary in sorted order, if
   *          "entry" is sorted, otherwise null
   * @param length
   *          Number of tokens in entry
   * @param props
   *          the properties object for the dictionary entry
   */
  public void putEntry(String key, String[] tokens, String unsortedEntry,
          int length, EntryProperties props);

  public Enumeration<String> keys();

  public String toString();

  /**
   * @param context
   * @param logger
   * @param tokenAnnotationName
   * @param tokenTypeFeatureName
   * @param tokenClassFeatureName
   * @param tokenizerDescriptor
   * @throws ResourceInitializationException
   */
  public void loadDictionaryContents(UimaContext context, Logger logger,
          String tokenAnnotationName, String tokenTypeFeatureName, String tokenClassFeatureName,
          String tokenizerDescriptor) throws ResourceInitializationException;

  public boolean isLoaded();
}
