from typing import Optional, List
import collections

'''
# Problem
 	- `Link`: https://leetcode.com/problems/binary-tree-level-order-traversal/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 24
	- `Answer`: levelOrder
'''

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:

    '''
    # Option #1
    - BFS
    - O(n)
    '''
    def levelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        
        if not root: return []
        deque = collections.deque([root])
        result = []
        while deque:
            
            size = len(deque)
            item = []
            for _ in range(size):
                node = deque.popleft()
                item.append(node.val)
                if node.left: deque.append(node.left)
                if node.right: deque.append(node.right)

            if item:
                result.append(item)

        return result