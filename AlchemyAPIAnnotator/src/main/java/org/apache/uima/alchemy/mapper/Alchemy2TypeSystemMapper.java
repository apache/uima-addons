/**
 * 	Licensed to the Apache Software Foundation (ASF) under one
 * 	or more contributor license agreements.  See the NOTICE file
 * 	distributed with this work for additional information
 * 	regarding copyright ownership.  The ASF licenses this file
 * 	to you under the Apache License, Version 2.0 (the
 * 	"License"); you may not use this file except in compliance
 * 	with the License.  You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * 	Unless required by applicable law or agreed to in writing,
 * 	software distributed under the License is distributed on an
 * 	"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * 	KIND, either express or implied.  See the License for the
 * 	specific language governing permissions and limitations
 * 	under the License.
 */
package org.apache.uima.alchemy.mapper;

import java.util.HashMap;
import java.util.Map;

import org.apache.uima.alchemy.digester.domain.AnnotatedResults;
import org.apache.uima.alchemy.digester.domain.CategorizationResults;
import org.apache.uima.alchemy.digester.domain.ConceptResults;
import org.apache.uima.alchemy.digester.domain.EntitiesResults;
import org.apache.uima.alchemy.digester.domain.KeywordResults;
import org.apache.uima.alchemy.digester.domain.LanguageDetectionResults;
import org.apache.uima.alchemy.digester.domain.MicroformatsResults;
import org.apache.uima.alchemy.digester.domain.Results;
import org.apache.uima.alchemy.digester.domain.SentimentAnalysisResults;
import org.apache.uima.alchemy.mapper.exception.MappingException;
import org.apache.uima.alchemy.mapper.processor.AlchemyOutputProcessor;
import org.apache.uima.alchemy.mapper.processor.AnnotatedEntitiesProcessor;
import org.apache.uima.alchemy.mapper.processor.CategorizationProcessor;
import org.apache.uima.alchemy.mapper.processor.ConceptTaggingProcessor;
import org.apache.uima.alchemy.mapper.processor.KeywordProcessor;
import org.apache.uima.alchemy.mapper.processor.LanguageProcessor;
import org.apache.uima.alchemy.mapper.processor.MicroformatsProcessor;
import org.apache.uima.alchemy.mapper.processor.RankedEntitiesProcessor;
import org.apache.uima.alchemy.mapper.processor.SentimentAnalysisProcessor;
import org.apache.uima.jcas.JCas;

public class Alchemy2TypeSystemMapper {

  private static Map<Class<? extends Results>, AlchemyOutputProcessor> processors = new HashMap<Class<? extends Results>, AlchemyOutputProcessor>();

  public static void mapResultsToTypeSystem(Results results, JCas aJCas) throws MappingException {
    try {
      setLanaguage(results, aJCas);
      if (processors.isEmpty())
        initializeProcessors();
      processors.get(results.getClass()).process(aJCas, results);
    } catch (Exception e) {
      throw new MappingException(e);
    }
  }

  private static void initializeProcessors() {
    processors.put(AnnotatedResults.class, new AnnotatedEntitiesProcessor());
    processors.put(CategorizationResults.class, new CategorizationProcessor());
    processors.put(ConceptResults.class, new ConceptTaggingProcessor());
    processors.put(EntitiesResults.class, new RankedEntitiesProcessor());
    processors.put(KeywordResults.class, new KeywordProcessor());
    processors.put(LanguageDetectionResults.class, new LanguageProcessor());
    processors.put(MicroformatsResults.class, new MicroformatsProcessor());
    processors.put(SentimentAnalysisResults.class, new SentimentAnalysisProcessor());
  }

  private static void setLanaguage(Results results, JCas aJCas) {
    String language = results.getLanguage();
    if (language != null)
      aJCas.setDocumentLanguage(language);
  }

}
