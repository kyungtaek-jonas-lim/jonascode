
/*
 # Problem
 	- `Link`: https://leetcode.com/problems/valid-anagram/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: May 30, 2025
 	- `Answer`: isAnagram / isAnagramSimple / isAnagramBest
 # Reference
 	- Anagrams
 		- Both strings must have the same length.
		- Both strings must have the exact same character counts.
*/

/*
* Option #1
* O(n)
* Space: O(1)
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

/*
* Option #2
* O(n)
* Space: O(k) - k: distinct chars, up to O(n)
*/
function isAnagramSimple(s: string, t: string): boolean {
    const n: number = s.length, m: number = t.length;
    if (n !== m) return false;
    const map: Map<string, number> = new Map();
    for (let i = 0; i < n; i++) {
        const c: string = s.charAt(i);
        map.set(c, (map.get(c) ?? 0) + 1);
    }
    for (let i = 0; i < n; i++) {
        const c: string = t.charAt(i);
        if ((map.get(c) ?? 0) === 0) {
            return false;
        }
        map.set(c, map.get(c)! - 1);
    }
    return true;
};

/*
* Option #3
* O(n)
* Space: O(1)
*/
function isAnagramBest(s: string, t: string): boolean {
    if (s.length !== t.length) return false;

    const cnt: Array<number> = new Array(128).fill(0);
    const n: number = s.length;

    for (let i = 0; i < n; i++) {
        cnt[s.charCodeAt(i)]++;
        cnt[t.charCodeAt(i)]--;
    }

    for (const c of cnt) {
        if (c !== 0) return false;
    }

    return true;
};