package hw1.gene.java;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import model.GeneTag;
import model.Sentences;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import edu.upenn.cis.taggers.LoadModelException;
import edu.upenn.cis.taggers.Tagger;
import edu.upenn.cis.taggers.gene.GeneTagger;

/**
 * Example annotator that detects room numbers using Java 1.4 regular expressions.
 */
public class GeneTagAnnotator extends JCasAnnotator_ImplBase {

  /**
   * @see JCasAnnotator_ImplBase#process(JCas)
   */
  public void process(JCas aJCas) {
    Tagger tagger = null;
    try {
      tagger = new GeneTagger("src/main/resources/model/geneModel1.crf.gz");
    } catch (LoadModelException e2) {
      // TODO Auto-generated catch block
      e2.printStackTrace();
    }
    Iterator annotationIter = aJCas.getAnnotationIndex(Sentences.type).iterator();
    while (annotationIter.hasNext()) {
      Sentences annot = (Sentences) annotationIter.next();
      String aText = annot.getText();
      String Text = aText;
      String id = annot.getId();      
      int index = 0;
      try {        
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
          //System.out.println(gene);
          GeneTag Gene = new GeneTag(aJCas);
          Gene.setId(id);
          Gene.setGeneTag(gene);          
          if(index>=0){
            index = index + Text.indexOf(gene);
            if(index>=0){
              int count = countblanks(aText.substring(0, index));
              Gene.setBegin(index-count);
              //System.out.println(id);
              count = count + countblanks(gene);
              Gene.setEnd(index + gene.length() - count - 1);
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
      /*
      try {
        PosTagNamedEntityRecognizer posTag = new PosTagNamedEntityRecognizer();
        Map<Integer, Integer> begin2end = new HashMap<Integer, Integer>();
        begin2end = posTag.getGeneSpans(aText);
        Set<Integer> from = begin2end.keySet();
        int count = 0;
        int index;
          for(Integer i:from){
            GeneTag gene = new GeneTag(aJCas);
            int to = begin2end.get(i);
            gene.setGeneTag(aText.substring(i, to));
            index = aText.indexOf(aText.substring(i, to));
            count = countblanks(aText.substring(0, index));
            gene.setId(id);
            gene.setBegin(i-count);
            count = countblanks(aText.substring(0, to));
            gene.setEnd(to-count-1);
            gene.addToIndexes();
          }
      } catch (ResourceInitializationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      */
    }    
  }
  
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

