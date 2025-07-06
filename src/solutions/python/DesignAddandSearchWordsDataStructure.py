from typing import Optional, List
import collections

'''
# Problem
 	- `Link`: https://leetcode.com/problems/design-add-and-search-words-data-structure/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 6, 2025
	- `Answer`: WordDictionary / WordDictionaryPrefixTree
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
- Prefix Tree
- O(L), O(26^L) (L = The length of the word)
'''
class TreeNode:
    def __init__(self):
        self.children = {}
        self.end = False

class WordDictionaryPrefixTree:

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
    



if __name__ == '__main__':
    wordDictionary = WordDictionary()
    wordDictionary.addWord("bad")
    wordDictionary.addWord("dad")
    wordDictionary.addWord("mad")
    
    print(wordDictionary.search("pad"))
    print(wordDictionary.search("bad"))
    print(wordDictionary.search(".ad"))
    print(wordDictionary.search("b.."))


    wordDictionary = WordDictionaryPrefixTree()
    wordDictionary.addWord("bad")
    wordDictionary.addWord("dad")
    wordDictionary.addWord("mad")
    
    print(wordDictionary.search("pad"))
    print(wordDictionary.search("bad"))
    print(wordDictionary.search(".ad"))
    print(wordDictionary.search("b.."))