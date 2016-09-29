/**
 * Abstraction Comments: What does this program do?
 * This program returns the reverse of the DNA string
 * @author Ivoryanna Brown
 */
public class DNAReverse {
	public String reverse(String dna) {
		String reversestring = "";
		for(int i= 0; i < dna.length(); i++){
			reversestring = dna.charAt(i) + reversestring; // Add the character i in dna to the front of the string
		}
		return reversestring;
	}
}
// charAt(int)