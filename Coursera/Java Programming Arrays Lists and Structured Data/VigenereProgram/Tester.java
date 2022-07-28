
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Tester {
    public void testTryKeyLength(){
        FileResource fr = new FileResource();
        String file = fr.asString();
        // System.out.println("File: " + file);
        VigenereBreaker vb = new VigenereBreaker();
        int[] result = vb.tryKeyLength(file, 5, 'e');
        for(int value: result){
            System.out.println(value);            
        }
    }
}
