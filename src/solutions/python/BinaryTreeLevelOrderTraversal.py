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
        
        result: list = []
        deque = collections.deque([root])
        cnt: int = 1
        
        while deque:
            temp, cnt = cnt, 0
            res: list = []
            for _ in range(temp):
                node = deque.popleft()
                res.append(node.val)
                left, right = node.left, node.right
                if left:
                    deque.append(left)
                    cnt += 1
                if right:
                    deque.append(right)
                    cnt += 1
            result.append(res)

        return result