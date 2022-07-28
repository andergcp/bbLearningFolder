
/**
 * Write a program to count how many genes are in a strand of DNA.
 * 
 * @andergcp(Anderson Castiblanco) 
 */
public class Part3 {
    public int findStopCodon (String dna, int startIndex, String stopCodon) {
        // Returns the index of the first occurrence of stopCodon that appears
        // past startIndex and is a multiple of 3 away from startIndex. 
        // If there is no such stopCodon, this method returns -1.
        startIndex = dna.indexOf("ATG", startIndex);
        int endIndex = 0;
        while (true){
            endIndex = dna.indexOf(stopCodon, startIndex + 3);
            if (endIndex == -1){
                return endIndex;
            }
            if ((endIndex - startIndex) % 3 == 0){
                return (endIndex);
            } else {
                startIndex = endIndex + 1;
            }
        }
    }
    public void testFindStopCodon(){
        String dna = "ATGXXXXXXTAA";
        int result = findStopCodon(dna, 0, "TAA");
        System.out.println(dna + " stop codon is in " + result);
        System.out.println("expected 9 " + "result: " + result);
        
        dna = "ATGXXXXXTAA";
        result = findStopCodon(dna, 0, "TAA");
        System.out.println(dna + " stop codon is in " + result);
        System.out.println("expected -1 " + "result: " + result);
        
        dna = "ATGXXXXXXTAG";
        result = findStopCodon(dna, 0, "TAA");
        System.out.println(dna + " stop codon is in " + result);
        System.out.println("expected -1 " + "result: " + result);        
        
        dna = "ATGTTT";
        result = findStopCodon(dna, 0, "TAA");
        System.out.println(dna + " stop codon is in " + result);
        System.out.println("expected -1 " + "result: " + result);        
        
        dna = "ATG";
        result = findStopCodon(dna, 0, "TAA");
        System.out.println(dna + " stop codon is in " + result);
        System.out.println("expected -1 " + "result: " + result);        
        
        dna = "ATGTAA";
        result = findStopCodon(dna, 0, "TAA");
        System.out.println(dna + " stop codon is in " + result);
        System.out.println("expected 3 " + "result: " + result);        
    }
    
    public String findGene (String dna){
        // Return the gene formed from the “ATG” and the closest stop
        // codon that is a multiple of three away. If there is no valid
        // stop codon and therefore no gene, return the empty string.
        
        //First section finds the first ocurrence of startCodon in dna
        String startCodon = "ATG";
        int startIndex = 0;
        startIndex = dna.indexOf(startCodon, startIndex);
        if (startIndex == -1){
            return "";
        }
        
        // Finds the stop index for the diferent stop codons
        int TAAIndex = findStopCodon(dna, startIndex, "TAA");
        int TAGIndex = findStopCodon(dna, startIndex, "TAG");
        int TGAIndex = findStopCodon(dna, startIndex, "TGA");
        
        // Decides wheter choose TAA, TAG or TGA index as stopIndex
        // Depending on the distance from startIndex, -1 is not a valid index
        int endIndex = 0;
        if ((TAGIndex == -1) ||
            (TAAIndex != -1 && TAAIndex < TAGIndex)){
            endIndex = TAAIndex;
        } else {
            endIndex = TAGIndex;
        }
        
        if ((TGAIndex == -1) ||
            (endIndex != -1 && endIndex < TGAIndex)){
            endIndex = endIndex;
        } else {
            endIndex = TGAIndex;
        }
        
        if (endIndex == -1){
            return "";
        }
        return dna.substring(startIndex, endIndex + 3);
    }
    
    public void testFindGene() {
        String dna = "AATGCTAACTAGCTGACTAAT";
        System.out.println("dna strend is: " + dna);
        System.out.println("valid dna is: " + findGene(dna));
        
        dna = "XXXATTXXXXXXTGAXXXX";
        System.out.println("dna strend is: " + dna);
        System.out.println("valid dna is: " + findGene(dna));
        
        dna = "XXXATGXXXTAAXXXTGAXXXX";
        System.out.println("dna strend is: " + dna);
        System.out.println("valid dna is: " + findGene(dna));
        
        dna = "XXXATGXXTAAXXXXTGAXXXX";
        System.out.println("dna strend is: " + dna);
        System.out.println("valid dna is: " + findGene(dna));
        
        dna = "XXXATGXXTAAXXXXTGAXXXTAG";
        System.out.println("dna strend is: " + dna);
        System.out.println("valid dna is: " + findGene(dna));
        
        dna = "XXXATGXXTAAXXXXTAGXXXTGA";
        System.out.println("dna strend is: " + dna);
        System.out.println("valid dna is: " + findGene(dna));
    }
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        String gene = "";
        while (true){
            gene = findGene(dna.substring(startIndex, dna.length()));
            if (gene == ""){
                break;
            }
            System.out.println(gene);
            startIndex = startIndex + gene.length();
        }
    }
    
    public void testPrinAllGenes(){
        String dna = "atggatcctccatatacaacggtatctccacctcaggtttagatctcaacaacggaaccattgccgacatgagacagttaggtatcgtcgagagttacaagctaaaacgagcagtagtcagctctgcatctgaagccgctgaagttctactaagggtggataacatcatccgtgcaagaccaagaaccgccaatagacaacatatgtaacatatttaggatatacctcgaaaataataaaccgccacactgtcattattataattagaaacagaacgcaaaaattatccactatataattcaaagacgcgaaaaaaaaagaacaacgcgtcatagaacttttggcaattcgcgtcacaaataaattttggcaacttatgtttcctcttcgagcagtactcgagccctgtctcaagaatgtaataatacccatcgtaggtatggttaaagatagcatctccacaacctcaaagctccttgccgagagtcgccctcctttgtcgagtaattttcacttttcatatgagaacttattttcttattctttactctcacatcctgtagtgattgacactgcaacagccaccatcactagaagaacagaacaattacttaatagaaaaattatatcttcctcgaaacgatttcctgcttccaacatctacgtatatcaagaagcattcacttaccatgacacagcttcagatttcattattgctgacagctactatatcactactccatctagtagtggccacgccctatgaggcatatcctatcggaaaacaataccccccagtggcaagagtcaatgaatcgtttacatttcaaatttccaatgatacctataaatcgtctgtagacaagacagctcaaataacatacaattgcttcgacttaccgagctggctttcgtttgactctagttctagaacgttctcaggtgaaccttcttctgacttactatctgatgcgaacaccacgttgtatttcaatgtaatactcgagggtacggactctgccgacagcacgtctttgaacaatacataccaatttgttgttacaaaccgtccatccatctcgctatcgtcagatttcaatctattggcgttgttaaaaaactatggttatactaacggcaaaaacgctctgaaactagatcctaatgaagtcttcaacgtgacttttgaccgttcaatgttcactaacgaagaatccattgtgtcgtattacggacgttctcagttgtataatgcgccgttacccaattggctgttcttcgattctggcgagttgaagtttactgggacggcaccggtgataaactcggcgattgctccagaaacaagctacagttttgtcatcatcgctacagacattgaaggattttctgccgttgaggtagaattcgaattagtcatcggggctcaccagttaactacctctattcaaaatagtttgataatcaacgttactgacacaggtaacgtttcatatgacttacctctaaactatgtttatctcgatgacgatcctatttcttctgataaattgggttctataaacttattggatgctccagactgggtggcattagataatgctaccatttccgggtctgtcccagatgaattactcggtaagaactccaatcctgccaatttttctgtgtccatttatgatacttatggtgatgtgatttatttcaacttcgaagttgtctccacaacggatttgtttgccattagttctcttcccaatattaacgctacaaggggtgaatggttctcctactattttttgccttctcagtttacagactacgtgaatacaaacgtttcattagagtttactaattcaagccaagaccatgactgggtgaaattccaatcatctaatttaacattagctggagaagtgcccaagaatttcgacaagctttcattaggtttgaaagcgaaccaaggttcacaatctcaagagctatattttaacatcattggcatggattcaaagataactcactcaaaccacagtgcgaatgcaacgtccacaagaagttctcaccactccacctcaacaagttcttacacatcttctacttacactgcaaaaatttcttctacctccgctgctgctacttcttctgctccagcagcgctgccagcagccaataaaacttcatctcacaataaaaaagcagtagcaattgcgtgcggtgttgctatcccattaggcgttatcctagtagctctcatttgcttcctaatattctggagacgcagaagggaaaatccagacgatgaaaacttaccgcatgctattagtggacctgatttgaataatcctgcaaataaaccaaatcaagaaaacgctacacctttgaacaacccctttgatgatgatgcttcctcgtacgatgatacttcaatagcaagaagattggctgctttgaacactttgaaattggataaccactctgccactgaatctgatatttccagcgtggatgaaaagagagattctctatcaggtatgaatacatacaatgatcagttccaatcccaaagtaaagaagaattattagcaaaacccccagtacagcctccagagagcccgttctttgacccacagaataggtcttcttctgtgtatatggatagtgaaccagcagtaaataaatcctggcgatatactggcaacctgtcaccagtctctgatattgtcagagacagttacggatcacaaaaaactgttgatacagaaaaacttttcgatttagaagcaccagagaaggaaaaacgtacgtcaagggatgtcactatgtcttcactggacccttggaacagcaatattagcccttctcccgtaagaaaatcagtaacaccatcaccatataacgtaacgaagcatcgtaaccgccacttacaaaatattcaagactctcaaagcggtaaaaacggaatcactcccacaacaatgtcaacttcatcttctgacgattttgttccggttaaagatggtgaaaatttttgctgggtccatagcatggaaccagacagaagaccaagtaagaaaaggttagtagatttttcaaataagagtaatgtcaatgttggtcaagttaaggacattcacggacgcatcccagaaatgctgtgattatacgcaacgatattttgcttaattttattttcctgttttattttttattagtggtttacagataccctatattttatttagtttttatacttagagacatttaattttaattccattcttcaaatttcatttttgcacttaaaacaaagatccaaaaatgctctcgccctcttcatattgagaatacactccattcaaaattttgtcgtcaccgctgattaatttttcactaaactgatgaataatcaaaggccccacgtcagaaccgactaaagaagtgagttttattttaggaggttgaaaaccattattgtctggtaaattttcatcttcttgacatttaacccagtttgaatccctttcaatttctgctttttcctccaaactatcgaccctcctgtttctgtccaacttatgtcctagttccaattcgatcgcattaataactgcttcaaatgttattgtgtcatcgttgactttaggtaatttctccaaatgcataatcaaactatttaaggaagatcggaattcgtcgaacacttcagtttccgtaatgatctgatcgtctttatccacatgttgtaattcactaaaatctaaaacgtatttttcaatgcataaatcgttctttttattaataatgcagatggaaaatctgtaaacgtgcgttaatttagaaagaacatccagtataagttcttctatatagtcaattaaagcaggatgcctattaatgggaacgaactgcggcaagttgaatgactggtaagtagtgtagtcgaatgactgaggtgggtatacatttctataaaataaaatcaaattaatgtagcattttaagtataccctcagccacttctctacccatctattcataaagctgacgcaacgattactattttttttttcttcttggatctcagtcgtcgcaaaaacgtataccttctttttccgaccttttttttagctttctggaaaagtttatattagttaaacagggtctagtcttagtgtgaaagctagtggtttcgattgactgatattaagaaagtggaaattaaattagtagtgtagacgtatatgcatatgtatttctcgcctgtttatgtttctacgtacttttgatttatagcaaggggaaaagaaatacatactattttttggtaaaggtgaaagcataatgtaaaagctagaataaaatggacgaaataaagagaggcttagttcatcttttttccaaaaagcacccaatgataataactaaaatgaaaaggatttgccatctgtcagcaacatcagttgtgtgagcaataataaaatcatcacctccgttgcctttagcgcgtttgtcgtttgtatcttccgtaattttagtcttatcaatgggaatcataaattttccaatgaattagcaatttcgtccaattctttttgagcttcttcatatttgctttggaattcttcgcacttcttttcccattcatctctttcttcttccaaagcaacgatccttctacccatttgctcagagttcaaatcggcctctttcagtttatccattgcttccttcagtttggcttcactgtcttctagctgttgttctagatcctggtttttcttggtgtagttctcattattagatctcaagttattggagtcttcagccaattgctttgtatcagacaattgactctctaacttctccacttcactgtcgagttgctcgtttttagcggacaaagatttaatctcgttttctttttcagtgttagattgctctaattctttgagctgttctctcagctcctcatatttttcttgccatgactcagattctaattttaagctattcaatttctctttgatc";
        dna = dna.toUpperCase();
        System.out.println("dna strend is: " + dna);
        printAllGenes(dna);
        
        dna = "XXXATTXXXXXXTGAXXXX";
        System.out.println("dna strend is: " + dna);
        printAllGenes(dna);
        
        dna = "XXXATGXXXTAAXXXTGAXXXX";
        System.out.println("dna strend is: " + dna);
        printAllGenes(dna);
        
        dna = "XXXATGXXTAAXXXXTGAXXXX";
        System.out.println("dna strend is: " + dna);
        printAllGenes(dna);
        
        dna = "XXXATGXXTAAXXXXTGAXXXTAG";
        System.out.println("dna strend is: " + dna);
        printAllGenes(dna);
        
        dna = "XXXATGXXTAAXXXXTAGXXXTGA";
        System.out.println("dna strend is: " + dna);
        printAllGenes(dna);
    }
    
    public int countGenes(String dna){
        int startIndex = 0;
        int count = 0;
        String gene = "";
        while (true){
            gene = findGene(dna.substring(startIndex, dna.length()));
            if (gene == ""){
                return count;
            }
            startIndex = startIndex + gene.length();
            count++;
        }
    }
    
    public void testCountGenes(){
        String dna = "atggatcctccatatacaacggtatctccacctcaggtttagatctcaacaacggaaccattgccgacatgagacagttaggtatcgtcgagagttacaagctaaaacgagcagtagtcagctctgcatctgaagccgctgaagttctactaagggtggataacatcatccgtgcaagaccaagaaccgccaatagacaacatatgtaacatatttaggatatacctcgaaaataataaaccgccacactgtcattattataattagaaacagaacgcaaaaattatccactatataattcaaagacgcgaaaaaaaaagaacaacgcgtcatagaacttttggcaattcgcgtcacaaataaattttggcaacttatgtttcctcttcgagcagtactcgagccctgtctcaagaatgtaataatacccatcgtaggtatggttaaagatagcatctccacaacctcaaagctccttgccgagagtcgccctcctttgtcgagtaattttcacttttcatatgagaacttattttcttattctttactctcacatcctgtagtgattgacactgcaacagccaccatcactagaagaacagaacaattacttaatagaaaaattatatcttcctcgaaacgatttcctgcttccaacatctacgtatatcaagaagcattcacttaccatgacacagcttcagatttcattattgctgacagctactatatcactactccatctagtagtggccacgccctatgaggcatatcctatcggaaaacaataccccccagtggcaagagtcaatgaatcgtttacatttcaaatttccaatgatacctataaatcgtctgtagacaagacagctcaaataacatacaattgcttcgacttaccgagctggctttcgtttgactctagttctagaacgttctcaggtgaaccttcttctgacttactatctgatgcgaacaccacgttgtatttcaatgtaatactcgagggtacggactctgccgacagcacgtctttgaacaatacataccaatttgttgttacaaaccgtccatccatctcgctatcgtcagatttcaatctattggcgttgttaaaaaactatggttatactaacggcaaaaacgctctgaaactagatcctaatgaagtcttcaacgtgacttttgaccgttcaatgttcactaacgaagaatccattgtgtcgtattacggacgttctcagttgtataatgcgccgttacccaattggctgttcttcgattctggcgagttgaagtttactgggacggcaccggtgataaactcggcgattgctccagaaacaagctacagttttgtcatcatcgctacagacattgaaggattttctgccgttgaggtagaattcgaattagtcatcggggctcaccagttaactacctctattcaaaatagtttgataatcaacgttactgacacaggtaacgtttcatatgacttacctctaaactatgtttatctcgatgacgatcctatttcttctgataaattgggttctataaacttattggatgctccagactgggtggcattagataatgctaccatttccgggtctgtcccagatgaattactcggtaagaactccaatcctgccaatttttctgtgtccatttatgatacttatggtgatgtgatttatttcaacttcgaagttgtctccacaacggatttgtttgccattagttctcttcccaatattaacgctacaaggggtgaatggttctcctactattttttgccttctcagtttacagactacgtgaatacaaacgtttcattagagtttactaattcaagccaagaccatgactgggtgaaattccaatcatctaatttaacattagctggagaagtgcccaagaatttcgacaagctttcattaggtttgaaagcgaaccaaggttcacaatctcaagagctatattttaacatcattggcatggattcaaagataactcactcaaaccacagtgcgaatgcaacgtccacaagaagttctcaccactccacctcaacaagttcttacacatcttctacttacactgcaaaaatttcttctacctccgctgctgctacttcttctgctccagcagcgctgccagcagccaataaaacttcatctcacaataaaaaagcagtagcaattgcgtgcggtgttgctatcccattaggcgttatcctagtagctctcatttgcttcctaatattctggagacgcagaagggaaaatccagacgatgaaaacttaccgcatgctattagtggacctgatttgaataatcctgcaaataaaccaaatcaagaaaacgctacacctttgaacaacccctttgatgatgatgcttcctcgtacgatgatacttcaatagcaagaagattggctgctttgaacactttgaaattggataaccactctgccactgaatctgatatttccagcgtggatgaaaagagagattctctatcaggtatgaatacatacaatgatcagttccaatcccaaagtaaagaagaattattagcaaaacccccagtacagcctccagagagcccgttctttgacccacagaataggtcttcttctgtgtatatggatagtgaaccagcagtaaataaatcctggcgatatactggcaacctgtcaccagtctctgatattgtcagagacagttacggatcacaaaaaactgttgatacagaaaaacttttcgatttagaagcaccagagaaggaaaaacgtacgtcaagggatgtcactatgtcttcactggacccttggaacagcaatattagcccttctcccgtaagaaaatcagtaacaccatcaccatataacgtaacgaagcatcgtaaccgccacttacaaaatattcaagactctcaaagcggtaaaaacggaatcactcccacaacaatgtcaacttcatcttctgacgattttgttccggttaaagatggtgaaaatttttgctgggtccatagcatggaaccagacagaagaccaagtaagaaaaggttagtagatttttcaaataagagtaatgtcaatgttggtcaagttaaggacattcacggacgcatcccagaaatgctgtgattatacgcaacgatattttgcttaattttattttcctgttttattttttattagtggtttacagataccctatattttatttagtttttatacttagagacatttaattttaattccattcttcaaatttcatttttgcacttaaaacaaagatccaaaaatgctctcgccctcttcatattgagaatacactccattcaaaattttgtcgtcaccgctgattaatttttcactaaactgatgaataatcaaaggccccacgtcagaaccgactaaagaagtgagttttattttaggaggttgaaaaccattattgtctggtaaattttcatcttcttgacatttaacccagtttgaatccctttcaatttctgctttttcctccaaactatcgaccctcctgtttctgtccaacttatgtcctagttccaattcgatcgcattaataactgcttcaaatgttattgtgtcatcgttgactttaggtaatttctccaaatgcataatcaaactatttaaggaagatcggaattcgtcgaacacttcagtttccgtaatgatctgatcgtctttatccacatgttgtaattcactaaaatctaaaacgtatttttcaatgcataaatcgttctttttattaataatgcagatggaaaatctgtaaacgtgcgttaatttagaaagaacatccagtataagttcttctatatagtcaattaaagcaggatgcctattaatgggaacgaactgcggcaagttgaatgactggtaagtagtgtagtcgaatgactgaggtgggtatacatttctataaaataaaatcaaattaatgtagcattttaagtataccctcagccacttctctacccatctattcataaagctgacgcaacgattactattttttttttcttcttggatctcagtcgtcgcaaaaacgtataccttctttttccgaccttttttttagctttctggaaaagtttatattagttaaacagggtctagtcttagtgtgaaagctagtggtttcgattgactgatattaagaaagtggaaattaaattagtagtgtagacgtatatgcatatgtatttctcgcctgtttatgtttctacgtacttttgatttatagcaaggggaaaagaaatacatactattttttggtaaaggtgaaagcataatgtaaaagctagaataaaatggacgaaataaagagaggcttagttcatcttttttccaaaaagcacccaatgataataactaaaatgaaaaggatttgccatctgtcagcaacatcagttgtgtgagcaataataaaatcatcacctccgttgcctttagcgcgtttgtcgtttgtatcttccgtaattttagtcttatcaatgggaatcataaattttccaatgaattagcaatttcgtccaattctttttgagcttcttcatatttgctttggaattcttcgcacttcttttcccattcatctctttcttcttccaaagcaacgatccttctacccatttgctcagagttcaaatcggcctctttcagtttatccattgcttccttcagtttggcttcactgtcttctagctgttgttctagatcctggtttttcttggtgtagttctcattattagatctcaagttattggagtcttcagccaattgctttgtatcagacaattgactctctaacttctccacttcactgtcgagttgctcgtttttagcggacaaagatttaatctcgttttctttttcagtgttagattgctctaattctttgagctgttctctcagctcctcatatttttcttgccatgactcagattctaattttaagctattcaatttctctttgatc";
        dna = dna.toUpperCase();
        System.out.println("dna strend is: " + dna);
        int numberGenes = countGenes(dna);
        System.out.println("number of genes: " + numberGenes);
        
        dna = "XXXATTXXXXXXTGAXXXX";
        System.out.println("dna strend is: " + dna);
        numberGenes = countGenes(dna);
        System.out.println("number of genes: " + numberGenes);
        
        dna = "XXXATGXXXTAAXXXTGAXXXX";
        System.out.println("dna strend is: " + dna);
        numberGenes = countGenes(dna);
        System.out.println("number of genes: " + numberGenes);
        
        dna = "XXXATGXXTAAXXXXTGAXXXX";
        System.out.println("dna strend is: " + dna);
        numberGenes = countGenes(dna);
        System.out.println("number of genes: " + numberGenes);
        
        dna = "XXXATGXXTAAXXXXTGAXXXTAG";
        System.out.println("dna strend is: " + dna);
        numberGenes = countGenes(dna);
        System.out.println("number of genes: " + numberGenes);
        
        dna = "XXXATGXXTAAXXXXTAGXXXTGA";
        System.out.println("dna strend is: " + dna);
        numberGenes = countGenes(dna);
        System.out.println("number of genes: " + numberGenes);
    }
}
