import java.lang.reflect.Array;
import java.util.*;

public class SyllableSorting {
	 
	 public String[] sortWords(String[] words) {
         // you write code here
		 String[] originals = new String[words.length];
		 String[] vowels = {"a","e","i","o","u"};
		 String[] syllables = new String[words.length];
		 int index = 0;
		 for(String i: words) {
			 originals[index] = i;
			 
			 for (int m=0; m<i.length(); m++){
				for( String v: vowels){
					if ((i.charAt(m))  == v.charAt(0)){
						i = i.substring(0,m+1)+","+ i.substring(m+1);
					}
				}	
			 }
			 
			 String[] b= i.split(",");
			 Arrays.sort(b);
			 String combined= "";
			 for(String n: b){
				 combined += n;
				 
			 }
			 syllables[index] = combined;
			 index++;
		 }
		 
		 String[] result = new String[syllables.length];
		
		 for(int i =0; i<syllables.length;i++){
			 for (int y= 0; y< syllables.length;y++){
				 if(result[y] == null || result[y].compareTo(syllables[i]) > 0){
					result[y] = syllables[i]; 
				 }
			 }
		 }
		 return result;
      }
	
}
