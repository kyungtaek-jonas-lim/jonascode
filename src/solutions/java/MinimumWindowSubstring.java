package solutions.java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
# Problem
	- `Link`: https://leetcode.com/problems/minimum-window-substring/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Feb 1, 2025
	- `Answer`: minWindow / minWindowAdvanced / minWindowBest / minWindowSimpleAndBest / minWindowAdditional
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
	 * @description Easy way
	 * @timeComplexity O(n + m) (n: the length of s, m: the length of t)
	 * @param s
	 * @param t
	 * @return
	 */
    public static String minWindow(String s, String t) {
    	
    	// Edge Case
    	if (s.length() == t.length()) {
    		char[] sCharArray = s.toCharArray();
    		char[] tCharArray = t.toCharArray();
    		Arrays.sort(sCharArray);
    		Arrays.sort(tCharArray);
    		
    		if (Arrays.equals(sCharArray, tCharArray)) {
    			return s;
    		}
    	}
    	
    	// Get Counts of Letters of t
    	int[] tCnt = new int[128];
    	int totalRequiredCnt = 0;
    	for (char c: t.toCharArray()) {
    		tCnt[(int)c]++;
    		totalRequiredCnt++;
    	}

    	// Compare Counts of Letters of s
    	String result = "";
    	int resultLength = Integer.MAX_VALUE;
    	
    	int[] sCnt = new int[128]; // Get Counts of Letters of s, which t has 
    	int[] extraCnt = new int[128]; // Get Extra Counts to minimize the substring whenever it exceeeds the numbers of each character of t
    	int matchedCnt = 0;
    	
    	int left = -1;
    	int right = 0;
    	
    	for (char c: s.toCharArray()) {
    		int index = (int)c;
    		
    		if (tCnt[index] > 0) {
    			
    			// Initialize left
    			if (left < 0) left = right;
    			
    			// If the right amount of the character is already found.
    			if (tCnt[index] == sCnt[index]) {
    				
    				extraCnt[index]++;
    				
    				// Narrow them down
    				while (true) {
        				int tempIndex = (int)s.charAt(left);
        				
        				// If t has s[left] but if there's no extra character, stop
    					if (tCnt[tempIndex] > 0) {
    						if (extraCnt[tempIndex] == 0) break;
    						extraCnt[tempIndex]--; // Remove the extra characters
    					}
    					left++;
    				}
    				
    			} else { // For the character, it encountered not enough numbers yet.
    				sCnt[index]++;
    				matchedCnt++;
    			}
    			
    			// Update result
    			if (totalRequiredCnt == matchedCnt) {
    				if (resultLength > right - left) {
    					result = s.substring(left, right + 1);
    					resultLength = right - left;
    				}
    			}
    			
    		}
    		
    		right++;
    	}
    	
    	return result;
    }
	
	/**
	 * @option 2
	 * @description Advanced way
	 * @timeComplexity O(n + m) (n: the length of s, m: the length of t)
	 * @param s
	 * @param t
	 * @return
	 */
    public static String minWindowAdvanced(String s, String t) {

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

	
	/**
	 * @option 3
	 * @description Best way
	 * @timeComplexity O(n + m) (n: the length of s, m: the length of t)
	 * @param s
	 * @param t
	 * @return
	 */
    public String minWindowBest(String s, String t) {
        if (s.length() < t.length()) return "";

        int[] tCount = new int[128];
        for (char c : t.toCharArray()) {
            tCount[c]++;
        }

        int[] windowCount = new int[128];
        int required = 0;
        for (int c : tCount) if (c > 0) required++;

        int formed = 0;
        int left = 0, right = 0;
        int start = 0, minLen = Integer.MAX_VALUE;

        while (right < s.length()) {
            char c = s.charAt(right);
            windowCount[c]++;
            if (tCount[c] > 0 && windowCount[c] == tCount[c]) {
                formed++;
            }

            while (formed == required) {
                if (right - left + 1 < minLen) {
                    start = left;
                    minLen = right - left + 1;
                }

                char leftChar = s.charAt(left);
                windowCount[leftChar]--;
                if (tCount[leftChar] > 0 && windowCount[leftChar] < tCount[leftChar]) {
                    formed--;
                }
                left++;
            }
            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
    


	
    /**
	* @option 4
	* @description Simple and Best way
	* @timeComplexity O(n + m) (n: the length of s, m: the length of t)
	* @param s
	* @param t
	* @return
	*/
    public String minWindowSimpleAndBest(String s, String t) {
        
        Map<Character, Integer> numberOfCharacters = new HashMap<>();
        char[] tCharArray = t.toCharArray();
        for (char c: tCharArray) {
            numberOfCharacters.put(c, numberOfCharacters.getOrDefault(c, 0) + 1);
        }

        int[] result = new int[] {-1, s.length() + 1};
        int left = 0, n = s.length();
        int curr = 0, goal = numberOfCharacters.size();
        char[] sCharArray = s.toCharArray();
        Map<Character, Integer> numberOfCurrent = new HashMap<>();

        for (int i = 0; i < n; i++) {
            char c = sCharArray[i];
            if (!numberOfCharacters.containsKey(c)) continue;

            numberOfCurrent.put(c, numberOfCurrent.getOrDefault(c, 0) + 1);
            
            if (numberOfCurrent.get(c).equals(numberOfCharacters.get(c))) {
                curr++;
            }

            while (curr == goal) {
                if (i - left < result[1] - result[0]) {
                    result[1] = i;
                    result[0] = left;
                }

                char temp = sCharArray[left++];
                if (numberOfCurrent.containsKey(temp)) {
                    numberOfCurrent.put(temp, numberOfCurrent.get(temp) - 1);
                    if (numberOfCurrent.get(temp) < numberOfCharacters.get(temp)) {
                        curr--;
                    }
                }
            }
        }

        if (result[0] == -1) return "";
        return s.substring(result[0], result[1] + 1);
    }
    

	
    /**
	* @option 5
	* @description Additional way
	* @timeComplexity O(n + m)
	* @date Jan 2, 2026
	* @param s
	* @param t
	* @return
	*/
    public String minWindowAdditional(String s, String t) {
        int n = s.length(), m = t.length();
        if (n < m) return "";
        else if (n == m) {
            char[] sChars = s.toCharArray();
            char[] tChars = t.toCharArray();
            Arrays.sort(sChars);
            Arrays.sort(tChars);
            if (!Arrays.equals(sChars, tChars)) return "";
            return s;
        }

        Map<Character, Integer> validCnt = new HashMap<>();
        int validTotalCnt = 0;
        Map<Character, Integer> currValidCnt = new HashMap<>();
        int currValidTotalCnt = 0;
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        for (char c: tChars) {
            if (validCnt.containsKey(c)) {
                validCnt.put(c, validCnt.get(c) + 1);
            } else {
                validCnt.put(c, 1);
                currValidCnt.put(c, 0);
                validTotalCnt++;
            }
        }

        int left = 0;
        for (int i = 0; i < n; i++) {
            if (validCnt.containsKey(sChars[i])) {
                left = i;
                break;
            }
        }

        String result = s + t;
        for (int i = left; i < n; i++) {
            char c = sChars[i];
            if (validCnt.containsKey(c)) {
                currValidCnt.put(c, currValidCnt.get(c) + 1);
                int goalCnt = validCnt.get(c), currCnt = currValidCnt.get(c);

                if (goalCnt == currCnt) {
                    currValidTotalCnt++;

                } else if (goalCnt < currCnt) {
                    int tobeLeft = left;
                    char tobeLeftChar = sChars[tobeLeft];
                    while (!validCnt.containsKey(tobeLeftChar) || (validCnt.get(tobeLeftChar) < currValidCnt.get(tobeLeftChar))) {
                        if (currValidCnt.containsKey(tobeLeftChar)) {
                            currValidCnt.put(tobeLeftChar, currValidCnt.get(tobeLeftChar) - 1);
                        }
                        tobeLeftChar = sChars[++tobeLeft];
                    }

                    left = tobeLeft;
                }

                if (currValidTotalCnt == validTotalCnt && result.length() > (i - left + 1)) {
                    result = s.substring(left, i + 1);
                }
            }
        }

        return result.equals(s + t) ? "" : result;
    }

}
