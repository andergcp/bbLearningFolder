
/**
 * Assingment about handling CSV files with Java, I find coldest day and other
 * facts about the weather from many years
 * 
 * @andergcp (Anderson Castiblanco) 
 * @version (8th January 2021)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Weather {
    /**
     * Returns the record with lower temperature
     */
    public CSVRecord findTheColdestOfTwo(CSVRecord first, CSVRecord second){
        CSVRecord coldest = null;
        double firstTemp = Double.parseDouble(first.get("TemperatureF"));
        double secondTemp = Double.parseDouble(second.get("TemperatureF"));
        // System.out.println("First: " + first.get("TemperatureF") + "Second :" + second.get("TemperatureF"));
        if (firstTemp < secondTemp){
            return first;
        }
        else {
            return second;
        }    
    }
    
    /**
     * Returns the CSVRecord with the coldest temperature in the file and thus
     * all the information about the coldest temperature, such as the hour of
     * the coldest temperature. 
     */
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldest = null;
        for (CSVRecord current: parser){
            boolean isValid = !(current.get("TemperatureF").contentEquals("-9999"));
            if (isValid){
                if (coldest == null){
                    coldest = current;
                }
                else {
                    coldest = findTheColdestOfTwo(coldest, current);
                }
            }
        }
        // System.out.println("Return of coldestHourInFile: " + coldest.get("TemperatureF"));
        return coldest;
    }
    
    /**
     * Tests couldestHourInFile method
     */
    public void testColdestHourInFile (){
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + coldest.get("TemperatureF")
        + " at " + coldest.get("DateUTC")); 
    }
    
    /**
     * Returns a string that is the name of the file from selected files that
     * has the coldest temperature, and aditional info from that day.
     */
    public void fileWithColdestTemperature(){
        CSVRecord coldest = null;
        File coldestFile = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord current = coldestHourInFile(fr.getCSVParser());
            if (coldest == null){
                coldest = current;
                coldestFile = f;
            }
            else {
                // System.out.println(current + " y " + coldest);
                coldest = findTheColdestOfTwo(current, coldest);
                coldestFile = f;
            }
            // System.out.println(coldest.get("TemperatureF") + coldest.get("DateUTC"));
        }
        System.out.println("Coldest day was in file " + coldestFile.getName());
        System.out.println("Coldest temperature on that day was " +
                            coldest.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        FileResource fr = new FileResource(coldestFile);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record: parser){
            System.out.println(record.get("DateUTC") + ": "
                                + record.get("TemperatureF"));
        }
    }
    
    /**
     * Tests fileWithColdestTemperature() method
     */
    public void testFileWithColdestTemperature(){
        fileWithColdestTemperature();
    }
    
    /**
     * Returns the record with the lowest Humidity
     */
    public CSVRecord lowestHumidityOfTwo(CSVRecord first, CSVRecord second){
        double firstHumidity = Double.parseDouble(first.get("Humidity"));
        double secondHumidity = Double.parseDouble(second.get("Humidity"));
        if (firstHumidity <= secondHumidity){
            return first;
        }
        else {
            return second;
        }
        
    }
    
    /**
     * Returns the CSVRecord that has the lowest humidity.
     *  If there is a tie, then return the first such record that was found.
     */
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumidity = null;
        for (CSVRecord current: parser){
            boolean isValid = !(current.get("Humidity").contentEquals("N/A"));
            if (isValid){
                if (lowestHumidity == null){
                    lowestHumidity = current;
                }
                else {
                    lowestHumidity = lowestHumidityOfTwo(lowestHumidity, current);
                }
            }
        }
        return lowestHumidity;
    }
    
    /**
     * Tests lowestHumidityInFile() method
     */
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVRecord lowestHumidity = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest Humidity was " + lowestHumidity.get("Humidity")
        + " at " + lowestHumidity.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumidity = null;
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord current = lowestHumidityInFile(fr.getCSVParser());
            if (lowestHumidity == null){
                lowestHumidity = current;
            }
            else {
                lowestHumidity = lowestHumidityOfTwo(current, lowestHumidity);
            }
        }
        return lowestHumidity;
    }

    public void testLowestHumidityInManyFiles(){
        CSVRecord result = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + result.get("Humidity")
                            + " at " + result.get("DateUTC"));
    }
    
    /**
     * Returns a double that represents the average temperature in the file. 
     */
    public double averageTemperatureInFile(CSVParser parser){
        int count = 0;
        double sumTemps = 0;
        for (CSVRecord record: parser){
            if (record.get("TemperatureF") != "-9999"){
                sumTemps += Double.parseDouble(record.get("TemperatureF"));
                count++;
            }
        }
        return sumTemps/count;
    }
    
    /**
     * Tests averageTemperatureInFile() method
     */
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        double result = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is " + result);
    }
    
    /**
     * Returns a double that represents the average temperature of only those
     * temperatures when the humidity was greater than or equal to value
     */
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        int count = 0;
        double sumTemps = 0;
        int humidity = 0;
        for (CSVRecord record: parser){
            if (record.get("TemperatureF") != "-9999"){
                humidity = Integer.parseInt(record.get("Humidity"));
                if (humidity >= value){
                    sumTemps += Double.parseDouble(record.get("TemperatureF"));
                    count++;    
                }
            }
        }
        if (count == 0){
            System.out.println("No temperatures with that humidity");
            return -1;
        }
        return sumTemps/count;
    }
    
    /**
     * Tests averageTemperatureWithHighHumidityInFile()
     */
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        double result = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (result != -1){
            System.out.println("Average temperature in file whith Humidity equal or greater than 80 is " + result);
        }
        
    }
    
}
