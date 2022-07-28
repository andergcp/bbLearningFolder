
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel{
    private String[] myText;
    Random myRandom;
    int myOrder;
    HashMap<WordGram, ArrayList<String>> followsMap;
    
    public EfficientMarkovWord(int order) {
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
        buildMap();
        System.out.println("___________________");
        System.out.println("Printing map:");
        printHashMapInfo();
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
        return followsMap.get(kGram);
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
    
    private void buildMap(){
        followsMap = new HashMap<WordGram, ArrayList<String>>();
        WordGram kGram = new WordGram(myText, 0, myOrder);
        
        for(int i=0; i <= myText.length - myOrder; i++){
            ArrayList<String> follows;
            
            if(followsMap.containsKey(kGram)){
                follows = followsMap.get(kGram);
            }
            else{
                follows = new ArrayList<String>();                
            }
            
            if (i + myOrder < myText.length){
                follows.add(myText[i + myOrder]);
            }
            
            followsMap.put(kGram, follows);
            
            if (i + myOrder < myText.length){
                kGram = kGram.shiftAdd(myText[i + myOrder]);
            }
        }
    }
    
    public void printHashMapInfo(){
        int maxSize = 0;
        int size = followsMap.size();
        
        if(size <= 10){
            System.out.println("The hashamap is: " + followsMap);
        }
        System.out.println("Hashmap size: " + size);
        
        for(WordGram wg: followsMap.keySet()){
            int thisSize = followsMap.get(wg).size();
            if(thisSize > maxSize){
                maxSize = thisSize;
            }
        }
        System.out.println("The maxsize is: " + maxSize);
        System.out.println("Key with the max size:");
        for(WordGram wg: followsMap.keySet()){
            int thisSize = followsMap.get(wg).size();
            if(thisSize == maxSize){
                System.out.println(wg);
            }
        }
    }

}
