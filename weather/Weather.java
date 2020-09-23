import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.File;

/**
 * Write a description of Weather here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Weather{
    public CSVRecord hottestHourInFile(CSVParser parser){
        CSVRecord largestRecord = null;
        for(CSVRecord currRecord : parser){
            largestRecord = getLargestOfTwo(currRecord,largestRecord);
        } return largestRecord;
    }
    
    public void hottestInDay() {
        FileResource fr = new FileResource();
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("hottest temp was " + largest.get("TemperatureF") + " at" + largest.get("TimeEST"));
    }

    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();

        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }
    public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar) {
        if (largestSoFar == null) {
            largestSoFar = currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            if (currentTemp > largestTemp) {
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }
    public CSVRecord getSmallestOfTwo(CSVRecord currentRow ,CSVRecord smallestSoFar){
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            if (currentTemp < smallestTemp) {
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }
    public CSVRecord getSmallestOfTwoH(CSVRecord currentRow ,CSVRecord smallestSoFar){
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        } else {
            String humidity = currentRow.get("Humidity");
            if(humidity.equals("N/A")){
                return smallestSoFar ; 
            }
            double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("Humidity"));
            if (currentTemp < smallestTemp) {
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord SS = null;
        for(CSVRecord CR : parser){
            SS = getSmallestOfTwo(CR,SS);
        }
        double result = Double.parseDouble(SS.get("TemperatureF"));
        System.out.println("coldestHourInFile is " + result);
        return SS;
    }
    public void testCodestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        coldestHourInFile(parser);
    }
    public String fileWithColdestTemperature(){
        String result = "";
        File coldestFile = null;
        CSVRecord SS = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord CR = coldestHourInFile(fr.getCSVParser());
            if(SS == null){
                SS = CR;
            }else{
                double CTemp = Double.parseDouble(CR.get("TemperatureF"));
                double STemp = Double.parseDouble(SS.get("TemperatureF"));
                if(CTemp<STemp && CTemp != -9999){
                    SS = CR;
                    result = f.getName();
                    coldestFile = f;
                }
            }
        }
        FileResource fr = new FileResource(coldestFile);
        CSVParser parser = fr.getCSVParser();
        System.out.println("Coldest day was in file " + result);
        System.out.println("Coldest temperature on that day was " + Double.parseDouble(SS.get("TemperatureF")));
        System.out.println("All the Temperatures on the coldest day were: ");
        for(CSVRecord data : parser){
            System.out.println(data.get("DateUTC") + ": " + data.get("TemperatureF"));

        }
        return result;
    }
     public void testFileWithColdestTemperature() {
        fileWithColdestTemperature();
    }
    public CSVRecord lowestHumidityInFile (CSVParser parser){
        CSVRecord LH = null;
        for(CSVRecord CH : parser){
            LH = getSmallestOfTwoH(CH,LH);
        }
        return LH;
    }
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord data = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was "+data.get("Humidity")+" at "+data.get("DateUTC"));
    }
    public CSVRecord lowestHumidityInManyFiles (){
        CSVRecord LH = null;
        File LHFile = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord CH = lowestHumidityInFile(fr.getCSVParser());
            LH = getSmallestOfTwoH(CH,LH);
        }
        return LH;
    } 
    public void testLowestHumidityInManyFiles(){
        CSVRecord data = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+data.get("Humidity")+" at "+data.get("DateUTC"));
    }
    public double averageTemperatureInFile (CSVParser parser){
        double sum = 0.0;
        int count = 0;
        double result = 0.0;
        for(CSVRecord CR : parser){
            sum = sum + Double.parseDouble(CR.get("TemperatureF"));
            count++;
        } 
        result = sum / count;
        return result; 
    }
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double result = averageTemperatureInFile (parser);
        System.out.println("Average temperature in file is "+result);
    }
    public double averageTemperatureWithHighHumidityInFile (CSVParser parser,int h){
        double sum = 0.0;
        int count = 0;
        double result = 0.0;
        for(CSVRecord CR : parser){
            int CHTemp = Integer.parseInt(CR.get("Humidity"));
            if(CHTemp >= h){
                sum = sum + Double.parseDouble(CR.get("TemperatureF"));
                count++;
            }
        }
        if(count == 0){System.out.println("No temperatures with that humidity");return result;}
        result = sum / count;
        System.out.println("Average Temp when high Humidity is "+result);
        return result;         
    }
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        averageTemperatureWithHighHumidityInFile(parser,80);
    }
}
