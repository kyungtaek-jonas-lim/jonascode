from typing import List
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/number-of-provinces/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: July 1, 2025
 	- `Answer`: findCircleNum
'''

class Solution:

    '''
	# Option #1
	- Union-find
	- O(n^2)
    '''
    def findCircleNum(self, isConnected: List[List[int]]) -> int:

        n: int = len(isConnected)
        parents: List[int] = [i for i in range(n)]
        ranks: List[int] = [1] * n
        
        def find(n: int) -> int:
            res: int = n
            while parents[res] != res:
                parents[res] = parents[parents[res]]
                res = parents[res]
            return res

        def union(n1: int, n2: int) -> int:
            p1, p2 = find(n1), find(n2)

            if p1 == p2: return 0

            if ranks[p1] >= ranks[p2]:
                ranks[p1] += ranks[p2]
                parents[p2] = p1
            else:
                ranks[p2] += ranks[p1]
                parents[p1] = p2

            return 1
        
        result: int = n
        for i in range(n):
            for j in range(i + 1, n):
                if isConnected[i][j]:
                    result -= union(i, j)

        return result