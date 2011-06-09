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
package org.apache.uima.alchemy.annotator;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.uima.alchemy.digester.DigesterProvider;
import org.apache.uima.alchemy.digester.microformats.MicroformatsDigesterProvider;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

public class URLMicroformatsAnnotator extends AbstractAlchemyAnnotator {

  protected URL createServiceURI() throws MalformedURLException {
    return URI.create("http://access.alchemyapi.com/calls/url/URLGetMicroformatData").toURL();
  }

  protected String[] getServiceParameters() {
    return new String[] { "outputMode" };
  }

  protected DigesterProvider createDigester() {
    return new MicroformatsDigesterProvider();
  }

  protected void initializeRuntimeParameters(JCas aJCas) throws AnalysisEngineProcessException {
    try {
      // fill url parameter
      StringBuffer serviceParamsBuf = new StringBuffer();
      serviceParamsBuf.append("&url=");
      serviceParamsBuf.append(URLEncoder.encode(aJCas.getDocumentText(), "UTF-8"));
      this.serviceParams += (serviceParamsBuf.toString());
    } catch (UnsupportedEncodingException e) {
      throw new AnalysisEngineProcessException(e);
    }
  }
}
