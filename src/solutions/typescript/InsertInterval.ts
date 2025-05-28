
/*
 # Problem
 	- `Link`: https://leetcode.com/problems/insert-interval/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: May 28, 2025
 	- `Answer`: insert / insertAdvanced
*/

/*
    # Option #1
    - O(n)
*/
function insert(intervals: number[][], newInterval: number[]): number[][] {
    
    let n: number = intervals.length;

    let result: number[][] = [];
    let inserted: boolean = false;
    for (let i: number = 0; i < n; i++) {
        const interval: number[] = intervals[i];

        if (!inserted && newInterval[1] < interval[0]) {
            result.push(newInterval);
            inserted = true;
        }

        if (!inserted && newInterval[1] >= interval[0] && newInterval[0] <= interval[1]) {
            newInterval[0] = Math.min(interval[0], newInterval[0]);
            newInterval[1] = Math.max(interval[1], newInterval[1]);
        } else {
            result.push(interval);
        }
    }

    if (!inserted) {
        result.push(newInterval);
    }
    return result;
};


/*
    # Option #2
    - Simple way
    - O(n)
    - ref: https://www.youtube.com/watch?v=A8NUOmlwOlM
*/
function insertAdvanced(intervals: number[][], newInterval: number[]): number[][] {
    
    let n: number = intervals.length;

    let result: number[][] = [];
    for (let i: number = 0; i < n; i++) {
        const interval: number[] = intervals[i];
        if (newInterval[1] < interval[0]) {
            result.push(newInterval);
            result.push(...intervals.slice(i));
            return result;
        } else if (newInterval[1] >= interval[0] && newInterval[0] <= interval[1]) {
            newInterval[0] = Math.min(newInterval[0], interval[0]);
            newInterval[1] = Math.max(newInterval[1], interval[1]);
        } else {
            result.push(interval);
        }
    }
    result.push(newInterval);
    return result;
};