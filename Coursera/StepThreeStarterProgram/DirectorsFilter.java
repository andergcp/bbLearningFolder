
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class DirectorsFilter implements Filter{
    private String myDirectors;
    
    public DirectorsFilter(String directors){
        myDirectors = directors;
    }
    
    public boolean satisfies(String id){
        String[] directors = myDirectors.split(",");
        String movieDirectors = MovieDatabase.getDirector(id);
        for(String d: directors){
            if (movieDirectors.contains(d.trim())){
                return true;
            }
        }
        return false;
    }
}
