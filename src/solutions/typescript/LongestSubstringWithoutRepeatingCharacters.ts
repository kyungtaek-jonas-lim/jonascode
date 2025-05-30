
/*
 # Problem
 	- `Link`: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: May 30, 2025
 	- `Answer`: lengthOfLongestSubstring / lengthOfLongestSubstringAdvanced
*/


/*
    # Option #1
    - String
    - O(n^2)
*/
function lengthOfLongestSubstring(s: string): number {
    let result: number = 0;
    let curr: string = "";
    for (const c of s) {
        if (curr.includes(c)) {
            result = Math.max(result, curr.length);
            for (let i = curr.length; i >= 0; i--) {
                if (curr[i] == c) {
                    curr = curr.slice(i + 1);
                    break;
                }
            }
        }
        curr += c;
    }
    return Math.max(result, curr.length);
};

/*
    # Option #2
    - Map (for each character), Start Index
    - O(n)
*/
function lengthOfLongestSubstringAdvanced(s: string): number {
    let result: number = 0;
    const map = new Map<string, number>();
    let n: number = s.length, start: number = 0;

    for (let i = 0; i < n; i++) {
        const c = s[i];

        if (map.has(c) && map.get(c)! >= start) {
            result = Math.max(result, i - start)
            start = map.get(c)! + 1;
        }
        map.set(c, i);
    }
    return Math.max(result, n - start);
};