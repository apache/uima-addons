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
package org.apache.uima.annotator.regex;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.uima.annotator.regex.impl.ConceptFileParser_impl;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.test.junit_extension.FileCompare;
import org.apache.uima.test.junit_extension.JUnitExtension;

/**
 * Test class to test the concept file XML parser.
 */
public class TestConceptFileParser extends TestCase {

  /**
   * checks the good case - concept file syntax check
   * 
   * @throws Exception
   */
  public void testSyntaxCheck() throws Exception {
    File conceptFile = JUnitExtension.getFile("conceptSyntax/syntaxCheck.xml");
    InputStream stream = new BufferedInputStream(new FileInputStream(conceptFile));
    //parse concept file
    ConceptFileParser parser = new ConceptFileParser_impl();
    Concept[] concepts = parser.parseConceptFile(conceptFile.getAbsolutePath(), stream);

    File outputFile = new File(conceptFile.getParent(), "syntaxCheck_current.txt");
    OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(outputFile, false),
            "UTF-8");

    // write all rules to the output file
    for (int i = 0; i < concepts.length; i++) {
      fileWriter.write(concepts[i].toString());
    }
    fileWriter.close();

    // get reference file
    File refFile = JUnitExtension.getFile("conceptSyntax/syntaxCheckRefOutput.txt");

    // compare current output with reference output
    Assert.assertTrue(FileCompare.compare(refFile, outputFile));
  }

  /**
   * check an empty concept file
   * 
   * @throws Exception
   */
  public void testEmptySyntaxFile() throws Exception {
    File conceptFile = JUnitExtension.getFile("conceptSyntax/emptyFile.xml");
    InputStream stream = new BufferedInputStream(new FileInputStream(conceptFile));
    
    try {
      //parse empty concept file
      ConceptFileParser parser = new ConceptFileParser_impl();
      parser.parseConceptFile(conceptFile.getAbsolutePath(), stream);
    } catch (ResourceInitializationException ex) {
      //we get an exception... what is correct!
    }
  }

}
