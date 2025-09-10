from typing import List

'''
# Problem
	- `Link`: https://leetcode.com/problems/pacific-atlantic-water-flow/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 18
	- `Answer`: pacificAtlantic / pacificAtlanticAdvanced
'''

class Solution:
    
    '''
    # Option #1
    - O((m * n) ^ 2)
    '''
    def pacificAtlantic(self, heights: List[List[int]]) -> List[List[int]]:
        
        m, n = len(heights), len(heights[0])
        move: List[List[int]] = [[1, 0], [-1, 0], [0, 1], [0, -1]]
        visited: List[List[bool]] = None
        pac, atl = False, False
        result: List[List[int]] = []
        
        def dfs(x: int, y: int, prev: int) -> bool:
            nonlocal pac, atl
            if x < 0 or y < 0 or x >= m or y >= n: return False
            if prev < heights[x][y]: return False
            if visited[x][y]: return False
            visited[x][y] = True

            if x == 0 or y == 0: pac = True
            if x == m - 1 or y == n - 1: atl = True
            if pac and atl: return True

            for dx, dy in move:
                if dfs(x + dx, y + dy, heights[x][y]):
                    return True
            
            return False

        for i in range(m):
            for j in range(n):
                pac, atl = False, False
                visited = [[False] * n for _ in range(m)]
                if dfs(i, j, float('inf')):
                    result.append([i, j])
        
        return result
    

    '''
    # Option #2
    - O(m * n)
    - Start from ocean (Move from one ocean to the other ocean if the previous height is not taller)
    '''    
    def pacificAtlanticAdvanced(self, heights: List[List[int]]) -> List[List[int]]:
        m, n = len(heights), len(heights[0])

        def dfs(x: int, y: int, prev: int, visited: List[List[bool]]):
            if x < 0 or y < 0 or x >= m or y >= n: return
            if prev > heights[x][y]: return
            if visited[x][y]: return
            visited[x][y] = True

            dfs(x - 1, y, heights[x][y], visited)
            dfs(x + 1, y, heights[x][y], visited)
            dfs(x, y - 1, heights[x][y], visited)
            dfs(x, y + 1, heights[x][y], visited)
        
        pac, atl = [[False] * n for _ in range(m)], [[False] * n for _ in range(m)]
        for i in range(m):
            dfs(i, 0, 0, pac)
        for i in range(n):
            dfs(0, i, 0, pac)
        for i in range(m):
            dfs(i, n - 1, 0, atl)
        for i in range(n):
            dfs(m - 1, i, 0, atl)
        
        result: List[List[int]] = []
        for i in range(m):
            for j in range(n):
                if pac[i][j] and atl[i][j]:
                    result.append([i, j])
        return result