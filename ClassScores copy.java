import java.util.*;

public class ClassScores {
	public int[] findMode(int[] scores){
		//sort
		Arrays.sort(scores);
		
		//in max(largest frequency encountered so far), Arraylist, counter
		int max = 0;
		int counter = 1;
		List<Integer> modes = new ArrayList<Integer>();
		
		//iterate through scores
		for (int i = 1; i < scores.length; i++){
			//if (scores[i] == scores[i-1]){
			if (scores[i] == scores[i-1]){	
				//increment 
				counter++;
			}
			//else
			else {
				//if counter> max
				if (counter> max){
					//replace max with counter
					max = counter;
					//clear the list
					modes.clear();
					//add previous number to list
					modes.add(scores[i-1]);
				}
				//else if counter == max
				else if (counter == max){
					//add previous number to list
					modes.add(scores[i-1]);
				}
				//reset counter
				counter = 1;
			}
		}
		if (counter> max){                     // this is so that the last number gets counter in the list if necessary even though the for loop is done
				//replace max with counter 
				max= counter;
				//clear the list
				modes.clear();
				//add last number to list
				modes.add(scores[scores.length - 1]);
		}
		//else if counter == max
		else if (counter == max){
				//add last number to list
				modes.add(scores[scores.length-1]);
		}
		
		// loop through list and move values into corresponding index of new array
		//modes.toArray(new int[modes.size()]);
		int[] ret = new int[modes.size()];
		for( int i =0; i < modes.size(); i++){
			ret[i] = modes.get(i);
		}
		return ret;
	
	}
}
