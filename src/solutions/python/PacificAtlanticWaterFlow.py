from typing import List
import collections

'''
# Problem
	- `Link`: https://leetcode.com/problems/pacific-atlantic-water-flow/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 18
	- `Answer`: pacificAtlantic / pacificAtlanticAdvanced / pacificAtlanticDfs / pacificAtlanticBfs
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
    

    '''
    # Option #3
    - O(m * n)
    - DFS - Basically, the same as Option #2
    - August 9, 2026
    '''    
    def pacificAtlanticDfs(self, heights: List[List[int]]) -> List[List[int]]:

        m, n = len(heights), len(heights[0])
        memo = [[False for _ in range(n)] for _ in range(m)]

        def dfs(x: int, y: int, prev: int) -> None:
            if x < 0 or y < 0 or x >= m or y >= n:
                return
            if memo[x][y] or heights[x][y] < prev:
                return

            memo[x][y] = True
            dfs(x + 1, y, heights[x][y])
            dfs(x - 1, y, heights[x][y])
            dfs(x, y + 1, heights[x][y])
            dfs(x, y - 1, heights[x][y])

        # Pacific
        for i in range(m):
            dfs(i, 0, 0)
        for j in range(n):
            dfs(0, j, 0)
        pacific = memo

        # Atlantic
        memo = [[False for _ in range(n)] for _ in range(m)]
        for i in range(m):
            dfs(i, n - 1, 0)
        for j in range(n):
            dfs(m - 1, j, 0)

        # Result
        result = []
        for i in range(m):
            for j in range(n):
                if pacific[i][j] and memo[i][j]:
                    result.append([i, j])

        return result


    '''
    # Option #4
    - O(m * n)
    - BFS - Basically, the same as Option #2, but BFS
    - August 9, 2026
    '''    
    def pacificAtlanticBfs(self, heights: List[List[int]]) -> List[List[int]]:
        m, n = len(heights), len(heights[0])
        deque = collections.deque()
        memo = [[False for _ in range(n)] for _ in range(m)]

        def bfs() -> None:
            while deque:
                (x, y, prev) = deque.popleft()
                if x < 0 or y < 0 or x >= m or y >= n or memo[x][y] or heights[x][y] < prev:
                    continue

                memo[x][y] = True
                deque.append((x + 1, y, heights[x][y]))
                deque.append((x - 1, y, heights[x][y]))
                deque.append((x, y + 1, heights[x][y]))
                deque.append((x, y - 1, heights[x][y]))

        # Pacific
        for i in range(m):
            deque.append((i, 0, 0))
            bfs()
        for j in range(n):
            deque.append((0, j, 0))
            bfs()
        pacific = memo

        # Atlantic
        deque.clear()
        memo = [[False for _ in range(n)] for _ in range(m)]
        for i in range(m):
            deque.append((i, n - 1, 0))
            bfs()
        for j in range(n):
            deque.append((m - 1, j, 0))
            bfs()

        # Result
        result = []
        for i in range(m):
            for j in range(n):
                if pacific[i][j] and memo[i][j]:
                    result.append([i, j])
        return result
