

/* First created by JCasGen Wed Oct 17 12:10:00 EDT 2012 */
package cmu.hw1.gene.model;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Oct 17 12:10:00 EDT 2012
 * XML source: /Users/lpq1990/Documents/workspace/11791/hw1-pengqil/src/main/resources/descriptors/type/SentenceTypeDescriptor.xml
 * @generated */
public class Sentences extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Sentences.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Sentences() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Sentences(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Sentences(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public Sentences(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: id

  /** getter for id - gets id of sentence
   * @generated */
  public String getId() {
    if (Sentences_Type.featOkTst && ((Sentences_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "cmu.hw1.gene.model.Sentences");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Sentences_Type)jcasType).casFeatCode_id);}
    
  /** setter for id - sets id of sentence 
   * @generated */
  public void setId(String v) {
    if (Sentences_Type.featOkTst && ((Sentences_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "cmu.hw1.gene.model.Sentences");
    jcasType.ll_cas.ll_setStringValue(addr, ((Sentences_Type)jcasType).casFeatCode_id, v);}    
   
    
  //*--------------*
  //* Feature: text

  /** getter for text - gets sentence
   * @generated */
  public String getText() {
    if (Sentences_Type.featOkTst && ((Sentences_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "cmu.hw1.gene.model.Sentences");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Sentences_Type)jcasType).casFeatCode_text);}
    
  /** setter for text - sets sentence 
   * @generated */
  public void setText(String v) {
    if (Sentences_Type.featOkTst && ((Sentences_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "cmu.hw1.gene.model.Sentences");
    jcasType.ll_cas.ll_setStringValue(addr, ((Sentences_Type)jcasType).casFeatCode_text, v);}    
  }

    