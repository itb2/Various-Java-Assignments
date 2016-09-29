package test; 

public class Test {
	public static void main(String[] args){
		String s = new String("y");
		String p = new String("y");
		System.out.println(s==p);// Are they the same thing?
		System.out.println(s!=p); //Are they not the same thing?
		System.out.println(s.equals(p));//Are they equal?
		
		//So != means that they are NOT THE SAME BOX! But they have the same contents 
		//in this case.
		//If it was s != "y" it would be false because they are the same! but p and s
		//are different variables.
		
	}	
}
