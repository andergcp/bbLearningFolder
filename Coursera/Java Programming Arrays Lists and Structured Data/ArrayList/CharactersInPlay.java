
/**
 * Assignment 2: Character Names
 * Write a program to print out the main characters in one of Shakespeare’s
 * plays, those with the most speaking parts. You should identify a speaking
 * part by reading the file line-by-line and finding the location of the first
 * period on the line. Then you will assume that everything up to the first
 * period is the name of a character and count how many times that occurs in
 * the file. You will only print those characters that appear more often than
 * others. Notice our method is somewhat error prone. For example, a period is
 * also used to indicate the end of a sentence. By printing out only those
 * characters that appear a lot, we will get rid of most of the errors.
 * Periods that indicate the end of a sentence will likely be a unique phrase
 * so you won’t print that as it would just occur once or maybe twice. 
 * 
 * @andergcp (Anderson Castiblanco) 
 * @version (26th Jan 2021)
 */
import edu.duke.*;
import java.util.*;

public class CharactersInPlay {
    private ArrayList<String> charactersNames;
    private ArrayList<Integer> namesCounters;
    
    public CharactersInPlay(){
        charactersNames = new ArrayList<String>();
        namesCounters = new ArrayList<Integer>();
    }
    /**
     * Updates the possible character's name in charactersNames and
     * increase the counter in namesCounters.
     */
    private void update(String person){
        int IdxName = charactersNames.indexOf(person);    
        // For testing:
        // System.out.println("person " + person);        
        // System.out.println("IdxName " + IdxName);
        
        if (IdxName != -1){
            int value = namesCounters.get(IdxName);
            namesCounters.set(IdxName, value+1);
        }
        else {
            charactersNames.add(person);
            namesCounters.add(1);         
        }
    }
    
    /**
     * If there is a period on the line, extract the possible name of the speaking part
     * and returns it. Otherwise returns null.
     */
    private String findName(String line){
        String name = null;
        int idxPeriod = line.indexOf(".");
        if (idxPeriod != -1){
            return line.substring(0, idxPeriod);
        }
        return name;
    }
    
    /**
     * This method reads the file line-by-line. For each line, if there is a
     * period on the line, extract the possible name of the speaking part, and call
     * update to count it as an occurrence for this person. Make sure you clear the
     * appropriate instance variables before each new file.
     */
    public void findAllCharacters(){
        charactersNames.clear();
        namesCounters.clear();

        FileResource fr = new FileResource();
        
        for(String line: fr.lines()){
            String character = findName(line);
            if (character != null){
                update(character);
            }
        }
    }
    
    /**
     * This method prints out the names of all those characters that have exactly number
     * speaking parts, where number is greater than or equal to num1 and less than or
     * equal to num2. 
     */
    public void charactersWithNumParts(int num1, int num2){
        ArrayList<String> namesInRange = new ArrayList<String>();
        ArrayList<Integer> occurrences = new ArrayList<Integer>();
        
        for(int i=0; i < namesCounters.size(); i++){
            if(namesCounters.get(i) >= num1 && namesCounters.get(i) <= num2){
                namesInRange.add(charactersNames.get(i));
                occurrences.add(namesCounters.get(i));
            }
        }
        
        System.out.println("Names within " + num1 + " and " + num2 + " occurrences.");
        for(int i=0; i < namesInRange.size(); i++){
            System.out.println(namesInRange.get(i) + " " + occurrences.get(i));
        }
    }
    
    public void tester(){
        findAllCharacters();
        for(int i=0; i < charactersNames.size(); i++){
            if(namesCounters.get(i) > 1){
                System.out.println("Character: " + charactersNames.get(i) +
                " occurrences: " + namesCounters.get(i));
            }
        }
        System.out.println("");
        charactersWithNumParts(10, 15);
    }
}
