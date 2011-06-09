/**
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.apache.uima.alchemy.mapper.processor;

import org.apache.uima.alchemy.digester.domain.LanguageDetectionResults;
import org.apache.uima.alchemy.digester.domain.Results;
import org.apache.uima.alchemy.ts.language.LanguageFS;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;

public class LanguageProcessor implements AlchemyOutputProcessor {

  public void process(JCas cas, Results results) throws Exception {
    LanguageFS languageFS = new LanguageFS(cas);
    Type type = languageFS.getType();
    LanguageDetectionResults languageDetectionResults = (LanguageDetectionResults) results;
    languageFS.setFeatureValueFromString(type.getFeatureByBaseName("language"),
            languageDetectionResults.getLanguage());
    languageFS.setFeatureValueFromString(type.getFeatureByBaseName("iso6391"),
            languageDetectionResults.getIso6391());
    languageFS.setFeatureValueFromString(type.getFeatureByBaseName("iso6392"),
            languageDetectionResults.getIso6392());
    languageFS.setFeatureValueFromString(type.getFeatureByBaseName("iso6393"),
            languageDetectionResults.getIso6393());
    languageFS.setFeatureValueFromString(type.getFeatureByBaseName("ethnologue"),
            languageDetectionResults.getEthnologue());
    languageFS.setFeatureValueFromString(type.getFeatureByBaseName("nativeSpeakers"),
            languageDetectionResults.getNativeSpeakers());
    languageFS.setFeatureValueFromString(type.getFeatureByBaseName("wikipedia"),
            languageDetectionResults.getWikipedia());
    languageFS.addToIndexes();
  }

}
