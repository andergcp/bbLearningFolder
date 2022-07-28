
/**
 * Implements CaesarCipher algorithm using two keys
 * 
 * @andergcp (Anderson Castiblanco) 
 * @version (25th Jan 2021)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int key1;
    private int key2;
    
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        key1 = key1;
        key2 = key2;
    }
    
    /**
     * This method returns a String that is the input encrypted using the two shifted alphabets.
     */
    public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        CaesarCipher cc = new CaesarCipher();
        for (int i = 0; i < input.length(); i++){
            //Converts the char in position i to String for passing it
            // as argument to encrypt() method
            String letter = Character.toString(input.charAt(i));
            //Decides which key to use for encrypting
            if (i % 2 == 0){
                letter = cc.encrypt(letter, key1);
            } else {
                letter = cc.encrypt(letter, key2);
            }
            //Set encrypted char in sb
            sb.setCharAt(i, letter.charAt(0));
        }
        return sb.toString();
    }
    
    public String decrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        CaesarCipher cc = new CaesarCipher();
        for (int i = 0; i < input.length(); i++){
            //Converts the char in position i to String for passing it
            // as argument to encrypt() method
            String letter = Character.toString(input.charAt(i));
            //Decides which key to use for encrypting
            if (i % 2 == 0){
                letter = cc.encrypt(letter, 26 - key1);
            } else {
                letter = cc.encrypt(letter, 26 - key2);
            }
            //Set encrypted char in sb
            sb.setCharAt(i, letter.charAt(0));
        }
        return sb.toString();
    }
}
