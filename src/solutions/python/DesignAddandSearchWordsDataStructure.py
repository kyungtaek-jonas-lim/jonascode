from typing import Optional, List
import collections

'''
# Problem
 	- `Link`: https://leetcode.com/problems/design-add-and-search-words-data-structure/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: July 6, 2025
	- `Answer`: WordDictionary
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


if __name__ == '__main__':
    wordDictionary = WordDictionary()
    wordDictionary.addWord("bad")
    wordDictionary.addWord("dad")
    wordDictionary.addWord("mad")
    
    print(wordDictionary.search("pad"))
    print(wordDictionary.search("bad"))
    print(wordDictionary.search(".ad"))
    print(wordDictionary.search("b.."))