from typing import List

'''
# Problem
	- `Link`: https://leetcode.com/problems/word-search/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: May 23, 2025
	- `Answer`: exist
'''
class Solution:

    '''
    # Option #1
    - O (m * n * 3^L) (m = row, n = col, L = the length of word)
    '''
    def exist(self, board: List[List[str]], word: str) -> bool:
        
        m, n = len(board), len(board[0])
        len_word = len(word)

        def dfs(x: int, y: int, index: int) -> bool:
            if index == len_word:
                return True
            if x < 0 or y < 0 or x >= m or y >= n:
                return False
            if board[x][y] != word[index]:
                return False

            temp = board[x][y]
            board[x][y] = '0'

            if (dfs(x + 1, y, index + 1)
                or dfs(x - 1, y, index + 1)
                or dfs(x, y + 1, index + 1)
                or dfs(x, y - 1, index + 1)
                ):
                return True

            board[x][y] = temp

            return False


        for i in range(m):
            for j in range(n):
                if dfs(i, j, 0):
                    return True

        return False