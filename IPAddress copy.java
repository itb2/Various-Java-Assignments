import java.util.Arrays;
import java.util.Scanner;

public class IPAddress {
	public static String ipAddress(){ 
		Scanner sc = new Scanner(System.in);
		String given = sc.nextLine();
		sc.close();
		String[] lines = given.split("\\r?\\n");  //extending a string, 
		
		String hexis = "abcdefABCDEF0123456789";
		String digits = "0123456789";
		StringBuilder resultString = new StringBuilder();
		//int N;
		Boolean firstLine = true;
		for (String currentLine: lines){
			if
			
			if (firstLine){
				//N =   Integer.parseInt(currentLine);
				firstLine = false;
			}else{
				if (currentLine.indexOf(":") == -1){
					int returnVal1 = 0;
					String[] splitByDot = currentLine.split("\\.");
					
					if (splitByDot.length != 4){
							returnVal1++;
					}else{
						for (String each: splitByDot){
							for (int i = 0; i< each.length(); i++){
								if (digits.indexOf(each.charAt(i)) == -1){
									returnVal1++;
								}
							}
							int x = Integer.parseInt(each);
							if(x < 0 || x > 255){
								returnVal1++;
							}
						}
					}
					
					if (returnVal1==0 && resultString != null){
						resultString.append("\n"+"IPv4");
					}else if(returnVal1==0 && resultString == null){
						resultString.append("IPv4");
					}else if(returnVal1!=0 && resultString != null){
						resultString.append("\n"+"Neither");
					}else{resultString.append("Neither");}
					
				}else{
					String[] splitLine = currentLine.split(":");
					int returnVal2=0;
					for (String item: splitLine){
						if ( item.length() != 4 || splitLine.length != 8){
							returnVal2++;
					}else{
							for (int i =0; i< 4; i++){
								if(hexis.indexOf(item.charAt(i)) == -1){
									returnVal2++;
								}
							}
							
						}
					}
					if (returnVal2==0 && resultString != null){
						resultString.append("\n"+"IPv6");
					}else if(returnVal2==0 && resultString == null){
						resultString.append("IPv6");
					}else if(returnVal2!=0 && resultString != null){
						resultString.append("\n"+"Neither");
					}else{
						resultString.append("Neither");
					}
				}
			}
		}
		
		return resultString.toString();
	}
	public static void main(String[] args){
		ipAddress();
	}
	
}
