
/*
# Problem
	- `Link`: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 10, 2025
	- `Answer`: removeNthFromEnd / removeNthFromEndDfs / removeNthFromEndTwoPointers / removeNthFromEndTotalCount
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
function removeNthFromEndDfs(head: ListNode | null, n: number): ListNode | null {
    
    let nthNode: ListNode | null = null;
    const result: ListNode = new ListNode(0, head);

    function dfs(node: ListNode | null): number {
        if (node === null) return 0;
        const next: number = dfs(node.next);
        if (next === n) nthNode = node;
        return next + 1;
    }

    dfs(result);
    if (nthNode !== null) nthNode.next = nthNode.next.next;
    return result.next;
};


/*
# Option #3
- One Pass with Two Pointers
- O(n)
- Space: O(1)
*/
function removeNthFromEndTwoPointers(head: ListNode | null, n: number): ListNode | null {

    const result: ListNode = new ListNode(0, head);
    let curr: ListNode = result, end: ListNode = result;

    // Have two nodes with n long distance
    for (let i = 0; i < n; i ++) {
        end = end.next;
    }

    // Find the end node with the same distance with curr node
    while (end.next !== null) {
        curr = curr.next;
        end = end.next;
    }

    // Remove the node
    curr.next = curr.next.next;
    return result.next;
};


/*
# Option #4
- Total Count
- O(n)
- Space: O(1)
*/
function removeNthFromEndTotalCount(head: ListNode | null, n: number): ListNode | null {
    
    const result: ListNode = new ListNode(0, head);

    // Find total count
    let total: number = 0;
    let curr: ListNode | null = result;
    while (curr !== null && curr.next !== null) {
        curr = curr.next.next;
        total += 2;
    }
    if (curr === null) total -= 1;

    // Find the nth node from the first
    const goal: number = total - n;
    let cnt: number = 0;
    curr = result;
    while (cnt !== goal) {
        curr = curr!.next;
        cnt++;
    }

    // Remove the node
    curr!.next = curr!.next!.next;

    return result.next;
};