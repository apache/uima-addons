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



// 

package org.apache.uima.examples.tagger;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

/**
 * General tagger interface in case one would want to define further types of taggers.
 * 
 * Known implementations: {@link HMMTagger} using {@link Viterbi} algorithm 
 * to compute the most probable path of parts of speech for a given sequence of tokens
 * @see Viterbi
 * @see HMMTagger
 */


public interface Tagger {

  
  /**
   * Instantiates {@code MODEL} for current tagger
   */
  
  public void initialize(UimaContext aContext) throws ResourceInitializationException;
  
  /**
   * Trains a new model for tagger, if a training is defined in {@code tagger.properties} file
   * @see org.apache.uima.examples.tagger.trainAndTest#ModelGeneration 
   */
  
   
  public void process(JCas aJCas) throws AnalysisEngineProcessException;
  
}
