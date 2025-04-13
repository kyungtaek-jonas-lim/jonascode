from typing import List
'''
# Problem
	- `Link`: https://leetcode.com/problems/spiral-matrix/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 13, 2025
	- `Answer`: spiralOrder / spiralOrderAdvanced
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
        
        # Set the limit
        top, bottom = 0, len(matrix) - 1
        left, right = 0, len(matrix[0]) - 1

        # Move
        result = []
        while top <= bottom and left <= right:

            # Move Right
            for i in range(left, right + 1):
                result.append(matrix[top][i])
            top += 1 # Narrow the limit (Checked all the cells on the top)

            # Move Down
            for i in range(top, bottom + 1):
                result.append(matrix[i][right])
            right -= 1 # Narrow the limit (Checked all the cells on the right)

            if not (top <= bottom and left <= right):
                break

            # Move Left
            for i in range(right, left - 1, -1):
                result.append(matrix[bottom][i])
            bottom -= 1 # Narrow the limit (Checked all the cells on the bottom)
            
            # Move Up
            for i in range(bottom, top - 1, -1):
                result.append(matrix[i][left])
            left += 1 # Narrow the limit (Checked all the cells on the left)
        
        return result


if __name__ == "__main__":
    sol = Solution()
    print(sol.spiralOrder([[1,2,3],[4,5,6],[7,8,9]]))
    print(sol.spiralOrder([[1,2,3,4],[5,6,7,8],[9,10,11,12]]))