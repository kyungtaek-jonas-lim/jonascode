package solutions.java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
# Problem
	- `Link`
        - `LeetCode`: https://leetcode.com/problems/graph-valid-tree/
        - `LintCode`: https://www.lintcode.com/problem/178/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 30
	- `Answer`: validTree
 */
public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        
        /*
        1. The number of edge (n - 1)
        2. Loop
        3. Separate
        */
    	
    	// The number of edges (Invalid #1)
    	if (edges.length != n - 1) return false;

        // Init
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new HashSet<>());
        }

        // Build graph
        for (int[] edge: edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        // Dfs
        Set<Integer> visited = new HashSet<>();
        if (!dfs(map, visited, 0, -1)) return false;

        // See if there's a separate tree (Invalid #3)
//        for (int i = 0; i < n; i++) {
//            if (!visited.contains(i)) return false;
//        }
//        return true;
        return visited.size() == n;
    }

    private boolean dfs(Map<Integer, Set<Integer>> map, Set<Integer> visited, int current, int prev) {
        if (visited.contains(current)) return false; // Loop (Invalid #2)

        visited.add(current);
        Set<Integer> currentEdge = map.get(current);
        for (int next: currentEdge) {
            if (next == prev) continue;
            if (!dfs(map, visited, next, current)) return false;
        }
        return true;
    }
    
    
    public static void main(String[] args) {
		GraphValidTree solution = new GraphValidTree();
		System.out.println(solution.validTree(5, new int[][] {{0, 1}, {0, 2}, {0, 3}, {1, 4}}));
		System.out.println(solution.validTree(5, new int[][] {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}}));
	}
}
