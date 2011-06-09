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

import org.apache.commons.lang.Validate;
import org.apache.uima.UimaContext;
import org.apache.uima.alchemy.annotator.exception.AlchemyCallFailedException;
import org.apache.uima.alchemy.digester.DigesterProvider;
import org.apache.uima.alchemy.digester.OutputDigester;
import org.apache.uima.alchemy.digester.domain.Results;
import org.apache.uima.alchemy.digester.exception.ResultDigestingException;
import org.apache.uima.alchemy.digester.exception.UnsupportedResultFormatException;
import org.apache.uima.alchemy.mapper.Alchemy2TypeSystemMapper;
import org.apache.uima.alchemy.mapper.exception.MappingException;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Level;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Base class for annotators which wrap AlchemyAPI web services
 */
public abstract class AbstractAlchemyAnnotator extends JCasAnnotator_ImplBase {

  private static final String STATUS_OK = "OK";

  private URL alchemyService;

  protected String serviceParams;

  private final String[] charsToReplace = { "<", ">", "\"", "'", "&" };

  private OutputDigester digester;

  private DigesterProvider digesterProvider;

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    digesterProvider = createDigester();

    try {
      Object configParameterValue = aContext.getConfigParameterValue("outputMode");
      this.digester = digesterProvider.getDigester(String.valueOf(configParameterValue));
    } catch (UnsupportedResultFormatException e1) {
      throw new ResourceInitializationException(e1);
    }

    try {
      this.alchemyService = createServiceURI();
    } catch (Exception e) {
      throw new ResourceInitializationException(e);
    }

    StringBuffer serviceParamsBuf = new StringBuffer();
    serviceParamsBuf.append("&apikey=");
    serviceParamsBuf.append(aContext.getConfigParameterValue("apikey"));

    for (String param : this.getServiceParameters()) {
      serviceParamsBuf.append("&").append(param).append("=");
      serviceParamsBuf.append(aContext.getConfigParameterValue(param));
    }

    this.serviceParams = serviceParamsBuf.toString();
  }

  protected String cleanText(JCas aJCas) {
    String modifiedText = aJCas.getDocumentText();
    for (int i = 0; i < this.charsToReplace.length; i++) {
      modifiedText = modifiedText.replaceAll(this.charsToReplace[i], "");
    }
    modifiedText = modifiedText.replaceAll("\n", " ");
    modifiedText = modifiedText.replaceAll("\r", " ");
    return modifiedText;
  }

  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    // initialize service parameters
    initializeRuntimeParameters(aJCas);
    URLConnection connection = null;
    try {
      // open connection and send data
      connection = this.alchemyService.openConnection();

      if (connection instanceof HttpURLConnection) {
        connection.setDoOutput(true);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection
                .getOutputStream(), "UTF-8"));
        writer.write(this.serviceParams);

        writer.flush();
        writer.close();
      }

      connection.connect();

      InputStream bufByteIn = parseOutput(connection);

      // map alchemy api results to UIMA type system
      try {
        Results results = this.digester.parseAlchemyXML(bufByteIn);
        Validate.notNull(results);
        Validate.notNull(results.getStatus());
        if (this.getContext().getLogger().isLoggable(Level.FINER))
            this.getContext().getLogger().log(Level.FINER, results.toString());
        if (results.getStatus().equalsIgnoreCase(STATUS_OK)) {
          mapResultsToTypeSystem(results, aJCas);
        } else {
          throw new AlchemyCallFailedException(new StringBuilder(results.getStatus()).append(" - ").
                  append(results.getStatusInfo()).toString());
        }

      } catch (Exception e) {
        throw new ResultDigestingException(e);
      } finally {
        bufByteIn.close();
      }
    } catch (Exception e) {
      throw new AnalysisEngineProcessException(e);
    } finally {
      if (connection != null && connection instanceof HttpURLConnection) {
        ((HttpURLConnection) connection).disconnect();
      }
    }

  }

  private InputStream parseOutput(URLConnection connection) throws IOException {
    return new BufferedInputStream(connection.getInputStream());
  }
  
  private void mapResultsToTypeSystem(Results results, JCas aJCas) throws MappingException {
    Alchemy2TypeSystemMapper.mapResultsToTypeSystem(results, aJCas);
  }

  public void setDigesterProvider(DigesterProvider digesterProvider) {
    this.digesterProvider = digesterProvider;
  }

  public DigesterProvider getDigesterProvider() {
    return digesterProvider;
  }

  protected abstract DigesterProvider createDigester();

  protected abstract URL createServiceURI() throws MalformedURLException;

  protected abstract String[] getServiceParameters();

  protected abstract void initializeRuntimeParameters(JCas aJCas)
          throws AnalysisEngineProcessException;

}
