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
package org.apache.uima.alchemy.utils;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.ProcessTrace;
import org.apache.uima.util.ProcessTraceEvent;
import org.apache.uima.util.XMLInputSource;
import org.junit.Ignore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Ignore
public class TestUtils {

  /**
   * get an AE from a path of the descriptor
   *
   * @param filePath
   * @return
   * @throws IOException
   * @throws InvalidXMLException
   * @throws ResourceInitializationException
   *
   */
  public static AnalysisEngine getAE(String filePath) throws IOException, InvalidXMLException,
          ResourceInitializationException {
    AnalysisEngine ae = null;
    // get Resource Specifier from XML file
    XMLInputSource in = new XMLInputSource(filePath);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);

    // create AE here
    ae = UIMAFramework.produceAnalysisEngine(specifier);

    return ae;
  }

  /**
   * get an analysis engine give path and runtime configuration parameters
   * @param xmlPath
   * @param parameterSettings
   * @return
   * @throws ResourceInitializationException
   */
  public static AnalysisEngine getAE(String xmlPath, Map<String, Object> parameterSettings) throws ResourceInitializationException {
    AnalysisEngine ae = null;
    try {
      XMLInputSource in = new XMLInputSource(xmlPath);

      // override descriptor's configuration parameters
      AnalysisEngineDescription desc = UIMAFramework.getXMLParser().parseAnalysisEngineDescription(in);
      for (String parameter : parameterSettings.keySet()) {
        desc.getAnalysisEngineMetaData().getConfigurationParameterSettings().setParameterValue(parameter, parameterSettings.get(parameter));
      }

      // create AE here
      ae = UIMAFramework.produceAnalysisEngine(desc);

    } catch (Exception e) {
      throw new ResourceInitializationException(e);
    }

    return ae;
  }

  /**
   * get an analysis engine given a path and a mocked implementation name to override the annotator class
   * @param xmlPath
   * @param implementationName
   * @return
   * @throws ResourceInitializationException
   */
  public static AnalysisEngine getAEWithMockedImplementation(String xmlPath, String implementationName) throws ResourceInitializationException {
    AnalysisEngine ae = null;
    try {
      XMLInputSource in = new XMLInputSource(xmlPath);

      AnalysisEngineDescription desc = UIMAFramework.getXMLParser().parseAnalysisEngineDescription(in);
      desc.setAnnotatorImplementationName(implementationName);

      // create AE here
      ae = UIMAFramework.produceAnalysisEngine(desc);

    } catch (Exception e) {
      throw new ResourceInitializationException(e);
    }

    return ae;
  }

  /**
   * executes an AE on a document
   *
   * @param ae
   * @param docText
   * @return
   * @throws AnalysisEngineProcessException
   * @throws ResourceInitializationException
   *
   */
  public static JCas executeAE(AnalysisEngine ae, String docText)
          throws AnalysisEngineProcessException, ResourceInitializationException {
    // create a JCas, given an Analysis Engine (ae)
    JCas jcas = ae.newJCas();

    // analyze a document
    jcas.setDocumentText(docText);
    ProcessTrace pt = ae.process(jcas);

    // analyze results
    for (ProcessTraceEvent e : pt.getEvents()) {
      if (e != null && e.getResultMessage() != null && e.getResultMessage().contains("error")) {
        throw new AnalysisEngineProcessException();
      }
    }
    return jcas;
  }

  /**
   * get all FeatureStructures of a type from the CAS
   *
   * @param type
   * @param cas
   * @return
   */
  public static List<? extends FeatureStructure> getAllFSofType(int type, JCas cas) {
    List<FeatureStructure> featureStructures = new ArrayList<FeatureStructure>();
    for (FSIterator<FeatureStructure> it = cas.getFSIndexRepository().getAllIndexedFS(cas.getCasType(type)); it.hasNext();) {
      featureStructures.add(it.next());
    }
    return featureStructures;
  }

}
