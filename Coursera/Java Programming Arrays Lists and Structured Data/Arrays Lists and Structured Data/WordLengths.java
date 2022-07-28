
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class WordLengths {
    /**
     * Return the length of a word, If a word has a non-letter as the first or last character,
     * it should not be counted as part of the word length.
     */
    public int getLength(String word){
        int counter = 0;
        char[] ch = word.toCharArray();
        for (int i=0; i < ch.length; i++){
            if ((i == 0 || i == ch.length - 1) && Character.isLetter(ch[i])){
                counter += 1;
            } else if ( i > 0 && i < ch.length - 1){
                counter += 1;
            }
        }
        return counter;
    }
    
    /**
     * This method reads in the words from resource and count the number of words of each length for all the
     * words in resource, storing these counts in the array counts.
     */
    public void countWordLengths(FileResource resource, int[] counts){
        for(String word: resource.words()){
            int wordLength = getLength(word);
            if(wordLength >= counts.length){
                counts[counts.length - 1] += 1;
            } else {
                counts[wordLength] += 1;                
            }
        }
    }
    
    /**
     * Returns the index position of the largest element in values.
     */
    public int indexOfMax(int[] values){
        int maxValue = 0;
        int maxIndex = 0;
        for (int i=0; i<values.length; i++){
            if (values[i] > maxValue){
                maxValue = values[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    /**
     * Tests CountWordLengths() and indexOfMax() methods
     */
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        for (int i=1; i<counts.length; i++){
            if (counts[i] > 0){
                System.out.println("Words with length: " + i + "= " + counts[i]);                
            }
        }
        int max = indexOfMax(counts);
        System.out.println(max);
    }
}
