
/**
 * 
 * 
 * @author Toleubay Alikhan (StarLightNova)
 * @version 1.0, 07/31/2020
 */

import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class EfficientMarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private Integer myOrder;
    private HashMap<WordGram, ArrayList<String>> map;
    
    public EfficientMarkovWord(int myOrder) {
        myRandom = new Random();
        this.myOrder = myOrder;
        map = new HashMap<WordGram, ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
    }
    
    private void buildMap() {

        for (int i=0; i<myText.length-myOrder;i++) {
            WordGram wg = new WordGram(myText,i,myOrder);
            String next = myText[i+myOrder];
            if (map.containsKey(wg)) {
                map.get(wg).add(next);
            }
            else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(next);
                map.put(wg, list);
            }
        }

    }
    
    public String getRandomText(int numWords){
        
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        
        sb.append(key.toString());
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            //System.out.println("KEY: " + key + " seperated: " + follows);
            if (follows == null || follows.size() == 0) {
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
        return map.get(kGram);
    }
    
    public void printHashMapInfo() {

        int maxSetSize = -1;

        for (WordGram wg : map.keySet()) {
            maxSetSize = Math.max(maxSetSize, map.get(wg).size());
            System.out.println(wg+"\t: "+map.get(wg));
        }

        System.out.println("Number of keys:\t"+map.keySet().size() + 1);
        System.out.println("Max Set Size:\t"+maxSetSize);

        //System.out.println("Keys with Max Size:");

        //for (WordGram wg : map.keySet()) {
        //    if (map.get(wg).size()==maxSetSize)
        //        System.out.println(wg);
        //}

    }
}
