

/* First created by JCasGen Fri Jun 12 15:31:15 CEST 2009 */
package org.apache.uima.tika;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;


/** Simple Feature Value
 * Updated by JCasGen Fri Jun 12 15:31:15 CEST 2009
 * XML source: C:/code/uima/TikaAnnotator/desc/MarkupAnnotationTypeSystem.xml
 * @generated */
public class FeatureValue extends TOP {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(FeatureValue.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected FeatureValue() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public FeatureValue(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public FeatureValue(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: name

  /** getter for name - gets name of the feature
   * @generated */
  public String getName() {
    if (FeatureValue_Type.featOkTst && ((FeatureValue_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "org.apache.uima.tika.FeatureValue");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FeatureValue_Type)jcasType).casFeatCode_name);}
    
  /** setter for name - sets name of the feature 
   * @generated */
  public void setName(String v) {
    if (FeatureValue_Type.featOkTst && ((FeatureValue_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "org.apache.uima.tika.FeatureValue");
    jcasType.ll_cas.ll_setStringValue(addr, ((FeatureValue_Type)jcasType).casFeatCode_name, v);}    
   
    
  //*--------------*
  //* Feature: value

  /** getter for value - gets 
   * @generated */
  public String getValue() {
    if (FeatureValue_Type.featOkTst && ((FeatureValue_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "org.apache.uima.tika.FeatureValue");
    return jcasType.ll_cas.ll_getStringValue(addr, ((FeatureValue_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets  
   * @generated */
  public void setValue(String v) {
    if (FeatureValue_Type.featOkTst && ((FeatureValue_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "org.apache.uima.tika.FeatureValue");
    jcasType.ll_cas.ll_setStringValue(addr, ((FeatureValue_Type)jcasType).casFeatCode_value, v);}    
  }

    