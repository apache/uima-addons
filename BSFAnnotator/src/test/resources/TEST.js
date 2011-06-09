//Licensed to the Apache Software Foundation (ASF) under one
//or more contributor license agreements.  See the NOTICE file
//distributed with this work for additional information
//regarding copyright ownership.  The ASF licenses this file
//to you under the Apache License, Version 2.0 (the
//"License"); you may not use this file except in compliance
//with the License.  You may obtain a copy of the License at
//
//http://www.apache.org/licenses/LICENSE-2.0
//
//Unless required by applicable law or agreed to in writing,
//software distributed under the License is distributed on an
//"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
//KIND, either express or implied.  See the License for the
//specific language governing permissions and limitations
//under the License.    

// Example of javascript script to be used as input for the BSFAnnotator
importPackage(Packages.org.apache.uima);
importPackage(Packages.org.apache.uima.analysis_component);
importPackage(Packages.org.apache.uima.jcas);
importPackage(Packages.org.apache.uima.jcas.tcas);
importPackage(Packages.org.apache.uima.annotator.bsf.types);
importPackage(java.util.regex);

var matchPattern;

// Annotator initialize method
function initialize(context) {
  var pattern = context.getConfigParameterValue("Regexp");
  matchPattern = Pattern.compile(pattern);
}

// Annotator process method
function process(jcas) {
  var text = jcas.getDocumentText();
  var matcher = matchPattern.matcher(text);
  while (matcher.find()) {
    var token = new Token(jcas, matcher.start(), matcher.end());
    token.addToIndexes();
  }
}
