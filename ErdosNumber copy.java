import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

import javax.xml.soap.Node;

public class ErdosNumber {

	private TreeMap<String, Integer> AuthorNums = new TreeMap<String, Integer>();
	
	public String[] calculateNumbers(String[] pubs) {
		//Given pubs, an array of strings with authors on that publication
		//Find which pubs have ERDOS
		//Make a Map <K,V> with all authors that are with ERDOS as keys and 1 as their ERDOS #
		//Go through each pub in list and add the pubs that dont exist updating their Erdos # as 
		//smallest in pub plus 1 if their current is larger than that.
		
		chidlrenMap(pubs,"ERDOS",0);
		
			
			
		ArrayList<String> sortedList = new ArrayList<String>(AuthorNums.keySet());
		sortedList.sort(null);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<sortedList.size(); i++){
			int ENum = AuthorNums.get(sortedList.get(i));
			if(ENum < 0){ sb.append(sortedList.get(i) + ":");
			}else{ sb.append(sortedList.get(i) + " " + ENum + ":");}
			
		}
		System.out.println(Arrays.toString(sb.toString().split(":")));
		return sb.toString().split(":");
	}


	public void chidlrenMap(String[] pubs, String current, int n){
				System.out.println(current + n);
				for (String pub: pubs){
					
					Boolean found = false;
					System.out.println(current + n + found.toString());
					
					String[] authors = pub.split(" ");
					for (String each: authors){
						if (each.equals(current)){
							if (!AuthorNums.containsKey(current) || AuthorNums.get(current) > n || AuthorNums.get(current) < 0){
								AuthorNums.put(current, n);
							
							}else{
								n = AuthorNums.get(current);
							}
							found = true;
							
						}else{
							if(!AuthorNums.containsKey(each)){
								AuthorNums.put(each, -1);
							}
						}
						
					}
					if (found){
							System.out.println("found "+ current);
							for(String friend: authors){
								if((AuthorNums.get(friend) < 0 || AuthorNums.get(friend) > n) ){
									AuthorNums.put(friend, n+1);
									chidlrenMap(pubs, friend, n+1);
								}
								System.out.println("last if not working");
							}
							
					}
					System.out.println("not found");
					
						
						
				}
				
				
				
	}
	
	
}
