package solutions.java;

import java.util.Arrays;

/*
# Problem
	- `Link`: https://leetcode.com/problems/meeting-rooms/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 16, 2025
	- `Answer`: canAttendMeetings
 */
public class MeetingRooms {
	
	// Definition for an interval.
	public class Interval {
	    int start;
	    int end;
	    Interval() { start = 0; end = 0; }
	    Interval(int s, int e) { start = s; end = e; }
	}

	/*
    # Option #1
    - Sort (O(n log n)) + Iterate (O(n))
    - O(n log n)
	 */
    public static boolean canAttendMeetings(Interval[] intervals) {
    	
    	if (intervals.length == 0) return true;
    	
    	// Sort
    	Arrays.sort(intervals, (a, b) -> a.start - b.start);
    	
    	int end = intervals[0].end;
    	
    	for (int i = 1; i < intervals.length; i++) {
    		Interval interval = intervals[i];
    		if (end > interval.start) return false; // Next start before it ends
    		end = interval.end;
    	}
    	
        return true;
    }
}
