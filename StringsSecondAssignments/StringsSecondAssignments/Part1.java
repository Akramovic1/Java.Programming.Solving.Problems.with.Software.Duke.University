package StringsSecondAssignments;
package StringsSecondAssignments libraries

/**
 * Find Many Genes !
 * 
 * @author (Akramovic) 
 * @version (1.0)
 */

public class Part1 {
    int findStopCodon(String dna , int startIndex , String stopCodon){
        int stopIndex = dna.indexOf(stopCodon,startIndex+3);
        while(stopIndex != -1){
            if((stopIndex-startIndex)%3==0){
                return stopIndex;
            }
            else{
                stopIndex=dna.indexOf(stopCodon,stopIndex+1);
            }}
        return dna.length();
    }
    void testFindStopCodon(){
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna, 0,"TAA");
        System.out.println(dex);

        dex = findStopCodon(dna, 9,"TAA");
        System.out.println(dex);

        dex = findStopCodon(dna, 1,"TAA");
        System.out.println(dex);

        dex = findStopCodon(dna, 0,"TAG");
        System.out.println(dex);
    }
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
	int tagIndex = findStopCodon(dna, startIndex, "TAG");
	int tgaIndex = findStopCodon(dna, startIndex, "TGA");
	int minIndex = 0;
	if(taaIndex == -1 || (tagIndex != -1 && tagIndex < taaIndex)) {
		minIndex = tagIndex;
	} else {
		minIndex = taaIndex;
	}
	if(minIndex == -1 || (tgaIndex != -1 && tgaIndex < minIndex)) {
		minIndex = tgaIndex;
	}
	if(minIndex == -1) {
		return "";
	}
	return dna.substring(startIndex , minIndex + 3);
        }
	
    
    public void testFindGene() {
		String one = "ATFxxxyyyzzzTAAxxxTAGxxx";
		String two = "xxxATGxxxyyyxxTAGxTAAxxx";
		String three = "xyyATGxxxyyyuuuTGAxxxTAGxxx";
		String four = "xyyATGxxxyyxxxyuuuTGAxxxTAGxxx";

		System.out.println("Gene is: " + one + " " + findGene(one));
		System.out.println("Gene is: " + two + " " + findGene(two));
		System.out.println("Gene is: " + three + " " + findGene(three));
		System.out.println("Gene is: " + four + " " + findGene(four));
	}

	public StorageResource getAllGenes(String dna) {
	    while (true) {
			String gene = findGene(dna);
			if (gene.isEmpty()) {
				break;
			} else {
				System.out.println(gene);
			}

		}
        }
    
    
    public static void main() {
    Part1 test = new Part1();
    test.testFindStopCodon();

    }
}
