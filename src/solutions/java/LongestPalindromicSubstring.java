package solutions.java;

/*
# Problem
	- `Link`: https://leetcode.com/problems/longest-palindromic-substring/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 17
	- `Answer`: longestPalindrome
 */
public class LongestPalindromicSubstring {
	
	public static void main(String[] args) {
		System.out.println(longestPalindrome("babad"));
		System.out.println(longestPalindrome("cbbd"));
		System.out.println(longestPalindrome("bb"));
		System.out.println(longestPalindrome("ccc"));
	}
	
	/*
    # Option #1
    - Two Pointers & Devide Even and Odd cases
    - O(n^2)
    - ref: https://www.youtube.com/watch?v=XYQecbcd6_c
	 */
    public static String longestPalindrome(String s) {
    	
    	int sLength = s.length();
    	char[] charArray = s.toCharArray();
		int left = -1, right = -1;
    	
    	String result = "" + charArray[0];
    	int resultLength = 1;
    	for (int i = 1; i < sLength; i++) {
    		
    		// odd cases
    		left = i;
    		right = i;
    		while(left >= 0 && right < sLength && charArray[left] == charArray[right]) {
    			if (right - left + 1 > resultLength) {
    				result = s.substring(left, right + 1);
    				resultLength = right - left + 1;
    			}
    			left--;
    			right++;
    		}
    		
    		// even cases
    		left = i - 1;
    		right = i;
    		while(left >= 0 && right < sLength && charArray[left] == charArray[right]) {
    			if (right - left + 1 > resultLength) {
    				result = s.substring(left, right + 1);
    				resultLength = right - left + 1;
    			}
    			left--;
    			right++;
    		}
    	}
    	
    	return result;
    }
}
