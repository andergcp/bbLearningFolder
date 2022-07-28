
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    /**
     * This method returns an integer representing the index position of the
     * QuakeEntry with the largest depth considering only those QuakeEntry’s
     * from position from to the end of the ArrayList. 
     */
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from){
        int maxIdx = from;
        // for (int i=from+1; i< quakeData.size(); i++) {
        for (int i=from+1; i<50; i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    /**
     * This method sorts the QuakeEntry’s in the ArrayList by depth using the
     * selection sort algorithm, but in reverse order from largest depth to
     * smallest depth (the QuakeEntry with the largest depth should be in the
     * 0th position in the ArrayList).
     */
    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
       // for (int i=0; i< in.size(); i++) {
       for (int i=0; i< 50; i++) {
            int maxIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx,qi);
        }        
    }
 
    /**
     * This method makes one pass of bubble sort on the ArrayList. It should
     * take advantage of the fact that the last numSorted elements are already
     * in sorted order
     */
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        for(int i=0; i < quakeData.size() - numSorted -1; i++){
            if(quakeData.get(i).getMagnitude() > quakeData.get(i+1).getMagnitude()){
                QuakeEntry qi = quakeData.get(i);
                QuakeEntry qiplus = quakeData.get(i+1);
                quakeData.set(i, qiplus);
                quakeData.set(i+1, qi);
            }
        }
    }
    
    /**
     * Implementing bubble sort
     */
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        for(int i=0; i < in.size(); i++){
            onePassBubbleSort(in, i);
        }
    }
    
    /**
     * This method returns true if the earthquakes are in sorted order by
     * magnitude from smallest to largest. Otherwise this methods returns
     * false
     */
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        for(int i=0; i < quakes.size() -1; i++){
            if(quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude()){
                return false;
            }
        }
        return true;
    }
    
    /**
     * This method should call checkInSortedOrder and stop early if the
     * ArrayList is already sorted. This method prints how many passes
     * were needed to sort the elements. 
     */
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        for(int i=0; i < in.size(); i++){
            if(checkInSortedOrder(in)){
                System.out.println(i + " passes needed for sorting");
                break;
            }
            onePassBubbleSort(in, i);
        }
    }
    
    /**
     * This method sorts earthquakes by their magnitude from smallest to
     * largest using selection sort similar to the sortByMagnitude method.
     * However, this method should call checkInSortedOrder and stop early if
     * the ArrayList is already sorted. This method should print how many
     * passes were needed to sort the elements. For selection sort, one pass
     * has exactly one swap. 
     */
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
       for (int i=0; i< in.size(); i++) {
            if(checkInSortedOrder(in)){
                System.out.println(i + " passes needed for sorting");
                break;
            }
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
    }
    
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
       
        System.out.println("read data for "+list.size()+" quakes");    
        // sortByMagnitude(list);
        sortByLargestDepth(list);
        // sortByMagnitudeWithBubbleSort(list);
        // sortByMagnitudeWithBubbleSortWithCheck(list);
        // sortByMagnitudeWithCheck(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                              qe.getLocation().getLatitude(),
                              qe.getLocation().getLongitude(),
                              qe.getMagnitude(),
                              qe.getInfo());
        }
        
    }
}
