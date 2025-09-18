package solutions.java;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
# Problem
	- `Link`
        - `LeetCode`: https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
        - `LintCode`: https://www.lintcode.com/problem/3651/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 30
	- `Answer`: countComponentsBfs / countComponentsDfs / countComponentsAdvanced
 */
public class NumberofConnectedComponentsinanUndirectedGraph {
	
	/*
    # Option #1
    - BFS
    - O(n + e)
	 */
    public int countComponentsBfs(int n, int[][] edges) {
        
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new HashSet<>());
        }

        // Build graph
        for (int[] edge: edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        int result = 0;
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (visited.contains(i)) continue;
            result += bfs(map, visited, i);
        }
        
        return result;
    }

    private int bfs(Map<Integer, Set<Integer>> map, Set<Integer> visited, int current) {
        
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(current);

        while (!deque.isEmpty()) {
            current = deque.poll();
            
            visited.add(current);

            Set<Integer> neighbors = map.get(current);
            for (int neighbor: neighbors) {
                if (visited.contains(neighbor)) continue;
                deque.add(neighbor);
            }
        }

        return 1;
    }
    
    /*
    # Option #2
    - DFS
    - O(n + e)
     */
    public int countComponentsDfs(int n, int[][] edges) {
        
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new HashSet<>());
        }

        // Build graph
        for (int[] edge: edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        int result = 0;
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (visited.contains(i)) continue;
            result += dfs(map, visited, i);
        }
        
        return result;
    }

    private int dfs(Map<Integer, Set<Integer>> map, Set<Integer> visited, int curr) {
        
        visited.add(curr);

        Set<Integer> neighbors = map.get(curr);
        for (int neighbor: neighbors) {
            if (visited.contains(neighbor)) continue;
            dfs(map, visited, neighbor);
        }

        return 1;
    }
    

	/*
    # Option #3
    - Union-Find
    - O(n + e)
    - https://www.youtube.com/watch?v=8f1XPm4WOUc
	 */
    public int countComponentsAdvanced(int n, int[][] edges) {
        
        // Parents
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
        	parents[i] = i;
        }

        // Size
        int[] size = new int[n];
        Arrays.fill(size, 1);

        // Union
        int result = n;
        for (int[] edge: edges) {
            result -= union(parents, size, edge[0], edge[1]);
        }
        return result;
    }

    private int find(int[] parents, int current) {
        int result = current;
        while (parents[result] != result) {
        	parents[result] = parents[parents[result]];
        	result = parents[result];
        }
        return result;
    }

    private int union(int[] parents, int[] size, int n1, int n2) {
        int p1 = find(parents, n1), p2 = find(parents, n2);
        if (p1 == p2) return 0;
        
        if (size[p1] < size[p2]) {
            parents[p1] = p2;
            size[p2] += size[p1];
        } else {
            parents[p2] = p1;
            size[p1] += size[p2];
        }
        return 1;
    }
}
