
/**
 * Program for Decrypting text encrypted with Caesar Chiper
 * 
 * @andergcp (Anderson Castiblanco) 
 * @version (21th Jan 2021)
 */
import edu.duke.*;

public class CaesarBreaker {
    public int[] countLetters(String encrypted){
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
    
    public int maxIndex(int[] counts){
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
    
    public int findKey(String encrypted){
        int key = -1;
        int[] counts = countLetters(encrypted);
        int maxIdx = maxIndex(counts);
        key = maxIdx - 4;
        if (maxIdx < 4){
            key = 26 - (4 - maxIdx);
        }
        return key;
    }
    
    public String decrypt(String encrypted){
        int key = findKey(encrypted);
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encrypt(encrypted, 26 - key);
        return message;
    }
    
    public void testDecrypt(){
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        String decrypted = decrypt(encrypted);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
    
    public String halfOfString(String message, int start){
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
    
    public String decryptTwoKeys(String encrypted){
        // Creating sub-string of every each other letter
        String first = halfOfString(encrypted, 0);
        String second = halfOfString(encrypted, 1);
        // Finds and prints keys for each substring
        int keyOne = findKey(first);
        int keyTwo = findKey(second);
        System.out.println("Key 1: " + keyOne);
        System.out.println("Key 2: " + keyTwo);
        // Decrypting messages
        CaesarCipher cc = new CaesarCipher();
        String decrypted = cc.encryptTwoKeys(encrypted, 26 - keyOne, 26 - keyTwo);
        return decrypted;
    }
    
    public void testDecryptTwoKeys(String encrypted){
        // FileResource fr = new FileResource();
        // String encrypted = fr.toString();
        String result = decryptTwoKeys(encrypted);
        System.out.println(result);
    }
}
