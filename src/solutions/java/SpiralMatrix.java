package solutions.java;

import java.util.ArrayList;
import java.util.List;

/*
# Problem
	- `Link`: https://leetcode.com/problems/spiral-matrix/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 13, 2025
	- `Answer`: spiralOrder
 */
public class SpiralMatrix {

	/*
    # Option #1
    - O (m * n)
    - Space Complexity: O(m * n)
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
    
    public static void main(String[] args) {
    	SpiralMatrix sol = new SpiralMatrix();
		System.out.println(sol.spiralOrder(new int[][] {{1,2,3},{4,5,6},{7,8,9}}));
		System.out.println(sol.spiralOrder(new int[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12}}));
	}
}
