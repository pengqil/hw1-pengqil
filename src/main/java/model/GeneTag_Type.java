
/* First created by JCasGen Fri Oct 12 01:48:16 EDT 2012 */
package model;

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
 * Updated by JCasGen Fri Oct 12 13:53:23 EDT 2012
 * @generated */
public class GeneTag_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (GeneTag_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = GeneTag_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new GeneTag(addr, GeneTag_Type.this);
  			   GeneTag_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new GeneTag(addr, GeneTag_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = GeneTag.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("model.GeneTag");



  /** @generated */
  final Feature casFeat_id;
  /** @generated */
  final int     casFeatCode_id;
  /** @generated */ 
  public String getId(int addr) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "model.GeneTag");
    return ll_cas.ll_getStringValue(addr, casFeatCode_id);
  }
  /** @generated */    
  public void setId(int addr, String v) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "model.GeneTag");
    ll_cas.ll_setStringValue(addr, casFeatCode_id, v);}
    
  
 
  /** @generated */
  final Feature casFeat_geneTag;
  /** @generated */
  final int     casFeatCode_geneTag;
  /** @generated */ 
  public String getGeneTag(int addr) {
        if (featOkTst && casFeat_geneTag == null)
      jcas.throwFeatMissing("geneTag", "model.GeneTag");
    return ll_cas.ll_getStringValue(addr, casFeatCode_geneTag);
  }
  /** @generated */    
  public void setGeneTag(int addr, String v) {
        if (featOkTst && casFeat_geneTag == null)
      jcas.throwFeatMissing("geneTag", "model.GeneTag");
    ll_cas.ll_setStringValue(addr, casFeatCode_geneTag, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public GeneTag_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.String", featOkTst);
    casFeatCode_id  = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_id).getCode();

 
    casFeat_geneTag = jcas.getRequiredFeatureDE(casType, "geneTag", "uima.cas.String", featOkTst);
    casFeatCode_geneTag  = (null == casFeat_geneTag) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_geneTag).getCode();

  }
}



    