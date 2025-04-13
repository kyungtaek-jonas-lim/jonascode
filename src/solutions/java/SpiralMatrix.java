package solutions.java;

import java.util.ArrayList;
import java.util.List;

/*
# Problem
	- `Link`: https://leetcode.com/problems/spiral-matrix/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 13, 2025
	- `Answer`: spiralOrder / spiralOrderAdvanced
 */
public class SpiralMatrix {

	/*
    # Option #1
    - Mark the checked cell
    - O (m * n)
    - Extra Space Complexity: O(m * n)
	 */
    public List<Integer> spiralOrder(int[][] matrix) {
        
    	List<Integer> list = new ArrayList<>();
    	
    	int m = matrix.length, n = matrix[0].length;
    	int i = 0, j = 0;
    	int arrow = 0;
    	int[][] arrowMatrix = new int[][] { {0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    	boolean[][] markedMatrix = new boolean[m][n];
    	
    	while (!markedMatrix[i][j]) {
    		
    		// Put the result
    		list.add(matrix[i][j]);
    		
    		// Mark
    		markedMatrix[i][j] = true;
    		
    		// Move
    		i += arrowMatrix[arrow][0];
    		j += arrowMatrix[arrow][1];
    		
    		// Set Arrow
    		if (i < 0 || i >= m || j < 0 || j >= n || markedMatrix[i][j]) {
        		i -= arrowMatrix[arrow][0];
        		j -= arrowMatrix[arrow][1];
    			arrow = (arrow + 1) % 4;
        		i += arrowMatrix[arrow][0];
        		j += arrowMatrix[arrow][1];
        		
        		// If it's finished checking all the cells
        		if (i < 0 || i >= m || j < 0 || j >= n || markedMatrix[i][j]) break;
    		}
    	}
    	
    	return list;
    }
    
    /*
    # Option #2
    - Narrow the zone(matrix) to be checked
    - O (m * n)
    - Extra Space Complexity: O(1)
    - ref: https://www.youtube.com/watch?v=BJnMZNwUk1M
     */
	public List<Integer> spiralOrderAdvanced(int[][] matrix) {
		
		// Set the limit
		int top = 0, bottom = matrix.length - 1;
		int left = 0, right = matrix[0].length - 1;
		
		// Move
		List<Integer> result = new ArrayList<>();
		while (top <= bottom && left <= right) {
			
			// Move Left
			for (int i = left; i <= right; i++) {
				result.add(matrix[top][i]);
			}
			top++; // Narrow the limit (Checked all the cells on the top)
			
			// Move Down
			for (int i = top; i <= bottom; i++) {
				result.add(matrix[i][right]);
			}
			right--; // Narrow the limit (Checked all the cells on the right)
			
			// Check the valid limit
			if (!(top <= bottom && left <= right)) break;
			
			// Move Left
			for (int i = right; i >= left; i--) {
				result.add(matrix[bottom][i]);
			}
			bottom--; // Narrow the limit (Checked all the cells on the bottom)
			
			// Move Up
			for (int i = bottom; i >= top; i--) {
				result.add(matrix[i][left]);
			}
			left++; // Narrow the limit (Checked all the cells on the left)
		}
		return result;
	}
    
    public static void main(String[] args) {
    	SpiralMatrix sol = new SpiralMatrix();
		System.out.println(sol.spiralOrder(new int[][] {{1,2,3},{4,5,6},{7,8,9}}));
		System.out.println(sol.spiralOrder(new int[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12}}));
	}
}
