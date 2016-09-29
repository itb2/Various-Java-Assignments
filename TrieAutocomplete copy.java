import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;

import jdk.nashorn.internal.ir.ReturnNode;

/**
 * General trie/priority queue algorithm for implementing Autocompletor
 * 
 * @author Austin Lu
 *
 */
public class TrieAutocomplete implements Autocompletor {

	/**
	 * Root of entire trie
	 */
	protected Node myRoot;

	/**
	 * Constructor method for TrieAutocomplete. Should initialize the trie
	 * rooted at myRoot, as well as add all nodes necessary to represent the
	 * words in terms.
	 * 
	 * @param terms
	 *            - The words we will autocomplete from
	 * @param weights
	 *            - Their weights, such that terms[i] has weight weights[i].
	 * @throws a
	 *             NullPointerException if either argument is null
	 */
	public TrieAutocomplete(String[] terms, double[] weights) {
		if (terms == null || weights == null)
			throw new NullPointerException("One or more arguments null");
		// Represent the root as a dummy/placeholder node
		myRoot = new Node('-', null, 0);

		for (int i = 0; i < terms.length; i++) {
			add(terms[i], weights[i]);
		}
	}

	/**
	 * Add the word with given weight to the trie. If word already exists in the
	 * trie, no new nodes should be created, but the weight of word should be
	 * updated.
	 * 
	 * In adding a word, this method should do the following: Create any
	 * necessary intermediate nodes if they do not exist. Update the
	 * subtreeMaxWeight of all nodes in the path from root to the node
	 * representing word. Set the value of myWord, myWeight, isWord, and
	 * mySubtreeMaxWeight of the node corresponding to the added word to the
	 * correct values
	 * 
	 * @throws a
	 *             NullPointerException if word is null
	 * @throws an
	 *             IllegalArgumentException if weight is negative.
	 * 
	 */
	private void add(String word, double weight) {
		
		
		if(word == null){ throw new NullPointerException("word is null");
		}else if(weight < 0){ throw new IllegalArgumentException("weight less than zero");
		
		}else{ 
			if(word != ""){
			Node current = this.myRoot;
			int len = word.length();
		
			for (int i = 0; i<len; i++){
				if(current.mySubtreeMaxWeight < weight) current.mySubtreeMaxWeight = weight;
				if(!current.children.containsKey(word.charAt(i))){
					current.children.put(word.charAt(i), new Node(word.charAt(i), current, weight));
				}
				current = current.children.get(word.charAt(i));
				
			}
			current.setWeight(weight);
			current.isWord = true;
			current.setWord(word);
			if(current.mySubtreeMaxWeight < weight){
				current.mySubtreeMaxWeight = weight;
			}
			if(current.mySubtreeMaxWeight > weight){
				double oldWeight = current.mySubtreeMaxWeight;
				current = current.parent;
				while(current.mySubtreeMaxWeight == oldWeight){
					current.mySubtreeMaxWeight = weight;
					current = current.parent;
				
				}
			}
			}
		}
		
	}

	@Override
	/**
	 * Required by the Autocompletor interface. Returns an array containing the
	 * k words in the trie with the largest weight which match the given prefix,
	 * in descending weight order. If less than k words exist matching the given
	 * prefix (including if no words exist), then the array instead contains all
	 * those words. e.g. If terms is {air:3, bat:2, bell:4, boy:1}, then
	 * topKMatches("b", 2) should return {"bell", "bat"}, but topKMatches("a",
	 * 2) should return {"air"}
	 * 
	 * @param prefix
	 *            - A prefix which all returned words must start with
	 * @param k
	 *            - The (maximum) number of words to be returned
	 * @return An array of the k words with the largest weights among all words
	 *         starting with prefix, in descending weight order. If less than k
	 *         such words exist, return an array containing all those words If
	 *         no such words exist, return an empty array
	 * @throws a
	 *             NullPointerException if prefix is null
	 */
	public String[] topKMatches(String prefix, int k) {
		
		if (prefix == null) throw new NullPointerException();
		int len = prefix.length();
		
	
		Node current = this.myRoot;
		
		for (int i = 0; i < len; i++) {
			Character charr = prefix.charAt(i);
			if (current.children.containsKey(charr)) {
				current = current.children.get(charr);
			} else {
				return new String[0];
			}

		}
		PriorityQueue<Node> pq = new PriorityQueue<Node>(k, new Node.ReverseSubtreeMaxWeightComparator());
		ArrayList<Term> list = new ArrayList<Term>();
		pq.add(current);
		
		while(!pq.isEmpty()){
			Node temp = pq.remove();
			
			if(temp.isWord){
				Term nodeterm = new Term(temp.myWord,temp.myWeight);
				list.add(nodeterm);
				Collections.sort(list, new Term.WeightOrder());
				if(list.size() == k && temp.mySubtreeMaxWeight < (list.get(k-1)).getWeight()){   
					break;
				}
			}

			for( Node x: temp.children.values()){
				pq.add(x);
			}
		}
		
		Collections.sort(list, new Term.ReverseWeightOrder());
	    int numResults = Math.min(k, list.size());
	    String[] ret = new String[numResults];
	    for (int b = 0; b < numResults; b++) {
	      ret[b] = list.get(b).getWord();
	    }
	    
	   
		return ret;
	}

	@Override
	/**
	 * Given a prefix, returns the largest-weight word in the trie starting with
	 * that prefix.
	 * 
	 * @param prefix
	 *            - the prefix the returned word should start with
	 * @return The word from _terms with the largest weight starting with
	 *         prefix, or an empty string if none exists
	 * @throws a
	 *             NullPointerException if the prefix is null
	 * 
	 */
	public String topMatch(String prefix) {
		String pre = prefix;
		if (pre == null)
			throw new NullPointerException();

		Node current = this.myRoot;
		
		int len = pre.length();
		for (int i = 0; i < len; i++) {
			Character charr = pre.charAt(i);
			if (current.children.containsKey(charr)) {
				current = current.children.get(charr);
			} else {
				return "";
			}

		}

		while (current.mySubtreeMaxWeight != current.myWeight) {
			for (Node n : current.children.values()) {
				
				if (n.myWeight == current.mySubtreeMaxWeight) {
					current = n;
					return current.myWord;
				} else if (n.mySubtreeMaxWeight == current.mySubtreeMaxWeight) {
					current = n;
					break;
				}
			}

		}

		return current.myWord;
	}
		
}
