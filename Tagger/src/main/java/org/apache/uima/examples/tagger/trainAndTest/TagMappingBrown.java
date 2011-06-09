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

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.examples.tagger.trainAndTest.Token;

public class TagMappingBrown implements MappingInterface{

  /**
   * Defines mapping for List<{@link Token}>
   * E.g. if we need to map tags, given a list of {@code Tokens}, we need to map the 
   * {@code pos} field of every {@code Token} to a different {@code pos}.  
   * Basically the mapping performed in this class is just a case of simple "normalization",
   * we just discard compound tags of the Brown corpus here.
   */
  @SuppressWarnings("unchecked")
  public List map_tags(List tokens) {

    // for (int i=0; i<sentences.size(); i++){ // iterate over sentences

    List<Token> tokens2 = new ArrayList<Token>(tokens.size());

    for (int x = 0; x < tokens.size(); x++) { // iterate over tokens of the sentence with their
      // corresponding POS
      Token current_token = (Token)tokens.get(x);
      String[] z = new String[2];
      if (current_token.pos.contains("+")) {
        z = current_token.pos.split("[+]");
        current_token.pos = z[0];
      }
      // for cases like : BEZ*
      if (current_token.pos.contains("*") && !(current_token.pos.startsWith("*"))) {
        z[0] = current_token.pos.replace("*", "");
        current_token.pos = z[0];
      }

      // for: *-h1
      if (current_token.pos.startsWith("*")) {
        z[0] = "*";
        current_token.pos = z[0];
      }
      if (current_token.pos.contains("-") && !(current_token.pos.startsWith("--"))) {
        z = current_token.pos.split("[-]");
        current_token.pos = z[0];
      }
      if (current_token.pos.startsWith("--")) {
        z[0] = "--";
        current_token.pos = z[0];
      }

      Token zwischen = new Token(current_token.word, current_token.pos);

      tokens2.add(zwischen);
    }
    return tokens2;

  }


}
