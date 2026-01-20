
/**
# Problem
	- `Link`: https://leetcode.com/problems/valid-parentheses/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 8, 2025
	- `Answer`: isValid / isValidIf
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

/*
# Option #2
- O(n)
*/
function isValidIf(s: string): boolean {

    const stack: string[] = [];

    for (const c of s) {
        if (c === "(" || c === "{" || c === "[") { // Open
            stack.push(c);
        } else { // Close
            if (stack.length === 0) return false;
            const last: string = stack.pop()!;
            if (last === "(" && c !== ")") return false;
            else if (last === "{" && c !== "}") return false;
            else if (last === "[" && c !== "]") return false;
        }
    }

    return stack.length === 0;
};