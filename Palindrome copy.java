inr
public class Palindrome {
	public static boolean isPalindrome(String paly){
		if (paly == null){
			return false;
		}
		String Str = paly.toLowerCase();
		StringBuilder testString = new StringBuilder();
		for (int i =0; i< Str.length(); i++){
			if (Character.isLetter(Str.charAt(i))){
				testString.append(Str.charAt(i));
			}	
		}
		String oldString = testString.toString();
		String newString = testString.reverse().toString();
		if(newString.compareTo(oldString) == 0){
			System.out.println(oldString);
			System.out.println(newString);
			System.out.println("true");
			return true;
		}
		System.out.println(oldString);
		System.out.println(newString);
		System.out.println("false");
		return false;
	}
	public static void main(String[] args){
		isPalindrome("");
		isPalindrome("bbcc");
		isPalindrome("b'bbb");
		isPalindrome("Bbbb");
	}
}
