

/* First created by JCasGen Wed Oct 17 12:09:32 EDT 2012 */
package cmu.hw1.gene.model;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Oct 17 12:09:32 EDT 2012
 * XML source: /Users/lpq1990/Documents/workspace/11791/hw1-pengqil/src/main/resources/descriptors/type/GeneTagSystemDescriptor.xml
 * @generated */
public class GeneTag extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(GeneTag.class);
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
  protected GeneTag() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public GeneTag(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public GeneTag(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public GeneTag(JCas jcas, int begin, int end) {
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
    if (GeneTag_Type.featOkTst && ((GeneTag_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "cmu.hw1.gene.model.GeneTag");
    return jcasType.ll_cas.ll_getStringValue(addr, ((GeneTag_Type)jcasType).casFeatCode_id);}
    
  /** setter for id - sets id of sentence 
   * @generated */
  public void setId(String v) {
    if (GeneTag_Type.featOkTst && ((GeneTag_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "cmu.hw1.gene.model.GeneTag");
    jcasType.ll_cas.ll_setStringValue(addr, ((GeneTag_Type)jcasType).casFeatCode_id, v);}    
   
    
  //*--------------*
  //* Feature: geneTag

  /** getter for geneTag - gets 
   * @generated */
  public String getGeneTag() {
    if (GeneTag_Type.featOkTst && ((GeneTag_Type)jcasType).casFeat_geneTag == null)
      jcasType.jcas.throwFeatMissing("geneTag", "cmu.hw1.gene.model.GeneTag");
    return jcasType.ll_cas.ll_getStringValue(addr, ((GeneTag_Type)jcasType).casFeatCode_geneTag);}
    
  /** setter for geneTag - sets  
   * @generated */
  public void setGeneTag(String v) {
    if (GeneTag_Type.featOkTst && ((GeneTag_Type)jcasType).casFeat_geneTag == null)
      jcasType.jcas.throwFeatMissing("geneTag", "cmu.hw1.gene.model.GeneTag");
    jcasType.ll_cas.ll_setStringValue(addr, ((GeneTag_Type)jcasType).casFeatCode_geneTag, v);}    
  }

    