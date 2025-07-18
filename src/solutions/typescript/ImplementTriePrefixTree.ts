
/*
# Problem
 	- `Link`: https://leetcode.com/problems/implement-trie-prefix-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 18, 2025
	- `Answer`: Trie
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