package solutions.java;

import java.util.ArrayList;
import java.util.List;

/*
# Problem
	- `Link`: https://leetcode.com/problems/reorder-list/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 12, 2025
	- `Answer`: reorderList
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
}