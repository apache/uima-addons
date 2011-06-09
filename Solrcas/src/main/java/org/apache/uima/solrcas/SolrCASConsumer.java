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

package org.apache.uima.solrcas;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.CasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.resource.ResourceAccessException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Level;
import org.xml.sax.SAXException;

/**
 * CAS Consumer to write on a Solr instance
 */
public class SolrCASConsumer extends CasAnnotator_ImplBase {

  private static final String CLASSPATH = "classpath:";
  private static final String FILEPATH = "file://";
  private static final String EMPTY_STRING = "";

  protected SolrServer solrServer;

  private SolrMappingConfiguration mappingConfig;

  private boolean autoCommit;

  @Override
  public void initialize(UimaContext context) throws ResourceInitializationException {
    super.initialize(context);

    /* create the SolrServer*/
    try {
      this.solrServer = createServer();
    } catch (Exception e) {
      context.getLogger().log(Level.SEVERE, "Unable to initialize SolrServer properly");
      throw new ResourceInitializationException(e);
    }

    /* create the mapping configuration */
    try{
      this.mappingConfig = createSolrMappingConfiguration();
    } catch (Exception e) {
      context.getLogger().log(Level.SEVERE, "Unable to initialize Solr mapping configuration properly");
      throw new ResourceInitializationException(e);
    }

    /* set Solr autoCommit parameter */
    try {
      this.autoCommit = getAutoCommitValue();
    } catch (Exception e) {
      context.getLogger().log(Level.SEVERE, "Unable to initialize Solr autoCommit parameter properly");
      throw new ResourceInitializationException(e);
    }

  }

  @Override
  public void typeSystemInit(TypeSystem typeSystem) throws AnalysisEngineProcessException {
    super.typeSystemInit(typeSystem);
    for (String key : mappingConfig.getFeatureStructuresMapping().keySet()) {
      Type type = typeSystem.getType(key);
      if (type==null) {
        throw new AnalysisEngineProcessException("required_feature_structure_missing_from_cas",
                new Object[]{key});
      }
      Map<String, String> stringStringMap = mappingConfig.getFeatureStructuresMapping().get(key);
      for (String featureName : stringStringMap.keySet()) {
        if (!"coveredText".equals(featureName) && type.getFeatureByBaseName(featureName)==null) {
          throw new AnalysisEngineProcessException("required_attribute_missing", 
                  new Object[]{featureName,type});
        }
      }
    }
  }


  public void process(CAS cas) throws AnalysisEngineProcessException {
    // create the SolrDocument from the CAS object basing on the mapping configuration
    SolrInputDocument document = createDocument(cas);

    // send the SolrDocument to SolrServer
    try {
      solrServer.add(document);
    } catch (Exception e) {
      getContext().getLogger().log(Level.SEVERE, new StringBuilder("Error while adding document").
              append(document.toString()).toString());
      throw new AnalysisEngineProcessException(e);
    }

    // if AutoCommit is enabled send the commit message to the SolrServer
    if (!autoCommit) {
      try {
        solrServer.commit();
      } catch (Exception e) {
        getContext().getLogger().log(Level.SEVERE, new StringBuilder("Error while committing document").
                append(document.toString()).toString());
        throw new AnalysisEngineProcessException(e);
      }
    }
  }


  /* allows retrieving of a URI from a path specifying one of:
   * file://absolute/path
   * http://something.com/res.ext
   * classpath:/path/to/something.xml
   * data/path/relative/file.ext
   */
  protected URI getURI(String path) throws ResourceAccessException, IOException, URISyntaxException {
    URI uri;
    if (path.startsWith(CLASSPATH)) {
      uri = System.class.getResource(path.replaceFirst(CLASSPATH, EMPTY_STRING)).toURI();
    } else {
      uri = UriUtils.create(path); // this supports file://ABSOLUTE_PATH and http://URL
      if (!uri.isAbsolute()) {
        String dataPath = getContext().getDataPath().replace('\\', '/');
        if (dataPath.matches("[a-zA-Z]\\:.*")) {
          dataPath = "/" + dataPath;
        }
         uri = UriUtils.create(new StringBuilder(FILEPATH).append(dataPath).
                append("/").append(path.replace(FILEPATH, EMPTY_STRING)).toString()); // this supports relative file paths
      }
    }
    return uri;
  }

  private boolean getAutoCommitValue() {
    boolean autoCommitValue = false;
    Object autoCommitParam = getContext().getConfigParameterValue("autoCommit");
    if (autoCommitParam != null && autoCommitParam.toString().length() > 0)
      autoCommitValue = Boolean.valueOf(autoCommitParam.toString());
    return autoCommitValue;
  }

  private SolrMappingConfiguration createSolrMappingConfiguration()
          throws IOException, ResourceAccessException, ParserConfigurationException, SAXException, URISyntaxException {
    FieldMappingReader fieldMappingReader = new FieldMappingReader();
    String mappingFileParam = String.valueOf(getContext().getConfigParameterValue("mappingFile"));

    InputStream input = getURI(mappingFileParam).toURL().openStream();

    SolrMappingConfiguration solrMappingConfiguration = fieldMappingReader.getConf(input);
    return solrMappingConfiguration;
  }

  protected SolrServer createServer() throws SolrServerException {
    SolrServer solrServer = null;
    try {
      /* get Solr type*/
      String solrInstanceTypeParam = String.valueOf(getContext().
              getConfigParameterValue("solrInstanceType"));
  
      /* get Solr Path */
      String solrPathParam = String.valueOf(getContext().
              getConfigParameterValue("solrPath"));
  
      if (solrInstanceTypeParam.equalsIgnoreCase("http")) {
        URL solrURL = UriUtils.create(solrPathParam).toURL();
        solrServer = new CommonsHttpSolrServer(solrURL);
      }
    } catch (Exception e) {
      throw new SolrServerException("Error creating SolrServer", e);
    }

    return solrServer;
  }

  /* create a SolrDocument from the current CAS object and the mapping configuration */
  private SolrInputDocument createDocument(CAS cas) {
    SolrInputDocument document = new SolrInputDocument();
    if (mappingConfig.getDocumentTextMapping() != null && mappingConfig.getDocumentTextMapping().length() > 0)
      document.addField(mappingConfig.getDocumentTextMapping(), cas.getDocumentText());
    if (mappingConfig.getDocumentLanguageMapping() != null && mappingConfig.getDocumentLanguageMapping().length() > 0)
      document.addField(mappingConfig.getDocumentLanguageMapping(), cas.getDocumentLanguage());
    for (String key : mappingConfig.getFeatureStructuresMapping().keySet()) {
      Type type = cas.getTypeSystem().getType(key);

      for (FSIterator<FeatureStructure> iterator = cas.getIndexRepository().getAllIndexedFS(type); iterator
              .hasNext();) {
        FeatureStructure fs = iterator.next();
        Map<String, String> stringStringMap = mappingConfig.getFeatureStructuresMapping().get(key);

        for (String featureName : stringStringMap.keySet()) {
          String fieldName = stringStringMap.get(featureName);
          String featureValue;
          if (fs instanceof AnnotationFS && "coveredText".equals(featureName)) {
            featureValue = ((AnnotationFS) fs).getCoveredText();
          } else {
            Feature feature = type.getFeatureByBaseName(featureName);
            featureValue = fs.getFeatureValueAsString(feature);
          }
          document.addField(fieldName, featureValue);
        }
      }
    }
    return document;
  }
}
