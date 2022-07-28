
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
 
    abstract public String getRandomText(int numChars);
    
    /**
     * This method finds all the characters from the private variable myText
     * in MarkovModel that follow key and puts all these characters into an
     * ArrayList and then returns this ArrayList. For example, if myText were
     * “this is a test yes this is a test.”, then the call getFollows(“t”)
     * returns an ArrayList with the Strings “h”, “e”, “ “, “h”, “e”, “.” as
     * “t” appears 6 times. The call getFollows(“e”) should return an
     * ArrayList with the Strings “s”, “s”, “s”. This method works even
     * if key is a word. Thus, getFollows(“es”) should return an ArrayList
     * with the Strings “t”, “ “, “t”.
     */
    protected ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<String>();
        int idx = 0;
        
        if(myText == null){
            return follows;
        }
        
        while(idx < myText.length()){
            idx = myText.indexOf(key, idx);
            if(idx == -1 ||
               idx + key.length() >= myText.length()){
                break;
            }
            follows.add(myText.substring(idx + key.length(), idx + key.length() + 1));
            idx += 1;
        }
        
        return follows;
    }


}
