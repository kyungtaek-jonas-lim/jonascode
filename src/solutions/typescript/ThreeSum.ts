
/*
 # Problem
 	- `Link`: https://leetcode.com/problems/3sum/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: June 4, 2025
 	- `Answer`: threeSum / threeSumAdvanced
*/

/**
# Option #1
- O(n^2)
 */
function threeSum(nums: number[]): number[][] {
    
    nums.sort((a, b) => a - b);
    const result: number[][] = [];
    let n: number = nums.length;
    const set = new Set<string>();

    for (let i = 0; i < n; i++) {
        const num: number = nums[i];
        let left: number = i + 1, right: number = n - 1;
        while (left < right) {
            const sum: number = num + nums[left] + nums[right];
            if (sum === 0) {
                const candidates: string[] = [];
                candidates.push(String(num));
                candidates.push(",");
                candidates.push(String(nums[left]));
                candidates.push(",");
                candidates.push(String(nums[right]));
                const candidatesString: string = candidates.join("");
                if (!set.has(candidatesString)) {
                    result.push([num, nums[left], nums[right]]);
                    set.add(candidatesString);
                }
                left++;
                right--;
            } else if (sum < 0) {
                left++;
            } else {
                right--;
            }  
        }
    }
    return result;
};


/**
# Option #2
- O(n^2)
 */
function threeSumAdvanced(nums: number[]): number[][] {
    
    nums.sort((a, b) => a - b);

    const result: number[][] = [];
    let n: number = nums.length;

    for (let i = 0; i < n; i++) {

        // Edge Case: Avoid duplicate values
        if (i > 0 && nums[i] === nums[i - 1]) continue;

        const num: number = nums[i];
        let left: number = i + 1, right: number = n - 1;

        while (left < right) {
            const sum: number = num + nums[left] + nums[right];
            if (sum === 0) {
                result.push([num, nums[left], nums[right]]);
                left++;
                right--;
                
                // Edge Case: Avoid duplicate values
                while (left < right && nums[left] === nums[left - 1]) {
                    left++;
                }
                while (left < right && nums[right] === nums[right + 1]) {
                    right--;
                }
                
            } else if (sum < 0) {
                left++;
            } else {
                right--;
            }  
        }
    }
    return result;
};