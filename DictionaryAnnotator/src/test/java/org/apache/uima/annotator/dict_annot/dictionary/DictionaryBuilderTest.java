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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.incubator.uima.DictionaryDocument;
import org.apache.incubator.uima.EntriesDocument;
import org.apache.incubator.uima.EntryDocument;
import org.apache.uima.annotator.dict_annot.dictionary.impl.DictionaryFileParserImpl;
import org.apache.uima.annotator.dict_annot.dictionary.impl.HashMapDictionary;
import org.apache.uima.annotator.dict_annot.dictionary.impl.HashMapDictionaryBuilder;
import org.apache.uima.annotator.dict_annot.impl.DictionaryAnnotatorConfigException;
import org.apache.uima.test.junit_extension.FileCompare;
import org.apache.uima.test.junit_extension.JUnitExtension;

/**
 * The DictionaryBuilderTest class tests the dictionary creation for single and
 * multi word entries.
 */
public class DictionaryBuilderTest extends TestCase {

   /**
    * test single word dictionary creation with case normalization
    * 
    * @throws Exception
    */
   public void testSingleWordDictionaryBuildingCaseNormalization()
         throws Exception {

      // read input file
      File dictFile = JUnitExtension
            .getFile("DictionaryBuilderTests/SingleWordsCaseNormalization.xml");
      InputStream stream = new BufferedInputStream(
            new FileInputStream(dictFile));

      // create dictionary
      HashMapDictionaryBuilder dictBuilder = new HashMapDictionaryBuilder();
      // create dictionary file parser
      DictionaryFileParser fileParser = new DictionaryFileParserImpl();
      fileParser.parseDictionaryFile(dictFile.getAbsolutePath(), stream,
            dictBuilder);
      HashMapDictionary dict = (HashMapDictionary) dictBuilder.getDictionary();

      // read dictionary entries
      ArrayList<String> entries = getDictionaryEntries(dictFile);

      for (int i = 0; i < entries.size(); i++) {

         Assert.assertTrue("Missing in dictionary: " + entries.get(i), dict
               .contains(entries.get(i)));
      }

      Assert.assertTrue(dict.contains("CpE"));
      Assert.assertTrue(dict.contains("CPE"));
      Assert.assertTrue(dict.contains("cpe"));
      Assert.assertEquals(2761, dict.getEntryCount());
   }

   /**
    * test single word dictionary creation without case normalization
    * 
    * @throws Exception
    */
   public void testSingleWordDictionaryBuildingNoCaseNormalization()
         throws Exception {

      // read input file
      File dictFile = JUnitExtension
            .getFile("DictionaryBuilderTests/SingleWordsNoCaseNormalization.xml");
      InputStream stream = new BufferedInputStream(
            new FileInputStream(dictFile));

      // create dictionary
      DictionaryBuilder dictBuilder = new HashMapDictionaryBuilder();
      // create dictionary file parser
      DictionaryFileParser fileParser = new DictionaryFileParserImpl();
      fileParser.parseDictionaryFile(dictFile.getAbsolutePath(), stream,
            dictBuilder);

      HashMapDictionary dict = (HashMapDictionary) dictBuilder.getDictionary();

      // read dictionary entries
      ArrayList<String> entries = getDictionaryEntries(dictFile);

      for (int i = 0; i < entries.size(); i++) {

         Assert.assertTrue("Missing in dictionary: " + entries.get(i), dict
               .contains(entries.get(i)));
      }

      Assert.assertFalse(dict.contains("CpE"));
      Assert.assertTrue(dict.contains("CPE"));
      Assert.assertTrue(dict.contains("cpe"));
      Assert.assertEquals(3337, dict.getEntryCount());
   }

   /**
    * test multi word dictionary creation without case normalization
    * 
    * @throws Exception
    */
   public void testMultiWordDictionaryBuildingNoCaseNormalization()
         throws Exception {

      // read input file
      File dictFile = JUnitExtension
            .getFile("DictionaryBuilderTests/MultiWordsNoCaseNormalization.xml");
      InputStream stream = new BufferedInputStream(
            new FileInputStream(dictFile));

      // create dictionary
      HashMapDictionaryBuilder dictBuilder = new HashMapDictionaryBuilder();
      // create dictionary file parser
      DictionaryFileParser fileParser = new DictionaryFileParserImpl();
      fileParser.parseDictionaryFile(dictFile.getAbsolutePath(), stream,
            dictBuilder);
      HashMapDictionary dict = (HashMapDictionary) dictBuilder.getDictionary();

      // read dictionary entries
      ArrayList<String> entries = getDictionaryEntries(dictFile);

      for (int i = 0; i < entries.size(); i++) {

         StringTokenizer tokenizer = new StringTokenizer(entries.get(i),
               dictBuilder.getMultiWordSeparator());

         ArrayList<String> list = new ArrayList<String>();
         while (tokenizer.hasMoreTokens()) {
            list.add(tokenizer.nextToken());
         }
         String[] multiWord = list.toArray(new String[] {});

         Assert.assertTrue("Missing in dictionary: " + entries.get(i), dict
               .contains(multiWord));
      }

      Assert.assertEquals(5, dict.getEntryCount());
      Assert.assertFalse(dict.contains(new String[] { "Unstructured",
            "Information", "Management", "Architecture" }));
      Assert.assertTrue(dict.contains(new String[] { "new", "York" }));
      Assert.assertTrue(dict.contains(new String[] { "new", "York", "City" }));
   }

   /**
    * test multi word dictionary creation with case normalization
    * 
    * @throws Exception
    */
   public void testMultiWordDictionaryBuildingCaseNormalization()
         throws Exception {

      // read input file
      File dictFile = JUnitExtension
            .getFile("DictionaryBuilderTests/MultiWordsCaseNormalization.xml");
      InputStream stream = new BufferedInputStream(
            new FileInputStream(dictFile));

      // create dictionary
      HashMapDictionaryBuilder dictBuilder = new HashMapDictionaryBuilder();
      // create dictionary file parser
      DictionaryFileParser fileParser = new DictionaryFileParserImpl();
      fileParser.parseDictionaryFile(dictFile.getAbsolutePath(), stream,
            dictBuilder);
      HashMapDictionary dict = (HashMapDictionary) dictBuilder.getDictionary();

      // read dictionary entries
      ArrayList<String> entries = getDictionaryEntries(dictFile);

      for (int i = 0; i < entries.size(); i++) {

         StringTokenizer tokenizer = new StringTokenizer(entries.get(i),
               dictBuilder.getMultiWordSeparator());

         ArrayList<String> list = new ArrayList<String>();
         while (tokenizer.hasMoreTokens()) {
            list.add(tokenizer.nextToken());
         }
         String[] multiWord = list.toArray(new String[] {});

         Assert.assertTrue("Missing in dictionary: " + entries.get(i), dict
               .contains(multiWord));
      }

      Assert.assertEquals(4, dict.getEntryCount());
      Assert.assertFalse(dict.contains(new String[] { "Unstructured",
            "Information", "Management", "Architecture" }));
      Assert.assertTrue(dict.contains(new String[] { "new", "yORk" }));
      Assert.assertTrue(dict.contains(new String[] { "new", "york", "city" }));
      Assert.assertFalse(dict.contains("new"));
   }

   /**
    * test multi-word dictionary creation with a special multi-word separator
    * 
    * @throws Exception
    */
   public void testMultiWordDictionaryBuildingWithSpecialMultiWordSeparator()
         throws Exception {

      // read input file
      File dictFile = JUnitExtension
            .getFile("DictionaryBuilderTests/MultiWordsSpecialMultiWordSeparator.xml");
      InputStream stream = new BufferedInputStream(
            new FileInputStream(dictFile));

      // create dictionary
      HashMapDictionaryBuilder dictBuilder = new HashMapDictionaryBuilder();
      // create dictionary file parser
      DictionaryFileParser fileParser = new DictionaryFileParserImpl();
      fileParser.parseDictionaryFile(dictFile.getAbsolutePath(), stream,
            dictBuilder);
      HashMapDictionary dict = (HashMapDictionary) dictBuilder.getDictionary();

      // read dictionary entries
      ArrayList<String> entries = getDictionaryEntries(dictFile);

      for (int i = 0; i < entries.size(); i++) {

         StringTokenizer tokenizer = new StringTokenizer(entries.get(i),
               dictBuilder.getMultiWordSeparator());

         ArrayList<String> list = new ArrayList<String>();
         while (tokenizer.hasMoreTokens()) {
            list.add(tokenizer.nextToken());
         }
         String[] multiWord = list.toArray(new String[] {});

         Assert.assertTrue("Missing in dictionary: " + entries.get(i), dict
               .contains(multiWord));
      }

      Assert.assertEquals(4, dict.getEntryCount());
      Assert.assertFalse(dict.contains(new String[] { "Unstructured",
            "Information", "Management", "Architecture" }));
      Assert.assertTrue(dict.contains(new String[] { "new", "yORk" }));
      Assert.assertTrue(dict.contains(new String[] { "new", "york", "city" }));
      Assert.assertFalse(dict.contains("new"));
   }

   /**
    * parse the dictionary file and returns the entries as ArrayList.
    * 
    * @param dictionaryFile
    * @return
    * @throws Exception
    */
   private ArrayList<String> getDictionaryEntries(File dictionaryFile)
         throws Exception {

      ArrayList<String> dictEntries = new ArrayList<String>();

      // parse the dictionary file and extract the content
      DictionaryDocument dictionaryDoc;
      try {
         dictionaryDoc = DictionaryDocument.Factory.parse(dictionaryFile);
      } catch (Exception ex) {
         throw new DictionaryAnnotatorConfigException(
               "dictionary_annotator_error_parsing_dictionary_file",
               new Object[] { dictionaryFile.getAbsolutePath() }, ex);
      }

      // get dictionary document
      DictionaryDocument.Dictionary dictionary = dictionaryDoc.getDictionary();

      // get dictionary entries
      EntriesDocument.Entries entries = dictionary.getTypeCollection()
            .getEntries();
      EntryDocument.Entry[] entryArray = entries.getEntryArray();
      for (int i = 0; i < entryArray.length; i++) {
         dictEntries.add(entryArray[i].getKey().getStringValue());
      }

      // return the dictionary entries
      return dictEntries;
   }

   /**
    * test building an invalid XML dictionary file
    * 
    * @throws Exception
    */
   public void testBuildingInvalidDictFile() throws Exception {

      // read input file
      File dictFile = JUnitExtension
            .getFile("DictionaryBuilderTests/InvalidDictFile.xml");
      InputStream stream = new BufferedInputStream(
            new FileInputStream(dictFile));

      // create dictionary
      HashMapDictionaryBuilder dictBuilder = new HashMapDictionaryBuilder();
      // create dictionary file parser
      DictionaryFileParser fileParser = new DictionaryFileParserImpl();

      boolean foundMessage = false;

      try {
         fileParser.parseDictionaryFile(dictFile.getAbsolutePath(), stream,
               dictBuilder);
      } catch (DictionaryAnnotatorConfigException ex) {
         String message = ex.getMessage();
         if (message.indexOf("InvalidDictFile.xml") > -1) {
            foundMessage = true;
         }

      }
      Assert.assertTrue(foundMessage);
   }

   /**
    * test building an invalid XML dictionary file
    * 
    * @throws Exception
    */
   public void testBuildingInvalidDictFile2() throws Exception {

      // read input file
      File dictFile = JUnitExtension
            .getFile("DictionaryBuilderTests/InvalidDictFile2.xml");
      InputStream stream = new BufferedInputStream(
            new FileInputStream(dictFile));

      // create dictionary
      HashMapDictionaryBuilder dictBuilder = new HashMapDictionaryBuilder();
      // create dictionary file parser
      DictionaryFileParser fileParser = new DictionaryFileParserImpl();

      boolean foundMessage = false;

      try {
         fileParser.parseDictionaryFile(dictFile.getAbsolutePath(), stream,
               dictBuilder);
      } catch (DictionaryAnnotatorConfigException ex) {
         String message = ex.getMessage();
         if (message.indexOf("InvalidDictFile2.xml") > -1) {
            foundMessage = true;
         }

      }
      Assert.assertTrue(foundMessage);
   }

   /**
    * test dictionary printing
    * 
    * @throws Exception
    */
   public void testDictionaryPrinting() throws Exception {

      // read input file
      File dictFile = JUnitExtension
            .getFile("DictionaryBuilderTests/MultiWordsSpecialMultiWordSeparator.xml");
      InputStream stream = new BufferedInputStream(
            new FileInputStream(dictFile));

      // create dictionary
      HashMapDictionaryBuilder dictBuilder = new HashMapDictionaryBuilder();
      // create dictionary file parser
      DictionaryFileParser fileParser = new DictionaryFileParserImpl();
      fileParser.parseDictionaryFile(dictFile.getAbsolutePath(), stream,
            dictBuilder);
      HashMapDictionary dict = (HashMapDictionary) dictBuilder.getDictionary();

      //create dictionary content output file
      File outputFile = new File(JUnitExtension
            .getFile("DictionaryBuilderTests"),
            "dictionaryPrinting_testoutput.txt");
      FileWriter writer = new FileWriter(outputFile);

      //print dictionary content to file
      dict.printDictionary(writer);
      
      writer.flush();
      writer.close();
      
      //compare dictionary content with reference content 
      FileCompare.compare(outputFile, JUnitExtension
            .getFile("DictionaryBuilderTests/DictionaryPrintingRef.txt"));
   }
}
