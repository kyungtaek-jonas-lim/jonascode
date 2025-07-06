from typing import Optional, List
import collections

'''
# Problem
 	- `Link`: https://leetcode.com/problems/word-search-ii/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 6, 2025
	- `Answer`: findWords
'''
    
class Solution:

    '''
    # Option #1
    - dict for prefixes
    - O((W * L) + m * n * 4^L * L) (W = The length of words list, L = the longest length of all the words)
    '''
    def findWords(self, board: List[List[str]], words: List[str]) -> List[str]:
        
        m, n = len(board), len(board[0])
        result: set[str] = set() # Set to prevent duplication
        my_dict = {}

        # Save Prefix {prefix: real word}
        for word in words:
            w = ""
            for c in word:
                w += c
                if w not in my_dict:
                    my_dict[w] = []
                my_dict[w].append(word)

        def dfs(x: int, y: int, cw_list: List[str], visited: set) -> None: # cw: current word
            if x < 0 or y < 0 or x >= m or y >= n:
                return
            if (x, y) in visited:
                return
            
            visited.add((x, y))
            cw_list.append(board[x][y])

            goon = False # Go on if there's a valid prefixes, not complete words
            cw = "".join(cw_list)
            if cw in my_dict:
                for w in my_dict[cw]:
                    if w == cw:
                        result.add(w)
                    else:
                        goon = True

            # Stop if there's no valid prefixes
            if goon:
                # Moving one step (up, down, right, left)
                for dx, dy in [[1, 0], [-1, 0], [0, 1], [0, -1]]:
                    dfs(x + dx, y + dy, cw_list, visited)
            
            visited.remove((x, y))
            cw_list.pop()

        
        for i in range(m):
            for j in range(n):
                visited = set()
                dfs(i, j, [], visited)

        return list(result) # Transform return values to List
    



if __name__ == "__main__":
    sol = Solution()
    print(sol.findWords([["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], ["oath","pea","eat","rain"]))
    print(sol.findWords([["o","a","b","n"],["o","t","a","e"],["a","h","k","r"],["a","f","l","v"]], ["oa","oaa"]))
    print(sol.findWords([["a","a"]], ["aaa"]))
    print(sol.findWords([["a","b","c","e"],["x","x","c","d"],["x","x","b","a"]], ["abc","abcd"]))