import java.util.*;

public class ServiceNames {
	public String[] makeList(String[] services){
		//hashmap string(list/set) strings
		HashMap<String, List<String>> map = new HashMap<>();
		
		//loop through services
		for(int i = 0; i<services.length; i ++){
			//string[] = services[i].split();
			String[] newList =  services[i].split(" ");
			//store newList[0] which is the company name
			String companyName = newList[0];
			//loop through the rest of the stuff
			for (int k= 1; k< newList.length; k++){
				//Check if service is already a key
				//If (!map.containsKey(stuff[i/j/k]))
				if (!map.containsKey(newList[k])){
					// put a new pair put(stuff[j], new ArrayList<String>())
					map.put(newList[k], new ArrayList<String>());
					
				}
				//get(newList[k]) from map .add(companyName)
				map.get(newList[k]).add(companyName);
				
			}
			
		}
		//set up string[]
		String[] serviceList = new String[map.keySet().size()];
		//set up counter
		int counter = 0;
		for (String service :map.keySet()){
			// string = service + "==>"
			String mapString = service + " ==> ";
			Collections.sort(map.get(service));
			//loop through map.get(service)
			for (int i=0; i< map.get(service).size(); i ++){
				//string += company +","
				mapString += map.get(service).get(i) + ", ";	
			}
			
			//String[counter++] = string.substring(0, length -2)
			serviceList[counter++] = mapString.substring(0, (mapString.length()) -2);
			
		
		}
		Arrays.sort(serviceList);
		return serviceList;
	}
}
