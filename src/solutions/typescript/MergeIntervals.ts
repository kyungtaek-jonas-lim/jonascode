
/*
# Problem
	- `Link`: https://leetcode.com/problems/merge-intervals/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 2, 2025
	- `Answer`: merge
*/

function merge(intervals: number[][]): number[][] {
    
    // Sort based on the first element of each interval
    intervals.sort((a, b) => a[0] - b[0]);

    // Put the first interval
    const result: number[][] = [];
    result.push(intervals[0]);
    let prevEnd: number = intervals[0][1];

    // Compare and make the result array
    for (let i = 1; i < intervals.length; i++) {
        const interval: number[] = intervals[i];
        if (prevEnd >= interval[0]) {
            prevEnd = Math.max(prevEnd, interval[1]);
            result[result.length - 1][1] = prevEnd;
        } else {
            result.push(interval);
            prevEnd = interval[1];
        }
    }

    return result;
};