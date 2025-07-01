package solutions.java;

import java.util.Arrays;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: July 1, 2025
 	- `Answer`: findCircleNum
 */
public class NumberofProvinces {
	
	/*
	# Option #1
	- Union-find
	- O(n^2)
	 */
    public int findCircleNum(int[][] isConnected) {
        
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
}
