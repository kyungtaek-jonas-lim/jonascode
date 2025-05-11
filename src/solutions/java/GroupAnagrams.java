package solutions.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
# Problem
	- `Link`: https://leetcode.com/problems/group-anagrams/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Mar 25, 2025
	- `Answer`: groupAnagrams / groupAnagramsAdvanced / groupAnagramsBest
*/
public class GroupAnagrams {

	public static void main(String[] args) {
		System.out.println(groupAnagrams(new String[] {"eat","tea","tan","ate","nat","bat"})); // [["bat"],["nat","tan"],["ate","eat","tea"]]
		System.out.println(groupAnagrams(new String[] {""})); // [[""]]
		System.out.println(groupAnagrams(new String[] {"a"})); // [["a"]]
		
		System.out.println("---");
		
		System.out.println(groupAnagramsAdvanced(new String[] {"eat","tea","tan","ate","nat","bat"})); // [["bat"],["nat","tan"],["ate","eat","tea"]]
		System.out.println(groupAnagramsAdvanced(new String[] {""})); // [[""]]
		System.out.println(groupAnagramsAdvanced(new String[] {"a"})); // [["a"]]
	}

	/*
    # Option #1
    - O(n * k * log k)
	 */
    public static List<List<String>> groupAnagrams(String[] strs) {
    	
    	// Initialize map to store items with the keys which are sorted string item
    	Map<String, List<String>> map = new HashMap<>();
    	for (int i = 0; i < strs.length; i++) {
    		
    		// Get Char Arrays out of string items
    		char[] charArray = strs[i].toCharArray();
    		
    		// Sort to see if they are anagrams
    		Arrays.sort(charArray);
    		
    		// Use sorted strings as a key
    		String key = new String(charArray);
    		if (map.get(key) == null) {
    			map.put(key, new ArrayList<>());
    		}
    		map.get(key).add(strs[i]);
    	}
    	
    	// Make the result List out of the Anagram Map
    	// Slower than Option #2
    	List<List<String>> result = new ArrayList<>();
    	for (String key: map.keySet()) {
    		result.add(map.get(key));
    	}
    	return result;
    }
    
    /*
    # Option #2
    - O(n * k * log k)
    - Not much faster than 'Option #1'
     */
    public static List<List<String>> groupAnagramsAdvanced(String[] strs) {
        // Use HashMap to group anagrams
        Map<String, List<String>> map = new HashMap<>();
        
        // Iterate through each string
        for (String s : strs) {
            // Convert to char array and sort
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);
            
            // Add to map, create new list if key doesn't exist
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        
        // Return all grouped anagrams
        return new ArrayList<>(map.values());
    }
    
    /*
    # Option #3
    - O(n * m) (n = the number of strs, m = the length of average str)
    - Counting Sort Key
     */
    public static List<List<String>> groupAnagramsBest(String[] strs) {
    	
    	Map<String, List<String>> map = new HashMap<>();
    	
    	for (String s: strs) {
    		
    		int[] count = new int[26];
    		
    		for (char c: s.toCharArray()) {
    			count[(int)c - (int)'a']++;
    		}
    		
    		StringBuilder stringBuilder = new StringBuilder();
    		for (int i: count) {
    			stringBuilder.append(i).append('#');
    		}
    		
    		String key = stringBuilder.toString();
    		if (map.containsKey(key)) {
    			map.get(key).add(s);
    		} else {
    			List<String> item = new ArrayList<>();
    			item.add(s);
    			map.put(key, item);
    		}
    	}
    	
    	return new ArrayList<>(map.values());
    }
}
