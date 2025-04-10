package solutions.java;

import java.util.HashSet;
import java.util.Set;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/linked-list-cycle/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 10, 2025
 	- `Answer`: hasCycle / hasCycleAdvanced
*/
public class LinkedListCycle {

	// Definition for singly-linked list.
	class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) {
	        val = x;
	        next = null;
	    }
	}
	
    /*
    # Option #1
    - Common (set)
    - O(n)
     */
    public boolean hasCycle(ListNode head) {
        
        if (head == null || head.next == null) return false;

        Set<ListNode> set = new HashSet<>();
        set.add(head);
        ListNode next = head.next;
        while (next != null) {
            if (set.contains(next)) return true;
            set.add(next);
            next = next.next;
        }
        return false;
    }
	
    /*
    # Option #2
    - Better (two pointers)
    - O(n)
    - ref: https://www.youtube.com/watch?v=gBTe7lFR3vc
     */
    public boolean hasCycleAdvanced(ListNode head) {
    	
        ListNode oneStep = head; // One step at a time
        ListNode twoStep = head; // Two steps at a time

        while (twoStep != null && twoStep.next != null) {
        	oneStep = oneStep.next; // Move one step
        	twoStep = twoStep.next.next; // Move two steps
        	if (oneStep == twoStep) return true;
        }
        return false;
    }
}
