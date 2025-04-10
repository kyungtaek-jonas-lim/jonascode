from typing import Optional

# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

'''
 # Problem
 	- `Link`: https://leetcode.com/problems/linked-list-cycle/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 10, 2025
 	- `Answer`: hasCycle / hasCycleAdvanced
'''
class Solution:

    '''
    # Option #1
    - Common (set)
    - O(n)
    '''
    def hasCycle(self, head: Optional[ListNode]) -> bool:
        
        if not head or not head.next:
            return False

        my_set = set()
        my_set.add(head)

        next = head.next
        while next:
            if next in my_set:
                return True
            my_set.add(next)
            next = next.next
        
        return False


    '''
    # Option #2
    - Better (two pointers)
    - O(n)
    - ref: https://www.youtube.com/watch?v=gBTe7lFR3vc
    '''
    def hasCycleAdvanced(self, head: Optional[ListNode]) -> bool:
        
        one_step: ListNode = head # One step at a time
        two_step: ListNode = head # Two steps at a time

        while two_step and two_step.next:
            one_step = one_step.next # Move one step
            two_step = two_step.next.next # Move two steps
            if one_step == two_step:
                return True
        
        return False