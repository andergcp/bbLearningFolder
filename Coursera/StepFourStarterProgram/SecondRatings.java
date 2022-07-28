
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    /**
     * Returns the number of movies that were read in and stored in the ArrayList of type
     * Movie.
     */
    public int getMovieSize(){
        return myMovies.size();
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
        double avg = 0.0;
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
        
        for(Movie m: myMovies){
            double avg = getAverageByID(m.getID(), minimalRaters);
            if(avg != 0){
                Rating r = new Rating(m.getID(), avg);
                avgRatings.add(r);
            }
        }
        return avgRatings;
    }
    
    /**
     * This method returns the title of the movie with that ID. If the movie ID does not
     * exist, then this method returns a String indicating the ID was not found.
     */
    public String getTitle(String id){
        for(Movie m: myMovies){
            if(m.getID().equals(id)){
                return m.getTitle();
            }
        }
        return "Movie Id was not found";
    }
    
    public String getID(String title){
        for(Movie m: myMovies){
            if(m.getTitle().equals(title)){
                return m.getID();
            }
        }
        return "This title is not in our list";
    }
}