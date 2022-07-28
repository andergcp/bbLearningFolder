
/**
 * Implements the CeasarCipher Class and methods, but implementing OOP
 * 
 * @andergcp (Anderson Castiblanco) 
 * @version (25 Jan 2021)
 */
public class CaesarCipherOOP {
    private String alphabet = "";
    private String shiftedAlphabet = "";
    private int mainKey = 0;
    
    /**
     * Class contructor
     */
    public CaesarCipherOOP(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }
    
    /**
     * This method returns a String that has been encrypted using the
     * Caesar Cipher algorithm. We can assume that all the alphabetic
     * characters are uppercase letters.
     */
    public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        
        for (int i = 0; i < input.length(); i++){
            int indexInAlp = alphabet.indexOf(input.charAt(i));
            if(indexInAlp != -1){
                sb.setCharAt(i, shiftedAlphabet.charAt(indexInAlp));
            }                
        }
        return sb.toString();
    }
    
    /**
     * his method returns a String that is the encrypted String decrypted using the key associated with
     * this CaesarCipher object
     */
    public String decrypt(String input){
        CaesarCipherOOP cc = new CaesarCipherOOP(26 - mainKey);
        return cc.encrypt(input);
    }
}
