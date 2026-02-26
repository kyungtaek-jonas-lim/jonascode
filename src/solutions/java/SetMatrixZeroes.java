package solutions.java;

import java.util.HashSet;
import java.util.Set;

/*
# Problem
	- `Link`: https://leetcode.com/problems/set-matrix-zeroes/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 10, 2025
	- `Answer`: setZeroes / setZeroesAdvanced
 */
public class SetMatrixZeroes {
	
	/*
    # Option #1
    - Common Way
    - O (m * n)
    - Space Complexity: O(m + n)
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
    
    /*
    # Option #2
    - Advanced Way
    - O (m * n)
    - Space Complexity: O(1)
     */
    public void setZeroesAdvanced(int[][] matrix) {
        
        final int m = matrix.length, n = matrix[0].length;

        // Check first row & col
        boolean row = false, col = false;
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                row = true;
                break;
            }
        }
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                col = true;
                break;
            }
        }

        // Put 0 on the first row & col
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        // Replace value based on the first row & col
        for (int j = 1; j < n; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < m; i++){
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Replace values on the first row & col
        if (row) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
        if (col) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
