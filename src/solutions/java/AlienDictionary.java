package solutions.java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/*
# Problem
	- `Link`
        - `LeetCode`: https://leetcode.com/problems/alien-dictionary/
        - `LintCode`: https://www.lintcode.com/problem/892/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 26
	- `Answer`: alienOrder
 */
public class AlienDictionary {
	
	/*
	# Option #1
	- Recursive DFS
	- O(n * m + k + e) (n = words.length, m = the average length of words, k = total number of unique characters, e = number of edges)
	- ref) https://www.youtube.com/watch?v=6kTZYvNNyps
	 */
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> adjacent = new HashMap<>();
        
        // Step 1: Create a graph with all unique characters as nodes
        // - Initiate Graph by putting all the characters in the graph
        // - Put all the characters to return strings even with the non-inferable characters
        for (String word: words) {
        	for (char c: word.toCharArray()) {
        		adjacent.putIfAbsent(c, new HashSet<>());
        	}
        }
        
        
        // Step 2: Build edges (graph) from adjacent words
        // - Graph Creation: Build edges (graph) from adjacent words
        int n = words.length;
        for (int i = 1; i < n; i++) {
        	
            String w1 = words[i - 1], w2 = words[i];
            int l1 = w1.length(), l2 = w2.length();
            
            int minLength = Math.min(l1, l2);
            
            // Edge case: Invalid case: If the first `min(s.length, t.length)` letters are the same, then `s`` is smaller if and only if `s.length < t.length`.
            if (l1 > l2 && w1.startsWith(w2)) return "";
            
            // Compare characters and add the first mismatch as a directed edge
            for (int j = 0; j < minLength; j++) {
                char c1 = w1.charAt(j), c2 = w2.charAt(j);
                if (c1 != c2) {
                    adjacent.get(c1).add(c2); // Edge (graph): prev[j] -> curr[j]
                    break;
                }
            }
        }
        
        
        // Step 3: DFS + Cycle detection + Topological sort
        Map<Character, Boolean> visit = new HashMap<>(); // visit.get(c) = True (visiting, current path, currently in recursion), False (visited and done)
        StringBuilder stringBuilder = new StringBuilder();
        
        
        // Step 4: Visit all nodes
        for (char c: adjacent.keySet()) {
        	if (dfs(adjacent, c, visit, stringBuilder)) return ""; // If cycle found, return ""
        }
        
        // Step 5: Return the reversed string
        return stringBuilder.reverse().toString();
    }
    
    private boolean dfs(Map<Character, Set<Character>> adjacent, char c, Map<Character, Boolean> visit, StringBuilder stringBuilder) {
    	if (visit.containsKey(c)) return visit.get(c); // Return True if cycle detected (If it's on the current path)
    	
    	visit.put(c, true); // Mark as visiting(current path)
    	
    	for (char neighbor: adjacent.get(c)) { // If a cycle is found in the path
    		if (dfs(adjacent, neighbor, visit, stringBuilder)) return true;
    	}
    	
    	visit.put(c, false); // Mark as finished
    	stringBuilder.append(c); // Post-order appending for topological order
    	
    	return false;
    }
    
    public static void main(String[] args) {
		AlienDictionary alienDictionary = new AlienDictionary();
		String result = alienDictionary.alienOrder(new String[] {"wrt","wrf","er","ett","rftt"});
		System.out.println(result);
		
		result = alienDictionary.alienOrder(new String[] {"z", "x"});
		System.out.println(result);
		
		result = alienDictionary.alienOrder(new String[] {"abc", "bcd"});
		System.out.println(result);
	}
}
