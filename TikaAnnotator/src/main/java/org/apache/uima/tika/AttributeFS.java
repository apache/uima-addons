

/* First created by JCasGen Fri Jun 12 15:31:15 CEST 2009 */
package org.apache.uima.tika;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;


/** Representation of an attribute
 * Updated by JCasGen Fri Jun 12 15:31:15 CEST 2009
 * XML source: C:/code/uima/TikaAnnotator/desc/MarkupAnnotationTypeSystem.xml
 * @generated */
public class AttributeFS extends TOP {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(AttributeFS.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected AttributeFS() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public AttributeFS(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public AttributeFS(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: localName

  /** getter for localName - gets 
   * @generated */
  public String getLocalName() {
    if (AttributeFS_Type.featOkTst && ((AttributeFS_Type)jcasType).casFeat_localName == null)
      jcasType.jcas.throwFeatMissing("localName", "org.apache.uima.tika.AttributeFS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((AttributeFS_Type)jcasType).casFeatCode_localName);}
    
  /** setter for localName - sets  
   * @generated */
  public void setLocalName(String v) {
    if (AttributeFS_Type.featOkTst && ((AttributeFS_Type)jcasType).casFeat_localName == null)
      jcasType.jcas.throwFeatMissing("localName", "org.apache.uima.tika.AttributeFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((AttributeFS_Type)jcasType).casFeatCode_localName, v);}    
   
    
  //*--------------*
  //* Feature: qualifiedName

  /** getter for qualifiedName - gets 
   * @generated */
  public String getQualifiedName() {
    if (AttributeFS_Type.featOkTst && ((AttributeFS_Type)jcasType).casFeat_qualifiedName == null)
      jcasType.jcas.throwFeatMissing("qualifiedName", "org.apache.uima.tika.AttributeFS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((AttributeFS_Type)jcasType).casFeatCode_qualifiedName);}
    
  /** setter for qualifiedName - sets  
   * @generated */
  public void setQualifiedName(String v) {
    if (AttributeFS_Type.featOkTst && ((AttributeFS_Type)jcasType).casFeat_qualifiedName == null)
      jcasType.jcas.throwFeatMissing("qualifiedName", "org.apache.uima.tika.AttributeFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((AttributeFS_Type)jcasType).casFeatCode_qualifiedName, v);}    
   
    
  //*--------------*
  //* Feature: uri

  /** getter for uri - gets 
   * @generated */
  public String getUri() {
    if (AttributeFS_Type.featOkTst && ((AttributeFS_Type)jcasType).casFeat_uri == null)
      jcasType.jcas.throwFeatMissing("uri", "org.apache.uima.tika.AttributeFS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((AttributeFS_Type)jcasType).casFeatCode_uri);}
    
  /** setter for uri - sets  
   * @generated */
  public void setUri(String v) {
    if (AttributeFS_Type.featOkTst && ((AttributeFS_Type)jcasType).casFeat_uri == null)
      jcasType.jcas.throwFeatMissing("uri", "org.apache.uima.tika.AttributeFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((AttributeFS_Type)jcasType).casFeatCode_uri, v);}    
   
    
  //*--------------*
  //* Feature: value

  /** getter for value - gets 
   * @generated */
  public String getValue() {
    if (AttributeFS_Type.featOkTst && ((AttributeFS_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "org.apache.uima.tika.AttributeFS");
    return jcasType.ll_cas.ll_getStringValue(addr, ((AttributeFS_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets  
   * @generated */
  public void setValue(String v) {
    if (AttributeFS_Type.featOkTst && ((AttributeFS_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "org.apache.uima.tika.AttributeFS");
    jcasType.ll_cas.ll_setStringValue(addr, ((AttributeFS_Type)jcasType).casFeatCode_value, v);}    
  }

    