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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.incubator.uima.DictionaryDocument;
import org.apache.incubator.uima.EntriesDocument;
import org.apache.incubator.uima.EntryDocument;
import org.apache.incubator.uima.TypeCollectionDocument;
import org.apache.incubator.uima.DictionaryMetaDataDocument.DictionaryMetaData;
import org.apache.uima.annotator.dict_annot.dictionary.Dictionary;
import org.apache.uima.annotator.dict_annot.dictionary.DictionaryBuilder;
import org.apache.uima.annotator.dict_annot.dictionary.DictionaryFileParser;
import org.apache.uima.annotator.dict_annot.impl.DictionaryAnnotatorConfigException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlOptions;

/**
 * The DictionaryFileParser implementation parse the dictionary XML file and
 * creates a dictionary with the given dictionary builder.
 */
public class DictionaryFileParserImpl implements DictionaryFileParser {

   /*
    * (non-Javadoc)
    * 
    * @see org.apache.uima.annotator.dict_annot.dictionary.DictionaryFileParser#parseDictionaryFile(java.lang.String,
    *      java.io.InputStream,
    *      org.apache.uima.annotator.dict_annot.dictionary.DictionaryBuilder)
    */
   public Dictionary parseDictionaryFile(String dictionaryFilePath,
         InputStream dictionaryFileStream, DictionaryBuilder dictBuilder)
         throws ResourceInitializationException {

      // parse the dictionary file and extract the content
      DictionaryDocument dictionaryDoc;
      try {
         dictionaryDoc = DictionaryDocument.Factory.parse(dictionaryFileStream);
      } catch (Exception ex) {
         throw new DictionaryAnnotatorConfigException(
               "dictionary_annotator_error_parsing_dictionary_file",
               new Object[] { dictionaryFilePath }, ex);
      }

      // validate input file
      ArrayList<XmlError> validationErrors = new ArrayList<XmlError>();
      XmlOptions validationOptions = new XmlOptions();
      validationOptions.setErrorListener(validationErrors);

      boolean isValid = dictionaryDoc.validate(validationOptions);

      // output the errors if the XML is invalid.
      if (!isValid) {
         Iterator<XmlError> iter = validationErrors.iterator();
         StringBuffer errorMessages = new StringBuffer();
         while (iter.hasNext()) {
            errorMessages.append("\n>> ");
            errorMessages.append(iter.next());
         }
         throw new DictionaryAnnotatorConfigException(
               "dictionary_annotator_error_xml_validation", new Object[] {
                     dictionaryFilePath, errorMessages.toString() });
      }

      // get dictionary document
      DictionaryDocument.Dictionary dictionary = dictionaryDoc.getDictionary();

      // get type collection settings
      TypeCollectionDocument.TypeCollection typeCollection = dictionary
            .getTypeCollection();
      DictionaryMetaData dictMetaData = typeCollection.getDictionaryMetaData();
      boolean caseNormalization = true;
      boolean multiWordEntries = true;
      String multiWordSeparator = null;
      if (dictMetaData != null) {
         caseNormalization = dictMetaData.getCaseNormalization();
         multiWordEntries = dictMetaData.getMultiWordEntries();
         multiWordSeparator = dictMetaData.getMultiWordSeparator();
      }
      // get the dictionary language and the type name
      String language = typeCollection.getLanguageId();
      String typeName = typeCollection.getTypeDescription().getTypeName();
      // set dictionary properties
      dictBuilder.setDictionaryProperties(language, typeName,
            caseNormalization, multiWordEntries, multiWordSeparator);

      // get dictionary entries and add process them with the dictionary builder
      EntriesDocument.Entries entries = typeCollection.getEntries();
      EntryDocument.Entry[] entryArray = entries.getEntryArray();
      for (int i = 0; i < entryArray.length; i++) {
         dictBuilder.addWord(entryArray[i].getKey().getStringValue());
      }

      // get dictionary and return it
      return dictBuilder.getDictionary();
   }

}
