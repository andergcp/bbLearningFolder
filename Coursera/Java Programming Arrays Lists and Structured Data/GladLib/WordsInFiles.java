
/**
 * Assignment 2: Words in Files
 * Write a program to determine which words occur in the greatest number of
 * files, and for each word, which files they occur in.
 * 
 * @andergcp (Anderson Castiblanco) 
 * @version (28th Jan 2021)
 */
import edu.duke.*;
import java.util.*;
import java.io.File;

public class WordsInFiles {
    //This variable stores the words in keys and the names
    // of the files the words appear in the ArrayList
    private HashMap<String, ArrayList<String>> map;
    private ArrayList<String> list;
    
    public WordsInFiles(){
        map = new HashMap<String, ArrayList<String>>();
    }
    
    /**
     * This method should add all the words from f into the map. If a word is
     * not in the map, then you must create a new ArrayList of type String
     * with this word, and have the word map to this ArrayList. If a word is
     * already in the map, then add the current filename to its ArrayList,
     * unless the filename is already in the ArrayList. 
     */
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        String fileName = f.getName();
        for(String word: fr.words()){
            if(map.containsKey(word)){
                list = map.get(word);
            }
            else {
                list = new ArrayList<String>();
            }
            if(list.indexOf(fileName) == -1){
                list.add(fileName);
                map.put(word,list);
            }
        }
    }
    
    /**
     * This method first clears the map, and then uses a DirectoryResource to
     * select a group of files. For each file, it puts all of its words into
     * the map by calling the method addWordsFromFile. The remaining methods
     * to write all assume that the HashMap has been built.
     */
    public void buildWordFileMap(){
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        
        for(File f: dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    /**
     * This method returns the maximum number of files any word appears in,
     * considering all words from a group of files.
     */
    public int maxNumber(){
        int max = 0;
        
        for(String key: map.keySet()){
            int numberOfFiles = map.get(key).size();
            if (numberOfFiles > max){
                max = numberOfFiles;
            }
        }
        return max;
    }
    
    /**
     * This method returns an ArrayList of words that appear in exactly
     * number files.
     */
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> words = new ArrayList<String>();
        
        for(String key: map.keySet()){
            int numberOfFiles = map.get(key).size();
            if (numberOfFiles == number){
                words.add(key);
            }
        }
        return words;
    }
    
    /**
     * This method prints the names of the files this word appears in,
     * one filename per line.
     */
    public void printFilesIn(String word){
        if (map.containsKey(word)){
            System.out.println("The word " + word + "appears in the next file(s):");
            for(String file: map.get(word)){
                System.out.println(file);
            }
        }
        else {
            System.out.println("This word doesn't appear in any file");
        }
    }
    
    public void tester(){
        buildWordFileMap();
        int maxNumber = maxNumber();
        System.out.println("Maximum number of files any word appears in: " + maxNumber);
        
        int numberFiles = 4;
        ArrayList<String> wordsInNumFiles = wordsInNumFiles(numberFiles);
        System.out.println(wordsInNumFiles.size() + " Words that appears in " + numberFiles + " files:");
        for(String word: wordsInNumFiles){
            System.out.println(word);
        }
        
        printFilesIn("tree");
        
    }
}
