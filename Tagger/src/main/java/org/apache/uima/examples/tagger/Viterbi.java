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

package org.apache.uima.examples.tagger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.uima.examples.tagger.trainAndTest.ModelGeneration;

/**
 * Viterbi Algorithm: Given a model and a sequence of observations, what is the most likely sequence
 * of states in the model that produces the observations?
 */
public class Viterbi {

  @SuppressWarnings("unchecked")
  public static Map<String, List> init_probs(Map<String, Double> pos_s) {
    Map<String, List> init_probs = new HashMap<String, List>();
    double p_init = Math.log(1.0); // symbolic initial probability, at the
    // moment equal for all tags

    Iterator a_pos = pos_s.entrySet().iterator();

    for (int h = 0; h < pos_s.size(); h++) { // for each of available parts of speech
      Map.Entry entry_init = (Map.Entry) a_pos.next();
      String key_init = (String) entry_init.getKey(); // get the tag (state)
      Double value_init = (Double) entry_init.getValue(); // get its P(w|t)
      Double p_local = p_init; // get the 'initial' probability of this tag,
      // it#s logged
      List vpath_list = new ArrayList<String>();
      List init_list = new ArrayList(3);
      double p; // P(tag|preceding_tag, here it is just a symbolic initial state
      // ".")
      vpath_list.add(key_init);

      p = p_local + Math.log(value_init); // Prob(this tag|beginning of sequence) x P(w|t)

      init_list.add(p);
      init_list.add(vpath_list);
      init_list.add(p);
      init_probs.put(key_init, init_list);
    }
    return init_probs;
  }

  /**
   * @param N
   * @param sentence
   * @param suffix_tree
   * @param suffix_tree_cap
   * @param transition_probs
   * @param word_probs
   * @param lambdas2
   * @param lambdas3
   * @param theta
   * @return the viterbi path
   */
  @SuppressWarnings("unchecked")
  public static List process(int N, List<String> sentence,
      Map<String, Map<String, Double>> suffix_tree,
      Map<String, Map<String, Double>> suffix_tree_cap, Map<NGram, Double> transition_probs,
      Map<String, Map<String, Double>> word_probs, double[] lambdas2, double[] lambdas3,
      double theta) {

    NGram ngram = null;

    NGram ngram2 = null;

    // here we add something like an "end-of-sentence" tag at the beginning of the first sentence,
    // to get probabilities for a certain tag to be at the beginning of the sentence.
    sentence.add(0, ".");

    /** ******************************************************** */

    Map<String, List> all = new HashMap<String, List>(); // saves paths to the
    // current token
    // int cardinals = 0;
    /** ********************************************************* */
    /** * */
    /** ********************************************************* */

    for (int i = 0; i < sentence.size() - 1; i++) { // for every token (observation) in a sentence

      /*
       * Get initial probabilities for the first token in a sentence, at the moment we just make
       * them all equal 1.0 through the init_probs() function
       */
      Map<String, Double> available_pos = new HashMap<String, Double>();
      String token;

      if (i == 0) {

        // lookup for the non-capitalized variant of the word for the first word in a sentence and
        // weight by relative frequencies of the corresponding forms and sum them
        token = sentence.get(1);
        String non_cap = token.toLowerCase();

        if (word_probs.containsKey(token) || word_probs.containsKey(non_cap)) {
          if (word_probs.containsKey(non_cap)) {
            available_pos = word_probs.get(non_cap);
          } else {
            available_pos = word_probs.get(token);
          }

        } else
        // 2. smoothed suffix- the strategy described in (Brants, 2000)
        {
          Map<String, Map<String, Double>> suffix_tree_local;
          // if a word is capitalized ...
          if (ModelGeneration.capitalized(sentence.get(1))) {
            suffix_tree_local = suffix_tree_cap;
          } else {
            suffix_tree_local = suffix_tree;
          }

          char[] unknown = sentence.get(1).toCharArray();

          for (int j = 0; j < unknown.length; j++) {
            // get the longest suffix distribution from the suffix tree
            String suffix = sentence.get(1).substring(j, unknown.length);
            if (suffix_tree_local.containsKey(suffix)) {
              // get available POS with corresponding counts for the longest suffix
              Map available_pos_zwischen = suffix_tree_local.get(suffix);
              // smoothing by successive abstraction for the probabilities of the longest suffix,
              for (int suf = j + 1; suf < unknown.length; suf++) {
                String subsuffix = sentence.get(1).substring(suf, unknown.length);
                Map pos = new HashMap();
                if (suffix_tree_local.containsKey(subsuffix)) {
                  pos = suffix_tree_local.get(subsuffix);
                  Iterator posValuePairs = pos.entrySet().iterator();
                  while (posValuePairs.hasNext()) {
                    Map.Entry<String, Double> entry = (Map.Entry<String, Double>) posValuePairs
                        .next();
                    Object key = entry.getKey();
                    Double value = entry.getValue();
                    if (available_pos_zwischen.containsKey(key)) {
                      double zwischen_prob = (value + theta
                          * (Double) available_pos_zwischen.get(key))
                          / (1 + theta);
                      available_pos_zwischen.put(key, zwischen_prob);
                    }/*
                      * else { available_pos_zwischen.put(key, value); }
                      */
                  }
                } else {
                  Iterator posValuePairs2 = available_pos_zwischen.entrySet().iterator();
                  while (posValuePairs2.hasNext()) {
                    Map.Entry<String, Double> entry = (Map.Entry<String, Double>) posValuePairs2
                        .next();
                    Object key = entry.getKey();
                    Double value = entry.getValue();
                    // smooth suffix probability P(suffix|tag)
                    double zwischen_prob = (0 + theta * value) / (1 + theta);
                    pos.put(key, zwischen_prob);

                  }
                  available_pos_zwischen = pos;
                }

              }
              available_pos = available_pos_zwischen;
              break;
            }
            // 
            else if (j == unknown.length - 1) {
              if (word_probs.containsKey("(")) {available_pos = word_probs.get("(");} else {available_pos.put("(", Double.MIN_VALUE);}
            }
          }
        }
        all.putAll(init_probs(available_pos));
        continue; // go over to the next token
      }

      token = sentence.get(i);

      Map<String, List> m = new HashMap<String, List>(); // saves paths to the
      // next token

      /** ********************************************************* */
      /** INDUCTION (~forward algorithm) * */
      /** ********************************************************* */
      /*
       * At INDUCTION step the algorithm looks at the total and local possibilities of the next
       * state(=tag), given a current observation (=token) If a token is known, we limit the coming
       * tags by only possible ones for this token from the dictionary; otherwise: we use suffix
       * analysis to find possible POS tags for the unknown words
       */

      Map<String, Double> possible_pos_next = new HashMap<String, Double>(); // possible pos of the
      // next token

      if (word_probs.containsKey(sentence.get(i + 1))) { // if the next token is known
        possible_pos_next = word_probs.get(sentence.get(i + 1)); // get possible POS of the next
      } else // if the token is unknown, then get possible tags from the suffix analysis
      {
        Map<String, Map<String, Double>> suffix_tree_local;
        // if a word is capitalized ...
        if (ModelGeneration.capitalized(sentence.get(i + 1))) {
          suffix_tree_local = suffix_tree_cap;
        } else {
          suffix_tree_local = suffix_tree;
        }

        char[] unknown = sentence.get(i + 1).toCharArray();
        for (int j = 0; j < unknown.length; j++) {
          // get the longest suffix distribution from the suffix tree
          String suffix = sentence.get(i + 1).substring(j, unknown.length);
          if (suffix_tree_local.containsKey(suffix)) {
            // get available POS with corresponding counts for the longest suffix
            Map available_pos_zwischen = suffix_tree_local.get(suffix);
            // smooth suffix probabilities
            for (int suf = j + 1; suf < unknown.length; suf++) {
              String subsuffix = sentence.get(i + 1).substring(suf, unknown.length);
              Map pos = new HashMap();
              if (suffix_tree_local.containsKey(subsuffix)) {
                pos = suffix_tree_local.get(subsuffix);
                Iterator posValuePairs = pos.entrySet().iterator(); // iterate over words
                while (posValuePairs.hasNext()) {
                  Map.Entry<String, Double> entry = (Map.Entry<String, Double>) posValuePairs
                      .next();
                  Object key = entry.getKey();
                  Double value = entry.getValue();
                  if (available_pos_zwischen.containsKey(key)) {
                    // smooth suffix probability P(suffix|tag)
                    double zwischen_prob = (value + theta
                        * (Double) available_pos_zwischen.get(key))
                        / (1 + theta);
                    available_pos_zwischen.put(key, zwischen_prob);
                  }
                }
              } else {
                Iterator posValuePairs2 = available_pos_zwischen.entrySet().iterator(); // iterate
                // over
                // words
                while (posValuePairs2.hasNext()) {
                  Map.Entry<String, Double> entry = (Map.Entry<String, Double>) posValuePairs2
                      .next();
                  Object key = entry.getKey();
                  Double value = entry.getValue();
                  // smooth suffix probability P(suffix|tag)
                  double zwischen_prob = (0 + theta * value) / (1 + theta);
                  pos.put(key, zwischen_prob);

                }
                available_pos_zwischen = pos;
              }

            }
            possible_pos_next = available_pos_zwischen;
            break;
          } // for unexpected cases which are neither in the dictionary nor in the suffix analysis
          else if (j == unknown.length - 1) {
            if (possible_pos_next.containsKey("(")) {possible_pos_next = word_probs.get("(");} else {possible_pos_next.put("(", Double.MIN_VALUE);}
          }
        }
      }

      Iterator keyValuePairs_next = possible_pos_next.entrySet().iterator();
      for (int u = 0; u < possible_pos_next.size(); u++) // for every possible tag of the next
      // token, if the token is known..
      {
        Map.Entry entry_next = (Map.Entry) keyValuePairs_next.next();
        String key_next = (String) entry_next.getKey();
        Double value_next = (Double) entry_next.getValue(); // get
        double total_prob = 0.0; // just for fun, for forward algorithm
        List max_viterbi_path = new ArrayList<String>(); // viterbi path
        double max_viterbi_prob = 0; // viterbi probability

        Iterator keyValuePairs = all.entrySet().iterator();

        NGram nextNgram = new NGram(key_next);
        double nextNgramTransProb = 0.0;
        if (transition_probs.containsKey(nextNgram)) {
          nextNgramTransProb = transition_probs.get(nextNgram);
        }

        /** ********************************************************************************** */
        /*
         * calculates for the given next token which of the possible paths to the current token are
         * more possible to end in the next token
         */

        for (int y = 0; y < all.size(); y++) // for every possible tag
        // of this token (path
        // till now)
        {
          Map.Entry entry = (Map.Entry) keyValuePairs.next();
          Object key = entry.getKey(); // get possible tag for the current token
          List probs = all.get(key);

          // Double prob_local = (Double) probs.get(0); // total_probability for
          // forward algorihtm
          List<String> path_local = (List) probs.get(1); // path to that token

          Double vprob_local = (Double) probs.get(2); // get viterbi probability for this token

          /*
           * The following 4 lines can be modified to get any other number of N-grams
           */

          /* -- from here -- */
          // um den ersten trigramm abzufangen
          if (N == 3 && i == 1) {
            // ngram = path_local.get(path_local.size() - 1) + "_" + key_next;
            ngram = new NGram(path_local.get(path_local.size() - 1), key_next);

          } else if (N == 2) {

            // ngram = path_local.get(path_local.size() - 1) + "_" + key_next;
            ngram = new NGram(path_local.get(path_local.size() - 1), key_next);
          } else if (N == 3 && i != 1) {
            // ngram = path_local.get(path_local.size() - 2) + "_"
            // + path_local.get(path_local.size() - 1) + "_" + key_next;
            // ngram2 = path_local.get(path_local.size() - 1) + "_" + key_next;
            ngram = new NGram(path_local.get(path_local.size() - 2), path_local.get(path_local
                .size() - 1), key_next);
            ngram2 = new NGram(path_local.get(path_local.size() - 1), key_next);
          } else {
            System.err.println("at the moment only bi-and trigramms are supported");
          }

          /* -- till here -- */

          double pp = 0;

          // smoothing only unknown n-grams strategy
          /*
           * if (transition_probs.containsKey(ngram)) { // P(t2|t1) || use logs because of small
           * numbers: log(pq) = log(p) + // log(q); if model parameters are stored logged then only
           * addition // is performed at runtime pp = value_next + transition_probs.get(ngram); }
           * else {
           */
          // System.err.println("UNKNOWN NGRAM");
          // At the moment we smooth both - known and unknown n-grams (seems to perform better)
          double ppp;
          if (N == 3 && i == 1) {
            double lambda1 = lambdas2[0];
            double lambda2 = lambdas2[1];

            ppp = (transition_probs.containsKey(ngram)) ? ((lambda2 * transition_probs.get(ngram)) + (lambda1 * transition_probs
                .get(nextNgram)))
                : (lambda1 * nextNgramTransProb);
            pp = Math.log(value_next) + Math.log(ppp); // P(t|w) * P(t1,t2,t3)
          } else if (N == 3) {
            double lambda1 = lambdas3[0];
            double lambda2 = lambdas3[1];
            double lambda3 = lambdas3[2];

            if (transition_probs.containsKey(ngram)) {

              double transProbNgram2 = (transition_probs.containsKey(ngram2) ? transition_probs
                  .get(ngram2) : 0.0);
              ppp = (lambda3 * transition_probs.get(ngram)) + (lambda2 * transProbNgram2)
                  + (lambda1 * nextNgramTransProb);
            } else {
              // System.out.println(ngram2);
              ppp = (transition_probs.containsKey(ngram2)) ? ((lambda2 * transition_probs
                  .get(ngram2)) + (lambda1 * nextNgramTransProb)) : (lambda1 * nextNgramTransProb);
            }
            pp = Math.log(value_next) + Math.log(ppp);
          }
          if (N == 2) {
            double lambda1 = lambdas2[0];
            double lambda2 = lambdas2[1];

            ppp = (transition_probs.containsKey(ngram)) ? ((lambda2 * transition_probs.get(ngram)) + (lambda1 * transition_probs
                .get(nextNgram)))
                : (lambda1 * transition_probs.get(nextNgram));
            pp = Math.log(value_next) + Math.log(ppp);
          }

          vprob_local += pp;

          if (y == 0) {
            max_viterbi_prob = vprob_local;
          }
          if (vprob_local >= max_viterbi_prob) {

            max_viterbi_prob = vprob_local;
            max_viterbi_path = new ArrayList(path_local);
            max_viterbi_path.add(key_next);
          }
        }

        List v = new ArrayList(3);
        v.add(total_prob);
        v.add(max_viterbi_path);
        v.add(max_viterbi_prob);
        m.put(key_next, v); // m contains total of the probabilities ending in
        // this step,
        // i.e. for every possible pos of the next token we store all the
        // possible paths to it
        // System.out.println("m:  "+m.entrySet().toString());
      }
      all = m;
    }
    /** ****************************************************** */
    /***********************************************************************************************
     * Best sequence identification / Termination / Backtracking /
     **********************************************************************************************/

    List max_viterbi_path = new ArrayList<String>(); // viterbi path
    double max_viterbi_prob = 0.0; // viterbi probability

    Iterator keyValuePairs_all = all.entrySet().iterator(); // iterate over them

    for (int j = 0; j < all.size(); j++) // for all computed possible paths,
    // choose the one with the maximum
    // local probability
    {
      Map.Entry entry = (Map.Entry) keyValuePairs_all.next();

      ArrayList value = (ArrayList) entry.getValue();

      List path_local = (List) value.get(1);
      Double vprob_local = (Double) value.get(2);

      if (j == 0) {
        max_viterbi_prob = vprob_local;
      }
      if (vprob_local >= max_viterbi_prob) {
        max_viterbi_path = path_local;
        max_viterbi_prob = vprob_local;
      }

    }
    List result = new ArrayList(3);
    result.add(max_viterbi_path);
    result.add(max_viterbi_prob);
    return max_viterbi_path;
  }
}
