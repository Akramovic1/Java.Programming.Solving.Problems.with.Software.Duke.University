
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public double cgRatio(String dna){
        dna = dna.toLowerCase();
        int countC=0;
        int countG=0;
        for(int i =0;i<dna.length();i++){
            if(dna.charAt(i) == 'c'){
                countC++;
            }else if (dna.charAt(i) == 'g'){
                countG++;
            }
        } return (double)(countC+countG)/dna.length();
    }
    public void testCGRatio() {
        System.out.println(cgRatio("ATGCCATAG"));
    }
    public int countCTG(String dna){
        dna = dna.toLowerCase();
        int count = 0 , pos = 0;
        int index = dna.indexOf("ctg",pos);
        while(index != -1){
            count++;
            pos = index + 3;
        } return count;
    } 
    public void testCountCTG() {
        System.out.println(countCTG("CTGCTGCCCCCTGCT"));
    }
    public static void main(String[] args) {
        Part2 part2 = new Part2();
        part2.testCGRatio();
    }
    

}
