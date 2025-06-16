from typing import Optional

'''
# Problem
	- `Link`: https://leetcode.com/problems/clone-graph/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 16
	- `Answer`: cloneGraph
'''

# Definition for a Node.
class Node:
    def __init__(self, val = 0, neighbors = None):
        self.val = val
        self.neighbors = neighbors if neighbors is not None else []

from typing import Optional
class Solution:

    '''
    # Option #1
    - O(N + E) (N == the number of Nodes, E == the number of Edges)
    '''
    def cloneGraph(self, node: Optional['Node']) -> Optional['Node']:
        
        memo: dict = {}

        def dfs(n: Node):
            if n in memo:
                return memo[n]
            
            curr = Node(n.val)
            memo[n] = curr
            for nn in n.neighbors:
                curr.neighbors.append(dfs(nn))
            return curr

        return dfs(node) if node else None