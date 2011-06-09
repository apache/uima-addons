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
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.TestCase;

import org.apache.uima.cas.CAS;
import org.apache.uima.test.junit_extension.AnnotatorTester;
import org.apache.uima.test.junit_extension.JUnitExtension;

/**
 * 
 * 
 */
public class TestConceptFileLocking extends TestCase {

  /**
   * Test 
   * 
   * @throws Exception
   */
  public void testEmptyRegex() throws Exception {

    System.out.println("Reading in concept file");
    final File conceptFile = JUnitExtension.getFile("conceptFileLocking/repeat.xml");
    BufferedInputStream is = new BufferedInputStream(new FileInputStream(conceptFile));
    final byte[] conceptBytes = new byte[(int) conceptFile.length()];
    int pos = 0;
    int rc = 0;
    while (((rc = is.read(conceptBytes, pos, conceptBytes.length - pos)) >= 0)
        && ((pos += rc) < conceptBytes.length)) {
      // side effects in loop condition; looks like C code ;-)
    }
    is.close();
    
    System.out.println("Starting test runs");

    // Run test max times
    final int max = 20;
    for (int i = 0; i < max; i++) {
      // create annotation tester with the regex annotator specifier
      AnnotatorTester annotTester = new AnnotatorTester(JUnitExtension
          .getFile("conceptFileLocking/repeatDescriptor.xml"));
      CAS cas = annotTester.performTest("Test a regex.", "en");
      // Document annotation and one "a" found.
      assertTrue(cas.getAnnotationIndex().size() == 2);
      try {
        org.apache.commons.io.FileUtils.forceDelete(conceptFile);
        BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(conceptFile));
        os.write(conceptBytes);
        os.close();
      } catch (IOException e) {
        e.printStackTrace();
        assertTrue(false);
      }
//      System.out.println("Number of test runs: " + (i + 1));
    }

  }
}
