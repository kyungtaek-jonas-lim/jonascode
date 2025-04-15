from typing import List

'''
# Problem
	- `Link`: https://leetcode.com/problems/rotate-image/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 15, 2025
	- `Answer`: rotate / rotateAdvanced
'''
class Solution:

    '''
	# Option #1
    - Moving one row at a time (4 rows as a pair)
	- O(n^2)
    '''
    def rotate(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        
        m = len(matrix)
        
        top_left = 0
        bottom_right = m - 1

        tmp = [[], [], [], []]

        while top_left < bottom_right:
            
            # ---
            # Data Save
            # top & right
            for i in range(top_left, bottom_right + 1):
                tmp[0].append(matrix[top_left][i])
                tmp[1].append(matrix[i][bottom_right])
            
            # bottom & left
            for i in range(bottom_right, top_left - 1, -1):
                tmp[2].append(matrix[bottom_right][i])
                tmp[3].append(matrix[i][top_left])
            
            # ---
            # Data Update
            # top & right
            for i in range(top_left, bottom_right + 1):
                matrix[top_left][i] = tmp[3].pop(0)
                matrix[i][bottom_right] = tmp[0].pop(0)
            
            # bottom & left
            for i in range(bottom_right, top_left -1, -1):
                matrix[bottom_right][i] = tmp[1].pop(0)
                matrix[i][top_left] = tmp[2].pop(0)
            
            top_left += 1
            bottom_right -= 1
        
        print(matrix)



    '''
	# Option #2
    - Moving one cell at a time (4 cells as a pair)
	- O(n^2)
    - ref: https://www.youtube.com/watch?v=fMSJSS7eO1w
    '''
    def rotateAdvanced(self, matrix: List[List[int]]) -> None:
        
        """
        Do not return anything, modify matrix in-place instead.
        """
        top_left = 0
        bottom_right = len(matrix) - 1

        while top_left < bottom_right:
            for i in range(bottom_right - top_left):
                # i is the offset
                # (+col from top-left, +row from top-right, -col form bottom-right, -row from bottom-left)
            
                # Save top-left
                top_left_val = matrix[top_left][top_left + i]

                # Move from bottom-left to top-left
                matrix[top_left][top_left + i] = matrix[bottom_right - i][top_left]

                # Move from bottom-right to bottom-left
                matrix[bottom_right - i][top_left] = matrix[bottom_right][bottom_right - i]

                # Move from top-right to bottom-right
                matrix[bottom_right][bottom_right - i] = matrix[top_left + i][bottom_right]

                # Move from top-left to top-right
                matrix[top_left + i][bottom_right] = top_left_val

            top_left += 1
            bottom_right -= 1


if __name__ == "__main__":
    sol = Solution()
    sol.rotate([[1,2,3],[4,5,6],[7,8,9]])
    sol.rotate([[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]])