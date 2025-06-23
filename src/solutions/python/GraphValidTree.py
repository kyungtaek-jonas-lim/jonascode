from typing import List
import collections

'''
# Problem
	- `Link`
        - `LeetCode`: https://leetcode.com/problems/graph-valid-tree/
        - `LintCode`: https://www.lintcode.com/problem/178/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 23
	- `Answer`: validTreeBfs / validTreeDfs
'''


class Solution:

    '''
    # Option #1
    - BFS
    - O(n + e)
    '''
    def validTreeBfs(self, n: int, edges: List[List[int]]) -> bool:
        
        if len(edges) != n - 1:
            return False
        
        memo: dict = { x: [] for x in range(n) }
        
        for i in range(len(edges)):
            memo[edges[i][0]].append(edges[i][1])
            memo[edges[i][1]].append(edges[i][0])

        def bfs():
            visited = set()
            my_deque = collections.deque()
            my_deque.append((0, -1))

            while my_deque:
                curr, prev = my_deque.popleft()
                if curr in visited:
                    return False
                visited.add(curr)

                for neighbor  in memo.get(curr):
                    if neighbor  == prev: continue
                    my_deque.append((neighbor , curr))

            return len(visited) == n

        return bfs()


    '''
    # Option #2
    - DFS
    - O(n + e)
    - ref) https://www.youtube.com/watch?v=bXsUuownnoQ
    '''
    def validTreeDfs(self, n: int, edges: List[List[int]]) -> bool:
        
        if len(edges) != n - 1:
            return False
        
        memo: dict = { x: [] for x in range(n) }

        for n1, n2 in edges:
            memo[n1].append(n2)
            memo[n2].append(n1)
        
        visited = set()
        def dfs(curr: int, prev: int) -> bool:
            if curr in visited:
                return False
            
            visited.add(curr)
            for neighbor in memo[curr]:
                if neighbor == prev: continue
                if not dfs(neighbor, curr):
                    return False

            return True
        
        return dfs(0, -1) and n == len(visited)