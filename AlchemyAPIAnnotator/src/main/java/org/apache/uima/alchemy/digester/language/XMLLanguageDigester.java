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
package org.apache.uima.alchemy.digester.language;

import org.apache.commons.digester.Digester;
import org.apache.uima.alchemy.digester.OutputDigester;
import org.apache.uima.alchemy.digester.domain.LanguageDetectionResults;
import org.apache.uima.alchemy.digester.domain.Results;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class XMLLanguageDigester implements OutputDigester {

  public Results parseAlchemyXML(InputStream responseStream) throws IOException, SAXException,
          URISyntaxException {
    Digester digester = new Digester();
    digester.setValidating(false);

    digester.addObjectCreate("results", LanguageDetectionResults.class);
    digester.addBeanPropertySetter("results/status", "status");
    digester.addBeanPropertySetter("results/statusInfo", "statusInfo");
    digester.addBeanPropertySetter("results/url", "url");
    digester.addBeanPropertySetter("results/language", "language");
    digester.addBeanPropertySetter("results/iso-639-1", "iso6391");
    digester.addBeanPropertySetter("results/iso-639-2", "iso6392");
    digester.addBeanPropertySetter("results/iso-639-3", "iso6393");
    digester.addBeanPropertySetter("results/ethnologue", "ethnologue");
    digester.addBeanPropertySetter("results/native-speakers", "nativeSpeakers");
    digester.addBeanPropertySetter("results/wikipedia", "wikipedia");
    return (Results) digester.parse(responseStream);
  }

}
