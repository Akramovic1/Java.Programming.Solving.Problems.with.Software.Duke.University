
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
 int howMany(String stringa , String stringb){
    int counter=0;
    int startIndex=0;
    while(stringb.indexOf(stringa,startIndex)!=0){
        counter++;
        startIndex = stringb.indexOf(stringa,startIndex) + stringb.length();
    }
    return counter;
 }
 void testHowMany(){
     System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
     System.out.println(howMany("AA", "ATAAAA"));
     
 }
 public static void main(String[] args) {
        Part2 part2 = new Part2();
        part2.testHowMany();
    }
}
