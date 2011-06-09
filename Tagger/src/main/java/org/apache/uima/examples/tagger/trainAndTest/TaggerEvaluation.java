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

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.uima.examples.tagger.HMMTagger;
import org.apache.uima.examples.tagger.Viterbi;


/**
 * Evaluation of Tagger 
 * NB. As it is implemented at the moment, to be used just for small tests with
 * small files. Takes quite a long time with big files. If one needs to test big files,
 * it is better to implement iteration over sentences and not over the complete list of {@code Tokens}.
 */
public class TaggerEvaluation {

  @SuppressWarnings("unchecked")
  static void get_eval(ModelGeneration my_model, List<String> wordList, List<String> posList,
      List<String> TagList) {

    int wrong_tag = 0;
    int right_tags = 0;
    List unknown_list = new ArrayList<String>();
    int unknown_count = 0; // counter for erroneously tagged unknown words
    int unknown_all = 0; // counter for all unknown words
    List wrong_tags = new ArrayList<String>();
    Map wrong_tag_counts = new HashMap();
    Map<String, Map> wrong_tag_map = new HashMap();
    int words = wordList.size();
    for (int u = 0; u < posList.size(); u++) {

      if (!posList.get(u).equalsIgnoreCase((String) TagList.get(u))) {
        wrong_tags.add(posList.get(u));

        Integer freq = (Integer) wrong_tag_counts.get(posList.get(u));
        wrong_tag_counts.put(posList.get(u), (freq == null) ? 1 : freq + 1);

        if (wrong_tag_map.containsKey(posList.get(u))) {
          // if the token is already in a dictionary, then get its erronous tags
          Map tags = wrong_tag_map.get(posList.get(u));
          Integer freq2 = (Integer) tags.get(TagList.get(u));
          // if a given POS is already in its values, then add its corresponding count, otherwise
          // add a POS value with a count of 1
          tags.put((String) TagList.get(u), (freq2 == null) ? 1 : freq2 + 1);
        } else {
          Map tags = new HashMap<String, Double>();
          tags.put((String) TagList.get(u), 1);
          wrong_tag_map.put(posList.get(u), tags);
        }

        wrong_tag += 1;
        if (!my_model.word_probs.containsKey(wordList.get(u))) {
          unknown_list.add(wordList.get(u));
          // System.out.println(wordList.get(u));
          unknown_count += 1;
        }
      } else {
        right_tags += 1;
      }
      // count unknown words
      if (!my_model.word_probs.containsKey(wordList.get(u))) {
        unknown_all += 1;
      }
    }

    System.out.println(wordList.size() + " tokens in the corpus");
    double percent_unknown_errors = (double) unknown_count / (double) wrong_tag;
    System.out.println("percent of unknown words among erronously tagged: " + unknown_count + " ("
        + percent_unknown_errors * 100 + "%" + ")");
    System.out.println("percent of correctly tagged unknown words from all unknown: "
        + (((double) unknown_all - unknown_count) / (double) unknown_all) * 100 + "%");
    System.out.println("total words: " + words);
    System.out.println("total unknown words: " + unknown_all + "  == " + (double) unknown_all
        / (double) words + "%");
    double percent_errors = (double) wrong_tag / (double) words;
    System.out.println("total errors" + wrong_tag + " which makes up " + percent_errors
        + " of tokens");

    double accuracy = (double) right_tags / (double) words;
    System.out.println("accuracy:  " + accuracy);

    ArrayList sortedValues = new ArrayList(wrong_tag_counts.values());
    Collections.sort(sortedValues);

    // System.out.println(sortedValues.toString());
    Object[] keys = wrong_tag_counts.keySet().toArray();
    Object[] keys2 = wrong_tag_map.keySet().toArray();
    for (int i = 0; i < keys.length; i++) {

      System.out.print(keys[i] + "  ");
      System.out.println(wrong_tag_counts.get(keys[i]));

      System.out.print(keys2[i]);
      System.out.print(":");
      System.out.println(wrong_tag_map.get(keys2[i]));

    }
  }

  /**
   * @param args
   */
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {

    ModelGeneration my_model;
    CorpusReader reader;
    MappingInterface MAPPING;
    String file;

    try {
      // Get configuration parameter values
      String paramFile = "tagger.properties";

      // create and load default properties
      Properties defaultProps = new Properties();
      FileInputStream in = new FileInputStream(paramFile);
      defaultProps.load(in);
      in.close();

      String n = defaultProps.getProperty("N");
      String MODEL = defaultProps.getProperty("MODEL_FILE");
      

      my_model = HMMTagger.get_model(MODEL);
      String t = defaultProps.getProperty("DO_MAPPING");

      my_model.N = Integer.parseInt(n);

      boolean DO_MAPPING = Boolean.valueOf(t);
      System.out.println("DO_MAPPING = " + DO_MAPPING);

      if (DO_MAPPING) {
        String m = defaultProps.getProperty("MAPPING");
        MAPPING = (MappingInterface) (Class.forName(m)).newInstance();
      } else {
        MAPPING = null;
      }
      String r = defaultProps.getProperty("CORPUS_READER");
      reader = (CorpusReader) (Class.forName(r)).newInstance();
      file = defaultProps.getProperty("GOLD_STANDARD");
      List<Token> corpus = reader.read_corpus(file, MAPPING);
      List<String> wordList = new ArrayList<String>();
     
      List<String> posList = new ArrayList<String>();

      for (int x = 0; x < corpus.size(); x++) { // iterate over tokens with their corresponding POS

        posList.add(corpus.get(x).pos);
        wordList.add(corpus.get(x).word);
      }
      List TagList = Viterbi.process(my_model.N, wordList, my_model.suffix_tree,
          my_model.suffix_tree_capitalized, my_model.transition_probs, my_model.word_probs,
          my_model.lambdas2, my_model.lambdas3, my_model.theta);

      TaggerEvaluation.get_eval(my_model, wordList, posList, TagList);

    } catch (Throwable th) {
      System.err.println(th);
    }

  }
}
