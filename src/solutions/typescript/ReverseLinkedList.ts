
/*
 # Problem
 	- `Link`: https://leetcode.com/problems/reverse-linked-list/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: June 9, 2025
 	- `Answer`: reverseList / reverseListRecursive
*/



// Definition for singly-linked list.
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
- Iterative Approach
- O(n)
*/
function reverseList(head: ListNode | null): ListNode | null {

    if (head === null) return head;

    let prev: ListNode | null = null;
    let next: ListNode | null = null;
    while (head !== null) {
        next = head.next;
        head.next = prev;
        prev = head;
        head = next;
    }
    return prev;
};

/*
# Option #2
- Recursive Approach
- O(n)
*/
function reverseListRecursive(head: ListNode | null): ListNode | null {

    if (head === null || head.next === null) return head;
    
    const newHead = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return newHead;
};