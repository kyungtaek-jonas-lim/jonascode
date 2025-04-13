from typing import List
'''
# Problem
	- `Link`: https://leetcode.com/problems/spiral-matrix/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 13, 2025
	- `Answer`: spiralOrder
'''

class Solution:

    '''
    # Option #1
    - O (m * n)
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

if __name__ == "__main__":
    sol = Solution()
    print(sol.spiralOrder([[1,2,3],[4,5,6],[7,8,9]]))
    print(sol.spiralOrder([[1,2,3,4],[5,6,7,8],[9,10,11,12]]))