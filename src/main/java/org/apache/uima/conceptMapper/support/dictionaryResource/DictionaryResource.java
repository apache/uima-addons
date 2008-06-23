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
import java.util.Collection;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.uima.analysis_engine.annotator.AnnotatorContext;
import org.apache.uima.conceptMapper.Logger;
import org.apache.uima.resource.DataResource;
import org.apache.uima.resource.ResourceInitializationException;

/*
 * Interface for external UIMA dictionary resource
 */

public interface DictionaryResource {
  // shared resource loader
  public void load(DataResource data) throws ResourceInitializationException;

  public DictionaryResource NewDictionaryResource(int initialSize);

  public interface DictEntriesByLength extends Serializable {
    public DictEntries getEntries(int length);

    void putEntry(int length, String entry, ArrayList<String> elements, String unsorted,
            Properties props);

    public Integer getLongest();

    public Integer getShortest();
  }

  public interface DictEntries extends Serializable {

    /**
     * @param elements
     * @param unsorted
     * @param key
     * @param length
     * @param props
     */
    void putEntry(String entry, Collection<String> elements, String unsorted, Properties props);

    /**
     * @param string
     * @return
     */
    ArrayList<DictEntry> getEntries();

    public String toString();
  }

  public interface DictEntry extends Serializable {
    public String getText();

    public void setElements(Collection<String> elements);

    public Collection<String> getElements();

    public void setText(String text);

    public String getUnsorted();

    public void setUnsorted(String text);

    public Properties getProperties();

    public void setProperties(Properties props);

    public String toString();

  }

  /**
   * return data structure containing a list of dictionary entries, sorted by number of tokens
   * 
   * @param key
   * @return
   */
  public DictEntriesByLength getEntries(String key);

  /**
   * @param key
   *          the key to index on
   * @param entry
   *          String representation of tokens to be entered in the dictionary
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
  public void putEntry(String key, String entry, ArrayList<String> tokens, String unsortedEntry,
          int length, Properties props);

  /**
   * @return
   */
  public Enumeration<String> keys();

  public String toString();

  /**
   * @param context
   * @param tokenizerDescriptor
   * @param tokenAnnotationName
   * @throws ResourceInitializationException
   */
  public void loadDictionaryContents(AnnotatorContext context, Logger logger,
          String tokenAnnotationName, String tokenTypeFeatureName, String tokenClassFeatureName,
          String tokenizerDescriptor) throws ResourceInitializationException;

  public boolean isLoaded();
}
