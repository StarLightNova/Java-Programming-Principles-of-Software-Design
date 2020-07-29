
/**
 * 
 * @author Toleubay Alikhan 
 * @version 1.0, 07/29/2020
 */

import java.util.Random;
import java.util.ArrayList;

public class MarkovModel extends AbstractMarkovModel{

    private int n;
    
    public MarkovModel() {
        myRandom = new Random();
    }
    
    public MarkovModel(int n) {
        myRandom = new Random();
        this.n = n;
    }
    
    
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - this.n);
        String key = myText.substring(index, index + this.n);
        sb.append(key);
        
        for(int k=0; k < numChars - this.n; k++){
            ArrayList<String> follows = getFollows(key);
            
            if(follows.size() == 0){
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
        return "MarkovModel order of n";
    }
}
