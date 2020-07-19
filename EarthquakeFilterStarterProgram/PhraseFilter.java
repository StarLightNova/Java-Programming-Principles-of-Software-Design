
/**
 * Write a description of DepthFilter here.
 * 
 * @author Toleubay Alikhan (StarLightNova)
 * @version 1.0, 07/19/2020
 */
public class PhraseFilter implements Filter{
    private String where;
    private String phrase;
    
    public PhraseFilter(String where, String phrase){
        this.where = where;
        this.phrase = phrase;
    }
    
    @Override
    public boolean satisfies(QuakeEntry qe){
        if(this.where.equals("start")){
            return (phrase.equals(qe.getInfo().substring(0, 
            phrase.length())));
        }
        if(this.where.equals("end")){
            return (phrase.equals(qe.getInfo().substring(qe.getInfo().
            length()-phrase.length(), qe.getInfo().length())));
        }
        if(this.where.equals("any")){
            return (qe.getInfo().contains(phrase));
        }
        return false;
    }   
    
    public String getName(){
        return "Phrase";
    }
}
