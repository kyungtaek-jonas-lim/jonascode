package solutions.java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/valid-anagram/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 9, 2025
 	- `Answer`: isAnagramWorse / isAnagram / isAnagramBetter / isAnagramAdvanced / isAnagramBest / isAnagramBest2
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
	 * Worse way
	 * O(n log n)
	 */
    public boolean isAnagramWorse(String s, String t) {
    	
    	if (s.length() == t.length()) {
    		char[] sCharArray = s.toCharArray();
    		char[] tCharArray = t.toCharArray();
    		
    		Arrays.sort(sCharArray);
    		Arrays.sort(tCharArray);
    		
    		return Arrays.equals(sCharArray, tCharArray);
    	} else return false;
    }
    
	/*
	 * Option #2
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
	 * Option #3
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
	 * Option #4
	 * Advanced way
	 * O(n)
	 */
    public boolean isAnagramAdvanced(String s, String t) {
    	if (s.length() != t.length()) return false;

    	int[] sCnt = new int[128];
    	
    	for (char c: s.toCharArray()) {
    		sCnt[(int)c]++;
    	}

    	for (char c: t.toCharArray()) {
    		sCnt[(int)c]--;
    		if (sCnt[(int)c] < 0) return false;
    	}
    	return true;
    }

	
	/*
	 * Option #5
	 * Best way
	 * O(n) => Because of the fixed length of array
	 */
    public static boolean isAnagramBest(String s, String t) {
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

	
	/*
	 * Option #6
	 * Best way
	 * O(n) => Because of the fixed length of array
	 */
    public boolean isAnagramBest2(String s, String t) {
        if (s.length() != t.length()) return false;

        final int n = s.length();
        int[] cnt = new int[128];
        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();

        for (int i = 0; i < n; i++) {
            cnt[(int)charsS[i]]++;
            cnt[(int)charsT[i]]--;
        }

        for (int c: cnt) {
            if (c != 0) return false;
        }

        return true;
    }
}




