/*
 *Licensed to the Apache Software Foundation (ASF) under one
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
 * 
 */

package org.apache.uima.examples.tagger.trainAndTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Reader for Brown Corpus from NLTK Distribution (nltk.sourceforge.net)
 */
public class BrownReader implements CorpusReader{

  
  /**
   * Reads Brown Corpus from NLTK Distribution Format. Iterates over all files in the directory,
   * which are in a sentence per line format, and returns all tokens in the collection in a List of
   * {@link Token}s}
   * 
   */

  

  public List<Token> read_corpus(String directory, MappingInterface mapping) {

 
    // Reads file names from Directory and returns an array of file names in the directory
    File dir = new File(directory);
    String[] list = dir.list();
    String[] new_list = new String[list.length];
    for (int i = 0; i < list.length; i++) {
      String dir_list = directory + "/" + list[i];
      new_list[i] = dir_list;
    }

    
    String line;
    List<Token> text = new ArrayList<Token>();

    // simple tokenizer: match one or more spaces
    // String delimiters = " +";

    Pattern delimiters = Pattern.compile("[ ]+");
    // Split input with the pattern

    int line_count = 0;

    for (int i = 0; i < new_list.length; i++) {
      String file = new_list[i];
      try {
        BufferedReader in = new BufferedReader(new FileReader(file));

        while ((line = in.readLine()) != null) {
          if (line.trim().length() > 0) {
            line_count += 1;
            String[] tokens = delimiters.split(line);

            for (int x = 0; x < tokens.length; x++) { // iterate over tokens with their
              // corresponding POS
              tokens[x] = tokens[x].replaceAll("[\\n\\t]+", "");

              // for cases in Brown corpus like "//in" :(
              if (tokens[x].startsWith("//")) {
                String t = tokens[x].replace("//", "per/");
                tokens[x] = t;
              }

              // and that was not all, further for cases like:
              // "before/in /l//nn and/cc AAb//nn or/cc /r//nn ./. " (text j in NLTK distribution)
              if (tokens[x].startsWith("/", 0)) {
                String t = tokens[x].substring(1);
                tokens[x] = t;
              }
              // for cases like : "AAb//nn" (s. above)
              if (tokens[x].contains("//")) {
                int j = tokens[x].indexOf("//");

                String t = tokens[x].substring(0, j) + tokens[x].substring(j + 1);
                tokens[x] = t;
              }

              // for cases in brown like: "lb/day/nn" (text 'J', sentence N. 8940)
              int first = tokens[x].indexOf("/");
              int last = tokens[x].lastIndexOf("/");
              if (first != last) {
                String[] zw = tokens[x].split("/");
                StringBuffer t = new StringBuffer();
                for (int w = 0; w < zw.length - 1; w++) {

                  t.append(zw[w]);
                }

                t.append("/");
                t.append(zw[zw.length - 1]);
                tokens[x] = t.toString();
              }

              String[] t = tokens[x].split("/");

              Token token = new Token(t[0], t[1]);

              text.add(token);
            }
          }
        }
        in.close();
      } catch (IOException e) {
        System.out.println(e);
        return null;
      }
    }
    System.out.println(line_count + " sentences in the corpus");
    if (mapping != null) {
      text = mapping.map_tags(text); // in case we need to map
    }
    return text;
  }
  /*
   * public static void main(String[] args) { // BrownReader b = new BrownReader("Brown_test"); }
   */
}
