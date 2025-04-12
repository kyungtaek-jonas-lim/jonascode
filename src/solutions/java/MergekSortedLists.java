package solutions.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
# Problem
	- `Link`: https://leetcode.com/problems/merge-k-sorted-lists/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 10, 2025
	- `Answer`: mergeKLists / mergeKListsBetter / mergeKListsAdvanced / mergeKListsBest
 */
public class MergekSortedLists {

	//Definition for singly-linked list.
	public static class ListNode {
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

    
    /*
    # Option #3
    - Priority Queue (More efficient)
    - O(n log k) (n = the number of total nodes, k = the number of lists)
     */
    public ListNode mergeKListsAdvanced(ListNode[] lists) {
    	
    	/**
    	 * This implementation is more efficient than pushing all node values into the heap.
    	 *
    	 * Key advantages:
    	 * - Time Complexity: O(n log k)
    	 *   - n: total number of nodes across all lists
    	 *   - k: number of linked lists
    	 * - We only keep at most k nodes in the heap at any given time,
    	 *   resulting in faster heap operations compared to inserting all n values.
    	 * - We store the actual nodes in the heap instead of just their values,
    	 *   which allows us to build the final list without allocating new ListNode objects.
    	 *
    	 * Why it’s better than the O(n log n) version:
    	 * - That version inserts all node values into the heap, resulting in a heap of size n.
    	 * - This leads to slower heap operations (log n vs log k).
    	 * - It also creates new ListNodes instead of reusing the original ones, which is unnecessary.
    	 */
    	
	    if (lists == null || lists.length == 0) return null;

	    // PriorityQueue with custom comparator to sort ListNode by val
	    PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));

	    // Initialize the heap with the head node of each list
	    for (ListNode node : lists) {
	        if (node != null) {
	            heap.offer(node);
	        }
	    }

	    ListNode dummy = new ListNode(0);
	    ListNode current = dummy;

	    // Pop the smallest node and push its next into the heap
	    while (!heap.isEmpty()) {
	        ListNode node = heap.poll();
	        current.next = node;
	        current = current.next;

	        if (node.next != null) {
	            heap.offer(node.next);
	        }
	    }

	    return dummy.next;
	}
    
    
    /*
    # Option #4
    - Divide and Conquer (Devide k and use the same algorithm as 'MergeTwoSortedLists')
    - MergeTwoSortedLists: https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/src/solutions/java/MergeTwoSortedLists.java
    - O(n log k)
    - ref: https://www.youtube.com/watch?v=q5a5OiGbT6Q&t=65s
     */
    public ListNode mergeKListsBest(ListNode[] lists) {
    	if (lists == null || lists.length == 0) return null;
    	
    	List<ListNode> mergedLists = new ArrayList<>(Arrays.asList(lists));
    	while (mergedLists.size() > 1) {
        	List<ListNode> tempLists = new ArrayList<>();
        	int mergedListsSize = mergedLists.size();
    		
        	for (int i = 0; i < mergedListsSize; i += 2) {
        		ListNode node1 = mergedLists.get(i);
        		ListNode node2 = (i + 1) < mergedListsSize ? mergedLists.get(i + 1) : null;
        		tempLists.add(mergeTwoLists(node1, node2));
        	}

        	mergedLists = tempLists;
    	}
    	return mergedLists.get(0);
    }
    
    // MergeTwoSortedLists: https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/src/solutions/java/MergeTwoSortedLists.java
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