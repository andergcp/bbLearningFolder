
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are: " + uniqueIPs + " unique IPs.");
    }
    
    public void testPrintAllHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
        
    }
    
    public void testuniqueIPVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> res = la.uniqueIPVisitsOnDay("Sep 24");
        System.out.println("Size of the ArrayList is: " + res.size());
        for(String ip: res){
            System.out.println(ip);
        }
    }
    
    public void testcountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int low = 200;
        int high = 299;
        int res = la.countUniqueIPsInRange(low,high);
        System.out.println("Unique IPs in range " + low + "-" + high + "->" + res);
    }
    
    public void testCountVisitsPerIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println("Times an IP visited the site:\n" + counts);
    }
    
    public void testmostNumberVisitsByIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        int maxVisits = la.mostNumberVisitsByIP(counts);
        System.out.println("The maximum number of visits to this website by a single IP address is: " + maxVisits);
    }
    
    public void testiPsMostVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        ArrayList<String> mostVisitedIPs = la.iPsMostVisits(counts);
        System.out.println(mostVisitedIPs.size() + " most visited IPs:\n" + mostVisitedIPs);
    }
    
    public void testIPsForDays(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> result =la.iPsForDays();
        System.out.println("IPs per day:\n" + result);
    }
    
    public void TestdayWithMostIPVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> visitsPerDay =la.iPsForDays();
        String result = la.dayWithMostIPVisits(visitsPerDay);
        System.out.println("The day with the most visits is: " + result);
    }
}
