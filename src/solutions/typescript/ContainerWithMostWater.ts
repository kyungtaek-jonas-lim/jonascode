
/**
 # Problem
 	- `Link`: https://leetcode.com/problems/container-with-most-water/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: June 2, 2025
 	- `Answer`: maxArea
*/
function maxArea(height: number[]): number {
    
    const n: number = height.length;
    let left: number = 0, right: number = n - 1;
    let result: number = 0;

    while (left < right) {

        // Update result
        result = Math.max(result, Math.min(height[left], height[right]) * (right - left));
        
        // Move the small height index
        if (height[left] < height[right]) {
            left++;
        } else {
            right--;
        }
    }

    return result;
};