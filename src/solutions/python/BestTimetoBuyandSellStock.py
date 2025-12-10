from typing import List
import sys
'''
 # Problem
 	- `Link`: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 8, 2025
 	- `Answer`: maxProfit / maxProfit2 / maxProfit3
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
    
    
    '''
	 # Option #2
	 - O(n)
    '''
    def maxProfit2(self, prices: List[int]) -> int:
        
        m = len(prices)
        if not m: return 0

        result, curr_max = 0, prices[m - 1]
        for i in range(m - 2, -1, -1):
            result = max(result, curr_max - prices[i])
            curr_max = max(curr_max, prices[i])
        return result


    '''
	 # Option #3
     - Two Pointer
	 - O(n)
    '''
    def maxProfit3(self, prices: List[int]) -> int:
        left, right = 0, 1
        m, result = len(prices), 0
        while right < m:
            if prices[left] >= prices[right]:
                left = right
            else:
                result = max(result, prices[right] - prices[left])
            right += 1
        return result

if __name__ == "__main__":
    sol = Solution()
    print(sol.maxProfit([7,1,5,3,6,4]))
    print(sol.maxProfit([7,6,4,3,1]))