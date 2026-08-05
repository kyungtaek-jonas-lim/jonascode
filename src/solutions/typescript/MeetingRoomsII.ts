
/**
# Problem
	- `Link`
        - `LeetCode`: https://leetcode.com/problems/meeting-rooms-ii/
        - `LintCode`: https://www.lintcode.com/problem/919/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 13, 2025
	- `Answer`: minMeetingRooms / minMeetingRoomsAdvanced / minMeetingRoomsBest
 */

export class Interval {
    start :number;
    end :number;
    constructor(start :number, end :number) {
        this.start = start;
        this.end = end;
    }
}

export class Solution {
  /**
   * @param intervals: an array of meeting time intervals
   * @return: the minimum number of conference rooms required
   */

/*
# Option #1
- O(n²)
- List, Splice
*/
  minMeetingRooms(intervals: Interval[]): number {
    // Write your code here

    const n: number = intervals.length;
    if (n <= 1) return 1;

    intervals.sort((a, b) => a.start - b.start);
    const ends: number[] = [];

    let result: number = 1;
    for (const interval of intervals) {

      let rooms: number = 1;
      for (let i = ends.length - 1; i >= 0; i--) {
        if (ends[i] > interval.start) {
          rooms++;
        } else {
          ends.splice(i, 1);
        }
      }
      result = Math.max(result, rooms);
      ends.push(interval.end);
    }
    return result;
  }

  
/*
# Option #2
- O(n log n)
- Pointers with two separate arrays (starts, ends)
*/
  minMeetingRoomsAdvanced(intervals: Interval[]): number {
    // Write your code here
    
    const n: number = intervals.length;
    if (n <= 1) return 1;
    
    const starts: number[] = [], ends: number[] = [];
    for (const interval of intervals) {
      starts.push(interval.start);
      ends.push(interval.end);
    }

    starts.sort((a, b) => a - b);
    ends.sort((a, b) => a - b);

    let result: number = 1;
    let startIndex: number = 0, endIndex: number = 0;
    while (startIndex < n) {
      if (starts[startIndex] < ends[endIndex]) {
        startIndex++;
        result = Math.max(result, startIndex - endIndex);
      } else {
        endIndex++;
      }
    }
    
    return result;
  }
}



/*
# Option #3
- O(n log n)
- Pointers with two separate arrays (starts, ends)
- August 5, 2026
*/
function minMeetingRoomsBest(intervals: Interval[]): number {
    
    const n: number = intervals.length;
    if (n <= 1) return n;

    // const starts: number[] = intervals.map(i => i.start).sort((a, b) => a - b);
    // const ends: number[] = intervals.map(i => i.end).sort((a, b) => a - b);
    const starts: number[] = [], ends: number[] = [];
    for (const i of intervals) {
        starts.push(i.start);
        ends.push(i.end);
    }
    starts.sort((a, b) => a - b);
    ends.sort((a, b) => a - b);

    let i: number = 0, j: number = 0, current: number = 0, result: number = 1;

    while (i < n) {
        if (ends[j] <= starts[i]) {
            current--;
            j++;
        } else {
            current++;
            result = Math.max(result, current);
            i++;
        }
    }

    return result;
}
