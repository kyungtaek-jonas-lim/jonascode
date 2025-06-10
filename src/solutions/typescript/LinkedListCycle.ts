
/*
 # Problem
 	- `Link`: https://leetcode.com/problems/linked-list-cycle/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: June 10, 2025
 	- `Answer`: hasCycle / hasCycleAdvanced
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
- O(n)
*/
function hasCycle(head: ListNode | null): boolean {
    if (head === null || head.next === null) return false;
    const set = new Set<ListNode>();
    while (head !== null) {
        if (set.has(head)) return true;
        set.add(head);
        head = head.next;
    }
    return false;
};

/*
# Option #2
- O(n)
*/
function hasCycleAdvanced(head: ListNode | null): boolean {
    let first: ListNode | null = head, second: ListNode | null = head;
    while (first !== null && first.next !== null) {
        first = first.next.next;
        second = second!.next;
        if (first === second) return true;
    }
    return false;
};