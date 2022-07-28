
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWord implements IMarkovModel{
    private String[] myText;
    Random myRandom;
    int myOrder;
    
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            // System.out.println("Follows----> of " + key + " : " + follows); //Just to test getFollows
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    /**
     * This method returns an ArrayList of all the single words that
     * immediately follow an instance of the WordGram parameter somewhere in
     * the training text. 
     */
    private ArrayList<String> getFollows(WordGram kGram) {
        int kl = kGram.length(); //kGram length
        ArrayList<String> follows = new ArrayList<String>();
        for(int i=0; i < myText.length - kl; i++){
            int index = indexOf(myText, kGram, i);
            if (index != -1 && index + kl < myText.length){
                follows.add(myText[index + kl]);
                i = index;
            }
            else break;
            // if(i+1 < myText.length){
                // if(myText[i].equals(key)) follows.add(myText[i+1]);                
        }
        return follows;
    }

    /**
     * This method returns the first position from start that has words in
     * the array words that match the WordGram target. If there is no such
     * match then return -1.
     */
    private int indexOf(String[] words, WordGram target, int start){
        int tl = target.length(); //Target length
        WordGram wg = new WordGram(words, start, tl);

        for(int i=start; i <= words.length - tl; i++){
            if(wg.equals(target)){
                return i;
            }
            if(i + tl < words.length){
                wg = wg.shiftAdd(words[i + tl]);                
            }
        }
        return -1;
    }

}
