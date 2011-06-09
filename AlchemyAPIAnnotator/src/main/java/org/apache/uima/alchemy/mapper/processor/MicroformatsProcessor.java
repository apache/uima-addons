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

import org.apache.uima.alchemy.digester.domain.Microformat;
import org.apache.uima.alchemy.digester.domain.MicroformatsResults;
import org.apache.uima.alchemy.digester.domain.Results;
import org.apache.uima.alchemy.ts.microformats.MicroformatFS;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;

public class MicroformatsProcessor implements AlchemyOutputProcessor {

  public void process(JCas cas, Results results) throws Exception {
    for (Microformat microformat : ((MicroformatsResults) results).getMicroformats()) {
      MicroformatFS microformatFS = new MicroformatFS(cas);
      Type type = microformatFS.getType();
      microformatFS.setFeatureValueFromString(type.getFeatureByBaseName("fieldName"), microformat
              .getFieldName());
      microformatFS.setFeatureValueFromString(type.getFeatureByBaseName("fieldData"), microformat
              .getFieldData());
      microformatFS.addToIndexes();
    }
  }

}
