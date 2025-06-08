
/*
 # Problem
 	- `Link`: https://leetcode.com/problems/word-break/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: June 8, 2025
 	- `Answer`: wordBreak_dfs / wordBreak_dp
*/

/*
# Option #1
- Top-Bottom
- O(n * m * k) (k === the average length of words in wordDict)
*/
function wordBreak_dfs(s: string, wordDict: string[]): boolean {
    
    const n: number = s.length, m: number = wordDict.length;
    const map = new Map<number, boolean>();

    function dfs(index: number): boolean {
        
        if (index > n) return false;
        if (index == n) return true;
        if (map.has(index)) return map.get(index)!;
        const restLength = n - index;

        let result: boolean = false;

        for (let i = 0; i < m; i++) {
            const wordLength = wordDict[i].length;
            if (restLength < wordLength) continue;
            if (s.substring(index, index + wordLength) === wordDict[i]) {
                if (dfs(index + wordLength)) {
                    result = true;
                    map.set(index, result);
                    return result;
                }
            }
        }
        map.set(index, result);
        return false;
    }

    return dfs(0);
};


/*
# Option #2
- Bottom-Top
- O(n * m * k) (k === the average length of words in wordDict)
*/
function wordBreak_dp(s: string, wordDict: string[]): boolean {
    
    const n: number = s.length, m: number = wordDict.length;
    const dp: boolean[] = new Array(n + 1).fill(false);
    dp[0] = true;

    for (let i = 0; i < n; i++) {
        if (!dp[i]) continue;
        const restLength  = n - i;
        for (let j = 0; j < m; j++) {
            const wordLength = wordDict[j].length;
            if (restLength < wordLength) continue;
            if (s.substring(i, i + wordLength) === wordDict[j]) {
                dp[i + wordLength] = true;
            }
        }
    }
    return dp[n];
};