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
    '''
    def isValidBST(self, root: Optional[TreeNode]) -> bool:

        if not root: return True
        
        def dfs(node: TreeNode, higher: int, lower: int) -> bool:
            
            if not node:
                return True

            if node.val >= higher or node.val <= lower:
                return False

            # if (node.left and node.val <= node.left.val) or node.right and node.val >= node.right.val:
            #     return False
            
            if not dfs(node.left, node.val, lower):
                return False

            if not dfs(node.right, higher, node.val):
                return False

            return True
        
        return dfs(root, float('inf'), float('-inf'))
    

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