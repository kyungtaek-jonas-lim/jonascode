from typing import List

'''
 # Problem
 	- `Link`: https://leetcode.com/problems/word-break/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 6, 2025
 	- `Answer`: wordBreak / wordBreakBetter/ wordBreakAdvanced
'''
class Solution:

    '''
    # Option #1
    - Dynamic Programming
    - DFS + Memoization
    - O(n * k * L) (n = len(s), k = number of words in wordDict, L = average length of the words in wordDict)
    '''
    class Solution:
        def wordBreak(self, s: str, wordDict: List[str]) -> bool:
            
            n = len(s)
            memo = [0] * n

            def dfs(index: int) -> bool:
                
                if index == n:
                    return True
                if memo[index] != 0:
                    return True if memo[index] == 1 else False
                
                for word in wordDict:
                    len_word = len(word)
                    if s[index:index + len_word] == word:
                        if dfs(index + len_word):
                            memo[index] = 1
                            return True
                
                memo[index] = -1
                return False

            return dfs(0)

    '''
    # Option #2
    - Dynamic Programming
    - Optimized Bottom Up
    - O(n * k * L) (n = len(s), k = number of words in wordDict, L = average length of the words in wordDict)
    - ref: Neetcode (https://www.youtube.com/watch?v=Sx9NNgInc3A)
    '''
    def wordBreakBetter(self, s: str, wordDict: List[str]) -> bool:

        '''
        # 1
        Starting from the last index of the string, the code checks whether any word from wordDict matches the substring.
        
        # 2
        If a match is found, set the value of the boolean array with the string index to the value of 'array[index - word_length]'. (array[string_length] = True)

        # 3
        If array[index] is already True, no need to checks further for the 'index' (so break the inner loop).
        
        # 4
        return array[0] (It's True if the string can be segmented using words from wordDict.)
        '''

        len_s = len(s)
        dp = [False] * len_s
        dp += [True]

        for i in range(len_s - 1, -1, -1):
            for w in wordDict:
                # 1
                len_w = len(w)
                if i + len(w) <= len_s and s[i:i + len_w] == w:
                    # 2
                    dp[i] = dp[i + len_w]
                
                # 3
                if dp[i]:
                    break
        
        # 4
        return dp[0]

    '''
    # Option #3
    - Dynamic Programming
    - Optimized Bottom Up
    - O(n * m) (m: max word Lenth)
    '''
    def wordBreakAdvanced(self, s: str, wordDict: List[str]) -> bool:
        # Convert the word list to a set for faster lookups (O(1) average case)
        word_set = set(wordDict)

        # Precompute the maximum length of words in the dictionary.
        # This allows us to avoid unnecessary checks for substrings longer than any word.
        max_len = max(map(len, word_set)) if word_set else 0

        # dp[i] is True if s[0:i] can be segmented using words from the dictionary.
        dp = [False] * (len(s) + 1)

        # Base case: empty string is always segmentable
        dp[0] = True

        # Loop over the length of the prefix we're checking (1 to len(s))
        for i in range(1, len(s) + 1):
            # Instead of checking all j from 0 to i,
            # we only check substrings of lengths that match possible word lengths.
            # This improves performance significantly when wordDict contains short words.
            for j in range(1, min(i, max_len) + 1):
                # If the prefix up to i - l is not segmentable, skip
                if not dp[i - j]:
                    continue

                # Extract the substring of length j ending at i
                if s[i - j:i] in word_set:
                    dp[i] = True
                    break # No need to check longer substrings once a match is found
        
        # The final result tells us whether the full string s[0:len(s)] is segmentable
        return dp[len(s)]
    
    
if __name__ == "__main__":
    sol = Solution()
    print(sol.wordBreak("leetcode", ["leet","code"]) == True)
    print(sol.wordBreak("applepenapple", ["apple","pen"]) == True)
    print(sol.wordBreak("catsandog", ["cats","dog","sand","and","cat"]) == False)
    print(sol.wordBreak("cars", ["car","ca","rs"]) == True)
    print(sol.wordBreak("ccbb", ["bc","cb"]) == False)

    print("---")
    print(sol.wordBreakAdvanced("leetcode", ["leet","code"]) == True)
    print(sol.wordBreakAdvanced("applepenapple", ["apple","pen"]) == True)
    print(sol.wordBreakAdvanced("catsandog", ["cats","dog","sand","and","cat"]) == False)
    print(sol.wordBreakAdvanced("cars", ["car","ca","rs"]) == True)
    print(sol.wordBreakAdvanced("ccbb", ["bc","cb"]) == False)