from typing import Optional, List

'''
# Problem
	- `Link`: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 12, 2025
	- `Answer`: removeNthFromEnd / removeNthFromEndDfs / removeNthFromEndTwoPointers / removeNthFromEndTotalCount
'''

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:

    '''
    # Option #1
    - Store Nodes into a List
    - O(n) (n = the number of nodes)
    - Space: O(n)
    '''
    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        
        # Edge case (length = 1)
        next = head.next
        if next == None:
            return None
        
        # Find the nth node from the end and store all the nodes to a list
        my_list: List[ListNode] = [head]
        while next:
            my_list.append(next)
            next = next.next
        
        # To-be-deleted node
        len_my_list = len(my_list)
        if n == 1: # Last node
            my_list[len(my_list) - 2].next = None
        elif len_my_list == n: # First node
            head = head.next
        else:
            my_list[len(my_list) - n - 1].next = my_list[len(my_list) - n + 1]
            
        return head
    

    '''
	# Option #2
	- DFS Recursive
    - O(n) (n = the number of nodes)
    - Space: O(n)
    '''
    def removeNthFromEndDfs(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        
        nthNode: ListNode = None
        result: ListNode = ListNode(next=head)
        
        def dfs(node: ListNode) -> int:
            nonlocal nthNode
            if node == None:
                return 0
            
            next = dfs(node.next)
            if next == n:
                nthNode = node
            return next + 1
        
        dfs(result)
        if nthNode:
            nthNode.next = nthNode.next.next

        return result.next
        
        

    '''
    # Option #3
    - Two Pointers (Better Space Complexity)
    - O(n) (n = the number of nodes)
    - Space: O(1)
    '''
    def removeNthFromEndTwoPointers(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        result = ListNode(next=head)
        curr = end = result

        # Have the n long distance
        for _ in range(n):
            end = end.next
        
        # Find the nth node with the distance
        while end.next:
            curr = curr.next
            end = end.next

        # Remove the node
        curr.next = curr.next.next

        return result.next
        
        

    '''
    # Option #4
    - Total Count (Better Space Complexity)
    - O(n) (n = the number of nodes)
    - Space: O(1)
    '''
    def removeNthFromEndTotalCount(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        
        result = ListNode(next=head)

        # Find total count
        curr = result
        total = 0
        while curr and curr.next:
            curr = curr.next.next
            total += 2
        if not curr:
            total -= 1

        # Find the nth Node from the first node
        goal, cnt = total - n, 0
        curr = result
        while goal != cnt:
            curr = curr.next
            cnt += 1

        # Remove the node
        curr.next = curr.next.next

        return result.next
        