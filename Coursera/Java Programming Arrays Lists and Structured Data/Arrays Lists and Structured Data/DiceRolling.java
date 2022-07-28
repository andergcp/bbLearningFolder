
/**
 * Practicing Java concepts - Arrays
 * 
 * @andergcp (Anderson Castiblanco) 
 * @version (21th Jan 2021)
 */
import java.util.Random;

public class DiceRolling {
    public void simulate(int rolls){
        Random rand = new Random();
        int[] counters = new int[13];
        
        for (int i=0; i < rolls; i++){
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;
            counters[d1 + d2] += 1;
        }
        for (int i=0; i < counters.length; i++) {
            if (i > 1){
                System.out.println(i + "'s=\t" + counters[i] + "\t" + 100 * counters[i]/rolls + "%") ;                
            }

        }
    }
}
