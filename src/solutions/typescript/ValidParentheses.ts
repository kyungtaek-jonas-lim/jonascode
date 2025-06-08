
/**
# Problem
	- `Link`: https://leetcode.com/problems/valid-parentheses/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 8, 2025
	- `Answer`: isValid
 */


/*
# Option #1
- O(n)
*/
function isValid(s: string): boolean {
    
    const map = new Map<string, string>();
    map.set(')', '(');
    map.set('}', '{');
    map.set(']', '[');
    
    const result: string[] = [];
    for (let i = 0; i < s.length; i++) {
        if (map.has(s[i])) {
            if (result.length == 0 || result.pop() !== map.get(s[i])) return false;
        } else {
            result.push(s[i]);
        }
    }
    return result.length === 0;
};