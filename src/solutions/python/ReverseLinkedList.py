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

        # Edge Case: Empty NodeList or only one node
        if not head or not head.next:
            return head
        
        next_node = head.next
        prev_node = head
        head.next = None

        while True:
            temp_node = next_node.next
            next_node.next = prev_node
            if temp_node == None: # It means originally `nextNode` was the last node
                break
            prev_node = next_node
            next_node = temp_node
        return next_node # return the revised lastNode which is now the first node.
    
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