
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
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

	public int countGenes(String dna) {
	    int count = 0;
	    while (true) {
			String gene = findGene(dna);
			if (gene.isEmpty()) {
				break;
			} else {
				count++;
			}

		}
		return count;
        }
        public void testCountGenes() {
        //System.out.println(countGenes("ATGTAAGATGCCCTAGT"));
        //System.out.println(countGenes("ATGTAAGATGCCCTAGTCAATGCCCTGA"));
        System.out.println(countGenes("AATGCTAACTAGCTGACTAAT"));
    }
        
        public static void main() {
            Part3 part3 = new Part3();
            part3.testCountGenes();
        }
}
