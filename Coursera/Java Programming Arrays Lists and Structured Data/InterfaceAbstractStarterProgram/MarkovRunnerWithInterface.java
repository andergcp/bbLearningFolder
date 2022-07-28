
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 200;
        int seed = 792;
        
        // IMarkovModel mz = new MarkovZero();
        // runModel(mz, st, size, seed);
    
        // IMarkovModel mOne = new MarkovOne();
        // runModel(mOne, st, size, seed);
        
        // IMarkovModel mThree = new MarkovModel(3);
        // runModel(mThree, st, size, seed);
        
        // IMarkovModel mFour = new MarkovFour();
        // runModel(mFour, st, size, seed);

        IMarkovModel mEM = new EfficientMarkovModel(6);
        runModel(mEM, st, size, seed);

    }
    
    public void testHashMap(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        // String st = "yes-this-is-a-thin-pretty-pink-thistle";
        int size = 50;
        int seed = 615;

        IMarkovModel mEM = new EfficientMarkovModel(5);
        runModel(mEM, st, size, seed);
    }
    
    public void compareMethods(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int size = 1000;
        int seed = 42;
        
        System.out.println("MarkovModel begins at: " + System.nanoTime());
        
        IMarkovModel mM = new MarkovModel(2);
        runModel(mM, st, size, seed);
        
        System.out.println("MarkovModel ends at: " + System.nanoTime());
        System.out.println("EfficientMarkovModel begins at: " + System.nanoTime());
        
        IMarkovModel mEM = new EfficientMarkovModel(2);
        runModel(mEM, st, size, seed);        

        System.out.println("EfficientMarkovModel ends at: " + System.nanoTime());
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
