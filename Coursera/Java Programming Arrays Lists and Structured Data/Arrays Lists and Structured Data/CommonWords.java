
/**
 * Practicing Arrays, Class for counting words from plays
 * 
 * @andergcp (Anderson Castiblanco) 
 * @version (21th Jan 2021)
 */
import edu.duke.*;
import java.util.Arrays;

public class CommonWords {
    public String[] getCommon(){
        FileResource fr = new FileResource("CommonWordsData/common.txt");
        String[] common = new String[20];
        int index = 0;
        for (String s: fr.words()){
            common[index] = s;
            index++;
        }
        return common;
    }
    
    public void countWords(FileResource resource, String[] common, int[] counts){
        for(String word: resource.words()){
            word = word.toLowerCase();
            int index = Arrays.asList(common).indexOf(word);
            if (index != -1){
                counts[index] += 1;
            }
        }
    }
    
    public void countShakespeare(){
        // String[] plays = {"caesar.txt","errors.txt", "hamlet.txt", "likeit.txt",
        // "macbeth.txt", "romeo.txt"};
        String[] plays = {"small.txt"};
        String[] common = getCommon();
        int[] counts = new int[common.length];
        for(int i=0; i < plays.length; i++){
            FileResource resource = new FileResource("CommonWordsData/" + plays[i]);
            countWords(resource, common, counts);
            System.out.println("Done with: " + plays[i]);
        }
        
        for (int i=0; i < common.length; i++){
            System.out.println(common[i] + ": " + counts[i]);
        }
    }
}
