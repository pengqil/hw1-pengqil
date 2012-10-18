package cmu.hw1.gene.java;

/****
 *  Use the training sample to construct a file (training_data.txt) 
 *  containing training data with specific format for 
 *  later machine learning. Not including machine learning class 
 *  in this file because it's too slow. The class that does machine
 *  learning is in edu.upenn.cis.taggers.gene.GeneTrainer.java.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import cmu.hw1.gene.model.Sentences;

import edu.upenn.cis.taggers.gene.GeneTrainer;
/**
 * Generage Training samples and train the data to a model
 * @author Pengqi Liu
 * 
 *
 */
public class GeneTagTrainAnnotator extends JCasAnnotator_ImplBase{
  private Map<String,String> sentences = new HashMap<String,String>();
  private Map<String,List<String>> geneTag = new HashMap<String,List<String>>();
  private Set<String> sentencesId1 = new HashSet<String>();
  private List<String> trainData = new ArrayList<String>();
  FileReader fr = null;
  File outFile;
  FileWriter fileWriter;
  GeneTrainer genetr;
  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    /*Read from sample.out for constructing training data*/
    readInFile(aJCas);
    /*create specific format of training data.*/
    createTrainFile();
    /*write the training data to training_data.txt*/    
    try {     
      writeToFile();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    /*Train model from sample data, run when necessary, it takes about 1 hour.*/
    /*
    try {
      genetr = new GeneTrainer("train", "src/main/resources/data/training/gene/data/training_data.txt","null", "src/main/resources/model/geneModel1.crf");
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    */
  }
  /**
   * Read from sample.out for constructing training data
   * */
  
  private void readInFile(JCas aJCas){
    String id = null;
    String sen = null;
    /*Read sample input sentences from JCas and put into a Map*/
    Iterator annotationIter = aJCas.getIndexRepository().getIndex("cmu.hw1.gene.java.index3").iterator();
    while (annotationIter.hasNext()) {
      Sentences s = (Sentences)annotationIter.next();
      id = s.getId();
      sen = s.getText();
      sentences.put(id, sen);
    }
    /*Read output result from sample.out and put into a Map*/
    try {
      fr = new FileReader("src/main/resources/data/testing/sample.out");
      BufferedReader br = new BufferedReader(fr);
      while (br.ready()){
        String sentence = br.readLine();
        //use "|" to split sample.out
        id = sentence.substring(0, sentence.indexOf("|"));
        sen = sentence.substring(sentence.lastIndexOf("|")+1);   
        if(geneTag.containsKey(id)){
          geneTag.get(id).add(sen);
        }else{
          ArrayList<String> tag = new ArrayList<String>();
          tag.add(sen);
          geneTag.put(id, tag);
        }       
      }
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
  
  /**
   * create specific format of training data.
   * */
  private void createTrainFile(){
    //read sample input sentences from Map
    sentencesId1 = sentences.keySet();
    Iterator<String> senIter = sentencesId1.iterator();
    Iterator<String> geneIter;
    String id;
    while(senIter.hasNext()){
      id = senIter.next();
      //find out if the genetag is in this sentence
      if(geneTag.containsKey(id)){
        //get Gene Tag list of this sentence from Map
        geneIter = geneTag.get(id).iterator();
        String text = null;
        String Gene = null;
        int index = 0;
        int temp = 0;
        boolean flag;
        //split the whole sentence and tag the gene word with specific notation
        while(geneIter.hasNext()){
          flag = false;
          text = null;
          Gene = geneIter.next();         
          if(index!=0){
            if(sentences.get(id).substring(index).indexOf(Gene)>0){
              text = sentences.get(id).substring(index, sentences.get(id).substring(index).indexOf(Gene)+index-1);
              flag = true;
            }
          }else{
            text = sentences.get(id).substring(index, sentences.get(id).substring(index).indexOf(Gene)+index);
            flag = true;
          }
          if(text!=null){
            splitSentence(text);
            if(flag == true){
              splitGene(Gene);
              index = index + text.length()+Gene.length();
            }
          }else{
            if(flag == true){
              splitGene(Gene);
              index = index + Gene.length()+1;
            }
          }
        }
        if(index<sentences.get(id).length()){
          text = sentences.get(id).substring(index);
          splitSentence(text);
        }       
      }
      //if gene tag is not in the sentence, simply split the sentence.
      else{
        splitSentence(sentences.get(id));
        
      }
      trainData.add("");
    }
  }
  /**
   * split sentence with specific format
   * @param s input string
   */
  private void splitSentence(String s){
    s.trim();
    String[] to = s.split(" ");
    boolean flag = false;
    for(String str:to){
      flag = false;
      //use Regular Expression to split documents without gene entity 
      //and tag them with O
      Pattern mytoken = Pattern.compile("[,\\.;!?#$%&'()-/\"]+");
      Matcher matcher = mytoken.matcher(str);
      while(matcher.find()){
        if(!(str.substring(0,matcher.start())+"\tO").equals("\tO"))
          trainData.add(str.substring(0,matcher.start())+"\tO");
        if(!(str.substring(matcher.start(),matcher.end())+"\tO").equals("\tO"))
          trainData.add(str.substring(matcher.start(),matcher.end())+"\tO");
        flag = true;
      }
      if(flag == false) trainData.add(str+"\tO");
    }   
  } 
  /**
   * split gene word with specific format with tagging
   * @param s input string
   */
  private void splitGene(String s){
    String[] gene = s.split(" ");
    if(gene.length==1){
      trainData.add(gene[0]+"\tB-GENE");
    }
    else{
      trainData.add(gene[0]+"\tB-GENE");
      for(int i=1;i<gene.length;i++){
        trainData.add(gene[i]+"\tI-GENE");
      }
    }
  }
  /**
   * write the training data to training_data.txt
   * */
  private void writeToFile() throws IOException{
    outFile = new File("src/main/resources/data/training/gene/data/training_data.txt");
    fileWriter = new FileWriter(outFile);
    for(String s:trainData){
      if(!s.equals("\tO")){
        fileWriter.write(s+"\n");
        fileWriter.flush();
      }     
    }
  }
}
