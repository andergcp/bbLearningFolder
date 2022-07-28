
/**
 * Tests CaesarCipherTwo Class
 * 
 * @andergcp (Anderson Castiblanco) 
 * @version (25th Jan 2021)
 */
import edu.duke.*;

public class TestCaesarCipherTwo {
    private int[] countLetters(String encrypted){
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counters = new int[26];
        for (int i=0; i < encrypted.length(); i++){
            char ch = encrypted.charAt(i);
            int index = alpha.indexOf(Character.toLowerCase(ch));
            if (index != -1){
                counters[index] += 1;
            }
        }
        return counters;
    }
    
    private int maxIndex(int[] counts){
        int maxValue = 0;
        int maxIndex = 0;
        for (int i=0; i<counts.length; i++){
            if (counts[i] > maxValue){
                maxValue = counts[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    private String halfOfString(String message, int start){
        String newString = "";
        for (int i=0; i < message.length(); i++){
            if (start == 0){
                if (i % 2 == 0){
                    newString += message.charAt(i);
                }
            } else if (start == 1){
                if (i % 2 != 0){
                    newString += message.charAt(i);
                }
            }
        }
        return newString;
    }
    
    private int findKey(String encrypted){
        int key = -1;
        int[] counts = countLetters(encrypted);
        int maxIdx = maxIndex(counts);
        key = maxIdx - 4;
        if (maxIdx < 4){
            key = 26 - (4 - maxIdx);
        }
        return key;
    }
    
    public String breakCaesarCipher(String input){
        // Creating sub-string of every each other letter
        String first = halfOfString(input, 0);
        String second = halfOfString(input, 1);
        // Finds and prints keys for each substring
        int keyOne = findKey(first);
        int keyTwo = findKey(second);
        System.out.println("Key 1: " + keyOne);
        System.out.println("Key 2: " + keyTwo);
        // Decrypting messages
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - keyOne, 26 - keyTwo);
        String decrypted = cc.encrypt(input);
        return decrypted;
    }
    public void simpleTests(){
        FileResource fr = new FileResource();
        String file = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(14,24);
        System.out.println("Original file: " + file);
        
        String encrypted = cc.encrypt(file);
        System.out.println("Encrypted file with CaesarCipherTwo Class: " + encrypted);
        
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted file with CaesarCipherTwo Class: " + decrypted);
        
        decrypted = breakCaesarCipher(encrypted);
        System.out.println("Decrypted file with breakCaesarCipher method: " + decrypted);
    }
}
