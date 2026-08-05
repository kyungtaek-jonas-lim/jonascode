
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
function encode(strs: string[]): string {
    const result: string[] = []
    for (const s of strs) {
        result.push(s.length.toString(), '#', s);
    }
    return result.join('');
};

function decode(str: string): string[] {
    const result: string[] = [];
    const n: number = str.length;
    let start: number = 0;
    while (start < n) {
        const sharp: number = str.indexOf('#', start);
        const count: number = Number(str.slice(start, sharp));
        start = sharp + 1 + count;
        result.push(str.slice(sharp + 1, start));
    }
    return result;
};