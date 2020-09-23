
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna){
        int start = dna.indexOf("ATG");
		if (start == -1) {
			return "";
		}
		int stop = dna.indexOf("TAA", start+3);
		if ((stop - start) % 3 == 0) {
			return dna.substring(start, stop+3);
		}
		else {
			return "";
		}
    }
    public void testSimpleGene() {
	String dna = "XCCDFVFDTAAFDC";
        System.out.println("DNA with no ATG "+ dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene is " +gene);
        ///////////
        dna = "VVATGCDFVGBFDC";
        System.out.println("DNA with no TAA "+ dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " +gene);
        ///////////
        dna = "MKJCDFVGBFDC";
        System.out.println("DNA with no ATG & TAA "+ dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " +gene);
        ///////////
        dna = "VVCATGCDFVGBTAAFDC";
        System.out.println("DNA with ATG & TAA (difference multiplied by 3) "+ dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " +gene);
        ///////////
        dna = "LVUATGCDFMBTAAFDC";
        System.out.println("DNA with ATG & TAA (difference not multiplied by 3) "+ dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " +gene);
	}
}
