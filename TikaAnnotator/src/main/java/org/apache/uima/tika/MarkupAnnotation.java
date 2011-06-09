

/* First created by JCasGen Fri Jun 12 15:31:15 CEST 2009 */
package org.apache.uima.tika;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Fri Jun 12 15:31:15 CEST 2009
 * XML source: C:/code/uima/TikaAnnotator/desc/MarkupAnnotationTypeSystem.xml
 * @generated */
public class MarkupAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(MarkupAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected MarkupAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public MarkupAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public MarkupAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public MarkupAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: attributes

  /** getter for attributes - gets 
   * @generated */
  public FSArray getAttributes() {
    if (MarkupAnnotation_Type.featOkTst && ((MarkupAnnotation_Type)jcasType).casFeat_attributes == null)
      jcasType.jcas.throwFeatMissing("attributes", "org.apache.uima.tika.MarkupAnnotation");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((MarkupAnnotation_Type)jcasType).casFeatCode_attributes)));}
    
  /** setter for attributes - sets  
   * @generated */
  public void setAttributes(FSArray v) {
    if (MarkupAnnotation_Type.featOkTst && ((MarkupAnnotation_Type)jcasType).casFeat_attributes == null)
      jcasType.jcas.throwFeatMissing("attributes", "org.apache.uima.tika.MarkupAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((MarkupAnnotation_Type)jcasType).casFeatCode_attributes, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for attributes - gets an indexed value - 
   * @generated */
  public FSArray getAttributes(int i) {
    if (MarkupAnnotation_Type.featOkTst && ((MarkupAnnotation_Type)jcasType).casFeat_attributes == null)
      jcasType.jcas.throwFeatMissing("attributes", "org.apache.uima.tika.MarkupAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((MarkupAnnotation_Type)jcasType).casFeatCode_attributes), i);
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((MarkupAnnotation_Type)jcasType).casFeatCode_attributes), i)));}

  /** indexed setter for attributes - sets an indexed value - 
   * @generated */
  public void setAttributes(int i, FSArray v) { 
    if (MarkupAnnotation_Type.featOkTst && ((MarkupAnnotation_Type)jcasType).casFeat_attributes == null)
      jcasType.jcas.throwFeatMissing("attributes", "org.apache.uima.tika.MarkupAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((MarkupAnnotation_Type)jcasType).casFeatCode_attributes), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((MarkupAnnotation_Type)jcasType).casFeatCode_attributes), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: children

  /** getter for children - gets 
   * @generated */
  public FSArray getChildren() {
    if (MarkupAnnotation_Type.featOkTst && ((MarkupAnnotation_Type)jcasType).casFeat_children == null)
      jcasType.jcas.throwFeatMissing("children", "org.apache.uima.tika.MarkupAnnotation");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((MarkupAnnotation_Type)jcasType).casFeatCode_children)));}
    
  /** setter for children - sets  
   * @generated */
  public void setChildren(FSArray v) {
    if (MarkupAnnotation_Type.featOkTst && ((MarkupAnnotation_Type)jcasType).casFeat_children == null)
      jcasType.jcas.throwFeatMissing("children", "org.apache.uima.tika.MarkupAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((MarkupAnnotation_Type)jcasType).casFeatCode_children, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for children - gets an indexed value - 
   * @generated */
  public MarkupAnnotation getChildren(int i) {
    if (MarkupAnnotation_Type.featOkTst && ((MarkupAnnotation_Type)jcasType).casFeat_children == null)
      jcasType.jcas.throwFeatMissing("children", "org.apache.uima.tika.MarkupAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((MarkupAnnotation_Type)jcasType).casFeatCode_children), i);
    return (MarkupAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((MarkupAnnotation_Type)jcasType).casFeatCode_children), i)));}

  /** indexed setter for children - sets an indexed value - 
   * @generated */
  public void setChildren(int i, MarkupAnnotation v) { 
    if (MarkupAnnotation_Type.featOkTst && ((MarkupAnnotation_Type)jcasType).casFeat_children == null)
      jcasType.jcas.throwFeatMissing("children", "org.apache.uima.tika.MarkupAnnotation");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((MarkupAnnotation_Type)jcasType).casFeatCode_children), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((MarkupAnnotation_Type)jcasType).casFeatCode_children), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: name

  /** getter for name - gets 
   * @generated */
  public String getName() {
    if (MarkupAnnotation_Type.featOkTst && ((MarkupAnnotation_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "org.apache.uima.tika.MarkupAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((MarkupAnnotation_Type)jcasType).casFeatCode_name);}
    
  /** setter for name - sets  
   * @generated */
  public void setName(String v) {
    if (MarkupAnnotation_Type.featOkTst && ((MarkupAnnotation_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "org.apache.uima.tika.MarkupAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((MarkupAnnotation_Type)jcasType).casFeatCode_name, v);}    
   
    
  //*--------------*
  //* Feature: parent

  /** getter for parent - gets 
   * @generated */
  public MarkupAnnotation getParent() {
    if (MarkupAnnotation_Type.featOkTst && ((MarkupAnnotation_Type)jcasType).casFeat_parent == null)
      jcasType.jcas.throwFeatMissing("parent", "org.apache.uima.tika.MarkupAnnotation");
    return (MarkupAnnotation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((MarkupAnnotation_Type)jcasType).casFeatCode_parent)));}
    
  /** setter for parent - sets  
   * @generated */
  public void setParent(MarkupAnnotation v) {
    if (MarkupAnnotation_Type.featOkTst && ((MarkupAnnotation_Type)jcasType).casFeat_parent == null)
      jcasType.jcas.throwFeatMissing("parent", "org.apache.uima.tika.MarkupAnnotation");
    jcasType.ll_cas.ll_setRefValue(addr, ((MarkupAnnotation_Type)jcasType).casFeatCode_parent, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: qualifiedName

  /** getter for qualifiedName - gets 
   * @generated */
  public String getQualifiedName() {
    if (MarkupAnnotation_Type.featOkTst && ((MarkupAnnotation_Type)jcasType).casFeat_qualifiedName == null)
      jcasType.jcas.throwFeatMissing("qualifiedName", "org.apache.uima.tika.MarkupAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((MarkupAnnotation_Type)jcasType).casFeatCode_qualifiedName);}
    
  /** setter for qualifiedName - sets  
   * @generated */
  public void setQualifiedName(String v) {
    if (MarkupAnnotation_Type.featOkTst && ((MarkupAnnotation_Type)jcasType).casFeat_qualifiedName == null)
      jcasType.jcas.throwFeatMissing("qualifiedName", "org.apache.uima.tika.MarkupAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((MarkupAnnotation_Type)jcasType).casFeatCode_qualifiedName, v);}    
   
    
  //*--------------*
  //* Feature: uri

  /** getter for uri - gets 
   * @generated */
  public String getUri() {
    if (MarkupAnnotation_Type.featOkTst && ((MarkupAnnotation_Type)jcasType).casFeat_uri == null)
      jcasType.jcas.throwFeatMissing("uri", "org.apache.uima.tika.MarkupAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((MarkupAnnotation_Type)jcasType).casFeatCode_uri);}
    
  /** setter for uri - sets  
   * @generated */
  public void setUri(String v) {
    if (MarkupAnnotation_Type.featOkTst && ((MarkupAnnotation_Type)jcasType).casFeat_uri == null)
      jcasType.jcas.throwFeatMissing("uri", "org.apache.uima.tika.MarkupAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((MarkupAnnotation_Type)jcasType).casFeatCode_uri, v);}    
  }

    