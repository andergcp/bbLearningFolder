
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{
    private Location loc;
    private double maxDistance;
    private String name;
    
    public DistanceFilter(Location l, double maxD, String filterName){
        loc = l;
        maxDistance = maxD;
        name = filterName;
    }
    
    public boolean satisfies(QuakeEntry qe){
        float dist = loc.distanceTo(qe.getLocation());
        if(dist < maxDistance)
            return true;
        return false;
    }
    
    public String getName(){
        return name;
    }

}
