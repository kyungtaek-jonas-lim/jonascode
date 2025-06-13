
/**
# Problem
	- `Link`: https://leetcode.com/problems/longest-palindromic-substring/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 13
	- `Answer`: longestPalindrome
 */

/*
# Option #1
- O(n^2)
*/
function longestPalindrome(s: string): string {
    
    const n: number = s.length;
    const result: number[] = [-1, -1];
    let resultLength: number = 0;

    for (let i = 0; i < n; i++) {
        let left: number = i, right: number = i;
        while (left >= 0 && right < n) {
            if (s[left] !== s[right]) {
                break;
            }
            const length = right - left + 1;
            if (resultLength < length) {
                result[0] = left;
                result[1] = right;
                resultLength = length;
            }
            left--, right++;
        }

        left = i - 1, right = i;
        while (left >= 0 && right < n) {
            if (s[left] !== s[right]) {
                break;
            }
            const length = right - left + 1;
            if (resultLength < length) {
                result[0] = left;
                result[1] = right;
                resultLength = length;
            }
            left--, right++;
        }
    }

    return s.substring(result[0], result[1] + 1);
};