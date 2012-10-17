package cmu.hw1.gene.java;


import cmu.hw1.gene.model.GeneTag;
import cmu.hw1.gene.model.Sentences;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;

/**
 * Annotator that splits source document into sentences with its id
 * @author Pengqi Liu
 */
public class SentenceAnnotator extends JCasAnnotator_ImplBase {
  /**
   * @see JCasAnnotator_ImplBase#process(JCas)
   */
  public void process(JCas aJCas) {
    // get document text
    String docText = aJCas.getDocumentText();
    // split into sentence
    String[] Sentence = docText.split("\n");
    // save to JCas
    for(String s:Sentence){
      int index = s.indexOf(" ");
      String id = s.substring(0, index);
      String gTag = s.substring(index+1);
      Sentences sen = new Sentences(aJCas);
      sen.setId(id);
      sen.setText(gTag);
      sen.addToIndexes();
    }
  }
}
