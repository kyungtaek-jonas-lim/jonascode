
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/climbing-stairs/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Feb 15, 2025
 	- `Answer`: climbStairs
 # Reference
	- https://github.com/kyungtaek-jonas-lim/jonascode/blob/main/src/explanation/ClimbingStairs.md
'''


class Solution:
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
        

solution = Solution()
if __name__ == "__main__":
    print(solution.climbStairs(n=2))
    print(solution.climbStairs(n=5))