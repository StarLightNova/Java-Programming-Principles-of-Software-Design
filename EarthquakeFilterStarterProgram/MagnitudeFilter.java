
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author Toleubay Alikhan (StarLightNova) 
 * @version 1.0, 07/19/2020
 */
public class MagnitudeFilter implements Filter{
    private double minimum;
    private double maximum;
    
    public MagnitudeFilter(double minimum, double maximum){
        this.minimum = minimum;
        this.maximum = maximum;
    }
    
    @Override
    public boolean satisfies(QuakeEntry qe){
        return (this.minimum <= qe.getMagnitude() && 
                                             qe.getMagnitude() <= this.maximum);
    }
    
    public String getName(){
        return "Magnitude";
    }
}
