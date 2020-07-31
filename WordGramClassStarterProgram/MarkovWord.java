
/**
 * Write a description of MarkovWord here.
 * 
 * @author Toleubay Alikhan (StarLightNova)
 * @version 1.0, 07/31/2020
 */

import java.util.Random;
import java.util.ArrayList;

public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private Integer myOrder;
    
    public MarkovWord(int myOrder) {
        myRandom = new Random();
        this.myOrder = myOrder;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        
        sb.append(key.toString());
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            //System.out.println("KEY: " + key + " seperated: " + follows);
            if (follows.size() == 0 || follows == null) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, WordGram target, int start) {

    	for (int i=start;i<words.length-myOrder;i++) {
    		WordGram wg = new WordGram(words,i,myOrder);
    		if (wg.equals(target)) return i;
    	}

    	return -1;
   }
    
    public void testIndexOf(){
        String[] test = "this is just a test yes this is a simple test".split("\\s+");
        //System.out.println(indexOf(test, "this", 0));
        //System.out.println(indexOf(test, "this", 3));
        //System.out.println(indexOf(test, "frog", 0));
        //System.out.println(indexOf(test, "frog", 5));
        //System.out.println(indexOf(test, "simple", 2));
        //System.out.println(indexOf(test, "test", 5));
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        
        int pos = 0;
        while(pos < myText.length){
            int start = indexOf(myText, kGram, pos);
            if(start == -1) break;
            if(start + myOrder >= myText.length) break;
            
            String next = myText[start + myOrder];
            follows.add(next);
            pos = start + myOrder;
        }
        
        return follows;
    }
}
