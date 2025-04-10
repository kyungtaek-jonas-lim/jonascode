package solutions.java;

import java.util.HashSet;
import java.util.Set;

/*
# Problem
	- `Link`: https://leetcode.com/problems/set-matrix-zeroes/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 10, 2025
	- `Answer`: setZeroes
 */
public class SetMatrixZeroes {
	
	/*
    # Option #1
    - Common Way
    - O (m * n)
	 */
    public void setZeroes(int[][] matrix) {
        
    	int m = matrix.length, n = matrix[0].length;
    	
    	Set<Integer> rowSet = new HashSet<>();
    	Set<Integer> colSet = new HashSet<>();
    	
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			if (matrix[i][j] == 0) {
    				rowSet.add(i);
    				colSet.add(j);
    			}
    		}
    	}
    	
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			if (rowSet.contains(i) || colSet.contains(j)) {
    				matrix[i][j] = 0;
    			}
    		}
    	}
    }
}
