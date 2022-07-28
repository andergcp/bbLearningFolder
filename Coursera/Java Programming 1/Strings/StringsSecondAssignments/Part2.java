
/**
 * This assignment will write a method to determine how many occurrences
 * of a string appear in another string.
 * 
 * @andergcp (Anderson Castiblanco) 
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
        // This method returns an integer indicating how many times stringa
        // appears in stringb, where each occurrence of stringa must not 
        // overlap with another occurrence of it.
        int startIndex = 0;
        int counter = 0;
        while (true){
            startIndex = stringb.indexOf(stringa, startIndex);
            if (startIndex == -1){
                return counter;
            }
            startIndex = startIndex + stringa.length();
            counter++;
        }
    }
    
    public void testHowMany(){
        int result = howMany("GAA", "ATGAACGAATTGAATC");
        System.out.println("howMany(\"GAA\", \"ATGAACGAATTGAATC\") -> Result: " + result);
        result = howMany("AA", "ATAAAA");
        System.out.println("howMany(\"AA\", \"ATAAAA\") -> Result: " + result);        
        result = howMany("BB", "ATAAAA");
        System.out.println("howMany(\"BB\", \"ATAAAA\") -> Result: " + result);        
    }
}
