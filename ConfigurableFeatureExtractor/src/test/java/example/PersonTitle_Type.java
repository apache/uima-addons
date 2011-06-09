
/* First created by JCasGen Mon Sep 28 09:59:34 EDT 2009 */
package example;

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

/** A Personal Title.
 * Updated by JCasGen Mon Sep 28 09:59:34 EDT 2009
 * @generated */
public class PersonTitle_Type extends Annotation_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (PersonTitle_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = PersonTitle_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new PersonTitle(addr, PersonTitle_Type.this);
  			   PersonTitle_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new PersonTitle(addr, PersonTitle_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = PersonTitle.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("example.PersonTitle");
 
  /** @generated */
  final Feature casFeat_Kind;
  /** @generated */
  final int     casFeatCode_Kind;
  /** @generated */ 
  public String getKind(int addr) {
        if (featOkTst && casFeat_Kind == null)
      jcas.throwFeatMissing("Kind", "example.PersonTitle");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Kind);
  }
  /** @generated */    
  public void setKind(int addr, String v) {
        if (featOkTst && casFeat_Kind == null)
      jcas.throwFeatMissing("Kind", "example.PersonTitle");
    ll_cas.ll_setStringValue(addr, casFeatCode_Kind, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public PersonTitle_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Kind = jcas.getRequiredFeatureDE(casType, "Kind", "example.PersonTitleKind", featOkTst);
    casFeatCode_Kind  = (null == casFeat_Kind) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Kind).getCode();

  }
}



    