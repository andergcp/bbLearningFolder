
/**
 * Write a description of LargestQuakes here.
 * 
 * @andergcp (Anderson Castiblanco) 
 * @version (4th Feb 2021)
 */
import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println(list.size() + " quakes read from file.");
        // for(QuakeEntry qe: list){
            // System.out.println(qe);
        // }
        // int idxOfMax = indexOfLargest(list);
        // System.out.println(idxOfMax + " Index-> " + list.get(idxOfMax));
        ArrayList<QuakeEntry> filtered = getLargest(list, 50);
        System.out.println(filtered.size() + " largest Quakes:");
        for(QuakeEntry qe: filtered){
            System.out.println(qe);
        }
    }
    
    /**
     * This method returns an integer representing the index location in data
     * of the earthquake with the largest magnitude.
     */
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        int maxIdx = 0;
        for(int i=0; i<data.size(); i++){
            if(data.get(i).getMagnitude() >
                data.get(maxIdx).getMagnitude()){
                    maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    /**
     * This method returns an ArrayList of type QuakeEntry of the top howMany
     * largest magnitude earthquakes from quakeData. The quakes returned
     * should be in the ArrayList in order by their magnitude, with the
     * largest magnitude earthquake in index position 0. If quakeData has
     * fewer than howMany earthquakes, then the number of earthquakes
     * returned in the ArrayList is equal to the number of earthquakes in
     * quakeData. 
     */
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData,
                                            int howMany){
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        
        if(quakeData.size() <= howMany){
            return quakeData;
        }
        
        for(int i=0; i<howMany; i++){
            int idxOfMax = indexOfLargest(copy);
            ret.add(copy.get(idxOfMax));
            copy.remove(idxOfMax);
        }
        // System.out.println("This is copy:");
        // for(QuakeEntry qe: copy){
            // System.out.println(qe);
        // }
        // System.out.println("End of copy");
        return ret;
    }
}
