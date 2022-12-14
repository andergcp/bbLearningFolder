
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter{
    private double minDepth;
    private double maxDepth;
    private String name;
    
    public DepthFilter(double min, double max, String filterName){
        minDepth = min;
        maxDepth = max;
    }
    
    public boolean satisfies(QuakeEntry qe){
        double depth = qe.getDepth();
        if(depth >= minDepth && depth <= maxDepth)
            return true;
        return false;
    }
    
    public String getName(){
        return name;
    }
}
