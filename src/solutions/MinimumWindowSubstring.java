package solutions;

/*
# Problem
	- `Link`: https://leetcode.com/problems/minimum-window-substring/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Feb 1, 2025
	- `Answer`: minWindow
*/

public class MinimumWindowSubstring {

	public static void main(String[] args) {
		String s = null, t = null;
		
		t = "ADOBECODEBANC";
		s = "ABC";
		System.out.println("Answer: " + minWindow(t, s));

		t = "a";
		s = "a";
		System.out.println("Answer: " + minWindow(t, s));

		t = "a";
		s = "aa";
		System.out.println("Answer: " + minWindow(t, s));
	}
	
	/**
	 * @option 1
	 * @description Common way
	 * @param s
	 * @param t
	 * @return
	 */
    public static String minWindow(String s, String t) {

		// ------------------------------------
		// Validation & Init Variables
		// ------------------------------------
		// Edge case: if t is longer than s 
		if (s.length() < t.length()) return "";
		
		
//		// Using map is solid, but array is faster if you know all the characters as inputs
//		Map<Character, Integer> mapTargetCharacterCounts = new HashMap<>(); 
//		// Get Target Count
//		for (char c: t.toCharArray()) {
//			mapTargetCharacterCounts.put(c, mapTargetCharacterCounts.getOrDefault(c, 0) + 1);
//		}
		
		// Variables
		int[] arrayTargetCharacterCounts = new int[58]; // Size is numbers of uppercase and lowercase English letters + the number of characters between them according to the ASCII code.
		int arrayTargetCharacterCountsSize = 0;
		
		// Get Target Count
		for (char c: t.toCharArray()) {
			if (arrayTargetCharacterCounts[c - 'A'] == 0) arrayTargetCharacterCountsSize++;
			arrayTargetCharacterCounts[c - 'A']++;
		}
		
		
		// ------------------------------------
		// Search for Minimum Window SubString
		// ------------------------------------
		int start = -1, end = -1; // the start and end indexes of substring(result string), default -1
		int left = 0; // left one is a temporary left index to scan the string, the right temporary index will be the loop index
		int countOfReadyTargetCharacters = 0; // it means it's required with the target characters 
		int[] arraySubString = new int[58]; // You can use Maps instead but arrays are faster
		
		for (int right = 0; right < s.length(); right++) { // the loop index will indicate 'right' index cause it's the right most index of a scanned string  
			char c = s.charAt(right);
			
			// If this is not one of the target characters, skip the turn
			if (arrayTargetCharacterCounts[c - 'A'] == 0) continue;
			
			arraySubString[c - 'A']++;
			
			// If the count of the scanned target characters are the same count
			if (arraySubString[c - 'A'] == arrayTargetCharacterCounts[c - 'A']) {
				countOfReadyTargetCharacters++; // meet the counts of the target characters
			}
			
			// If all of the counts of the target characters meet the whole target string
			// Narrow them down to minimize the substring
			while (countOfReadyTargetCharacters == arrayTargetCharacterCountsSize) { // Takes time cause it's located in 'for'
				
				// Check the minimum length 
				if (start == -1 || end - start > right - left) { // Compare the result length and current substringlength
					// Set the start and end indexes with the min window
					start = left;
					end = right;
				}
				
				// Move to the left index to the right cause it's already checked with the minimum size of the required substring.
				char leftChar = s.charAt(left); // the character to be deleted
				if (arraySubString[leftChar - 'A'] > 0) {
					arraySubString[leftChar - 'A']--; // count down for the character
					
					// If the shorted substring does not fit for the requirements
					if (arraySubString[leftChar - 'A'] < arrayTargetCharacterCounts[leftChar - 'A']) {
						countOfReadyTargetCharacters--;
					}
				}
				left++; // Move the left index to the right and see if it's still required.
			}
		}
		if (start == -1) return ""; // If you couldn't find the required substring.
		return s.substring(start, end + 1);
	}
}
