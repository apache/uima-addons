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

import org.apache.uima.test.junit_extension.AnnotatorPerformanceTester;
import org.apache.uima.test.junit_extension.JUnitExtension;
import org.apache.uima.test.junit_extension.PerformanceTestResult;

/**
 * 
 * WhitespaceTokenizer performance test class
 */
public class WhitespaceTokenizerPerfTest extends TestCase {

  /**
   * test annotator performance for simple english document.
   * 
   * @throws Exception
   */
  public void testEnglishProcessingTest() throws Exception {
    // get descriptor
    File descFile = JUnitExtension.getFile("WhitespaceTokenizer.xml");
    File testFileDir = JUnitExtension.getFile("perfTestFiles");
    // run performance test
    PerformanceTestResult result = AnnotatorPerformanceTester.runPerformanceTest(true, 10000, descFile, testFileDir,
            null, true);
    // get result file
    System.out.println(result);
  }

  /**
   * main method to start the tests not a JUnit test.
   * 
   * @param args
   */
  public static void main(String[] args) {
    WhitespaceTokenizerPerfTest perf = new WhitespaceTokenizerPerfTest();
    try {
      perf.testEnglishProcessingTest();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

}