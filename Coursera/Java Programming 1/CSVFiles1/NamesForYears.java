
/**
 * In this assignment I handle CSV files which contains a list of names, ant two 
 * columns with F or M according to the gender, and the number of children with
 * this name.
 * 
 * @andergcp (Anderson Castiblanco) 
 * @version (8th January 2021)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class NamesForYears {
    /**
     * Prints the number of girls names , the number of boys names and the
     * total names in the file.
     */
    public void totalBirths(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        int totalBirths = 0;
        int totalGirls = 0;
        int totalBoys = 0;
        for (CSVRecord record : parser){
            int births = Integer.parseInt(record.get(2));
            totalBirths += births;
            if (record.get(1).equals("F")){
                totalGirls += births;
            }
            if (record.get(1).equals("M")){
                totalBoys += births;
            }
        }
        System.out.println("Total Births: " + totalBirths);
        System.out.println("Girls Born: " + totalGirls);
        System.out.println("Boys Born: " + totalBoys);
    }
    
    /**
     * Tests totalBirths() method
     */
    public void testTotalBirths(){
        totalBirths();
    }
    
    /**
     * Returns the rank of the name in the file for the given gender,
     * where rank 1 is the name with the largest number of births.
     * If the name is not in the file, then -1 is returned.
     */
    public int getRank(int year, String name, String gender){
        // String fileName = "/Users/acastiblanco/Documents/Coursera/Java Programming 1/CSV Sample Files/us_babynames/us_babynames_test/yob"
                            // + Integer.toString(year) + "short.csv";
        String fileName = "/Users/acastiblanco/Documents/Coursera/Java Programming 1/CSV Sample Files/us_babynames/us_babynames_by_year/yob"
                            + Integer.toString(year) + ".csv";
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        int rank = 0;
        for (CSVRecord record : parser){
            if (record.get(1).equals(gender)){
                rank++;
                if(record.get(0).equals(name)){
                   return rank; 
                }
            }
        }
        return -1;
    }
    
    /**
     * Tests getRank() method
     */
    public void testGetRank(){
        int result = getRank(1971, "Frank", "M");
        System.out.println(result);
    }
    
    /**
     * Returns the name of the person in the file at this rank, for the given
     * gender, where rank 1 is the name with the largest number of births.
     * If the rank does not exist in the file, then “NO NAME”  is returned.
     */
    public String getName(int year, int rank, String gender){
        int counter = 0;
        // String fileName = "/Users/acastiblanco/Documents/Coursera/Java Programming 1/CSV Sample Files/us_babynames/us_babynames_test/yob"
                            // + Integer.toString(year) + "short.csv";
        String fileName = "/Users/acastiblanco/Documents/Coursera/Java Programming 1/CSV Sample Files/us_babynames/us_babynames_by_year/yob"
                            + Integer.toString(year) + ".csv";
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser){
            if (record.get(1).equals(gender)){
                counter++;
                if (counter == rank){
                    return record.get(0);
                }
            }
        }
        return "NO NAME";
    }
    
    /**
     * Tests getName() method
     */
    public void testGetName(){
        String result = getName(1982, 450, "M");
        System.out.println(result);
    }
    
    /**
     * his method determines what name would have been named if they were born
     * in a different year, based on the same popularity.
     */
    public void whatIsNameInYear(String name, int year, int newYear,
                                String gender){
        int rankInYear = getRank(year, name, gender);
        String nameInNewYear = getName(newYear, rankInYear, gender);
        System.out.println(name + " born in " + year + " would be " +
        nameInNewYear + " if she was born in " + newYear + ".");
    }
    
    /**
     * Tests whatIsNameInYear method
     */
    public void testWhatIsNameInYear(){
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    /**
     * yearOfHighestRank
     */
    public int yearOfHighestRank(String name, String gender){    
        DirectoryResource dr = new DirectoryResource();
        int highestRank = 1000000000;
        int yearHighestRank = -1;
        for (File f: dr.selectedFiles()){
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3, 7));
            int rank = getRank(year, name, gender);
            if (rank < highestRank && rank != -1){
                highestRank = rank;
                yearHighestRank = year;
            }
        }
        return yearHighestRank;
    }

    /**
     * Tests yearOfHighestRank
     */
    public void testYearOfHighestRank(){
        int result = yearOfHighestRank("Mich", "M");
        System.out.println(result);
    }
    
    /**
     * This method selects a range of files to process and returns a double
     * representing the average rank of the name and gender over the selected
     * files. It should return -1.0 if the name is not ranked in any of the
     * selected files. 
     */
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int sumRanks = 0;
        int counter = 0;
        for (File f: dr.selectedFiles()){
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3, 7));
            int rank = getRank(year, name, gender);
            if (rank != -1){
                sumRanks += rank;
            }
            counter++;
        }
        if (sumRanks == 0){
            return -1.0;
        }
        return (double)sumRanks/counter;
    }
    
    /**
     * Tests getAverageRank() method
     */
    public void testGetAverageRank(){
        double result = getAverageRank("Susan", "F");
        System.out.println(result);
        result = getAverageRank("Robert", "M");
        System.out.println(result);
        // result = getAverageRank("Jacobbbbbb", "M");
        // System.out.println(result);
    }
    
    /**
     * This method returns an integer, the total number of births of those
     * names with the same gender and same year who are ranked
     * higher than name.
     */
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int result = 0;
        int rankOfName = 1000000000;
        if (getRank(year, name, gender) != -1){
            rankOfName = getRank(year, name, gender);
        }
        // String fileName = "/Users/acastiblanco/Documents/Coursera/Java Programming 1/CSV Sample Files/us_babynames/us_babynames_test/yob"
                            // + Integer.toString(year) + "short.csv";
        String fileName = "/Users/acastiblanco/Documents/Coursera/Java Programming 1/CSV Sample Files/us_babynames/us_babynames_by_year/yob"
                            + Integer.toString(year) + ".csv";
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser){
            int currentRank = getRank(year, record.get(0), gender);
            if (currentRank != -1 && currentRank < rankOfName){
                int birthsOfName = Integer.parseInt(record.get(2));
                result += birthsOfName;
            }
        }
        return result;
    }
    
    /**
     * Tests getTotalBirthsRankedHigher() method
     */
    public void testGetTotalBirthsRankedHigher(){
        int result = getTotalBirthsRankedHigher(1990, "Emily", "F");
        System.out.println("R:" + result);
        result = getTotalBirthsRankedHigher(1990, "Drew", "M");
        System.out.println("R:" + result);
    }
    
    /**
     * Prints the number of names in the file
     */
    public void numberNames(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        int totalNames = 0;
        int totalGirls = 0;
        int totalBoys = 0;
        for (CSVRecord record : parser){
            totalNames++;
            if (record.get(1).equals("F")){
                totalGirls++;
            }
            if (record.get(1).equals("M")){
                totalBoys++;
            }
        }
        System.out.println("Total Names: " + totalNames);
        System.out.println("Girl Names: " + totalGirls);
        System.out.println("Boy Names: " + totalBoys);
    }
    
    /**
     * Tests numberNames() method
     */
    public void testNumberNames(){
        numberNames();
    }
}
