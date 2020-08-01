
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
        printHashMapInfo();
    }
    
    @Override
    public ArrayList<String> getFollows(String key) {
        return map.get(key);
    }
    
    public void buildMap(){
        for (int i = 0; i < myText.length() - n; i++) {

            String key = myText.substring(i, i + n);
            String follow = myText.substring(i+n, i+n+1);;

            if (map.containsKey(key)) {
                map.get(key).add(follow);
            }
            else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(follow);
                map.put(key, list);
            }
        }
    }
    
    public void printHashMapInfo() {
        int maxv = Integer.MIN_VALUE;
        String maxKey = "";
        for (String key : map.keySet()) {
            System.out.printf("Key:\t[%s]\tvalues: ", key);
            System.out.println(map.get(key));
            maxv = Math.max(maxv, map.get(key).size()); 
	}
	System.out.println("Max number: " + map.size());
	System.out.println("Max array num:  " + maxv);
	//System.out.println("Max key: " + maxKey);

    }
    
    public String getRandomText(int numChars){
        
        if (myText == null){
            return "";
        }
        buildMap();
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
    
    public String toString(){
        return "EfficientMarkovModel " + this.n;
    }
    
}
