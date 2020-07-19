
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (Toleubay Alikhan (StarLightNova)
 * @version 07/19/2020
 */
import java.util.*;
public class LargestQuakes {
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "https://www.dukelearntoprogram.com//course4/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read " + list.size() + " quakes found");
        /*
        for(QuakeEntry qe : list){
            System.out.println(qe);
        }
        System.out.println(list.get(indexOfLargest(list)));
        */
        ArrayList<QuakeEntry> printList = getLargest(list, 5);
        for(QuakeEntry qe : printList){
            System.out.println(qe);
        }
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        ArrayList<QuakeEntry> copy = new ArrayList<>(quakeData);
        
        if(howMany > quakeData.size()){
            howMany = quakeData.size();
        }
        
        while(howMany > 0){
            int index = indexOfLargest(copy);
            answer.add(copy.remove(index));
            howMany--;
        }
        
        return answer;
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> quakeData){
        int index = 0;
        double magnitude = 0.000;
        for(int i = 0; i < quakeData.size(); i++){
            if(quakeData.get(i).getMagnitude() > magnitude){
                magnitude = quakeData.get(i).getMagnitude();
                index = i;
            }
        }
        return index;
    }
}
