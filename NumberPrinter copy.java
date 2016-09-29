import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class NumberPrinter {
	
	public static void printSmallNumbers(Scanner source){
		while(source.hasNextInt()){
			while(!source.hasNextInt() && source.hasNext()){
				if (source.next().equals("\\(*")){
					source.useDelimiter("*\\)");
					source.next();
					source.useDelimiter("\\s+");
				}
				
			}
			if (source.hasNext()){
				int x = source.nextInt();
				if (x<=5){
					System.out.println(x);
				}
			}
			//if (source.nextInt() <= 5){
				//System.out.println(source.nextInt());
			//}  THIS WAS THE CODE ORIGINALLY
		}
	}
	
	public static void main(String[] args){
		try {
			Scanner input = new Scanner(new File("data/input2.txt"));
			printSmallNumbers(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
//with input 1 it doesnt print out 7 because next int makes it skip that and it doesnt get printed ?
//also six shouldnt get printed.. because its more than 5 - 
//so stores source.nextInt before if statement so it wont skip numbers

// with input 2 it doesnt run because we are calling next int and the next thing is not an integer.
//