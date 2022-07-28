
/**
 * Class for Duke-Coursera Java Programming assignment:
 * 
 * Write a program that reads the lines from the file at this URL location,
 * https://www.dukelearntoprogram.com//course2/data/manylinks.html,
 * and prints each URL on the page that is a link to youtube.com.
 * Assume that a link to youtube.com has no spaces in it and would be in
 * the format (where [stuff] represents characters that are not verbatim):
 * “http:[stuff]youtube.com[stuff]”
 * 
 * @author (@andergcp)
 */
import edu.duke.*;

public class Part4 {
    public void printURL(String word){
        int firstQuotation = word.indexOf("\"");
        int lastQuotation = word.lastIndexOf("\"");
        String URL = word.substring(firstQuotation + 1, lastQuotation);
        System.out.println(URL);
    }
    public void findURLs(String url, String urlToFind){
        URLResource ur = new URLResource(url);
        int index = -1;
        String lowerCaseWord = "";
        for (String word : ur.words()){
            lowerCaseWord = word.toLowerCase();
            index = lowerCaseWord.indexOf(urlToFind);
            if (index >= 0){
                printURL(word);
            }
        }
        
     }
    
    public void testing(){
        findURLs("https://www.dukelearntoprogram.com//course2/data/manylinks.html", "youtube.com");
    }
}
