package solutions.java;

import java.util.ArrayList;
import java.util.List;

/*
# Problem
	- `Link`: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 12, 2025
	- `Answer`: removeNthFromEnd / removeNthFromEndAdvanced
 */
public class RemoveNthNodeFromEndofList {

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
    - Store Nodes into a List
    - O(n) (n = the number of nodes)
    - Space: O(n)
	 */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
    	// Edge case (length = 1)
    	ListNode next = head.next;
    	if (head.next == null) return null;
    	
    	// Find the nth node from the end and store all the nodes to a list
    	List<ListNode> list = new ArrayList<>();
    	list.add(head);
    	while (next != null) {
    		list.add(next);
    		next = next.next;
    	}
    	
    	// To-be-deleted node
    	int listLength = list.size();
    	if (n == 1) { // Last node
    		list.get(listLength - 2).next = null;
    	} else if (n == listLength) { // First node
    		head = head.next;
    	} else {
    		list.get(listLength - n - 1).next = list.get(listLength - n + 1);
    	}
    	
    	return head;
    }

	/*
    # Option #2
    - Two Pointers (Better Space Complexity)
    - O(n) (n = the number of nodes)
    - Space: O(1)
	 */
    public ListNode removeNthFromEndAdvanced(ListNode head, int n) {
    	
    	ListNode dummy = new ListNode(0, head); // start from -1
    	ListNode first = dummy;
    	ListNode second = dummy;
    	
    	// Advance 'first' by n+1 steps
    	for (int i = 0; i <= n; i++) { // (n + 1)th node cause it starts from -1
    		first = first.next;
    	}
    	
    	// Move both pointers until 'first' reaches the end
    	while (first != null) {
    		first = first.next;
    		second = second.next;
    	}
    	
    	// Move both pointers until 'first' reaches the end
    	second.next = second.next.next;
    	
    	return dummy.next;
    }
}