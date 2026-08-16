package solutions.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/number-of-provinces/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: July 1, 2025
 	- `Answer`: findCircleNumUnionFind / findCircleNumDfs
 */
public class NumberofProvinces {
	
	/*
	# Option #1
	- Union-find
	- O(n^2)
	 */
    public int findCircleNumUnionFind(int[][] isConnected) {
        
        int n = isConnected.length;

        // Parents
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        // Ranks    
        int[] ranks = new int[n];
        Arrays.fill(ranks, 1);

        // Union-find
        int result = n;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    result -= union(parents, ranks, i, j);
                }
            }
        }
        return result;
    }

    private int find(int[] parents, int n) {
        int result = n;
        while (parents[result] != result) {
            parents[result] = parents[parents[result]];
            result = parents[result];
        }
        return result;
    }

    private int union(int[] parents, int[] ranks, int n1, int n2) {
        int p1 = find(parents, n1), p2 = find(parents, n2);

        if (p1 == p2) return 0;
        
        if (ranks[p1] >= ranks[p2]) {
            ranks[p1] += ranks[p2];
            parents[p2] = p1;
        } else {
            ranks[p2] += ranks[p1];
            parents[p1] = p2;
        }
        return 1;
    }


	/*
	# Option #2
	- DFS 'Option #1(Union-find)' is faster
	- O(n^2)
    - August 16, 2026
	 */
    public int findCircleNumDfs(int[][] isConnected) {
        
        final int n = isConnected.length;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) graph.put(i, new ArrayList<>());
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        Set<Integer> visited = new HashSet<>();
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                result++;
                dfs(graph, i, visited);
            }
        }
        return result;
    }

    private void dfs(Map<Integer, List<Integer>> graph, int curr, Set<Integer> visited) {
        if (visited.contains(curr)) return;
        visited.add(curr);
        for (int nei: graph.get(curr)) {
            dfs(graph, nei, visited);
        }
    }
}
