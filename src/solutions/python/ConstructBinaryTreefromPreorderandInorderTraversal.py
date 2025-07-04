from typing import Optional, List

'''
# Problem
 	- `Link`: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 4, 2025
	- `Answer`: buildTree / buildTreeAdvanced
'''

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:

    '''
    # Option 1
    - DFS Recurisve
    - O(n^2)
    - https://www.youtube.com/watch?v=ihj4IQGZ2zc
    '''
    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        if not preorder or not inorder:
            return None
        root = TreeNode(preorder[0])
        mid = inorder.index(root.val)
        root.left = self.buildTree(preorder[1:mid + 1], inorder[:mid])
        root.right = self.buildTree(preorder[mid + 1:], inorder[mid + 1:])
        return root

    '''
    # Option 2
    - Advanced
    - O(n)
    '''
    def buildTreeAdvanced(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        n: int = len(preorder)
        inorder_map: dict = {inorder[i]: i for i in range(n)}
        curr_preorder_index = 0

        def dfs(left: int, right: int) -> Optional[TreeNode]:
            nonlocal curr_preorder_index
            if left > right:
                return None
            
            root = TreeNode(preorder[curr_preorder_index])
            curr_preorder_index += 1
            index = inorder_map[root.val]

            root.left = dfs(left, index - 1)
            root.right = dfs(index + 1, right)
            return root
        
        return dfs(0, n - 1)

            
