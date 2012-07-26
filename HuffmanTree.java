package huffmantree;

/**
 *
 * @author Ken
 */
import java.io.*;
import java.util.*;
public class HuffmanTree {

  PriorityQueue<HuffTree> pQueue;
  TreeMap leafOutput;
  
  public static void main(String[] args) {
    HuffmanTree p2 = new HuffmanTree();
    
    //first input file
    p2.ReadFile("c:\\java\\p2input1.txt");
    p2.leafOutput = new TreeMap();
    p2.BuildTree();
    HuffTree t = p2.pQueue.remove();
    HuffBaseNode b = t.getRoot();

    p2.getCode(b, "");
    p2.Output("1");

    //first second file
    p2.ReadFile("c:\\java\\p2input2.txt");
    p2.leafOutput = new TreeMap();
    p2.BuildTree();
    t = p2.pQueue.remove();
    b = t.getRoot();

    p2.getCode(b, "");
    p2.Output("2");    
  }
    
  private void getCode(HuffBaseNode N, String S) {
    if (N.isLeaf()) {
      leafOutput.put(N.getLetter(), N.getWeight() + ","+S); //store in Treemap
      return;
    }
    getCode(N.getLeftLeaf(),S+"0");
    getCode(N.getRightLeaf(),S+"1");
  }
  
  private void Output(String output) {
    Set set = leafOutput.entrySet();
    Iterator i = set.iterator();
    Integer sumWeights = 0;
    Integer sumFrequencies = 0;
    StringBuilder sb = new StringBuilder();
    Formatter formatter = new Formatter(sb,Locale.US);
    try {
      FileWriter fstream = new FileWriter("output_"+output+".txt");
      BufferedWriter out = new BufferedWriter(fstream);      
      formatter.format("%s %10s %10s"+System.lineSeparator(),"char","frequency","Huffman Code");
      while (i.hasNext()) {
        Map.Entry me = (Map.Entry)i.next();
        String l = me.getKey().toString();
        String v = me.getValue().toString();
        String[] temp = v.split(",");
        String f = temp[0];
        String bits = temp[1];
        sumFrequencies += Integer.parseInt(f);
        sumWeights += Integer.parseInt(f) * bits.length();
        formatter.format("%s %10s %10s"+System.lineSeparator(),l,f,bits).toString();
      } 
      Double Lavg = sumWeights / Double.parseDouble(sumFrequencies.toString());
      formatter.format("Lavg = %.3f"+System.lineSeparator(),Lavg).toString();
      formatter.format("CR = %.3f"+System.lineSeparator(), (8-Lavg)/8).toString();
      out.write(sb.toString());
      out.close();
    }
    catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
    
  }
  private void  BuildTree() {
    HuffTree tmp1, tmp2, tmp3 = null;
    while (this.pQueue.size()>1) {
      tmp1 = pQueue.remove();
      tmp2 = pQueue.remove();
      tmp3 = new HuffTree(tmp1.root,tmp2.root,tmp1.weight()+tmp2.weight());
      pQueue.add(tmp3);
    }         
  }
  private void ReadFile(String file) {
    pQueue = new PriorityQueue<HuffTree>();
    
    try {
      FileReader input = new FileReader(file);
      BufferedReader bufRead = new BufferedReader(input);
      String line = null;
      do {
        line = bufRead.readLine();
        if (line != null) {
          String[] temp = line.split(":");
          String letter = temp[0];
          int weight = Integer.parseInt(temp[1]);
          HuffTree node = new HuffTree(letter,weight);
          this.pQueue.add(node);
        }
      } while (line != null);
       
      bufRead.close();
    }
    catch(Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
  
  abstract class HuffBaseNode {
    abstract boolean isLeaf();
    abstract int getWeight();
    
    //kludgy but it works
    abstract HuffBaseNode getLeftLeaf();
    abstract HuffBaseNode getRightLeaf();
    abstract String getLetter();
    
  }
  class HuffTree implements Comparable<HuffTree> {
    private HuffBaseNode root;
    
    public HuffTree(String letter, int wt) {
      root = new Leaf(letter,wt);
    }
    public HuffTree(HuffBaseNode l, HuffBaseNode r, int w) {
      root = new Internal(l,r,w);
    }
    public int weight() {
      return root.getWeight();
    }
    public boolean isLeaf() {
      return root.isLeaf();
    }
    public HuffBaseNode getRoot() {
      return root;
    }
    public int compareTo(HuffTree h){
     if (root.getWeight() < h.weight()) {
       return -1;
     } 
     else if (root.getWeight() == h.weight()) {
       return 0;
     }
     else {
       return 1;
     }
    }
  }
  class Internal extends HuffBaseNode {
    private int weight;
    private HuffBaseNode leftLeaf;
    private HuffBaseNode rightLeaf;

    public Internal(HuffBaseNode l,HuffBaseNode r,int weight) {
      leftLeaf = l;
      rightLeaf = r;
      this.weight = weight;
    }

    public HuffBaseNode getLeftLeaf() {
      return leftLeaf;
    }

    public void setLeftLeaf(Leaf leftLeaf) {
      this.leftLeaf = leftLeaf;
    }

    public HuffBaseNode getRightLeaf() {
      return rightLeaf;
    }

    public void setRightLeaf(Leaf rightLeaf) {
      this.rightLeaf = rightLeaf;
    }

    public int getWeight() {
      return weight;
    }

    public void setWeight(int weight) {
      this.weight = weight;
    }
    public boolean isLeaf() {
      return false;
    }
    
    public String getLetter() {
      return null;
    }
  }
  
  class Leaf extends HuffBaseNode {
    private String letter;
    private int weight;
    
    public Leaf(String letter,int weight) {
      this.letter = letter;
      this.weight = weight;
    }
    public int getWeight() {
      return weight;
    }

    public void setWeight(int weight) {
      this.weight = weight;
    }

    public String getLetter() {
      return letter;
    }

    public void setLetter(String letter) {
      this.letter = letter;
    }
    public boolean isLeaf() {
      return true;
    }
    public HuffBaseNode getLeftLeaf() {
      return null;
    }
    public HuffBaseNode getRightLeaf() {
      return null;
    }    
    
  }
}
