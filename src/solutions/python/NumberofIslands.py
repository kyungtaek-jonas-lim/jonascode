from typing import List
import collections

'''
# Problem
	- `Link`: https://leetcode.com/problems/number-of-islands/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 19
	- `Answer`: numIslands / numIslandsAdvanced / numIslandsBfs
'''

class Solution:

    '''
    # Option #1
    - O(m × n)
    - DFS using Set
    '''
    def numIslands(self, grid: List[List[str]]) -> int:
        
        m, n = len(grid), len(grid[0])
        visited = set()

        def dfs(x: int, y: int):
            if (x < 0 or y < 0 or x == m or y == n):
                return
            if (x, y) in visited:
                return
            if grid[x][y] == "0":
                return
            
            visited.add((x, y))

            for dx, dy in [[1, 0], [-1, 0], [0, 1], [0, -1]]:
                dfs(x + dx, y + dy)
        
        result: int = 0
        for i in range(m):
            for j in range(n):
                if (grid[i][j] == "1" and (i, j) not in visited):
                    dfs(i, j)
                    result += 1

        return result
    

    '''
    # Option #2
    - O(m × n)
    - DFS modifying the actual grid values instead of using Set
    '''
    def numIslandsAdvanced(self, grid: List[List[str]]) -> int:
        
        m, n = len(grid), len(grid[0])

        def dfs(x: int, y: int):
            if (x < 0 or y < 0 or x == m or y == n):
                return
            if grid[x][y] == "0":
                return
            
            grid[x][y] = '0'

            for dx, dy in [[1, 0], [-1, 0], [0, 1], [0, -1]]:
                dfs(x + dx, y + dy)
        
        result: int = 0
        for i in range(m):
            for j in range(n):
                if grid[i][j] == "1":
                    dfs(i, j)
                    result += 1

        return result


    '''
    # Option #3
    - O(m × n)
    - BFS (free from recursive stack call - more secure)
    '''
    def numIslandsBfs(self, grid: List[List[str]]) -> int:
        
        m, n = len(grid), len(grid[0])

        visited = set()
        result = 0

        def bfs(x: int, y: int):

            visited.add((x, y))
            q = collections.deque()
            q.append((x, y))

            while q:
                qx, qy = q.popleft()                
                for dx, dy in [[1, 0], [-1, 0], [0, 1], [0, -1]]:
                    x, y = qx + dx, qy + dy
                    if (x in range(m) and y in range(n) and
                        grid[x][y] == "1" and (x, y) not in visited):
                        q.append((x, y))
                        visited.add((x, y))


        for i in range(m):
            for j in range(n):
                if grid[i][j] == "1" and (i, j) not in visited:
                    bfs(i, j)
                    result += 1

        return result