
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    //This hashmap maps the name of the director to the count of movies has been part of.
    private HashMap<String, Integer> directors = new HashMap<String, Integer>();
    //This hashmap maps the movie Id with its rating:
    private ArrayList<String> moviesRated = new ArrayList<String>();
    
    /**
     * This method processes every record from the CSV file whose name is
     * filename, a file of movie information, and return an ArrayList of type
     * Movie with all of the movie data from the file.
     */
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord r: parser){
            Movie m = new Movie(r.get(0), r.get(1), r.get(2), r.get(4), r.get(5), r.get(3),
                                r.get(7), Integer.parseInt(r.get(6)));
            movies.add(m);
        }
        return movies;
    }
    
    /**
     * This method does several things: 
     */
    public void testLoadMovies(){
        // String filename = "data/ratedmovies_short.csv";
        String filename = "data/ratedmoviesfull.csv";
        ArrayList<Movie> movies = loadMovies(filename);
        int genreCount = 0; //Movies that include X genre
        int longerThan = 0; //Movies that are greater than X minutes in lentgh
        
        //Prints the number of movies and the movies:
        System.out.println("There are " + movies.size() + " movies");
        
        for(Movie m: movies){
            // System.out.println(m);
            String genres = m.getGenres().toLowerCase();
            int duration = m.getMinutes();
            if(genres.indexOf("comedy") != -1) genreCount+=1;
            if(duration > 150) longerThan+=1;
            addDirectors(m);
        }
        System.out.println(genreCount + " movie(s) include(s) the Comedy genre");
        System.out.println(longerThan + " movie(s) are longer than 150 minutes");
        maxMoviesOfDirectors();
    }
    
    /**
     * This method adds directors from each movie to the HashMap directors
     */
    public void addDirectors(Movie m){
        String dir = m.getDirector();
        String[] dirArray = dir.split(",");
        
        for(String d: dirArray){
            int times = 1;
            if(directors.containsKey(d)){
                times = directors.get(d);
                times+=1;
            }
            directors.put(d, times);
        }
    }
    
    /**
     * This method takes the HashMap directors and prints the  maximum number of movies by
     * any director, and who the directors are that directed that many movies.
     */
    public void maxMoviesOfDirectors(){
        int max = 0;
        ArrayList<String> dirs = new ArrayList<String>();
        
        for(String d: directors.keySet()){
            int countMovies = directors.get(d);
            if(countMovies > max) max=countMovies;
        }
        for(String d: directors.keySet()){
            int countMovies = directors.get(d);
            if(countMovies == max) dirs.add(d);
        }
        System.out.println("The maximum number of movies by any director is: " + max);
        System.out.println("The director(s) with that amount of movies is(are):");
        System.out.println(dirs);
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater> raters = new ArrayList<Rater>();
        ArrayList<String> ratersId = new ArrayList<String>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        
        //Creates new rater objects
        for(CSVRecord r: parser){
            String id = r.get(0);
            if(!ratersId.contains(id)) {
                ratersId.add(id);
                Rater rater = new Rater(id);
                raters.add(rater);
            }
        }
        // System.out.println("ratersId " + ratersId);
        // for(Rater r: raters){
            // System.out.println("rater " + r + " ID " + r.getID());
        // }
        //Adds ratings to raters:

        for(Rater rater: raters){
            // System.out.println("for raters, rater->" + rater + "-" + rater.getID());
            parser = fr.getCSVParser();
            for(CSVRecord r: parser){
                // System.out.println("For parser->" + r);
                if(rater.getID().equals(r.get(0))){
                    // System.out.println("Adding rating");
                    rater.addRating(r.get(1), Double.parseDouble(r.get(2)));
                    moviesRated.add(r.get(1));
                }
            }
        }

        return raters;
    }
    
    public void testLoadRaters(){
        ArrayList<Rater> raters = loadRaters("data/ratings.csv");
        // ArrayList<Rater> raters = loadRaters("data/ratings_short.csv");
        int numRatings = 0;
        int maxRatings = 0;
        String rater_id = "193";
        String movie = "1798709";
        int ratingsPerMovie = 0;
        
        System.out.println("Number of raters: " + raters.size());
        System.out.println("---------------------------------------");
        // for(Rater r: raters){
            // System.out.println("Rater ID: " + r.getID() + " Ratings done: " + r.numRatings());
            // ArrayList<String> itemsRated = r.getItemsRated();
            // for(String item: itemsRated){
                // System.out.println("Item: " + item + " rating: " + r.getRating(item));
            // }
            // System.out.println("---------------------------------------");
        // }
        for(Rater r: raters){
            if(r.getID().equals(rater_id)) numRatings = r.numRatings();
            if(r.numRatings() > maxRatings) maxRatings = r.numRatings();
            
        }
        System.out.println("Rater Id " + rater_id + " has " + numRatings + " ratings.");
        
        System.out.println("Maximum number of ratings:" + maxRatings);       
        for(Rater r: raters){
            if(r.numRatings() == maxRatings){
                System.out.println(r.getID());
            }
        }
        
        //Ratings per movie:
        for(String movieId: moviesRated){
            if(movieId.equals(movie)){
                ratingsPerMovie+=1;
            }
        }
        System.out.println("The movie with id " + movie + " has " + ratingsPerMovie + " ratings");
        
        //determine how many different movies have been rated by all these raters. 
        ArrayList<String> uniqueMovies = new ArrayList<String>();
        for(String m: moviesRated){
            if(!uniqueMovies.contains(m)) uniqueMovies.add(m);
        }
        System.out.println("Number of movies rated:" + uniqueMovies.size());
    }
}
