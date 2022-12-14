
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MatchAllFilter implements Filter{
    private ArrayList<Filter> filters;
    
    public MatchAllFilter(){
        filters = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f){
        System.out.println(f);
        filters.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe){
        for(Filter f: filters){
            if(!f.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
    
    public String getName(){
        String name = "";
        for(Filter f: filters){
            name = name +  f.getName() + " ";
        }
        return name;
    }
}
