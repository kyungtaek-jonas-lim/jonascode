from typing import Optional

'''
# Problem
	- `Link`: https://leetcode.com/problems/reorder-list/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 12, 2025
	- `Answer`: reorderList / reorderListAdvanced
'''

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
        
class Solution:

    '''
    # Option #1
    - List
    - O(n) (n = the number of nodes)
    - Space Complexity: O(n)
    '''
    def reorderList(self, head: Optional[ListNode]) -> None:
        """
        Do not return anything, modify head in-place instead.
        """
        if not head.next or not head.next.next:
            return
        
        my_list = []
        next = head
        while next:
            my_list.append(next)
            next = next.next
        
        len_my_list = len(my_list)
        for i in range(len_my_list // 2 - 1):
            my_list[i].next = my_list[len_my_list - i - 1]
            my_list[len_my_list - i - 1].next = my_list[i + 1]
        
        my_list[len_my_list // 2 + len_my_list % 2].next.next = None


    '''
    # Option #2
    - List
    - O(n) (n = the number of nodes)
    - Space Complexity: O(1)
    - Better
    - https://www.youtube.com/watch?v=S5bfdUTrKLM
    '''
    def reorderListAdvanced(self, head: Optional[ListNode]) -> None:
        
        '''
        1. Devide into 2 ListNodes
        2. Make the right half ListNode reverse
        3. Put togehter the two half ListNodes
        '''

        # 0. Edge Case
        if not head or not head.next:
            return

        # 1. Find middle node
        slow = fast = head
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next

        # 2. Reverse second half
        prev = None
        curr = slow.next
        slow.next = None  # Cut the first half
        while curr:
            tmp = curr.next
            curr.next = prev
            prev = curr
            curr = tmp

        # 3. Merge two halves
        first, second = head, prev
        while second: # The second half length will be equal or shorter than the first half
            tmp1, tmp2 = first.next, second.next
            first.next = second
            second.next = tmp1
            first, second = tmp1, tmp2
