

/* First created by JCasGen Tue Nov 06 10:21:22 CET 2007 */
package org.apache.uima.annotator.bsf.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.StringArray;


/** 
 * Updated by JCasGen Tue Nov 06 10:21:22 CET 2007
 * XML source: C:/dev/uima/BSFAnnotator/src/main/java/org/apache/uima/annotator/bsf/types/BSFExampleTypes.xml
 * @generated */
public class Nickname extends Firstname {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Nickname.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Nickname() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Nickname(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Nickname(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Nickname(JCas jcas, int begin, int end) {
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
  //* Feature: firstnames

  /** getter for firstnames - gets List of possible firstnames related to this nickname
   * @generated */
  public StringArray getFirstnames() {
    if (Nickname_Type.featOkTst && ((Nickname_Type)jcasType).casFeat_firstnames == null)
      jcasType.jcas.throwFeatMissing("firstnames", "org.apache.uima.annotator.bsf.types.Nickname");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Nickname_Type)jcasType).casFeatCode_firstnames)));}
    
  /** setter for firstnames - sets List of possible firstnames related to this nickname 
   * @generated */
  public void setFirstnames(StringArray v) {
    if (Nickname_Type.featOkTst && ((Nickname_Type)jcasType).casFeat_firstnames == null)
      jcasType.jcas.throwFeatMissing("firstnames", "org.apache.uima.annotator.bsf.types.Nickname");
    jcasType.ll_cas.ll_setRefValue(addr, ((Nickname_Type)jcasType).casFeatCode_firstnames, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for firstnames - gets an indexed value - List of possible firstnames related to this nickname
   * @generated */
  public String getFirstnames(int i) {
    if (Nickname_Type.featOkTst && ((Nickname_Type)jcasType).casFeat_firstnames == null)
      jcasType.jcas.throwFeatMissing("firstnames", "org.apache.uima.annotator.bsf.types.Nickname");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Nickname_Type)jcasType).casFeatCode_firstnames), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Nickname_Type)jcasType).casFeatCode_firstnames), i);}

  /** indexed setter for firstnames - sets an indexed value - List of possible firstnames related to this nickname
   * @generated */
  public void setFirstnames(int i, String v) { 
    if (Nickname_Type.featOkTst && ((Nickname_Type)jcasType).casFeat_firstnames == null)
      jcasType.jcas.throwFeatMissing("firstnames", "org.apache.uima.annotator.bsf.types.Nickname");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((Nickname_Type)jcasType).casFeatCode_firstnames), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((Nickname_Type)jcasType).casFeatCode_firstnames), i, v);}
  }

    