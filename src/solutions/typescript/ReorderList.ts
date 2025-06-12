
/**
# Problem
	- `Link`: https://leetcode.com/problems/reorder-list/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 12, 2025
	- `Answer`: reorderList / reorderListAdvanced
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
- List
- O(n)
*/
function reorderList(head: ListNode | null): void {
    
    if (head === null) return;

    const list: ListNode[] = [];
    let first: ListNode | null = head;
    while (first !== null) {
        list.push(first);
        first = first.next;
    }

    let second: ListNode | null = new ListNode(0, head);
    head = second;

    const n: number = list.length;
    const odd: boolean = n % 2 === 1;
    const end: number = Math.floor(n / 2)
    for (let i = 0; i < end; i++) {
        second.next = list[i];
        second.next.next = list[n - 1 - i];
        second = second.next.next;
    }
    if (odd) {
        second.next = list[end];
        second = second.next;
    }

    second.next = null;
    head = head.next;
};


/*
# Option #2
- Optimized Space
- O(n)
*/
function reorderListAdvanced(head: ListNode | null): void {
    
    let first: ListNode | null = head, second: ListNode | null = head;
    while (first !== null && first.next != null) {
        first = first.next.next;
        second = second!.next;
    }

    const secondLast: ListNode | null = second;
    let prev: ListNode | null = null;
    while (second !== null) {
        const next = second.next;
        second.next = prev;
        prev = second;
        second = next;
    }

    first = head;
    second = prev;
    while (first !== secondLast) {
        const firstNext = first!.next;
        const secondNext = second!.next;

        first!.next = second;
        second!.next = firstNext;
        
        first = firstNext;
        second = secondNext;
    }
    if (first === secondLast) first!.next = null;
};