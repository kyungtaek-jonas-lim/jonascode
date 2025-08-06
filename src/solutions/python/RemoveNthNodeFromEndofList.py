from typing import Optional, List

'''
# Problem
	- `Link`: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 12, 2025
	- `Answer`: removeNthFromEnd / removeNthFromEndDfsRecursive / removeNthFromEndTwoPointers
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
    def removeNthFromEndDfsRecursive(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        
        next: Optional[ListNode] = None

        def dfs(node: Optional[ListNode]) -> int:
            nonlocal next
            
            if not node:
                return 0
            current = 1 + dfs(node.next)
            if current == n - 1:
                next = node
            elif current == n + 1:
                node.next = next
            return current

        headNth: int = dfs(head)
        if headNth == n:
            return head.next
        return head
        

    '''
    # Option #3
    - Two Pointers (Better Space Complexity)
    - O(n) (n = the number of nodes)
    - Space: O(1)
    '''
    def removeNthFromEndTwoPointers(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        dummy = ListNode(0, head) # start from -1
        first = second = dummy
        
        # Advance 'first' by n+1 steps
        for _ in range(n + 1): # (n + 1)th node cause it starts from -1
            first = first.next
        
        # Move both pointers until 'first' reaches the end
        while first:
            first = first.next
            second = second.next
        
        # Remove the nth node from end
        second.next = second.next.next
        
        return dummy.next
