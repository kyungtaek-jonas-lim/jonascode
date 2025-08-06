
/*
# Problem
	- `Link`: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 10, 2025
	- `Answer`: removeNthFromEnd / removeNthFromEndDfsRecursive / removeNthFromEndTwoPointers
*/

class ListNode {
    val: number
    next: ListNode | null
    constructor(val?: number, next?: ListNode | null) {
        this.val = (val===undefined ? 0 : val)
        this.next = (next===undefined ? null : next)
    }
}

/*
# Option #1
- Two Pass
- O(n)
- Space: O(n)
*/
function removeNthFromEnd(head: ListNode | null, n: number): ListNode | null {
    
    let node: ListNode | null = head;
    let total: number = 0;
    while (node != null) {
        node = node.next;
        total++;
    }

    let goal: number = total - n;
    if (goal === 0) return head!.next;
    let curr: number = 1;
    node = head;
    while (node != null && curr < goal) {
        node = node.next;
        curr++;
    }

    if (node!.next !== null) {
        node!.next = node!.next.next;
    }
    return head;
};


/*
# Option #2
- DFS Recursive
- O(n)
- Space: O(n)
*/
function removeNthFromEndDfsRecursive(head: ListNode | null, n: number): ListNode | null {

    let next: ListNode | null = null;
    
    function dfs(node: ListNode | null): number {
        if (node === null) return 0;
        const current = 1 + dfs(node.next);
        if (current === n - 1) {
            next = node;
        } else if (current === n + 1) {
            node.next = next;
        }
        return current;
    }

    const headNth: number = dfs(head);
    if (headNth === n) return head!.next;
    return head;
};


/*
# Option #3
- One Pass with Two Pointers
- O(n)
- Space: O(1)
- ref: https://www.youtube.com/watch?v=XVuQxVej6y8
*/
function removeNthFromEndTwoPointers(head: ListNode | null, n: number): ListNode | null {
    
    const dummy = new ListNode(0, head);
    let first: ListNode | null = head;
    let second: ListNode | null = dummy;

    while (n > 0 && first !== null) {
        first = first.next;
        n--;
    }

    while (first !== null) {
        second = second!.next;
        first = first.next;
    }
    
    second!.next = second!.next!.next;
    return dummy.next;
};