package solutions.java;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/decode-ways/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 7, 2025
 	- `Answer`: numDecodings / numDecodingsAdvanced
 */
public class DecodeWays {
	
	public static void main(String[] args) {
		
		System.out.println(numDecodings("12")); // 2
		System.out.println(numDecodings("226")); // 3
		System.out.println(numDecodings("06")); // 0
		System.out.println(numDecodings("10")); // 1
		System.out.println(numDecodings("102")); // 2
		
		System.out.println("---");
		System.out.println(numDecodingsAdvanced("12")); // 2
		System.out.println(numDecodingsAdvanced("226")); // 3
		System.out.println(numDecodingsAdvanced("06")); // 0
		System.out.println(numDecodingsAdvanced("10")); // 1
		System.out.println(numDecodingsAdvanced("102")); // 2
	}

    /*
    # Option #1
    - Dynamic Programming
    - Integer Conversion
    - O(n)
     */
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
    
    

    /*
    # Option #2
    - Dynamic Programming
    - Only String Comparison
    - O(n)
    - ref: https://www.youtube.com/watch?v=6aEyTjOwlJU
     */
    public static int numDecodingsAdvanced(String s) {
    	
    	/*
        #1
        Start from the last character and move backward.

        #2
        Add the number of ways from dp[i + 1] if the current digit is between 1 and 9 (inclusive).

        #3
        Add the number of ways from dp[i + 2] if the two-digit number formed by s[i] and s[i + 1] is valid:
            - If s[i] is '1', s[i + 1] can be any digit (i.e., '10' to '19')
            - If s[i] is '2', s[i + 1] must be between '0' and '6' (i.e., '20' to '26')
    	 */
    	int sLength = s.length();
    	int[] dp = new int[sLength + 1];
    	dp[sLength] = 1;
    	
    	for (int i = sLength - 1; i >= 0; i--) {
    		char current = s.charAt(i);
    		if (current == '0') continue;
    		
    		dp[i] += dp[i + 1];
    		
    		if (i + 1 < sLength) {
                char next = s.charAt(i + 1);
    			if (current == '1' || (current == '2' && next >= '0' && next <= '6')) {
    				dp[i] += dp[i + 2];
    			}
    		}
    	}
    	
    	return dp[0];
    }
}
