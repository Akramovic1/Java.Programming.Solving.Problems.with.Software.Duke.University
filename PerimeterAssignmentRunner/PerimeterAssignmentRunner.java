import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int np=0;
        for(Point currPt : s.getPoints()){
            np=np+1;
        }
        return np;
    }

    public double getAverageLength(Shape s) {
        return (getPerimeter(s)/getNumPoints(s));
    }

    public double getLargestSide(Shape s) {
        double LS = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if(currDist>LS){LS=currDist;}
            prevPt = currPt;
        }
        
        return LS;
    }

    public double getLargestX(Shape s) {
        double max_x = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double curr_x = currPt.getX();
            if(curr_x>max_x){max_x = curr_x;}
            prevPt = currPt;
        }
        return max_x;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double max_perim=0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perim=getPerimeter(s); 
            if (perim>max_perim){
                max_perim=perim;
            }
        }        
        return max_perim;
    }

    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        double max_perim=0.0;
        File temp=null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perim=getPerimeter(s); 
            if (perim>max_perim){max_perim=perim;temp=f;}
        }        
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        
        double num_p=getNumPoints (s);
        System.out.println("number of points = " + num_p);
        
        double a_length=getAverageLength(s); 
        System.out.println("average length = " + a_length);
        
        double l_length=getLargestSide(s);
        System.out.println("largest length = " + l_length);
        
        double max_X=getLargestX(s);
        System.out.println("largest X coordinate = " + max_X);
    }
    
    public void testPerimeterMultipleFiles() {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        double max_peri=pr.getLargestPerimeterMultipleFiles();
        System.out.println("maximal perimeter = " + max_peri);
    }

    public void testFileWithLargestPerimeter() {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        String f_name=pr.getFileWithLargestPerimeter(); 
        System.out.println("the file with largest perimeter is "+f_name);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        
    }
}
