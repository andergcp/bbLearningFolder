import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Returns the number of points of the Shape s
        int numberPoints = 0;
        for (Point aPoint : s.getPoints()) {
            numberPoints += 1;
        }
        return numberPoints;
    }

    public double getAverageLength(Shape s) {
        // returns average of all the sides’ lengths in the Shape S.
        int numberPoints = getNumPoints(s);
        double perimeter = getPerimeter(s);
        return perimeter / numberPoints;
    }

    public double getLargestSide(Shape s) {
        // returns the longest side in the Shape S.
        double longestSide = 0;
        Point prevPt = s.getLastPoint();
        for (Point pt : s.getPoints()){
            double thisSide = prevPt.distance(pt);
            if (thisSide > longestSide){
                longestSide = thisSide;
            }
            prevPt = pt;
        }
        return longestSide;
    }

    public double getLargestX(Shape s) {
        // Returns the largest x value over all the points in the Shape s.
        double largestX = 0;
        for (Point pt : s.getPoints()){
            double x = pt.getX();
            if (x > largestX) {
                largestX = x;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Return the the largest perimeter over all the shapes in the files selected.
        double largestPerim = 0.0;
        Shape s = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            s = new Shape(fr);
            double sPerim = getPerimeter(s);
            if (sPerim > largestPerim) {
                largestPerim = sPerim;
            }
        }
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        // Returns the File that has the largest perimeter
        File temp = null;
        double largestPerim = 0.0;
        Shape s = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            s = new Shape(fr);
            double sPerim = getPerimeter(s);
            if (sPerim > largestPerim) {
                largestPerim = sPerim;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int numPoints = getNumPoints(s);
        System.out.println("MyShape has: " + numPoints + " points");
        double avgLength = getAverageLength(s);
        System.out.println("The avg length of myShape's sides is: " + avgLength);
        double longestSide = getLargestSide(s);
        System.out.println("The longest side of myShape is: " + longestSide);
        double largestX = getLargestX(s);    
        System.out.println("The longest X of myShape is: " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPerim = getLargestPerimeterMultipleFiles();
        System.out.println("The largest Perimeter of selected files is: " + largestPerim);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String fileLargestPerim = getFileWithLargestPerimeter();
        System.out.println("The file with the largest Perimeter, of selected files is: " + fileLargestPerim);        
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
//        pr.testPerimeter();
//        pr.testPerimeter();
//        pr.testPerimeter();
//        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
