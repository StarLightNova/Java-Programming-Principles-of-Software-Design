
/**
 * Write a description of DepthFilter here.
 * 
 * @author Toleubay Alikhan (StarLightNova)
 * @version 1.0, 07/19/2020
 */
public class DistanceFilter implements Filter{
    private Location location;
    private double maximum;
    
    public DistanceFilter(Location location, double maximum){
        this.location = location;
        this.maximum = maximum;
    }
    
    @Override
    public boolean satisfies(QuakeEntry qe){
        return (qe.getLocation().distanceTo(location) < this.maximum);
    }
    
    public String getName(){
        return "Distance";
    }
}
