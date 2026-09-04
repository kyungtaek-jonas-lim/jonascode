from typing import Optional, List, Dict
import collections

'''
# Problem
 	- `Link`: https://leetcode.com/problems/implement-trie-prefix-tree/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 6, 2025
	- `Answer`: Trie / TriePrefixTree
'''


'''
# Option #1
- dict, set
- O(K^2), O(1), O(1)
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




'''
# Option #3
- Trie (Prefix Tree)
- O(L), O(W·L), O(W·L) (L = length of the word/prefix you're searching for, W = number of stored words that start with the same first letter as your query (worst case))
- Slower because the root has all the words even though they start with the same alphabet (e.g., 'app' and 'apple' have separate nodes not even partially.)
- September 4, 2026
'''

class Node:
    def __init__(self, val: str, next: Node):
        self.val = val
        self.next = next

class Trie:

    def __init__(self):
        self.map: Dict[str, List[Node]] = {}

    def insert(self, word: str) -> None:
        n: int = len(word)
        node: Optional[Node] = None
        for i in range(n - 1, -1, -1):
            node = Node(word[i], node)
            
        if word[0] not in self.map:
            self.map[word[0]] = []
        self.map[word[0]].append(node)
            

    def search(self, word: str) -> bool:
        if word[0] not in self.map:
            return False
        n: int = len(word)
        for node in self.map[word[0]]:
            node = node.next
            success: bool = True
            for i in range(1, n):
                if not node or node.val != word[i]:
                    success = False
                    break
                node = node.next
            if success and node == None:
                return True
        return False
        

    def startsWith(self, prefix: str) -> bool:
        if prefix[0] not in self.map:
            return False
        n: int = len(prefix)
        for node in self.map[prefix[0]]:
            node = node.next
            success: bool = True
            for i in range(1, n):
                if not node or node.val != prefix[i]:
                    success = False
                    break
                node = node.next
            if success:
                return True
        return False




'''
# Option #4
- Trie (Prefix Tree) - The same as Option #2
- O(L), O(L), O(P) (L = The length of the words, P = the length of the prefixes)
- September 4, 2026
'''
class Node:
    def __init__(self):
        self.next: Dict[str, Node] = {}
        self.end: bool = False

class Trie:

    def __init__(self):
        self.root: Dict[str, Node] = {}

    def insert(self, word: str) -> None:
        n: int = len(word)
        if word[0] not in self.root:
            self.root[word[0]] = Node()
        node: Node = self.root[word[0]]

        for c in word[1:]:
            if c not in node.next.keys():
                node.next[c] = Node()
            node = node.next[c]
            
        node.end = True

    def search(self, word: str) -> bool:
        if word[0] not in self.root:
            return False
            
        node = self.root[word[0]]
        for c in word[1:]:
            if c not in node.next.keys():
                return False
            node = node.next[c]
        return node.end
        

    def startsWith(self, prefix: str) -> bool:
        if prefix[0] not in self.root:
            return False
            
        node = self.root[prefix[0]]
        for c in prefix[1:]:
            if c not in node.next.keys():
                return False
            node = node.next[c]
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