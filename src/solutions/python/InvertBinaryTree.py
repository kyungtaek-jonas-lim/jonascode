from typing import Optional
import collections

'''
# Problem
 	- `Link`: https://leetcode.com/problems/invert-binary-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 24
	- `Answer`: invertTreeBfs / invertTreeRecursiveDfs / invertTreeIterativeDfs
'''

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:

    '''
    # Option #1
    - BFS(FIFO)
    - O(n)
    '''
    def invertTreeBfs(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        
        if not root: return root

        deque = collections.deque()
        deque.append(root)
        
        while deque:
            n = deque.popleft()
            if n:
                n.left, n.right = n.right, n.left
                deque.append(n.left)
                deque.append(n.right)
        
        return root


    '''
    # Option #2
    - Recursive DFS(FILO)
    - O(n)
    '''
    def invertTreeRecursiveDfs(self, root: Optional[TreeNode]) -> Optional[TreeNode]:

        if not root: return root

        root.left, root.right = self.invertTree(root.right), self.invertTree(root.left)

        return root
    

    '''
    # Option #3
    - Iterative(/Stack-based) DFS(FILO)
    - O(n)
    '''
    def invertTreeIterativeDfs(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        
        if not root: return root

        stack = [root]
        
        while stack:
            n = stack.pop()
            if n:
                n.left, n.right = n.right, n.left
                stack.append(n.left)
                stack.append(n.right)
        
        return root