
/*
# Problem
 	- `Link`: https://leetcode.com/problems/implement-trie-prefix-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 18, 2025
	- `Answer`: Trie
*/


/*
# Option #1
- Trie (Prefix Tree)
- O(L), O(L), O(P) (L = The length of the words, P = the length of the prefixes)
- ref) https://www.youtube.com/watch?v=oobqoCJlHA0
*/
class TreeNode { // Might have to change the name of the class (TreeNode -> TreeNodeCustom), due to its duplication issue on LeetCode.

    children: Map<string, TreeNode>;
    end: boolean;

    constructor() {
        this.children = new Map();
        this.end  = false;
    }
}

class Trie {

    node: TreeNode;

    constructor() {
        this.node = new TreeNode();
    }

    insert(word: string): void {
        let curr: TreeNode = this.node;
        for (const c of word) {
            if (!curr.children.has(c)) {
                curr.children.set(c, new TreeNode());
            }
            curr = curr.children.get(c)!;
        }
        curr.end = true;
    }

    search(word: string): boolean {
        let curr: TreeNode = this.node;
        for (const c of word) {
            if (!curr.children.has(c)) return false;
            curr = curr.children.get(c)!;
        }
        return curr.end;
    }

    startsWith(prefix: string): boolean {
        let curr: TreeNode = this.node;
        for (const c of prefix) {
            if (!curr.children.has(c)) return false;
            curr = curr.children.get(c)!;
        }
        return true;
    }
}




/*
# Option #2
- Trie (Prefix Tree)
- O(L), O(W·L), O(W·L) (L = length of the word/prefix you're searching for, W = number of stored words that start with the same first letter as your query (worst case))
- Slower because the root has all the words even though they start with the same alphabet (e.g., 'app' and 'apple' have separate nodes not even partially.)
- September 4, 2026
*/
class _Node {
    val: string
    next: _Node | null

    constructor(val: string, next?: _Node | null) {
        this.val = val;
        this.next = next === undefined ? null : next;
    }
}


class Trie {
    dict: Map<string, Array<_Node>>

    constructor() {
        this.dict = new Map();
    }

    insert(word: string): void {
        const n: number = word.length;
        let node: _Node | null = null;
        for (let i = n - 1; i >= 0; i--) {
            node = new _Node(word[i], node);
        }
        if (!this.dict.has(word[0])) this.dict.set(word[0], []);
        this.dict.get(word[0])!.push(node!);
    }

    search(word: string): boolean {
        const n: number = word.length;
        if (!this.dict.has(word[0])) return false;
        
        for (const curr of this.dict.get(word[0])!) {
            let node: _Node | null = curr.next;
            let success: boolean = true;
            for (let i = 1; i < n; i++) {
                if (node === null || node.val !== word[i]) {
                    success = false;
                    break;
                }
                node = node.next;
            }
            if (success && node === null) return true;
        }
        return false;
    }

    startsWith(prefix: string): boolean {
        const n: number = prefix.length;
        if (!this.dict.has(prefix[0])) return false;
        
        for (const curr of this.dict.get(prefix[0])!) {
            let node: _Node | null = curr.next;
            let success: boolean = true;
            for (let i = 1; i < n; i++) {
                if (node === null || node.val !== prefix[i]) {
                    success = false;
                    break;
                }
                node = node.next;
            }
            if (success) return true;
        }
        return false;
    }
}




/*
# Option #3
- Trie (Prefix Tree) - The same as Option #1
- O(L), O(L), O(P) (L = The length of the words, P = the length of the prefixes)
- September 4, 2026
*/
class _Node {
    next: Map<string, _Node>
    end: boolean

    constructor() {
        this.next = new Map()
        this.end = false;
    }
}


class Trie {
    root: Map<string, _Node>

    constructor() {
        this.root = new Map();
    }

    insert(word: string): void {
        const n: number = word.length;
        if (!this.root.has(word[0])) this.root.set(word[0], new _Node());
        let node: _Node = this.root.get(word[0])!;
        
        for (let i = 1; i < n; i++) {
            if (!node.next.has(word[i])) node.next.set(word[i], new _Node());
            node = node.next.get(word[i]);
        }

        node.end = true;
    }

    search(word: string): boolean {
        const n: number = word.length;
        if (!this.root.has(word[0])) return false;

        let node: _Node = this.root.get(word[0]);
        for (let i = 1; i < n; i++) {
            if (!node.next.has(word[i])) return false;
            node = node.next.get(word[i]);
        }
        return node.end;
    }

    startsWith(prefix: string): boolean {
        const n: number = prefix.length;
        if (!this.root.has(prefix[0])) return false;

        let node: _Node = this.root.get(prefix[0]);
        for (let i = 1; i < n; i++) {
            if (!node.next.has(prefix[i])) return false;
            node = node.next.get(prefix[i]);
        }
        return true;
    }
}