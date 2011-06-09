

/* First created by JCasGen Tue Nov 06 10:21:22 CET 2007 */
package org.apache.uima.annotator.bsf.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Nov 06 10:21:22 CET 2007
 * XML source: C:/dev/uima/BSFAnnotator/src/main/java/org/apache/uima/annotator/bsf/types/BSFExampleTypes.xml
 * @generated */
public class Firstname extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Firstname.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Firstname() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Firstname(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Firstname(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Firstname(JCas jcas, int begin, int end) {
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
     
}

    