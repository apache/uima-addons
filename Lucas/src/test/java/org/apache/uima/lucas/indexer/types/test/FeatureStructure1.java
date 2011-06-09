

/* First created by JCasGen Sat Jul 25 21:50:44 CEST 2009 */
package org.apache.uima.lucas.indexer.types.test;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;


/** 
 * Updated by JCasGen Sat Jul 25 21:50:44 CEST 2009
 * XML source: /home/landefeld/workspace/lucas/src/test/resources/AnnotationTokenStreamTestTypeSystem.xml
 * @generated */
public class FeatureStructure1 extends TOP {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(FeatureStructure1.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected FeatureStructure1() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public FeatureStructure1(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public FeatureStructure1(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: feature1

  /** getter for feature1 - gets 
   * @generated */
  public String getFeature1() {
    if (FeatureStructure1_Type.featOkTst && ((FeatureStructure1_Type)jcasType).casFeat_feature1 == null)
      jcasType.jcas.throwFeatMissing("feature1", "org.apache.uima.lucas.indexer.types.test.FeatureStructure1");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FeatureStructure1_Type)jcasType).casFeatCode_feature1);}
    
  /** setter for feature1 - sets  
   * @generated */
  public void setFeature1(String v) {
    if (FeatureStructure1_Type.featOkTst && ((FeatureStructure1_Type)jcasType).casFeat_feature1 == null)
      jcasType.jcas.throwFeatMissing("feature1", "org.apache.uima.lucas.indexer.types.test.FeatureStructure1");
    jcasType.ll_cas.ll_setStringValue(addr, ((FeatureStructure1_Type)jcasType).casFeatCode_feature1, v);}    
   
    
  //*--------------*
  //* Feature: feature2

  /** getter for feature2 - gets 
   * @generated */
  public String getFeature2() {
    if (FeatureStructure1_Type.featOkTst && ((FeatureStructure1_Type)jcasType).casFeat_feature2 == null)
      jcasType.jcas.throwFeatMissing("feature2", "org.apache.uima.lucas.indexer.types.test.FeatureStructure1");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FeatureStructure1_Type)jcasType).casFeatCode_feature2);}
    
  /** setter for feature2 - sets  
   * @generated */
  public void setFeature2(String v) {
    if (FeatureStructure1_Type.featOkTst && ((FeatureStructure1_Type)jcasType).casFeat_feature2 == null)
      jcasType.jcas.throwFeatMissing("feature2", "org.apache.uima.lucas.indexer.types.test.FeatureStructure1");
    jcasType.ll_cas.ll_setStringValue(addr, ((FeatureStructure1_Type)jcasType).casFeatCode_feature2, v);}    
   
    
  //*--------------*
  //* Feature: feature3

  /** getter for feature3 - gets 
   * @generated */
  public FeatureStructure2 getFeature3() {
    if (FeatureStructure1_Type.featOkTst && ((FeatureStructure1_Type)jcasType).casFeat_feature3 == null)
      jcasType.jcas.throwFeatMissing("feature3", "org.apache.uima.lucas.indexer.types.test.FeatureStructure1");
    return (FeatureStructure2)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureStructure1_Type)jcasType).casFeatCode_feature3)));}
    
  /** setter for feature3 - sets  
   * @generated */
  public void setFeature3(FeatureStructure2 v) {
    if (FeatureStructure1_Type.featOkTst && ((FeatureStructure1_Type)jcasType).casFeat_feature3 == null)
      jcasType.jcas.throwFeatMissing("feature3", "org.apache.uima.lucas.indexer.types.test.FeatureStructure1");
    jcasType.ll_cas.ll_setRefValue(addr, ((FeatureStructure1_Type)jcasType).casFeatCode_feature3, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: featureStructures2

  /** getter for featureStructures2 - gets 
   * @generated */
  public FSArray getFeatureStructures2() {
    if (FeatureStructure1_Type.featOkTst && ((FeatureStructure1_Type)jcasType).casFeat_featureStructures2 == null)
      jcasType.jcas.throwFeatMissing("featureStructures2", "org.apache.uima.lucas.indexer.types.test.FeatureStructure1");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureStructure1_Type)jcasType).casFeatCode_featureStructures2)));}
    
  /** setter for featureStructures2 - sets  
   * @generated */
  public void setFeatureStructures2(FSArray v) {
    if (FeatureStructure1_Type.featOkTst && ((FeatureStructure1_Type)jcasType).casFeat_featureStructures2 == null)
      jcasType.jcas.throwFeatMissing("featureStructures2", "org.apache.uima.lucas.indexer.types.test.FeatureStructure1");
    jcasType.ll_cas.ll_setRefValue(addr, ((FeatureStructure1_Type)jcasType).casFeatCode_featureStructures2, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for featureStructures2 - gets an indexed value - 
   * @generated */
  public FeatureStructure2 getFeatureStructures2(int i) {
    if (FeatureStructure1_Type.featOkTst && ((FeatureStructure1_Type)jcasType).casFeat_featureStructures2 == null)
      jcasType.jcas.throwFeatMissing("featureStructures2", "org.apache.uima.lucas.indexer.types.test.FeatureStructure1");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureStructure1_Type)jcasType).casFeatCode_featureStructures2), i);
    return (FeatureStructure2)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureStructure1_Type)jcasType).casFeatCode_featureStructures2), i)));}

  /** indexed setter for featureStructures2 - sets an indexed value - 
   * @generated */
  public void setFeatureStructures2(int i, FeatureStructure2 v) { 
    if (FeatureStructure1_Type.featOkTst && ((FeatureStructure1_Type)jcasType).casFeat_featureStructures2 == null)
      jcasType.jcas.throwFeatMissing("featureStructures2", "org.apache.uima.lucas.indexer.types.test.FeatureStructure1");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureStructure1_Type)jcasType).casFeatCode_featureStructures2), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureStructure1_Type)jcasType).casFeatCode_featureStructures2), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    