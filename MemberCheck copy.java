import java.util.*;
public class MemberCheck {
// return the members who are dishonest about how many health club locations they have visited
//return type: String Array
	//Use for loop to loop through names in each string array and compare to see if that name is in one of the other two
	//Have to make sure I dont count the same name twice when I move on to next array and compare its values
	//if I use a set it shouldn't repeat values
	//Then I have to change the set into a string array and sort it in alphabetical order
	//Stuff that ben suggested sounded maybe easier - put all names from all the different arrays in a new array and loop around that
	// loop through names and count occurence then leave the ones that occur more than once and delete the ones that occur only once
	//then delete repition among the violators (using a set) and return that as a string array
	public String[] whosDishonest(String[] club1, String[] club2, String[] club3) {
	     TreeSet<String> c1 = new TreeSet<String>();
	     TreeSet<String> c2 = new TreeSet<String>();
	     TreeSet<String> c3 = new TreeSet<String>();
	     TreeSet<String> violators = new TreeSet<String>();
	     for(String temp: club1){
	    	 c1.add(temp);
	     }
	     for(String temp: club2){
	    	 c2.add(temp);
	     }
	     for(String temp: club3){
	    	 c3.add(temp);
	     }  
	    violators.addAll(c1);
	    violators.retainAll(c2);
	    
	    for(String temp: c1){
	    	if (c3.contains(temp)){
	    		violators.add(temp);
	    	}
	    }
	    for(String temp: c2){
	    	if (c3.contains(temp)){
	    		violators.add(temp);
	    	}
	    }
	    
	    String[] multiples = new String[violators.size()];	 
	    int i = 0;
	    for (String temp: violators){
	    	multiples[i] = temp;
	    	i++;
	    }
	    
	  Arrays.sort(multiples);
	  
	  return multiples;
	    
	 }


}
