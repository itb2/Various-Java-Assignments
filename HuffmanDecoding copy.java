
public class HuffmanDecoding {
	public String decode(String archive, String[] dictionary){
		int start= 0;
		int end = 1;	
		StringBuilder answer = new StringBuilder();
		String substr = "";
		
		while(end <= archive.length()){
			
			substr = archive.substring(start, end);
			int strLen = 0;
			
			for(int i = 0; i< dictionary.length;i++){
				if(substr.equals(dictionary[i])){
					answer.append(String.valueOf((char)(i+65)));	
					strLen++;
				}
			}
			
			if (strLen == 0){
				end += 1;
			}else{
				start = end;
				end += 1;
				
			}
			
		}
		return answer.toString();
	}
	
}
