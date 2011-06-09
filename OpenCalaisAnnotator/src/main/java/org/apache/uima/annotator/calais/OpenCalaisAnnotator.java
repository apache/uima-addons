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
package org.apache.uima.annotator.calais;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.CasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Level;
import org.apache.uima.util.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class OpenCalaisAnnotator extends CasAnnotator_ImplBase {

  private static String CONFIG_PARAM_NAME_SUBMITTER = "submitter";

  private static String CONFIG_PARAM_NAME_LICENSE_ID = "licenseID";

  private static String CONFIG_PARAM_NAME_ALLOW_SEARCH = "allowSearch";

  private static String CONFIG_PARAM_NAME_ALLOW_DISTRIBUTION = "allowDistribution";

  private SAXParser saxParser;

  private Type anniversaryType;

  private Type cityType;

  private Type companyType;

  private Type continentType;

  private Type countryType;

  private Type currencyType;

  private Type emailAddressType;

  private Type facilityType;

  private Type faxNumberType;

  private Type holidayType;

  private Type industryTermType;

  private Type naturalDisasterType;

  private Type naturalFeatureType;

  private Type organizationType;

  private Type personType;

  private Type phoneNumberType;

  private Type provinceOrStateType;

  private Type regionType;

  private Type technologyType;

  private Type urlType;

  private Feature calaisTypeFeat;

  private String serviceParams;

  private Logger logger;

  private URL calaisService;

  private HashMap<String, Type> typeMapping;
  
  private String[] charsToReplace = {"<", ">", "\"", "'", "&"};

  public void process(CAS aCas) throws AnalysisEngineProcessException {

    try {
      String modifiedText = aCas.getDocumentText();
      
      // open connection and send data
      InputStream serviceInputStream = callServiceOnText(modifiedText);

      // result is an XML that contains the RDF XML result
      // first get the RDF XML result out of the returned XML
      DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
      BufferedInputStream in = new BufferedInputStream(serviceInputStream);
      Document feedDoc = docBuilder.parse(in);

      in.close();
      String RdfXmlContent = feedDoc.getDocumentElement().getTextContent();
//      System.out.println(RdfXmlContent);

      // create new InputStream for the RDF XML content
      BufferedInputStream bufByteIn = new BufferedInputStream(new ByteArrayInputStream(
              RdfXmlContent.getBytes(feedDoc.getXmlEncoding())));

      // create SAX handler
      HashMap<String, DescriptionElement> elements = new HashMap<String, DescriptionElement>();
      ArrayList<DescriptionElement> subjectMap = new ArrayList<DescriptionElement>();
      Offset offset = new Offset();
      RDFSaxHandler saxHandler = new RDFSaxHandler(elements, subjectMap, offset);

      // parse RDF XML content returned by the calais service
      this.saxParser.parse(bufByteIn, saxHandler);

      //check offset correction
      String text = aCas.getDocumentText();
      ArrayList<Integer> positionsList = new ArrayList<Integer>();
      int index = -1;
      for(int i = 0; i < this.charsToReplace.length; i++) {
        index = text.indexOf(this.charsToReplace[i]);
        while(index > -1) {
          positionsList.add(index);
          index = text.indexOf(this.charsToReplace[i],index + 1);
        }
      }
     //now the positions list contains all positions where characters have been removed
      Integer[] positions = positionsList.toArray(new Integer[]{});
      
      Arrays.sort(positions);
            
      // analyze entities
      Iterator<DescriptionElement> elementIt = subjectMap.iterator();
      while (elementIt.hasNext()) {
        DescriptionElement element = elementIt.next();
        
        // retrieve subject URL, the subject URL must be equal to an about URL in the elements
        // map to get the type of the current element
        DescriptionElement typeElement = elements.get(element.getSubjectURL());
        String typeURL = typeElement.getTypeURL();
        
        // get current CAS type for the type URL
        Type currentType = this.typeMapping.get(typeURL);
        
        //if mapping is available, create an annotation
        if (currentType != null) {
          // get reference element that contains the annotation span
          int begin = 0;
          if (element.getOffset()>0) {
            begin = element.getOffset() - offset.getOffset() - 1;
          }
          
          //make begin offset correction
          for(int i = 0; i < positions.length; i++) {
            Integer pos = positions[i];
            if(pos < begin) {
              begin++;
            }
          }
                  
          int end = begin + element.getLength();
          // create annotation
          if (end - begin > 0) {
            AnnotationFS annotFs = aCas.createAnnotation(currentType, begin, end);
            annotFs.setStringValue(this.calaisTypeFeat, element.getTypeURL().intern());
            aCas.addFsToIndexes(annotFs);
          }
        }
      }
    } catch (IOException ex) {
      throw new AnalysisEngineProcessException(ex);
    } catch (SAXException ex) {
      throw new AnalysisEngineProcessException(ex);
    } catch (ParserConfigurationException ex) {
      throw new AnalysisEngineProcessException(ex);
    }

  }

  protected InputStream callServiceOnText(String modifiedText) throws IOException,
          UnsupportedEncodingException {
    URLConnection connection = this.calaisService.openConnection();
    connection.setDoOutput(true);
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection
            .getOutputStream(), "UTF-8"));
    writer.write(this.serviceParams);
    for(int i = 0; i < this.charsToReplace.length; i++) {
      modifiedText = modifiedText.replaceAll(this.charsToReplace[i], "");
    }
    modifiedText = modifiedText.replaceAll("\n", " ");
    modifiedText = modifiedText.replaceAll("\r", " ");

    if (this.getContext().getLogger().isLoggable(Level.INFO))
      this.getContext().getLogger().log(Level.FINER,new StringBuilder(this.calaisService.toString()).
              append(this.serviceParams).append(modifiedText).toString());     
    writer.write(modifiedText);
    writer.flush();
    writer.close();
    return connection.getInputStream();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.analysis_component.AnalysisComponent_ImplBase#initialize(org.apache.uima.UimaContext)
   */
  public void initialize(UimaContext context) throws ResourceInitializationException {
    super.initialize(context);

    // initialize annotator logger
    this.logger = this.getContext().getLogger();

    // create SAX parser
    try {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      this.saxParser = factory.newSAXParser();
    } catch (ParserConfigurationException ex) {
      throw new ResourceInitializationException(ex);
    } catch (SAXException ex) {
      throw new ResourceInitializationException(ex);
    }

    // get configuration parameters
    // get submitter
    String submitter = (String) this.getContext().getConfigParameterValue(
            CONFIG_PARAM_NAME_SUBMITTER);
    this.logger.log(Level.CONFIG, "Parameter \"{0}\" set to \"{1}\"", new Object[] {
        CONFIG_PARAM_NAME_SUBMITTER, submitter });
    // get licenseID
    String licenseID = (String) this.getContext().getConfigParameterValue(
            CONFIG_PARAM_NAME_LICENSE_ID);
    this.logger.log(Level.CONFIG, "Parameter \"{0}\" set to \"{1}\"", new Object[] {
        CONFIG_PARAM_NAME_LICENSE_ID, licenseID });
    // get allowSearch
    Boolean allowSearch = (Boolean) this.getContext().getConfigParameterValue(
            CONFIG_PARAM_NAME_ALLOW_SEARCH);
    this.logger.log(Level.CONFIG, "Parameter \"{0}\" set to \"{1}\"", new Object[] {
        CONFIG_PARAM_NAME_ALLOW_SEARCH, allowSearch.toString() });
    // get allowDistribution
    Boolean allowDistribution = (Boolean) this.getContext().getConfigParameterValue(
            CONFIG_PARAM_NAME_ALLOW_DISTRIBUTION);
    this.logger.log(Level.CONFIG, "Parameter \"{0}\" set to \"{1}\"", new Object[] {
        CONFIG_PARAM_NAME_ALLOW_DISTRIBUTION, allowDistribution.toString() });

    // generate externalID for the calais service
    String externalID = "UIMACalaisAnnotWrapper-" + System.currentTimeMillis();
    this.logger.log(Level.CONFIG, "Generated external ID: \"{0}\"", externalID);

    // create configXML for calais service
    String configXML = getConfigXML(allowDistribution.toString(), allowSearch.toString(),
            externalID, submitter);

    // create service parameter string
    StringBuffer serviceParamsBuf = new StringBuffer();
    // param licenseID
    serviceParamsBuf.append("&licenseID=");
    serviceParamsBuf.append(licenseID);
    // param configXML
    serviceParamsBuf.append("&paramsXML=");
    serviceParamsBuf.append(configXML);
    // param content
    serviceParamsBuf.append("&content=");
    this.serviceParams = serviceParamsBuf.toString();

    // create calais service URL
    try {
      this.calaisService = new URL("http://api.opencalais.com/enlighten/calais.asmx/Enlighten");
    } catch (MalformedURLException ex) {
      throw new ResourceInitializationException(ex);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.apache.uima.analysis_component.CasAnnotator_ImplBase#typeSystemInit(org.apache.uima.cas.TypeSystem)
   */
  public void typeSystemInit(TypeSystem typeSystem) throws AnalysisEngineProcessException {
    super.typeSystemInit(typeSystem);

    // get types and features
    this.personType = typeSystem.getType("org.apache.uima.calais.Person");
    this.anniversaryType = typeSystem.getType("org.apache.uima.calais.Anniversary");
    this.cityType = typeSystem.getType("org.apache.uima.calais.City");
    this.companyType = typeSystem.getType("org.apache.uima.calais.Company");
    this.continentType = typeSystem.getType("org.apache.uima.calais.Continent");
    this.countryType = typeSystem.getType("org.apache.uima.calais.Country");
    this.currencyType = typeSystem.getType("org.apache.uima.calais.Currency");
    this.emailAddressType = typeSystem.getType("org.apache.uima.calais.EmailAddress");
    this.facilityType = typeSystem.getType("org.apache.uima.calais.Facility");
    this.faxNumberType = typeSystem.getType("org.apache.uima.calais.FaxNumber");
    this.holidayType = typeSystem.getType("org.apache.uima.calais.Holiday");
    this.industryTermType = typeSystem.getType("org.apache.uima.calais.IndustryTerm");
    this.naturalDisasterType = typeSystem.getType("org.apache.uima.calais.NaturalDisaster");
    this.naturalFeatureType = typeSystem.getType("org.apache.uima.calais.NaturalFeature");
    this.organizationType = typeSystem.getType("org.apache.uima.calais.Organization");
    this.phoneNumberType = typeSystem.getType("org.apache.uima.calais.PhoneNumber");
    this.provinceOrStateType = typeSystem.getType("org.apache.uima.calais.ProviceOrState");
    this.regionType = typeSystem.getType("org.apache.uima.calais.Region");
    this.technologyType = typeSystem.getType("org.apache.uima.calais.Technology");
    this.urlType = typeSystem.getType("org.apache.uima.calais.URL");
    this.calaisTypeFeat = this.personType.getFeatureByBaseName("calaisType");

    // create type mapping HashMap
    this.typeMapping = new HashMap<String, Type>(20);
    this.typeMapping.put("http://s.opencalais.com/1/type/em/e/Person", this.personType);
    this.typeMapping.put("http://s.opencalais.com/1/type/em/e/Anniversary", this.anniversaryType);
    this.typeMapping.put("http://s.opencalais.com/1/type/em/e/City", this.cityType);
    this.typeMapping.put("http://s.opencalais.com/1/type/em/e/Company", this.companyType);
    this.typeMapping.put("http://s.opencalais.com/1/type/em/e/Continent", this.continentType);
    this.typeMapping.put("http://s.opencalais.com/1/type/em/e/Country", this.countryType);
    this.typeMapping.put("http://s.opencalais.com/1/type/em/e/Currency", this.currencyType);
    this.typeMapping.put("http://s.opencalais.com/1/type/em/e/EmailAddress", this.emailAddressType);
    this.typeMapping.put("http://s.opencalais.com/1/type/em/e/Facility", this.facilityType);
    this.typeMapping.put("http://s.opencalais.com/1/type/em/e/FaxNumber", this.faxNumberType);
    this.typeMapping.put("http://s.opencalais.com/1/type/em/e/Holiday", this.holidayType);
    this.typeMapping.put("http://s.opencalais.com/1/type/em/e/IndustryTerm", this.industryTermType);
    this.typeMapping.put("http://s.opencalais.com/1/type/em/e/NaturalDisaster",
            this.naturalDisasterType);
    this.typeMapping.put("http://s.opencalais.com/1/type/em/e/NaturalFeature",
            this.naturalFeatureType);
    this.typeMapping.put("http://s.opencalais.com/1/type/em/e/Organization", this.organizationType);
    this.typeMapping.put("http://s.opencalais.com/1/type/em/e/PhoneNumber", this.phoneNumberType);
    this.typeMapping.put("http://s.opencalais.com/1/type/em/e/ProvinceOrState",
            this.provinceOrStateType);
    this.typeMapping.put("http://s.opencalais.com/1/type/em/e/Region", this.regionType);
    this.typeMapping.put("http://s.opencalais.com/1/type/em/e/Technology", this.technologyType);
    this.typeMapping.put("http://s.opencalais.com/1/type/em/e/URL", this.urlType);

  }

  /**
   * create configuration XML needed by the calais service
   * 
   * @param allowDistribution
   * @param allowSearch
   * @param externalID
   * @param submitter
   * @return
   */
  private String getConfigXML(String allowDistribution, String allowSearch, String externalID,
          String submitter) {
    StringBuffer buffer = new StringBuffer();
    // create root element
    buffer.append("<c:params");
    buffer.append(" xmlns:c=\"http://s.opencalais.com/1/pred/\"");
    buffer.append(" xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">");

    // set processing directives
    buffer.append("<c:processingDirectives");
    // set parameter contentType = TEXT/TXT
    buffer.append(" c:contentType=\"TEXT/html\"");
    // set parameter outputFormat = XML/RDF
    buffer.append(" c:outputFormat=\"xml/rdf\">");
    // close processing directives
    buffer.append("</c:processingDirectives>");

    // set user directives
    buffer.append("<c:userDirectives");
    // set parameter allowDistribution
    buffer.append(" c:allowDistribution=\"");
    buffer.append(allowDistribution);
    buffer.append("\"");
    // set parameter allowSearch
    buffer.append(" c:allowSearch=\"");
    buffer.append(allowSearch);
    buffer.append("\"");
    // set parameter externalID
    buffer.append(" c:externalID=\"");
    buffer.append(externalID);
    buffer.append("\"");
    // set parameter externalID
    buffer.append(" c:submitter=\"");
    buffer.append(submitter);
    buffer.append("\"");
    // set parameter externalID
    buffer.append(" c:calculateRelevanceScore=\"");
    buffer.append("false");
    buffer.append("\"");
    // close user directives
    buffer.append("></c:userDirectives>");

    // set external meta data
    buffer.append("<c:externalMetadata>");
    buffer.append("</c:externalMetadata>");

    // close root element
    buffer.append("</c:params>");

    return buffer.toString();
  }

}
