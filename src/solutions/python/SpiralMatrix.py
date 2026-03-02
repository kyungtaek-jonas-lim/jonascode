from typing import List
'''
# Problem
	- `Link`: https://leetcode.com/problems/spiral-matrix/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 13, 2025
	- `Answer`: spiralOrder / spiralOrderAdvanced / spiralOrderDfs
'''

class Solution:

    '''
    # Option #1
    - Mark the checked cell
    - O (m * n)
    - Extra Space Complexity: O(m * n)
    '''
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        
        m, n = len(matrix), len(matrix[0])
        marked_matrix = [[False] * n for _ in range(m)]
        
        arrow = 0
        arrow_matrix = [[0, 1], [1, 0], [0, -1], [-1, 0]]

        result = []
        i, j = 0, 0
        while not marked_matrix[i][j]:

            # Put the result
            result.append(matrix[i][j])

            # Mark
            marked_matrix[i][j] = True

            # Move
            i += arrow_matrix[arrow][0]
            j += arrow_matrix[arrow][1]

            # Set Arrow
            if i < 0 or i >= m or j < 0 or j >= n or marked_matrix[i][j]:
                i -= arrow_matrix[arrow][0]
                j -= arrow_matrix[arrow][1]
                arrow = (arrow + 1) % 4
                i += arrow_matrix[arrow][0]
                j += arrow_matrix[arrow][1]
                
                # If it's finished checking all the cells
                if i < 0 or i >= m or j < 0 or j >= n or marked_matrix[i][j]:
                    break
        
        return result
    
    '''
    # Option #2
    - Narrow the zone(matrix) to be checked
    - O (m * n)
    - Extra Space Complexity: O(1)
    - ref: https://www.youtube.com/watch?v=BJnMZNwUk1M
    '''
    def spiralOrderAdvanced(self, matrix: List[List[int]]) -> List[int]:
        
        t, b, l, r = 0, len(matrix), 0, len(matrix[0])
        result = []
        
        while t < b and l < r:
            
            # right
            for i in range(l, r):
                result.append(matrix[t][i])
            t += 1

            # down
            for i in range(t, b):
                result.append(matrix[i][r - 1])
            r -= 1

            # Check the condition to prevent the duplicates
            if not (t < b and l < r):
                break

            # left
            for i in range(r - 1, l - 1, -1):
                result.append(matrix[b - 1][i])
            b -= 1

            # up
            for i in range(b - 1, t - 1, -1):
                result.append(matrix[i][l])
            l += 1

        return result
    
    '''
    # Option #3
    - DFS traversal with direction control
    - O(m * n)
    - Extra Space Complexity: O(m * n)
    - visited matrix: O(m * n)
    - recursion stack: O(m * n) in worst case
    - A DFS version is implemented, but since spiral traversal is deterministic and does not require graph exploration, a boundary-based iterative solution is more optimal in Python due to lower overhead and O(1) extra space.
    '''
    def spiralOrderDfs(self, matrix: List[List[int]]) -> List[int]:

        result = []
        m, n = len(matrix), len(matrix[0])
        # done = set()
        done = [[False] * n for _ in range(m)]
        dx, dy = [0, 1, 0, -1], [1, 0, -1, 0]
        
        def dfs(x: int, y: int, d: int, final: bool) -> None:
            # if x < 0 or y < 0 or x >= m or y >=n or (x, y) in done:
            if x < 0 or y < 0 or x >= m or y >=n or done[x][y]:
                if final:
                    return
                x -= dx[d]
                y -= dy[d]
                d = (d + 1) % 4
                final = True
            else:
                final = False
                # done.add((x, y))
                done[x][y] = True
                result.append(matrix[x][y])
            dfs(x + dx[d], y + dy[d], d, final)
        
        dfs(0, 0, 0, False)
        return result


if __name__ == "__main__":
    sol = Solution()
    print(sol.spiralOrder([[1,2,3],[4,5,6],[7,8,9]]))
    print(sol.spiralOrder([[1,2,3,4],[5,6,7,8],[9,10,11,12]]))