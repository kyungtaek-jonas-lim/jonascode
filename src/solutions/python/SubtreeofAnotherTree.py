from typing import Optional
import collections

'''
# Problem
 	- `Link`: https://leetcode.com/problems/subtree-of-another-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 3, 2025
	- `Answer`: isSubtreeBfs / isSubtreeDfsRecursive
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
    - O(m * n) (m = the number of root nodes, n = the number of subRoot nodes)
    '''
    def isSubtreeBfs(self, root: Optional[TreeNode], subRoot: Optional[TreeNode]) -> bool:
        
        if not root and not subRoot:
            return True

        deque = collections.deque()
        deque.append(root)

        while deque:
            node: TreeNode = deque.popleft()

            if not node:
                continue

            if node.val == subRoot.val:
                search_deque = collections.deque([(node, subRoot)])
                success = True
                while search_deque:
                    n1, n2 = search_deque.popleft()
                    if not n1 and not n2:
                        continue
                    if not n1 or not n2 or n1.val != n2.val:
                        success = False
                        break
                    search_deque.append((n1.left, n2.left))
                    search_deque.append((n1.right, n2.right))
                
                if success:
                    return True
            
            deque.append(node.left)
            deque.append(node.right)

        return False

    '''
    # Option #2
    - DFS Recursive
    - O(m * n) (m = the number of root nodes, n = the number of subRoot nodes)
    '''
    def isSubtreeDfsRecursive(self, root: Optional[TreeNode], subRoot: Optional[TreeNode]) -> bool:
        
        def isSame(n1: TreeNode, n2: TreeNode) -> bool:
            if not n1 and not n2: return True
            if not n1 or not n2 or n1.val != n2.val: return False

            if not isSame(n1.left, n2.left) or not isSame(n1.right, n2.right):
                return False

            return True

        def dfs(node: TreeNode) -> bool:
            if isSame(node, subRoot):
                return True

            if node and (dfs(node.left) or dfs(node.right)):
                return True

            return False

        return dfs(root)
