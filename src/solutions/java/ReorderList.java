package solutions.java;

import java.util.ArrayList;
import java.util.List;

/*
# Problem
	- `Link`: https://leetcode.com/problems/reorder-list/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 12, 2025
	- `Answer`: reorderList / reorderListAdvanced / reorderListOther
*/
public class ReorderList {


	// Definition for singly-linked list.
	public static class ListNode {
	    int val;
	    ListNode next;
	    ListNode() {}
	    ListNode(int val) { this.val = val; }
	    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
	
	/*
    # Option #1
    - List
    - O(n) (n = the number of nodes)
    - Space Complexity: O(n)
	 */
    public void reorderList(ListNode head) {
    	
    	// Edge case
    	if (head.next == null || head.next.next == null) return;
    	
    	// Put nodes into List
    	List<ListNode> list = new ArrayList<>();
    	ListNode next = head;
    	while (next != null) {
    		list.add(next);
    		next = next.next;
    	}

    	// Rearrange
    	int listSize = list.size();
    	for (int i = 0; i < listSize / 2; i++) {
    		list.get(i).next = list.get(listSize - i - 1);
    		list.get(listSize - i - 1).next = list.get(i + 1);
    	}
    	
    	// The last node
    	list.get(listSize / 2 + listSize % 2).next.next = null;
    }
	
	/*
    # Option #2
    - List
    - O(n) (n = the number of nodes)
    - Space Complexity: O(1)
    - Better
    - https://www.youtube.com/watch?v=S5bfdUTrKLM
	 */
    public void reorderListAdvanced(ListNode head) {
    	
    	/*
        1. Devide into 2 ListNodes
        2. Make the right half ListNode reverse
        3. Put togehter the two half ListNodes
    	 */
    	
    	// 0. Edge Case
    	if (head.next == null || head.next.next == null) return;
    	
    	// 1. Find middle node
    	ListNode fast = head;
    	ListNode slow = head;
    	while (fast != null && fast.next != null) {
    		slow = slow.next;
    		fast = fast.next.next;
    	}
    	
    	// 2. Reverse second half
    	ListNode prev = null;
    	ListNode curr = slow.next;
    	slow.next = null; // Cut the first half
    	while (curr != null) {
    		ListNode tmp = curr.next;
    		curr.next = prev;
    		prev = curr;
    		curr = tmp;
    	}
    	
    	ListNode first = head;
    	ListNode second = prev;
    	while (second != null) { // The second half length will be equal or shorter than the first half
    		ListNode tmp1 = first.next;
    		ListNode tmp2 = second.next;
    		
    		first.next = second;
    		second.next = tmp1;
    		
    		first = tmp1;
    		second = tmp2;
    	}
    }

	/*
    # Option #3
    - Other (August 10, 2025)
    - O(n)
	 */
    public void reorderListOther(ListNode head) {
        
        if (head == null || head.next == null) return;

        // Find Middle Node
        ListNode first = head, second = head;
        while (first != null && first.next != null) {
            first = first.next.next;
            second = second.next;
        }
        if (first == null) {
            ListNode next = second.next;
            second.next = null;
            second = next;
        }

        // Reverse the right sub list
        ListNode prev = null;
        while (second != null) {
            ListNode next = second.next;
            second.next = prev;
            prev = second;
            second = next;
        }
        second = prev;

        // Reorder
        first = head;
        while (second != null) {
            ListNode firstNext = first.next, secondNext = second.next;
            first.next = second;
            second.next = firstNext;
            first = firstNext;
            second = secondNext;
        }
    }
}