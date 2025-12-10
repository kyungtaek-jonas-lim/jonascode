/*
 # Problem
 	- `Link`: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: May 8, 2025
 	- `Answer`: maxProfit / maxProfit2 / maxProfit3
*/

/*
* Option #1
* O(n)
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


/*
* Option #2
* O(n)
*/
function maxProfit2(prices: number[]): number {
    
    const m: number = prices.length;
    if (m === 0) return 0;
    
    let max: number = prices[m - 1];
    let result: number = 0;
    
    for (let i = m - 2; i >= 0; i--) {
        result = Math.max(result, max - prices[i]);
        max = Math.max(max, prices[i]);
    }

    return result;
};

/*
* Option #3
* Two Pointer
* O(n)
*/
function maxProfit3(prices: number[]): number {
    let left: number = 0, right: number = 1, result: number = 0;
    const m: number = prices.length;
    while (right < m) {
        if (prices[left] >= prices[right]) left = right;
        else {
            result = Math.max(result, prices[right] - prices[left]);
        }
        right++;
    }
    return result;
}