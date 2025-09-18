from typing import List
import collections

'''
# Problem
	- `Link`
        - `LeetCode`: https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
        - `LintCode`: https://www.lintcode.com/problem/3651/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 23
	- `Answer`: countComponentsBfs / countComponentsDfs / countComponentsAdvanced
'''



class Solution:
    
    """
    # Option #1
    - BFS
    - O(n + e)
    """
    def countComponentsBfs(self, n: int, edges: List[List[int]]) -> int:
        
        memo = { x: [] for x in range(n) }
        for n1, n2 in edges:
            memo[n1].append(n2)
            memo[n2].append(n1)

        result = 0
        visited = set()

        def bfs(i: int) -> int:
            if i in visited:
                return 0
            
            deque = collections.deque()
            deque.append(i)

            while deque:
                curr = deque.popleft()
                visited.add(curr)
                
                for neighbor in memo.get(curr):
                    if neighbor in visited: continue
                    deque.append(neighbor)

            return 1
        

        for i in range(n):
            result += bfs(i)
        return result
    
    """
    # Option #2
    - DFS
    - O(n + e)
    """
    def countComponentsDfs(self, n: int, edges: List[List[int]]) -> int:

        memo = { x: [] for x in range(n) }
        for n1, n2 in edges:
            memo[n1].append(n2)
            memo[n2].append(n1)

        visited = set()
        result = 0
        def dfs(curr: int) -> int:
            if curr in visited:
                return 0
            
            visited.add(curr)
            
            for neighbor in memo.get(curr):
                dfs(neighbor)

            return 1
        
        for i in range(n):
            result += dfs(i)
        return result

    """
    # Option #3
    - Union-Find
    - O(n + e)
    - https://www.youtube.com/watch?v=8f1XPm4WOUc
    """
    def countComponentsAdvanced(self, n: int, edges: List[List[int]]) -> int:
        parent = [ i for i in range(n) ] # Parent(Root) Node (parent[1] = the root node of 1)
        size = [1] * n # If a node merged, the root node gets +1

        # Find the root node
        def find(n):
            res = n
            while res != parent[res]: # If parent[n] has not itself, find the root node
                parent[res] = parent[parent[res]] # Put the root node of the root node as a parent(root) node
                res = parent[res]
            return res
        

        def union(n1, n2):
            p1, p2 = find(n1), find(n2) # Find the root nodes of each node

            if p1 == p2: # If the roots are the same, don't perform union
                return 0
            
            if size[p2] > size[p1]:
                parent[p1] = p2
                size[p2] += size[p1]
            else:
                parent[p2] = p1
                size[p1] += size[p2]

            return 1
        

        result = n
        for n1, n2 in edges:
            result -= union(n1, n2) # If union performed, decrement result 
        return result