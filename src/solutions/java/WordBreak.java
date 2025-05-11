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
 	- `Date`: Apr 6, 2025
 	- `Answer`: wordBreak / wordBreakBetter / wordBreakAdvanced
 */
public class WordBreak {

	public static void main(String[] args) {

	    System.out.println(wordBreak("leetcode", Arrays.asList("leet","code")) == true);
	    System.out.println(wordBreak("applepenapple", Arrays.asList("apple","pen")) == true);
	    System.out.println(wordBreak("catsandog", Arrays.asList("cats","dog","sand","and","cat")) == false);
	    System.out.println(wordBreak("cars", Arrays.asList("car","ca","rs")) == true);
	    System.out.println(wordBreak("ccbb", Arrays.asList("bc","cb")) == false);
	    
	    System.out.println("---");
	    System.out.println(wordBreakAdvanced("leetcode", Arrays.asList("leet","code")) == true);
	    System.out.println(wordBreakAdvanced("applepenapple", Arrays.asList("apple","pen")) == true);
	    System.out.println(wordBreakAdvanced("catsandog", Arrays.asList("cats","dog","sand","and","cat")) == false);
	    System.out.println(wordBreakAdvanced("cars", Arrays.asList("car","ca","rs")) == true);
	    System.out.println(wordBreakAdvanced("ccbb", Arrays.asList("bc","cb")) == false);
	}
	
	/*
    # Option #1
    - Dynamic Programming
    - DFS + Memoization
    - O(n * k * L) (n = len(s), k = number of words in wordDict, L = average length of the words in wordDict)
	 */
    public static boolean wordBreak(String s, List<String> wordDict) {
        int[] memo = new int[s.length()];
        return dfs(s, wordDict, 0, memo);
    }

    private static boolean dfs(String s, List<String> wordDict, int index, int[] memo) {

        if (s.length() == index) return true;
        if (memo[index] != 0) return memo[index] == 1 ? true : false;
        
        for (String word: wordDict) {
            int wordLength = word.length();
            if (s.length() - index < wordLength) continue;
            if (s.substring(index, index + wordLength).equals(word)) {
                if (dfs(s, wordDict, index + wordLength, memo)) {
                    memo[index] = 1;
                    return true;
                }
            }
        }

        memo[index] = -1;
        return false;
    }
	
	/*
    # Option #2
    - Dynamic Programming
    - Optimized Bottom Up
    - O(n * k * L) (n = len(s), k = number of words in wordDict, L = average length of the words in wordDict)
    - ref: Neetcode (https://www.youtube.com/watch?v=Sx9NNgInc3A)
	 */
    public static boolean wordBreakBetter(String s, List<String> wordDict) {
    	/*
        # 1
        Starting from the last index of the string, the code checks whether any word from wordDict matches the substring.
        
        # 2
        If a match is found, set the value of the boolean array with the string index to the value of 'array[index - word_length]'. (array[string_length] = True)

        # 3
        If array[index] is already True, no need to checks further for the 'index' (so break the inner loop).
        
        # 4
        return array[0] (It's True if the string can be segmented using words from wordDict.)
    	 */
    	
    	int sLength = s.length();
    	boolean[] dp = new boolean[sLength + 1];
    	dp[sLength] = true;
    	
    	for (int i = sLength - 1; i >= 0; i--) {
    		for (String w: wordDict) {
    			
    			// # 1
    			int wLength = w.length();
    			if ((i + wLength) <= sLength && s.substring(i, i + wLength).equals(w)) {
    				
    				// # 2
    				dp[i] = dp[i + wLength];
    			}
    			
    			// # 3
    			if (dp[i]) break;
    		}
    	}
    	
    	// # 4
    	return dp[0];
    }
	
	/*
    # Option #3
    - Dynamic Programming
    - Optimized Bottom Up
    - O(n * m) (m: max word Lenth)
	 */
    public static boolean wordBreakAdvanced(String s, List<String> wordDict) {
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
