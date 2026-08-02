package solutions.java;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
# Problem
	- `Link`: https://leetcode.com/problems/rotate-image/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 15, 2025
	- `Answer`: rotate / rotateAdvanced
 */
public class RotateImage {
	public static void main(String[] args) {
		rotate(new int[][] {{1,2,3},{4,5,6},{7,8,9}});
		rotate(new int[][] {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}});
	}
	
	/*
	# Option #1
    - Moving one row at a time (4 rows as a pair)
	- O(n^2)
	 */
    public static void rotate(int[][] matrix) {
    	
    	List<Queue<Integer>> tmp = new ArrayList<>();
    	tmp.add(new LinkedList<>());
    	tmp.add(new LinkedList<>());
    	tmp.add(new LinkedList<>());
    	tmp.add(new LinkedList<>());
    	
    	int m = matrix.length;
    	int top_left = 0;
    	int bottom_right = m - 1;
        
    	while (top_left < bottom_right) {
    		
    		// ---
    		// Data Save
    		// top & right
    		for (int i = top_left; i <= bottom_right; i++) {
    			tmp.get(0).add(matrix[top_left][i]);
    			tmp.get(1).add(matrix[i][bottom_right]);
    		}
    		
    		// bottom & left
    		for (int i = bottom_right; i >= top_left; i--) {
    			tmp.get(2).add(matrix[bottom_right][i]);
    			tmp.get(3).add(matrix[i][top_left]);
    		}
    		

    		// ---
    		// Data Update
    		// top & right
    		for (int i = top_left; i <= bottom_right; i++) {
    			matrix[top_left][i] = tmp.get(3).poll();
    			matrix[i][bottom_right] = tmp.get(0).poll();
    		}
    		
    		// bottom & left
    		for (int i = bottom_right; i >= top_left; i--) {
    			matrix[bottom_right][i] = tmp.get(1).poll();
    			matrix[i][top_left] = tmp.get(2).poll();
    		}
    		
    		top_left++;
    		bottom_right--;
    	}
    }
	
	/*
	# Option #2
    - Moving one cell at a time (4 cells as a pair)
	- O(n^2)
    - ref: https://www.youtube.com/watch?v=fMSJSS7eO1w
	 */
    public static void rotateAdvanced(int[][] matrix) {
    	
    	int top_left = 0, bottom_right = matrix.length - 1;
    	
    	while (top_left < bottom_right) {
    		for (int i = 0; i < bottom_right - top_left; i++) {
    			// i is the offset
    			// (+col from top-left, +row from top-right, -col form bottom-right, -row from bottom-left)
    			
    			// Save top-left
    			int top_left_val = matrix[top_left][top_left + i];
    			
    			// Move from bottom-left to top-left
    			matrix[top_left][top_left + i] = matrix[bottom_right - i][top_left];
    			
    			// Move from bottom-right to bottom-left
    			matrix[bottom_right - i][top_left] = matrix[bottom_right][bottom_right - i];
    			
    			// Move from top-right to bottom-right
    			matrix[bottom_right][bottom_right - i] = matrix[top_left + i][bottom_right];
    			
    			// Move from top-left to top-right
    			matrix[top_left + i][bottom_right] = top_left_val;
    		}
    		top_left++;
    		bottom_right--;
    	}
		
        // int s = 0, e = matrix.length - 1, n = matrix.length - 1;
        // while (s < e) {
        //     for (int i = s; i < e; i++) {
        //         int temp = matrix[s][i];
        //         matrix[s][i] = matrix[n - i][s];
        //         matrix[n - i][s] = matrix[e][n - i];
        //         matrix[e][n - i] = matrix[i][e];
        //         matrix[i][e] = temp;
        //     }

        //     s++;
        //     e--;
        // }
    }
}
