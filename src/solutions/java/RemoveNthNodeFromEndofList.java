package solutions.java;

import java.util.ArrayList;
import java.util.List;

/*
# Problem
	- `Link`: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 12, 2025
	- `Answer`: removeNthFromEnd / removeNthFromEndDfs / removeNthFromEndTwoPointers / removeNthFromEndTotalCount
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
	- DFS Recursive
    - O(n) (n = the number of nodes)
    - Space: O(n)
	 */
    public ListNode removeNthFromEndDfs(ListNode head, int n) {
        ListNode result = new ListNode(0, head);
        dfs(result, n);
        return result.next;
    }

    private int dfs(ListNode node, int n) {
        if (node.next == null) return 1;
        
        final int current = dfs(node.next, n);
        if (current == n) {
            node.next = node.next.next;
            return 0;
        }
        return current == 0 ? 0 : current + 1;
    }


	/*
    # Option #3
    - Two Pointers (Better Space Complexity)
    - O(n) (n = the number of nodes)
    - Space: O(1)
	 */
    public ListNode removeNthFromEndTwoPointers(ListNode head, int n) {
        ListNode result = new ListNode(0, head);
        ListNode curr = result, end = result;
        
        // Have the n long distance
        for (int i = 0; i < n; i++) {
            end = end.next;
        }

        // Move to the nth node with the distance
        while (end.next != null) {
            curr = curr.next;
            end = end.next;
        }

        // Remove the node
        curr.next = curr.next.next;

        return result.next;
    }


	/*
    # Option #4
    - Total Count (Better Space Complexity)
    - O(n) (n = the number of nodes)
    - Space: O(1)
	 */
    public ListNode removeNthFromEndTotalCount(ListNode head, int n) {
        ListNode result = new ListNode(0, head);
        ListNode curr = result;
        
        // Find the total count
        int total = 0;
        while (curr != null && curr.next != null ){
            curr = curr.next.next;
            total += 2;
        }
        if (curr == null) total--;

        // Find nth Node from the first
        curr = result;
        int cnt = 0, goal = total - n;
        while (cnt != goal) {
            curr = curr.next;
            cnt++;
        }

        // Remove the node
        curr.next = curr.next.next;

        return result.next;
    }
}