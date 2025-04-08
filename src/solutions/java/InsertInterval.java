package solutions.java;

import java.util.ArrayList;
import java.util.List;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/insert-interval/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Jan 8, 2025 (insertBetter) / Jan 20, 2025 (insert)  / Apr 8, 2025 (insertAdvanced)
 	- `Answer`: insert / insertBetter / insertAdvanced
 */

public class InsertInterval {
	public static void main(String[] args) {
		
		int[][] result = insertAdvanced(new int[][] {{1,3},{6,9}}, new int []{2,5});
		for (int i = 0; i < result.length; i ++) {

			System.out.print("[ ");
			for (int j = 0; j < result[i].length; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println("]");
		}
		
	}

	/*
	 * Option #1 
	 * Complexed way
	 * O(n+m)≈O(n)
	 */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        // Edge Case #1 - intervals with zero length
        if (intervals.length == 0) return new int[][] {{newInterval[0], newInterval[1]}};

        // Edge Case #2 - newInterval less than the minimun of intervals or more than the maximum of intervals
        List<int[]> list = new ArrayList<>();
        boolean addLast = false;
        if (newInterval[1] < intervals[0][0]) {
            list.add(new int[] {newInterval[0], newInterval[1]});
        } else if (newInterval[0] > intervals[intervals.length - 1][1]) {
            addLast = true;
        }
        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;
        boolean process = false;
		for (int i = 0 ; i < intervals.length; i++) {
            if (!addLast) { // If the newinterval is already bigger than all the items of intervals, no need.

                // Mergeing items
                if (intervals[i][0] <= newInterval[1] && intervals[i][1] >= newInterval[0]) {
                    start = Math.min(start, Math.min(intervals[i][0], newInterval[0]));
                    end = Math.max(end, Math.max(intervals[i][1], newInterval[1]));
                    process = true;
                    continue;
                }

                // For merged Items
                if (process) {
                    list.add(new int[] {start, end});
                    process = false;
                }

                // Edge Case #3 - newIntervals between items of intervals
                if (i > 0
                    && intervals[i - 1][1] < newInterval[0]
                    && intervals[i][0] > newInterval[1]) {
                        list.add(newInterval);
                    }
            }
			list.add(new int[] {intervals[i][0], intervals[i][1]});
		}
        // Edge Case #2 - newInterval more than the maximum of intervals
        if (addLast) {
            list.add(new int[] {newInterval[0], newInterval[1]});
        }
        // Edge Case #4 - ends before the merged item put
        else if (process) {
        	list.add(new int[] {start, end});
        	// process = false;
        }
        return list.toArray(new int[list.size()][]);
    }

    
    /*
     * Option #2
     * Better / Common way
     * O(n)
     */
    public static int[][] insertBetter(int[][] intervals, int[] newInterval) {
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

    
    /*
     * Option #3
     * Simple way
     * O(n)
     * ref: https://www.youtube.com/watch?v=A8NUOmlwOlM
     */
    public static int[][] insertAdvanced(int[][] intervals, int[] newInterval) {
        
    	List<int[]> list = new ArrayList<>();
    	
    	for (int i = 0; i < intervals.length; i++) {
    		
    		int[] interval = intervals[i];
    		
    		if (newInterval[1] < interval[0]) {
    			list.add(newInterval);
    			for (int j = i; j < intervals.length; j++) {
    				list.add(intervals[j]);
    			}
    			return list.toArray(new int[list.size()][]);
    			
    		} else if (newInterval[0] <= interval[1]) {
    			newInterval[0] = Math.min(newInterval[0], interval[0]);
    			newInterval[1] = Math.max(newInterval[1], interval[1]);
    			
    		} else {
    			list.add(interval);
    		}
    	}
    	
    	list.add(newInterval);
    	return list.toArray(new int[list.size()][]);
    }
}
