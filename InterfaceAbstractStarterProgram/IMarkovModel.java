
/**
 * Write a description of interface IMarkovModel here.
 * 
 * @author Toleubay Alikhan (StarLightNova)
 * @version 1.0, 07/29/2020
 */

public interface IMarkovModel {
    public void setTraining(String text);
    public void setRandom(int seed);
    
    public String getRandomText(int numChars);
    
}
