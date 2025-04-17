package solutions.java;

/*
# Problem
	- `Link`: https://leetcode.com/problems/valid-palindrome/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 17
	- `Answer`: isPalindrome / isPalindromeAdvanced
 */
public class ValidPalindrome {
	
	/*
	# Option #1
	- Compare valid strings(in order, in reverse) with builder
	- O(n)
	- Space Complexity: O(n)
	 */
    public static boolean isPalindrome(String s) {
        
    	// Convert to lowercase
    	s = s.toLowerCase();
    	
    	// Make a string with valid characters.
    	StringBuilder builder = new StringBuilder();
    	char[] charArray = s.toCharArray();
    	for (char c: charArray) {
    		if ((c >= (int)'0' && c <= (int)'9')
				|| (c >= (int)'a' && c <= (int)'z')
    				) {
    			builder.append(c);
    		}
    	}
    	
    	// If they are the same
    	return builder.toString().equals(builder.reverse().toString());
    }
	
	/*
	# Option #2
	- Two Pointers
	- O(n)
	- Space Complexity: O(1)
	 */
    public static boolean isPalindromeAdvanced(String s) {
    	
    	s = s.toLowerCase().trim();
    	if (s.isEmpty()) return true;
    	
    	int left = 0, right = s.length() - 1;
    	char[] charArray = s.toCharArray();
    	while (left < right) {
    		
    		char c1 = charArray[left];
    		if (!((c1 >= (int)'0' && c1 <= (int)'9')
				|| (c1 >= (int)'a' && c1 <= (int)'z'))
    				) {
    			left++;
    			continue;
    		}
    		
    		char c2 = charArray[right];
    		if (!((c2 >= (int)'0' && c2 <= (int)'9')
				|| (c2 >= (int)'a' && c2 <= (int)'z'))
    				) {
    			right--;
    			continue;
    		}
    		
    		if (c1 != c2) return false;
    		
    		left++;
    		right--;
    	}
    	
    	return true;
    }
}
