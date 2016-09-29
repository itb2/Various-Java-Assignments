/**
 * Abstraction Comments: What does this program do?
 *This program returns the number of assignments (on which I will receive scores of ten)
 *I will need to complete in order to make my average a 9.5 or higher.
 * @author IVORYANNA BROWN
 */
public class AimToTen {
	public int need(int[] marks) {
		// TODO Implement need
		int numOFTens = 0;
		double sum = 0.0;
		for(int i = 0; i < marks.length ;i++){ 
			//This for loop adds up the sum of the values in marks
			sum += marks[i];
		}
		double average = sum/marks.length; //divides sum by num of values for average
		while (average<9.5){ 
			//This while loop will run until my average is at least 9.5 adding ten to the sum each time
			sum += 10;
			numOFTens +=1;
			average = sum/(marks.length+ numOFTens); //Divide sum by num of values
		}
			
		return numOFTens; //return num of tens - which is num of additional assignments
	}
}
