
/*
# Problem
 	- `Link`: https://leetcode.com/problems/word-search-ii/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 18, 2025
	- `Answer`: TreeNodeCustom / TreeNodeCustom2 / findWordsTrie
*/

/*
===================================================================================
# Option #1
- Prefix Node (Trie)
- O((W * L) + m * n * 4^L * L) (W = The length of words list, L = the longest length of all the words)
*/
class TreeNodeCustom {
    children: Map<string, TreeNodeCustom>;
    end: boolean;

    constructor() {
        this.children = new Map();
        this.end = false;
    }
}

class Trie {
    node: TreeNodeCustom;
    memo: Map<string, boolean>;

    constructor() {
        this.node = new TreeNodeCustom();
        this.memo = new Map();
    }

    addWord(word: string): void {
        let curr: TreeNodeCustom = this.node;
        for (const c of word) {
            if (!curr.children.has(c)) {
                curr.children.set(c, new TreeNodeCustom());
            }
            curr = curr.children.get(c)!;
        }
        curr.end = true;
    }

    search(word: string, result: Set<string>): boolean {
        if (this.memo.has(word)) return this.memo.get(word)!;
        let curr: TreeNodeCustom = this.node;
        for (const c of word) {
            if (!curr.children.has(c)) {
                this.memo.set(word, false);
                return false;
            }
            curr = curr.children.get(c)!;
        }
        if (curr.end) {
            result.add(word);
            this.memo.set(word, curr.children.size > 0);
            return curr.children.size > 0;
        }
        this.memo.set(word, true);
        return true;
    }
}

function findWords(board: string[][], words: string[]): string[] {

    const m: number = board.length, n: number = board[0].length;
    const result: Set<string> = new Set();
    
    const trie: Trie = new Trie();
    for (const word of words) {
        trie.addWord(word);
    }

    function dfs(x: number, y: number, visited: boolean[][], word: string): void {
        if (x < 0 || y < 0 || x >= m || y >= n) return;

        if (visited[x][y]) return;

        visited[x][y] = true;
        word += board[x][y];
        const search = trie.search(word, result);

        if (search) {
            dfs(x - 1, y, visited, word);
            dfs(x + 1, y, visited, word);
            dfs(x, y - 1, visited, word);
            dfs(x, y + 1, visited, word);
        }

        visited[x][y] = false;
    }

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            dfs(i, j, Array.from({length : m}, () => Array(n).fill(false)), "");
        }
    }

    return Array.from(result);
};

/*
===================================================================================
# Option #2
- Prefix Node (Trie) - Simple
- O((W * L) + m * n * 4^L) (W = The length of words list, L = the longest length of all the words)
*/
class TreeNodeCustom2 {
    children: Map<string, TreeNodeCustom2>;
    word: string | null;

    constructor() {
        this.children = new Map();
        this.word = null;
    }
}

function findWordsAdvanced(board: string[][], words: string[]): string[] {

    const m: number = board.length, n: number = board[0].length;
    const result: Set<string> = new Set();
    
    const node: TreeNodeCustom2 = new TreeNodeCustom2();
    for (const word of words) {
        let curr: TreeNodeCustom2 = node;
        for (const c of word) {
            if (!curr.children.has(c)) {
                curr.children.set(c, new TreeNodeCustom2());
            }
            curr = curr.children.get(c)!;
        }
        curr.word = word;
    }

    function dfs(x: number, y: number, visited: boolean[][], curr: TreeNodeCustom2): void {
        if (x < 0 || y < 0 || x >= m || y >= n) return;

        if (visited[x][y]) return;

        visited[x][y] = true;

        if (curr.children.has(board[x][y])) {
            curr = curr.children.get(board[x][y])!;
            if (curr.word !== null) result.add(curr.word);
            dfs(x - 1, y, visited, curr);
            dfs(x + 1, y, visited, curr);
            dfs(x, y - 1, visited, curr);
            dfs(x, y + 1, visited, curr);
        }

        visited[x][y] = false;
    }

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            dfs(i, j, Array.from({length: m}, () => Array(n).fill(false)), node);
        }
    }

    return Array.from(result);
};


/*
===================================================================================
# Option #3
- Prefix Node (Trie)
- O((W * L) + m * n * 4^L) (W = The length of words list, L = the longest length of all the words)
- September 6, 2026
*/
class _TreeNode {
    children: Map<string, _TreeNode>
    word: string | null

    constructor() {
        this.children = new Map();
        this.word = null;
    }
}

function findWordsTrie(board: string[][], words: string[]): string[] {
    
    const root: Map<string, _TreeNode> = new Map();

    function addWord(w: string): void {
        if (!root.has(w[0])) root.set(w[0], new _TreeNode());
        let node: _TreeNode = root.get(w[0])!;
        const n: number = w.length;
        for (let i = 1; i < n; i++) {
            if (!node.children.has(w[i])) node.children.set(w[i], new _TreeNode());
            node = node.children.get(w[i])!;
        }
        node.word = w;
    }

    for (const w of words) {
        addWord(w);
    }

    const m: number = board.length, n: number = board[0].length;
    const result: string[] = [];
    function dfs(x: number, y: number, node: _TreeNode): void {
        if (x < 0 || y < 0 || x >= m || y >= n || !node.children.has(board[x][y])) return;
        node = node.children.get(board[x][y])!;

        if (node.word !== null) {
            result.push(node.word);
            node.word = null;
        }

        const temp: string = board[x][y];
        board[x][y] = '#';
        dfs(x + 1, y, node);
        dfs(x - 1, y, node);
        dfs(x, y + 1, node);
        dfs(x, y - 1, node);
        board[x][y] = temp;
    }

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (!root.has(board[i][j])) continue;
            let node: _TreeNode = root.get(board[i][j])!;
            if (node.word !== null) {
                result.push(node.word);
                node.word = null;
            }
            const temp: string = board[i][j];
            board[i][j] = '#';
            dfs(i + 1, j, node);
            dfs(i - 1, j, node);
            dfs(i, j + 1, node);
            dfs(i, j - 1, node);
            board[i][j] = temp;
        }
    }
    return result;
};