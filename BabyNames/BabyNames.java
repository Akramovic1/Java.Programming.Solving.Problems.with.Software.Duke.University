/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import org.apache.commons.csv.*;
import edu.duke.*;
import java.io.*;

public class BabyNames {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int boyTotalBirths = 0;
        int girlTotalBirths = 0;
        int boyCount = 0;
        int girlCount = 0;
        
        for (CSVRecord record : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(record.get(2));
            totalBirths += numBorn;
            
            if (record.get(1).equals("M")) {
                boyTotalBirths += numBorn;
                boyCount++;
            } else {
                girlTotalBirths += numBorn;
                girlCount++;
            }
        }
        
        System.out.println("Total data = " + (boyCount + girlCount));
        System.out.println("Total births = " + totalBirths);
        
        System.out.println("Total girls = " + girlCount);
        System.out.println("Total girls births = " + girlTotalBirths);
        
        System.out.println("Total boys = " + boyCount);
        System.out.println("Total boys births = " + boyTotalBirths);
    }
    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int getRank(int year , String name , String gender){
        FileResource fr = new FileResource("C:\\Users\\20114\\Desktop\\Java Duke\\BabyNames\\us_babynames_by_year\\yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        int rank = 0;
        for(CSVRecord CR : parser ){
            if(CR.get(1).equals(gender)){
                rank++;
                if(CR.get(0).equals(name)){
                    return rank;
                }
            }
        }   return -1;
    }
    public void testGetRank() {
        System.out.println( "Rank of Frank in 1971: " + getRank( 1971, "Frank", "M" ) );
    }
    
    public String getName(int year , int rank , String gender){
        FileResource fr = new FileResource("C:\\Users\\20114\\Desktop\\Java Duke\\BabyNames\\us_babynames_by_year\\yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        int count = 0;
        String name = "";
        for(CSVRecord CR : parser ){
            if(CR.get(1).equals(gender)){
                count++;
                name = CR.get(0);
                if(rank == count){
                    return name;
                }
            }
        }   return "NO NAME";
    }
    public void testGetName() {
        System.out.println(this.getName(1982, 450 , "M"));
    }
    
    public void whatIsNameInYear (String name , int year , int newYear ,String gender){
        int CR = getRank(year , name , gender);
        String newName = getName(newYear, CR , gender);
        System.out.println( name + " born in " + year + " would be " + newName + " if they were born in " + newYear );
    }
    public void testWhatIsNameInYear() {
        whatIsNameInYear( "Owen", 1974, 2014, "M" );
    }
    
    public int yearOfHighestRank(String name , String gender){
        int rank = Integer.MAX_VALUE;
        int year = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            int CR = 0;
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            for(CSVRecord record : parser){
                if(record.get(1).equals(gender)){
                    CR++;
                }
                if(record.get(0).equals(name)){
                    if(CR < rank){
                        rank = CR;
                        year = Integer.parseInt(f.getName().substring(3,7));
                    }
                }
            }
            
        } 
        System.out.println("Highest rank of name " + name + " was in " + year + " and rank = " + rank);
        return year;
    }
    public void testYearOfHighestRank(){
        String name = "Isabella";
        String gender = "F"; 
        int year = yearOfHighestRank(name ,gender);
    }
    
    public double getAverageRank(String name , String gender ){
        int rank = 0;
        int count = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            int CR = 0;
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            for(CSVRecord record : parser){
                if(record.get(1).equals(gender)){
                    CR++;
                }
                if(record.get(0).equals(name)){
                    rank = rank + CR ;
                    break;
                }
            }if(rank == 0){return -1.0;} 
            count++;
        }
        double result = ((double)rank) / count ; 
        System.out.println("Average rank is " +result);
        return result;
    }
    public void testGetAverageRank(){
        String name = "Susan" , gender = "F" ;
        double result = getAverageRank(name ,gender );
    }
    
    public int getTotalBirthsRankedHigher (int year,String name ,String gender){
        int sum = 0;
        int nameRank = getRank(year , name ,gender);
        int count = 0;
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord record : parser){
            if(count<nameRank-1){
            count++;
            sum = sum + Integer.parseInt(record.get(2));
        }}
        System.out.println("Total Births Ranked Higher  is " +sum);
        return sum;
    }
    public void testTotalBirthsRankedHigher(){
        String name = "Drew"; 
        String gender = "M";
        int year = 1990;
        int result = getTotalBirthsRankedHigher (year,name ,gender);
    }
    
}
