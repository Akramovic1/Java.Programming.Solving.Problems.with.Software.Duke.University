import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * Write a description of Exports here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Exports {
    public String countryInfo(CSVParser parser, String country){
        for(CSVRecord record : parser){
            String currCountry = record.get("Country");
            if(currCountry.contains(country)){
                return country + ": " +record.get("Exports") + ": " + record.get("Value (dollars)");
            }
        } return "NOT FOUND";
        
    }
    public void listExportersTwoProducts(CSVParser parser , String exportItem1 , String exportItem2){
        for(CSVRecord record : parser){
            String exportsList = record.get("Exports");
            String country = record.get("Country");
            if(exportsList.contains(exportItem1) && exportsList.contains(exportItem2)){
                 System.out.println(country);
            }
        };
    }
    public int numberOfExporters(CSVParser parser , String exportItem){
        int count = 0;
        for(CSVRecord record : parser){
            String exportsList = record.get("Exports");
            if(exportsList.contains(exportItem)){
                count++;
            }
        }return count;
    }
    public void bigExporters (CSVParser parser , String amount){
        for(CSVRecord record : parser){
            String dollars = record.get("Value (dollars)");
            String country = record.get("Country");
            if(dollars.length()>amount.length()){
                System.out.println(country + " " + dollars);
            }
        }
    }
    
    public void test() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //String result = countryInfo(parser, "Nauru");
        //System.out.println(result);
        //listExportersTwoProducts(parser,"cotton","flowers");
        //int result = numberOfExporters(parser , "cocoa");
        //System.out.println(result);
        bigExporters(parser,"$999,999,999,999");
    }

    
}
