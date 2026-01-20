from typing import List
from collections import defaultdict
'''
# Problem
	- `Link`: https://leetcode.com/problems/group-anagrams/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Mar 25, 2025
	- `Answer`: groupAnagrams / groupAnagramsAdvanced / groupAnagramsBest / groupAnagramsSimple / groupAnagramsBest2
'''

class Solution:

    '''
    # Option #1
    - O(n * k * log k)
    '''
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        
        # Initialize dict to store items with the keys which are sorted string item
        my_dict = {}
        for str in strs:

            # Get sorted string value
            key = "".join(sorted(str))

            # Add values to dict
            if type(my_dict.get(key, None)) != list:
                my_dict[key] = list()
            my_dict[key].append(str)
        
        # Make the result List out of the Anagram dict
        # Slower than Option #2
        result = list()
        for key in my_dict.keys():
            result.append(my_dict.get(key))
        return result
    
    '''
    # Option #2
    - O(n * k * log k)
    - Not much faster than 'Option #1'
    '''
    def groupAnagramsAdvanced(self, strs: List[str]) -> List[List[str]]:
        # Use dictionary to group anagrams
        anagram_map = {}
        
        # Iterate through each string
        for s in strs:
            # Sort the string and use it as key
            sorted_str = ''.join(sorted(s))
            # Add to list of anagrams
            if sorted_str in anagram_map:
                anagram_map[sorted_str].append(s)
            else:
                anagram_map[sorted_str] = [s]
        
        # Return all grouped anagrams
        return list(anagram_map.values())
    
    '''
    # Option #3
    - O(n * m) (n = the number of strs, m = the length of average str)
    - Counting Sort Key
    '''
    def groupAnagramsBest(self, strs: List[str]) -> List[List[str]]:

        result = defaultdict(list) # There's a default value, in this case, list
        
        for s in strs:

            count = [0] * 26

            for c in s:
                count[ord(c) - ord('a')] += 1
                
            result[tuple(count)].append(s) # Array cannot be key of dicts, so use tuples

        return list(result.values())
    
    '''
    # Option #4
    - O(n * k * log k)
    - Simple
    - Jan 19, 2026
    '''
    def groupAnagramsSimple(self, strs: List[str]) -> List[List[str]]:
        
        memo = {}
        result = []

        for str in strs:
            sortedStr = "".join(sorted(str))
            if sortedStr in memo:
                result[memo[sortedStr]].append(str)
            else:
                memo[sortedStr] = len(result)
                result.append([str])

        return result
    
    '''
    # Option #5
    - O(n * m) (n = the number of strs, m = the length of average str)
    - Counting Sort Key (Similar to Option #3)
    - Jan 19, 2026
    '''
    def groupAnagramsBest2(self, strs: List[str]) -> List[List[str]]:

        memo = {}
        
        for s in strs:
            count = [0] * 26
            
            for c in s:
                count[ord(c) - ord('a')] += 1
            
            # key = "#".join(map(str, count)) # Slow
            key = tuple(count) # Faster by using tuple as a key
            if key in memo:
                memo[key].append(s)
            else:
                memo[key] = [s]
        
        return list(memo.values())


if __name__ == '__main__':
    sol = Solution()
    print(sol.groupAnagrams(["eat","tea","tan","ate","nat","bat"])) # [["bat"],["nat","tan"],["ate","eat","tea"]]
    print(sol.groupAnagrams([""])) # [[""]]
    print(sol.groupAnagrams(["a"])) # [["a"]]

    print("---")

    print(sol.groupAnagramsAdvanced(["eat","tea","tan","ate","nat","bat"])) # [["bat"],["nat","tan"],["ate","eat","tea"]]
    print(sol.groupAnagramsAdvanced([""])) # [[""]]
    print(sol.groupAnagramsAdvanced(["a"])) # [["a"]]