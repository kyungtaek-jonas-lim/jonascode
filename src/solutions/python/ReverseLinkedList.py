from typing import Optional

'''
 # Problem
 	- `Link`: https://leetcode.com/problems/reverse-linked-list/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Mar 27, 2025
 	- `Answer`: reverseList / reverseListRecursive
'''

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:

    '''
	 # Option #1
	 - Iterative way
    '''
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        
        if not head or not head.next:
            return head

        prev, next = None, None

        while head:
            next = head.next
            head.next = prev
            prev = head
            head = next

        return prev
    
    '''
	 # Option #2
	 - Recursive way
    '''
    # def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
    def reverseListRecursive(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head or not head.next:
            return head
        
        # finalNode = self.reverseList(head.next)
        finalNode = self.reverseListRecursive(head.next)
        head.next.next = head
        head.next = None # It will be set at the (n - 1) turn.
        
        return finalNode