from typing import List

'''
# Problem
	- `Link`: https://leetcode.com/problems/group-anagrams/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: Mar 25, 2025
	- `Answer`: groupAnagrams / groupAnagramsAdvanced
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


if __name__ == '__main__':
    sol = Solution()
    print(sol.groupAnagrams(["eat","tea","tan","ate","nat","bat"])) # [["bat"],["nat","tan"],["ate","eat","tea"]]
    print(sol.groupAnagrams([""])) # [[""]]
    print(sol.groupAnagrams(["a"])) # [["a"]]

    print("---")

    print(sol.groupAnagramsAdvanced(["eat","tea","tan","ate","nat","bat"])) # [["bat"],["nat","tan"],["ate","eat","tea"]]
    print(sol.groupAnagramsAdvanced([""])) # [[""]]
    print(sol.groupAnagramsAdvanced(["a"])) # [["a"]]