package hw1.gene.java;


import model.GeneTag;
import model.Sentences;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

/**
 * Example annotator that detects room numbers using Java 1.4 regular expressions.
 */
public class SentenceAnnotator extends JCasAnnotator_ImplBase {

  /**
   * @see JCasAnnotator_ImplBase#process(JCas)
   */
  public void process(JCas aJCas) {
    // get document text
    String docText = aJCas.getDocumentText();
    String[] Sentence = docText.split("\n");
    for(String s:Sentence){
      int index = s.indexOf(" ");
      String id = s.substring(0, index);
      //System.out.println(id);
      String gTag = s.substring(index+1);
      Sentences sen = new Sentences(aJCas);
      sen.setId(id);
      sen.setText(gTag);
      sen.addToIndexes();
    }
  }
}
