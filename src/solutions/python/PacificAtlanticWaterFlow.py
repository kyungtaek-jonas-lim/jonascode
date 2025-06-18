from typing import List

'''
# Problem
	- `Link`: https://leetcode.com/problems/pacific-atlantic-water-flow/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 18
	- `Answer`: pacificAtlantic
'''

class Solution:
    
    '''
    # Option #1
    - O((m * n) ^ 2)
    '''
    def pacificAtlantic(self, heights: List[List[int]]) -> List[List[int]]:
        
        m, n = len(heights), len(heights[0])
        visited = set()
        ocean = [False, False]

        def dfs(x: int, y: int, height: int) -> bool:
            if (x < 0 or y < 0 or x >= m or y >= n): return False
            if (heights[x][y] > height): return False

            if ((x, y) in visited): return False
            visited.add((x, y))

            if (x == 0 or y == 0): ocean[0] = True
            if (x == m - 1 or y == n - 1): ocean[1] = True

            if ocean[0] and ocean[1]: return True

            height = heights[x][y]
            if (dfs(x + 1, y, height)
                or dfs(x - 1, y, height)
                or dfs(x, y + 1, height)
                or dfs(x, y - 1, height)):
                return True
            
            visited.remove((x, y))

            return False

        result = []
        for i in range(m):
            for j in range(n):
                if dfs(i, j, heights[i][j]):
                    result.append([i, j])
                ocean[0] = False
                ocean[1] = False
                visited.clear()
        return result
    

    '''
    # Option #2
    - O(m * n)
    - Start from ocean (Move from one ocean to the other ocean if the previous height is not taller)
    '''    
    def pacificAtlanticAdvanced(self, heights: List[List[int]]) -> List[List[int]]:
        m, n = len(heights), len(heights[0])
        p, a = set(), set()

        def dfs(x, y, visited, h):
            if ((x, y) in visited or
                x < 0 or y < 0 or x == m or y == n or
                heights[x][y] < h):
                return

            visited.add((x, y))
            h = heights[x][y]
            for dx, dy in [[1, 0], [-1, 0], [0, 1], [0, -1]]:
                dfs(x + dx, y + dy, visited, h)
        
        
        # Pacific (top, left)
        for j in range(n):
            dfs(0, j, p, heights[0][j])
        for i in range(m):
            dfs(i, 0, p, heights[i][0])

        # Atlantic (bottom, right)
        for j in range(n):
            dfs(m - 1, j, a, heights[m - 1][j])
        for i in range(m):
            dfs(i, n - 1, a, heights[i][n - 1])

        # Sum
        result = list(p & a)
        # result = []
        # for i in range(m):
        #     for j in range(n):
        #         if (i, j) in p and (i, j) in a:
        #             result.append([i, j])
        return result