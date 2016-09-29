/**
 *  Abstraction Comments: What does this program do?
 *  This program returns the minimum number of borders someone must cross in order to get to a certain country
 *  @author Ivoryanna Brown
 */
public class CirclesCountry
{
    public int leastBorders(int[] x, int[] y, int[] r,int x1, int y1, int x2, int y2) {
        int counter = 0;
    	// TODO complete leastBorders
    	//loop through all of the circles
    	for (int i = 0; i<x.length; i ++){
    		// check start and end points if they are in the circle
        	// if one is in the circle and the other is outside
    		if (distance(x[i],y[i],r[i],x1,y1) && !distance(x[i],y[i],r[i],x2,y2)){
    			//increment counter
    			counter ++;
    		}
    		else if (!distance(x[i],y[i],r[i],x1,y1) && distance(x[i],y[i],r[i],x2,y2)){
    			//increment counter
    			counter ++;
    		}
    	}
    			
    	
    	
        return counter;
    }
    //HELPER METHOD
    public boolean  distance(int centerX, int centerY, int radius, int pointX, int pointY){
    	//distance = sqrt((x1 - x2) ^2 + (y1-y2)^2) <-- PUT THIS IS JAVA TERMS
    	
    	return Math.sqrt(Math.pow(pointY-centerY, 2) + Math.pow(pointX- centerX, 2)) < radius;
    	
    	//NOTE:you dont need an if statement because just returning this will 
    	//return the boolean value you need
    }
}
