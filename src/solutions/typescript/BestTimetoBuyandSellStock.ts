/*
 # Problem
 	- `Link`: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: May 8, 2025
 	- `Answer`: maxProfit
*/

function maxProfit(prices: number[]): number {
    
    let result: number = 0;
    let n: number = prices.length;
    let min: number = prices[0];
    for (const price of prices.slice(1)) {
        if (min > price) {
            min = price;
        } else {
            result = Math.max(result, price - min);
        }
    }
    return result;
};