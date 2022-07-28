
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String where;
    private String phrase;
    private String name;
    
    public PhraseFilter(String w, String ph, String filterName){
        where = w;
        phrase = ph;
        name = filterName;
    }
    
    public boolean satisfies(QuakeEntry qe) { 
        String title = qe.getInfo();
        int index = title.indexOf(phrase);
        if((where.equals("start") && index == 0) ||
           (where.equals("end") && title.endsWith(phrase)) ||
           (where.equals("any") && index != -1)){
            return true;
        }
        return false;
    }
    
        public String getName(){
        return name;
    }
}
