package solutions.java;

import java.util.HashMap;
import java.util.Map;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/decode-ways/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 7, 2025
 	- `Answer`: numDecodingsDfs / numDecodingsDp / numDecodingsDpAdvanced
 # Reference
	- https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/doc/reference/DecodeWays.png
 */
public class DecodeWays {
	
	public static void main(String[] args) {
		
		System.out.println(numDecodingsDp("12")); // 2
		System.out.println(numDecodingsDp("226")); // 3
		System.out.println(numDecodingsDp("06")); // 0
		System.out.println(numDecodingsDp("10")); // 1
		System.out.println(numDecodingsDp("102")); // 2
		
		System.out.println("---");
		System.out.println(numDecodingsDpAdvanced("12")); // 2
		System.out.println(numDecodingsDpAdvanced("226")); // 3
		System.out.println(numDecodingsDpAdvanced("06")); // 0
		System.out.println(numDecodingsDpAdvanced("10")); // 1
		System.out.println(numDecodingsDpAdvanced("102")); // 2
	}

    /*
    # Option #1
    - DFS Recursive + Memoization
    - O(n)
     */
    public int numDecodingsDfs(String s) {
        Map<Integer, Integer> memo = new HashMap<>();
        return dfs(s, 0, memo);
    }

    private int dfs(String s, int index, Map<Integer, Integer> memo) {
        int n = s.length();
        if (index == n) return 1;
        if (s.charAt(index) == '0') return 0;
        if (memo.containsKey(index)) return memo.get(index);

        int res = dfs(s, index + 1, memo);

        if (index + 1 < n) {
            int twoDigits = Integer.parseInt(s.substring(index, index + 2));
            if (twoDigits <= 26) {
                res += dfs(s, index + 2, memo);
            }
        }

        memo.put(index, res);
        return res;
    }

    /*
    # Option #2
    - Dynamic Programming
    - Integer Conversion
    - O(n)
     */
    public static int numDecodingsDp(String s) {
    	
    	if (s.startsWith("0")) return 0;
    	
    	int sLength = s.length();
    	if (sLength == 1) return 1;

    	int[] dp = new int[sLength + 1];
    	dp[0] = 1; // Empty string -> doing nothing is also 1 way
    	dp[1] = 1; // If the length is 1, only 1 way
    	for (int i = 2; i < sLength + 1; i++) {
    		
    		int targetNum = Integer.parseInt(s.substring(i - 2, i));
    		int lastDigit = targetNum % 10;
    		
    		// If the second digit is valid (1-9), add ways from dp[i - 1]
    		// Shouldn't be 0
    		if (lastDigit >= 1 && lastDigit <= 9) {
    			dp[i] += dp[i - 1];
    		}
    		
    		// If the target number is valid (10-26), add ways from dp[i - 2]
    		if (targetNum >= 10 && targetNum <= 26) {
    			dp[i] += dp[i - 2];
    		}
    	}
    	
    	return dp[sLength];
    }
    
    

    /*
    # Option #3
    - Dynamic Programming
    - 3 Variables & Only String Comparison
    - O(n)
     */
    public static int numDecodingsDpAdvanced(String s) {
        
        int n = s.length();
        char[] c = s.toCharArray();
        
        int curr = 0;
        int next = 1;
        int nextNext = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (c[i] == '0') {
                curr = 0;
            } else {
                curr = next;
            }

            if (i + 1 < n) {
                if (c[i] == '1' || (c[i] == '2' && ((int)c[i + 1] <= (int)'6' && (int)c[i+1] >= (int)'0'))) {
                    curr += nextNext;
                }
            }

            nextNext = next;
            next = curr;
        }

        return curr;
    }
}
