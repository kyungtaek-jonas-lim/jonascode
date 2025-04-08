package solutions.java;

/*
 # Problem
 	- `Link`: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 # Solution
 	- `Author`: Kyungtaek Lim (Jonas)
 	- `Date`: Apr 8, 2025
 	- `Answer`: maxProfit
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
}
