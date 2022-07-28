
/**
 * Tests CaesarCipherOOP Class
 * 
 * @andergcp (Anderson Castiblanco) 
 * @version (25 Jan 2021)
 */
import edu.duke.*;
import java.util.*;

public class TestCaesarCipher {
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
        int key = findKey(input);
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encrypt(input, 26 - key);
        return message;
    }
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        String file = fr.asString();
        file = file.toUpperCase();
        CaesarCipherOOP cc = new CaesarCipherOOP(18);
        System.out.println("Original String: " + file);

        String encrypted = cc.encrypt(file);
        System.out.println("Encrypted with the CaesarCipherOOP's method: " + encrypted);
        
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted with the CaesarCipherOOP's method: " + decrypted);
        
        decrypted = breakCaesarCipher(encrypted);
        System.out.println("Decrypted with breakCaesarCipher method: " + decrypted);
    }
}
