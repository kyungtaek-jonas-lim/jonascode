from typing import List
import sys
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 8, 2025
 	- `Answer`: maxProfit
'''

class Solution:    

    '''
	 # Option #1
	 - O(n)
    '''
    def maxProfit(self, prices: List[int]) -> int:
        result = 0
        min = prices[0]

        for price in prices[1:]:
            if min > price:
                min = price
            elif result < price - min:
                result = price - min

        return result

if __name__ == "__main__":
    sol = Solution()
    print(sol.maxProfit([7,1,5,3,6,4]))
    print(sol.maxProfit([7,6,4,3,1]))