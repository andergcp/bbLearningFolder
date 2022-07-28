
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.*;

public class MarkovModel extends AbstractMarkovModel{
    // private String myText;
    // private Random myRandom;
    private int keyLength;
    
    public MarkovModel(int k) {
        // myRandom = new Random();
        keyLength = k;
    }
    
    // public void setRandom(int seed){
        // myRandom = new Random(seed);
    // }
    
    // public void setTraining(String s){
        // myText = s.trim();
    // }
    
    /**
     * This method generates and returns random text that is numChars long.
     */
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()- keyLength);
        String key = myText.substring(index, index+ keyLength);
        sb.append(key);
        
        for(int k=0; k < numChars- keyLength; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
    
    public String toString(){
        return "MarkovModel of order " + String.valueOf(keyLength);
    }

    // /**
     // * This method finds all the characters from the private variable myText
     // * in MarkovModel that follow key and puts all these characters into an
     // * ArrayList and then returns this ArrayList. For example, if myText were
     // * “this is a test yes this is a test.”, then the call getFollows(“t”)
     // * returns an ArrayList with the Strings “h”, “e”, “ “, “h”, “e”, “.” as
     // * “t” appears 6 times. The call getFollows(“e”) should return an
     // * ArrayList with the Strings “s”, “s”, “s”. This method works even
     // * if key is a word. Thus, getFollows(“es”) should return an ArrayList
     // * with the Strings “t”, “ “, “t”.
     // */
    // public ArrayList<String> getFollows(String key){
        // ArrayList<String> follows = new ArrayList<String>();
        // int idx = 0;
        
        // if(myText == null){
            // return follows;
        // }
        
        // while(idx < myText.length()){
            // idx = myText.indexOf(key, idx);
            // if(idx == -1 ||
               // idx + key.length() >= myText.length()){
                // break;
            // }
            // follows.add(myText.substring(idx + key.length(), idx + key.length() + 1));
            // idx += 1;
        // }
        
        // return follows;
    // }

}
