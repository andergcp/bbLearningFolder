
/**
 * Assignment 1: Most Frequent Word 
 * This program determines the word that occurs the most often in a file.
 * If more than one word occurs as the most often, then return the first such
 * word found. It makes all words lowercase before counting them.
 * Thus, “This” and “this” will both be counted as the lowercase version of
 * “this”. It does not consider punctuation, so “end” and “end,” will be
 * considered different words.
 * 
 * @andergcp (Anderson Castiblanco) 
 * @version (25th Jan 2021)
 */
import java.util.*;
import edu.duke.*;

public class WordFrequencies {
    // The kth position in myFreqs should represent the number of times
    // the kth word in myWords occurs in the file. 
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    /**
     * Adds every unique word to myWords and increment the
     * counter according to the index of the word in myFreqs,
     * to know the number of occurences for each word.
     */
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        
        FileResource resource = new FileResource();
        
        for(String s: resource.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value + 1);
            }
        }
    }
    
    /**
     * Returns an int that is the index location of the largest value in
     * myFreqs. If there is a tie, then return the first such value.
     */
    public int findIndexOfMax(){
        int indexOfMax = -1;
        int maxValue = 0;
        for(int i=0; i < myFreqs.size(); i++){
            if(myFreqs.get(i) > maxValue){
                indexOfMax = i;
                maxValue = myFreqs.get(i);
            }
        }
        return indexOfMax;
    }
    
    public void tester(){
        findUnique();
        System.out.println("# unique words: " + myWords.size());
        for(int i=0; i < myWords.size(); i++){
            System.out.println(myFreqs.get(i)+"\t"+myWords.get(i));
        }
        int IndexOfMax = findIndexOfMax();
        System.out.println("The word that occurs the most is: " +
            myWords.get(IndexOfMax) + " with " + myFreqs.get(IndexOfMax) +
            " occurrences."); 
    }
}
