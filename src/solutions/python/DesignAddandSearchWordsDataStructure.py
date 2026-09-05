from typing import Optional, List, Dict
import collections

'''
# Problem
 	- `Link`: https://leetcode.com/problems/design-add-and-search-words-data-structure/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 6, 2025
	- `Answer`: WordDictionary / WordDictionaryDfs / WordDictionaryBfs
'''

'''
# Option #1
- set
- O(n^3), O(1)
'''
class WordDictionary:

    def __init__(self):
        self.my_set = set()

    # O(n^3)
    def addWord(self, word: str) -> None:
        self.my_set.add(word)

        n = len(word)
        for i in range(n):
            w = word[:i] + '.'
            self.my_set.add(w + word[i + 1:])
            for j in range(i + 1, n):
                self.my_set.add(w + word[i + 1:j] + '.' + word[j + 1:])

    # O(1)
    def search(self, word: str) -> bool:
        return word in self.my_set

'''
# Option #2
- Prefix Tree (DFS)
- addWord: O(L)                          (L = length of the word)
- search:
    - Best case  (no '.'):  O(L)
    - Worst case (all '.'): O(26^L)       (26 = alphabet size)
- Space:
    - Trie storage: O(N × L)              (N = number of words stored)
    - search recursion stack: O(L)         ← DFS backtracks, only 1 path alive at a time
'''
class TreeNode:
    def __init__(self):
        self.children = {}
        self.end = False

class WordDictionaryDfs:

    def __init__(self):
        self.root = TreeNode()

    # O(L) (L = The length of the word)
    def addWord(self, word: str) -> None:
        curr = self.root
        for c in word:
            if c not in curr.children:
                curr.children[c] = TreeNode()
            curr = curr.children[c]
        curr.end = True

    # O(26^L) (L = The length of the word)
    def search(self, word: str) -> bool:
        n = len(word)

        def dfs(curr: TreeNode, i: int) -> bool:
            if i >= n:
                return curr.end
            
            if word[i] == '.':
                for temp in curr.children.values():
                    if dfs(temp, i + 1):
                        return True
                return False
            elif word[i] not in curr.children:
                return False

            return dfs(curr.children[word[i]], i + 1)

        return dfs(self.root, 0)
    


'''
# Option #3
- Prefix Tree (BFS)
- addWord: O(L)                          (L = length of the word)
- search:
    - Best case  (no '.'):  O(L)
    - Worst case (all '.'): O(26^L)
- Space:
    - Trie storage: O(N × L)              (N = number of words stored)
    - search queue: O(26^L)  ← WORSE than Option #1!
                            all nodes at current depth stay alive in the queue at once
- Date:
    - September 5, 2026
'''
class WordDictionaryBfs:

    def __init__(self):
        self.root: Dict[str, TreeNode] = {}

    def addWord(self, word: str) -> None:
        if word[0] not in self.root:
            self.root[word[0]] = TreeNode()
        node: TreeNode = self.root[word[0]]
        n: int = len(word)
        for c in word[1:]:
            if c not in node.children:
                node.children[c] = TreeNode()
            node = node.children[c]
        node.end = True

    def search(self, word: str) -> bool:
        deque = collections.deque()
        if word[0] == '.':
            if not self.root:
                return False
            for nd in self.root.values():
                deque.append((nd, 0))
            
        else:
            if word[0] not in self.root:
                return False
            deque.append((self.root[word[0]], 0))
        
        n: int = len(word)
        node: Optional[TreeNode] = None
        while deque:
            node, depth = deque.popleft()
            if depth == n - 1:
                if node.end:
                    return True
                continue
            depth += 1

            if word[depth] == '.':
                if not node.children:
                    continue
                for nd in node.children.values():
                    deque.append((nd, depth))
            elif word[depth] not in node.children:
                continue
            else:
                deque.append((node.children[word[depth]], depth))
        return False


'''
# Option #4
- Prefix Tree (DFS)
- addWord: O(L)                          (L = length of the word)
- search:
    - Best case  (no '.'):  O(L)
    - Worst case (all '.'): O(26^L)       (26 = alphabet size)
- Space:
    - Trie storage: O(N × L)              (N = number of words stored)
    - search recursion stack: O(L)         ← DFS backtracks, only 1 path alive at a time
- Date:
    - September 5, 2026
'''
class WordDictionaryDfs2:

    def __init__(self):
        self.root: Dict[str, TreeNode] = {}

    def addWord(self, word: str) -> None:
        if word[0] not in self.root:
            self.root[word[0]] = TreeNode()
        node: TreeNode = self.root[word[0]]
        n: int = len(word)
        for c in word[1:]:
            if c not in node.children:
                node.children[c] = TreeNode()
            node = node.children[c]
        node.end = True

    def search(self, word: str) -> bool:
        n: int = len(word)
        
        def dfs(i: int, node: TreeNode) -> bool:
            if i == n - 1:
                return node.end
            
            i += 1
            if word[i] == '.':
                if not node.children:
                    return False
                for nd in node.children.values():
                    if dfs(i, nd):
                        return True
            else:
                if word[i] not in node.children:
                    return False
                if dfs(i, node.children[word[i]]):
                    return True
            return False

        if word[0] == '.':
            for nd in self.root.values():
                if dfs(0, nd):
                    return True
            return False
        else:
            if word[0] not in self.root:
                return False
            return dfs(0, self.root[word[0]])






if __name__ == '__main__':
    wordDictionary = WordDictionary()
    wordDictionary.addWord("bad")
    wordDictionary.addWord("dad")
    wordDictionary.addWord("mad")
    
    print(wordDictionary.search("pad"))
    print(wordDictionary.search("bad"))
    print(wordDictionary.search(".ad"))
    print(wordDictionary.search("b.."))


    wordDictionary = WordDictionaryDfs()
    wordDictionary.addWord("bad")
    wordDictionary.addWord("dad")
    wordDictionary.addWord("mad")
    
    print(wordDictionary.search("pad"))
    print(wordDictionary.search("bad"))
    print(wordDictionary.search(".ad"))
    print(wordDictionary.search("b.."))