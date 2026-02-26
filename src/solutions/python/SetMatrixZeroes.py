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
    - Space Complexity: O(m + n)
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
    - Space Complexity: O(1)
    '''
    def setZeroesAdvanced(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        m, n = len(matrix), len(matrix[0])
        row, col = False, False

        # Check First row & col
        for j in range(n):
            if not matrix[0][j]:
                row = True
                break
        for i in range(m):
            if not matrix[i][0]:
                col = True
                break
        
        # Check The other row
        for i in range(1, m):
            for j in range(1, n):
                if not matrix[i][j]:
                    matrix[0][j] = 0
                    matrix[i][0] = 0
        
        # Put 0 based on the first row & col
        for j in range(1, n):
            if not matrix[0][j]:
                for i in range(1, m):
                    matrix[i][j] = 0

        for i in range(1, m):
            if not matrix[i][0]:
                for j in range(1, n):
                    matrix[i][j] = 0
        
        # Change First row & col
        if row:
            for j in range(n):
                matrix[0][j] = 0

        if col:
            for i in range(m):
                matrix[i][0] = 0