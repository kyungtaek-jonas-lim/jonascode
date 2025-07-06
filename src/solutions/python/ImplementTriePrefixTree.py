from typing import Optional, List
import collections

'''
# Problem
 	- `Link`: https://leetcode.com/problems/implement-trie-prefix-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 6, 2025
	- `Answer`: Trie
'''


'''
# Option #1
- dict, set
- O(K), O(1), O(1)
'''
class Trie:
    def __init__(self):
        self.my_set = set()
        self.my_dict = {}

    # O(k) (k == the length of the word)
    def insert(self, word: str) -> None:
        self.my_set.add(word)
        prefix = ""
        for c in word:
            prefix += c
            self.my_dict[prefix] = True

    # O(1)
    def search(self, word: str) -> bool:
        return word in self.my_set

    # O(1)
    def startsWith(self, prefix: str) -> bool:
        return self.my_dict.get(prefix, False)




'''
# Option #2
- Trie (Prefix Tree)
- O(L), O(L), O(P) (L = The length of the words, P = the length of the prefixes)
- ref) https://www.youtube.com/watch?v=oobqoCJlHA0
'''
class TreeNode:
    def __init__(self):
        self.children = {}
        self.end = False # You cannot tell the end of the tree with only children cause nodes are shared for other words

class TriePrefixTree:

    def __init__(self):
        self.root = TreeNode()

    # O(L) (L = The length of the words)
    def insert(self, word: str) -> None:
        curr = self.root
        for c in word:
            if c not in curr.children:
                curr.children[c] = TreeNode()
            curr = curr.children[c]
        curr.end = True

    # O(L) (L = The length of the words)
    def search(self, word: str) -> bool:
        curr = self.root
        for c in word:
            if c not in curr.children:
                return False
            curr = curr.children[c]
        return curr.end

    # O(P) (P = The length of the prefixes)
    def startsWith(self, prefix: str) -> bool:
        curr = self.root
        for c in prefix:
            if c not in curr.children:
                return False
            curr = curr.children[c]
        return True


    
if __name__ == '__main__':
    word = "1234"
    prefix = "12"
    obj = Trie()
    obj.insert(word)
    param_2 = obj.search(word)
    param_3 = obj.startsWith(prefix)
    
    word = "1234"
    prefix = "12"
    obj = TriePrefixTree()
    obj.insert(word)
    param_2 = obj.search(word)
    param_3 = obj.startsWith(prefix)
    print(param_2, param_3)