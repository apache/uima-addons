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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.internal.util.CommandLineParser;
import org.apache.uima.pear.tools.PackageBrowser;
import org.apache.uima.pear.tools.PackageInstaller;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.XMLInputSource;

/**
 * The DicrionaryCreator creates an dictionary XML file based on the given input
 * parameters. The input file must contain one dictionary entry for each line.
 * The line is tokenized into multi words either with the specified tokenizer
 * PEAR file or with the separator character.
 * 
 * The dictionary output XML file (UTF-8) must be updated after the creation
 * with some metadata information.
 */
public class DictionaryCreator {

   // input file command line parameter
   private static final String INPUT_FILE_PARAM = "-input";

   // output file command line parameter
   private static final String OUTPUT_FILE_PARAM = "-output";

   // input file encoding command line parameter
   private static final String INPUT_FILE_ENCODING_PARAM = "-encoding";

   // input file language command line parameter
   private static final String INPUT_FILE_LANGUAGE_PARAM = "-lang";

   // tokenizer command line parameter
   private static final String TOKENIZER_PARAM = "-tokenizer";

   // tokenizer token type command line parameter
   private static final String TOKEN_TYPE_PARAM = "-tokenType";

   // token separator character command line parameter
   private static final String SEPARATOR_CHAR_PARAM = "-separator";

   // default separator character
   private static final String SEPARATOR_CHAR = " ";

   // default output separator character
   private static final String OUTPUT_SEPARATOR_CHAR = "|";

   private static HashMap<Character, String> entities;

   static {
      entities = new HashMap<Character, String>(5);
      entities.put('<', "&lt;");
      entities.put('>', "&gt;");
      entities.put('&', "&amp;");
      entities.put('\"', "&quot;");
      entities.put('\'', "&apos;");
   }

   /**
    * creates the CommandLine parser used to parse the DictionaryCreator command
    * line.
    * 
    * @return returns the CommandLineParser for the DictionaryCreator
    */
   private static final CommandLineParser createCmdLineParser() {
      CommandLineParser parser = new CommandLineParser();
      parser.addParameter(INPUT_FILE_PARAM, true);
      parser.addParameter(INPUT_FILE_LANGUAGE_PARAM, true);
      parser.addParameter(OUTPUT_FILE_PARAM, true);
      parser.addParameter(INPUT_FILE_ENCODING_PARAM, true);
      parser.addParameter(TOKENIZER_PARAM, true);
      parser.addParameter(TOKEN_TYPE_PARAM, true);
      parser.addParameter(SEPARATOR_CHAR_PARAM, true);
      return parser;
   }

   /**
    * prints the DictionaryCreator usage to the command line
    */
   private static final void printUsage() {
      System.out
            .println("Usage: java org.apache.uima.annotator.dict_annot.dictionary.impl.DictionaryCreator -input <InputFile> -encoding <InputFileEncoding> -output <OutputFile> [-tokenizer <TokenizerPear> -tokenType <tokenType>] [-separator <separatorChar>] ");
      System.out.println("Additional optional parameters:");
      System.out.println("  -lang <dictionaryLanguage>");

   }

   /**
    * checks the command line syntax for errors
    * 
    * @param clp
    *           current CommandLineParser
    * 
    * @return returns false in case of command line syntax errors, otherwise
    *         true
    */
   private static final boolean checkCmdLineSyntax(CommandLineParser clp) {
      boolean error = false;
      // check mandatory command line parameters
      if (!clp.isInArgsList(INPUT_FILE_PARAM)) {
         System.err.println("InputFile parameter -input is missing");
         error = true;
      }
      if (!clp.isInArgsList(INPUT_FILE_ENCODING_PARAM)) {
         System.err
               .println("InputFile encoding parameter -encoding is missing");
         error = true;
      }
      if (!clp.isInArgsList(OUTPUT_FILE_PARAM)) {
         System.err.println("OutputFile parameter -output is missing");
         error = true;
      }
      if (clp.isInArgsList(TOKENIZER_PARAM)) {
         if (!clp.isInArgsList(TOKEN_TYPE_PARAM)) {
            System.err
                  .println("If a tokenizer is used, the -tokenType paramter must be specified");
            error = true;
         }
      }

      // if there was an error, return false
      if (error == true) {
         return false;
      }

      return true;
   }

   /**
    * main method for DictionaryCreator
    * 
    * @param args
    *           command line arguments
    */
   public static void main(String[] args) {

      // create command line parser
      CommandLineParser clp = createCmdLineParser();
      try {
         // parse command line
         clp.parseCmdLine(args);
         // check command line syntax
         if (!checkCmdLineSyntax(clp)) {
            printUsage();
            System.exit(-1);
         }
      } catch (Exception e) {
         System.err.println("Error parsing command line: " + e.getMessage());
      }

      // get command line arguments
      String inputFile = clp.getParamArgument(INPUT_FILE_PARAM);
      String language = clp.getParamArgument(INPUT_FILE_LANGUAGE_PARAM);
      String encoding = clp.getParamArgument(INPUT_FILE_ENCODING_PARAM);
      String outputFile = clp.getParamArgument(OUTPUT_FILE_PARAM);
      String tokenizerFile = clp.getParamArgument(TOKENIZER_PARAM);
      String tokenTypeStr = clp.getParamArgument(TOKEN_TYPE_PARAM);
      String separatorChar = clp.getParamArgument(SEPARATOR_CHAR_PARAM);

      // create dictionary
      try {
         DictionaryCreator.createDictionary(inputFile, encoding, outputFile,
               language, tokenizerFile, tokenTypeStr, separatorChar);

         System.out.println("The dictionary was sucessfully created at: "
               + outputFile);
      } catch (Exception ex) {
         ex.printStackTrace();
      }

   }

   public static boolean createDictionary(String inputFile, String encoding,
         String outputFile, String language, String tokenizerFile,
         String tokenTypeStr, String separatorChar) throws Exception {

      String outputSeparatorChar = OUTPUT_SEPARATOR_CHAR;

      // check input file command line argument
      File inFile = new File(inputFile);
      if (!inFile.canRead()) {
         throw new Exception("Error: Input file " + inputFile
               + " cannot be read!");
      }

      // check tokenizer pear command line arguments
      AnalysisEngine ae = null;
      Type tokenType = null;
      CAS cas = null;
      File tempDir = null;

      if (tokenizerFile != null) {
         // if a tokenizer is specified, check if the file can be read
         File pearFile = new File(tokenizerFile);
         if (!pearFile.canRead()) {
            throw new Exception("Error: Tokenizer file " + tokenizerFile
                  + " cannot be read!");
         }
         if (tokenTypeStr == null) {
            throw new Exception("Error: Tokenizer tokenType not specified");
         }
         try {
            // create temp directory to install PEAR
            tempDir = new File(".", "~tokenizer_temp_install");
            tempDir.deleteOnExit();
            tempDir.mkdir();

            // Install PEAR package
            PackageBrowser instPear = PackageInstaller.installPackage(tempDir,
                  pearFile, true);

            // Create analysis engine from the installed PEAR package
            XMLInputSource in = new XMLInputSource(instPear
                  .getComponentPearDescPath());
            ResourceSpecifier specifier = UIMAFramework.getXMLParser()
                  .parseResourceSpecifier(in);
            ae = UIMAFramework.produceAnalysisEngine(specifier);

            // create CAS and initialize tokenType
            cas = ae.newCAS();
            tokenType = cas.getTypeSystem().getType(tokenTypeStr);

         } catch (Exception ex) {
            throw new Exception("Error creating tokenizer: " + ex.getMessage(),
                  ex);
         }
      }

      // check separator char command line argument
      if (separatorChar == null) {
         // use default separator character
         separatorChar = SEPARATOR_CHAR;
      } else {
         // if set, use specified separator char also as output separator
         outputSeparatorChar = separatorChar;
      }

      // initialize input and output files
      BufferedReader reader = new BufferedReader(new InputStreamReader(
            new FileInputStream(inputFile), encoding));
      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(outputFile), "UTF-8"));

      // write dictionary XML lead in
      writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
      writer
            .write("<dictionary xmlns=\"http://incubator.apache.org/uima\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"dictionary.xsd\">\n");
      writer.write("<typeCollection>\n");
      writer
            .write("<dictionaryMetaData caseNormalization=\"true\" multiWordEntries=\"true\" multiWordSeparator=\""
                  + outputSeparatorChar + "\"/>\n");
      if (language != null) {
         writer.write("<languageId>" + language + "</languageId>\n");
      }
      writer.write("<typeDescription>\n");
      writer.write("<typeName> ADD DICTIONARY OUTPUT TYPE HERE</typeName>\n");
      writer.write("</typeDescription>\n");
      writer.write("<entries>\n");

      // start adding dictionary entries
      String line = reader.readLine();
      while (line != null) {

         // multi workd string buffer - contains the tokens for each entry
         StringBuffer multiWordString = new StringBuffer();

         // tokenize entry line
         if (ae != null) { // use tokenizer
            cas.setDocumentText(line);
            if (language != null) {
               cas.setDocumentLanguage(language);
            }
            // tokenize line
            ae.process(cas);

            // read results
            FSIterator it = cas.getAnnotationIndex(tokenType).iterator();
            while (it.hasNext()) {
               multiWordString.append(((AnnotationFS) it.next())
                     .getCoveredText());
               multiWordString.append(outputSeparatorChar);
            }
            cas.reset();

         } else { // use separator char

            StringTokenizer tokenizer = new StringTokenizer(line, separatorChar);
            while (tokenizer.hasMoreTokens()) {
               multiWordString.append(tokenizer.nextToken());
               multiWordString.append(outputSeparatorChar);
            }
         }

         // trim string and remove separator char at the end
         String multiWordTokenString = multiWordString.toString().trim();
         if (multiWordTokenString.endsWith(outputSeparatorChar)) {
            int separatorLength = outputSeparatorChar.length();
            int length = multiWordTokenString.length();
            multiWordTokenString = multiWordTokenString.substring(0, length
                  - separatorLength);
         }

         // replace XML entities
         multiWordTokenString = replaceXMLEntities(multiWordTokenString);

         // write dictionary entry to XML
         writer.write("<entry>\n");
         writer.write("<key>" + multiWordTokenString + "</key>\n");
         writer.write("</entry>\n");

         // get next line
         line = reader.readLine();
      } // all dictionary lines are processed
      reader.close();

      // write dictionary XML lead out
      writer.write("</entries>\n");
      writer.write("</typeCollection>\n");
      writer.write("</dictionary>\n");
      writer.close();

      // try to delete PEAR temp dir
      if (tempDir != null) {
         FileUtils.deleteRecursive(tempDir);
         if (tempDir != null) {
            List files = FileUtils.getFiles(tempDir, true);
            if (files != null) {
               for (int i = 0; i < files.size(); i++) {
                  ((File) files.get(i)).deleteOnExit();
               }
            }
         }
      }

      return true;
   }

   /**
    * returns text with replaced XML entities
    * 
    * @param text
    *           input text
    * 
    * @return XML valid text
    */
   private static String replaceXMLEntities(String text) {
      StringBuffer buffer = new StringBuffer();
      for (int i = 0; i < text.length(); i++) {
         char character = text.charAt(i);
         if (DictionaryCreator.entities.containsKey(character)) {
            buffer.append(DictionaryCreator.entities.get(character));
         } else {
            buffer.append(character);
         }
      }
      return buffer.toString();
   }
}
