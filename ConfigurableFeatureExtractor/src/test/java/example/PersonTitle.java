

/* First created by JCasGen Mon Sep 28 09:59:34 EDT 2009 */
package example;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** A Personal Title.
 * Updated by JCasGen Mon Sep 28 09:59:34 EDT 2009
 * XML source: C:/a/Eclipse/apache/ConfigurableFeatureExtractor/src/test/resources/aeDescriptor.xml
 * @generated */
public class PersonTitle extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(PersonTitle.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected PersonTitle() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public PersonTitle(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public PersonTitle(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public PersonTitle(JCas jcas, int begin, int end) {
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
  //* Feature: Kind

  /** getter for Kind - gets The kind of title - Civilian, Military, or Government.
   * @generated */
  public String getKind() {
    if (PersonTitle_Type.featOkTst && ((PersonTitle_Type)jcasType).casFeat_Kind == null)
      jcasType.jcas.throwFeatMissing("Kind", "example.PersonTitle");
    return jcasType.ll_cas.ll_getStringValue(addr, ((PersonTitle_Type)jcasType).casFeatCode_Kind);}
    
  /** setter for Kind - sets The kind of title - Civilian, Military, or Government. 
   * @generated */
  public void setKind(String v) {
    if (PersonTitle_Type.featOkTst && ((PersonTitle_Type)jcasType).casFeat_Kind == null)
      jcasType.jcas.throwFeatMissing("Kind", "example.PersonTitle");
    jcasType.ll_cas.ll_setStringValue(addr, ((PersonTitle_Type)jcasType).casFeatCode_Kind, v);}    
  }

    