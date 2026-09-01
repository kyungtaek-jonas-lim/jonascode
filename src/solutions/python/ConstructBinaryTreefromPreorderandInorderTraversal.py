from typing import Optional, List, Final, Dict

'''
# Problem
 	- `Link`: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 4, 2025
	- `Answer`: buildTreeSimple / buildTreeUsingInorderRangeAndIncreasingPreorderIndex
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
    def buildTreeSimple(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        if not preorder or not inorder:
            return None
        root = TreeNode(preorder[0])
        mid = inorder.index(root.val)
        root.left = self.buildTreeSimple(preorder[1:mid + 1], inorder[:mid])
        root.right = self.buildTreeSimple(preorder[mid + 1:], inorder[mid + 1:])
        return root

    '''
    # Option 2
	- DFS Recurisve Using Inorder Range & Increasing Preorder Index
	- O(n)
    '''            
    def buildTreeUsingInorderRangeAndIncreasingPreorderIndex(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        n: Final[int] = len(preorder)
        inorderIndexDict: Final[Dict[int, int]] = {inorder[i]: i for i in range(n)}
        
        currentPreorderIndex: List[int] = [0]
        def dfs(left: int, right: int) -> Optional[TreeNode]:
            if left >= right:
                return None
            
            node: Final[TreeNode] = TreeNode(preorder[currentPreorderIndex[0]])
            currentPreorderIndex[0] += 1
            mid: Final[int] = inorderIndexDict[node.val]

            node.left = dfs(left, mid)
            node.right = dfs(mid + 1, right)
            return node

        return dfs(0, n)