
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Toleubay Alikhan (StarLightNova)
 * @version 1.0, 07/31/2020
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord mw = new MarkovWord(5);
        runModel(mw, st, 500, 844);
        //MarkovWordOne markovWord = new MarkovWordOne(); 
        //runModel(markovWord, st, 200); 
    } 
    
    public void printHashMapInfo(){
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        EfficientMarkovWord mw = new EfficientMarkovWord(5);
        runModel(mw, st, 500, 531);
        //mw.printHashMapInfo();
        //MarkovWordOne markovWord = new MarkovWordOne(); 
        //runModel(markovWord, st, 200); 
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

}
