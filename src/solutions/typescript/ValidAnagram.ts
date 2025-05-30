
/*
 # Problem
 	- `Link`: https://leetcode.com/problems/valid-anagram/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: May 30, 2025
 	- `Answer`: isAnagram
 # Reference
 	- Anagrams
 		- Both strings must have the same length.
		- Both strings must have the exact same character counts.
*/

function isAnagram(s: string, t: string): boolean {
    
    let n: number = s.length, m: number = t.length;
    if (n != m) return false;

    const cnt: number[] = Array(128).fill(0);
    for (const c of s) {
        cnt[c.charCodeAt(0)]++;
    }

    for (const c of t) {
        cnt[c.charCodeAt(0)]--;
        if (cnt[c.charCodeAt(0)] < 0) return false;
    }

    return true;
};