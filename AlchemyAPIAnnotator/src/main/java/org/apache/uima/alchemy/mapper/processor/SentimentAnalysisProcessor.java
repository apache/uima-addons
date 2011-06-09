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

import org.apache.uima.alchemy.digester.domain.DocumentSentiment;
import org.apache.uima.alchemy.digester.domain.Results;
import org.apache.uima.alchemy.digester.domain.SentimentAnalysisResults;
import org.apache.uima.alchemy.ts.sentiment.SentimentFS;
import org.apache.uima.jcas.JCas;

public class SentimentAnalysisProcessor implements AlchemyOutputProcessor {

  public void process(JCas cas, Results results) throws Exception {
    DocumentSentiment docSentiment = ((SentimentAnalysisResults) results).getDocSentiment();
    if (docSentiment != null) {
      SentimentFS sentimentFS = new SentimentFS(cas);
      sentimentFS.setScore(docSentiment.getScore());
      sentimentFS.setSentimentType(docSentiment.getType());
      sentimentFS.addToIndexes();
    }
  }

}
