from typing import List, Optional
import heapq

'''
# Problem
	- `Link`: https://leetcode.com/problems/merge-k-sorted-lists/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 10, 2025
	- `Answer`: mergeKLists / mergeKListsBetter / mergeKListsAdvanced / mergeKListsBest
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
    
    
    
    '''
    # Option #3
    - Priority Queue (More efficient)
    - O(n log k) (n = the number of total nodes, k = the number of lists)
    '''
    def mergeKListsAdvanced(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        '''
        This implementation is more efficient than pushing all node values into the heap.

        Key advantages:
        - Time Complexity: O(n log k)
        - n: total number of nodes across all lists
        - k: number of linked lists
        - We only keep at most k nodes in the heap at any given time,
        resulting in faster heap operations compared to inserting all n values.
        - We store the actual nodes in the heap instead of just their values,
        which allows us to build the final list without allocating new ListNode objects.
        
        Why it’s better than the O(n log n) version:
        - That version inserts all node values into the heap, resulting in a heap of size n.
        - This leads to slower heappush/heappop operations (log n vs log k).
        - It also creates new ListNodes instead of reusing the original ones, which is unnecessary.
        '''

        import heapq

        heap = []

        # Initialize the heap with the head node of each list.
        # Use (node.val, id(node), node) to handle cases where node values are equal.
        # 'id(node)' ensures that Python can compare tuples without errors if values are the same.
        for node in lists:
            if node:
                heapq.heappush(heap, (node.val, id(node), node))

        dummy = ListNode()
        current = dummy

        # Continuously pop the smallest node from the heap and attach it to the merged list
        while heap:
            val, _, node = heapq.heappop(heap)
            current.next = node
            current = current.next

            # If the current node has a next node, push it into the heap
            # This ensures we always maintain one node per list in the heap
            if node.next:
                heapq.heappush(heap, (node.next.val, id(node.next), node.next))

        return dummy.next

    
    '''
    # Option #4
    - Divide and Conquer (Devide k and use the same algorithm as 'MergeTwoSortedLists')
    - MergeTwoSortedLists: https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/src/solutions/python/MergeTwoSortedLists.py
    - O(n log k)
    - ref: https://www.youtube.com/watch?v=q5a5OiGbT6Q&t=65s
    '''
    def mergeKListsBest(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        if not lists or len(lists) == 0:
            return None
        
        while len(lists) > 1:
            merged_lists = []
            len_lists = len(lists)

            for i in range(0, len_lists, 2):
                l1 = lists[i]
                l2 = lists[i + 1] if (i + 1) < len_lists else None # odd or even
                merged_lists.append(self.mergeTwoLists(l1, l2))
            lists = merged_lists
        return lists[0]
    
    # MergeTwoSortedLists: https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/src/solutions/python/MergeTwoSortedLists.py
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