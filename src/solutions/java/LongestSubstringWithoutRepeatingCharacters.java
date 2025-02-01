package solutions.java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Jan 21, 2025
 	- `Answer`: lengthOfLongestSubstring / lengthOfLongestSubstringBetter / lengthOfLongestSubstringAdvanced
 */

public class LongestSubstringWithoutRepeatingCharacters {

	public static void main(String[] args) {
		System.out.println(lengthOfLongestSubstring("abcabcbb")); // 3 - "abc"
		System.out.println(lengthOfLongestSubstring("bbbbb")); // 1 - "b"
		System.out.println(lengthOfLongestSubstring("pwwkew")); // 3 - "wke"
		System.out.println(lengthOfLongestSubstring("dvdf")); // 3 - "vdf"
//		System.out.println(lengthOfLongestSubstringWithComment("dvdf")); // 3 - "vdf"
	}


	/*
	 * Option #1 
	 * Common way
	 * O(n2) - cf> String.contains(): 𝑂(𝑘)
	 * `String.contains()` is inefficient => HashMap/HashSet is more efficient.
	 */
    public static int lengthOfLongestSubstring(String s) {
    	
        // Edge case: If the input string is empty, return 0
        if (s.isEmpty()) return 0;
        
        String substring = "";
        int max = 1;
        for (int i = 0; i < s.length(); i++) {
        	
        	char objectCharacter = s.charAt(i);
//        	if (substring.contains("" + objectCharacter)) { // inefficient: it uses `StringBuilder`, `append(char)` and `toString()`  internally. It uses unnecessary objects.
//    		if (substring.contains(Character.toString(objectCharacter))) { // it uses `String.valueOf()`.
			if (substring.contains(String.valueOf(objectCharacter))) {
        		if (max < substring.length()) max = substring.length();
        		substring = substring.substring(substring.indexOf(objectCharacter) + 1);
        		
        	}
        	substring += objectCharacter;
        }
        if (max < substring.length()) max = substring.length();
        return max;
    }


	/*
	 * Option #2
	 * Better way
	 * O(n) : The HashMap-based code(Opetion #3 - lengthOfLongestSubstringAdvanced) is better than the HashSet code because it directly updates the start pointer to the next valid position, avoiding redundant removals.
	 */
    public static int lengthOfLongestSubstringBetter(String s) {
    	
        // Edge case: If the input string is empty, return 0
        if (s.isEmpty()) return 0;

        // HashSet to track characters in the current sliding window
        Set<Character> seen = new HashSet<>();
        int max = 0; // To store the maximum length of substring without repeating characters
        int start = 0; // Start index of the sliding window

        // Iterate through the string with the end pointer
        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end); // Current character being processed

            // If the character is already in the set, move the start pointer to the right
            while (seen.contains(currentChar)) {
                // Remove the character at the start of the window
                seen.remove(s.charAt(start)); // The HashMap-based code is better than the HashSet code because it directly updates the start pointer to the next valid position, avoiding redundant removals.
                start++; // Increment the start pointer
            }

            // Add the current character to the set
            seen.add(currentChar);

            // Update the maximum length of the substring
            max = Math.max(max, end - start + 1);
        }

        // Return the maximum length found
        return max;
    }
    
    

	/*
	 * Option #3
	 * Advanced way
	 * O(n) - cf> HashMap.put: O(1), HashMap.get: O(1)
	 */
    public static int lengthOfLongestSubstringAdvanced(String s) {
    	
        // Edge case: If the input string is empty, return 0
        if (s.isEmpty()) return 0;

        // Map to store the last seen index of each character
        HashMap<Character, Integer> charIndexMap = new HashMap<>();
        
        int maxLength = 0;
        int start = 0; // Sliding window start index

        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);

            // If the character is already in the map and within the current window
            if (charIndexMap.containsKey(currentChar) && charIndexMap.get(currentChar) >= start) {
                // Move the start to the right of the last seen position
                start = charIndexMap.get(currentChar) + 1;
            }

            // Update the character's last seen index
            charIndexMap.put(currentChar, end);

            // Calculate the length of the current substring and update max length
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }
}
