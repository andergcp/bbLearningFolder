
/**
 * Write a description of GladLibHashMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class GladLibHashMap {
    private ArrayList<String> usedWords;
    private ArrayList<String> usedCategories;
    private HashMap<String, ArrayList<String>> myMap;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibHashMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        String[] categories = {"adjective", "noun", "color", "country",
                            "name", "animal", "timeframe", "verb", "fruit"};
        for (String s: categories){
            ArrayList<String> list = readIt(source + "/" + s + ".txt");
            myMap.put(s, list);
        }
        usedWords = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        else if (myMap.keySet().contains(label)) {
            if(!usedCategories.contains(label)){
                usedCategories.add(label);                
            }
            return randomFrom(myMap.get(label));
        }       
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while(usedWords.contains(sub)){
            sub = getSubstitute(w.substring(first+1,last));
        }
        usedWords.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
        /**
     * This method returns the total number of words in all the ArrayLists in
     * the HashMap.
     */
    public int totalWordsInMap(){
        int totalWords = 0;
        for(String key: myMap.keySet()){
            totalWords += myMap.get(key).size();
        }
        System.out.println("Total words in files: " + totalWords);
        return totalWords;
    }
    
    /**
     * This method returns the total number of words in the ArrayLists of the
     * categories that were used for a particular GladLib
     */
    public int totalWordsConsidered(){
        int totalWordsConsidered = 0;
        for (String category: usedCategories){
            totalWordsConsidered += myMap.get(category).size();
        }
        System.out.println("Total words considered: " + totalWordsConsidered);
        return totalWordsConsidered;
    }
    
    public void makeStory(){
        usedWords.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n\nWords used:\n");
        for(int i=0; i < usedWords.size(); i++){
            System.out.println(usedWords.get(i));
        }
    }
}
