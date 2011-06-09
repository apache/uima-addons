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

package org.apache.uima.simpleserver;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.uima.InternationalizedException;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.pear.tools.PackageBrowser;
import org.apache.uima.pear.tools.PackageInstaller;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.simpleserver.config.ConfigFactory;
import org.apache.uima.simpleserver.config.Output;
import org.apache.uima.simpleserver.config.ServerSpec;
import org.apache.uima.simpleserver.config.TypeMap;
import org.apache.uima.simpleserver.config.impl.XmlConfigReader;
import org.apache.uima.simpleserver.output.Result;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.Level;
import org.apache.uima.util.Logger;
import org.apache.uima.util.XMLInputSource;
import org.apache.xmlbeans.XmlException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This is the main class of the project. It represents a wrapper over UIMA Framework. One Server
 * instance is able to perform one task. The Server object must be created, configured (method
 * configure(...)) and can be used by calling process(...) method.
 */
public class Service {

  private static final String noDescriptionProvided = "No description provided";

  private static final Logger logger = UIMAFramework.getLogger(Service.class);

  private AnalysisEngine ae = null;

  protected CAS cas = null;

  protected ServerSpec serviceSpec = null;

  protected ResultExtractor resultExtractor = null;

  protected volatile boolean initialized = false;

  /**
   * Default constructor.
   */
  public Service() {
    super();
    this.resultExtractor = new ResultExtractor();
  }

  public static Logger getLogger() {
    return logger;
  }

  /**
   * Configure service with a UIMA descriptor. AE code must be on the classpath.
   * 
   * @param descriptorFile
   *                AE descriptor file.
   * @param resultSpecXMLFile
   *                Service specification file.
   * @throws XmlException
   *                 If service spec is not valid XML.
   * @throws IOException
   *                 On I/O problems (file not found etc).
   * @throws SimpleServerException
   *                 Other initialization problems.
   */
  public void configureAnalysisEngine(File descriptorFile, File resultSpecXMLFile)
      throws SimpleServerException, IOException, XmlException {
    assert (descriptorFile != null);
    try {
      loadDescriptor(descriptorFile);
    } catch (InternationalizedException e) {
      SimpleServerException sse = new SimpleServerException(
          SimpleServerException.uima_initialization_error, new Object[] {}, e);
      throw sse;
    }
    configure(resultSpecXMLFile);
  }

  /**
   * Configure service with a UIMA PEAR file. The PEAR is installed during installation.
   * 
   * @param pearFile
   *                The PEAR file.
   * @param pearInstallationDir
   *                The installation directory for the PEAR.
   * @param serviceSpecFile
   *                Service specification file.
   * @throws XmlException
   *                 If service spec is not valid XML.
   * @throws IOException
   *                 On I/O problems (file not found etc).
   * @throws SimpleServerException
   *                 Other initialization problems.
   */
  public void configurePear(File pearFile, File pearInstallationDir, File serviceSpecFile)
      throws IOException, SimpleServerException, XmlException {
    try {
      loadPear(pearFile, pearInstallationDir);
    } catch (InternationalizedException e) {
      SimpleServerException sse = new SimpleServerException(
          SimpleServerException.uima_initialization_error, new Object[] {}, e);
      throw sse;
    }
    configure(serviceSpecFile);
  }

  protected void configure(File serviceSpecFile) throws IOException, SimpleServerException,
      XmlException {
    if (serviceSpecFile == null) {
      this.serviceSpec = ConfigFactory.newServerSpec(noDescriptionProvided, noDescriptionProvided,
          true);
    } else {
      this.serviceSpec = XmlConfigReader.readServerSpec(serviceSpecFile);
      validateResultSpec();
    }
    this.initialized = true;
  }

  protected final void logInitializationError() {
    // TODO: log which service could not be initialized!!!!!!!!!!!!!!!!!
    SimpleServerException e = new SimpleServerException(
        SimpleServerException.service_state_exception, new Object[] {});
    getLogger().log(Level.SEVERE, "", e);
  }

  /**
   * Gets an XSD for the output format of the service.
   * 
   * @return A string representation of the XSD; serialize as utf-8.
   */
  public String getXMLResultXSD() {
    if (!this.initialized) {
      logInitializationError();
      return null;
    }
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      DOMImplementation impl = builder.getDOMImplementation();

      Document document = impl.createDocument(null, null, null);
      Element root = document.createElement("schema");
      root.setAttribute("xmlns", "http://www.w3.org/2001/XMLSchema");
      document.appendChild(root);
      Element resultElement = document.createElement("element");
      resultElement.setAttribute("name", "result");
      root.appendChild(resultElement);
      Element complexTypeElement = document.createElement("complexType");
      resultElement.appendChild(complexTypeElement);
      Element sequenceElement = document.createElement("sequence");
      complexTypeElement.appendChild(sequenceElement);

      for (TypeMap tSpec : this.serviceSpec.getTypeSpecs()) {
        Element element = document.createElement("element");
        element.setAttribute("name", tSpec.getOutputTag());
        element.setAttribute("maxOccurs", "unbounded");
        element.setAttribute("minOccurs", "0");

        Element complexType = document.createElement("complexType");
        element.appendChild(complexType);

        Element simpleContent = document.createElement("simpleContent");
        complexType.appendChild(simpleContent);

        Element extension = document.createElement("extension");
        extension.setAttribute("base", "string");
        simpleContent.appendChild(extension);

        for (Output output : tSpec.getOutputs()) {
          Element attributeElement = document.createElement("attribute");
          extension.appendChild(attributeElement);
          attributeElement.setAttribute("name", output.getAttribute());
          attributeElement.setAttribute("type", "string");
          attributeElement.setAttribute("use", "optional");
        }
        sequenceElement.appendChild(element);
      }

      DOMSource source = new DOMSource(document);
      TransformerFactory tFactory = TransformerFactory.newInstance();
      Transformer transformer = tFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.METHOD, "xml");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");

      StringWriter stringWriter = new StringWriter();
      StreamResult streamResult = new StreamResult(stringWriter);
      transformer.transform(source, streamResult);

      return stringWriter.toString();
    } catch (TransformerException e) {
      getLogger().log(Level.SEVERE, "", e);
    } catch (ParserConfigurationException e) {
      getLogger().log(Level.SEVERE, "", e);
    }
    return null;
  }

  /**
   * Calls the services analysis engine on the input text, filters and produces the result.
   */
  // If UIMA-AS is included as an ADD-ON to SimpleServer and UIMA-AS servlet 
  // is invoked, UIMA-AS (UimaAsService) will override this method
  public synchronized Result process(String text, String lang) {
    // Check that service has been initialized.
    if (!this.initialized) {
      logInitializationError();
      return null;
    }
    this.cas.reset();
    this.cas.setDocumentText(text);
    if (lang != null) {
      this.cas.setDocumentLanguage(lang);
    }
    try {
      this.ae.process(this.cas);
    } catch (AnalysisEngineProcessException e) {
      getLogger().log(Level.SEVERE, "", e);
      return null;
    }
    return this.resultExtractor.getResult(this.cas, this.serviceSpec);
  }

  public Result process(String text) {
    return process(text, null);
  }

  public ServerSpec getServiceSpec() {
    return this.serviceSpec;
  }

  /*
   * initialises the AE, in the case if the AE descriptor was passed to the configure(...) method.
   * 
   * The code here is actually a copy of code from the UIMA tutorial.
   */
  private void loadDescriptor(File descriptorFile) throws IOException, InvalidXMLException,
      ResourceInitializationException {
    XMLInputSource in = new XMLInputSource(descriptorFile);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
    ResourceManager manager = UIMAFramework.newDefaultResourceManager();

    this.ae = UIMAFramework.produceAnalysisEngine(specifier, manager, null);
    this.cas = this.ae.newCAS();

  }

  private void loadPear(File pearFile, File installationDir) throws IOException,
      InvalidXMLException, ResourceInitializationException {
    boolean doVerification = true;
    PackageBrowser instPear = PackageInstaller.installPackage(installationDir, pearFile,
        doVerification);
    String pearDescriptorFileName = instPear.getComponentPearDescPath();
    loadDescriptor(new File(pearDescriptorFileName));
  }

  private void validateResultSpec() {
    List<SimpleServerException> exc = this.serviceSpec.validate(this.cas.getTypeSystem());
    if (exc.size() > 0) {
      SimpleServerException e = new SimpleServerException(SimpleServerException.validation_warning,
          new Object[] { this.serviceSpec.getShortDescription() });
      getLogger().log(Level.WARNING, e.getLocalizedMessage());
      for (SimpleServerException sse : exc) {
        getLogger().log(Level.WARNING, sse.getLocalizedMessage());
      }
    }
  }

  /**
   * Delivers the service description as an XML document.
   */
  public String getServiceDescription() {
    if (!this.initialized) {
      logInitializationError();
      return null;
    }
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      DOMImplementation impl = builder.getDOMImplementation();

      Document document = impl.createDocument(null, null, null);
      Element root = document.createElement("uimaService");
      root.setAttribute("shortDescription", this.serviceSpec.getShortDescription());
      document.appendChild(root);
      for (TypeMap tspec : this.serviceSpec.getTypeSpecs()) {
        Element typeDescription = document.createElement("element");
        root.appendChild(typeDescription);
        typeDescription.setAttribute("name", tspec.getOutputTag());
        typeDescription.setAttribute("shortDescription", tspec.getShortDescription());
        for (Output output : tspec.getOutputs()) {
          Element outputSpec = document.createElement("attribute");
          typeDescription.appendChild(outputSpec);
          outputSpec.setAttribute("name", output.getAttribute());
          outputSpec.setAttribute("shortDescription", output.getShortDescription());
        }
        if (tspec.isOutputCoveredText()) {
          Element outputSpec = document.createElement("attribute");
          typeDescription.appendChild(outputSpec);
          outputSpec.setAttribute("name", "coveredText");
          outputSpec.setAttribute("shortDescription", "Text covered by annotation");
        }
      }

      DOMSource source = new DOMSource(document);
      TransformerFactory tFactory = TransformerFactory.newInstance();
      Transformer transformer = tFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.METHOD, "xml");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");

      StringWriter stringWriter = new StringWriter();
      StreamResult streamResult = new StreamResult(stringWriter);
      transformer.transform(source, streamResult);

      return stringWriter.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "";
  }
}
