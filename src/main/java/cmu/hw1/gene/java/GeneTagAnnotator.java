package cmu.hw1.gene.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import cmu.hw1.gene.model.GeneTag;
import cmu.hw1.gene.model.Sentences;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

import edu.upenn.cis.taggers.LoadModelException;
import edu.upenn.cis.taggers.Tagger;
import edu.upenn.cis.taggers.gene.GeneTagger;

/**
 * Annotator that find genetag in sentences with its begin and end position.
 * @author Pengqi Liu
 *
 */
public class GeneTagAnnotator extends JCasAnnotator_ImplBase {
  Tagger tagger = null;
  /**
   * @see JCasAnnotator_ImplBase#process(JCas)
   */
  public void process(JCas aJCas) {

    
    try {
      //use the geneModel1 to tag the documents
      tagger = new GeneTagger("src/main/resources/model/geneModel1.crf.gz");
    } catch (LoadModelException e2) {
      // TODO Auto-generated catch block
      e2.printStackTrace();
    }
    Iterator annotationIter = aJCas.getAnnotationIndex(Sentences.type).iterator();
    while (annotationIter.hasNext()) {
      // Get sentences with their ids
      Sentences annot = (Sentences) annotationIter.next();
      String aText = annot.getText();
      String Text = aText;
      String id = annot.getId();      
      int index = 0;
      try { 

         //tag the sentence
        ArrayList geneWord = (ArrayList) tagger.taG(aText);
        //System.out.println(result);  
        for(int i=0;i<geneWord.size();i++){
          String gene = geneWord.get(i).toString();
          gene = gene.replace("O", "") ;
          gene = gene.replace("\t", "");
          gene = gene.replace("],[", " ");
          gene = gene.replace("]", "");
          gene = gene.replace("[", "");
          String[] token = gene.split("-");
          gene = "";
          for(String s:token){
            gene = gene + s.trim() + "-";
          }
          gene = gene.substring(0, gene.length()-1);
          GeneTag Gene = new GeneTag(aJCas);
          Gene.setId(id);
          Gene.setGeneTag(gene);  
          //calculate begin and end position
          if(index>=0){
            index = index + Text.indexOf(gene);
            if(index>=0){
              int count = countblanks(aText.substring(0, index));
              Gene.setBegin(index-count);
              count = count + countblanks(gene);
              Gene.setEnd(index + gene.length() - count - 1);
              //add geneTag to JCas index
              Gene.addToIndexes();
            }
            Text = aText.substring(index+1);
            index++;
          }
        }       
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }    
 
  }
  
  /**
   * calculate number of space in a string
   * @param s input string
   * @return
   */
  public static int countblanks(String s){
    int i = 0 ;
    int count = 0;
     while(i < s.length()){
       if(s.charAt(i) == ' ')
         count++;
       i++;       
     }
     return count;
  }
}
