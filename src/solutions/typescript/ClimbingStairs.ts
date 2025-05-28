
/*
 # Problem
 	- `Link`: https://leetcode.com/problems/climbing-stairs/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: May 28, 2025
 	- `Answer`: climbStairs
 # Reference
	- https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/doc/explanation/ClimbingStairs.md
 */

function climbStairs(n: number): number {
    
    if (n <= 2) return n;

    let prev: number = 1, curr: number = 2;
    let temp: number = 0;

    for (let i: number = 3; i <= n; i++) {
        temp = curr;
        curr += prev;
        prev = temp;
    }
    return curr;
};