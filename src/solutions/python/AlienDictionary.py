from typing import List, Dict

'''
# Problem
	- `Link`
        - `LeetCode`: https://leetcode.com/problems/alien-dictionary/
        - `LintCode`: https://www.lintcode.com/problem/892/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 20
	- `Answer`: alienOrder / alien_order
'''

class Solution:

    """
	# Option #1
	- Recursive DFS
	- O(n * m + k + e) (n = words.length, m = the average length of words, k = total number of unique characters, e = number of edges)
	- ref) https://www.youtube.com/watch?v=6kTZYvNNyps
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


    """
	# Option #2
	- Recursive DFS
	- O(n * m + k + e) (n = words.length, m = the average length of words, k = total number of unique characters, e = number of edges)
    - August 11, 2026
    """
    def alien_order(self, words: List[str]) -> str:

        n: int = len(words)
        if n == 0:
            return ""


        # Find rules
        graph: Dict[str, Set[str]] = {}
        for i in range(n):
            for j in range(len(words[i])):
                if words[i][j] in graph:
                    continue
                graph[words[i][j]] = set()
                

        for i in range(1, n):
            if words[i].startswith(words[i - 1]):
                continue
            if words[i - 1].startswith(words[i]):
                return ""

            m: int = min(len(words[i - 1]), len(words[i]))
            for j in range(m):
                if words[i - 1][j] != words[i][j]:
                    graph[words[i][j]].add(words[i - 1][j])
                    break


        # Make string
        memo: Dict[str, bool] = {}
        def dfs(curr: str) -> str:

            if curr in memo:
                if memo[curr]:
                    return "#"
                return ""

            memo[curr] = True

            res = ""
            for s in graph[curr]:
                pre = dfs(s)
                if pre == '#':
                    return '#'
                res += pre

            memo[curr] = False

            return res + curr
        
        result = ""
        for key in graph.keys():
            prefix = dfs(key)
            if prefix == '#':
                return ""
            result += prefix
        return result




if __name__ == '__main__':

    solution = Solution()
    print(solution.alien_order(["wrt","wrf","er","ett","rftt"]))
    print(solution.alien_order(["z","x"]))
    print(solution.alien_order(["abc", "ab"]))
    print(solution.alien_order(["z", "x", "z"]))
    print(solution.alien_order(["wrt", "wrf", "er", "ett", "rftt"]))
    print(solution.alien_order(["abc", "abd"]))
    print(solution.alien_order( ["za", "zb", "ca", "cb"]))
    print(solution.alien_order(["abc", "abd", "acd"]))
    print(solution.alien_order(["cbb", "cab", "cac", "cca"]))
    print(solution.alien_order( ["wrt", "wrf", "er", "ett", "rftt"]))
    print(solution.alien_order( ["cac", "caa", "caa"]))
    print(solution.alien_order(["cca"]))