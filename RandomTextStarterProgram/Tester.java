
/**
 * 
 * @author Toleubay Alikhan 
 * @version 1.0, 07/29/2020
 */

import java.util.ArrayList;
import edu.duke.*;

public class Tester {
    private ArrayList<String> temp;
    public void testGetFollows(){
        MarkovOne markov = new MarkovOne();
        String st = "this is a test yes this is a test.";
        markov.setTraining(st);
        temp = markov.getFollows("es");
        System.out.println(temp);
        System.out.println(temp.size());
    }
    
    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        temp = markov.getFollows("th");
        System.out.println(temp);
        System.out.println(temp.size());
    }
}
