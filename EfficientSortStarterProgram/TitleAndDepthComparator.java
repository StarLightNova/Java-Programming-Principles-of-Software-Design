
/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @author Toleubay Alikhan (StarLightNova)
 * @version 1.0, 07/22/2020
 */
import java.util.Comparator;
public class TitleAndDepthComparator implements Comparator<QuakeEntry>{
    
    @Override 
    public int compare(QuakeEntry q1, QuakeEntry q2){
        if(q1.getInfo().compareTo(q2.getInfo()) > 0) return 1;
        else if(q1.getInfo().compareTo(q2.getInfo()) < 0) return -1;
        else{
            if(q1.getDepth() > q2.getDepth()) return 1;
            else if(q1.getDepth() < q2.getDepth()) return -1;
            return 0;
        }
    }
}
