from typing import Optional
'''
# Problem
	- `Link`: https://leetcode.com/problems/merge-two-sorted-lists/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 10, 2025
	- `Answer`: mergeTwoLists
'''

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:

    '''
	 # Option #1
	 - O(n + m) ((n, m) == the lengths of list1 and list2)
    '''
    def mergeTwoLists(self, list1: Optional[ListNode], list2: Optional[ListNode]) -> Optional[ListNode]:
        
        result = ListNode()
        move = result

        while list1 and list2:
            if list1.val < list2.val:
                move.next = list1
                list1 = list1.next
            else:
                move.next = list2
                list2 = list2.next
            move = move.next
        
        if list1:
            move.next = list1
        else:
            move.next = list2
        
        return result.next