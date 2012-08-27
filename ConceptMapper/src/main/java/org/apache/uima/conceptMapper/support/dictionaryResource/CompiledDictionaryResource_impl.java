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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.uima.UimaContext;
import org.apache.uima.conceptMapper.Logger;
import org.apache.uima.resource.DataResource;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.SharedResourceObject;
/**
 * Implementation of a UIMA DictionaryResource
 */

public class CompiledDictionaryResource_impl implements DictionaryResource, SharedResourceObject {
  /**
   * Hashtable of first words. Contains a DictEntries object keyed on word string for the first word
   * of every entry in the specified dictionary.
   */
  protected Hashtable<String, DictEntriesByLength> dictImpl;
  protected EntryPropertiesRoot entryPropertiesRoot;

  public DictionaryResource newDictionaryResource(int initialSize) {
    throw new UnsupportedOperationException();
  }

  public DictEntriesByLength getEntries(String key) {
    return dictImpl.get(key);
  }

  public boolean isLoaded() {
    return true;
  }

  public Enumeration<String> keys() {
    return dictImpl.keys();
  }

  @SuppressWarnings("unchecked")
  public void load(DataResource data) throws ResourceInitializationException {
    try {
      ObjectInputStream ois = new ObjectInputStream(data.getInputStream());
      entryPropertiesRoot = (EntryPropertiesRoot) ois.readObject();
      dictImpl = (Hashtable) ois.readObject();
      ois.close();
    } catch (IOException e) {
      throw new ResourceInitializationException(e);
    } catch (ClassNotFoundException e) {
      throw new ResourceInitializationException(e);
    }
  }

  public void loadDictionaryContents(UimaContext context, Logger logger,
          String tokenAnnotationName, String tokenTypeFeatureName, String tokenClassFeatureName,
          String tokenizerDescriptor) throws ResourceInitializationException {
    // nothing to do
  }

  public void putEntry(String key, String[] tokens, String unsortedEntry,
          int length, EntryProperties props) {
    throw new UnsupportedOperationException();
  }

  public EntryPropertiesRoot getEntryPropertiesRoot() {
	return entryPropertiesRoot;
  }

}