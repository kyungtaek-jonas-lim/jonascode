
/**
# Problem
	- `Link`: https://leetcode.com/problems/word-search/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 13, 2025
	- `Answer`: exist
 */

/*
# Option #1
- dfs
- O(m * n * 4^w) (w = the length of the word)
*/
function exist(board: string[][], word: string): boolean {
    
    const m: number = board.length, n: number = board[0].length;
    const wordLength: number = word.length;
    
    function dfs(x: number, y: number, index: number): boolean {
        if (x < 0 || x >= m || y < 0 || y >= n) return false;
        if (board[x][y] === '#') return false;
        if (word[index] !== board[x][y]) return false;
        if (index === wordLength - 1) return true;

        const temp = board[x][y];
        board[x][y] = '#';

        const result = (
            dfs(x + 1, y, index + 1)
            || dfs(x - 1, y, index + 1)
            || dfs(x, y + 1, index + 1)
            || dfs(x, y - 1, index + 1));

        board[x][y] = temp;

        return result;
    }

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (dfs(i, j, 0)) return true;
        }
    }
    return false;
};