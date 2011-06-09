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

import org.apache.uima.alchemy.digester.domain.Keyword;
import org.apache.uima.alchemy.digester.domain.KeywordResults;
import org.apache.uima.alchemy.digester.domain.Results;
import org.apache.uima.alchemy.mapper.exception.MappingException;
import org.apache.uima.alchemy.ts.keywords.KeywordFS;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;

public class KeywordProcessor implements AlchemyOutputProcessor {

  public void process(JCas cas, Results results) throws Exception {
    for (Keyword k : ((KeywordResults) results).getKeywords()) {
      try {
        KeywordFS fs = new KeywordFS(cas);
        Type type = fs.getType();
        fs.setFeatureValueFromString(type.getFeatureByBaseName("text"), k.getText()); // text
        fs.addToIndexes();
      } catch (Exception e) {
        throw new MappingException(e);
      }
    }
  }

}
