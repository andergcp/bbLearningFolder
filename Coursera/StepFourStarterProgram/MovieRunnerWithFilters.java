
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        // String moviesFile = "data/ratedmoviesfull.csv";
        // String ratingsFile = "data/ratings.csv";
        String moviesFile = "ratedmoviesfull.csv";
        String ratingsFile = "data/ratings.csv";
        
        ThirdRatings tr = new ThirdRatings(ratingsFile);
        MovieDatabase.initialize(moviesFile);
        System.out.println("Number of movies:" + MovieDatabase.size());
        System.out.println("Number of raters:" + tr.getRaterSize());
        
        int minimalRaters = 35;
        ArrayList<Rating> avgRatings = tr.getAverageRatings(minimalRaters);
        System.out.println("movies with "+ minimalRaters + " or more ratings: " + avgRatings.size());
        Collections.sort(avgRatings);
        for(Rating r: avgRatings){
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYear(){
        // String moviesFile = "data/ratedmoviesfull.csv";
        // String ratingsFile = "data/ratings.csv";
        String moviesFile = "ratedmoviesfull.csv";
        String ratingsFile = "data/ratings.csv";
        
        ThirdRatings tr = new ThirdRatings(ratingsFile);
        MovieDatabase.initialize(moviesFile);
        System.out.println("Number of movies:" + MovieDatabase.size());
        System.out.println("Number of raters:" + tr.getRaterSize());
        int yearToFilter = 2000;
        Filter filter = new YearAfterFilter(yearToFilter);
        int minimalRaters = 20;
        ArrayList<Rating> avgRatingsFiltered = getAverageRatingsByFilter(minimalRaters, filter, tr);
               System.out.println("movies with "+ minimalRaters + " or more ratings and year the same or after "
               + yearToFilter + ": " + avgRatingsFiltered.size());
        Collections.sort(avgRatingsFiltered);
        for(Rating r: avgRatingsFiltered){
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem())
            + " " + MovieDatabase.getTitle(r.getItem()));
        } 
    }
    
    public void printAverageRatingsByGenre(){
        // String moviesFile = "data/ratedmoviesfull.csv";
        // String ratingsFile = "data/ratings.csv";
        String moviesFile = "ratedmoviesfull.csv";
        String ratingsFile = "data/ratings.csv";
        
        ThirdRatings tr = new ThirdRatings(ratingsFile);
        MovieDatabase.initialize(moviesFile);
        System.out.println("Number of movies:" + MovieDatabase.size());
        System.out.println("Number of raters:" + tr.getRaterSize());
        String genreToFilter = "Comedy";
        Filter filter = new GenreFilter(genreToFilter);
        int minimalRaters = 20;
        ArrayList<Rating> avgRatingsFiltered = getAverageRatingsByFilter(minimalRaters, filter, tr);
               System.out.println("movies with "+ minimalRaters + " or more ratings and has the "
               + genreToFilter + "genre: " + avgRatingsFiltered.size());
        Collections.sort(avgRatingsFiltered);
        for(Rating r: avgRatingsFiltered){
            System.out.println(r.getValue() + " "+ MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
        }        
    }
    
    public void printAverageRatingsByMinutes(){
        // String moviesFile = "data/ratedmoviesfull.csv";
        // String ratingsFile = "data/ratings.csv";
        String moviesFile = "ratedmoviesfull.csv";
        String ratingsFile = "data/ratings.csv";
        
        ThirdRatings tr = new ThirdRatings(ratingsFile);
        MovieDatabase.initialize(moviesFile);
        System.out.println("Number of movies:" + MovieDatabase.size());
        System.out.println("Number of raters:" + tr.getRaterSize());
        int minMinutes = 105;
        int maxMinutes = 135;
        Filter filter = new MinutesFilter(minMinutes, maxMinutes);
        int minimalRaters = 5;
        ArrayList<Rating> avgRatingsFiltered = getAverageRatingsByFilter(minimalRaters, filter, tr);
        System.out.println("movies with "+ minimalRaters + " and minute between "
               + minMinutes + " and " + maxMinutes + ": " + avgRatingsFiltered.size());
        Collections.sort(avgRatingsFiltered);
        for(Rating r: avgRatingsFiltered){
            System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(r.getItem()) 
                + " " + MovieDatabase.getTitle(r.getItem()));
        }        
    }
    
    public void printAverageRatingsByDirectors(){
        // String moviesFile = "data/ratedmoviesfull.csv";
        // String ratingsFile = "data/ratings.csv";
        String moviesFile = "ratedmoviesfull.csv";
        String ratingsFile = "data/ratings.csv";
        
        ThirdRatings tr = new ThirdRatings(ratingsFile);
        MovieDatabase.initialize(moviesFile);
        System.out.println("Number of movies:" + MovieDatabase.size());
        System.out.println("Number of raters:" + tr.getRaterSize());
        String directorsToFilter = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        Filter filter = new DirectorsFilter(directorsToFilter);
        int minimalRaters = 4;
        ArrayList<Rating> avgRatingsFiltered = getAverageRatingsByFilter(minimalRaters, filter, tr);
        System.out.println("movies with "+ minimalRaters + " and at least one of this directors "
               + directorsToFilter + ": " + avgRatingsFiltered.size());
        Collections.sort(avgRatingsFiltered);
        for(Rating r: avgRatingsFiltered){
            System.out.println(r.getValue() + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(r.getItem()));
        }                
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        // String moviesFile = "data/ratedmoviesfull.csv";
        // String ratingsFile = "data/ratings.csv";
        String moviesFile = "ratedmoviesfull.csv";
        String ratingsFile = "data/ratings.csv";
        //Initial steps
        ThirdRatings tr = new ThirdRatings(ratingsFile);
        MovieDatabase.initialize(moviesFile);
        System.out.println("Number of movies:" + MovieDatabase.size());
        System.out.println("Number of raters:" + tr.getRaterSize());
        
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
        ArrayList<Rating> avgRatingsFiltered = getAverageRatingsByFilter(minimalRaters, allFilters, tr);
        System.out.println("movies with "+ minimalRaters + " minimal raters , the genre "
               + genreToFilter + " an at least this year "
               + yearToFilter + ": " + avgRatingsFiltered.size());
        Collections.sort(avgRatingsFiltered);
        for(Rating r: avgRatingsFiltered){
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
        }                                
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        // String moviesFile = "data/ratedmoviesfull.csv";
        // String ratingsFile = "data/ratings.csv";
        String moviesFile = "ratedmoviesfull.csv";
        String ratingsFile = "data/ratings.csv";
        //Initial steps
        ThirdRatings tr = new ThirdRatings(ratingsFile);
        MovieDatabase.initialize(moviesFile);
        System.out.println("Number of movies:" + MovieDatabase.size());
        System.out.println("Number of raters:" + tr.getRaterSize());
        
        //AllFilters and filters to be added
        AllFilters allFilters = new AllFilters();
        int minMinutes = 90;
        int maxMinutes = 180;
        Filter minutesFilter = new MinutesFilter(minMinutes, maxMinutes);
        String directorsToFilter = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        Filter directorsFilter = new DirectorsFilter(directorsToFilter);
        allFilters.addFilter(minutesFilter);
        allFilters.addFilter(directorsFilter);
        //Average
        int minimalRaters = 3;
        ArrayList<Rating> avgRatingsFiltered = getAverageRatingsByFilter(minimalRaters, allFilters, tr);
        System.out.println("movies with "+ minimalRaters + " , at least one of this directors "
               + directorsToFilter + " and minutes between "
               + minMinutes + " and " + maxMinutes + ": " + avgRatingsFiltered.size());
        Collections.sort(avgRatingsFiltered);
        for(Rating r: avgRatingsFiltered){
            System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(r.getItem()));
        }                        
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria, ThirdRatings tr){
        ArrayList<String> moviesIds = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        for(String id: moviesIds){
            double avg = tr.getAverageByID(id, minimalRaters);
            if (avg != -1){
                Rating r = new Rating(id, avg);
                ratings.add(r);
            }
        }
        return ratings;
    }
}
