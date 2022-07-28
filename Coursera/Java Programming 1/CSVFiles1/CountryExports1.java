
/**
 * Learning CSV handling in Java
 * Java Programming: Solving Problems with Software course
 * Coursera - Duke university
 * @andergcp (Anderson Castiblanco) 
 * @version (January 7th 2021)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class CountryExports1 {
    /**
     * Prints a string of information about the country or
     * “NOT FOUND” if there is no information about the country.
     */
    public void countryInfo(CSVParser parser, String country){
        boolean found = false;
        for (CSVRecord record: parser){
            if (country.contentEquals(record.get("Country"))){
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                System.out.println(country + ": " + exports + ": " + value);
                found = true;
            }
        }
        if (!found){
            System.out.println("NOT FOUND");
        }
    }
    
    /**  
     *Prints the names of all the countries that have both exportItem1
     * and exportItem2 as export items.
     */
    public void listExportersTwoProducts(CSVParser parser, String exportItem1,
    String exportItem2){
        for (CSVRecord record : parser){
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        }
    }
    
    /**
     * Returns the number of countries that export exportItem
     */
    public int numberOfExporters(CSVParser parser, String exportItem){
        int counter = 0;
        for (CSVRecord record: parser){
            String exports = record.get("Exports");
            if (exports.contains(exportItem)){
                counter++;
            }
        }
        return counter;
    }
    
    /**
      * Prints the names of countries and their Value amount for all
      * countries whose Value (dollars) string is longer than the amount
      * string
      */
    public void  bigExporters(CSVParser parser, String amount){
        String countryValue = "";
        for (CSVRecord record: parser){
            countryValue = record.get("Value (dollars)");
            if (countryValue.length() > amount.length()){
                System.out.println(record.get("Country") + " " + countryValue);
            }
        }
    }
    
    /**
     * Tests previous Methods
     */
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Country Info of Nauru:");
        countryInfo(parser, "Nauru");
        
        System.out.println("List of exporters of cotton and flowers");
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        
        System.out.println("Number of exporters of cocoa");        
        parser = fr.getCSVParser();        
        int numberExporters = numberOfExporters(parser, "cocoa");
        System.out.println(numberExporters);
        
        System.out.println("Big exporters with $999,999,999,999");                
        parser = fr.getCSVParser();        
        bigExporters(parser, "$999,999,999,999");
    }

}
