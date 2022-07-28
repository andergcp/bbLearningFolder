
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){
        // String moviesFile = "data/ratedmoviesfull.csv";
        // String ratingsFile = "data/ratings.csv";
        String moviesFile = "ratedmoviesfull.csv";
        String ratingsFile = "ratings.csv";
        
        FourthRatings fr = new FourthRatings();
        MovieDatabase.initialize(moviesFile);
        RaterDatabase.initialize(ratingsFile);
        System.out.println("Number of movies:" + MovieDatabase.size());
        System.out.println("Number of raters:" + RaterDatabase.size());
        
        int minimalRaters = 35;
        ArrayList<Rating> avgRatings = fr.getAverageRatings(minimalRaters);
        System.out.println("movies with "+ minimalRaters + " or more ratings: " + avgRatings.size());
        Collections.sort(avgRatings);
        for(Rating r: avgRatings){
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        // String moviesFile = "data/ratedmoviesfull.csv";
        // String ratingsFile = "data/ratings.csv";
        String moviesFile = "ratedmoviesfull.csv";
        String ratingsFile = "data/ratings.csv";
        //Initial steps
        FourthRatings fr = new FourthRatings();        
        MovieDatabase.initialize(moviesFile);
        RaterDatabase.initialize(ratingsFile);
        System.out.println("Number of movies:" + MovieDatabase.size());
        System.out.println("Number of raters:" + RaterDatabase.size());
        
        //AllFilters and filters to be added
        AllFilters allFilters = new AllFilters();
        String genreToFilter = "Drama";
        Filter genreFilter = new GenreFilter(genreToFilter);
        int yearToFilter = 1990;
        Filter yearFilter = new YearAfterFilter(yearToFilter);
        allFilters.addFilter(genreFilter);
        allFilters.addFilter(yearFilter);
        //Average
        int minimalRaters = 8;
        ArrayList<Rating> avgRatingsFiltered = getAverageRatingsByFilter(minimalRaters, allFilters, fr);
        System.out.println("movies with "+ minimalRaters + " minimal raters , the genre "
               + genreToFilter + " an at least this year "
               + yearToFilter + ": " + avgRatingsFiltered.size());
        Collections.sort(avgRatingsFiltered);
        for(Rating r: avgRatingsFiltered){
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
        }                                
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria, FourthRatings fr){
        ArrayList<String> moviesIds = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        for(String id: moviesIds){
            double avg = fr.getAverageByID(id, minimalRaters);
            if (avg != -1){
                Rating r = new Rating(id, avg);
                ratings.add(r);
            }
        }
        return ratings;
    }
    
    public void printSimilarRatings(){
        // String moviesFile = "data/ratedmoviesfull.csv";
        // String ratingsFile = "data/ratings.csv";
        String moviesFile = "ratedmoviesfull.csv";
        String ratingsFile = "ratings.csv";
        //Initial steps
        FourthRatings fr = new FourthRatings();        
        MovieDatabase.initialize(moviesFile);
        RaterDatabase.initialize(ratingsFile);
        System.out.println("Number of movies:" + MovieDatabase.size());
        System.out.println("Number of raters:" + RaterDatabase.size());
        
        String RaterId = "71";
        int numSimilarRaters = 20;
        int minimalSimilarRaters = 5;
        ArrayList<Rating> similarRatings = fr.getSimilarRatings(RaterId, numSimilarRaters,
                                            minimalSimilarRaters);
        System.out.println("Similar ratings are: \n" + similarRatings);
        for(Rating movieRating: similarRatings){
            String movieTitle = MovieDatabase.getTitle(movieRating.getItem());
            System.out.println(movieTitle);
        }

    }
    
    public void printSimilarRatingsByGenre(){
        // String moviesFile = "data/ratedmoviesfull.csv";
        // String ratingsFile = "data/ratings.csv";
        String moviesFile = "ratedmoviesfull.csv";
        String ratingsFile = "ratings.csv";
        //Initial steps
        FourthRatings fr = new FourthRatings();        
        MovieDatabase.initialize(moviesFile);
        RaterDatabase.initialize(ratingsFile);
        System.out.println("Number of movies:" + MovieDatabase.size());
        System.out.println("Number of raters:" + RaterDatabase.size());
        // Values
        String genreToFilter = "Mystery";
        String RaterId = "964";
        int numSimilarRaters = 20;
        int minimalSimilarRaters = 5;
        // Filter 
        Filter genreFilter = new GenreFilter(genreToFilter);
        
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(RaterId, numSimilarRaters,
                                            minimalSimilarRaters, genreFilter);
        for(Rating movieRating: similarRatings){
            String movieTitle = MovieDatabase.getTitle(movieRating.getItem());
            System.out.println(movieTitle + " " + movieRating.getValue());
            System.out.println(MovieDatabase.getGenres(movieRating.getItem()));
        }
    }
    
    public void printSimilarRatingsByDirector(){
        // String moviesFile = "data/ratedmoviesfull.csv";
        // String ratingsFile = "data/ratings.csv";
        String moviesFile = "ratedmoviesfull.csv";
        String ratingsFile = "ratings.csv";
        //Initial steps
        FourthRatings fr = new FourthRatings();        
        MovieDatabase.initialize(moviesFile);
        RaterDatabase.initialize(ratingsFile);
        System.out.println("Number of movies:" + MovieDatabase.size());
        System.out.println("Number of raters:" + RaterDatabase.size());
        // Values
        String directorsToFilter = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        String RaterId = "120";
        int numSimilarRaters = 10;
        int minimalSimilarRaters = 2;
        // Filter 
        Filter directorsFilter = new DirectorsFilter(directorsToFilter);
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(RaterId, numSimilarRaters,
                                            minimalSimilarRaters, directorsFilter); 
        for(Rating movieRating: similarRatings){
            String movieTitle = MovieDatabase.getTitle(movieRating.getItem());
            System.out.println(movieTitle + " " + movieRating.getValue());
            System.out.println(MovieDatabase.getDirector(movieRating.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        // String moviesFile = "data/ratedmoviesfull.csv";
        // String ratingsFile = "data/ratings.csv";
        String moviesFile = "ratedmoviesfull.csv";
        String ratingsFile = "ratings.csv";
        //Initial steps
        FourthRatings fr = new FourthRatings();        
        MovieDatabase.initialize(moviesFile);
        RaterDatabase.initialize(ratingsFile);
        System.out.println("Number of movies:" + MovieDatabase.size());
        System.out.println("Number of raters:" + RaterDatabase.size());
        // Values
        String genreToFilter = "Drama";
        int minMinutes = 80;
        int maxMinutes = 160;
        String RaterId = "168";
        int numSimilarRaters = 10;
        int minimalSimilarRaters = 3;
        // Filter 
        AllFilters allFilters = new AllFilters();
        Filter genreFilter = new GenreFilter(genreToFilter);
        Filter minutesFilter = new MinutesFilter(minMinutes, maxMinutes);
        allFilters.addFilter(genreFilter);
        allFilters.addFilter(minutesFilter);
        
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(RaterId, numSimilarRaters,
                                            minimalSimilarRaters, allFilters); 
        for(Rating movieRating: similarRatings){
            String movieTitle = MovieDatabase.getTitle(movieRating.getItem());
            int movieMinutes = MovieDatabase.getMinutes(movieRating.getItem());
            System.out.println(movieTitle + " " + movieMinutes + " " + movieRating.getValue());
            System.out.println("Genres: " + MovieDatabase.getGenres(movieRating.getItem()));
        }        
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        // String moviesFile = "data/ratedmoviesfull.csv";
        // String ratingsFile = "data/ratings.csv";
        String moviesFile = "ratedmoviesfull.csv";
        String ratingsFile = "ratings.csv";
        //Initial steps
        FourthRatings fr = new FourthRatings();        
        MovieDatabase.initialize(moviesFile);
        RaterDatabase.initialize(ratingsFile);
        System.out.println("Number of movies:" + MovieDatabase.size());
        System.out.println("Number of raters:" + RaterDatabase.size());
        // Values
        int yearAfter = 1975;
        int minMinutes = 70;
        int maxMinutes = 200;
        String RaterId = "314";
        int numSimilarRaters = 10;
        int minimalSimilarRaters = 5;
        // Filter 
        AllFilters allFilters = new AllFilters();
        Filter yearFilter = new YearAfterFilter(yearAfter);
        Filter minutesFilter = new MinutesFilter(minMinutes, maxMinutes);
        allFilters.addFilter(yearFilter);
        allFilters.addFilter(minutesFilter);
        
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(RaterId, numSimilarRaters,
                                            minimalSimilarRaters, allFilters); 
        for(Rating movieRating: similarRatings){
            String movieTitle = MovieDatabase.getTitle(movieRating.getItem());
            int movieMinutes = MovieDatabase.getMinutes(movieRating.getItem());
            int movieYear = MovieDatabase.getYear(movieRating.getItem());
            System.out.println(movieTitle + " " + movieYear + " " + movieMinutes + " " +
                                movieRating.getValue());
        }        
        
    }
}
