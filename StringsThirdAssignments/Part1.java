import edu.duke.*;

/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
	            int startCodonIndex = dna.indexOf( "ATG" );
		    StorageResource geneList = new StorageResource();
	    while (true) {
			String gene = findGene(dna);
			if (gene.isEmpty()) {
				break;
			} else {
				geneList.add(gene);
				startCodonIndex = dna.indexOf( gene, startCodonIndex ) + gene.length();
			}

		} return geneList;
        } 
    public void testGetAllGenes() {
        String t1 = "ATGxxx";
        String t2 = "ATGxxxTAAATGxxxTGAxxxATGxxxTAG";
        
        System.out.println( "Getting all genes in " + t1 + ". Should be none.");
        StorageResource t1Genes = getAllGenes( t1 );
        int t1NumGenes = 0;
        for( String gene : t1Genes.data() ){
            System.out.println( gene );
            t1NumGenes++;
        }
        System.out.println( t1 + " contains " + t1NumGenes + " genes. Should be 0." ); 
        
        System.out.println( "Getting all genes in " + t2 + ". Should be ATGxxxTAA, ATGxxxTGA, ATGxxxTAG..");
        StorageResource t2Genes = getAllGenes( t2 );
        int t2NumGenes = 0;
        for( String gene : t2Genes.data() ){
            System.out.println( gene );
            t2NumGenes++;
        }
        System.out.println( t1 + " contains " + t2NumGenes + " genes. Should be 3." );
        
        
    }
    
    
    public static void main() {
    Part1 test = new Part1();
    test.testGetAllGenes();

    }

}
