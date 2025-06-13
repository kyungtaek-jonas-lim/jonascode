
/**
# Problem
	- `Link`
        - `LeetCode`: https://leetcode.com/problems/meeting-rooms/
        - `LintCode`: https://www.lintcode.com/problem/920/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 13, 2025
	- `Answer`: canAttendMeetings
 */


export class Interval {
    start :number;
    end :number;
    constructor(start :number, end :number) {
        this.start = start;
        this.end = end;
    }
}


/*
# Option #1
- O(n log n)
*/
export class Solution {
  /**
   * @param intervals: an array of meeting time intervals
   * @return: if a person could attend all meetings
   */
  canAttendMeetings(intervals: Interval[]): boolean {

      const n: number = intervals.length;
      if (n <= 1) return true;
      
      intervals.sort((a, b) => a.start - b.start);
      let prevEnd: number = intervals[0].end;
      for (let i = 1; i < n; i++) {
          if (prevEnd > intervals[i].start) return false;
          prevEnd = intervals[i].end;
      }
      return true;
  }
}