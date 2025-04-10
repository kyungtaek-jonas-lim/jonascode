from typing import List, Optional

'''
# Problem
	- `Link`: https://leetcode.com/problems/merge-k-sorted-lists/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 10, 2025
	- `Answer`: mergeKLists
'''

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:

    '''
	# Option #1
	- O(k * n) (k: The number of lists, n: The number of the total nodes)
    '''
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        
        result = ListNode()
        move = result

        while lists:
            min_index = -1
            min_val = float('inf')
            is_continue = False
            for i, each_list in enumerate(lists):
                
                # Validation
                if not each_list:
                    lists.pop(i)
                    is_continue = True
                    break

                # Compare
                if each_list.val <= min_val:
                    min_val = each_list.val
                    min_index = i

            if is_continue:
                continue

            # Make the next node
            move.next = ListNode(min_val)
            move = move.next

            # Deal with the node that has the min value
            if not lists[min_index].next:
                lists.pop(min_index)
            else:
                lists[min_index] = lists[min_index].next

        return result.next