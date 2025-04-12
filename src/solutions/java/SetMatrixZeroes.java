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
    - https://www.youtube.com/watch?v=T41rL0L3Pnw
     */
    public void setZeroesAdvanced(int[][] matrix) {
    	
    	/*
        1. Determin which rows/cols need to be zero by updating the first row, the first colum based on the values, matrix[i][j] (i > 0, j > 0)
            (When it comes to the first colum, exclude the first row because the first row has the cell)
            => So use two row, 'matrix[0][j]' + 'matrix[i][j] (i > 0)'
        2. Update matrix[i][j] (i > 0) based on the first row and the first column.
        3. Update the first row based on the seperate variable. (row_zero)
    	 */
    	
    	int m = matrix.length, n = matrix[0].length;
    	boolean firstRowZero = false; // If it's true, all the values of the row is zero. So store it seperately.
    	
    	// ---
    	// 1. Determin which rows/cols need to be zero
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			
    			// If the value is zero,
    			if (matrix[i][j] == 0) {
    				matrix[0][j] = 0; // Update the first row value of the same column to Zero
    				if (i > 0) { // Exclude the first row
    					matrix[i][0] = 0;
    				} else { // If it's the first row, keep it as is for now. It will be updated later because it should serve as a pivot for all the other rows, except the first one itself, so it cannot be updated yet.
    					firstRowZero = true;
    				}
    			}
    		}
    	}
    	
    	// ---
    	// 2. Update values with Zero
    	for (int i = 1; i < m; i++) { // Skip the first row
    		for (int j = 1; j < n; j++) { // Skip the first col
    			if (matrix[0][j] == 0 || matrix[i][0] == 0) {
    				matrix[i][j] = 0;
    			}
    		}
    	}
    	
    	// For the first column
    	if (matrix[0][0] == 0) {
    		for (int i = 1; i < m; i++) {
    			matrix[i][0] = 0;
    		}
    	}
    	
    	// --
    	// 3. For the first row (now you can update the pivot row)
    	if (firstRowZero) {
    		for (int j = 0; j < n; j++) {
    			matrix[0][j] = 0;
    		}
    	}
    }
}
