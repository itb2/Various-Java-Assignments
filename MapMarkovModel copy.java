import java.util.*;



public class MapMarkovModel extends AbstractModel{
	
	
	    protected String myString;
	    protected Random myRandom;
	    public static final int DEFAULT_COUNT = 100; // default # random letters generated
	    public static int RANDOM_SEED = 1234; 
	    
	    
	    public MapMarkovModel() {
	        myRandom = new Random(RANDOM_SEED);
	    }
	  
	    public void initialize(Scanner s) {
	        double start = System.currentTimeMillis();
	        int count = readChars(s);
	        double end = System.currentTimeMillis();
	        double time = (end - start) / 1000.0;
	        super.messageViews("#read: " + count + " chars in: " + time + " secs");
	    }
	    
	   
	    protected int readChars(Scanner s) {
	        myString = s.useDelimiter("\\Z").next();
	        s.close();
	        return myString.length();
	    }
	    
	   
	    public void process(Object o) {
	        String temp = (String) o;
	        String[] nums = temp.split("\\s+");
	        int k = Integer.parseInt(nums[0]);
	        int maxLetters = DEFAULT_COUNT;
	        if (nums.length > 1) {
	            maxLetters = Integer.parseInt(nums[1]);
	        }
	        
	        double stime = System.currentTimeMillis();
	        String text = makeNGram(k, maxLetters);
	        double etime = System.currentTimeMillis();
	        double time = (etime - stime) / 1000.0;
	        this.messageViews("time to generate: " + time +" | chars generated:" + 
	        		text.length()); //For benchmarking purposes
	        this.notifyViews(text);
	        
	    }
	   
	    //Edit this to make the nGrams
	    
	    protected String makeNGram(int k, int maxLetters){
			//This method is similar to that of MarkovModel but it uses maps to make it faster
	    	//Create an empty hash map
	    	TreeMap<String, ArrayList<Character>> gramMap = new TreeMap<String, ArrayList<Character>>();
	 
	    
	    	//loop through my string (while or for?) maybe while indexcount+ k <= length of mystring
	    	for(int i = 0; i <= myString.length()-k; i++){
	    		//if this nGram is not a key of the map
	    		//create string for current nGram
	    		String currentNgram = myString.substring(i, i + k);;
	    		
	    		
	    		if (!gramMap.containsKey(currentNgram)){ //if key isnt already in map
	    			ArrayList<Character> valueList = new ArrayList<Character>();	
	    			
	    			if(i + k >= myString.length()){
	    				//add int as char 0 to an arraylist
	    				valueList.add((char) 0);
	    				
	    				System.out.println(valueList.toString() + "when 0 is added");//DELETE THIS LATER 
	    			}
		    		//else
	    			else{
	    				//add this k-letter ngram to value arraylist of current nGram
	    				valueList.add(myString.charAt(i + k));
	    				
	    				System.out.println(valueList.toString() + "WHEN ADDING"); //DELETE THIS LATER
	    			}
	    			
	    			gramMap.put(currentNgram, valueList );
	    			
	    			
	    		}
	    		else{
	    			
		    	   
		    		//IF indexcount+ k >= length of mystring
	    			
	    			if (i + k >= myString.length()){
	    				
		    			//add int as char 0 to the existing arraylist value of current nGram
	    				gramMap.get(currentNgram).add((char) 0);
	    				
	    			}
	    			//else
	    			else{
		    			//add this k-letter ngram to value list of current nGram
		   
		    			gramMap.get(currentNgram).add(myString.charAt(i + k));
	    				
	    				
	    				//System.out.println(Arrays.toString(valueList) + "WHEN ADDING TO CURRENT LIST"); //DELETE THIS LATER
	    			}
	    			//move forward one index
	    			
		
	    		}
	    		//set this nGram equal to the current nGram
	    		System.out.println(currentNgram);//DELETE THIS LATER
	    		
	    	}
	    	//AT THE END OF THIS I SHOULD HAVE A HASHMAP WITH ALL OF THE NGRAMS AND POSSIBLE VALUES
	    	System.out.println(gramMap);//DELETE THIS LATER
	    	
	    	//Pick a random Starting index in myString
	    	int start = myRandom.nextInt(myString.length() - k + 1);
	    	//Use that starting index and k to pick my starting nGram
	    	String startingGram = myString.substring(start, start+k);
	    	//add that nGram to a result string
	    	StringBuilder resultString = new StringBuilder();
	    	resultString.append(startingGram);
	    	//while the length of the result string is less than maxLetters
	    	
	  
	    	while (resultString.length() < maxLetters){
		    	
	    		//Find starting nGram in my map
	    		
		    	//randomly choose a resulting ngram from my list of possible resulting nGrams
	    		int pick = myRandom.nextInt((gramMap.get(startingGram)).size());
	            Character resultingNgram = (gramMap.get(startingGram)).get(pick);
		    	//if resulting n gram = null (EOF indicator)
	            if (resultingNgram == 0){
	            	
	            	return resultString.toString();
	            }
		    	
		    	//if not then add LAST CHARACTER of the resulting nGram to the result string
	            resultString.append(resultingNgram);
	          //set starting nGram = resulting ngram
	            startingGram =  startingGram.substring(1) + resultingNgram;
	            
	            System.out.println("This is your resultString changing: " +resultString.toString()); //DELETE THIS LATER
	    		
	            
	    	}
	    	//return result string
	    	
	    	System.out.println("This is your resultString: " +resultString.toString());//DELETE THIS LATER
	    	return resultString.toString();
		}

	    //HOW TO SAVE MAP AS AN INSTANCE VARIABLE SO THAT IT ISNT CALLED MORE THAN ONCE IF THE TEST STRING ISNT CHANGED?
}
