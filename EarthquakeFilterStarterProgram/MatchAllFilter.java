
/**
 * Write a description of DepthFilter here.
 * 
 * @author Toleubay Alikhan (StarLightNova)
 * @version 1.0, 07/19/2020
 */

import java.util.ArrayList;
public class MatchAllFilter implements Filter{
    private ArrayList<Filter> filters;
    
    public MatchAllFilter(){
        this.filters = new ArrayList<>();
    }
    
    public void addFilter(Filter f){
        this.filters.add(f);
    }
    
    @Override 
    public boolean satisfies(QuakeEntry qe){
        for(Filter f : this.filters){
            if(!f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
    
    public String getName(){
        String ans = "";
        for(Filter f : this.filters){
            ans += f.getName() + " ";
        }
        return ans;
    }
}
