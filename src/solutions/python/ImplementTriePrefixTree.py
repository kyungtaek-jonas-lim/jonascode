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

    
if __name__ == '__main__':
    word = "1234"
    prefix = "12"
    obj = Trie()
    obj.insert(word)
    param_2 = obj.search(word)
    param_3 = obj.startsWith(prefix)