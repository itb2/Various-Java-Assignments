import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SpreadingNews {
	public int minTime(int[] supervisors) {
		ArrayList<ArrayList<Integer>> subordinates = new ArrayList<ArrayList<Integer>>();
		
		for (int i = 0; i< supervisors.length; i ++){
			ArrayList<Integer> subs = new ArrayList<Integer>();
			for (int k=0; k<supervisors.length;k++){
				if(i == supervisors[k]){
					subs.add(k);
				}
			}
			subordinates.add(subs);
			
		}
		

		return callTime(subordinates, 0);
	}
	public int callTime(ArrayList<ArrayList<Integer>> subordinates, int person){
		ArrayList<Integer> callTimes = new ArrayList<Integer>();
		if(subordinates.get(person).isEmpty()){
			return 0;
		}
		ArrayList<Integer> subList = subordinates.get(person);
		for(int elem: subList){
			callTimes.add(callTime(subordinates, person +1));
		}
		Collections.sort(callTimes, Collections.reverseOrder());
		
		int priority = 1;
		int max = 0;
		for (int num: callTimes){
			int sum = priority + num;
			if (sum > max){
				max = sum;
			}
			priority++;
		}

		return max;
	}
}
