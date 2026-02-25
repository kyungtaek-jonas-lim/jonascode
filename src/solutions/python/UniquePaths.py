
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/unique-paths/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 7, 2025
 	- `Answer`: uniquePaths / uniquePathsAdvanced / uniquePathsDfs
'''

class Solution:

    '''
    # Option #1
    - O (m * n)
    - 2D Array
    '''
    def uniquePaths(self, m: int, n: int) -> int:
        
        # Initialize m * n 2d array
        dp = [[0] * (n) for _ in range(m)]
        
        for i in range(0, m):
            dp[i][0] = 1 # To get to (i, 0), there's only one way. (set to 1)
            for j in range(1, n):
                if i == 0:
                    dp[i][j] = 1 # To get to (0, j), there's only one way. (set to 1)
                else:
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j] # To get to (i, j), the number of way will be the way to get to (i - 1, j) and (i, j - 1) as the robot only can move either right or down at any point in time.

        # The number of ways to bottom right corner
        return dp[m - 1][n - 1]


    '''
    # Option #2
    - O (m * n)
    - 1D Array
    '''
    def uniquePathsAdvanced(self, m: int, n: int) -> int:
        
        # Initialize a 1D DP array with size n
        # For the first row, all values are 1 (only one way: move right)
        dp = [1] * n

        # Start from the second row
        for i in range(1, m):
            for j in range(1, n):
                # The number of ways to reach (i, j) is the sum of:
                #   - ways to reach (i-1, j)
                #   - ways to reach (i, j-1)
                dp[j] += dp[j - 1]

        # The number of ways to bottom right corner
        return dp[n - 1]
    

    '''
    # Option #3
    - O (m * n)
    - DFS + Memoization
    '''
    def uniquePathsDfs(self, m: int, n: int) -> int:
        
        memo = {}

        def dfs(x: int, y: int) -> int:
            if x >= m or y >= n:
                return 0
            if x == m - 1 and y == n - 1:
                return 1
            if (x, y) in memo:
                return memo[(x, y)]
            
            res = dfs(x + 1, y) + dfs(x, y + 1)
            memo[(x, y)] = res
            return res
                
        return dfs(0, 0)

                 
if __name__ == '__main__':
    sol = Solution()
    print(sol.uniquePaths(3, 7)) # 28
    print(sol.uniquePaths(3, 2)) # 3
    
    print("---")
    print(sol.uniquePaths(3, 7)) # 28
    print(sol.uniquePaths(3, 2)) # 3