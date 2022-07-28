
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator <QuakeEntry>{
    public int compare(QuakeEntry q1, QuakeEntry q2){
        String q1Title = q1.getInfo();
        String q2Title = q2.getInfo();
        String lastq1 = q1Title.substring(q1Title.lastIndexOf(" ")+1);
        String lastq2 = q2Title.substring(q2Title.lastIndexOf(" ")+1);
        
        if(lastq1.compareTo(lastq2) == 0){
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return lastq1.compareTo(lastq2);
    }
}
