import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder answer = new StringBuilder();
        char[] messageArray = message.toCharArray();
        for (int i=whichSlice; i < message.length(); i+=totalSlices){
            answer.append(messageArray[i]);
        }
        return answer.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for(int i=0; i < klength; i++){
            String slice = sliceString(encrypted, i, klength);
            // System.out.print(i + " slice: " + slice);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            int thisKey = cc.getKey(slice);
            key[i] = thisKey;
        }
        return key;
    }

    public void breakVigenere () {
        String decrypted = "";
        System.out.println("Choose the file with the encrypted message");
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        System.out.println("Choose the files with the dictionaries");
        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        
        for(File f: dr.selectedFiles()){
            FileResource dict = new FileResource(f);
            HashSet<String> dictHash = readDictionary(dict);
            String fileName = f.getName();
            languages.put(fileName, dictHash);
        }
        
        // Used when I knew the key length and language:
        // int keyLength = 4;
        // int[] keys = tryKeyLength(file, keyLength, 'e');
        // VigenereCipher vc = new VigenereCipher(keys);
        // String decrypted = vc.decrypt(file);
        
        // Used when I knew the language:
        // String decrypted = breakForLanguage(file, dictHash);
        decrypted = breakForAllLangs(encrypted, languages);
        System.out.println("Decrypted message:\n" + decrypted);
    }
    
    /**
     * This method first makes a new HashSet of Strings, then read each line
     * in fr (which should contain exactly one word per line), convert that
     * line to lowercase, and put that line into the HashSet created.
     * The method then returns the HashSet representing the words in a
     * dictionary.
     */
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dictionary = new HashSet<String>();
        for (String line: fr.lines()){
            dictionary.add(line.toLowerCase());
        }
        return dictionary;
    }
    
    /**
     * This method splits the message into words, iterate over those words,
     * and see how many of them are “real words”—that is, how many appear
     * in the dictionary. Recall that the words in dictionary are lowercase.
     * This method return the integer count of how many valid words it found.
     */
    public int countWords(String message, HashSet<String> dictionary){
        int validWords = 0;
        String[] words = message.split("\\W+");
        for(String word: words){
            if(dictionary.contains(word.toLowerCase())){
                validWords++;
            }
        }
        return validWords;
    }
    
    /**
     * This method tries all key lengths from 1 to 100 (uses tryKeyLength
     * method to try one particular key length) to obtain the best decryption
     * for each key length in that range. For each key length (i), this method
     * decrypts the message (using VigenereCipher’s decrypt method), and counts
     * how many of the “words” in it are real words in English, based on the
     * dictionary passed in (using the countWords method). This method figures
     * out which decryption gives the largest count of real words, and returns
     * that String decryption. Note that there is nothing special about 100;
     * it will just receive messages with key lengths in the range 1–100.
     * If you did not have this information, it could iterate all the way to
     * encrypted.length(). The program would just take a bit longer to run.
     */
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        String decrypted = "";
        int maxValidWords = 0;
        int lengthUsed = 0;
        char commonChar = mostCommonCharIn(dictionary);
        int[] keyUsed;
        for(int i=1; i<=100; i++){
        // Use the next line if you don't know the max length of the key:
        // for(int i=1; i<=encrypted.length(); i++){
            int[] key = tryKeyLength(encrypted, i, commonChar);
            VigenereCipher vc = new VigenereCipher(key);
            String thisDecrypted = vc.decrypt(encrypted);
            int validWords = countWords(thisDecrypted, dictionary);
            if(validWords > maxValidWords){
                decrypted = thisDecrypted;
                maxValidWords = validWords;
                lengthUsed = i;
            }
        }
        // System.out.println("The key used is: ");
        // for(int k: keyUsed){
            // System.out.print(k + " ");
        // }
        // System.out.println("");
        System.out.println("Max valid words:" + maxValidWords);
        System.out.println("Length used: " + lengthUsed);
        return decrypted;
    }
    
    /**
     * This method finds out which character, of the letters in the English
     * alphabet, appears most often in the words in dictionary.
     * It returns this most commonly occurring character.
     */
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<String,Integer> letters = new HashMap<String,Integer>();
        int max = 0;
        String frequentLetter = "";
        // Goes through each word in the dictionary counting the letters
        for(String word: dictionary){
            for(char ch: word.toCharArray()){
                String letter = Character.toString(ch);
                if(letters.containsKey(letter)){
                    letters.put(letter, letters.get(letter) + 1);                    
                }
                else{
                    letters.put(letter, 1);                                        
                }
            }
        }
        //Finds the most frequent letter
        for(String letter: letters.keySet()){
            if(letters.get(letter) > max){
                max = letters.get(letter);
                frequentLetter = letter;
            }
        }
        
        return frequentLetter.charAt(0);
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages){
        String answer = "";
        int maxWords = 0;
        String languageUsed = "";
        for(String language: languages.keySet()){
            System.out.println("Language: " + language);
            String decrypted = breakForLanguage(encrypted, languages.get(language));
            int usedWords = countWords(decrypted, languages.get(language));
            if(usedWords > maxWords){
                maxWords = usedWords;
                answer = decrypted;
                languageUsed = language;
            }
        }
        System.out.println("The language used is: " + languageUsed);
        // System.out.println("The decrypted message is:\n" + answer);
        return answer;
    }
}
