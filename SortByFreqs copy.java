import java.util.*;



public class SortByFreqs {
	
	TreeMap<String, Integer> mapCount = new TreeMap<String, Integer>(); //dont forget to import treemap
	
	class CountSort implements Comparator<String>{

		@Override
		public int compare(String arg0, String arg1) {
			int count0 = mapCount.get(arg0);
			int count1 = mapCount.get(arg1);
			if(count0 == count1){
				return arg0.compareTo(arg1); // why does countsort return integers
			}else{
				return count1 - count0;  // why does this work??
			}
		}
		
	}
	public String[] sort(String[] data) {
        // COUNT OCCURRENCES. sort by count. in case of tie, sort alphabetically. 
		
		for (int i = 0; i < data.length; i++){
			
			if(mapCount.containsKey(data[i])){
				mapCount.put(data[i], mapCount.get(data[i])+1); //if the key already exists
			}else{
				mapCount.put(data[i], 1);	
			}
		}
		// System.out.println(mapCount); //to make sure I have a map
		String[] answer = new String[mapCount.size()]; //because its a data item it would be private but methods dont have private data items btw)
		
		int i = 0;     // building answer one thing at a time
		for(String element: mapCount.keySet()){
			answer[i]=  element;
			i++;
		}
		//sorting
		Arrays.sort(answer, new CountSort());
		return answer;
    }
}
