from typing import List

'''
# Problem
	- `Link`: https://leetcode.com/problems/course-schedule/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 18
	- `Answer`: canFinish
'''

class Solution:

    '''
    # Option #1
    - O (n + p) (n = numCourses, p = the number of prerequisites)
    '''
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        
        pres = { i: set() for i in range(numCourses)}

        for curr, pre in prerequisites:
            pres[curr].add(pre)
        
        visited = {}
        def dfs(curr: int) -> bool:
            if curr in visited: return visited[curr]
            
            visited[curr] = False

            for pre in pres[curr]:
                if not dfs(pre): return False

            visited[curr] = True

            return True

        for i in range(numCourses):
            if not dfs(i): return False
        return True