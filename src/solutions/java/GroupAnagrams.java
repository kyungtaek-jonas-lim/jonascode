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
	- `Answer`: groupAnagrams / groupAnagramsAdvanced / groupAnagramsBest / groupAnagramsSimple
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
    public List<List<String>> groupAnagramsBest(String[] strs) {

        Map<String, List<String>> memo = new HashMap<>();

        for (String str: strs) {
            
            char[] chars = str.toCharArray();
            int[] counts = new int[26];
            for (char c: chars) {
                counts[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for (int c: counts) {
                sb.append(c).append("#");
            }

            String key = sb.toString();
            if (memo.containsKey(key)) {
                memo.get(key).add(str);
            } else {
                List<String> item = new ArrayList<>();
                item.add(str);
                memo.put(key, item);
            }
        }

        return new ArrayList<>(memo.values());
    }
    
    /*
    # Option #4
    - O(n * k * log k)
	- Simple
	- Jan 19, 2026
     */
    public List<List<String>> groupAnagramsSimple(String[] strs) {
        
        Map<String, Integer> memo = new HashMap<>();
        List<List<String>> result = new ArrayList<>();
        
        for (String str: strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);

            if (memo.containsKey(sortedStr)) {
                result.get(memo.get(sortedStr)).add(str);
            } else {
                memo.put(sortedStr, result.size());
                List<String> newItem = new ArrayList<>();
                newItem.add(str);
                result.add(newItem);
            }
        }

        return result;
    }
}
