import java.util.*;

/**
 * Name: YOUR NAME HERE 
 * Course: CompSci 201 
 * Problem: Discussion 4 
 * Date: September 14, 2015 
 * Purpose: Solve a problem by applying some set operations
 */

public class Thesaurus {
	/**
	 * Converts the elements of a String to a set. The format of a n-word String
	 * should be "word1 word2 word3... wordn" That is, each word should be
	 * separated by exactly one space and there should be no leading or trailing
	 * spaces.
	 * 
	 * @param s
	 *            words with individual spaces separating words
	 * @return elements of s as a Set
	 */
	public TreeSet<String> sToSet(String s) {
		TreeSet<String> set = new TreeSet<String>(); //TREE SETS
		for (String temp: s.split("\\s+")){ // FOR EACH LOOP!
			set.add(temp);
			
		}
		return set;  //turn a string into a set
		
	}

	/**
	 * Converts the elements in a collection to a space-separated list. That is,
	 * if the collection contains [A, B, C, D], the method should return
	 * "A B C D". There should be no leading or trailing spaces
	 * 
	 * @return the elements of elems as a space-separated String
	 */
	public String collToS(Collection<String> elems) {
		String s  = "";
		for(String temp: elems){ // ANother for each loop
			s += temp +" ";
		}
		return s.trim(); //because i dont wanna return and leading or trailing white space
	}

	/**
	 * Returns the number of elements contained in both sets. The sets passed in
	 * should not be changed.
	 * 
	 * @param a
	 *            a set of words
	 * @param b
	 *            another set of words
	 * @return number of elements in common to a and b
	 */
	public int numInCommon(Set<String> a, Set<String> b) {
	    TreeSet<String> intersection = new TreeSet<String>(); 
		intersection.addAll(a);
		intersection.retainAll(b);
		
		return intersection.size();
		
	}

	/**
	 * Creates a new set that is the union of the given sets. The union of two
	 * sets is the set that contains all of the elements of both sets. The sets
	 * passed in should not be changed.
	 *
	 * @param a
	 *            a set of words
	 * @param b
	 *            another set of words
	 * @return union of sets a and b
	 */
	public TreeSet<String> union(TreeSet<String> a, TreeSet<String> b) {
		TreeSet<String> results = new TreeSet<String>();
		results.addAll(b);
	    results.addAll(a);
		
		return results;
	}

	/**
	 * Creates an edited version of Thesaurus entries.
	 * 
	 * If any two entries have 2 or more words in common then they should be
	 * combined into a single entry. The final Thesaurus must contain no pair of
	 * entries that have 2 or more words in common. Each entry must contain no
	 * duplicates. The words within each element of the returned value must be
	 * in alphabetical order, and the elements must appear in alphabetical order
	 * 
	 * @param entry
	 *            each element is a list of words that are all synonyms
	 * @return edited version of Thesaurus entries
	 */
	public String[] edit(String[] entry) {
		//convert entry to a list of sets
		
		ArrayList<TreeSet<String>> result = new ArrayList<>();
		for (String temp: entry){
			result.add(sToSet(temp));
		}
		//boolean = true
		boolean Switch = true;
        while (Switch){
			//boolean=false if we find 2 pairs that need to merge then we set it back equal to true so it'll do it again
			Switch = false;
			//for i = 0, size, i++
			for(int i=0; i< result.size(); i++){
				//for j= i+1 -> size j++
				for (int j= i+1; j< result.size(); j++){
					//if (numInCommon(result.get(i),result.get(j))>=2)
					if(numInCommon(result.get(i),result.get(j))>=2){
						//merge the sets (with union) and set equal to a new set
						TreeSet<String> newSet = union(result.get(i), result.get(j));
						//remove j
						result.remove(j);
						//remove i
						result.remove(i);
						//add new set at index i
						result.add(i,newSet);
						// j --
						j--;
						//set boolean equal to true so it'll check all the sets again
						Switch = true;
						
					}
					
				}	
			}
	
        }
		int count = 0;
		String[] ArrayString = new String[result.size()];
		for(int k = 0; k<result.size(); k++){
			ArrayString[k] = collToS(result.get(k));
		}	
		// Turn the arraylist of strings into a String array 
		
		Arrays.sort(ArrayString);// alphabetical order
//		

		return ArrayString;
	}

	public static void main(String[] args) {
		Thesaurus t = new Thesaurus();
		String[] input = { "ape monkey wrench", "wrench twist strain", "monkey twist frugue strain" };
		System.out.println(t.edit(input));
		// More Tests
		String[] input2 = {"Ivory Maegan Chelsea", "Sam Symonne Hiba", "Chelsea Chad Dylan", "Ivory Chad Dylan"};
		System.out.println(t.edit(input2));
		String[] input3 = {"Ivory Maegan josh", "Maegan Ivory Hiba", "Hiba Chad Bryan", "Ivory Josh Dylan"};
		System.out.println(t.edit(input3));
		
	}
}
