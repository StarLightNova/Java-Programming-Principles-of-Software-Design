import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "https://www.dukelearntoprogram.com//course4/data/nov20quakedata.atom";
        String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        //System.out.println("read data for "+list.size()+" quakes");

        Filter f = new MinMagFilter(4.0);
        
        //Second one
        //Filter dif = new DistanceFilter(new Location(39.7392, -104.9903), 1000000);
        Filter pf = new PhraseFilter("any", "Kazakhstan");
        //Filter mf = new MagnitudeFilter(3.5, 4.5);
        //Filter df = new DepthFilter(-55000, -20000.0);
        
        //ArrayList<QuakeEntry> m7 = filter(list, df);
        ArrayList<QuakeEntry> m8 = filter(list, pf);
        
        for(QuakeEntry qe : m8){
            System.out.println(qe);
        }
        System.out.println(m8.size());
        /*
         * First one
        Filter mf = new MagnitudeFilter(4.0, 5.0);
        Filter df = new DepthFilter(-35000.0, -12000.0);
        ArrayList<QuakeEntry> m7 = filter(list, mf);
        ArrayList<QuakeEntry> m8 = filter(m7, df);
        
        for(QuakeEntry qe : m8){
            System.out.println(qe);
        }
        
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
        */
    }

    public void testMathAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "https://www.dukelearntoprogram.com//course4/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        Filter magf = new MagnitudeFilter(1.0, 4.0);
        Filter df = new DepthFilter(-180000.0, -30000);
        Filter pf = new PhraseFilter("any", "o");
        maf.addFilter(magf);
        maf.addFilter(df);
        maf.addFilter(pf);
        
        
        ArrayList<QuakeEntry> m7 = filter(list, maf);
        for(QuakeEntry qe : m7){
            System.out.println(qe);
        }
        System.out.println(m7.size());
        System.out.println("Filters used are: " + maf.getName());
        
        /*
        for(QuakeEntry qe : list){
            System.out.println(qe);
        }
        */
    }
    
    public void testMathAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "https://www.dukelearntoprogram.com//course4/data/nov20quakedata.atom";
        
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        Filter magf = new MagnitudeFilter(0.0, 5.0);
        Filter dif = new DistanceFilter(new Location(55.7308, 9.1153), 3000000);
        Filter pf = new PhraseFilter("any", "e");
        maf.addFilter(magf);
        maf.addFilter(dif);
        maf.addFilter(pf);
        
        
        ArrayList<QuakeEntry> m7 = filter(list, maf);
        for(QuakeEntry qe : m7){
            System.out.println(qe);
        }
        System.out.println(m7.size());
        /*
        for(QuakeEntry qe : list){
            System.out.println(qe);
        }
        */
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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
