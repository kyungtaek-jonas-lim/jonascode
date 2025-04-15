from typing import List

'''
# Problem
	- `Link`: https://leetcode.com/problems/word-search/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Apr 15, 2025
	- `Answer`: exist
'''
class Solution:

    '''
    # Option #1
    - O (m * n * 3^L) (m = row, n = col, L = the length of word)
    '''
    def exist(self, board: List[List[str]], word: str) -> bool:
        
        m, n = len(board), len(board[0])

        # Move and search for a route (Recursive)
        def search(board_: List[List[str]], word_: str, x: int, y: int) -> bool:
            
            # Success
            if not word_:
                return True
            
            # Edge Case
            if not (0 <= x < m and 0 <= y < n):
                return False
            
            # Matches
            startChar_ = word_[0]
            if startChar_ != board_[x][y]:
                return False
            word_ = word_[1:]

            # Mark
            board_[x][y] = "0"

            # Move
            if search(board_, word_, x + 1, y):
                return True
            if search(board_, word_, x - 1, y):
                return True
            if search(board_, word_, x, y + 1):
                return True
            if search(board_, word_, x, y - 1):
                return True

            # Unmark
            board_[x][y] = startChar_
            return False

        startChar = word[0]
        for i in range(m):
            for j in range(n):
                if board[i][j] == startChar:
                    if search(board, word, i, j):
                        return True
                    
        return False