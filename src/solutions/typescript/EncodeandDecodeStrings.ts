
/**
# Problem
	- `Link`
        - `LeetCode`: https://leetcode.com/problems/encode-and-decode-strings/
        - `LintCode`: https://www.lintcode.com/problem/659/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 13
	- `Answer`: encode / decode
 */

/*
# Option #1
- encode: O(n * L) (L === the average length of strings)
- decode: o(n)
*/
export class Solution {
    /*
     * @param strs: a list of strings
     * @return: encodes a list of strings to a single string.
     */
    encode(strs: string[]): string {
        const n: number = strs.length;
        const result: string[] = [];
        for (let i = 0; i < n; i++) {
            result.push(`${strs[i].length}#${strs[i]}`);
        }
        return result.join('');
        // return strs.map(s => `${s.length}#${s}`).join('');
    }


    /*
     * @param str: A string
     * @return: decodes a single string to a list of strings
     */
    decode(str: string): string[] {
        const result: string[] = [];
        let start: number = 0;
        let find: number = str.indexOf('#', start);
        while (find !== -1) {
            const length = Number(str.substring(start, find));

            start = find + 1 + length;
            result.push(str.substring(find + 1, start));

            find = str.indexOf('#', start);
        }
        return result;
    }
}
