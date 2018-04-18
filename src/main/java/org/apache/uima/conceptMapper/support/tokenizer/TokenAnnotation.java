

/* First created by JCasGen Tue Mar 25 16:39:54 EDT 2008 */
package org.apache.uima.conceptMapper.support.tokenizer;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** offset tokenizer functionality
 * Updated by JCasGen Tue Mar 25 16:39:54 EDT 2008
 * XML source: /OtherStuff/IBM/eclipse-apacheuima/conceptMapper/src/org/apache/uima/conceptMapper/support/tokenizer/TokenAnnotation.xml
 * @generated */
public class TokenAnnotation extends uima.tt.TokenAnnotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(TokenAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected TokenAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated 
   * @param addr -
   * @param type -
   */
  public TokenAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated 
   * @param jcas -
   */
  public TokenAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated 
   * @param jcas -
   * @param begin -
   * @param end -
   */
  public TokenAnnotation(JCas jcas, int begin, int end) {
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
  //* Feature: text

  /** getter for text - gets text of token
   * @generated 
   * @return -
   */
  public String getText() {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "org.apache.uima.conceptMapper.support.tokenizer.TokenAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_text);}
    
  /** setter for text - sets text of token 
   * @generated 
   * @param v -
   */
  public void setText(String v) {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "org.apache.uima.conceptMapper.support.tokenizer.TokenAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_text, v);}    
   
    
  //*--------------*
  //* Feature: tokenType

  /** getter for tokenType - gets 
   * @generated 
   * @return -
   */
  public int getTokenType() {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_tokenType == null)
      jcasType.jcas.throwFeatMissing("tokenType", "org.apache.uima.conceptMapper.support.tokenizer.TokenAnnotation");
    return jcasType.ll_cas.ll_getIntValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_tokenType);}
    
  /** setter for tokenType - sets  
   * @generated 
   * @param v -
   */
  public void setTokenType(int v) {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_tokenType == null)
      jcasType.jcas.throwFeatMissing("tokenType", "org.apache.uima.conceptMapper.support.tokenizer.TokenAnnotation");
    jcasType.ll_cas.ll_setIntValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_tokenType, v);}    
   
    
  //*--------------*
  //* Feature: tokenClass

  /** getter for tokenClass - gets semantic class, or other such classification of this token
   * @generated 
   * @return -
   */
  public String getTokenClass() {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_tokenClass == null)
      jcasType.jcas.throwFeatMissing("tokenClass", "org.apache.uima.conceptMapper.support.tokenizer.TokenAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_tokenClass);}
    
  /** setter for tokenClass - sets semantic class, or other such classification of this token 
   * @generated 
   * @param v -
   */
  public void setTokenClass(String v) {
    if (TokenAnnotation_Type.featOkTst && ((TokenAnnotation_Type)jcasType).casFeat_tokenClass == null)
      jcasType.jcas.throwFeatMissing("tokenClass", "org.apache.uima.conceptMapper.support.tokenizer.TokenAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((TokenAnnotation_Type)jcasType).casFeatCode_tokenClass, v);}    
  }

    