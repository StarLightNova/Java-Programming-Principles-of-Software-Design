
/**
 * Write a description of DepthFilter here.
 * 
 * @author Toleubay Alikhan (StarLightNova)
 * @version 1.0, 07/19/2020
 */
public class DepthFilter implements Filter{
    private double minimum;
    private double maximum;
    
    public DepthFilter(double minimum, double maximum){
        this.minimum = minimum;
        this.maximum = maximum;
    }
    
    @Override
    public boolean satisfies(QuakeEntry qe){
        return (this.minimum <= qe.getDepth() && qe.getDepth() <= this.maximum);
    }
    
    public String getName(){
        return "Depth";
    }
}
