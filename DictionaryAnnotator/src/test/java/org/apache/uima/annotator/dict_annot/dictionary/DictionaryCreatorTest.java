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

import java.io.File;

import junit.framework.TestCase;

import org.apache.uima.annotator.dict_annot.dictionary.impl.DictionaryCreator;
import org.apache.uima.test.junit_extension.FileCompare;
import org.apache.uima.test.junit_extension.JUnitExtension;

/**
 * Test dictionary creation with the DictionarCreator
 */
public class DictionaryCreatorTest extends TestCase {

   /**
    * test default dictionary creation
    * 
    * @throws Exception
    */
   public void testDefaultDictionaryCreation() throws Exception {

      String inputFile = JUnitExtension.getFile(
            "DictionaryCreatorTest/dictInput.txt").getAbsolutePath();
      File outputFile = new File(JUnitExtension
            .getFile("DictionaryCreatorTest"), "defaultDict.xml");

      DictionaryCreator.createDictionary(inputFile, "UTF-8", outputFile
            .getAbsolutePath(), null, null, null, null);

      File refFile = JUnitExtension
            .getFile("DictionaryCreatorTest/defaultDictRef.xml");

      assertTrue(FileCompare.compare(refFile, outputFile));
   }

   /**
    * test dictionary creation is language and special separator character
    * @throws Exception
    */
   public void testCustomSeparatorDictionaryCreation() throws Exception {

      String inputFile = JUnitExtension.getFile(
            "DictionaryCreatorTest/dictInput.txt").getAbsolutePath();
      File outputFile = new File(JUnitExtension
            .getFile("DictionaryCreatorTest"), "separatorDict.xml");

      DictionaryCreator.createDictionary(inputFile, "UTF-8", outputFile
            .getAbsolutePath(), "en", null, null, " ");

      File refFile = JUnitExtension
            .getFile("DictionaryCreatorTest/separatorDictRef.xml");

      assertTrue(FileCompare.compare(refFile, outputFile));
   }

   /**
    * test dictionary creation with a tokenizer PEAR
    * 
    * @throws Exception
    */
   public void testTokenizerDictionaryCreation() throws Exception {

      String inputFile = JUnitExtension.getFile(
            "DictionaryCreatorTest/dictInput.txt").getAbsolutePath();
      File outputFile = new File(JUnitExtension
            .getFile("DictionaryCreatorTest"), "tokenizerDict.xml");
      File pearFile = JUnitExtension.getFile("DictionaryCreatorTest/WhitespaceTokenizer.pear");
      DictionaryCreator.createDictionary(inputFile, "UTF-8", outputFile
            .getAbsolutePath(), "en", pearFile.getAbsolutePath(), "org.apache.uima.TokenAnnotation", null);

      File refFile = JUnitExtension
            .getFile("DictionaryCreatorTest/tokenizerDictRef.xml");

      assertTrue(FileCompare.compare(refFile, outputFile));
   }
}
