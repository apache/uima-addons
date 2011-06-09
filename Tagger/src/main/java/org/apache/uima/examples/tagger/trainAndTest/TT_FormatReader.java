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

/** 
 * Tree Tagger Format Reader: a word per line with possibly html-tags, 
 * which are ignored and 2/3 columns separated by tabs.
 * Columns are: "word \t tag \t lemma"
 * 
 */

package org.apache.uima.examples.tagger.trainAndTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TreeTagger Format Reader, i.e. "word-per-line with tags separated with tabs"
 *
 */

public class TT_FormatReader implements CorpusReader{
 
  public List<Token> read_corpus(String file, MappingInterface Mapping) {

    // Text is already tokenized

    int line_count = 0;
    String line;
    List<Token> text = new ArrayList<Token>();
    try {
      BufferedReader in = new BufferedReader(new FileReader(file));

      while ((line = in.readLine()) != null) {
        if (line.trim().length() > 0) {
          line_count += 1;
          String[] t = line.split("\t");
          if (t.length >= 2) {
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
    if (Mapping != null) {
      text = Mapping.map_tags(text); // in case we need to map
    }
    return text;
  }

}
