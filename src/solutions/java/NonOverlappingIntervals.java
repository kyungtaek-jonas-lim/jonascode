package solutions.java;

import java.util.Arrays;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/non-overlapping-intervals/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 7, 2025
 	- `Answer`: eraseOverlapIntervals / eraseOverlapIntervalsAdvanced
 */
public class NonOverlappingIntervals {
	public static void main(String[] args) {

	    System.out.println(eraseOverlapIntervals(new int[][] {{1,2},{2,3},{3,4},{1,3}})); // 1
	    System.out.println(eraseOverlapIntervals(new int[][] {{1,2},{1,2},{1,2}})); // 2
	    System.out.println(eraseOverlapIntervals(new int[][] {{1,2},{2,3}})); // 0
	    System.out.println(eraseOverlapIntervals(new int[][] {{0,2},{1,3},{2,4},{3,5},{4,6}})); // 2
	    System.out.println(eraseOverlapIntervals(new int[][] {{-52,31},{-73,-26},{82,97},{-65,-11},{-62,-49},{95,99},{58,95},{-31,49},{66,98},{-63,2},{30,47},{-40,-26}})); // 7

	    System.out.println("---");

	    System.out.println(eraseOverlapIntervalsAdvanced(new int[][] {{1,2},{2,3},{3,4},{1,3}})); // 1
	    System.out.println(eraseOverlapIntervalsAdvanced(new int[][] {{1,2},{1,2},{1,2}})); // 2
	    System.out.println(eraseOverlapIntervalsAdvanced(new int[][] {{1,2},{2,3}})); // 0
	    System.out.println(eraseOverlapIntervalsAdvanced(new int[][] {{0,2},{1,3},{2,4},{3,5},{4,6}})); // 2
	    System.out.println(eraseOverlapIntervalsAdvanced(new int[][] {{-52,31},{-73,-26},{82,97},{-65,-11},{-62,-49},{95,99},{58,95},{-31,49},{66,98},{-63,2},{30,47},{-40,-26}})); // 7

	}
	
	/*
	 # Option #1
	 - O (n log n) 
	 */
    public static int eraseOverlapIntervals(int[][] intervals) {
        
    	// Sort the intervals in ascending order based on the start value
    	Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
    	
    	// Initialize with the end value of the first interval
    	int max = intervals[0][1];
    	
    	// Overlapping interval Count
    	int result = 0;
    	int start = 0, end = 0;
    	for (int i = 1; i < intervals.length; i++) {
    		
    		start = intervals[i][0];
    		end = intervals[i][1];
    		
    		// If it's overlapped, count up and reset the max variable to the min value
    		if (start < max) {
    			result ++;
    			max = Math.min(max, end);
    			
    		} else { // If it's not overlapped, reset the max variable to the max value.
    			max = end;
    		}
    	}
    	
    	return result;
    }
	
	/*
	 # Option #2
	 - O(n log n)
	 */
	public static int eraseOverlapIntervalsAdvanced(int[][] intervals) {
		
		// Sort intervals by their end time
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
		
		// Track the end of the last added interval
		int end = Integer.MIN_VALUE;
		int count = 0; // Count of non-overlapping intervals
		
		for (int[] interval: intervals) {
			if (interval[0] >= end) {
				// No overlap, include this interval
				end = interval[1];
				count++;
			}
			// Else: overlap, so we skip this interval (i.e., remove it)
		}
		
		// Number of removals = total intervals - non-overlapping intervals kept
		return intervals.length - count;
	}
}
