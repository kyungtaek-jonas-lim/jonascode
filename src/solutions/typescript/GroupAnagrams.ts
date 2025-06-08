
/**
# Problem
	- `Link`: https://leetcode.com/problems/group-anagrams/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 8, 2025
	- `Answer`: groupAnagrams / groupAnagramsDifferent
 */


/*
# Option #1
- O(n * k log k) (n = strs.length, k = The average length of strs => split + sort + join = O(k log k))
*/
function groupAnagrams(strs: string[]): string[][] {
    
    const map = new Map<string, string[]>();
    for (let i = 0; i < strs.length; i++) {
        const sorted: string = strs[i].split('').sort().join('');
        if (!map.has(sorted)) {
            map.set(sorted, []);
        }
        map.get(sorted)!.push(strs[i]);
    }

    const result: string[][] = [];
    for (const arr of map.values()) {
        result.push(arr);
    }
    return result;
};


/*
# Option #2
- O(n * k)
*/
function groupAnagramsDifferent(strs: string[]): string[][] {
    
    const map = new Map<string, string[]>();
    const codeAta = 'a'.charCodeAt(0);
    for (let i = 0; i < strs.length; i++) {
        const counts = new Array(26).fill(0);
        const cArray: string[] = strs[i].split('');
        for (const c of cArray) {
            counts[c.charCodeAt(0) - codeAta]++;
        }
        const key = counts.join('#');
        if (!map.has(key)) {
            map.set(key, []);
        }
        map.get(key)!.push(strs[i]);
    }
    return Array.from(map.values());
};