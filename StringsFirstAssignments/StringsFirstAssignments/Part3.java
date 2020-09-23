
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences (String texta, String searchText) {
        int count=0;
        boolean s=false;
      int startSearchingIndex=0;

      while(startSearchingIndex<texta.length()){
          int x=texta.indexOf(searchText,startSearchingIndex);
          if(x==-1){
              break;}
          else {count++;}
          startSearchingIndex=searchText.length()+x;

      } if(count>=2){
          s= true;
            System.out.println("Number of occurences = "+count);
        }
      else{
          s=false;
            System.out.println("Number of occurences = "+count);
        }
      return s;

    }
     public String lastPart(String texta, String searchSintagme){
        int firstOccuranceIndex=texta.indexOf(searchSintagme);
        int indexOfRemainedText = firstOccuranceIndex+searchSintagme.length();
        if(firstOccuranceIndex==-1){
            return texta;
        }
        else {
            return texta.substring(indexOfRemainedText);
        }
    }
}
