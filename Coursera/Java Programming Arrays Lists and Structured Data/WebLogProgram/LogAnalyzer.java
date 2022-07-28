
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         WebLogParser lp = new WebLogParser();
         for(String line: fr.lines()){
             LogEntry le = lp.parseEntry(line);
             records.add(le);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     /**
      * This method should return an integer representing the number of unique
      * IP addresses. It should also assume that the instance variable
      * records already has its ArrayList of Strings read in from a file,
      * and should access records in computing this value.
      */
     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for(LogEntry le: records){
             String ipAddr = le.getIpAddress();
             if (!uniqueIPs.contains(ipAddr)){
                 uniqueIPs.add(ipAddr);
             }
         }
         return uniqueIPs.size();
     }
     
     /**
      * This method examines all the web log entries in records and print
      * those LogEntrys that have a status code greater than num.
      */
     public void printAllHigherThanNum(int num){
         int total = 0;
         for(LogEntry le: records){
             int status = le.getStatusCode();
             if(status > num){
                 System.out.println(le);
                 total += 1;
             }
         }
         System.out.println("Total: " + total);
     }
     
     /**
      * This method has one String parameter named someday in the format
      * “MMM DD” where MMM is the first three characters of the month name
      * with the first letter capitalized and the others in lowercase, and
      * DD is the day in two digits (examples are “Dec 05” and “Apr 22”).
      * This method accesses the web logs in records and returns an ArrayList
      * of Strings of unique IP addresses that had access on the given day.
      */
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> uniqueIPsOnDay = new ArrayList<String>();
         
         for(LogEntry le: records){
             String date = le.getAccessTime().toString();
             String monthDay = (date.substring(4,8) + date.substring(8,10));
             if(monthDay.equals(someday)){
                 if(!uniqueIPsOnDay.contains(le.getIpAddress())){
                     uniqueIPsOnDay.add(le.getIpAddress());   
                 }
             }
         }
         return uniqueIPsOnDay;
     }
     
     /**
      * This method returns the number of unique IP addresses in records that
      * have a status code in the range from low to high, inclusive.
      */
     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> uniqueIPsOnRange = new ArrayList<String>();
         
         for(LogEntry le : records){
             int statusCode = le.getStatusCode();
             if(statusCode >= low
                && statusCode <= high
                && !uniqueIPsOnRange.contains(le.getIpAddress())){
                    uniqueIPsOnRange.add(le.getIpAddress());
            }
         }
         return uniqueIPsOnRange.size();
     }
     
     /**
      * This method returns a HashMap<String, Integer> that maps an IP address
      * to the number of times that IP address appears in records, meaning
      * the number of times this IP address visited the website.
      */
     public HashMap<String,Integer> countVisitsPerIP(){
         HashMap<String,Integer> counts = new HashMap<String,Integer>();
         
         for(LogEntry le: records){
             String ip = le.getIpAddress();
             if(counts.containsKey(ip)){
                 counts.put(ip, counts.get(ip) + 1);
             }
             else {
                 counts.put(ip, 1);                 
             }
         }
         
         return counts;
     }
     
     /**
      *  This method returns the maximum number of visits to this website by
      *  a single IP address.
      */
     public int mostNumberVisitsByIP(HashMap<String,Integer> visitsPerIp){
         int maxVisits = 0;
         for(String key: visitsPerIp.keySet()){
             int visits = visitsPerIp.get(key);
             if(visits > maxVisits){
                 maxVisits = visits;
             }
         }
         return maxVisits;
     }
     
     /**
      * This method returns an ArrayList of Strings of IP addresses that all
      * have the maximum number of visits to this website.
      */
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> visitsPerIp){
         ArrayList<String> mostVisitedIPs = new ArrayList<String>();
         int maxVisits = mostNumberVisitsByIP(visitsPerIp);
         for(String key: visitsPerIp.keySet()){
             if(visitsPerIp.get(key) == maxVisits){
                 mostVisitedIPs.add(key);
             }
         }
         return mostVisitedIPs;
     }
     
     /**
      * This method returns a HashMap<String, ArrayList<String>> that uses
      * records and maps days from web logs to an ArrayList of IP addresses
      * that occurred on that day (including repeated IP addresses). A day is
      * in the format “MMM DD” where MMM is the first three characters of the
      * month name with the first letter capital and the others in lowercase,
      * and DD is the day in two digits (examples are “Dec 05” and “Apr 22”).
      */
     public HashMap<String, ArrayList<String>> iPsForDays(){
         HashMap<String, ArrayList<String>> iPsInDays = new HashMap<String, ArrayList<String>>();
         for(LogEntry le: records){
             ArrayList<String> list;
             String date = le.getAccessTime().toString();
             String day = (date.substring(4,8) + date.substring(8,10));
             String ip = le.getIpAddress();
             if(iPsInDays.containsKey(day)){
                 list = iPsInDays.get(day);
             }
             else{
                 list = new ArrayList<String>();
             }
             list.add(ip);
             iPsInDays.put(day, list);
         }
         return iPsInDays;
     }
     
     /**
      * This method returns the day that has the most IP address visits.
      * If there is a tie, then return any such day.
      */
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipsForDays){
         String mostVisitedDay = "";
         int maxDayVisits = 0;
         for(String key: ipsForDays.keySet()){
             int dayVisits = ipsForDays.get(key).size();
             if(dayVisits > maxDayVisits){
                 mostVisitedDay = key;
                 maxDayVisits = dayVisits;
             }
         }
         return mostVisitedDay;
     }
     
     /**
      * This method returns an ArrayList<String> of IP addresses that had the
      * most accesses on the given day
      */
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ipsForDays, String day){
         ArrayList<String> ipsMostVisited = new ArrayList<String>();
         ArrayList<String> ipsVisited = ipsForDays.get(day);
         ArrayList<String> ss = uniqueIPVisitsOnDay(day);
         return ipsMostVisited;
     }
}
