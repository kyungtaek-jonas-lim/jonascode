package solutions.java;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/longest-common-subsequence/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 21, 2025
 	- `Answer`: longestCommonSubsequence / longestCommonSubsequence2d
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

		System.out.println("---");
		System.out.println(longestCommonSubsequence2d("abcde", "ace")); // 3
		System.out.println(longestCommonSubsequence2d("abc", "abc")); // 3
		System.out.println(longestCommonSubsequence2d("abc", "def")); // 0
		System.out.println(longestCommonSubsequence2d("ezupkr", "ubmrapg")); // 2
		System.out.println(longestCommonSubsequence2d("bsbininm", "jmjkbkjkv")); // 1
		System.out.println(longestCommonSubsequence2d("oxcpqrsvwf", "shmtulqrypy")); // 2
		System.out.println(longestCommonSubsequence2d("mhunuzqrkzsnidwbun", "szulspmhwpazoxijwbq")); // 6 ("mhziwb")
		System.out.println(longestCommonSubsequence2d("abc", "abbbbbc")); // 3
		System.out.println(longestCommonSubsequence2d("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")); // 210
	}

    
	// ====================================================================
	/*
	 * Option #1
	 * Dynamic Programming 
	 * 1D Array
	 * O (n * m)
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
	 * Dynamic Programming 
	 * 2D Array
	 * O (n * m)
	 */
    public static int longestCommonSubsequence2d(String text1, String text2) {
    	
    	// Get lengths of both input strings
    	int m = text1.length();
    	int n = text2.length();
    	
    	// Initialize a 2D DP table with (m+1) rows and (n+1) columns
    	// dp[i][j] will represent the length of LCS
    	int[][] dp = new int[m + 1][n + 1];
    	
    	// Build the DP table row by row
    	for (int i = 0; i < m; i++) { // Loop through characters in text1
    		for (int j = 0; j < n; j++) { // Loop through characters in text2
    			
    			// Characters match: extend the LCS by 1
    			// Use the value from the diagonal (previous characters)
    			if (text1.charAt(i) == text2.charAt(j)) dp[i + 1][j + 1] = dp[i][j] + 1;
    			
    			// Characters don't match:
    			// Take the maximum LCS length by either:
    			// - Skipping current character in text1 (dp[i][j + 1])
    			// - Skipping current character in text2 (dp[i + 1][j])
    			else dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
    		}
    	}
    	
    	// The final cell contains the length of the longest common subsequence
    	return dp[m][n];
    }
    
}
