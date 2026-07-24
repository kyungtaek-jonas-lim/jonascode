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
 	- `Answer`: wordBreakDfs1 / wordBreakDfs2 / wordBreakDp1 / wordBreakDp2 / wordBreakDp3 / wordBreakDp4
 */
public class WordBreak {

	public static void main(String[] args) {

	    System.out.println(wordBreakDfs1("leetcode", Arrays.asList("leet","code")) == true);
	    System.out.println(wordBreakDfs1("applepenapple", Arrays.asList("apple","pen")) == true);
	    System.out.println(wordBreakDfs1("catsandog", Arrays.asList("cats","dog","sand","and","cat")) == false);
	    System.out.println(wordBreakDfs1("cars", Arrays.asList("car","ca","rs")) == true);
	    System.out.println(wordBreakDfs1("ccbb", Arrays.asList("bc","cb")) == false);
	    
	    System.out.println("---");
	    System.out.println(wordBreakDp1("leetcode", Arrays.asList("leet","code")) == true);
	    System.out.println(wordBreakDp1("applepenapple", Arrays.asList("apple","pen")) == true);
	    System.out.println(wordBreakDp1("catsandog", Arrays.asList("cats","dog","sand","and","cat")) == false);
	    System.out.println(wordBreakDp1("cars", Arrays.asList("car","ca","rs")) == true);
	    System.out.println(wordBreakDp1("ccbb", Arrays.asList("bc","cb")) == false);
	}
	
	/*
    # Option #1
    - DFS + Memoization
    - O(n * k * L) (n = len(s), k = number of words in wordDict, L = average length of the words in wordDict)
	 */
    public static boolean wordBreakDfs1(String s, List<String> wordDict) {
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
    - DFS + Memoization
    - O(n * k * L) (n = len(s), k = number of words in wordDict, L = average length of the words in wordDict)
	- July 22, 2026
	 */
    public boolean wordBreakDfs2(String s, List<String> wordDict) {
        Set<String> memo = new HashSet<>();
        return dfs(s, wordDict, memo);
    }

    private boolean dfs(String x, List<String> wd, Set<String> memo) {
        if (x.isEmpty()) return true;
        if (memo.contains(x)) return false;

        for (String w: wd) {
            if (x.startsWith(w)) {
                if (dfs(x.substring(w.length()), wd, memo)) return true;
            }
        }

        memo.add(x);
        return false;
    }

	
	/*
    # Option #3
    - Dynamic Programming
    - Optimized Bottom Up
    - O(n * k * L) (n = len(s), k = number of words in wordDict, L = average length of the words in wordDict)
    - ref: Neetcode (https://www.youtube.com/watch?v=Sx9NNgInc3A)
	 */
    public static boolean wordBreakDp1(String s, List<String> wordDict) {
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
    # Option #4
    - Dynamic Programming
    - Optimized Bottom Up
    - O(n * m) (m: max word Lenth)
	 */
    public static boolean wordBreakDp2(String s, List<String> wordDict) {
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
	
	/*
	# Option #5
	- Dynamic Programming
	- O(n × k × m) (n = len(s), k = number of words in wordDict, L = average length of the words in wordDict)
	- Jan 13, 2026
	 */
    public boolean wordBreakDp3(String s, List<String> wordDict) {
        final int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        
        int i = 0;
        while (i < n) {
            if (!dp[i]) {
                i++;
                continue;
            }

            for (String word: wordDict) {
                int m = word.length();
                if (n >= i + m && s.substring(i, i + m).equals(word)) {
                    dp[i + m] = true;
                }
            }

            i++;
        }

        return dp[n];
    }
	
	/*
	# Option #6
	- Dynamic Programming
	- O(n × k × m) (n = len(s), k = number of words in wordDict, L = average length of the words in wordDict)
	- July 22, 2026
	 */
    public boolean wordBreakDp4(String s, List<String> wordDict) {
        final int n = s.length();
        boolean[] dp = new boolean[n + 1];
        Arrays.fill(dp, false);
        dp[0] = true;
        
        for (int i = 0; i < n; i++) {
            if (!dp[i]) continue;
            for (String w: wordDict) {
                if (s.substring(i).startsWith(w)) dp[i + w.length()] = true;
            }
        }

        return dp[n];
    }
}
