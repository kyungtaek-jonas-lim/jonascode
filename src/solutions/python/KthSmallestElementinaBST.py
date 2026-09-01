from typing import Optional, List
import collections

'''
# Problem
 	- `Link`: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 4, 2025
	- `Answer`: kthSmallestBfs / kthSmallestDfsRecursive / kthSmallestDfsRecursive2 / kthSmallestDfsIterative
'''

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:

    '''
    # Option #1
    - BFS - Put all and sort
    - O(n log n)
    '''
    def kthSmallestBfs(self, root: Optional[TreeNode], k: int) -> int:
        
        deque = collections.deque([root])
        result: List[int] = []
        
        while deque:
            node: TreeNode = deque.popleft()
            if node:
                result.append(node.val)
                deque.append(node.left)
                deque.append(node.right)
        
        result.sort()
        return result[k - 1]


    '''
    # Option #2
    - DFS Recursive - Find the smallest and gets bigger
    - O(H + k) (H = the height of the tree)
    '''
    def kthSmallestDfsRecursive(self, root: Optional[TreeNode], k: int) -> int:
        
        # result = []
        count = 0
        result = None
        
        def dfs(node: TreeNode) -> int:
            nonlocal count, result
            # if not node or len(result) >= k:
            if not node or result is not None:
                return
            
            dfs(node.left)
            # result.append(node.val)
            count += 1
            if count == k:
                result = node.val
                return
            dfs(node.right)

        dfs(root)
        # return result[k - 1]
        return result


    '''
    # Option #3
    - DFS Recursive - Find the smallest and gets bigger
    - O(H + k) (H = the height of the tree)
    '''
    def kthSmallestDfsRecursive2(self, root: Optional[TreeNode], k: int) -> int:

        result: List[int] = [-1]
        def dfs(node: Optional[TreeNode], curr: int) -> int:
            if not node:
                return curr
            
            res: int = dfs(node.left, curr) + 1
            if res == 0:
                return -1

            if res == k:
                result[0] = node.val
                return -1

            res = dfs(node.right, res)
            if res == -1:
                return -1

            if res == k:
                result[0] = node.val
                return -1

            return res

        dfs(root, 0)
        return result[0]
    

    '''
    # Option #4
    - DFS Iterative - Find the smallest and gets bigger
    - O(H + k) (H = the height of the tree)
    '''
    def kthSmallestDfsIterative(self, root: Optional[TreeNode], k: int) -> int:
        
        stack: List[TreeNode] = []
        count = 0
        node: TreeNode = root

        while True:
            while node:
                stack.append(node)
                node = node.left

            node = stack.pop()
            count += 1

            if count == k:
                return node.val
            
            node = node.right