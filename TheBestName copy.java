import java.util.*;

public class TheBestName {
	
	private class NameComp implements Comparable<NameComp>{
		
		private String name;
		private int weight;
		
		public NameComp(String n){
			name = n;
			weight = 0;
			for (int i=0; i< name.length(); i++){
				weight += name.charAt(i) - 'A' + 1; //What?		
			}
		}
		
		public int compareTo(NameComp other){
			if(name.equals("JOHN")){
				return -1;
			}
			if(other.name.equals("JOHN")){
				return 1;
			}
			//now comparing by weight
			if(weight == other.weight){
				return name.compareTo(other.name);
			}
			
			return other.weight -weight; // if other weight greater, we get a positive number and other comes first
			//if weight is greater than other weight we get a negative and this goes b4 other
		}
		
		public String toString(){
			return name;
		}
	}
	
	
	public String[] sort(String[] names) {
        // put all objects of name through NameComp for comparing as an arraylist
		List<NameComp> n = new ArrayList<NameComp>();
		for (int i = 0; i< names.length;i++){
			n.add(new NameComp(names[i]));
		}
		Collections.sort(n);
		for (int i=0; i< n.size(); i++){
			names[i] = n.get(i).toString();
		}
		//YOU can also do this with a lambda expression....
		return names;
	}
}











