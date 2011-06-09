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

package org.apache.uima.fsvars.test;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.fsvars.FsVariableFactory;
import org.apache.uima.fsvars.FsVariables;
import org.apache.uima.fsvars.FsVariablesException;
import org.apache.uima.test.junit_extension.JUnitExtension;
import org.apache.uima.util.XMLInputSource;
import org.apache.uima.util.XMLParser;

public class FsVariableTest extends TestCase {

  private static final String annotTypeName1 = "org.apache.uima.fsvars.TestAnnot1";

  private static final String annotTypeName2 = "org.apache.uima.fsvars.TestAnnot2";

  private static final String annotTypeName3 = "org.apache.uima.fsvars.TestAnnot3";

  private static final String name1 = "name1";

  private static final String name2 = "name2";

  private static final String name3 = "name3";

  private static final String name4 = "name4";

  private static final String name5 = "name5";

  private static final String name6 = "name6";

  private Type annotType1;

  private Type annotType2;

  private Type annotType3;
  
  private Feature beginFeat;

  private CAS cas;

  public FsVariableTest(String arg0) {
    super(arg0);
    try {
      XMLParser parser = UIMAFramework.getXMLParser();
      File descriptorFile = JUnitExtension.getFile("fsvars1.xml");
      AnalysisEngineDescription aeDesc = (AnalysisEngineDescription) parser
          .parse(new XMLInputSource(descriptorFile));
      this.cas = UIMAFramework.produceAnalysisEngine(aeDesc).newCAS();
      TypeSystem ts = this.cas.getTypeSystem();
      this.annotType1 = ts.getType(annotTypeName1);
      this.annotType2 = ts.getType(annotTypeName2);
      this.annotType3 = ts.getType(annotTypeName3);
      this.beginFeat = ts.getFeatureByFullName(CAS.FEATURE_FULL_NAME_END);
    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
    }
  }

  protected void setUp() throws Exception {
    super.setUp();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testInit() throws Exception {
    boolean caughtException = false;
    FsVariables fsvars = FsVariableFactory.newInstance();

    // Initialize with null
    try {
      fsvars.init(null);
    } catch (NullPointerException e) {
      caughtException = true;
    }
    assertTrue(caughtException);

    this.cas.reset();
    // Check CAS
    assertTrue(fsvars.checkCas(this.cas));

    // Initialize with a proper CAS
    caughtException = false;
    try {
      fsvars.init(this.cas);
    } catch (Exception e) {
      e.printStackTrace();
      caughtException = true;
    }
    assertFalse(caughtException);
  }

  public void testDeclare() {
    this.cas.reset();
    FsVariables fsvars = FsVariableFactory.newInstance();
    fsvars.init(this.cas);
    fsvars.declareFsVariable(name1, this.annotType1);
    fsvars.declareFsVariable(name2, this.annotType2);
    fsvars.declareFsVariable(name3, this.annotType3);
    boolean caughtExc = false;
    try {
      fsvars.declareFsVariable(name1, this.annotType1);
    } catch (FsVariablesException e) {
      caughtExc = true;
    }
    assertTrue(caughtExc);
    caughtExc = false;
    try {
      fsvars.declareFsVariable(name2, this.annotType1);
    } catch (FsVariablesException e) {
      caughtExc = true;
    }
    assertTrue(caughtExc);
    caughtExc = false;
    try {
      fsvars.declareFsVariable(name3, this.annotType3);
    } catch (FsVariablesException e) {
      caughtExc = true;
    }
    assertTrue(caughtExc);
  }

  public void testIsVariable() {
    this.cas.reset();
    FsVariables fsvars = FsVariableFactory.newInstance();
    fsvars.init(this.cas);

    // Check that no variable is declared.
    assertFalse(fsvars.isFsVariable(name1));
    assertFalse(fsvars.isFsVariable(name2));
    assertFalse(fsvars.isFsVariable(name3));
    // Declare first variable.
    fsvars.declareFsVariable(name1, this.annotType1);
    // Check that only first variable is declared.
    assertTrue(fsvars.isFsVariable(name1));
    assertFalse(fsvars.isFsVariable(name2));
    assertFalse(fsvars.isFsVariable(name3));
    // Declare second var, check.
    fsvars.declareFsVariable(name2, this.annotType2);
    assertTrue(fsvars.isFsVariable(name1));
    assertTrue(fsvars.isFsVariable(name2));
    assertFalse(fsvars.isFsVariable(name3));
    // Declare third var, check.
    fsvars.declareFsVariable(name3, this.annotType3);
    assertTrue(fsvars.isFsVariable(name1));
    assertTrue(fsvars.isFsVariable(name2));
    assertTrue(fsvars.isFsVariable(name3));
  }

  public void testGetFsVariableType() {
    this.cas.reset();
    FsVariables fsvars = FsVariableFactory.newInstance();
    fsvars.init(this.cas);
    boolean caughtExc = false;
    try {
      fsvars.getFsVariableType(name1);
    } catch (FsVariablesException e) {
      caughtExc = true;
    }
    assertTrue(caughtExc);

    fsvars.declareFsVariable(name1, this.annotType1);
    fsvars.declareFsVariable(name2, this.annotType2);
    fsvars.declareFsVariable(name3, this.annotType3);

    assertTrue(fsvars.getFsVariableType(name1).equals(this.annotType1));
    assertTrue(fsvars.getFsVariableType(name2).equals(this.annotType2));
    assertTrue(fsvars.getFsVariableType(name3).equals(this.annotType3));
    
  }

  public void testListVariables() {
    this.cas.reset();
    FsVariables fsvars = FsVariableFactory.newInstance();
    fsvars.init(this.cas);

    // Declare some variables.
    fsvars.declareFsVariable(name1, this.annotType1);
    fsvars.declareFsVariable(name2, this.annotType2);
    fsvars.declareFsVariable(name3, this.annotType3);
    fsvars.declareFsVariable(name4, this.annotType1);
    fsvars.declareFsVariable(name5, this.annotType2);
    fsvars.declareFsVariable(name6, this.annotType3);

    // Test with no parameters.
    List list = fsvars.listFsVariables();
    assertTrue(list.size() == 6);
    
    // Check that all names are different.
    Set set = new HashSet();
    set.addAll(list);
    assertTrue(set.size() == 6);
    
    // Test with annotation type as parameter, which should be the same as without param.
    list = fsvars.listFsVariables(this.cas.getTypeSystem().getType(CAS.TYPE_NAME_ANNOTATION));
    assertTrue(list.size() == 6);
    
    // Check that all names are different.
    set = new HashSet();
    set.addAll(list);
    assertTrue(set.size() == 6);
    
    // Check with a specific annotation type.
    list = fsvars.listFsVariables(this.annotType1);
    assertTrue(list.size() == 2);
    assertTrue(list.contains(name1));
    assertTrue(list.contains(name4));
  }
  
  public void testSetAndGet() {
    
    this.cas.reset();
    FsVariables fsvars = FsVariableFactory.newInstance();
    fsvars.init(this.cas);

    // Declare some variables.
    fsvars.declareFsVariable(name1, this.annotType1);
    fsvars.declareFsVariable(name2, this.annotType2);
    fsvars.declareFsVariable(name3, this.annotType3);
    fsvars.declareFsVariable(name4, this.annotType1);
    fsvars.declareFsVariable(name5, this.annotType2);
    fsvars.declareFsVariable(name6, this.annotType3);

    // Create some values.
    AnnotationFS annot1 = this.cas.createAnnotation(this.annotType1, 1, 1);
    AnnotationFS annot2 = this.cas.createAnnotation(this.annotType2, 2, 2);
    AnnotationFS annot3 = this.cas.createAnnotation(this.annotType3, 3, 3);
    AnnotationFS annot4 = this.cas.createAnnotation(this.annotType1, 4, 4);
    AnnotationFS annot5 = this.cas.createAnnotation(this.annotType2, 5, 5);
    AnnotationFS annot6 = this.cas.createAnnotation(this.annotType3, 6, 6);
    
    // Check that values are null, set value, check it's not null.
    assertNull(fsvars.getVariableValue(name1));
    assertNull(fsvars.getVariableValue(name2));
    assertNull(fsvars.getVariableValue(name3));
    assertNull(fsvars.getVariableValue(name4));
    assertNull(fsvars.getVariableValue(name5));
    assertNull(fsvars.getVariableValue(name6));
    
    fsvars.setVariable(name1, annot1);
    assertNotNull(fsvars.getVariableValue(name1));
    fsvars.setVariable(name2, annot2);
    assertNotNull(fsvars.getVariableValue(name2));
    fsvars.setVariable(name3, annot3);
    assertNotNull(fsvars.getVariableValue(name3));
    fsvars.setVariable(name4, annot4);
    assertNotNull(fsvars.getVariableValue(name4));
    fsvars.setVariable(name5, annot5);
    assertNotNull(fsvars.getVariableValue(name5));
    fsvars.setVariable(name6, annot6);
    assertNotNull(fsvars.getVariableValue(name6));
    
    AnnotationFS annot = (AnnotationFS) fsvars.getVariableValue(name1);
    assertTrue(annot.getIntValue(this.beginFeat) == 1);
    annot1.setIntValue(this.beginFeat, 42);
    assertTrue(annot.getIntValue(this.beginFeat) == 42);
    annot = (AnnotationFS) fsvars.getVariableValue(name1);
    assertTrue(annot.getIntValue(this.beginFeat) == 42);

    
    fsvars.setVariable(name1, annot4);
    annot = (AnnotationFS) fsvars.getVariableValue(name1);
    assertTrue(annot.getIntValue(this.beginFeat) == 4);
    
    boolean caughtExc = false;
    try {
      fsvars.setVariable(name2, annot1);
    } catch (FsVariablesException e) {
      caughtExc = true;
    }
    assertTrue(caughtExc);
    annot = (AnnotationFS) fsvars.getVariableValue(name2);
    assertNotNull(annot);
    assertTrue(annot.getIntValue(this.beginFeat) == 2);
  }
  
}

