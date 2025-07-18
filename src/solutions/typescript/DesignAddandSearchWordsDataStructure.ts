
/*
# Problem
 	- `Link`: https://leetcode.com/problems/design-add-and-search-words-data-structure/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 18, 2025
	- `Answer`: WordDictionary
*/

class TreeNodeCustom {

    children: Map<string, TreeNodeCustom>;
    end: boolean;

    constructor() {
        this.children = new Map();
        this.end = false;
    }
}

class WordDictionary {

    node: TreeNodeCustom;

    constructor() {
        this.node = new TreeNodeCustom();
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

    search(word: string): boolean {

        const n: number = word.length;

        function dfs(curr: TreeNodeCustom, i: number): boolean {
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