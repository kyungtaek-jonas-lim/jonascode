
/*
# Problem
	- `Link`: https://leetcode.com/problems/merge-two-sorted-lists/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 10, 2025
	- `Answer`: mergeTwoLists
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
- O(n + m)
*/
function mergeTwoLists(list1: ListNode | null, list2: ListNode | null): ListNode | null {

    const result = new ListNode(0, null);
    let curr: ListNode | null = result;
    
    while (list1 !== null && list2 !== null) {
        if (list1.val < list2.val) {
            curr.next = list1;
            list1 = list1.next;
        } else {
            curr.next = list2;
            list2 = list2.next;
        }
        curr = curr.next;
    }

    if (list1 !== null) curr.next = list1;
    else curr.next = list2;
    return result.next;
};