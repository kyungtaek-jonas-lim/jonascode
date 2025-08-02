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
        if (head == null || head.next == null) return head;

        ListNode prev = null, next = null;

        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
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