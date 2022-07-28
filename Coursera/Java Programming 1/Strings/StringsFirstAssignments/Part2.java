/**
 * Class for Duke-Coursera Java Programming assignment
 * 
 * @author (@andergcp)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        String result = "";
        int startIndex = dna.indexOf(startCodon);
        if (startIndex < 0){
            return result;
        }
        int stopIndex = dna.indexOf(stopCodon, startIndex+3);
        if (stopIndex < 0){
            return result;
        }
        if ((startIndex - stopIndex) % 3 == 0){
            result = dna.substring(startIndex, stopIndex + 3);
        }
        return result;
    }
    
    public void testSimpleGene(){
        String dna = "CAGATCTAAG";
        String res = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("The Gene in the strand " + dna + " is " + res);
        
        dna = "CAGATGTACGAAGTCAG";
        res = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("The Gene in the strand " + dna + " is " + res);
        
        dna = "CAGATGTACGAAGTCAG";
        res = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("The Gene in the strand " + dna + " is " + res);
        
        dna = "CATGATCGTACGAAGTTAACAG";
        res = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("The Gene in the strand " + dna + " is " + res);
        
        dna = "CATGATCGTACAAGTTAACAG";
        res = findSimpleGene(dna, "ATG", "TAA");
        System.out.println("The Gene in the strand " + dna + " is " + res);
        
    }
}
