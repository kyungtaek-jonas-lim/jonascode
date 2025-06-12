
/*
# Problem
	- `Link`: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 10, 2025
	- `Answer`: removeNthFromEnd / removeNthFromEndAdvanced
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
- One Pass
- O(n)
- ref: https://www.youtube.com/watch?v=XVuQxVej6y8
*/
function removeNthFromEndAdvanced(head: ListNode | null, n: number): ListNode | null {
    
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