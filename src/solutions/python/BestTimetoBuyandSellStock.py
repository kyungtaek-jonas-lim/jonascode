from typing import List
import sys
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Feb 23, 2025
 	- `Answer`: maxProfit
'''

class Solution:    
    def maxProfit(self, prices: List[int]) -> int:
        result = 0
        price_min = sys.maxsize
        for price in prices:
            if price_min > price:
                price_min = price
                continue
            result = max(result, price - price_min)
        return result

if __name__ == "__main__":
    sol = Solution()
    print(sol.maxProfit([7,1,5,3,6,4]))
    print(sol.maxProfit([7,6,4,3,1]))