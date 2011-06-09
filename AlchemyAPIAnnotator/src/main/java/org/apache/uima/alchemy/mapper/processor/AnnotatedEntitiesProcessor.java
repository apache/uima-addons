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

import org.apache.commons.lang.StringUtils;
import org.apache.uima.alchemy.digester.domain.AnnotatedResults;
import org.apache.uima.alchemy.digester.domain.Results;
import org.apache.uima.alchemy.ts.entity.AlchemyAnnotation;
import org.apache.uima.jcas.JCas;

public class AnnotatedEntitiesProcessor implements AlchemyOutputProcessor {

  public void process(JCas cas, Results results) throws Exception {
    String annotatedText = ((AnnotatedResults) results).getAnnotatedText();

    // find strings of pattern 'TYPE[TEXT'
    String[] ants = StringUtils.substringsBetween(annotatedText, "[", "]");

    // map the ants to UIMA CAS
    for (String ant : ants) {
      if (ant.indexOf("[") > 0) {
        AlchemyAnnotation alchemyAnnotation = new AlchemyAnnotation(cas);

        int indexOfAnt = annotatedText.indexOf(ant);
        alchemyAnnotation.setBegin(indexOfAnt - 1);

        String antText = ant.substring(ant.indexOf("[") + 1);
        alchemyAnnotation.setEnd(indexOfAnt + antText.length() - 1);

        String antType = ant.substring(0, ant.indexOf("["));
        alchemyAnnotation.setAlchemyType(antType);
        alchemyAnnotation.addToIndexes();

        annotatedText = annotatedText.replaceFirst("\\[" + ant.replace("[", "\\[") + "\\]\\]",
                antText);
      }
    }

  }

}
