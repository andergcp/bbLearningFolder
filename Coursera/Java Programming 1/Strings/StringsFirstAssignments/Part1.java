
/**
 * Class for Duke-Coursera Java Programming assignment
 * 
 * @author (@andergcp)
 */
public class Part1 {
    public String findSimpleGene(String dna){
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex < 0){
            return result;
        }
        int stopIndex = dna.indexOf("TAA", startIndex+3);
        if (stopIndex < 0){
            return result;
        }
        if ((startIndex - stopIndex) % 3 == 0){
            result = dna.substring(startIndex, stopIndex + 3);
        }
        return result;
    }
    
    public void testSimpleGene(){
        String dna = "AAATGCCCTAACTAGATTAAGAAACC";
        String res = findSimpleGene(dna);
        System.out.println("The Gene in the strand " + dna + " is " + res);
        
        dna = "CAGATGTACGAAGTCAG";
        res = findSimpleGene(dna);
        System.out.println("The Gene in the strand " + dna + " is " + res);
        
        dna = "CAGATGTACGAAGTCAG";
        res = findSimpleGene(dna);
        System.out.println("The Gene in the strand " + dna + " is " + res);
        
        dna = "CATGATCGTACGAAGTTAACAG";
        res = findSimpleGene(dna);
        System.out.println("The Gene in the strand " + dna + " is " + res);
        
        dna = "CATGATCGTACAAGTTAACAG";
        res = findSimpleGene(dna);
        System.out.println("The Gene in the strand " + dna + " is " + res);
        
    }
}
