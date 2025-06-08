

/**
 # Problem
 	- `Link`: https://leetcode.com/problems/non-overlapping-intervals/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: June 8, 2025
 	- `Answer`: eraseOverlapIntervals
 */

/*
# Option #1
- O(n log n)
*/
function eraseOverlapIntervals(intervals: number[][]): number {
    
    intervals.sort((a, b) => a[0] - b[0]);

    const n: number = intervals.length;

    let prevEnd: number = intervals[0][1];
    let result: number = 0;
    for (let i = 1; i < n; i++) {
        if (prevEnd > intervals[i][0]) {
            result++;
            prevEnd = Math.min(prevEnd, intervals[i][1]);
        } else {
            prevEnd = intervals[i][1];
        }
        
    }
    return result;
};