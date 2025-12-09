
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/climbing-stairs/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Feb 15, 2025
 	- `Answer`: climbStairs
 # Reference
	- https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/doc/explanation/ClimbingStairs.md
'''


class Solution:

    '''
    # Option #1
    - Fibonacci
    - O(n)
    '''
    def climbStairs(self, n: int) -> int:
        if n <= 2:
            return n
        before = 2
        current = 3
        for i in range(4, n + 1):
            temp = before
            before = current
            current += temp
        return current
    
    '''
    # Option #2
    - DFS with Memoization
    - O(n)
    '''
    def climbStairsAdvanced(self, n: int) -> int:
        if n < 3: return n
        res = [0] * (n + 1)
        res[n], res[n - 1] = 1, 1
        for i in range(n - 2, -1, -1):
            res[i] = res[i + 1] + res[i + 2]
        return res[0]
    

    '''
    # Option #3
    - DFS with Memoization & Space Optimization
    - O(n)
    '''
    def climbStairsBest(self, n: int) -> int:
        one, two = 1, 1
        for _ in range(n - 2, -1, -1):
            one, two = one + two, one
        return one
        

solution = Solution()
if __name__ == "__main__":
    print(solution.climbStairs(n=2))
    print(solution.climbStairs(n=5))