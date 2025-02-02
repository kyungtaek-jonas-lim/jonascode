package solutions.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
# Problem
	- `Link`: https://leetcode.com/problems/merge-intervals/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Feb 1, 2025
	- `Answer`: merge / mergeBetter / mergeAdvanced 
*/

public class MergeIntervals {
	public static void main(String[] args) {
		int[][] result = null;
		
		result = mergeAdvanced(new int[][] { {1, 6}, {8, 10}, {15, 18} });
		print(result);
		
		result = mergeAdvanced(new int[][] { {1, 4}, {4, 5} });
		print(result);
	}

	public static void print(int[][] result) {
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * @option 1
	 * @description Common way
	 * @timeComplexity O(n² log n) - Sorting(n² log n) + Loop(n² log n)
	 * @param intervals
	 * @return
	 */
    public static int[][] merge(int[][] intervals) {

    	// Edge Case: If the array has only one interval, return it as no merging is needed.
        if (intervals.length == 1) return intervals;

        
        // --------------------------------
    	// Step 1: Put all the numbers in TreeMap so they are sorted by the Binary Search Tree
    	// O(n² log n)
    	TreeMap<Integer, Boolean> map = new TreeMap<>(); // Boolean means if it is the end of the intervals of not.
    	for (int i = 0; i < intervals.length; i++) {
    		for (int j = intervals[i][0]; j < intervals[i][1]; j++) {
    			map.put(j, false); // Each map.put(j, false) operation takes O(log k) time
    		}
    		map.put(intervals[i][1], map.getOrDefault(intervals[i][1], true)); // If the biggest number of the interval is already set, it stays with the same boolean but if it's not, mark it as ends of the interval.
    	}

    	
        // --------------------------------
    	// Step 2: Generate Interval Arrays with List
    	// O(n² log n)
    	List<int[]> list = new ArrayList<>();
    	int start = map.firstKey();
    	for (int i: map.keySet()) {
    		if (map.get(i)) { // If it's the ends of intervals
    			list.add(new int[] {start, i});
    			
    			// If there's the next interval
    			Integer next = map.higherKey(i);
    			if (next == null) break;
    			start = next.intValue();
    		}
    	}
    	
    	// Step 3: List to Array
    	return list.toArray(new int[list.size()][]);
    }
    
    
    /**
	 * @option 2
	 * @description Better way
	 * @timeComplexity O(n log n) - Sorting(O(n log n)) + Loop(O(n log n)) 
     * @param intervals
     * @return
     */
    public static int[][] mergeBetter(int[][] intervals) {

    	// Edge Case: If the array has only one interval, return it as no merging is needed.
        if (intervals.length == 1) return intervals;

        
        // --------------------------------
        // Step 1: Record only start and end numbers of the intervals, Start +1, End -1
        // O(n log n)
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] interval : intervals) {
        	// Sweep Line Algorithm

        	// Mark the start of an interval with +1 (indicating a new interval starts here)
            map.put(interval[0], map.getOrDefault(interval[0], 0) + 1);
            
            // Mark the end of an interval with -1 (indicating an interval end here)
            // This ensures that overlapping intervals are properly handled.
            map.put(interval[1], map.getOrDefault(interval[1], 0) - 1);
        }
        
        /*
         * Interpretation of values in TreeMap:
         * - Positive value (>0): Indicates that a new interval starts at this point.
         *   - Example: If value is +2, it means two new intervals start at this point.
         * 
         * - Negative value (<0): Indicates that one or more intervals end at this point.
         *   - Example: If value is -1, it means one interval ends at this point.
         *   - Example: If value is -2, two intervals end here.
         * 
         * - Zero (0) may still exist in TreeMap in some cases:
         *   - When adding a new key, if its value becomes 0 immediately, it will not be stored.
         *   - However, if a key already exists and later its value becomes 0 through calculations, it will remain in the TreeMap
         *   - Example:
         *     map.put(1, +1); // Interval starts at 1
         *     map.put(1, -1); // Interval ends at 1 (value becomes 0)
         *     -> In this case, key `1` will still exist in TreeMap with value `0`.
         *   
         *   - TreeMap does not automatically remove keys with value 0:
         *     - If required, manual removal (`map.remove(key)`) can be performed to clean up such cases.s
         */

        
        // --------------------------------
        // Step 2: Merge intervals based on active interval count
        // O(n log n)
        List<int[]> merged = new ArrayList<>();
        int active = 0, start = -1;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            
            // If no intervals are currently active, this marks the start of a new merged interval.
            if (active == 0) {
                start = key; // Store the start of this merged interval.
            }
            
            // Update active interval count:
            // - If value is positive (+), it means new intervals start at this point.
            // - If value is negative (-), it means some intervals end at this point.
            active += value; // it keeps adding so active means the current interval status.

            // If active becomes 0, it means there are no ongoing intervals,
            // so we have reached the end of a merged interval.
            if (active == 0) {
                merged.add(new int[]{start, key}); // Store the merged interval.
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

	
	/**
	 * @option 3
	 * @description Advanced way
	 * @timeComplexity O(n log n) - Sorting(O(n log n)) + Loop(O(n))
	 * @param intervals
	 * @return
	 */
    public static int[][] mergeAdvanced(int[][] intervals) {
    	
    	// Edge Case: If the array has only one interval, return it as no merging is needed.
        if (intervals.length == 1) return intervals;

        
        // --------------------------------
        // Step 1: Sort the intervals based on the start time (first element of each interval).
        // Sorting ensures that overlapping intervals are adjacent, making merging easier.
        // O(n log n)
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); // sorts the intervals array based on the start value of each interval.

        
        // --------------------------------
        // Step 2: Iterate through the sorted intervals and merge overlapping ones.
        // O(n)
        List<int[]> list = new ArrayList<>(); // Stores the merged intervals
        int[] current = intervals[0]; // Initialize with the first interval
        list.add(current); // Add the first interval to the result list
        
        for (int i = 1; i < intervals.length; i++) {
            int[] next = intervals[i]; // Get the next interval
            
            // current[0] will always less than(/equal to) next[0]
            
            // Check if the current interval overlaps with the next interval.
            // Overlap condition: current interval's end >= next interval's start.
            if (current[1] >= next[0]) {
            	// Merge by extending the current interval's end time to the maximum of both intervals.
                current[1] = Math.max(current[1], next[1]); // Even though the current was added to the list, the value in the list will be changed cause it was a shallow copy.
                
            } else { // No overlap, move to the next interval
            	// Add it to the result list and update the `current` reference.
                current = next;
                list.add(current);
            }
        }

        return list.toArray(new int[list.size()][]);
    }
    
}
