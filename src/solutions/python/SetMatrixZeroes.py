from typing import List

'''
# Problem
	- `Link`: https://leetcode.com/problems/set-matrix-zeroes/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 10, 2025
	- `Answer`: setZeroes
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
        n = len(matrix)
        m = len(matrix[0])

        row_set = set()
        col_set = set()
        
        for i in range(n):
            for j in range(m):
                if not matrix[i][j]:
                    row_set.add(i)
                    col_set.add(j)
        
        for i in range(n):
            for j in range(m):
                if i in row_set or j in col_set:
                    matrix[i][j] = 0