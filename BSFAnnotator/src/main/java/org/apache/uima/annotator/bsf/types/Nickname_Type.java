
/* First created by JCasGen Tue Nov 06 10:21:22 CET 2007 */
package org.apache.uima.annotator.bsf.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** 
 * Updated by JCasGen Tue Nov 06 10:21:22 CET 2007
 * @generated */
public class Nickname_Type extends Firstname_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Nickname_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Nickname_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Nickname(addr, Nickname_Type.this);
  			   Nickname_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Nickname(addr, Nickname_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Nickname.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.annotator.bsf.types.Nickname");
 
  /** @generated */
  final Feature casFeat_firstnames;
  /** @generated */
  final int     casFeatCode_firstnames;
  /** @generated */ 
  public int getFirstnames(int addr) {
        if (featOkTst && casFeat_firstnames == null)
      jcas.throwFeatMissing("firstnames", "org.apache.uima.annotator.bsf.types.Nickname");
    return ll_cas.ll_getRefValue(addr, casFeatCode_firstnames);
  }
  /** @generated */    
  public void setFirstnames(int addr, int v) {
        if (featOkTst && casFeat_firstnames == null)
      jcas.throwFeatMissing("firstnames", "org.apache.uima.annotator.bsf.types.Nickname");
    ll_cas.ll_setRefValue(addr, casFeatCode_firstnames, v);}
    
   /** @generated */
  public String getFirstnames(int addr, int i) {
        if (featOkTst && casFeat_firstnames == null)
      jcas.throwFeatMissing("firstnames", "org.apache.uima.annotator.bsf.types.Nickname");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_firstnames), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_firstnames), i);
	return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_firstnames), i);
  }
   
  /** @generated */ 
  public void setFirstnames(int addr, int i, String v) {
        if (featOkTst && casFeat_firstnames == null)
      jcas.throwFeatMissing("firstnames", "org.apache.uima.annotator.bsf.types.Nickname");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_firstnames), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_firstnames), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_firstnames), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Nickname_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_firstnames = jcas.getRequiredFeatureDE(casType, "firstnames", "uima.cas.StringArray", featOkTst);
    casFeatCode_firstnames  = (null == casFeat_firstnames) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_firstnames).getCode();

  }
}



    