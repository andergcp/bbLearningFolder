
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class Tester {
    /**
     * This method creates a MarkovOne object, sets the training text as
     * “this is a test yes this is a test.”. Then have it call getFollows and
     * prints out the resulting ArrayList and also its size
     */
    public void testGetFollows(){
        MarkovOne markov = new MarkovOne();
        markov.setTraining("this is a test yes this is a test.");
        ArrayList<String> follows = markov.getFollows("t");
        System.out.println("key 't'" + follows + " size->" + follows.size());
        follows = markov.getFollows("e");
        System.out.println("key 'e'" + follows + " size->" + follows.size());
        follows = markov.getFollows("es");
        System.out.println("key 'es'" + follows + " size->" + follows.size());
        follows = markov.getFollows(".");
        System.out.println("key '.'" + follows + " size->" + follows.size());
        follows = markov.getFollows("t.");
        System.out.println("key 't.'" + follows + " size->" + follows.size());
    }
    
    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("he");
        System.out.println("key 'he'" + follows + " size->" + follows.size());
    }
}
