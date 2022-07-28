import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * This method returns an ArrayList of type QuakeEntry of all the
     * earthquakes from quakeData that have a magnitude larger than magMin.
     */
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe: quakeData){
            if(qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }
        return answer;
    }

    /**
     * This method returns an ArrayList of type QuakeEntry of all the
     * earthquakes from quakeData that are less than distMax from the
     * location from.
     * distMax is in kilometers, and distanceTo returns a value in metters.
     */
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe: quakeData){
            double distanceFrom = from.distanceTo(qe.getLocation())/1000;
            if(distanceFrom < distMax){
                answer.add(qe);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    /**
     *  Prints earthquakes above a certain magnitude, and also print the
     *  number of such earthquakes.
     */
    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        double magMin = 5;
        ArrayList<QuakeEntry> filtered = filterByMagnitude(list, magMin);
        System.out.println(filtered.size() + 
        " quakes with magnitude greater than " + magMin + ":");
        for(QuakeEntry qe: filtered){
            System.out.println(qe);
        }
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        // Location city = new Location(35.988, -78.907);
        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        double distMax = 1000;
        ArrayList<QuakeEntry> filtered = filterByDistanceFrom(list, distMax, city);
        for(QuakeEntry qe: filtered){
            System.out.println(city.distanceTo(qe.getLocation())/1000
                                + " " + qe.getInfo());
        }
        System.out.println(filtered.size() + " quakes with a distance shorter than "
        + distMax + " kilometers.");
        // TODO
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    /**
     * This method returns an ArrayList of type QuakeEntry of all the
     * earthquakes from quakeData whose depth is between minDepth and
     * maxDepth, exclusive.
     */
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
                                double minDepth, double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for(QuakeEntry qe: quakeData){
            if(qe.getDepth() < maxDepth && qe.getDepth() > minDepth){
                answer.add(qe);
            }
        }
        return answer;
    }
    
    /**
     * this method prints all the earthquakes from a data source whose depth
     * is between a given minimum and maximum value. It also prints out the 
     * number of earthquakes found.
     */
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        double minDepth = -4000.0;
        double maxDepth = -2000.0;
        ArrayList<QuakeEntry> filtered = filterByDepth(list, minDepth, maxDepth);
        System.out.println("Find quakes with depth between " + minDepth +
                            " and " + maxDepth);
        for(QuakeEntry qe: filtered){
            System.out.println(qe);
        }
        System.out.println("Found " + filtered.size() +
                            " quakes that match that criteria");
    }
    
    /**
     * filterByPhrase
     */
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
                                String where, String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe: quakeData){
            String title = qe.getInfo();
            int index = title.indexOf(phrase);
            if(where.equals("start") && index == 0){
                answer.add(qe);
            }
            if(where.equals("end") &&
            title.endsWith(phrase)){
                answer.add(qe);                
            }
            if(where.equals("any") && index != -1){
                answer.add(qe);                                
            }
        }
        return answer;
    }
    
    /**
     * This method prints all the earthquakes from a data source that have
     * phrase in their title in a given position in the title.
     * It also prints out the number of earthquakes found.
     */
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        String where = "any";
        ArrayList<QuakeEntry> filtered = filterByPhrase(list, where, "Can");
        for(QuakeEntry qe: filtered){
            System.out.println(qe);
        }
        System.out.println("Found "+ filtered.size() +" quakes that match California at " + where);
    }
    
    
}
