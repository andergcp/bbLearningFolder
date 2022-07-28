
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class FourthRatings {
    /**
     * This method returns a double representing the average movie rating for this ID if
     * there are at least minimalRaters ratings. If there are not minimalRaters ratings,
     * then it returns 0.0
     */
    public double getAverageByID(String id, int minimalRaters){
        double avg = -1;
        double ratingsById = 0.0;
        double sumOfRatingsById = 0.0;
        
        for(Rater r: RaterDatabase.getRaters()){
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
    
    /**
     * This method first translates a rating from the scale 0 to 10 to
     * the scale -5 to 5 and return the dot product of the ratings of movies
     * that they both rated.
     * If they dond't have common movies then returns -10
     */
    private double dotProduct (Rater me, Rater r){
        // System.out.println("It's me-->" + me.getID());
        // System.out.println("It's r the other-->" + r.getID());        
        ArrayList<String> moviesRatedByMe = me.getItemsRated();
        double finalDotProduct = 0;
        boolean haveCommonMovies = false;
        for (String movieId: moviesRatedByMe){
            if (r.hasRating(movieId)){
                double similarity = (me.getRating(movieId) - 5)
                                    * (r.getRating(movieId) - 5);
                finalDotProduct += similarity;
                haveCommonMovies = true;
            }
        }
        if(haveCommonMovies){
            return finalDotProduct;
        } else {
            return -1000000000.0;
        }
    }
        
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for (Rater r: RaterDatabase.getRaters()){
            if(r != me){
                double dp = dotProduct(me, r);
                if(dp >= 0){
                    Rating raterDotProduct = new Rating(r.getID(), dp);
                    list.add(raterDotProduct);
                }
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
    
    /**
     * This method returns an ArrayList of type Rating, of movies and
     * their weighted average ratings using only the top numSimilarRaters
     * with positive ratings and including only those movies that have at
     * least minimalRaters ratings from those most similar raters (not just
     * minimalRaters ratings overall)
     */
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> similarRatings = new ArrayList<Rating>();
        ArrayList<Rating> similarRaters = getSimilarities(id);
        ArrayList<String> moviesIds = MovieDatabase.filterBy(new TrueFilter());        
        
        for(String movieId : moviesIds){
            int topRaterNum = 0;
            double sumRatings = 0;
            double numSimilarRatersInThisMovie = 0;

            while(topRaterNum < numSimilarRaters
                    && topRaterNum < similarRaters.size()){
                Rating similarRater = similarRaters.get(topRaterNum);
                String similarRaterId = similarRater.getItem();
                Rater similarRaterObj = RaterDatabase.getRater(similarRaterId);

                if(similarRaterObj.hasRating(movieId)){
                    double movieRating = similarRaterObj.getRating(movieId);
                    double dotProduct = similarRater.getValue();
                    double weightedRating = movieRating * dotProduct;
                    sumRatings += weightedRating;
                    numSimilarRatersInThisMovie += 1;
                }
                topRaterNum += 1;
            }
            
            if(numSimilarRatersInThisMovie >= minimalRaters){
                double avgWeighted = sumRatings / numSimilarRatersInThisMovie;
                Rating avgWeightedRating = new Rating(movieId, avgWeighted);
                similarRatings.add(avgWeightedRating);
            }            
        }
        Collections.sort(similarRatings, Collections.reverseOrder());    
        System.out.println("------> Similar ratings" + similarRatings);
        return similarRatings;
    }
    
    /**
     * Similar to the getSimilarRatings method but has one additional Filter parameter
     * named filterCriteria and uses that filter to access and rate only those movies
     * that match the filter criteria. 
     */
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters,
                            int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> similarRatings = new ArrayList<Rating>();
        ArrayList<Rating> similarRaters = getSimilarities(id);
        ArrayList<String> moviesIds = MovieDatabase.filterBy(filterCriteria);        
        for(String movieId : moviesIds){
            int topRaterNum = 0;
            double sumRatings = 0;
            double numSimilarRatersInThisMovie = 0;

            while(topRaterNum < numSimilarRaters
                    && topRaterNum < similarRaters.size()){
                Rating similarRater = similarRaters.get(topRaterNum);
                String similarRaterId = similarRater.getItem();
                Rater similarRaterObj = RaterDatabase.getRater(similarRaterId);

                if(similarRaterObj.hasRating(movieId)){
                    double movieRating = similarRaterObj.getRating(movieId);
                    double dotProduct = similarRater.getValue();
                    double weightedRating = movieRating * dotProduct;
                    sumRatings += weightedRating;
                    numSimilarRatersInThisMovie += 1;
                }

                topRaterNum += 1;
            }
            
            if(numSimilarRatersInThisMovie >= minimalRaters){
                double avgWeighted = sumRatings / numSimilarRatersInThisMovie;
                Rating avgWeightedRating = new Rating(movieId, avgWeighted);
                similarRatings.add(avgWeightedRating);
            }
        }
        
        Collections.sort(similarRatings, Collections.reverseOrder());    
        return similarRatings;
    }
}