package solutions;

import java.util.HashMap;
import java.util.Map;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/valid-anagram/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Jan 23, 2025
 	- `Answer`: isAnagram / isAnagramBetter / isAnagramAdvanced
 # Reference
 	- Anagrams
 		- Both strings must have the same length.
		- Both strings must have the exact same character counts.
 */

public class ValidAnagram {
	public static void main(String[] args) {
		System.out.println(isAnagram("rat", "car"));
		System.out.println(isAnagram("anagram", "nagaram"));
	}

	
	/*
	 * Option #1 
	 * Common way
	 * O(n + m)
	 */
    public static boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        
        for (int i = 0; i < t.length(); i++) { // Time Complexity: O(n)
        	char currentChar = t.charAt(i);
        	if (map.get(currentChar) == null) {
        		map.put(currentChar, 1);
        	} else {
        		map.put(currentChar, map.get(currentChar) + 1);
        	}
        }
        for (int i = 0; i < s.length(); i++) {// Time Complexity: O(m)
        	char currentChar = s.charAt(i);
        	Integer currentCnt = map.get(currentChar);
        	if (currentCnt == null) return false;
        	currentCnt--;
        	if (currentCnt == 0) {
                map.remove(currentChar);
                continue;
            }
        	map.put(currentChar, currentCnt);
        }
        return map.isEmpty();
    }

	
	/*
	 * Option #2 
	 * Better way
	 * O(n + m) => The same time complexity as the first option
	 */
    public static boolean isAnagramBetter(String s, String t) {
    	if (s.length() != t.length()) return false;
    	
    	Map<Character, Integer> map = new HashMap<>();
    	for (char c: t.toCharArray()) {
    		map.put(c, map.getOrDefault(c, 0) + 1);
    	}
    	
    	for (char c: s.toCharArray()) {
    		if (!map.containsKey(c) || map.get(c) == 0) return false;
    		map.put(c,  map.get(c) - 1);
    	}
    	return true;
    }

	
	/*
	 * Option #3
	 * Advanced way
	 * O(n) => Because of the fixed length of array
	 */
    public static boolean isAnagramAdvanced(String s, String t) {
    	if (s.length() != t.length()) return false;
    	
    	int[] charCount = new int[26]; // Time Complexity: O(1) => Fixed
    	// => Constraints #2: s and t consist of lowercase English letters.
    	
    	for (int i = 0; i < s.length(); i++) {
    		charCount[s.charAt(i) - 'a']++;
    		charCount[t.charAt(i) - 'a']--;
    	}
    	
    	for (int count: charCount) {
    		if(count != 0) return false;
    	}
    	return true;
    }
}




