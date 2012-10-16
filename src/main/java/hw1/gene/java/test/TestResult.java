package hw1.gene.java.test;

import java.io.*;
import java.util.*;

public class TestResult{
  private int tp = 0;
  private int fp = 0;
  private int fn = 0;
  private double Precision;
  private double Recall;
  private double F;
  private List<String> realResult = new ArrayList<String>(); 
  private Map<String,Integer> testResult = new HashMap<String,Integer>(); 
  
  public void calAccuracy(String file1, String file2) throws IOException{
    
    FileReader fr1 = null,fr2 = null;
    try {
      fr1 = new FileReader(file1);
      fr2 = new FileReader(file2);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    BufferedReader br1 = new BufferedReader(fr1);
    BufferedReader br2 = new BufferedReader(fr2);
    while (br1.ready()){
      String sentences1 = br1.readLine();
      fn++;
      realResult.add(sentences1);
    }
    while (br2.ready()){
      String sentences2 = br2.readLine();
      fp++;
      testResult.put(sentences2, 1);
    }
    for (String s:realResult){
      if (testResult.containsKey(s)) tp++;
    }
    fp = fp - tp;
    fn = fn - tp;
    Precision = (double)tp/(tp+fp);
    Recall = (double)tp/(tp+fn);
    F=2*(Precision*Recall)/(Precision+Recall);
    System.out.println(fp+" "+fn+" "+tp);
    System.out.println("Precision:"+Precision);
    System.out.println("Recall:"+Recall);
    System.out.println("F:"+F);
  }
  
  public static void main(String[] args) throws IOException {
    TestResult tr = new TestResult();
    tr.calAccuracy("src/main/resources/sample.out", "hw1-pengqil.out");
  }
}