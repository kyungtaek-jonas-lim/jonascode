package solutions.java;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/reverse-linked-list/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 27, 2025
 	- `Answer`: reverseList / reverseListRecursive
*/
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
public class ReverseLinkedList {
	
	/*
	 # Option #1
	 - Iterative way
	 */
    public static ListNode reverseList(ListNode head) {
        
    	// Edge Case: Empty NodeList or only one node
    	if (head == null || head.next == null) return head;
    	
    	// Move the next node
    	ListNode nextNode = head.next;
    	ListNode prevNode = head;
    	head.next = null;
    	while (true) {
    		ListNode tempNode = nextNode.next;
    		nextNode.next = prevNode;
    		if (tempNode == null) // It means originally `nextNode` was the last node
    			break;
    		prevNode = nextNode;
    		nextNode = tempNode;
    	}
    	return nextNode; // return the revised lastNode which is now the first node.
    }
    

	/*
	 # Option #2
	 - Recursive way
	 */
//    public static ListNode reverseList(ListNode head) {
    public static ListNode reverseListRecursive(ListNode head) {
    	if (head == null || head.next == null) return head;
    	
//    	ListNode finalNode = reverseList(head.next);
    	ListNode finalNode = reverseListRecursive(head.next);
    	head.next.next = head; 
    	head.next = null; // It will be set at the (n - 1) turn.
    	return finalNode;
    }
}