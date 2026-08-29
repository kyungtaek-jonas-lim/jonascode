from typing import Optional

'''
# Problem
 	- `Link`: https://leetcode.com/problems/validate-binary-search-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 4, 2025
	- `Answer`: isValidBST
'''

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:

    '''
    # Option #1
    - DFS
    - O(n)
    - August 29, 2026
    '''
    def isValidBST(self, root: Optional[TreeNode]) -> bool:

        def dfs(node: Optional[TreeNode], low: int, high: int) -> bool:
            if not node:
                return True
            
            if not (low < node.val < high):
                return False
            
            return dfs(node.left, low, node.val) and dfs(node.right, node.val, high)
        
        return dfs(root, float('-inf'), float('inf'))
    

if __name__ == "__main__":
    sol = Solution()
    
    # [2, 1, 3]
    root = TreeNode(2)
    root.left = TreeNode(1)
    root.right = TreeNode(3)
    print(sol.isValidBST(root)) # true

    # [5,1,4,None,None,3,6]
    root = TreeNode(5)
    root.left = TreeNode(1)
    root.right = TreeNode(4)
    root.right.left = TreeNode(3)
    root.right.right = TreeNode(6)
    print(sol.isValidBST(root)) # false