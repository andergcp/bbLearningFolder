
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile){
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    /**
     * Returns the number of raters that were read in and stored in the ArrayList of type
     * Rater.
     */
    public int getRaterSize(){
        return myRaters.size();
    }
    
    /**
     * This method returns a double representing the average movie rating for this ID if
     * there are at least minimalRaters ratings. If there are not minimalRaters ratings,
     * then it returns 0.0
     */
    public double getAverageByID(String id, int minimalRaters){
        double avg = -1;
        double ratingsById = 0.0;
        double sumOfRatingsById = 0.0;
        
        for(Rater r: myRaters){
            double rating = r.getRating(id);
            if(rating != -1){
                ratingsById+=1;
                sumOfRatingsById+=rating;
            }
        }
        if(ratingsById >= minimalRaters){
            avg = sumOfRatingsById/ratingsById;
        }
        return avg;
    }
    
    /**
     * This method finds the average rating for every movie that has been rated by at least
     * minimalRaters raters. Store each such rating in a Rating object in which the movie
     * ID and the average rating are used in creating the Rating object. This method returns
     * an ArrayList of all the Rating objects for movies that have at least the minimal
     * number of raters supplying a rating
     */
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        ArrayList<String> moviesIds = MovieDatabase.filterBy(new TrueFilter());
        for(String id: moviesIds){
            double avg = getAverageByID(id, minimalRaters);
            if(avg != -1){
                Rating r = new Rating(id, avg);
                avgRatings.add(r);
            }
        }
        return avgRatings;
    }
    
}