
/**
 * Practicing classes code - Breaking theCaesar Chiper
 * 
 * @andergcp (Anderson Castiblanco) 
 * @version (22 Jan 2021)
 */
public class BreakingCaesarC {
    public void textFingerPrint(String s){
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counters = new int[26];
        for (int i=0; i < s.length(); i++){
            char ch = s.charAt(i);
            int index = alpha.indexOf(Character.toLowerCase(ch));
            if (index != -1){
                counters[index] += 1;
            }
        }
        for (int i=0; i<counters.length; i++){
            System.out.println(alpha.charAt(i)+"\t"+counters[i]);
        }
    }
    public void testTextFingerPrint(){
        textFingerPrint("Hola que hace");
    }
}
