package solutions.java;

/*
# Problem
	- `Link`: https://leetcode.com/problems/palindromic-substrings/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 20
	- `Answer`: countSubstrings
 */
public class PalindromicSubstrings {

	/*
	# Option #1
	- O(n*2)
	 */
    public static int countSubstrings(String s) {
    	int sLength = s.length();
    	int result = sLength;
    	char[] charArray = s.toCharArray();
    	int left = 0, right = 0;
    	for (int i = 1; i < sLength; i++) {
    		
    		left = i - 1;
    		right = i + 1;
    		while (left >= 0 && right < sLength && charArray[left] == charArray[right]) {
    			result++;
    			left--;
    			right++;
    		}
    		
    		left = i - 1;
    		right = i;
    		while (left >= 0 && right < sLength && charArray[left] == charArray[right]) {
    			result++;
    			left--;
    			right++;
    		}
    	}
    	return result;
    }
}
