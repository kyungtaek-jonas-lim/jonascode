from typing import Optional
import collections

'''
# Problem
 	- `Link`: https://leetcode.com/problems/same-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 24
	- `Answer`: isSameTreeBfs / isSameTreeRecursiveDfs / isSameTreeIterativeDfs
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
    def isSameTreeBfs(self, p: Optional[TreeNode], q: Optional[TreeNode]) -> bool:
        
        deque = collections.deque()
        deque.append((p, q))

        while deque:
            n1, n2 = deque.popleft()
            if not n1 and not n2: continue
            if not n1 or not n2 or n1.val != n2.val: return False
            deque.append((n1.left, n2.left))
            deque.append((n1.right, n2.right))
        
        return True


    '''
    # Option #2
    - Recursive DFS(FILO)
    - O(n)
    '''
    def isSameTreeRecursiveDfs(self, p: Optional[TreeNode], q: Optional[TreeNode]) -> bool:
        
        if not p and not q: return True
        if not q or not p or p.val != q.val:
            return False
        
        if not self.isSameTree(p.left, q.left): return False
        if not self.isSameTree(p.right, q.right): return False
        return True
    

    '''
    # Option #3
    - Iterative(/Stack-based) DFS(FILO)
    - O(n)
    '''    
    def isSameTreeIterativeDfs(self, p: Optional[TreeNode], q: Optional[TreeNode]) -> bool:
        
        queue = [(p, q)]

        while queue:
            n1, n2 = queue.pop()
            if not n1 and not n2: continue
            if not n1 or not n2 or n1.val != n2.val: return False
            queue.append((n1.left, n2.left))
            queue.append((n1.right, n2.right))
        
        return True