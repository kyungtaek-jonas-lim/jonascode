package solutions.java;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/decode-ways/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 31, 2025
 	- `Answer`: numDecodings
 */
public class DecodeWays {
	
	public static void main(String[] args) {
		
		System.out.println(numDecodings("12")); // 2
		System.out.println(numDecodings("226")); // 3
		System.out.println(numDecodings("06")); // 0
		System.out.println(numDecodings("10")); // 1
		System.out.println(numDecodings("102")); // 2
	}

    public static int numDecodings(String s) {
    	
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
}
