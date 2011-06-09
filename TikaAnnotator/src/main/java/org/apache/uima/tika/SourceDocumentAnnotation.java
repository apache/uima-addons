

/* First created by JCasGen Fri Jun 12 15:31:15 CEST 2009 */
package org.apache.uima.tika;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.DocumentAnnotation;


/** 
 * Updated by JCasGen Fri Jun 12 15:31:15 CEST 2009
 * XML source: C:/code/uima/TikaAnnotator/desc/MarkupAnnotationTypeSystem.xml
 * @generated */
public class SourceDocumentAnnotation extends DocumentAnnotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(SourceDocumentAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected SourceDocumentAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public SourceDocumentAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public SourceDocumentAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public SourceDocumentAnnotation(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: uri

  /** getter for uri - gets 
   * @generated */
  public String getUri() {
    if (SourceDocumentAnnotation_Type.featOkTst && ((SourceDocumentAnnotation_Type)jcasType).casFeat_uri == null)
      jcasType.jcas.throwFeatMissing("uri", "org.apache.uima.tika.SourceDocumentAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SourceDocumentAnnotation_Type)jcasType).casFeatCode_uri);}
    
  /** setter for uri - sets  
   * @generated */
  public void setUri(String v) {
    if (SourceDocumentAnnotation_Type.featOkTst && ((SourceDocumentAnnotation_Type)jcasType).casFeat_uri == null)
      jcasType.jcas.throwFeatMissing("uri", "org.apache.uima.tika.SourceDocumentAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((SourceDocumentAnnotation_Type)jcasType).casFeatCode_uri, v);}    
   
    
  //*--------------*
  //* Feature: contentType

  /** getter for contentType - gets 
   * @generated */
  public String getContentType() {
    if (SourceDocumentAnnotation_Type.featOkTst && ((SourceDocumentAnnotation_Type)jcasType).casFeat_contentType == null)
      jcasType.jcas.throwFeatMissing("contentType", "org.apache.uima.tika.SourceDocumentAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SourceDocumentAnnotation_Type)jcasType).casFeatCode_contentType);}
    
  /** setter for contentType - sets  
   * @generated */
  public void setContentType(String v) {
    if (SourceDocumentAnnotation_Type.featOkTst && ((SourceDocumentAnnotation_Type)jcasType).casFeat_contentType == null)
      jcasType.jcas.throwFeatMissing("contentType", "org.apache.uima.tika.SourceDocumentAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((SourceDocumentAnnotation_Type)jcasType).casFeatCode_contentType, v);}    
   
    
  //*--------------*
  //* Feature: features

  /** getter for features - gets 
   * @generated */
  public FSArray getFeatures() {
    if (SourceDocumentAnnotation_Type.featOkTst && ((SourceDocumentAnnotation_Type)jcasType).casFeat_features == null)
      jcasType.jcas.throwFeatMissing("features", "org.apache.uima.tika.SourceDocumentAnnotation");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SourceDocumentAnnotation_Type)jcasType).casFeatCode_features)));}
    
  /** setter for features - sets  
   * @generated */
  public void setFeatures(FSArray v) {
    if (SourceDocumentAnnotation_Type.featOkTst && ((SourceDocumentAnnotation_Type)jcasType).casFeat_features == null)
      jcasType.jcas.throwFeatMissing("features", "org.apache.uima.tika.SourceDocumentAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((SourceDocumentAnnotation_Type)jcasType).casFeatCode_features, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for features - gets an indexed value - 
   * @generated */
  public FeatureValue getFeatures(int i) {
    if (SourceDocumentAnnotation_Type.featOkTst && ((SourceDocumentAnnotation_Type)jcasType).casFeat_features == null)
      jcasType.jcas.throwFeatMissing("features", "org.apache.uima.tika.SourceDocumentAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SourceDocumentAnnotation_Type)jcasType).casFeatCode_features), i);
    return (FeatureValue)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SourceDocumentAnnotation_Type)jcasType).casFeatCode_features), i)));}

  /** indexed setter for features - sets an indexed value - 
   * @generated */
  public void setFeatures(int i, FeatureValue v) { 
    if (SourceDocumentAnnotation_Type.featOkTst && ((SourceDocumentAnnotation_Type)jcasType).casFeat_features == null)
      jcasType.jcas.throwFeatMissing("features", "org.apache.uima.tika.SourceDocumentAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((SourceDocumentAnnotation_Type)jcasType).casFeatCode_features), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((SourceDocumentAnnotation_Type)jcasType).casFeatCode_features), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    