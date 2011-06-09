
/* First created by JCasGen Sat Jul 25 21:50:44 CEST 2009 */
package org.apache.uima.lucas.indexer.types.test;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Sat Jul 25 21:50:44 CEST 2009
 * @generated */
public class Annotation1_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Annotation1_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Annotation1_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Annotation1(addr, Annotation1_Type.this);
  			   Annotation1_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Annotation1(addr, Annotation1_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = Annotation1.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.lucas.indexer.types.test.Annotation1");
 
  /** @generated */
  final Feature casFeat_featureString;
  /** @generated */
  final int     casFeatCode_featureString;
  /** @generated */ 
  public String getFeatureString(int addr) {
        if (featOkTst && casFeat_featureString == null)
      jcas.throwFeatMissing("featureString", "org.apache.uima.lucas.indexer.types.test.Annotation1");
    return ll_cas.ll_getStringValue(addr, casFeatCode_featureString);
  }
  /** @generated */    
  public void setFeatureString(int addr, String v) {
        if (featOkTst && casFeat_featureString == null)
      jcas.throwFeatMissing("featureString", "org.apache.uima.lucas.indexer.types.test.Annotation1");
    ll_cas.ll_setStringValue(addr, casFeatCode_featureString, v);}
    
  
 
  /** @generated */
  final Feature casFeat_featureStringArray;
  /** @generated */
  final int     casFeatCode_featureStringArray;
  /** @generated */ 
  public int getFeatureStringArray(int addr) {
        if (featOkTst && casFeat_featureStringArray == null)
      jcas.throwFeatMissing("featureStringArray", "org.apache.uima.lucas.indexer.types.test.Annotation1");
    return ll_cas.ll_getRefValue(addr, casFeatCode_featureStringArray);
  }
  /** @generated */    
  public void setFeatureStringArray(int addr, int v) {
        if (featOkTst && casFeat_featureStringArray == null)
      jcas.throwFeatMissing("featureStringArray", "org.apache.uima.lucas.indexer.types.test.Annotation1");
    ll_cas.ll_setRefValue(addr, casFeatCode_featureStringArray, v);}
    
   /** @generated */
  public String getFeatureStringArray(int addr, int i) {
        if (featOkTst && casFeat_featureStringArray == null)
      jcas.throwFeatMissing("featureStringArray", "org.apache.uima.lucas.indexer.types.test.Annotation1");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_featureStringArray), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_featureStringArray), i);
	return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_featureStringArray), i);
  }
   
  /** @generated */ 
  public void setFeatureStringArray(int addr, int i, String v) {
        if (featOkTst && casFeat_featureStringArray == null)
      jcas.throwFeatMissing("featureStringArray", "org.apache.uima.lucas.indexer.types.test.Annotation1");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_featureStringArray), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_featureStringArray), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_featureStringArray), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_featureInteger;
  /** @generated */
  final int     casFeatCode_featureInteger;
  /** @generated */ 
  public int getFeatureInteger(int addr) {
        if (featOkTst && casFeat_featureInteger == null)
      jcas.throwFeatMissing("featureInteger", "org.apache.uima.lucas.indexer.types.test.Annotation1");
    return ll_cas.ll_getIntValue(addr, casFeatCode_featureInteger);
  }
  /** @generated */    
  public void setFeatureInteger(int addr, int v) {
        if (featOkTst && casFeat_featureInteger == null)
      jcas.throwFeatMissing("featureInteger", "org.apache.uima.lucas.indexer.types.test.Annotation1");
    ll_cas.ll_setIntValue(addr, casFeatCode_featureInteger, v);}
    
  
 
  /** @generated */
  final Feature casFeat_featureFloat;
  /** @generated */
  final int     casFeatCode_featureFloat;
  /** @generated */ 
  public float getFeatureFloat(int addr) {
        if (featOkTst && casFeat_featureFloat == null)
      jcas.throwFeatMissing("featureFloat", "org.apache.uima.lucas.indexer.types.test.Annotation1");
    return ll_cas.ll_getFloatValue(addr, casFeatCode_featureFloat);
  }
  /** @generated */    
  public void setFeatureFloat(int addr, float v) {
        if (featOkTst && casFeat_featureFloat == null)
      jcas.throwFeatMissing("featureFloat", "org.apache.uima.lucas.indexer.types.test.Annotation1");
    ll_cas.ll_setFloatValue(addr, casFeatCode_featureFloat, v);}
    
  
 
  /** @generated */
  final Feature casFeat_featureStructure1;
  /** @generated */
  final int     casFeatCode_featureStructure1;
  /** @generated */ 
  public int getFeatureStructure1(int addr) {
        if (featOkTst && casFeat_featureStructure1 == null)
      jcas.throwFeatMissing("featureStructure1", "org.apache.uima.lucas.indexer.types.test.Annotation1");
    return ll_cas.ll_getRefValue(addr, casFeatCode_featureStructure1);
  }
  /** @generated */    
  public void setFeatureStructure1(int addr, int v) {
        if (featOkTst && casFeat_featureStructure1 == null)
      jcas.throwFeatMissing("featureStructure1", "org.apache.uima.lucas.indexer.types.test.Annotation1");
    ll_cas.ll_setRefValue(addr, casFeatCode_featureStructure1, v);}
    
  
 
  /** @generated */
  final Feature casFeat_featureStructures1;
  /** @generated */
  final int     casFeatCode_featureStructures1;
  /** @generated */ 
  public int getFeatureStructures1(int addr) {
        if (featOkTst && casFeat_featureStructures1 == null)
      jcas.throwFeatMissing("featureStructures1", "org.apache.uima.lucas.indexer.types.test.Annotation1");
    return ll_cas.ll_getRefValue(addr, casFeatCode_featureStructures1);
  }
  /** @generated */    
  public void setFeatureStructures1(int addr, int v) {
        if (featOkTst && casFeat_featureStructures1 == null)
      jcas.throwFeatMissing("featureStructures1", "org.apache.uima.lucas.indexer.types.test.Annotation1");
    ll_cas.ll_setRefValue(addr, casFeatCode_featureStructures1, v);}
    
   /** @generated */
  public int getFeatureStructures1(int addr, int i) {
        if (featOkTst && casFeat_featureStructures1 == null)
      jcas.throwFeatMissing("featureStructures1", "org.apache.uima.lucas.indexer.types.test.Annotation1");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_featureStructures1), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_featureStructures1), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_featureStructures1), i);
  }
   
  /** @generated */ 
  public void setFeatureStructures1(int addr, int i, int v) {
        if (featOkTst && casFeat_featureStructures1 == null)
      jcas.throwFeatMissing("featureStructures1", "org.apache.uima.lucas.indexer.types.test.Annotation1");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_featureStructures1), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_featureStructures1), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_featureStructures1), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Annotation1_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_featureString = jcas.getRequiredFeatureDE(casType, "featureString", "uima.cas.String", featOkTst);
    casFeatCode_featureString  = (null == casFeat_featureString) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_featureString).getCode();

 
    casFeat_featureStringArray = jcas.getRequiredFeatureDE(casType, "featureStringArray", "uima.cas.StringArray", featOkTst);
    casFeatCode_featureStringArray  = (null == casFeat_featureStringArray) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_featureStringArray).getCode();

 
    casFeat_featureInteger = jcas.getRequiredFeatureDE(casType, "featureInteger", "uima.cas.Integer", featOkTst);
    casFeatCode_featureInteger  = (null == casFeat_featureInteger) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_featureInteger).getCode();

 
    casFeat_featureFloat = jcas.getRequiredFeatureDE(casType, "featureFloat", "uima.cas.Float", featOkTst);
    casFeatCode_featureFloat  = (null == casFeat_featureFloat) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_featureFloat).getCode();

 
    casFeat_featureStructure1 = jcas.getRequiredFeatureDE(casType, "featureStructure1", "org.apache.uima.lucas.indexer.types.test.FeatureStructure1", featOkTst);
    casFeatCode_featureStructure1  = (null == casFeat_featureStructure1) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_featureStructure1).getCode();

 
    casFeat_featureStructures1 = jcas.getRequiredFeatureDE(casType, "featureStructures1", "uima.cas.FSArray", featOkTst);
    casFeatCode_featureStructures1  = (null == casFeat_featureStructures1) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_featureStructures1).getCode();

  }
}



    