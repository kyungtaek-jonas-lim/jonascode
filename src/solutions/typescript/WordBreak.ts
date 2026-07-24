
/*
 # Problem
 	- `Link`: https://leetcode.com/problems/word-break/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: June 8, 2025
 	- `Answer`: wordBreakDfs1 / wordBreakDfs2 / wordBreakDp1 / wordBreakDp2 / wordBreakDp3
*/

/*
# Option #1
- Top-down
- O(n * m * k) (k === the average length of words in wordDict)
*/
function wordBreakDfs1(s: string, wordDict: string[]): boolean {
    
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
- Top-down
- O(n * m * k) (k === the average length of words in wordDict)
- July 22, 2026
*/
function wordBreakDfs2(s: string, wordDict: string[]): boolean {
    
    const memo: Set<string> = new Set();

    function dfs(x: string): boolean {
        if (x.length === 0) return true;
        if (memo.has(x)) return false;

        for (const word of wordDict) {
            let result: boolean = false;
            if (x.startsWith(word)) {
                result = dfs(x.slice(word.length));
                if (result) return true;
            }
        }
        memo.add(x);
        return false;
    }

    return dfs(s);
};


/*
# Option #3
- Bottom-up
- O(n * m * k) (k === the average length of words in wordDict)
*/
function wordBreakDp1(s: string, wordDict: string[]): boolean {
    
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


/*
# Option #4
- Dynamic Programming
- O(n × k × m) (n = len(s), k = number of words in wordDict, L = average length of the words in wordDict)
- Jan 13, 2026
*/
function wordBreakDp2(s: string, wordDict: string[]): boolean {
    
    const n: number = s.length, m: number = wordDict.length;
    const dp: Array<boolean> = new Array(n + 1).fill(false);
    dp[0] = true;

    let i: number = 0;
    while (i < n) {
        if (!dp[i]) {
            i++;
            continue;
        }
        for (const word of wordDict) {
            const m: number = word.length;
            if (s.substring(i, i + m) === word) {
                dp[i + m] = true;
            }
        }
        i++;
    }

    return dp[n];
};


/*
# Option #5
- Dynamic Programming
- O(n × k × m) (n = len(s), k = number of words in wordDict, L = average length of the words in wordDict)
- July 22, 2026
*/
function wordBreakDp3(s: string, wordDict: string[]): boolean {
    const n: number = s.length;
    const dp: boolean[] = new Array(n + 1).fill(false);
    dp[0] = true;

    for (let i = 0; i < n; i++) {
        if (!dp[i]) continue;
        for (const w of wordDict) {
            if (s.slice(i).startsWith(w)) dp[i + w.length] = true;
        }
    }

    return dp[n];
};