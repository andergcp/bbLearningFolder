
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings(){
        // String moviesFile = "data/ratedmoviesfull.csv";
        // String ratingsFile = "data/ratings.csv";
        String moviesFile = "data/ratedmoviesfull.csv";
        String ratingsFile = "data/ratings.csv";
        
        SecondRatings sr = new SecondRatings(moviesFile, ratingsFile);
        System.out.println("Number of movies:" + sr.getMovieSize());
        System.out.println("Number of raters:" + sr.getRaterSize());
        
        ArrayList<Rating> avgRatings = sr.getAverageRatings(12);
        System.out.println("movies with 50 or more ratings" + avgRatings.size());
        Collections.sort(avgRatings);
        for(Rating r: avgRatings){
            System.out.println(r.getValue() + " " + sr.getTitle(r.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie(){
        // String moviesFile = "data/ratedmoviesfull.csv";
        // String ratingsFile = "data/ratings.csv";
        String moviesFile = "data/ratedmoviesfull.csv";
        String ratingsFile = "data/ratings.csv";
        
        SecondRatings sr = new SecondRatings(moviesFile, ratingsFile);
        String movieTitle = "Vacation";
        String movieId = sr.getID(movieTitle);
        System.out.println("MovieId: " + movieId);
        double avg = sr.getAverageByID(movieId, 0);
        
        System.out.println("The average rating of " + movieTitle + " movie is " + avg);
    }
}
