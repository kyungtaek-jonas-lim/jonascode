package solutions.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
# Problem
	- `Link`: https://leetcode.com/problems/merge-k-sorted-lists/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 10, 2025
	- `Answer`: mergeKLists
 */
public class MergekSortedLists {

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
	- O(k * n) (k: The number of lists, n: The number of the total nodes)
	 */
    public ListNode mergeKLists(ListNode[] lists) {
    	
    	List<ListNode> list = new ArrayList<>(Arrays.asList(lists));
        
    	ListNode result = new ListNode();
    	ListNode move = result;
    	
    	int minIndex = -1;
		int minValue = Integer.MAX_VALUE;
		boolean is_continue = false;
		
    	while (!list.isEmpty()) {
        	
        	minIndex = -1;
    		minValue = Integer.MAX_VALUE;
    		is_continue = false;
    		
	    	for (int i = 0; i < list.size(); i++) {
	    		
	    		ListNode item = list.get(i);
	    		
	    		// Validation
	    		if (item == null) {
		    		list.remove(i);
		    		is_continue = true;
		    		break;
	    		}
	    		
	    		// Compare
	    		if (item.val <= minValue) {
	    			minValue = item.val;
	    			minIndex = i;
	    		}
	    	}
	    	
	    	if (is_continue) continue;
	    	
	    	// Make the next node
	    	move.next = new ListNode(minValue);
	    	move = move.next;
	    	
	    	// Deal with the node that has the min value
	    	if (list.get(minIndex).next == null) {
	    		list.remove(minIndex);
	    	} else {
	    		list.set(minIndex, list.get(minIndex).next);
	    	}
    	}
    	
    	return result.next;
    }
}