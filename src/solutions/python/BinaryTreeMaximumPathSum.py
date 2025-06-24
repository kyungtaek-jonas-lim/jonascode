from typing import Optional
import collections

'''
# Problem
 	- `Link`: https://leetcode.com/problems/binary-tree-maximum-path-sum/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 24
	- `Answer`: maxPathSumRecursiveDfs / maxPathSumIterativeDfs / maxPathSumAdvancedDFS
'''

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:

    '''
    # Option #1
    - Recusive DFS
    - O(n)
    '''
    def maxPathSumRecursiveDfs(self, root: Optional[TreeNode]) -> int:

        results = [] # Max Candidates

        def dfs(node: Optional[TreeNode]):
            if not node: return float('-inf')
            if not node.left and not node.right:
                return node.val

            left = dfs(node.left) # Left way max value
            right = dfs(node.right) # Right way max value
            curr = node.val

            results.append(max(left, right, curr + left + right)) # If it goes both way, the node is the top node
            return max(curr, curr + left, curr + right) # Return to the parent node with one-way max value

        results.append(dfs(root)) # When the root is the top node
        return max(results)


    '''
    # Option #2
    - Iterative DFS
    - O(n)
    '''
    def maxPathSumIterativeDfs(self, root: Optional[TreeNode]) -> int:
            
        stack = [root]
        cnt = 1
        results = []

        while cnt:
            n: int = len(stack)
            temp, cnt = cnt, 0
            for i in range(n - 1, n - 1 - temp, -1):
                if stack[i].left:
                    stack.append(stack[i].left)
                    cnt += 1
                if stack[i].right:
                    stack.append(stack[i].right)
                    cnt += 1                
        
        while stack:
            node = stack.pop()
            if not node.left and not node.right:
                results.append(node.val)
                continue

            curr, left, right = node.val, 0, 0
            if node.left and node.right:
                left = node.left.val
                right = node.right.val
                results.append(max(left, right, curr + left + right))

            elif node.left:
                left = node.left.val
                results.append(max(left, curr + left + right))

            elif node.right:
                right = node.right.val
                results.append(max(right, curr + left + right))

            node.val = max(curr, curr + left, curr + right)


        results.append(root.val)
        return max(results)


    '''
    # Option #3
    - Advanced DFS
    - O(n)
    - ref) https://www.youtube.com/watch?v=Hr5cWUld4vU
    '''
    def maxPathSumAdvancedDFS(self, root: Optional[TreeNode]) -> int:
        
        result = [root.val]

        # return max path sum without split
        def dfs(node: Optional[TreeNode]):
            if not node: return 0

            left = max(dfs(node.left), 0) # If left max is less than 0, just put 0 (use only current node value)
            right = max(dfs(node.right), 0)
            
            # comput max path sum with split
            result[0] = max(result[0], node.val + left + right)
        
            return node.val + max(left, right)

        dfs(root)
        return result[0]
