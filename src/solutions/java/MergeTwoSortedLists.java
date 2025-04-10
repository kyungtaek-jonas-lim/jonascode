package solutions.java;

/*
# Problem
	- `Link`: https://leetcode.com/problems/merge-two-sorted-lists/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 10, 2025
	- `Answer`: mergeTwoLists
 */
public class MergeTwoSortedLists {
	
	//Definition for singly-linked list.
	public class ListNode {
	    int val;
	    ListNode next;
	    ListNode() {}
	    ListNode(int val) { this.val = val; }
	    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
	
	/*
	 # Option #1
	 - O(n + m) ((n, m) == the lengths of list1 and list2)
	 */
	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode result = new ListNode();
        ListNode move = result;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                move.next = list1;
                list1 = list1.next;
            } else {
                move.next = list2;
                list2 = list2.next;
            }
            move = move.next;
        }
        
        if (list1 != null) {
            move.next = list1;
        } else if (list2 != null) {
            move.next = list2;
        }

        return result.next;
    }
}