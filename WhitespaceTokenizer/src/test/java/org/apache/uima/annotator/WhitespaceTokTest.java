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

package org.apache.uima.annotator;

import java.io.File;

import junit.framework.TestCase;

import org.apache.uima.cas.CAS;
import org.apache.uima.test.junit_extension.AnnotatorTester;
import org.apache.uima.test.junit_extension.JUnitExtension;


/**
 * Testclass for the WhitespaceTokenizer annotator.
 */
public class WhitespaceTokTest extends TestCase
{
	private AnnotatorTester annotTester;
	
	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception
	{
		this.annotTester = new AnnotatorTester(JUnitExtension.getFile("WhitespaceTokenizer.xml"));
	}

    /* (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception
    {
      super.tearDown();
      this.annotTester = null;
    }
    
	public void testAnnotatorSpecial() throws Exception
	{
		//retrieve Annotator sample text
		String text = AnnotatorTester.readFileContent(JUnitExtension.getFile("testdoc.txt"), "UTF-8");
		
		//execute sample text
		CAS cas = this.annotTester.performTest(text,"en");
		
		//define result interested in
		String[] tofs = {"org.apache.uima.TokenAnnotation", "org.apache.uima.SentenceAnnotation"};
		
		//compare results
    File outputFile = new File(JUnitExtension.getFile("testdocRef.txt").getParent(), "testdocRef_testoutput.txt") ;
		AnnotatorTester.checkResult(cas, tofs, JUnitExtension.getFile("testdocRef.txt"), outputFile);		
	}

  public void testAnnotatorEnglish() throws Exception
  {
    //retrieve Annotator sample text
    String text = AnnotatorTester.readFileContent(JUnitExtension.getFile("english.txt"), "UTF-8");
    
    //execute sample text
    CAS cas = this.annotTester.performTest(text,"en");
    
    //define result interested in
    String[] tofs = {"org.apache.uima.TokenAnnotation", "org.apache.uima.SentenceAnnotation"};
    
    //compare results
    File outputFile = new File(JUnitExtension.getFile("englishRef.txt").getParent(), "englishRef_testoutput.txt") ;
    AnnotatorTester.checkResult(cas, tofs, JUnitExtension.getFile("englishRef.txt"), outputFile);   
  }
  
  public void testAnnotatorTabs() throws Exception {
    // retrieve Annotator sample text
    String text = AnnotatorTester.readFileContent(JUnitExtension.getFile("tabs.txt"), "UTF-8");

    // execute sample text
    CAS cas = this.annotTester.performTest(text, "en");

    // define result interested in
    String[] tofs = { "org.apache.uima.TokenAnnotation", "org.apache.uima.SentenceAnnotation" };

    // compare results
    File outputFile = new File(JUnitExtension.getFile("tabsRef.txt").getParent(),
        "tabsRef_testoutput.txt");
    AnnotatorTester.checkResult(cas, tofs, JUnitExtension.getFile("tabsRef.txt"), outputFile);
  }
  

}
