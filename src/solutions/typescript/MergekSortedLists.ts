
/*
# Problem
	- `Link`: https://leetcode.com/problems/merge-k-sorted-lists/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 10, 2025
	- `Answer`: mergeKLists / mergeKListsAdvanced
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
- O(n * k) (k = The length of lists, n = the number of all nodes)
- ref: MergeTwoSortedLists - https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/src/solutions/typescript/MergeTwoSortedLists.ts
*/
function mergeKLists(lists: Array<ListNode | null>): ListNode | null {

    const n: number = lists.length;
    if (n === 0) return null;
    
    function mergeTwoLists(list1: ListNode | null, list2: ListNode | null) {
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
    }

    let merged: ListNode | null = lists[0];
    for (let i = 1; i < n; i++) {
        merged = mergeTwoLists(lists[i], merged);
    }
    return merged;
};


/*
# Option #2
- O(n log k) (k = The length of lists, n = the number of all nodes)
- ref: MergeTwoSortedLists - https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/src/solutions/typescript/MergeTwoSortedLists.ts
*/
function mergeKListsAdvanced(lists: Array<ListNode | null>): ListNode | null {

    const n: number = lists.length;
    if (n === 0) return null;
    
    function mergeTwoLists(list1: ListNode | null, list2: ListNode | null) {
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
    }

    let result: Array<ListNode | null> = lists;
    while (result.length > 1) {
        const temp: Array<ListNode | null> = new Array();
        for (let i = 0; i < result.length; i += 2) {
            if (i + 1 < result.length) {
                temp.push(mergeTwoLists(result[i], result[i + 1]));
            } else {
                temp.push(mergeTwoLists(result[i], null));
            }
        }
        result = temp;
    }
    return result[0];
};