
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        // markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            markov.setRandom(seed);
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' ');
        int seed = 643;
        MarkovWord markovWord = new MarkovWord(3); 
        runModel(markovWord, st, 200, seed); 
    } 
    
    public void testHashMap(){
        // FileResource fr = new FileResource(); 
        // String st = fr.asString(); 
        String st = "this is a test yes this is really a test yes a test this is wow";
        st = st.replace('\n', ' ');
        int seed = 42;
        EfficientMarkovWord eMarkovWord = new EfficientMarkovWord(2); 
        runModel(eMarkovWord, st, 50, seed);         
    }
    
    public void compareMethods(){
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        // String st = "this is a test yes this is really a test yes a test this is wow";
        st = st.replace('\n', ' ');
        int seed = 531;
        int textSize = 100;
        int myOrder = 5;
        System.out.println("Begins EfficientMarkovWord: " + System.nanoTime());
        EfficientMarkovWord eMarkovWord = new EfficientMarkovWord(myOrder); 
        runModel(eMarkovWord, st, textSize, seed);         
        System.out.println("Ends EfficientMarkovWord: " + System.nanoTime());
        
        System.out.println("Begins MarkovWord: " + System.nanoTime());
        MarkovWord MarkovWord = new MarkovWord(myOrder); 
        runModel(MarkovWord, st, textSize, seed);         
        System.out.println("Ends MarkovWord: " + System.nanoTime());
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

}
