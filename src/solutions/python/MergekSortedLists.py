from typing import List, Optional
import heapq

'''
# Problem
	- `Link`: https://leetcode.com/problems/merge-k-sorted-lists/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 10, 2025
	- `Answer`: mergeKLists / mergeKListsBetter
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
            for i, node in enumerate(lists):
                
                # Validation
                if not node:
                    lists.pop(i)
                    is_continue = True
                    break

                # Compare
                if node.val <= min_val:
                    min_val = node.val
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

    '''
    # Option #2
    - Priority Queue (heapq)
    - O(n log n)
    '''
    def mergeKListsBetter(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        
        heap = []
        
        # Push node value into the priority queue(heapq)
        for node in lists:
            while node:
                heapq.heappush(heap, node.val)
                node = node.next
        
        result = ListNode()
        move = result

        # Make a result node list in order
        while heap:
            move.next = ListNode(heapq.heappop(heap))
            move = move.next
        
        return result.next