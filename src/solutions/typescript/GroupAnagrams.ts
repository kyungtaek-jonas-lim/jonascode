
/**
# Problem
	- `Link`: https://leetcode.com/problems/group-anagrams/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 8, 2025
	- `Answer`: groupAnagrams / groupAnagramsBest / groupAnagramsSimple
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
function groupAnagramsBest(strs: string[]): string[][] {
    
    const map: Map<string, Array<string>> = new Map();

    for (const str of strs) {
        const count: number[] = new Array(26).fill(0); // Needed to be initialized as '0'
        
        for (const c of str) {
            count[c.charCodeAt(0) - 'a'.charCodeAt(0)]++;
        }

        const key = count.join("#");
        if (map.has(key)) {
            map.get(key)!.push(str);
        } else {
            map.set(key, [str]);
        }
    }

    return Array.from(map.values());
};


/*
# Option #3
- O(n * k log k) (n = strs.length, k = The average length of strs => split + sort + join = O(k log k))
- Simple (Simliar to Option #1)
- Jan 19, 2026
*/
function groupAnagramsSimple(strs: string[]): string[][] {
    const result: string[][] = [];
    const memo: Map<string, number> = new Map();

    for (const str of strs) {
        const sortedStr: string = str.split("").sort().join("");
        if (memo.has(sortedStr)) {
            result[memo.get(sortedStr)!].push(str);
        } else {
            memo.set(sortedStr, result.length);
            result.push([str]);
        }
    }

    return result;
};