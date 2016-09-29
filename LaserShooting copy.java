/**
 * Abstraction Comments: What does this program do?
 * This Program returns the expected number of targets that will be hit on the plane by a single shot
 * @author Ivoryanna Brown
 */
public class LaserShooting {
	public double numberOfHits(int[] x, int[] y1, int[] y2) {
		// declare the value, initialize it to zero
		double counter = 0.0;
		//for loop - iterate though targets 
		for (int i =0; i < x.length; i++){
			//find angle inside the for loop
			double theta1 = Math.atan2(y1[i],x[i]);     //used atan2 instead of atan to return a double instead of an int
			double theta2 = Math.atan2(y2[i],x[i]);
			double theta = Math.abs(theta1 - theta2);
			//divide by pi
			theta /= Math.PI;   // this means theta = theta/Math.PI
			//increment return value
			counter += theta;
		}
		//return it
		return counter;
	}
}
