from typing import Optional
import collections

'''
# Problem
 	- `Link`: https://leetcode.com/problems/maximum-depth-of-binary-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 24
	- `Answer`: maxDepthBfs / maxDepthRecursiveDfs / maxDepthIterativeDfs
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
    def maxDepthBfs(self, root: Optional[TreeNode]) -> int:
        
        if not root: return 0

        result: int = 0
        deque = collections.deque()
        deque.append(root)

        while deque:
            result += 1 # Increment one depth
            cnt = len(deque) # Get how many loop needed

            # Append the nodes of the next depth
            for _ in range(cnt):
                curr = deque.popleft()
                if curr.left:
                    deque.append(curr.left)
                if curr.right:
                    deque.append(curr.right)

        return result

    '''
    # Option #2
    - Recursive DFS(FILO)
    - O(n)
    '''
    def maxDepthRecursiveDfs(self, root: Optional[TreeNode]) -> int:
        if not root: return 0
        return 1 + max(self.maxDepth(root.left), self.maxDepth(root.right))


    '''
    # Option #3
    - Iterative(/Stack-based) DFS(FILO)
    - O(n)
    '''
    def maxDepthIterativeDfs(self, root: Optional[TreeNode]) -> int:
        
        stack = [(root, 1)]
        max_depth = 0

        while stack:
            node, depth = stack.pop()
            if node:
                max_depth = max(max_depth, depth)
                stack.append((node.left, depth + 1))
                stack.append((node.right, depth + 1))
        
        return max_depth