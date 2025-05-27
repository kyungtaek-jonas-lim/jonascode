package solutions.java;

/*
# Problem
	- `Link`: https://leetcode.com/problems/valid-palindrome/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 17
	- `Answer`: isPalindrome / isPalindromeAdvanced / isPalindromeAdvanced2
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
    	StringBuilder sb = new StringBuilder();
    	char[] charArray = s.toCharArray();
    	for (char c: charArray) {
    		if ((c >= (int)'0' && c <= (int)'9')
				|| (c >= (int)'a' && c <= (int)'z')
    				) {
    			sb.append(c);
    		}
    	}
    	
    	// If they are the same
        int n = sb.length();
        for (int i = 0; i < n / 2; i++) {
            if (sb.charAt(i) != sb.charAt(n - 1 - i)) return false;
        }
        return true;
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
	
	/*
	# Option #3
	- Two Pointers with Java Utility
	- O(n)
	- Space Complexity: O(1)
	 */
    public static boolean isPalindromeAdvanced2(String s) {
        
        int left = 0, right = s.length() - 1;
        char[] c = s.toCharArray();
        
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(c[left])) {
                left++;
            }

            while (left < right && !Character.isLetterOrDigit(c[right])) {
                right--;
            }

            if (Character.toLowerCase(c[left]) != Character.toLowerCase(c[right])) return false;

            left++;
            right--;
        }

        return true;
    }
}
