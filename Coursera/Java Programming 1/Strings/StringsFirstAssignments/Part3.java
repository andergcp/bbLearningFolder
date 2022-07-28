
/**
 * Class for Duke-Coursera Java Programming assignment
 * 
 * @author (@andergcp)
 */
public class Part3 {
    // Returns true if stringa appears at least
    // twice in stringb, otherwise it returns false.
    public boolean twoOccurrences(String stringa, String stringb) {
        int index = 0;
        int counter = 0;
        int occurrence = 0;
        int bLength = stringb.length();
        int aLength = stringa.length();
        while (index < bLength) {
            occurrence = stringb.indexOf(stringa, index);
            if (occurrence == -1) {
                if (counter < 2) {
                    return false;
                } else {
                    return true;
                }
            }
            index = occurrence + aLength;
            counter += 1;
        }
        if (counter < 2) {
            return false;
        } else {
            return true;
        }
    }
    
    public String lastPart(String stringa, String stringb) {
        // finds the first occurrence of stringa in stringb,
        // and returns the part of stringb that follows stringa.
        // If stringa does not occur in stringb, then return stringb.
        int occurrence = stringb.indexOf(stringa);
        int aLength = stringa.length();
        if (occurrence == -1) {
            return stringb;
        } else {
            return stringb.substring(aLength + occurrence);
        }
    }
    
    public void testing() {
        // Tests basic cases for twoOccurrences() and lastPart() methods
        System.out.println("twoOccurrences(“by”, “A story by Abby Long”) " + twoOccurrences("by", "A story by Abby Long"));
        System.out.println("twoOccurrences(“a”, “banana”)" + twoOccurrences("a", "banana"));
        System.out.println("twoOccurrences(“atg”, “ctgtatgta”)" + twoOccurrences("atg", "ctgtatgta"));
        System.out.println("lastPart(“an”, “banana”)" + lastPart("an", "banana"));
        System.out.println("lastPart(“am”, “banana”)" + lastPart("am", "banana"));
        System.out.println("lastPart(“banana”, “banana”)" + lastPart("banana", "banana"));
    }
}
