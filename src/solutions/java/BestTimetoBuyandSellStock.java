package solutions.java;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 8, 2025
 	- `Answer`: maxProfit / maxProfit2 / maxProfit3
 */

public class BestTimetoBuyandSellStock {
	
	public static void main(String[] args) {
		
		int[] a = new int[] {7,1,5,3,6,4};
		System.out.println(maxProfit(a));
		
	}
	

	/*
	 * Option #1
	 * O(n)
	 */
    public static int maxProfit(int[] prices) {
        int result = 0;
        int min = prices[0];

        for (int i = 1; i < prices.length; i++) {
        	int price = prices[i];
        	if (min > price) {
        		min = price;
        	} else if (result < price - min) {
        		result = price - min;
        	}
        }
        return result;
    }

	/*
	 * Option #2
	 * O(n)
	 */
    public int maxProfit2(int[] prices) {
        int m = prices.length;
        if (m == 0) return 0;
        int result = 0, max = prices[m - 1];

        for (int i = m - 2; i >= 0; i--) {
            result = Math.max(result, max - prices[i]);
            max = Math.max(max, prices[i]);
        }

        return result;
    }

	/*
	 * Option #3
	 * Two Pointer
	 * O(n)
	 */
    public int maxProfit3(int[] prices) {
		int left = 0, right = 1;
		int m = prices.length;
		int result = 0;

		while (right < m) {
			if (prices[left] >= prices[right]) left = right;
			else {
				result = Math.max(result, prices[right] - prices[left]);
			}
			right++;
		}

		return result;
	}


}
