package solutions.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
# Problem
	- `Link`
        - `LeetCode`: https://leetcode.com/problems/meeting-rooms-ii/
        - `LintCode`: https://www.lintcode.com/problem/919/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 16, 2025
	- `Answer`: minMeetingRooms / minMeetingRoomsLineSweep / minMeetingRoomsHeap
 */
public class MeetingRoomsII {
	
	// Definition for an interval.
	public class Interval {
	    int start;
	    int end;
	    Interval() { start = 0; end = 0; }
	    Interval(int s, int e) { start = s; end = e; }
	}
	
	/*
	# Option #1
	- O(n^2)
	 */
    public int minMeetingRooms(Interval[] intervals) {
    	
    	int intervalsLength = intervals.length;
    	if (intervalsLength <= 1) return intervalsLength;
    	
    	// Sort
    	Arrays.sort(intervals, (a, b) -> {
    		if (a.start != b.start)
    			return a.start - b.start;
    		else
    			return a.end - b.end;
    	});
    	
    	int result = 1;
    	
    	List<Interval> list = new ArrayList<>();
    	for (int i = 1; i < intervalsLength; i++) {
    		Interval interval = intervals[i];
    		
    		int currentDesiredCnt = 1;
    		for (int j = 0; j < list.size(); j++) {
    			Interval pre = list.get(j);
    			if (pre.end > interval.start) {
    				currentDesiredCnt++;
    			} else {
    				list.remove(j);
    				j--;
    			}
    		}
    		result = Math.max(result, currentDesiredCnt);
    		
    		list.add(interval);
    		
    	}
    	return result;
    	
    }
	
	/*
	# Option #2
	- Line Sweep
	- O(n log n)
	- ref: https://www.youtube.com/watch?v=FdzJmTCVyJU
	 */
    public int minMeetingRoomLineSweep(List<Interval> intervals) {
        final int n = intervals.size();
        if (n <= 1) return n;

        List<Integer> starts = new ArrayList<>();
        List<Integer> ends = new ArrayList<>();

        for (Interval i: intervals) {
            starts.add(i.start);
            ends.add(i.end);
        }

        starts.sort((a, b) -> a - b);
        ends.sort((a, b) -> a - b);

        int i = 0, j = 0, current = 0, result = 1;
        while (i < n) {
            if (starts.get(i) >= ends.get(j)) {
                current--;
                j++;
            } else {
                current++;
                i++;
                result = Math.max(result, current);
            }
        }
        return result;
    }
	
	/*
	# Option #3
	- Heap
	- O(n log n)
	 */
    public int minMeetingRoomHeap(Interval[] intervals) {
    	
    	// Edge
    	int intervalsLength = intervals.length;
    	if (intervalsLength <= 1) return intervalsLength;
    	
    	// Sort with starts
    	Arrays.sort(intervals, (a, b) -> a.start - b.start);
    	
    	// To-be-sorted with ends
    	int result = 1;
    	PriorityQueue<Integer> heap = new PriorityQueue<>();
    	heap.offer(intervals[0].end);
    	
    	for (int i = 1; i < intervalsLength; i++) {
    		Interval interval = intervals[i];
    		
    		// Dismiss previous meetings
    		while (!heap.isEmpty() && heap.peek() <= interval.start) {
    			heap.poll();
    		}
    		heap.offer(interval.end);
    		result = Math.max(result, heap.size());
    	}
    	return result;
    }
}
