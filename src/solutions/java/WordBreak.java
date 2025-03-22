package solutions.java;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/word-break/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 22, 2025
 	- `Answer`: wordBreak
 */
public class WordBreak {

	public static void main(String[] args) {

	    System.out.println(wordBreak("leetcode", Arrays.asList("leet","code")) == true);
	    System.out.println(wordBreak("applepenapple", Arrays.asList("apple","pen")) == true);
	    System.out.println(wordBreak("catsandog", Arrays.asList("cats","dog","sand","and","cat")) == false);
	    System.out.println(wordBreak("cars", Arrays.asList("car","ca","rs")) == true);
	    System.out.println(wordBreak("ccbb", Arrays.asList("bc","cb")) == false);
	}
	
	/*
    # Option #1
    - Dynamic Programming
    - Optimized Bottom Up
    - O(n * m) (m: max word Lenth)
	 */
    public static boolean wordBreak(String s, List<String> wordDict) {
        // Convert the word list into a HashSet for fast lookup (O(1) average time)
    	Set<String> wordSet = new HashSet<>(wordDict);
    	
    	// Find the maximum word length in the dictionary
    	// This helps us avoid unnecessary substring checks
    	int maxWordLength = 0;
    	for (String word: wordDict) {
    		maxWordLength = Math.max(maxWordLength, word.length());
    	}
    	
    	// dp[i] will be true if s[0..i] can be segmented into valid dictionary words
    	boolean[] dp = new boolean[s.length() + 1];
    	dp[0] = true; // Base case: empty string is always segmentable
    	
    	// Loop through all possible substring end positions
    	for (int i = 1; i <= s.length(); i++) {
    		// Only check substrings up to maxWordLength characters long
    		for (int j = 1; j <= maxWordLength && j <= i; j++) {
    			// If s[0..i-j] cannot be segmented, skip this check
    			if (!dp[i - j]) continue;
    			
    			// Check if the current substring is in the dictionary
    			String sub = s.substring(i - j, i);
    			if (wordSet.contains(sub)) {
    				dp[i] = true;
    				break; // Found a valid segmentation, no need to check longer substtrings
    			}
    		}
    	}
    	
    	// The final value tells us if the entire string can be segmented
    	return dp[s.length()];
    }
}
