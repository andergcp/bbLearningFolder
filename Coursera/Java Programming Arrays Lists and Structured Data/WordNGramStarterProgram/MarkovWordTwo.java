
/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWordTwo implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index+1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key1, key2);
            // System.out.println("Follows----> of " + key1 + " and " + key2 + " : " + follows); //Just to test getFollows
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        for(int i=0; i < myText.length; i++){
            if(i+2 < myText.length){
                if(myText[i].equals(key1) && myText[i+1].equals(key2)){
                    follows.add(myText[i+2]);                
                }
            }
        }
        return follows;
    }

    /**
     * It returns the first location of target1 such that target2 immediately
     * follows it, and the search starts looking at index start.
     */
    private int indexOf(String[] words, String target1, String target2, int start){
        for(int i=start; i+1 < words.length; i++){
            if(words[i].equals(target1) && words[i+1].equals(target2)){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * This method is only for testing the indexOf method.
     */
    public void testIndexOf(){
        String text = "this is just a test yes this is a simple test";
        String[] textArray = text.split("\\s+");
        System.out.println(indexOf(textArray, "this", "is", 0));
        System.out.println(indexOf(textArray, "this", "is", 3));
        System.out.println(indexOf(textArray, "frog", "is", 0));
        System.out.println(indexOf(textArray, "frog", "is", 5));
        System.out.println(indexOf(textArray, "simple", "frog", 2));
        System.out.println(indexOf(textArray, "test", "yes", 1));
    }

}
