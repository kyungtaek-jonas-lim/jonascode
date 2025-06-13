
/**
# Problem
	- `Link`: https://leetcode.com/problems/palindromic-substrings/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 13
	- `Answer`: countSubstrings
 */


/*
# Option #1
- O(n^2)
*/
function countSubstrings(s: string): number {
    
    const n: number = s.length;
    let result: number = 0;

    for (let i = 0; i < n; i++) {
        let left: number = i, right: number = i;
        while (left >= 0 && right < n) {
            if (s[left] !== s[right]) {
                break;
            }
            result++;
            left--, right++;
        }

        left = i - 1, right = i;
        while (left >= 0 && right < n) {
            if (s[left] !== s[right]) {
                break;
            }
            result++;
            left--, right++;
        }
    }

    return result;
};