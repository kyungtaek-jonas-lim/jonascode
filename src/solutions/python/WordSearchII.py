from typing import Optional, List, Dict
import collections

'''
# Problem
 	- `Link`: https://leetcode.com/problems/word-search-ii/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 6, 2025
	- `Answer`: Solution / SolutionPrefixNode / SolutionPrefixNode2
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
    

'''
# Option #2
- Prefix Node (Trie)
- O((W * L) + m * n * 4^L) (W = The length of words list, L = the longest length of all the words)
'''
class TreeNode:
    def __init__(self):
        self.children = {}
        self.end = False

class SolutionPrefixNode:
    def findWords(self, board: List[List[str]], words: List[str]) -> List[str]:
        
        root = TreeNode()


        # Insert Words
        for word in words:
            curr = root

            for c in word:
                if c not in curr.children:
                    curr.children[c] = TreeNode()
                curr = curr.children[c]
            curr.end = True


        # Search Function
        result = set()
        m, n = len(board), len(board[0])
        def dfs(x: int, y: int, curr: TreeNode, visited: set, word: List[str]):
            if x < 0 or y < 0 or x >= m or y >= n:
                return
            if (x, y) in visited:
                return
            cw: str = board[x][y]
            if cw not in curr.children:
                return

            visited.add((x, y))
            word.append(cw)
            curr = curr.children[cw]
            if curr.end:
                result.add("".join(word))

            for dx, dy in [[1, 0], [-1, 0], [0, 1], [0, -1]]:
                dfs(x + dx, y + dy, curr, visited, word)

            visited.remove((x, y))
            word.pop()
            
        
        # Search
        for i in range(m):
            for j in range(n):
                dfs(i, j, root, set(), [])
        return list(result)
    

'''
# Option #3
- Prefix Node (Trie)
- O((W * L) + m * n * 4^L) (W = The length of words list, L = the longest length of all the words)
- September 6, 2026
'''
class TreeNode:
    def __init__(self):
        self.children: Dict[str, TreeNode] = {}
        self.word: Optional[str] = None

class SolutionPrefixNode2:
    def __init__(self):
        self.root: Dict[str, TreeNode] = {}
        self.result: List[str] = []

    def findWords(self, board: List[List[str]], words: List[str]) -> List[str]:

        def addWord(word: str) -> None:
            if word[0] not in self.root:
                self.root[word[0]] = TreeNode()
            node: TreeNode = self.root[word[0]]
            
            for c in word[1:]:
                if c not in node.children:
                    node.children[c] = TreeNode()
                node = node.children[c]
            
            node.word = word
        
        for w in words:
            addWord(w)
        

        m: int = len(board)
        n: int = len(board[0])

        def dfs(x: int, y: int, node: TreeNode) -> None:
            if x < 0 or y < 0 or x >= m or y >= n or board[x][y] not in node.children:
                return

            node = node.children[board[x][y]]
            if node.word:
                self.result.append(node.word)
                node.word = None

            temp: str = board[x][y]
            board[x][y] = '#'
            dfs(x + 1, y, node)
            dfs(x - 1, y, node)
            dfs(x, y + 1, node)
            dfs(x, y - 1, node)
            board[x][y] = temp

        for i in range(m):
            for j in range(n):
                if board[i][j] not in self.root:
                    continue
                node = self.root[board[i][j]]
                if node.word:
                    self.result.append(node.word)
                    node.word = None
                temp: str = board[i][j]
                board[i][j] = '#'
                dfs(i + 1, j, node)
                dfs(i - 1, j, node)
                dfs(i, j + 1, node)
                dfs(i, j - 1, node)
                board[i][j] = temp
        
        return self.result




if __name__ == "__main__":
    sol = Solution()
    print(sol.findWords([["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], ["oath","pea","eat","rain"]))
    print(sol.findWords([["o","a","b","n"],["o","t","a","e"],["a","h","k","r"],["a","f","l","v"]], ["oa","oaa"]))
    print(sol.findWords([["a","a"]], ["aaa"]))
    print(sol.findWords([["a","b","c","e"],["x","x","c","d"],["x","x","b","a"]], ["abc","abcd"]))

    sol = SolutionPrefixNode()
    print(sol.findWords([["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], ["oath","pea","eat","rain"]))
    print(sol.findWords([["o","a","b","n"],["o","t","a","e"],["a","h","k","r"],["a","f","l","v"]], ["oa","oaa"]))
    print(sol.findWords([["a","a"]], ["aaa"]))
    print(sol.findWords([["a","b","c","e"],["x","x","c","d"],["x","x","b","a"]], ["abc","abcd"]))