
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "data/earthquakeDataSampleSix1.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "data/earthQuakeDataDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list); sort by magnitude standart
        
        sortByLargestDepth(list); // sort by depth in reverse order
        
        //SortByMagnitudeWithBubbleSort(list); // sort by magnitude using bubble sort
        
        //sortByMagnitudeWithBubbleSortWithCheck(list); // sort by magnitude with bubble sort
        
        //sortByMagnitudeWithCheck(list);
        
        //selectionSort(list);
        
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
        //System.out.println(this.test);
    }
    
    public void selectionSort(ArrayList<QuakeEntry> list){
        for(int i = 0; i < list.size(); i++){
            int max = i;
            for(int j = i; j < list.size(); j++){
                if(list.get(max).getDepth() < list.get(j).getDepth()){
                    max = j;
                }
            }
            QuakeEntry temp_min = list.get(i);
            QuakeEntry t_max = list.get(max);
            list.set(i, t_max);
            list.set(max, temp_min);
        }
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from){
        int maxIndex = from;
        for(int i = from; i < quakeData.size(); i++){
            if(quakeData.get(maxIndex).getDepth() < quakeData.get(i).getDepth()){
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
        int cnt = 1;
        for(int i = 0; i < in.size(); i++){
            System.out.println(cnt);
            if(cnt == 70){
                return;
            }
            int maxIndex = getLargestDepth(in, i);
            cnt++;
            QuakeEntry temp_s = in.get(i);
            QuakeEntry temp_max = in.get(maxIndex);
            in.set(i, temp_max);
            in.set(maxIndex, temp_s);
        }
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        int minIdx = 0;
        for (int i = 1; i< quakeData.size()-numSorted; i++) {

            if (quakeData.get(i).getMagnitude() < quakeData.get(minIdx).getMagnitude()) {
                QuakeEntry qi = quakeData.get(i);
                QuakeEntry qmin = quakeData.get(minIdx);
                quakeData.set(i,qmin);
                quakeData.set(minIdx,qi);        
            }
            minIdx = i;  
         }
    }
    
    
    public void SortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        int n = in.size();
        for(int i = 0; i < n - 1; i++){
            onePassBubbleSort(in, i);
            System.out.println("Printing quakes after path " + i);
            for(QuakeEntry qe : in){
                System.out.println(qe);
            }
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){

        for(int i = 1; i < quakes.size(); i++){
            int j = i - 1;
            if(quakes.get(i).getMagnitude() < quakes.get(j).getMagnitude()){
                return false;
            }
        }
        
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        int n = in.size();
        for(int i = 0; i < n - 1; i++){
            if(checkInSortedOrder(in)){
                return;
            }
            onePassBubbleSort(in, i);
            System.out.println("Printing quakes after path " + i);
            //for(QuakeEntry qe : in){
            //    System.out.println(qe);
            //}
        } 
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        for (int i=0; i< in.size(); i++) {
            if(checkInSortedOrder(in)){
                return;
            }
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            System.out.println("Printing quakes after path " + i);
        }
    }
    
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
