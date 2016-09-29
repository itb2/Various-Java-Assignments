import java.util.*;


public class WordMarkovModel extends AbstractModel{
	protected String myString;
    protected Random myRandom;
    public static final int DEFAULT_COUNT = 100; // default # random letters generated
    public static int RANDOM_SEED = 1234; 
    
	 public WordMarkovModel() {
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
	    	TreeMap<WordNgram, ArrayList<WordNgram>> gramMap = new TreeMap<WordNgram, ArrayList<WordNgram>>();
	    	String[] words = myString.split("\\s+");
	    
	    	//loop through my string (while or for?) maybe while indexcount+ k <= length of mystring
	    	for(int i = 0; i <= words.length-k; i++){
	    		//if this nGram is not a key of the map
	    	
	    		WordNgram currentNgram = new WordNgram(words, i, i+k);
	    		
	
	    		if (!gramMap.containsKey(currentNgram)){ //if key isnt already in map
	    			ArrayList<WordNgram> valueList = new ArrayList<WordNgram>();	
	    			
	    			if(i + k >= words.length){
	    				//add null to an arraylist
	    				valueList.add(null);
	    				
	    				System.out.println(valueList.toString() + " when 0 is added");//DELETE THIS LATER 
	    			}
		    		//else
	    			else{
	    				//add this k-word ngram to value arraylist of current nGram
	    				valueList.add(new WordNgram(words, i, k));
	    				
	    				System.out.println(valueList.toString() + "WHEN ADDING"); //DELETE THIS LATER
	    			}
	    			
	    			gramMap.put(currentNgram, valueList );
	    			
	    			
	    		}
	    		else{
	    			
		    	   
		    		//IF indexcount+ k >= length of mystring
	    			
	    			if (i + k >= words.length){
		    			//add int as char 0 to the existing arraylist value of current nGram
	    				gramMap.get(currentNgram).add(null);	
	    			}
	    			//else
	    			else{
		    			//add the last word ngram to value list of current nGram
		   
		    			gramMap.get(currentNgram).add(new WordNgram(words, i, k));
	    			
	    			}
	    		}
	    		//set this nGram equal to the current nGram
	    		System.out.println(currentNgram.toString());//DELETE THIS LATER
	    		
	    	}
	    	//AT THE END OF THIS I SHOULD HAVE A HASHMAP WITH ALL OF THE NGRAMS AND POSSIBLE VALUES
	    	System.out.println(gramMap.toString());//DELETE THIS LATER
	    	
	    	//Pick a random Starting index in myString
	    	int start = myRandom.nextInt(words.length - k + 1);
	    	//Use that starting index and k to pick my starting nGram
	    	
	    	WordNgram startingGram = new WordNgram(words, start, start+k);
	    	//add that nGram to a result string
	    	
	    	StringBuilder resultString = new StringBuilder();
	    	resultString.append(startingGram);
	    	//while the length of the result string is less than maxLetters
	    	
	    	while (resultString.length() < maxLetters){
		    	
	    		//Find starting nGram in my map
	    		
		    	//randomly choose a resulting ngram from my list of possible resulting nGrams
	    		int pick = myRandom.nextInt((gramMap.get(startingGram)).size());
	            WordNgram resultingNgram = (gramMap.get(startingGram).get(pick));
		    	//if resulting n gram = null (EOF indicator)
	            if (resultingNgram == null){
	            	
	            	return resultString.toString();
	            }
		    	
		    	//if not then add LAST word of the resulting nGram to the result string
	            
	            resultString.append(" "+ resultingNgram);
	          //set starting nGram = resulting ngram
	            StringBuilder build = new StringBuilder();
	            String h = startingGram.toString();
	            
	            build.append(h + " " + resultingNgram);
	            
	            startingGram = new WordNgram((build.toString()).split("\\s+"), 1, k+1);
	          
	            System.out.println("This is your resultString changing: " +resultString.toString()); //DELETE THIS LATER
	    		
	            
	    	}
	    	//return result string
	    	
	    	System.out.println("This is your resultString: " +resultString.toString());//DELETE THIS LATER
	    	return resultString.toString();
		}

	    //HOW TO SAVE MAP AS AN INSTANCE VARIABLE SO THAT IT ISNT CALLED MORE THAN ONCE IF THE TEST STRING ISNT CHANGED?



}
