
/*
# Problem
	- `Link`: https://leetcode.com/problems/longest-consecutive-sequence/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 10, 2025
	- `Answer`: longestConsecutive
*/

/*
# Option #1
- O(n)
*/
function longestConsecutive(nums: number[]): number {
    
    // const set = new Set<number>();
    const set = new Set<number>(nums);
    let result: number = 0;
    
    // for (const num of nums) {
    //     set.add(num);
    // }

    // for (const num of nums) { // Array is slower
    for (const num of set) {
        if (!set.has(num - 1)) {
            let temp: number = 1;
            let increasedNum: number = num + 1;
            while (set.has(increasedNum)) {
                temp++;
                increasedNum++;
            }
            result = Math.max(result, temp);
        }
    }

    return result;
};