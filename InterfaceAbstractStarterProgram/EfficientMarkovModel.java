
/**
 * 
 * @author Toleubay Alikhan 
 * @version 1.0, 07/29/2020
 */

import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private int n;
    private HashMap<String, ArrayList<String>> map;
    
    public EfficientMarkovModel(int number) {
        myRandom = new Random();
        n = number;
        map = new HashMap<String, ArrayList<String>>();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
        buildMap();
    }
    
    public void buildMap(){
        for (int i=0; i<myText.length()-n;i++){
            String newKey = myText.substring(i, i+n);
             if(!map.containsKey(newKey)){
                 ArrayList<String> list = getFollows(newKey);
                 map.put(newKey,list);
             }
        }
    }
    
    public void printHashMapInfo(){
       buildMap();
       System.out.println("Keys in the hashmap: "+(map.size()+1));
       int index =0;
       String maxkey = "";
       for (String s : map.keySet()){
           if(map.get(s).size()> index){
            index = map.get(s).size();
            maxkey = s;
           }
        System.out.println(s + "  " + map.get(s));
        }
       System.out.println("max num of keys = " +index);
       System.out.println("the key is this: " + maxkey);
    }
    
    public String getRandomText(int numChars){
        
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-n);
        String key = myText.substring(index, index+n);
        sb.append(key);
        for(int k=0; k < numChars;  k++){
            ArrayList<String> follows = getFollows(key);
            //System.out.println(key + " " + follows);
            if (follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }    
    
}
