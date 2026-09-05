
/*
# Problem
 	- `Link`: https://leetcode.com/problems/design-add-and-search-words-data-structure/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 18, 2025
	- `Answer`: WordDictionaryDfs / WordDictionaryBfs / WordDictionaryDfs2
*/

class _TreeNode {

    children: Map<string, _TreeNode>;
    end: boolean;

    constructor() {
        this.children = new Map();
        this.end = false;
    }
}

/*
# Option #1
- Prefix Tree (DFS)
- addWord: O(L)                          (L = length of the word)
- search:
    - Best case  (no '.'):  O(L)
    - Worst case (all '.'): O(26^L)       (26 = alphabet size)
- Space:
    - Trie storage: O(N × L)              (N = number of words stored)
    - search recursion stack: O(L)         ← DFS backtracks, only 1 path alive at a time
*/
class WordDictionaryDfs {

    node: _TreeNode;

    constructor() {
        this.node = new _TreeNode();
    }

    addWord(word: string): void {
        let curr: _TreeNode = this.node;
        for (const c of word) {
            if (!curr.children.has(c)) {
                curr.children.set(c, new _TreeNode());
            }
            curr = curr.children.get(c)!;
        }
        curr.end = true;
    }

    search(word: string): boolean {

        const n: number = word.length;

        function dfs(curr: _TreeNode, i: number): boolean {
            if (n === i) return curr.end;
            if (word[i] !== '.') {
                const c: string = word[i];
                if (!curr.children.has(c)) return false;
                return dfs(curr.children.get(c)!, i + 1);
            } else {
                for (const c of curr.children.values()) {
                    if (dfs(c, i + 1)) return true;
                }
                return false;
            }
        }

        return dfs(this.node, 0);
    }

    // search(word: string): boolean {

    //     const n: number = word.length;

    //     function dfs(curr: TreeNodeCustom, i: number): boolean {
    //         if (word[i] !== '.') {
    //             const c: string = word[i];
    //             if (!curr.children.has(c)) return false;
    //             if (i == n - 1) return curr.children.get(c)!.end;
    //             return dfs(curr.children.get(c)!, i + 1);
    //         } else {
    //             for (const c of curr.children.keys()) {
    //                 if (i == n - 1) {
    //                     if (curr.children.get(c)!.end) return true;
    //                 }
    //                 else if (dfs(curr.children.get(c)!, i + 1)) return true;
    //             }
    //             return false;
    //         }
    //     }

    //     return dfs(this.node, 0);
    // }
}


/*
# Option #2
- Prefix Tree (BFS)
- addWord: O(L)                          (L = length of the word)
- search:
    - Best case  (no '.'):  O(L)
    - Worst case (all '.'): O(26^L)
- Space:
    - Trie storage: O(N × L)              (N = number of words stored)
    - search queue: O(26^L)  ← WORSE than Option #1!
                            all nodes at current depth stay alive in the queue at once
- Date:
    - September 5, 2026
*/
class WordDictionaryBfs {

    root: Map<string, _TreeNode>

    constructor() {
        this.root = new Map();
    }

    addWord(word: string): void {
        if (!this.root.has(word[0])) this.root.set(word[0], new _TreeNode());
        let node: _TreeNode = this.root.get((word[0]))!;

        const n: number = word.length;
        for (let i = 1; i < n; i++) {
            if (!node.children.has(word[i])) node.children.set(word[i], new _TreeNode());
            node = node.children.get(word[i])!;
        }
        node.end = true;
    }

    search(word: string): boolean {

        const queue: [_TreeNode, number][] = []
        let head: number = 0
        if (word[0] === '.') {
            if (this.root.size === 0) return false;
            for (const nd of this.root.values()) queue.push([nd, 0]);
        } else {
            if (!this.root.has(word[0])) return false;
            queue.push([this.root.get(word[0])!, 0]);
        }

        const n: number = word.length;
        while (head < queue.length) {
            let [node, depth]: [_TreeNode, number] = queue[head++];
            if (depth === n - 1) {
                if (node.end) return true;
                continue;
            }
            depth++;

            if (word[depth] === '.') {
                if (node.children.size === 0) continue;
                for (const nd of node.children.values()) {
                    queue.push([nd, depth]);
                }
            } else if (!node.children.has(word[depth])) continue;
            else {
                queue.push([node.children.get(word[depth])!, depth]);
            }
        }

        return false;
    }
}




/*
# Option #3
- Prefix Tree (DFS)
- addWord: O(L)                          (L = length of the word)
- search:
    - Best case  (no '.'):  O(L)
    - Worst case (all '.'): O(26^L)       (26 = alphabet size)
- Space:
    - Trie storage: O(N × L)              (N = number of words stored)
    - search recursion stack: O(L)         ← DFS backtracks, only 1 path alive at a time
- Date:
    - September 5, 2026
*/
class WordDictionaryDfs2 {

    root: Map<string, _TreeNode>

    constructor() {
        this.root = new Map();
    }

    addWord(word: string): void {
        if (!this.root.has(word[0])) this.root.set(word[0], new _TreeNode());
        let node: _TreeNode = this.root.get((word[0]))!;

        const n: number = word.length;
        for (let i = 1; i < n; i++) {
            if (!node.children.has(word[i])) node.children.set(word[i], new _TreeNode());
            node = node.children.get(word[i])!;
        }
        node.end = true;
    }

    search(word: string): boolean {

        const n: number = word.length;

        function dfs(i: number, node: _TreeNode): boolean {
            if (i++ === n - 1) return node.end;
            
            if (word[i] === '.') {
                for (const nd of node.children.values()) {
                    if (dfs(i, nd)) return true;
                }
                return false;
            } else {
                if (!node.children.has(word[i])) return false;
                return dfs(i, node.children.get(word[i])!);
            }
        }

        if (word[0] === '.') {
            for (const nd of this.root.values()) {
                if (dfs(0, nd)) return true;
            }
            return false;
        } else {
            if (!this.root.has(word[0])) return false;
            return dfs(0, this.root.get(word[0])!);
        }
    }
}