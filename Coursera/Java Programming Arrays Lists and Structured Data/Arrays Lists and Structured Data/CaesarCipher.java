
/**
 * Implement CaesarCipher algorithim
 * 
 * @andergcp (Anderson Castiblanco) 
 * @version (14th January 2021)
 */
import edu.duke.*;

public class CaesarCipher {
    /**
     * This method returns a String that has been encrypted using the
     * Caesar Cipher algorithm. We can assume that all the alphabetic
     * characters are uppercase letters.
     */
    public String encrypt(String input, int key){
        String ualp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String uSwiftAlp = ualp.substring(key) + ualp.substring(0, key);
        String lalp = "abcdefghijklmnopqrstuvwxyz";
        String lSwiftAlp = lalp.substring(key) + lalp.substring(0, key);
        StringBuilder sb = new StringBuilder(input);
        
        for (int i = 0; i < input.length(); i++){
            if (Character.isUpperCase(input.charAt(i))){
                int indexInAlp = ualp.indexOf(input.charAt(i));
                if(indexInAlp != -1){
                    sb.setCharAt(i, uSwiftAlp.charAt(indexInAlp));
                }                
            }
            if (Character.isLowerCase(input.charAt(i))){
                int indexInAlp = lalp.indexOf(input.charAt(i));
                if(indexInAlp != -1){
                    sb.setCharAt(i, lSwiftAlp.charAt(indexInAlp));
                }
            }
        }
        return sb.toString();
    }
    
    /**
     * Tests encrypt() method
     */
    public void testEncrypt(){
        String result = encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", 15);
        System.out.println(result);
        // int key = 15;
        // FileResource fr = new FileResource();
        // String message = fr.asString();
        // String encrypted = encrypt(message, key);
        // System.out.println("key is " + key + "\n" + encrypted);
    }
    
    /**
     * This method returns a String that has been encrypted using the
     * following algorithm. Parameter key1 is used to encrypt every other
     * character with the Caesar Cipher algorithm, starting with the first
     * character, and key2 is used to encrypt every other character,
     * starting with the second character.
     */
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++){
            //Converts the char in position i to String for passing it
            // as argument to encrypt() method
            String letter = Character.toString(input.charAt(i));
            //Decides which key to use for encrypting
            if (i % 2 == 0){
                letter = encrypt(letter, key1);
            } else {
                letter = encrypt(letter, key2);
            }
            //Set encrypted char in sb
            sb.setCharAt(i, letter.charAt(0));
        }
        return sb.toString();
    }
    
    /**
     * Tests encryptTwoKeys
     */
    public void testEncryptTwoKeys(){
        String result = encryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.", 12,2);
        System.out.println(result);
    }
}
