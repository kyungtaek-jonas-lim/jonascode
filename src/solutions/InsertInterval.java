package solutions;

import java.util.ArrayList;
import java.util.List;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/insert-interval/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Jan 8, 2025
 	- `Answer`: insert
 */

public class InsertInterval {
	public static void main(String[] args) {
		
		int[][] result = insert(new int[][] {{1,3},{6,9}}, new int []{2,5});
		for (int i = 0; i < result.length; i ++) {

			System.out.print("[ ");
			for (int j = 0; j < result[i].length; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println("]");
		}
		
	}
	
    public static int[][] insert(int[][] intervals, int[] newInterval) {
    	List<int[]> result = new ArrayList<>();
    	
    	int currentIndex = 0;
    	int initialLengthOfIntervalsLength = intervals.length;

    	// Add the not overlapped ones & Detect the overlap
    	// : Add all intervals that come before the new interval
    	while (currentIndex < initialLengthOfIntervalsLength
    			&& intervals[currentIndex][1] < newInterval[0]) {
    		result.add(intervals[currentIndex]);
    		currentIndex++;
    	}

    	// Merge overlap part
    	// : Merge overlapping intervals with the new interval
    	while (currentIndex < initialLengthOfIntervalsLength
    			&& intervals[currentIndex][0] <= newInterval[1]) {
    		if (intervals[currentIndex][0] < newInterval[0]) {
    			newInterval[0] = intervals[currentIndex][0];
    		}
    		if (intervals[currentIndex][1] > newInterval[1]) {
    			newInterval[1] = intervals[currentIndex][1];
    		}
    		currentIndex++;
    	}
    	result.add(newInterval);
    	
    	// Deal with not overlapped ones after the overlap
    	// : Add all intervals that come after the new interval
    	for (int i = currentIndex; i < initialLengthOfIntervalsLength; i++) {
    		result.add(intervals[i]);
    	}
    	
    	// Convert the list to array
    	return result.toArray(new int[result.size()][]);
    }
}
