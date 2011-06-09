

/* First created by JCasGen Sat Jul 25 21:50:44 CEST 2009 */
package org.apache.uima.lucas.indexer.types.test;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.cas.StringArray;


/** 
 * Updated by JCasGen Sat Jul 25 21:50:44 CEST 2009
 * XML source: /home/landefeld/workspace/lucas/src/test/resources/AnnotationTokenStreamTestTypeSystem.xml
 * @generated */
public class FeatureStructure2 extends TOP {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(FeatureStructure2.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected FeatureStructure2() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public FeatureStructure2(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public FeatureStructure2(JCas jcas) {
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
    if (FeatureStructure2_Type.featOkTst && ((FeatureStructure2_Type)jcasType).casFeat_feature1 == null)
      jcasType.jcas.throwFeatMissing("feature1", "org.apache.uima.lucas.indexer.types.test.FeatureStructure2");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FeatureStructure2_Type)jcasType).casFeatCode_feature1);}
    
  /** setter for feature1 - sets  
   * @generated */
  public void setFeature1(String v) {
    if (FeatureStructure2_Type.featOkTst && ((FeatureStructure2_Type)jcasType).casFeat_feature1 == null)
      jcasType.jcas.throwFeatMissing("feature1", "org.apache.uima.lucas.indexer.types.test.FeatureStructure2");
    jcasType.ll_cas.ll_setStringValue(addr, ((FeatureStructure2_Type)jcasType).casFeatCode_feature1, v);}    
   
    
  //*--------------*
  //* Feature: feature2

  /** getter for feature2 - gets 
   * @generated */
  public String getFeature2() {
    if (FeatureStructure2_Type.featOkTst && ((FeatureStructure2_Type)jcasType).casFeat_feature2 == null)
      jcasType.jcas.throwFeatMissing("feature2", "org.apache.uima.lucas.indexer.types.test.FeatureStructure2");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FeatureStructure2_Type)jcasType).casFeatCode_feature2);}
    
  /** setter for feature2 - sets  
   * @generated */
  public void setFeature2(String v) {
    if (FeatureStructure2_Type.featOkTst && ((FeatureStructure2_Type)jcasType).casFeat_feature2 == null)
      jcasType.jcas.throwFeatMissing("feature2", "org.apache.uima.lucas.indexer.types.test.FeatureStructure2");
    jcasType.ll_cas.ll_setStringValue(addr, ((FeatureStructure2_Type)jcasType).casFeatCode_feature2, v);}    
   
    
  //*--------------*
  //* Feature: feature3

  /** getter for feature3 - gets 
   * @generated */
  public StringArray getFeature3() {
    if (FeatureStructure2_Type.featOkTst && ((FeatureStructure2_Type)jcasType).casFeat_feature3 == null)
      jcasType.jcas.throwFeatMissing("feature3", "org.apache.uima.lucas.indexer.types.test.FeatureStructure2");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureStructure2_Type)jcasType).casFeatCode_feature3)));}
    
  /** setter for feature3 - sets  
   * @generated */
  public void setFeature3(StringArray v) {
    if (FeatureStructure2_Type.featOkTst && ((FeatureStructure2_Type)jcasType).casFeat_feature3 == null)
      jcasType.jcas.throwFeatMissing("feature3", "org.apache.uima.lucas.indexer.types.test.FeatureStructure2");
    jcasType.ll_cas.ll_setRefValue(addr, ((FeatureStructure2_Type)jcasType).casFeatCode_feature3, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for feature3 - gets an indexed value - 
   * @generated */
  public String getFeature3(int i) {
    if (FeatureStructure2_Type.featOkTst && ((FeatureStructure2_Type)jcasType).casFeat_feature3 == null)
      jcasType.jcas.throwFeatMissing("feature3", "org.apache.uima.lucas.indexer.types.test.FeatureStructure2");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureStructure2_Type)jcasType).casFeatCode_feature3), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureStructure2_Type)jcasType).casFeatCode_feature3), i);}

  /** indexed setter for feature3 - sets an indexed value - 
   * @generated */
  public void setFeature3(int i, String v) { 
    if (FeatureStructure2_Type.featOkTst && ((FeatureStructure2_Type)jcasType).casFeat_feature3 == null)
      jcasType.jcas.throwFeatMissing("feature3", "org.apache.uima.lucas.indexer.types.test.FeatureStructure2");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureStructure2_Type)jcasType).casFeatCode_feature3), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((FeatureStructure2_Type)jcasType).casFeatCode_feature3), i, v);}
  }

    