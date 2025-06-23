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
	- `Answer`: countComponentsBfs / countComponentsDfs
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
            deque.append((i, -1))

            while deque:
                curr, prev = deque.popleft()
                if curr in visited: continue
                visited.add(curr)
                
                for neighbor in memo.get(curr):
                    if neighbor == prev: continue
                    deque.append((neighbor, curr))

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
        def dfs(curr: int, prev: int) -> int:
            if curr in visited:
                return 0
            
            visited.add(curr)
            
            for neighbor in memo.get(curr):
                if neighbor == prev: continue
                dfs(neighbor, curr)

            return 1
        
        for i in range(n):
            result += dfs(i, -1)
        return result
