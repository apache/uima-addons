
/* First created by JCasGen Fri Jun 12 15:31:15 CEST 2009 */
package org.apache.uima.tika;

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
 * Updated by JCasGen Fri Jun 12 15:31:15 CEST 2009
 * @generated */
public class MarkupAnnotation_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (MarkupAnnotation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = MarkupAnnotation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new MarkupAnnotation(addr, MarkupAnnotation_Type.this);
  			   MarkupAnnotation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new MarkupAnnotation(addr, MarkupAnnotation_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = MarkupAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.tika.MarkupAnnotation");
 
  /** @generated */
  final Feature casFeat_attributes;
  /** @generated */
  final int     casFeatCode_attributes;
  /** @generated */ 
  public int getAttributes(int addr) {
        if (featOkTst && casFeat_attributes == null)
      jcas.throwFeatMissing("attributes", "org.apache.uima.tika.MarkupAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_attributes);
  }
  /** @generated */    
  public void setAttributes(int addr, int v) {
        if (featOkTst && casFeat_attributes == null)
      jcas.throwFeatMissing("attributes", "org.apache.uima.tika.MarkupAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_attributes, v);}
    
   /** @generated */
  public int getAttributes(int addr, int i) {
        if (featOkTst && casFeat_attributes == null)
      jcas.throwFeatMissing("attributes", "org.apache.uima.tika.MarkupAnnotation");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_attributes), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_attributes), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_attributes), i);
  }
   
  /** @generated */ 
  public void setAttributes(int addr, int i, int v) {
        if (featOkTst && casFeat_attributes == null)
      jcas.throwFeatMissing("attributes", "org.apache.uima.tika.MarkupAnnotation");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_attributes), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_attributes), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_attributes), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_children;
  /** @generated */
  final int     casFeatCode_children;
  /** @generated */ 
  public int getChildren(int addr) {
        if (featOkTst && casFeat_children == null)
      jcas.throwFeatMissing("children", "org.apache.uima.tika.MarkupAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_children);
  }
  /** @generated */    
  public void setChildren(int addr, int v) {
        if (featOkTst && casFeat_children == null)
      jcas.throwFeatMissing("children", "org.apache.uima.tika.MarkupAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_children, v);}
    
   /** @generated */
  public int getChildren(int addr, int i) {
        if (featOkTst && casFeat_children == null)
      jcas.throwFeatMissing("children", "org.apache.uima.tika.MarkupAnnotation");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_children), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_children), i);
	return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_children), i);
  }
   
  /** @generated */ 
  public void setChildren(int addr, int i, int v) {
        if (featOkTst && casFeat_children == null)
      jcas.throwFeatMissing("children", "org.apache.uima.tika.MarkupAnnotation");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_children), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_children), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_children), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_name;
  /** @generated */
  final int     casFeatCode_name;
  /** @generated */ 
  public String getName(int addr) {
        if (featOkTst && casFeat_name == null)
      jcas.throwFeatMissing("name", "org.apache.uima.tika.MarkupAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_name);
  }
  /** @generated */    
  public void setName(int addr, String v) {
        if (featOkTst && casFeat_name == null)
      jcas.throwFeatMissing("name", "org.apache.uima.tika.MarkupAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_name, v);}
    
  
 
  /** @generated */
  final Feature casFeat_parent;
  /** @generated */
  final int     casFeatCode_parent;
  /** @generated */ 
  public int getParent(int addr) {
        if (featOkTst && casFeat_parent == null)
      jcas.throwFeatMissing("parent", "org.apache.uima.tika.MarkupAnnotation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_parent);
  }
  /** @generated */    
  public void setParent(int addr, int v) {
        if (featOkTst && casFeat_parent == null)
      jcas.throwFeatMissing("parent", "org.apache.uima.tika.MarkupAnnotation");
    ll_cas.ll_setRefValue(addr, casFeatCode_parent, v);}
    
  
 
  /** @generated */
  final Feature casFeat_qualifiedName;
  /** @generated */
  final int     casFeatCode_qualifiedName;
  /** @generated */ 
  public String getQualifiedName(int addr) {
        if (featOkTst && casFeat_qualifiedName == null)
      jcas.throwFeatMissing("qualifiedName", "org.apache.uima.tika.MarkupAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_qualifiedName);
  }
  /** @generated */    
  public void setQualifiedName(int addr, String v) {
        if (featOkTst && casFeat_qualifiedName == null)
      jcas.throwFeatMissing("qualifiedName", "org.apache.uima.tika.MarkupAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_qualifiedName, v);}
    
  
 
  /** @generated */
  final Feature casFeat_uri;
  /** @generated */
  final int     casFeatCode_uri;
  /** @generated */ 
  public String getUri(int addr) {
        if (featOkTst && casFeat_uri == null)
      jcas.throwFeatMissing("uri", "org.apache.uima.tika.MarkupAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_uri);
  }
  /** @generated */    
  public void setUri(int addr, String v) {
        if (featOkTst && casFeat_uri == null)
      jcas.throwFeatMissing("uri", "org.apache.uima.tika.MarkupAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_uri, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public MarkupAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_attributes = jcas.getRequiredFeatureDE(casType, "attributes", "uima.cas.FSArray", featOkTst);
    casFeatCode_attributes  = (null == casFeat_attributes) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_attributes).getCode();

 
    casFeat_children = jcas.getRequiredFeatureDE(casType, "children", "uima.cas.FSArray", featOkTst);
    casFeatCode_children  = (null == casFeat_children) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_children).getCode();

 
    casFeat_name = jcas.getRequiredFeatureDE(casType, "name", "uima.cas.String", featOkTst);
    casFeatCode_name  = (null == casFeat_name) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_name).getCode();

 
    casFeat_parent = jcas.getRequiredFeatureDE(casType, "parent", "org.apache.uima.tika.MarkupAnnotation", featOkTst);
    casFeatCode_parent  = (null == casFeat_parent) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_parent).getCode();

 
    casFeat_qualifiedName = jcas.getRequiredFeatureDE(casType, "qualifiedName", "uima.cas.String", featOkTst);
    casFeatCode_qualifiedName  = (null == casFeat_qualifiedName) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_qualifiedName).getCode();

 
    casFeat_uri = jcas.getRequiredFeatureDE(casType, "uri", "uima.cas.String", featOkTst);
    casFeatCode_uri  = (null == casFeat_uri) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_uri).getCode();

  }
}



    