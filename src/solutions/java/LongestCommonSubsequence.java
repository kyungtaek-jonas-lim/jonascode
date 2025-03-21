package solutions.java;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/longest-common-subsequence/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 21, 2025
 	- `Answer`: longestCommonSubsequence
*/
public class LongestCommonSubsequence {
	
	public static void main(String[] args) {
		System.out.println(longestCommonSubsequence("abcde", "ace")); // 3
		System.out.println(longestCommonSubsequence("abc", "abc")); // 3
		System.out.println(longestCommonSubsequence("abc", "def")); // 0
		System.out.println(longestCommonSubsequence("ezupkr", "ubmrapg")); // 2
		System.out.println(longestCommonSubsequence("bsbininm", "jmjkbkjkv")); // 1
		System.out.println(longestCommonSubsequence("oxcpqrsvwf", "shmtulqrypy")); // 2
		System.out.println(longestCommonSubsequence("mhunuzqrkzsnidwbun", "szulspmhwpazoxijwbq")); // 6 ("mhziwb")
		System.out.println(longestCommonSubsequence("abc", "abbbbbc")); // 3
		System.out.println(longestCommonSubsequence("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")); // 210
	}

    
	// ====================================================================
	/*
	 * Option #1
	 * Dynamic Programming 
	 */
	public static int longestCommonSubsequence(String text1, String text2) {
		    // Store lengths of both input strings for reuse and efficiency
		    int text1Length = text1.length();
		    int text2Length = text2.length();

		    // Always ensure text1 is the longer string.
		    // This is important for space optimization when using 1D arrays:
		    // The shorter string determines the width of our DP array.
		    // If we reduce the DP array size to match the shorter string,
		    // we can minimize memory usage without affecting correctness.
		    if (text1Length < text2Length) {
		        return longestCommonSubsequence(text2, text1);
		    }

		    // Initialize two 1D arrays to simulate two rows of a 2D DP matrix.
		    // - 'prev' holds the previous row (i - 1)
		    // - 'curr' holds the current row (i)
		    // Why use two arrays?
		    // In LCS DP, each cell dp[i][j] only depends on:
		    //     dp[i-1][j], dp[i][j-1], and dp[i-1][j-1]
		    // So, only the current and previous rows are needed at any time.
		    int[] prev = new int[text2Length + 1];
		    int[] curr = new int[text2Length + 1];

		    // Use 1-based indexing in this loop for simplicity:
		    // This allows natural handling of base cases,
		    // where i == 0 or j == 0 corresponds to comparing with an empty string.
		    for (int i = 1; i <= text1Length; i++) {
		        for (int j = 1; j <= text2Length; j++) {
		            // Compare characters at (i - 1) and (j - 1) since strings are 0-based
		            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
		                // Characters match: extend the LCS by 1
		                // Take the diagonal value (prev[j - 1]) and add 1
		                curr[j] = prev[j - 1] + 1;
		            } else {
		                // Characters do not match:
		                // Take the maximum of:
		                //   - prev[j]   → LCS excluding text1[i - 1]
		                //   - curr[j-1] → LCS excluding text2[j - 1]
		                curr[j] = Math.max(prev[j], curr[j - 1]);
		            }
		        }

		        // Swap the current and previous arrays for the next iteration
		        // We don't copy values; we just swap the references.
		        // After swapping:
		        //   - 'prev' becomes the current row
		        //   - 'curr' becomes the new row to be filled
		        int[] temp = prev;
		        prev = curr;
		        curr = temp;
		    }

		    // The final LCS length is stored in prev[text2Length]
		    // because after the last iteration, 'prev' contains the last computed row.
		    return prev[text2Length];
		}

    
	
	// ====================================================================
	/*
	 * Option #2
	 * Time Limit Exceeded
	 */
//    public static int longestCommonSubsequence(String text1, String text2) {
//
//    	// Edge Cases
//    	if (text1.equals(text2)) return text1.length();
//    	if (text1.startsWith(text2)) return text2.length(); 
//    	else if (text2.startsWith(text1)) return text1.length();
//    	
//    	// Process
//        int result = process(text1, text2, 0);
//        return result;
//    }
//    public static int process(String start, String target, int waitingStartIndex) {
//        int result = 0;
//        int startIndex = 0;
//        do {
//
//            int waitingStartIndexTemp = waitingStartIndex;
//            waitingStartIndex = 0;
//            startIndex = 0;
//            int max = 0;
//            
//	    	for (int i = waitingStartIndexTemp; i < start.length(); i++) {
//	    		for (int j = startIndex; j < target.length(); j++) {
//	    			if (start.charAt(i) == target.charAt(j)) {
//	    				startIndex = j + 1;
//	    				max++;
//	    				
//	    				String startTemp = start.substring(0, i) + start.substring(i + 1);
//	    				result = Math.max(result, process(startTemp, target, waitingStartIndexTemp));
//	    				
//	    				if (waitingStartIndex == 0) {
//	    					waitingStartIndex = i + 1;
//	    				}
//	    				break;
//	    			}
//	    		}
//	    	}
//	    	result = Math.max(result, max);
//        } while (waitingStartIndex != 0);
//    	return result;
//    }

	
	// ====================================================================
	/*
	 * Option #3
	 * Time Limit Exceeded
	 */
//    public static int longestCommonSubsequence(String text1, String text2) {
//    	Map<String, Integer> memo = new HashMap<>();
//    	int result = process(text1, text2, 0, 0, 0, memo);
//        return result;
//    }
//    
//    
//    public static int process(String start, String target, int x, int y, int cnt, Map<String, Integer> memo) {
//    	
//    	// Memoization
//        String key = x + "," + y + "," + cnt;
//        if (memo.containsKey(key)) return memo.get(key);
//    	
//    	// Edge Cases
//        if (x >= start.length() || y >= target.length()) return cnt;
//        if (start.length() - x == target.length() - y && start.substring(x).equals(target.substring(y))) {
//            return cnt + start.length() - x;
//        }
//    	if (start.equals(target)) return cnt + start.length();
//    	if (start.startsWith(target) || start.endsWith(target)) return cnt + target.length();
//    	else if (target.startsWith(start) || target.endsWith(start)) return cnt + start.length();
//    	
//    	// Process
//    	int result = cnt;
//    	for (int i = x; i < start.length(); i++) {
//    		for (int j = y; j < target.length(); j++) {
//    			if (start.charAt(i) == target.charAt(j)) {
//
//    				// For Continuous String
//    				int iTemp = i + 1;
//    				int jTemp = j + 1;
//    				int cntTemp = cnt + 1;
//    				while (iTemp < start.length() && jTemp < target.length()) {
//    					if (start.charAt(iTemp) == target.charAt(jTemp)) {
//    						iTemp++;
//    						jTemp++;
//    						cntTemp++;
//    					} else break;
//    				}
//    				
//    				int max = process(start, target, iTemp, jTemp, cntTemp, memo);
//    				result = Math.max(result, max);
//    				break;
//    			}
//    		}
//    	}
//        memo.put(key, result);
//    	return result;
//    }
    
}
