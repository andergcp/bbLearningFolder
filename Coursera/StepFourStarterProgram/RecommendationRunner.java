
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class RecommendationRunner implements Recommender{
    /**
     * This method returns a list of movie IDs that will be used to look up 
     * the movies in the MovieDatabase and present them to users to rate. 
     *  
     * The movies returned in the list will be displayed on a web page, so
     * the number you choose may affect how long the page takes to load and
     * how willing users are to rate the movies.  For example, 10-20 should
     * be fine, 50 or more would be too many.
     * 
     * There are no restrictions on the method you use to generate this list
     * of movies: the most recent movies, movies from a specific genre, 
     * randomly chosen movies, or simply your favorite movies.
     * 
     * The ratings for these movies will make the profile for a new Rater 
     * that will be used to compare to for finding recommendations.
     */
    public ArrayList<String> getItemsToRate (){        
        String genreToFilter = "Comedy";
        Filter trueFilter = new TrueFilter();        
        ArrayList<String> movies = MovieDatabase.filterBy(trueFilter);
        ArrayList<String> moviesToReturn = new ArrayList<String>();
        int numMovies = 10;
        int counter = 0;
        while(counter < numMovies && counter < movies.size()){
            moviesToReturn.add(movies.get(counter));
            counter ++;
        }
        return moviesToReturn;
    };

    /**
     * This method returns nothing, but prints out an HTML table of the 
     * movies recommended for the given rater.
     * 
     * The HTML printed will be displayed on a web page, so the number you
     * choose to display may affect how long the page takes to load.  For 
     * example, you may want to limit the number printed to only the top 
     * 20-50 movies recommended or to movies not rater by the given rater.
     * 
     * You may also include CSS styling for your table using the &lt;style&gt;
     * tag before you print the table.  There are no restrictions on which 
     * movies you print, what order you print them in, or what information
     * you include about each movie. 
     * 
     * @param webRaterID the ID of a new Rater that has been already added to 
     *        the RaterDatabase with ratings for the movies returned by the 
     *        method getItemsToRate
     */
    public void printRecommendationsFor (String webRaterID){
        FourthRatings fr = new FourthRatings();
        Filter trueFilter = new TrueFilter();        
        int numSimilarRaters = 20;
        int minimalRaters = 4;        
        ArrayList<Rating> recommendedMovies = fr.getSimilarRatingsByFilter(webRaterID,
                                            numSimilarRaters, minimalRaters, trueFilter);
        String tableRows = "";
        System.out.println("<h1>Recommendations by andergcp<h1>");
        System.out.println("<div><table>");
        
        for(Rating movie: recommendedMovies){
            String movieTitle = MovieDatabase.getTitle(movie.getItem());
            int movieYear = MovieDatabase.getYear(movie.getItem());
            String movieGenres = MovieDatabase.getGenres(movie.getItem());
            System.out.println("<tr><td>"+ movieTitle +"</td><td>" + movieYear + "</td><td>"
                        + movieGenres + "</td></tr>");
        }
        System.out.println("</table><div>");
        System.out.println("Recommended movies: " + recommendedMovies.size());
    }
}
