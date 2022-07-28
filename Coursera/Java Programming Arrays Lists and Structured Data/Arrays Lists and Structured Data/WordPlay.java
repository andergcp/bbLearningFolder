
/**
 * Program to transform words from a file into another form,
 * such as replacing vowels with an asterix.
 * 
 * @andergcp (Anderson Castiblanco) 
 * @version (14th January 2021)
 */
public class WordPlay {
    /**
     * This method returns true if ch is a vowel (one of 'a', 'e', 'i', 'o',
     * or 'u' or the uppercase versions) and false otherwise.
     */
    public boolean isVowel(char ch){
        String vowels = "aeiouAEIOU";
        if (vowels.indexOf(ch) != -1){
            return true;
        }
        return false;
    }
    
    /**
     * Tests isVowel() method
     */
    public void testIsVowel(){
        boolean result = isVowel('b');
        System.out.println(result);
        result = isVowel('a');
        System.out.println(result);
        result = isVowel('A');
        System.out.println(result);
        result = isVowel('-');
        System.out.println(result);        
    }
    
    /**
     * Return a String that is the string phrase with all the vowels
     * (uppercase or lowercase) replaced by ch.
     */
    public String replaceVowels(String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++){
            if (isVowel(sb.charAt(i))){
                sb.setCharAt(i, ch);
            }
        }
        return sb.toString();
    }
    
    /**
     * Tests replaceVowels() method
     */
    public void testReplaceVowels(){
        String result = replaceVowels("Hello World!", '*');
        System.out.println(result);
    }
    
    /**
     * Return a String that is the string phrase but with the
     * Char ch (upper- or lowercase) replaced by
     * ‘*’ if it is in an odd number location in the string
     * (e.g. the first character has an odd number location but an even
     * index, it is at index 0), or ‘+’ if it is in an even number location
     * in the string (e.g. the second character has an even number location
     * but an odd index, it is at index 1).
     */
    public String emphasize(String phrase, char ch){
        StringBuilder newString = new StringBuilder(phrase);
        char uch = Character.toUpperCase(ch);
        char lch = Character.toLowerCase(ch);
        for (int i = 0; i < phrase.length(); i++){
            if (newString.charAt(i) == lch || newString.charAt(i) == uch){
                if (i % 2 == 0){
                    newString.setCharAt(i, '*');
                } else {
                    newString.setCharAt(i, '+');
                }
            }
        }
        return newString.toString();
    }
    
    /**
     * Tests emphasize() method
     */
    public void testEmphasize(){
        String result = emphasize("Mary Bella Abracadabra", 'z');
        System.out.println(result);
    }
}
