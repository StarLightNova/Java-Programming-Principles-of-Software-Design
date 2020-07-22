
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author Toleubay Alikhan (StarLifghtNova)
 * @version 1.0, 07/22/2020
 */

import java.util.Comparator;
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{

    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2){
        String w1 = q1.getInfo().split(" ")[q1.getInfo().split(" ").length - 1];
        String w2 = q2.getInfo().split(" ")[q2.getInfo().split(" ").length - 1];
        
        if(w1.compareTo(w2) < 0) return -1;
        else if(w1.compareTo(w2) > 0) return 1;
        else{
            if(q1.getMagnitude() > q2.getMagnitude()) return 1;
            else if(q1.getMagnitude() < q2.getMagnitude()) return -1;
            return 0;
        }
    }
}
