from typing import Optional, List
import collections

'''
# Problem
 	- `Link`: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 5, 2025
	- `Answer`: lowestCommonAncestorDfsRecursive / lowestCommonAncestorDfsRecursive2 / lowestCommonAncestorSearch / lowestCommonAncestorSearch2
'''

class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:

    '''
    # Option #1
    - DFS
    - O(log N) ~ O(N)
    '''
    def lowestCommonAncestorDfsRecursive(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        
        result: TreeNode = None

        def dfs(node: TreeNode):
            nonlocal result
            
            if not node:
                return

            if node.val > p.val and node.val > q.val:
                result = node
                dfs(node.left)
            elif node.val < p.val and node.val < q.val:
                result = node
                dfs(node.right)
            else:
                result = node

        dfs(root)
        return result

    '''
    # Option #2
    - DFS
    - O(log N) ~ O(N)
    - September 2, 2026
    '''
    def lowestCommonAncestorDfsRecursive2(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        if not root:
            return None
        
        if (root.val <= p.val and root.val >= q.val) or (root.val >= p.val and root.val <= q.val):
            return root
        if root.val < p.val and root.val < q.val:
            return self.lowestCommonAncestorDfsRecursive2(root.right, p, q)
        return self.lowestCommonAncestorDfsRecursive2(root.left, p, q)
    

    '''
    # Option #3
    - Search
    - O(log N) ~ O(N)
    '''
    def lowestCommonAncestorSearch(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':

        while root:
            if root.val < p.val and root.val < q.val:
                root = root.right
            elif root.val > p.val and root.val > q.val:
                root = root.left
            else:
                break

        return root
    

    '''
    # Option #4
    - Search
    - September 2, 2026
    '''
    def lowestCommonAncestorSearch2(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        while root:
            if (root.val <= p.val and root.val >= q.val) or (root.val >= p.val and root.val <= q.val):
                return root
            if root.val < p.val and root.val < q.val:
                root = root.right
            else:
                root = root.left
        return root