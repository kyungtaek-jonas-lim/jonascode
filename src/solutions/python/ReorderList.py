from typing import Optional

'''
# Problem
	- `Link`: https://leetcode.com/problems/reorder-list/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 12, 2025
	- `Answer`: reorderList
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