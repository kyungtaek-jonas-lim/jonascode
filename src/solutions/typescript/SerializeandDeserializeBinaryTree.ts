
/*
# Problem
 	- `Link`: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 17, 2025
	- `Answer`: (serializeBfs, deserializeBfs) / (serializeDfsRecursive, deserializeDfsRecursive)
*/

class TreeNode {
    val: number
    left: TreeNode | null
    right: TreeNode | null
    constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
        this.val = (val===undefined ? 0 : val)
        this.left = (left===undefined ? null : left)
        this.right = (right===undefined ? null : right)
    }
}



/*
============================================================================
# Option #1
- BFS
- O(n)
- Since Queue in TypeScript is slow (Array.shift() => Slow), Time Limit Exceeded
*/

/*
 * Encodes a tree to a single string.
 */
function serializeBfs(root: TreeNode | null): string {
    const result: string[] = [];
    const deque: (TreeNode | null)[] = [];
    deque.push(root);

    while (deque.length !== 0) {
        const end: number = deque.length;
        let nullCnt: number = 0;

        for (let i = 0; i < end; i++) {
            const node: TreeNode = deque.shift()!;
            if (node === null) {
                result.push("null");
                deque.push(null);
                deque.push(null);
                nullCnt++;
                continue;
            }

            result.push(`${node.val}`);

            deque.push(node.left);
            deque.push(node.right);
        }

        if (nullCnt === end) break;
    }

    return result.join(",");
};

/*
 * Decodes your encoded data to tree.
 */
function deserializeBfs(data: string): TreeNode | null {
    const nodes: string[] = data.split(",");
    if (nodes[0] === "null") return null;

    const n: number = nodes.length;
    const root: TreeNode = new TreeNode(Number(nodes[0]));
    const deque: (TreeNode | null)[] = [root];
    let curr: number = 1;

    while (deque.length !== 0 && curr < n) {

        const node: TreeNode | null = deque.shift()!;
        if (node === null) {
            curr += 2;
            deque.push(null);
            deque.push(null);
            continue;
        }

        const left: string = nodes[curr++], right: string = nodes[curr++];
        if (left !== "null") {
            node.left = new TreeNode(Number(left));
        }
        if (right !== "null") {
            node.right = new TreeNode(Number(right));
        }
        
        deque.push(node.left);
        deque.push(node.right);
    }

    return root;
};



/*
============================================================================
# Option #2
- DFS Recursive
- O(n)
*/

/*
 * Encodes a tree to a single string.
 */
function serializeDfsRecursive(root: TreeNode | null): string {
    const result: string[] = [];
    dfsForSerializing(root, result);
    return result.join(",");
};


function dfsForSerializing(node: TreeNode | null, result: string[]): void {
    if (node === null) {
        result.push("null");
        return;
    } else {
        result.push(`${node.val}`);
    }
    dfsForSerializing(node.left, result);
    dfsForSerializing(node.right, result);
}

/*
 * Decodes your encoded data to tree.
 */
function deserializeDfsRecursive(data: string): TreeNode | null {
    const array: string[] = data.split(",");
    if (array[0] === "null") return null;

    const root: TreeNode = new TreeNode(Number(array[0]));
    dfsForDeserializing(root, array, 0);
    return root;
};


function dfsForDeserializing(prevNode: TreeNode, array: string[], index: number): number {

    index++;
    if (array[index] !== "null") {
        prevNode.left = new TreeNode(Number(array[index]));
        index = dfsForDeserializing(prevNode.left, array, index);
    }

    index++;
    if (array[index] !== "null") {
        prevNode.right = new TreeNode(Number(array[index]));
        index = dfsForDeserializing(prevNode.right, array, index);
    }

    return index;
}
