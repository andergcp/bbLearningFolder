
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int keyLength;
    private HashMap<String, ArrayList<String>> map;
    
    public EfficientMarkovModel(int k) {
        keyLength = k;
    }
    
    /**
     * This method generates and returns random text that is numChars long.
     */
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        buildMap();
        printHashMapInfo();
        
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
        return "EfficientMarkovModel of order " + String.valueOf(keyLength);
    }
    
    public void buildMap(){
        map = new HashMap<String, ArrayList<String>>();
        int idx;
        String key = "";
        
        for(int i=0; i < myText.length(); i++){
            if (i + keyLength <= myText.length()){
                key = myText.substring(i, i + keyLength);              
            }
            else break;
            
            ArrayList<String> follows = new ArrayList<String>();            
            
            if(map.containsKey(key)){
                follows = map.get(key);
            }
            
            if(i+keyLength < myText.length()){
                follows.add(myText.substring(i+keyLength, i+keyLength+1));                
            }
            
            map.put(key, follows);
        }
    }

    public ArrayList<String> getFollows(String key){
        return map.get(key);
    }
    
    public void printHashMapInfo(){
        System.out.println("HashMap info:");
        // The map itself:
        if(map.keySet().size() < 20) System.out.println(map);
        
        // The number of keys in map
        int numberKeys = map.keySet().size();
        System.out.println("Number of keys in map: " + numberKeys);
        
        // The size of the largest value in the HashMap—that is, the size
        // of the largest ArrayList of characters
        int largest = 0;
        for(String l: map.keySet()){
            ArrayList<String> follows = map.get(l);
            int size = follows.size();
            if (size > largest) largest = size;
        }
        System.out.println("The size of the largest value in map is: " + largest);
        
        // Print the keys that have the maximum size value.
        System.out.println("the keys with the largest size are: ");
        for(String l: map.keySet()){
            ArrayList<String> follows = map.get(l);
            int size = follows.size();
            if (size == largest) System.out.println(l);
        }    
    }

}

