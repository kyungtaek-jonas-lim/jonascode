package solutions.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
# Problem
	- `Link`: https://leetcode.com/problems/merge-k-sorted-lists/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 10, 2025
	- `Answer`: mergeKLists / mergeKListsBetter
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
	    		
	    		ListNode node = list.get(i);
	    		
	    		// Validation
	    		if (node == null) {
		    		list.remove(i);
		    		is_continue = true;
		    		break;
	    		}
	    		
	    		// Compare
	    		if (node.val <= minValue) {
	    			minValue = node.val;
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
    
    /*
    # Option #2
    - Priority Queue
    - O(n log n)
     */
    public ListNode mergeKListsBetter(ListNode[] lists) {

    	PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> a - b);

    	// Push node value into the priority queue
    	for (int i = 0; i < lists.length; i++) {
    		ListNode node = lists[i];
    		while (node != null) {
	    		queue.add(node.val);
	    		node = node.next;
    		}
    	}
    
    	ListNode result = new ListNode();
    	ListNode move = result;
    	
    	// Make a result node list in order
    	while (!queue.isEmpty()) {
    		move.next = new ListNode(queue.poll());
    		move = move.next;
    	}
    	
    	return result.next;
    }
}