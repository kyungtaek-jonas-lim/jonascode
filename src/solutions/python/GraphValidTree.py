from typing import List, Dict, Set
import collections

'''
# Problem
	- `Link`
        - `LeetCode`: https://leetcode.com/problems/graph-valid-tree/
        - `LintCode`: https://www.lintcode.com/problem/178/
# Solution
	- `Author`: Kyungtaek Lim (Jonas)
	- `Date`: June 23
	- `Answer`: validTreeBfs / validTreeDfs / validTreeDfs2 / validTreeUnionFind
'''


class Solution:

    '''
    # Option #1
    - BFS
    - O(n + e)
    '''
    def validTreeBfs(self, n: int, edges: List[List[int]]) -> bool:
        
        if len(edges) != n - 1:
            return False
        
        memo: dict = { x: [] for x in range(n) }
        
        for i in range(len(edges)):
            memo[edges[i][0]].append(edges[i][1])
            memo[edges[i][1]].append(edges[i][0])

        def bfs():
            visited = set()
            my_deque = collections.deque()
            my_deque.append((0, -1))

            while my_deque:
                curr, prev = my_deque.popleft()
                if curr in visited:
                    return False
                visited.add(curr)

                for neighbor  in memo.get(curr):
                    if neighbor  == prev: continue
                    my_deque.append((neighbor , curr))

            return len(visited) == n

        return bfs()


    '''
    # Option #2
    - DFS
    - O(n + e)
    - ref) https://www.youtube.com/watch?v=bXsUuownnoQ
    '''
    def validTreeDfs(self, n: int, edges: List[List[int]]) -> bool:
        
        if len(edges) != n - 1:
            return False
        
        memo: dict = { x: [] for x in range(n) }

        for n1, n2 in edges:
            memo[n1].append(n2)
            memo[n2].append(n1)
        
        visited = set()
        def dfs(curr: int, prev: int) -> bool:
            if curr in visited:
                return False
            
            visited.add(curr)
            for neighbor in memo[curr]:
                if neighbor == prev: continue
                if not dfs(neighbor, curr):
                    return False

            return True
        
        return dfs(0, -1) and n == len(visited)


    '''
    # Option #3
    - DFS (Basically, the same as Option #2)
    - O(n + e)
    - August 14, 2026
    '''
    def validTreeDfs2(self, n: int, edges: List[List[int]]) -> bool:
        if n - 1 != len(edges):
            return False

        graph: Dict[int, List[int]] = {}
        for i in range(n):
            graph[i] = []
        for e in edges:
            graph[e[0]].append(e[1])
            graph[e[1]].append(e[0])

        visited: Set[int] = set()
        def hasCycle(curr: int, parent: int) -> bool:
            visited.add(curr)
            for nei in graph[curr]:
                if nei == parent:
                    continue
                if nei in visited or hasCycle(nei, curr):
                    return True
            return False

        if hasCycle(0, -1):
            return False

        return len(visited) == n


    '''
    # Option #4
    - Union-Find
    - O(n + e)
    - August 14, 2026
    '''
    def validTreeUnionFind(self, n: int, edges: List[List[int]]) -> bool:

        if n - 1 != len(edges):
            return False

        parents: List[int] = [i for i in range(n)]
        def find(curr: int) -> int: # Find the parent
            while curr != parents[curr]:
                parents[curr] = parents[parents[curr]]
                curr = parents[curr]
            return curr

        for e in edges:
            p1: int = find(e[0])
            p2: int = find(e[1])
            if p1 == p2: # Cycle
                return False
            parents[p1] = p2
        return True # Survived all edges with no cycle, and had exactly n-1 of them

    

if __name__ == '__main__':
    solution = Solution()

    print(solution.validTree(5, [[0,1],[0,2],[0,3],[1,4]]))
    # True

    print(solution.validTree(5, [[0,1],[1,2],[2,3],[1,3],[1,4]]))
    # False  # cycle: 1 -> 2 -> 3 -> 1

    print(solution.validTree(4, [[1,0],[2,0],[3,0]]))
    # True  # star tree

    print(solution.validTree(4, [[0,1],[0,2],[3,1]]))
    # True  # valid tree, edge written "backwards"

    print(solution.validTree(5, [[0,1],[2,3]]))
    # False  # disconnected forest, also wrong edge count

    print(solution.validTree(2, []))
    # False  # disconnected, needs 1 edge

    print(solution.validTree(1, []))
    # True  # single node, trivially valid

    print(solution.validTree(4, [[0,1],[1,2],[2,3]]))
    # True  # straight line (path graph)