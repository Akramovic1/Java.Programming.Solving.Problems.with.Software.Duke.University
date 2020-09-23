import edu.duke.*;
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public void processGenes(StorageResource sr){
        StorageResource highChar = new StorageResource();
        StorageResource highRatio = new StorageResource();
        String longest = "";
        int c1 = 0 , c2 = 0;
        for(String line : sr.data()){
            if(line.length()>60){
                highChar.add(line);
                c1++;
            }
            if(cgRatio(line)>0.35){
                highRatio.add(line);
                c2++;
            }
            if(line.length()>longest.length()){
                longest = line;
            }
        }
        System.out.println( "More than 60 Character Strings: \n " );
        for(String line : highChar.data()){
              System.out.println(line);
        }
        System.out.println( "Number of more than 60 Char Strings: " + highChar.size() + " OR " + c1);
        System.out.println( " CG Ratio > 0.35 Strings: \n" );
        for(String line : highRatio.data()){
            System.out.println(line);
        }
        System.out.println( "Number of CG Ratio > 0.35 Strings: " + highRatio.size()+ " OR " +c2);
        System.out.println( "Longest String Length: " + longest.length() );
        System.out.println( "\n \n Total Number of Genes: " + sr.size() );
    }
    public void testProcessGenes() {
        
        FileResource fr = new FileResource("C:\\Users\\20114\\Desktop\\Java Duke\\StringsThirdAssignments\\brca1line.fa");
        String dna = fr.asString();
        dna = dna.toLowerCase();
        StorageResource dnaFinal = new StorageResource();
        dnaFinal.add(dna);
        processGenes(dnaFinal);
        int count=0, pos=0;
        while(dna.indexOf("ctg",pos) != -1){
            count++;
            pos = dna.indexOf("ctg",pos) + 3;
        }
        System.out.println("-----------\n\n"+count);
        /*String t1 = "ATGxxx";
        String t2 = "ATGxxxTAAATGxxxTGAxxxATGxxxTAG";
        String t3 = "ATGxxCCCCxxxxxxTAACCCCGGGG";
        String t4 = "ATGxxxTAAxxxATGxxxTAG";
        String t5 = "xxxATGGGGCCCCGGGGGTGACCCC";
        
        System.out.println( "Testing string " + t1 + "." );
        processGenes( getAllGenes( t1 ) );
        
        System.out.println( "Testing string " + t2 + "." );
        processGenes( getAllGenes( t2 ) );
        
        System.out.println( "Testing string " + t3 + "." );
        processGenes( getAllGenes( t3 ) );
        
        System.out.println( "Testing string " + t4 + "." );
        processGenes( getAllGenes( t4 ) );
        
        System.out.println( "Testing string " + t5 + "." );
        processGenes( getAllGenes( t5 ) );*/
    
    }
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
    public int countCTG(String dna){
        dna = dna.toLowerCase();
        int count = 0 , pos = 0;
        int index = dna.indexOf("ctg",pos);
        while(index != -1){
            count++;
            pos = index + 3;
        } return count;
    } 
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
    
        public static void main(String[] args) {
        Part3 part3 = new Part3();
        part3.testProcessGenes();
        /*String dna = "TTTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAA";
        int pos = dna.indexOf("T");
        int count = 0;
        int startPos = 0;
        String newDna = "";
        if (pos == -1) {
            System.out.println(dna);
        }
        while (count < 3) {
            count += 1;
            newDna = newDna + dna.substring(startPos,pos);
            startPos = pos+1;
            pos = dna.indexOf("T", startPos);
            if (pos == -1) {
                break;
            }
        }
        newDna = newDna + dna.substring(startPos);
        System.out.println(newDna);*/
    }
}
