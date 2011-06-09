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
package org.apache.uima.examples.tagger.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import junit.framework.TestCase;

import org.apache.uima.TokenAnnotation;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.examples.tagger.HMMTagger;
import org.apache.uima.examples.tagger.Viterbi;
import org.apache.uima.jcas.JCas;
import org.apache.uima.test.junit_extension.JUnitExtension;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.XMLInputSource;
import org.apache.uima.util.XMLParser;

//This test was run with JUnit3

public class TaggerTest extends TestCase {

  private HMMTagger hmm;

  private List<String> sent; // sentence

  private List<String> gold_standard;

  private List<String> tagger_output;

  /**
   * Set up the test fixture
   */

  protected void setUp() {

    hmm = new HMMTagger();
    gold_standard = new ArrayList<String>();
    sent = new ArrayList<String>();
    tagger_output = new ArrayList<String>();

  }

  /**
   * Tests tagging for German.
   * 
   */
  @SuppressWarnings("unchecked")
  public void testGermanTagger() {

    System.out.println("Testing German Model... ");
    List POS = new ArrayList();

    try {
      hmm.my_model = HMMTagger.get_model("resources/german/TuebaModel.dat");
    } catch (Exception e) {
      System.out.println("Model which is supposed to be used for testing does not exist");
    }
    System.out.println(hmm.my_model.word_probs.size() + " distinct words in the model");

    Iterator<Entry<String, Map<String, Double>>> keyValuePairs = hmm.my_model.word_probs.entrySet()
        .iterator(); // iterate over words

    for (int i = 0; i < hmm.my_model.word_probs.size(); i++) {
      Map.Entry<String, Map<String, Double>> entry = (Map.Entry<String, Map<String, Double>>) keyValuePairs
          .next();
      Object key = entry.getKey();
      Map<String, Double> pos = (Map) hmm.my_model.word_probs.get(key); // map of possible pos-s of
      // the word
      Object[] pos_s = pos.entrySet().toArray(); // for iteration over possible pos_s

      for (int u = 0; u < pos_s.length; u++) {

        Map.Entry<String, Map<String, Double>> entry2 = (Map.Entry<String, Map<String, Double>>) pos_s[u];
        Object key2 = entry2.getKey(); // pos of a word
        if (POS.contains(key2)) {
          continue;

        } else {
          POS.add(key2);
        }
      }

    }
    Collections.sort(POS);
    System.out.println("Number of part-of-speech tags used: " + POS.size());
    System.out.println("These are:  " + POS);

    System.out.println("Testing German trigram tagger..");

    sent.add("Jerry");
    sent.add("liebt");
    sent.add("Wansley");
    sent.add(".");

    System.out.println(sent);

    hmm.N = 3;
    // hmm.END_OF_SENT_TAG = "$.";

    String[] out = new String[] { "NE", "VVFIN", "NE", "$." };
    gold_standard.addAll(Arrays.asList(out));
    tagger_output = Viterbi.process(hmm.N, sent, hmm.my_model.suffix_tree,
        hmm.my_model.suffix_tree_capitalized, hmm.my_model.transition_probs,
        hmm.my_model.word_probs, hmm.my_model.lambdas2, hmm.my_model.lambdas3, hmm.my_model.theta);
    System.out.println("expected: " + gold_standard);
    System.out.println("tagger output: " + tagger_output);
  }

  /**
   * Tests English trigram tagger
   * 
   */
  @SuppressWarnings("unchecked")
  public void testEnglishTagger() {

    System.out.println("Tesing English Model... ");
    List POS = new ArrayList();

    try {
      hmm.my_model = HMMTagger.get_model("resources/english/BrownModel.dat");
    } catch (Exception e) {
      System.out.println("Model which is supposed to be used for testing does not exist");
    }
    System.out.println(hmm.my_model.word_probs.size() + " distinct words in the model");

    Iterator<Entry<String, Map<String, Double>>> keyValuePairs = hmm.my_model.word_probs.entrySet()
        .iterator(); // iterate over words

    for (int i = 0; i < hmm.my_model.word_probs.size(); i++) {
      Map.Entry<String, Map<String, Double>> entry = (Map.Entry<String, Map<String, Double>>) keyValuePairs
          .next();
      Object key = entry.getKey();
      Map<String, Double> pos = (Map) hmm.my_model.word_probs.get(key); // map of possible pos-s of
      // the word
      Object[] pos_s = pos.entrySet().toArray(); // for iteration over possible pos_s

      for (int u = 0; u < pos_s.length; u++) {

        Map.Entry<String, Map<String, Double>> entry2 = (Map.Entry<String, Map<String, Double>>) pos_s[u];
        Object key2 = entry2.getKey(); // pos of a word
        if (POS.contains(key2)) {
          continue;

        } else {
          POS.add(key2);
        }
      }

    }
    Collections.sort(POS);
    System.out.println("Number of part-of-speech tags used: " + POS.size());
    System.out.println("These are:  " + POS);

    System.out.println("Testing English trigram tagger...");

    sent.add("Jerry");
    sent.add("loves");
    sent.add("Wansley");
    sent.add(".");

    System.out.println(sent);

    hmm.N = 3;
    // hmm.END_OF_SENT_TAG = "$.";

    String[] out = new String[] { "np", "vbz", "np", "." };
    gold_standard.addAll(Arrays.asList(out));
    tagger_output = Viterbi.process(hmm.N, sent, hmm.my_model.suffix_tree,
        hmm.my_model.suffix_tree_capitalized, hmm.my_model.transition_probs,
        hmm.my_model.word_probs, hmm.my_model.lambdas2, hmm.my_model.lambdas3, hmm.my_model.theta);
    System.out.println("expected: " + gold_standard);
    System.out.println("tagger output: " + tagger_output);
  }

  /**
   * Run tagger on Moby Dick and compare result to pre-computed XCAS. This test case is a bit
   * brittle. First of all, it requires the uimaj-core project as a neighbor, otherwise it won't
   * find moby.txt, the test input.  Secondly, the reference output is only a list of POS tags.
   * This is so the file doesn't get so big.  Finally, if anything changes, even improvements, this
   * test will most likely fail and will need to be updated.  There's an unused method below that
   * can be used to write a new set of POS tags when the old ones become obsolete.
   */
  public void testMobyDick() {
    try {
      XMLParser xmlParser = UIMAFramework.getXMLParser();
      XMLInputSource xmlInputSource = new XMLInputSource("desc/HmmTaggerAggregate.xml");
      AnalysisEngine taggerEngine = UIMAFramework.produceAnalysisEngine(xmlParser
          .parseResourceSpecifier(xmlInputSource));
      String text = null;
      try {
        text = FileUtils.file2String(new File("../uimaj-core/src/test/resources/data/moby.txt"),
            "utf-8");
      } catch (FileNotFoundException e) {
        System.err
            .println("Warning: this test case runs only if uimaj-core is a sister project to the tagger project; otherwise, the test input data is not available.");
        System.err.println("Skipping test");
        return;
      }
      JCas cas = taggerEngine.newJCas();
      cas.setDocumentText(text);
      taggerEngine.process(cas);
      List<String> savedTags = readSavedTagList();
      List<String> currentTags = getCurrentTagList(cas);
      assertTrue("List of tags is not the same length", savedTags.size() == currentTags.size());
      for (int i = 0; i < savedTags.size(); i++) {
        assertEquals("Different tags at position " + i, savedTags.get(i), currentTags.get(i));
      }
    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
    }
  }

  private List<String> readSavedTagList() throws IOException {
    List<String> tags = new ArrayList<String>();
    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(
        "src/test/resources/moby-tag-list.txt"), "utf-8"));
    String tag = null;
    while ((tag = reader.readLine()) != null) {
      tags.add(tag);
    }
    return tags;
  }

  private List<String> getCurrentTagList(JCas cas) {
    List<String> tagList = new ArrayList<String>();
    AnnotationIndex tokenIndex = cas.getAnnotationIndex(TokenAnnotation.type);
    FSIterator tokIt = tokenIndex.iterator();
    TokenAnnotation token = null;
    for (tokIt.moveToFirst(); tokIt.isValid(); tokIt.moveToNext()) {
      token = (TokenAnnotation) tokIt.get();
      tagList.add(token.getPosTag());
    }
    return tagList;
  }

  /**
   * @param cas
   * @throws IOException
   * @throws UnsupportedEncodingException
   */
  private void printPosTags(JCas cas) throws UnsupportedEncodingException, IOException {
    Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
        "moby-tag-list.txt"), "utf-8"));
    AnnotationIndex tokenIndex = cas.getAnnotationIndex(TokenAnnotation.type);
    FSIterator tokIt = tokenIndex.iterator();
    TokenAnnotation token = null;
    for (tokIt.moveToFirst(); tokIt.isValid(); tokIt.moveToNext()) {
      token = (TokenAnnotation) tokIt.get();
      writer.write(token.getPosTag());
      writer.write('\n');
    }
    writer.close();
  }
}
