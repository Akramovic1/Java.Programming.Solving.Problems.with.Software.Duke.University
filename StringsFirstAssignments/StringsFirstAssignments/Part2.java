
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna , int startPosition , int endPosition){
      
        if (startPosition == -1 || endPosition == -1) {
            return "";
        }
        String result = dna.substring(startPosition , endPosition +3);
        if ((startPosition - endPosition + 3) % 3 != 0) {
            return "This is invalid gene";
        }
        return result;
    }
    public void testSimpleGene() {
        int a = 0 , z = 0;
        String dna = "XCCDFVFDTAAFDC";
        a = dna.indexOf("ATG");
        z = dna.indexOf("TAA");
        System.out.println("DNA with no ATG "+ dna);
        String gene = findSimpleGene( dna , a , z );
        System.out.println("Gene is " +gene);
        ///////////
        dna = "VVATGCDFVGBFDC";
        a = dna.indexOf("ATG");
        z = dna.indexOf("TAA");
        System.out.println("DNA with no TAA "+ dna);
        gene = findSimpleGene(dna,a,z);
        System.out.println("Gene is " +gene);
        ///////////
        dna = "MKJCDFVGBFDC";
        a = dna.indexOf("ATG");
        z = dna.indexOf("TAA");
        System.out.println("DNA with no ATG & TAA "+ dna);
        gene = findSimpleGene(dna,a,z);
        System.out.println("Gene is " +gene);
        ///////////
        dna = "VVCATGCDFVGBTAAFDC";
        a = dna.indexOf("ATG");
        z = dna.indexOf("TAA");
        System.out.println("DNA with ATG & TAA (difference multiplied by 3) "+ dna);
        gene = findSimpleGene(dna,a,z);
        System.out.println("Gene is " +gene);
        ///////////
        dna = "LVUATGCDFMBTAAFDC";
        a = dna.indexOf("ATG");
        z = dna.indexOf("TAA");
        System.out.println("DNA with ATG & TAA (difference not multiplied by 3) "+ dna);
        gene = findSimpleGene(dna,a,z);
        System.out.println("Gene is " +gene);
    }

}
