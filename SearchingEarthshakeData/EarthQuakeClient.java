import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe : quakeData){
            if(qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        //System.out.println(from.distanceTo(new Location(35.988, -78.907)));
        for(QuakeEntry qe : quakeData){
            //System.out.println(qe.getLocation().distanceTo(from));
            if(from.distanceTo(qe.getLocation()) < distMax){
                answer.add(qe);
            }
        }

        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth
    , double maxDepth){
        
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        
        for(QuakeEntry qe : quakeData){
            if(minDepth < qe.getDepth() && qe.getDepth() < maxDepth){
                answer.add(qe);
            }
        }
        
        return answer;
                                                    
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, 
    String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        
        if(where.equals("start")){
            for(QuakeEntry qe : quakeData){
                String qeInfo = qe.getInfo();
                if(phrase.equals(qeInfo.substring(0, phrase.length()))){
                    answer.add(qe);
                }
            }
        }
        else if(where.equals("end")){
            for(QuakeEntry qe : quakeData){
                String qeInfo = qe.getInfo();
                if(phrase.equals(qeInfo.substring(qeInfo.length() - phrase.length(), qeInfo.length()))){
                    answer.add(qe);
                }
            }
        }
        else{
            for(QuakeEntry qe : quakeData){
                if(qe.getInfo().contains(phrase)){
                    answer.add(qe);
                }
            }
        }
        
        
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "https://www.dukelearntoprogram.com//course4/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        
        ArrayList<QuakeEntry> printList = filterByMagnitude(list, 5.0);
        for(QuakeEntry qe : printList){
            System.out.println(qe);
        }


    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "https://www.dukelearntoprogram.com//course4/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> printList = filterByDistanceFrom(list, 1000000.0, city);
        System.out.println(printList.size());
        for(QuakeEntry qe : printList){
            System.out.println(city.distanceTo(qe.getLocation()) + " " + qe.getInfo());
        }
    }
    
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "https://www.dukelearntoprogram.com//course4/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        
        System.out.println("Find quakes with depth between -10000.0 and -5000.0");
        ArrayList<QuakeEntry> printList = filterByDepth(list, -10000.0, -5000.0);
        for(QuakeEntry qe : printList){
            System.out.println(qe);
        }
        
    }
    
    public void quakeByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "https://www.dukelearntoprogram.com//course4/data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        
        ArrayList<QuakeEntry> printList = filterByPhrase(list, "start", "Explosion");
        for(QuakeEntry qe : printList){
            System.out.println(qe);
        }
        System.out.println("Found " + printList.size() + " quakes that match Explosion at the start");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
