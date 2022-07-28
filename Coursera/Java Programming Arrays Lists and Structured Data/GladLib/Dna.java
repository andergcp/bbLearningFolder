
/**
 * Assignment 1: Codon Count
 * Write a program to find out how many times each codon occurs in a strand
 * of DNA based on reading frames. A strand of DNA is made up of the symbols
 * C, G, T, and A. A codon is three consecutive symbols in a strand of DNA
 * such as ATT or TCC. A reading frame is a way of dividing a strand of DNA
 * into consecutive codons. Consider the following strand of
 * DNA = “CGTTCAAGTTCAA”.
 * 
 * There are three reading frames. 
 * 
 * The first reading frame starts at position 0 and has the codons: “CGT”,
 * “TCA”, “AGT” and “TCA”. Here TCA occurs twice and the others each occur
 * once.
 * The second reading frame starts at position 1 (ignoring the first C
 * character) and has the codons: “GTT”, “CAA”, “GTT”, “CAA”. Here both GTT
 * and CAA occur twice.
 * The third reading frame starts at position 2 (ignoring the first two
 * characters CG) and has the codons: “TTC”, “AAG”, “TTC”. Here TTC occurs
 * twice and AAG occurs once.
 * A map of DNA codons to the number times each codon appears in a reading
 * frame would be helpful in solving this problem.


 * 
 * @andergcp (Anderson Castiblanco) 
 * @version (28th Jan 2021)
 */
import java.util.*;
import edu.duke.*;

public class Dna {
    HashMap<String, Integer> map;
    
    public Dna(){
        map = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna){
        map.clear();
        System.out.println(dna);
        String newDna = dna.substring(start);
        int index = 0;
        
        while(index < newDna.length() - 2){
            String codon = newDna.substring(index, index + 3);
            if (codon.substring(1,2).equals(" ") || codon.substring(2).equals(" ")){
                break;
            }
            if (map.keySet().contains(codon)){
                map.put(codon, map.get(codon) + 1);
            }
            else {
                map.put(codon, 1);
            }
            index += 3;
        }
        
        // for (String s: map.keySet()){
            // System.out.println(s + "->" + map.get(s));
        // }
    }
 
    /**
     * Returns a String, the codon in a reading frame that has the largest
     * count. If there are several such codons, return any one of them.
     * This method assumes the HashMap of codons to counts has already been 
     * built.
     */
    public String getMostCommonCodon(){
        String commonCodon = "";
        int max = 0;
        for(String codon: map.keySet()){
            if(map.get(codon) > max){
                commonCodon = codon;
                max = map.get(codon);
            }
        }
        return commonCodon;
    }
    
    /**
     * This method prints all the codons in the HashMap along with their
     * counts if their count is between start and end, inclusive.
     */
    public void printCodonCounts(int start, int end){
        for(String codon: map.keySet()){
            int value = map.get(codon);
            if(value >= start && value <= end){
                System.out.println(codon + " -> " + value);
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString();
        
        System.out.println("1st cycle:\n");
        buildCodonMap(0, dna);
        System.out.println("Number of unique codons: " + map.keySet().size());
        String commonCodon = getMostCommonCodon();
        printCodonCounts(0, 10000);
        
        System.out.println("2nd cycle:\n");
        buildCodonMap(1, dna);
        System.out.println("Number of unique codons: " + map.keySet().size());
        commonCodon = getMostCommonCodon();
        printCodonCounts(0, 10000);
        
        System.out.println("3rd cycle:\n");
        buildCodonMap(2, dna);
        System.out.println("Number of unique codons: " + map.keySet().size());
        commonCodon = getMostCommonCodon();
        printCodonCounts(0, 10000);
    }
}
