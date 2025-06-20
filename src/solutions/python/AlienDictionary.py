from typing import List

'''
# Problem
	- `Link`
        - `LeetCode`: https://leetcode.com/problems/alien-dictionary/
        - `LintCode`: https://www.lintcode.com/problem/892/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 20
	- `Answer`: alienOrder
'''

class Solution:

    """
    """
    def alienOrder(self, words: List[str]) -> str:
        

        # Step 1: Create a graph with all unique characters as nodes
        my_dict: dict = {}
        for word in words:
            for c in word:
                my_dict[c] = set() # Initialize adjacency set for each character


        # Step 2: Build edges (graph) from adjacent words
        n: int = len(words)        
        for i in range(n - 1):
            prev, curr = words[i], words[i + 1]

            len_prev, len_curr = len(prev), len(curr)
            len_min = min(len_prev, len_curr)

            # Invalid case: If the first `min(s.length, t.length)` letters are the same, then `s`` is smaller if and only if `s.length < t.length`.
            if len_prev > len_curr and prev[:len_min] == curr[:len_min]:
                return ""
            
            # Compare characters and add the first mismatch as a directed edge
            for j in range(len_min):
                if prev[j] != curr[j]:
                    my_dict[prev[j]].add(curr[j]) # Edge (graph): prev[j] -> curr[j]
                    break
        

        # Step 3: DFS + Cycle detection + Topological sort
        visit = {} # visit[c] = True (visiting), False (visited and done)
        result = []
        def dfs(c: str):
            if c in visit:
                return visit[c] # Return True if cycle detected
            
            visit[c] = True # Mark as visiting

            for char_after_c in my_dict[c]:
                if dfs(char_after_c): # If a cycle is found in the path
                    return True

            visit[c] = False # Mark as finished
            result.append(c) # Post-order appending for topological order
            return False
        

        # Step 4: Visit all nodes
        for c in my_dict:
            if dfs(c): # If cycle found, return ""
                return ""
            

        # Step 5: Return the reversed string
        result.reverse()
        return "".join(result)