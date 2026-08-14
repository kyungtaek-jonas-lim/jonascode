package solutions.java;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
	- `Answer`: validTreeBfs / validTreeDfs / validTreeDfs2 / validTreeUnionFind
 */
public class GraphValidTree {

	/*
    # Option #1
    - BFS
    - O(n + e)
	 */
    public boolean validTreeBfs(int n, int[][] edges) {
        
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

        // Bfs
        return bfs(map);
    }
    
    private boolean bfs(Map<Integer, Set<Integer>> map) {
    	
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {0, -1});

        Set<Integer> visited = new HashSet<>();
        
        while (!queue.isEmpty()) {
        	int[] item = queue.poll();
        	int current = item[0], prev = item[1];

            if (visited.contains(current)) return false; // Loop (Invalid #2)
            visited.add(current);
        	
	        Set<Integer> currentEdge = map.get(current);
	        for (int next: currentEdge) {
	            if (next == prev) continue;
	            queue.add(new int[] {next, current});
	        }
        }
        return visited.size() == map.size(); // See if there's a separate tree (Invalid #3)
    }
    
    
    /*
    # Option #2
    - DFS
    - O(n + e)
     */
    public boolean validTreeDfs(int n, int[][] edges) {
        
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
    
    
    /*
    # Option #3
    - DFS (Bascially, the same as Option #2)
    - O(n + e)
    - August 14, 2026
     */
    public boolean validTreeDfs2(int n, int[][] edges) {

        if (edges.length != n - 1) return false;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) graph.put(i, new ArrayList<>());
        for (int[] e: edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        Set<Integer> visited = new HashSet<>();
        if (hasCycle(graph, 0, -1, visited)) return false;
        return visited.size() == n;
    }

    private boolean hasCycle(Map<Integer, List<Integer>> graph, int curr, int parent, Set<Integer> visited) {
        visited.add(curr);
        for (int n: graph.get(curr)) {
            if (n == parent) continue;
            if (visited.contains(n)) return true;
            if (hasCycle(graph, n, curr, visited)) return true;
        }
        return false;
    }


    
    /*
    # Option #4
    - Union-find
    - O(n + e)
    - August 14, 2026
     */
    public boolean validTreeUnionFind(int n, int[][] edges) {
        if (n - 1 != edges.length) return false;

        int[] parents = new int[n];
        for (int i = 0; i < n; i++) parents[i] = i;
        for (int[] e: edges) {
            int p1 = find(parents, e[0]); // Find each parent
            int p2 = find(parents, e[1]);
            if (p1 == p2) return false; // Cycle
            parents[p1] = p2; // Union
        }
        return true; // Survived all edges with no cycle, and had exactly n-1 of them
    }

    private int find(int[] parents, int curr) {
        while (curr != parents[curr]) {
            parents[curr] = parents[parents[curr]];
            curr = parents[curr];
        }
        return curr;
     }


    
    
    public static void main(String[] args) {
		GraphValidTree solution = new GraphValidTree();
		System.out.println(solution.validTreeDfs(5, new int[][] {{0, 1}, {0, 2}, {0, 3}, {1, 4}}));
		System.out.println(solution.validTreeDfs(5, new int[][] {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}}));

        
        // Case A: star tree, reversed edge direction — YOUR CODE FAILS THIS
        System.out.println(solution.validTreeDfs(4, new int[][] {{1,0},{2,0},{3,0}}));
        // true   <- your code returns false

        // Case B: valid tree 2-0-1-3, one edge written "backwards" — YOUR CODE FAILS THIS
        System.out.println(solution.validTreeDfs(4, new int[][] {{0,1},{0,2},{3,1}}));
        // true   <- your code returns false

        // Case C: your original example, still correct
        System.out.println(solution.validTreeDfs(5, new int[][] {{0,1},{0,2},{0,3},{1,4}}));
        // true

        // Case D: your original example, still correct
        System.out.println(solution.validTreeDfs(5, new int[][] {{0,1},{1,2},{2,3},{1,3},{1,4}}));
        // false  // cycle: 1 -> 2 -> 3 -> 1

        // Case E: disconnected forest, no cycle
        System.out.println(solution.validTreeDfs(5, new int[][] {{0,1},{2,3}}));
        // false  // disconnected, also wrong edge count (needs n-1 = 4)

        // Case F: two nodes, no edge
        System.out.println(solution.validTreeDfs(2, new int[][] {}));
        // false  // disconnected
	}
}
