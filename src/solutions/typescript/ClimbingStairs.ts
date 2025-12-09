
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
    

/*
# Option #1
- DFS with Memoization
- O(n)
*/
function climbStairsAdvanced(n: number): number {
    if (n < 3) return n;
    const result: number[] = new Array(n + 1).fill(0);
    result[n] = 1;
    result[n - 1] = 1;
    for (let i = n - 2; i >= 0; i--) {
        result[i] = result[i + 1] + result[i + 2];
    }
    return result[0];
}


/*
# Option #2
- DFS with Memoization & Space Optimization
- O(n)
*/
function climbStairsBest(n: number): number {
    let prev: number = 1, curr: number = 1;
    for (let i = n - 2; i >= 0; i--) {
        const temp = curr;
        curr += prev;
        prev = temp;
    }
    return curr;
};