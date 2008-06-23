
/* First created by JCasGen Tue Mar 25 16:39:54 EDT 2008 */
package org.apache.uima.conceptMapper.support.tokenizer;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** offset tokenizer functionality
 * Updated by JCasGen Tue Mar 25 16:39:54 EDT 2008
 * @generated */
public class TokenAnnotation_Type extends uima.tt.TokenAnnotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (TokenAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = TokenAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new TokenAnnotation(addr, TokenAnnotation_Type.this);
  			   TokenAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new TokenAnnotation(addr, TokenAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = TokenAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.conceptMapper.support.tokenizer.TokenAnnotation");
 
  /** @generated */
  final Feature casFeat_text;
  /** @generated */
  final int     casFeatCode_text;
  /** @generated */ 
  public String getText(int addr) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "org.apache.uima.conceptMapper.support.tokenizer.TokenAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_text);
  }
  /** @generated */    
  public void setText(int addr, String v) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "org.apache.uima.conceptMapper.support.tokenizer.TokenAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_text, v);}
    
  
 
  /** @generated */
  final Feature casFeat_tokenType;
  /** @generated */
  final int     casFeatCode_tokenType;
  /** @generated */ 
  public int getTokenType(int addr) {
        if (featOkTst && casFeat_tokenType == null)
      jcas.throwFeatMissing("tokenType", "org.apache.uima.conceptMapper.support.tokenizer.TokenAnnotation");
    return ll_cas.ll_getIntValue(addr, casFeatCode_tokenType);
  }
  /** @generated */    
  public void setTokenType(int addr, int v) {
        if (featOkTst && casFeat_tokenType == null)
      jcas.throwFeatMissing("tokenType", "org.apache.uima.conceptMapper.support.tokenizer.TokenAnnotation");
    ll_cas.ll_setIntValue(addr, casFeatCode_tokenType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_tokenClass;
  /** @generated */
  final int     casFeatCode_tokenClass;
  /** @generated */ 
  public String getTokenClass(int addr) {
        if (featOkTst && casFeat_tokenClass == null)
      jcas.throwFeatMissing("tokenClass", "org.apache.uima.conceptMapper.support.tokenizer.TokenAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_tokenClass);
  }
  /** @generated */    
  public void setTokenClass(int addr, String v) {
        if (featOkTst && casFeat_tokenClass == null)
      jcas.throwFeatMissing("tokenClass", "org.apache.uima.conceptMapper.support.tokenizer.TokenAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_tokenClass, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public TokenAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_text = jcas.getRequiredFeatureDE(casType, "text", "uima.cas.String", featOkTst);
    casFeatCode_text  = (null == casFeat_text) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_text).getCode();

 
    casFeat_tokenType = jcas.getRequiredFeatureDE(casType, "tokenType", "uima.cas.Integer", featOkTst);
    casFeatCode_tokenType  = (null == casFeat_tokenType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_tokenType).getCode();

 
    casFeat_tokenClass = jcas.getRequiredFeatureDE(casType, "tokenClass", "uima.cas.String", featOkTst);
    casFeatCode_tokenClass  = (null == casFeat_tokenClass) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_tokenClass).getCode();

  }
}



    