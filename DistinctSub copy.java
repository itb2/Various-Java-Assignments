import java.util.*;
public class DistinctSub {
	public static int subStrings(String given){
		int strLength = given.length();
		Set<String> distinctSubs = new HashSet<String>();
		for (int i=0; i< strLength;i++){
			for (int k = i; k< strLength; k++){
				distinctSubs.add(given.substring(i,k+1));
			}
		}
		System.out.println(distinctSubs.size());
		return distinctSubs.size();
	}
	public static void main(String[] args){
		subStrings("kincenvizh");
	}
}
