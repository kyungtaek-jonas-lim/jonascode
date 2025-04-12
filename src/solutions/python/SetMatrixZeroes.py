from typing import List

'''
# Problem
	- `Link`: https://leetcode.com/problems/set-matrix-zeroes/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 10, 2025
	- `Answer`: setZeroes / setZeroesAdvanced
'''
class Solution:

    '''
    # Option #1
    - Common Way
    - O (m * n)
    '''
    def setZeroes(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        m = len(matrix)
        n = len(matrix[0])

        row_set = set()
        col_set = set()
        
        for i in range(m):
            for j in range(n):
                if not matrix[i][j]:
                    row_set.add(i)
                    col_set.add(j)
        
        for i in range(m):
            for j in range(n):
                if i in row_set or j in col_set:
                    matrix[i][j] = 0


    '''
    # Option #2
    - Advanced Way
    - O (m * n)
    - https://www.youtube.com/watch?v=T41rL0L3Pnw
    '''
    def setZeroesAdvanced(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """

        '''
        1. Determin which rows/cols need to be zero by updating the first row, the first colum based on the values, matrix[i][j] (i > 0, j > 0)
            (When it comes to the first colum, exclude the first row because the first row has the cell)
            => So use two row, 'matrix[0][j]' + 'matrix[i][j] (i > 0)'
        2. Update matrix[i][j] (i > 0) based on the first row and the first column.
        3. Update the first row based on the seperate variable. (row_zero)
        '''
        m = len(matrix)
        n = len(matrix[0])

        row_zero = False # If it's true, all the values of the row is zero. So store it seperately.

        # ---
        # 1. Determin which rows/cols need to be zero
        for i in range(m):
            for j in range(n):

                # If the value is zero,
                if not matrix[i][j]:
                    matrix[0][j] = 0 # Update the first row value of the same column to Zero

                    if i > 0: # Exclude the first row
                        matrix[i][0] = 0
                    else: # If it's the first row, keep it as is for now. It will be updated later because it should serve as a pivot for all the other rows, except the first one itself, so it cannot be updated yet.
                        row_zero = True

        # ---
        # 2. Update values with Zero
        for i in range(1, m): # Skip the first row
            for j in range(1, n): # Skip the first col
                if matrix[0][j] == 0 or matrix[i][0] == 0:
                    matrix[i][j] = 0

        # For the first column
        if matrix[0][0] == 0:
            for i in range(1, m):
                matrix[i][0] = 0

        # ---
        # 3. For the first row (now you can update the pivot row)
        if row_zero:
            for j in range(n):
                matrix[0][j] = 0